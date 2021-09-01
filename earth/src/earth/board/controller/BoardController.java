package earth.board.controller;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import earth.board.dto.BoardDTO;
import earth.board.service.BoardServiceImpl;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	// 자동주입
	@Autowired
	private BoardServiceImpl boardService = null;

	// list page
	@RequestMapping("list.do")	// list.do?pageNum=.......... pageNum 파라미터 넘기기
	public String list(String pageNum, String sel, String search, Model model) throws SQLException{
		// 해당 페이지에 맞게 화면에 보여줄 게시글 가져와서 view 에게 전달 (pageNum 에 맞는 게시글 목록 가져오기)
		Map<String, Object> result = boardService.getArticleList(pageNum);
		// 전체 게시글 가져오기
		if(sel == null || search == null) {
			result = boardService.getArticleList(pageNum);
		}else { // 검색 게시글 가져오기
			result = boardService.getArticleSearch(pageNum, sel, search); 
		}
		
		// view 에 전달할 데이터 보내기
		model.addAttribute("pageSize", result.get("pageSize"));
		model.addAttribute("pageNum", result.get("pageNum"));
		model.addAttribute("currentPage", result.get("currentPage"));
		model.addAttribute("startRow", result.get("startRow"));
		model.addAttribute("endRow", result.get("endRow"));
		model.addAttribute("articleList", result.get("articleList"));
		model.addAttribute("count", result.get("count"));
		model.addAttribute("number", result.get("number"));
		model.addAttribute("sel", sel);
		model.addAttribute("search", search);
		
		return "board/list";
	}

	// 글 작성 writeForm page
	@RequestMapping("writeForm.do")
	public String writeForm(HttpServletRequest request, Model model) {
		//
		int num = 0, ref = 0, re_step = 0, re_level = 0;
		if(request.getParameter("num") != null) {
			num = Integer.parseInt(request.getParameter("num"));
			ref = Integer.parseInt(request.getParameter("ref"));
			re_step = Integer.parseInt(request.getParameter("re_step"));
			re_level = Integer.parseInt(request.getParameter("re_level"));
		}
		
		model.addAttribute("num", num);
		model.addAttribute("ref", ref);
		model.addAttribute("re_step", re_step);
		model.addAttribute("re_level", re_level);
		model.addAttribute("id", "java");	// test용 id. 나중에 session id 받으므로 그때 지우기

		return "board/writeForm";
	}
	// 글 작성 처리 페이지
	@RequestMapping("writePro.do")
	public String writePro(BoardDTO dto) throws SQLException {
		dto.setWriter("java");	// test용. 나중에 session애서 id 꺼내 추가로 변경하기
		boardService.insertArticle(dto);
		
		return "redirect:/board/list.do";
	}
	
	// 글 본문 페이지 요청 content page
	@RequestMapping("content.do")
	public String content(@ModelAttribute("pageNum") String pageNum, int num, Model model) throws SQLException {		// Integer num 이라 해도 됨
																														// 어노테이션 ModelAttrubute 로 pageNum을 view인 content.jsp 페이지로 바로 넘김
		// 글 고유번호 넘겨 주면서 해당 글에 대한 본문 내용 받아와 view에 전달
		BoardDTO article = boardService.getArticle(num);
		
		model.addAttribute("article", article);
		
		return "board/content";
	}
	
	// 글 수정 폼 modifyForm page
	@RequestMapping("modifyForm.do")
	public String modifyForm(@ModelAttribute("pageNum") String pageNum, int num, Model model) throws SQLException {	
		// 글 고유번호 num을 넘겨주면서 해당 글 전체 정보 불러오기
		BoardDTO article = boardService.getUpdateArticle(num);		
		model.addAttribute("article", article);
		
		return "board/modifyForm";
	}
	// 글 수정 처리 modifyPro page
	@RequestMapping("modifyPro.do")
	public String modifyPro(@ModelAttribute("pageNum") String pageNum, BoardDTO dto, Model model) throws SQLException{
		int result = boardService.updateArticle(dto);
		model.addAttribute("result", result);
		
		return "board/modifyPro";
	}
	// 글 삭제 폼 deleteForm
	@RequestMapping("deleteForm.do")
	public String deleteForm(@ModelAttribute("pageNum") String pageNum, @ModelAttribute("num") String num) {
		return "board/deleteForm";
	}
	// 글 삭제 처리 deletePro
	@RequestMapping("deletePro.do")
	public String deletePro(@ModelAttribute("pageNum") String pageNum, BoardDTO dto, Model model) throws SQLException {
		int result = boardService.deleteArticle(dto);
		model.addAttribute("result", result);
		return "board/deletePro";
	}



}
