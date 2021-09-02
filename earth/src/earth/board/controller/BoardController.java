package earth.board.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import earth.board.dto.BoardDTO;
import earth.board.dto.NoticeDTO;
import earth.board.dto.TodayDTO;
import earth.board.service.BoardServiceImpl;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	// 자동주입
	@Autowired
	private BoardServiceImpl boardService = null;

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
	
	// 게시글 목록 조회
	// 공지사항(1) 목록 요청 - 노현호
	@RequestMapping("noticeList.et")
	public String notice(String pageNum, Model model, HttpSession session) throws SQLException{
		System.out.println("공지사항 목록 요청");
		
		// today 테이블에서 전체 게시글 가져오기
		int code = 1;
		Map<String, Object> result = boardService.getArticleList(pageNum, code);
		
		// view 에 전달할 데이터 보내기
		model.addAttribute("pageSize", result.get("pageSize"));
		model.addAttribute("pageNum", result.get("pageNum"));
		model.addAttribute("currentPage", result.get("currentPage"));
		model.addAttribute("startRow", result.get("startRow"));
		model.addAttribute("endRow", result.get("endRow"));
		model.addAttribute("articleList", result.get("articleList"));
		model.addAttribute("count", result.get("count"));
		model.addAttribute("number", result.get("number"));
		
		return "board/noticeList";
	}
	
	// 오늘의 실천(5) 요청 - 노현호
	@RequestMapping("dailyChallenge.et")
	public String dailyChallenge(String pageNum, Model model) throws SQLException{
		System.out.println("오늘의 실천 요청");
		
		// today 테이블에서 전체 게시글 가져오기
		int code = 5;
		Map<String, Object> result = boardService.getArticleList(pageNum, code);
		
		// view 에 전달할 데이터 보내기
		model.addAttribute("pageSize", result.get("pageSize"));
		model.addAttribute("pageNum", result.get("pageNum"));
		model.addAttribute("currentPage", result.get("currentPage"));
		model.addAttribute("startRow", result.get("startRow"));
		model.addAttribute("endRow", result.get("endRow"));
		model.addAttribute("articleList", result.get("articleList"));
		model.addAttribute("count", result.get("count"));
		model.addAttribute("number", result.get("number"));
		
		return "board/dailyChallenge";
	}
	
	// 게시글 등록
	// 공지사항 게시글 등록 페이지 - 노현호
	@RequestMapping("noticeWriteForm.et")
	public String noticeWriteForm(HttpServletRequest request, Model model, HttpSession session) throws SQLException{
		System.out.println("공지사항 게시글 등록 페이지 요청");
		
		/*
 		//admin이 아니면 접근 불가능하게 해야되어요(추후 활성화 필요)
		
		if(!session.getAttribute("sid").equals("admin")) {
			System.out.println("관리자가 아닌 사람이 공지사항 작성에 접근함");
			return "main/main.et";
		}
		*/
		
		// model.addAttribute("id", session.getAttribute("sid"));										// sid세션 개발 후 활성화 필요
		model.addAttribute("id", "admin");
		return "board/noticeWriteForm";
	}
	
	// 공지사항 게시글 등록 처리 - 노현호
	@RequestMapping("noticeWritePro.et")
	public String noticeWritePro(NoticeDTO dto, HttpSession session, MultipartHttpServletRequest request, Model model) throws SQLException, IOException {
		System.out.println("공지사항 게시글 등록 처리 요청");
		
		// dto.setId((String)session.getAttribute("sid"));												// sid세션 개발 후 활성화 필요
		dto.setId("admin");																				// sid세션 개발 후 삭제 필요
		dto.setCode(1);
		dto.setReadcount(0);
		
		System.out.println("업로드파일 항목 : "+ dto.getUploadFile());
		MultipartFile mf = request.getFile("uploadFile");
		 	
		String path = request.getRealPath("save");
		System.out.println("path : " + path);

		UUID uuid = UUID.randomUUID();  
		System.out.println("uuid : " + uuid.toString().replace("-",""));
		
		String orgName = mf.getOriginalFilename();
		if(!orgName.equals("")) {
			System.out.println("orgName : " + orgName);
			
			// 확장자를 뺀 파일이름 
			String imgName = orgName.substring(0, orgName.lastIndexOf('.'));
			System.out.println("imgName :" + imgName);
			
			// 확장자(.jpeg)
			String ext = orgName.substring(orgName.lastIndexOf('.'));
			System.out.println("ext : " +  ext);
			
			// 새 이름 지정
			String newName = uuid.toString().replace("-", "") + ext;
			String imgPath = path + "\\" + newName;
			System.out.println("imgPath ==>> " + imgPath);
			
			// 파일 객체 생성 및 저장
			File f = new File(imgPath); 
			mf.transferTo(f);
			
			// NoticeDTO에 저장
			dto.setImg(newName);
		}
		
		System.out.println("저장된 이미지파일 : "+ dto.getImg());
		
		int result = boardService.insertNotice(dto);
		if(result == 1) {
			System.out.println("업로드 성공");
		}else {
			System.out.println("업로드 실패");
		}
		model.addAttribute("result", result);
		return "board/noticeWritePro";
	}
	
	// 오늘의 실천 게시글 등록+처리 - 노현호
	@RequestMapping("uploadTodayChallenge.et")
	public String uploadTodayChallenge(TodayDTO dto) throws SQLException{
		System.out.println("오늘의 실천 게시글 등록 요청");
		
		// 비로그인상태일 시 처리 필요함
		// dto.setId((String)session.getAttribute("sid"));													// sid세션 개발 후 활성화 필요
		dto.setId("javatest");																				// sid세션 개발 후 삭제 필요
		dto.setCode(5);
		
		int result = boardService.upload(dto);
		if(result == 1) {
			System.out.println("업로드 성공");
		}else {
			System.out.println("업로드 실패");
		}
		return "redirect:/board/dailyChallenge.et";
	}
	
	// 게시글 내용 조회
	// 공지사항 게시글 조회 - 노현호
	@RequestMapping("noticeContent.et")
	public String noticeContent(@ModelAttribute("pageNum") String pageNum, @ModelAttribute("boardnum") int boardnum, Model model, HttpServletRequest request) throws SQLException {
		System.out.println("공지사항 게시글 조회");
		
		// 글 고유번호(boardnum)를 가지고 게시글 하나의 정보 불러오기 + 조회수 1 올리기
		NoticeDTO article = boardService.getNoticeArticle(boardnum);
		model.addAttribute("article", article);
		
		String path = request.getRealPath("save");
		path += "\\" + article.getImg();
		System.out.println(path);
		model.addAttribute("path", path);
		return "board/noticeContent";
	}
	
	// 게시글 내용 수정
	// 공지사항 게시글 수정 - 노현호
	@RequestMapping("noticeModifyForm.et")
	public String noticeModifyForm(@ModelAttribute("pageNum") String pageNum, @ModelAttribute("boardnum") int boardnum, Model model) throws SQLException {
		System.out.println("공지사항 게시글 수정 요청");
		
		/*
 		//admin이 아니면 접근 불가능하게 해야되어요(추후 활성화 필요)
		
		if(!session.getAttribute("sid").equals("admin")) {
			System.out.println("관리자가 아닌 사람이 공지사항 수정에 접근함");
			return "main/main.et";
		}
		*/
		
		
		NoticeDTO article = boardService.getNoticeArticle(boardnum);
		System.out.println(article.getBoardnum());
		model.addAttribute("article", article);
		return "board/noticeModifyForm";
	}
	
	// 공지사항 게시글 수정 처리(비밀번호 체크) - 노현호
	@RequestMapping("noticeModifyPro.et")
	public String noticeModifyPro(NoticeDTO dto, Model model, MultipartHttpServletRequest request) throws SQLException, IOException {
		System.out.println("공지사항 게시글 수정 처리 요청");
		System.out.println(dto.getBoardnum());
		
		MultipartFile mf = request.getFile("uploadFile");
		String path = request.getRealPath("save");
		UUID uuid = UUID.randomUUID();  
		String orgName = mf.getOriginalFilename();
		if(!orgName.equals("")) {
			// 확장자를 뺀 파일이름 
			String imgName = orgName.substring(0, orgName.lastIndexOf('.'));
			
			// 확장자(.jpeg)
			String ext = orgName.substring(orgName.lastIndexOf('.'));
			
			// 새 이름 지정
			String newName = uuid.toString().replace("-", "") + ext;
			String imgPath = path + "\\" + newName;
			
			// 파일 객체 생성 및 저장
			File f = new File(imgPath); 
			mf.transferTo(f);
			
			// NoticeDTO에 저장
			dto.setImg(newName);
		}
		if(dto.getImg()==null) {
			// 이미지를 새로 올리지 않은 경우
			int result = boardService.updateNoticeArticle(dto);
			model.addAttribute("result", result);
			return "board/noticeModifyPro";
		}else {
			// 이미지를 새로 올린 경우
			int result = boardService.updateNoticeArticleImg(dto);
			model.addAttribute("result", result);
			return "board/noticeModifyPro";
		}
	}
	
	
}
