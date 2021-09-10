package earth.board.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import earth.board.dto.BracketsDTO;
import earth.board.dto.EventDTO;
import earth.board.dto.FreeCommentDTO;
import earth.board.dto.FreeDTO;
import earth.board.dto.MonthDTO;
import earth.board.dto.NoticeDTO;
import earth.board.dto.ShopDTO;
import earth.board.dto.TipDTO;
import earth.board.dto.TodayDTO;
import earth.board.service.BoardServiceImpl;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	@Autowired
	private BoardServiceImpl boardService = null;
	
	// 메서드 정렬 순서 : CRUD순 1차 정렬, 게시판 코드순 2차 정렬
	// 로그인파트 연결 등 추후 수정이 필요한 메서드 * 표시
	
	
	
	// Create(insert)
		// 0. 통합기능 - 노현호
	
	
		// 1. 공지사항 - 노현호
			// *공지사항 게시글 등록 페이지 요청
			@RequestMapping("noticeWriteForm.et")
			public String noticeWriteForm(HttpServletRequest request, Model model, HttpSession session) throws SQLException{
				System.out.println("noticeWriteForm.et");
				
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
				
			// *공지사항 게시글 등록 처리 요청
			@RequestMapping("noticeWritePro.et")
			public String noticeWritePro(NoticeDTO dto, HttpSession session, MultipartHttpServletRequest request, Model model) throws SQLException, IOException {
				System.out.println("noticeWritePro.et");
				
				// dto.setId((String)session.getAttribute("sid"));												// sid세션 개발 후 활성화 필요
				dto.setId("admin");																				// sid세션 개발 후 삭제 필요
				dto.setCode(1);
				dto.setReadcount(0);
				String hashedPassword = BCrypt.hashpw(dto.getPw(), BCrypt.gensalt());
				dto.setPw(hashedPassword);
				
				int result = boardService.insertNotice(dto);
				if(result == 1) {
					System.out.println("업로드 성공");
				}else {
					System.out.println("업로드 실패");
				}
				model.addAttribute("result", result);
				
				return "board/noticeWritePro";
			}

			
		// 2. 자유게시판 - 노현호
			// *자유게시판 게시글 등록 페이지
			@RequestMapping("freeWriteForm.et")
			public String freeWriteForm(HttpServletRequest request, Model model, HttpSession session) throws SQLException{
				System.out.println("freeWriteForm.et");
				
				// 말머리 선택용 목록 가져오기
				List<BracketsDTO> Brackets = boardService.getBrackets();
				model.addAttribute("Brackets", Brackets);
				
				// model.addAttribute("id", session.getAttribute("sid"));										// sid세션 개발 후 활성화 필요
				model.addAttribute("id", "javatest");
				
				return "board/freeWriteForm";
			}
				
			// *자유게시판 게시글 등록 처리
			@RequestMapping("freeWritePro.et")
			public String freeWritePro(FreeDTO dto, HttpSession session, MultipartHttpServletRequest request, Model model) throws SQLException, IOException {
				System.out.println("freeWritePro.et");
				
				// dto.setId((String)session.getAttribute("sid"));												// sid세션 개발 후 활성화 필요
				model.addAttribute("id", "javatest");
				dto.setId("javatest");																			// sid세션 개발 후 삭제 필요
				dto.setCode(2);
				dto.setReadcount(0);
				String hashedPassword = BCrypt.hashpw(dto.getPw(), BCrypt.gensalt());
				dto.setPw(hashedPassword);
				
				int result = boardService.insertFree(dto);
				if(result == 1) {
					System.out.println("업로드 성공");
				}else {
					System.out.println("업로드 실패");
				}
				model.addAttribute("result", result);
				
				return "board/freeWritePro";
			}
				
		// 3. 환경일기 - 이다희
			
			
		// 4. 이달의 챌린지 - 이다희
			
			
		// 5. 오늘의 실천 - 노현호
			// *오늘의 실천 게시글 등록+처리
			@RequestMapping("uploadTodayChallenge.et")
			public String uploadTodayChallenge(TodayDTO dto) throws SQLException{
				System.out.println("uploadTodayChallenge.et");
				
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
			
			
		// 6. 상점소개 - 김하영
			// *상점소개 게시글 등록 페이지
			@RequestMapping("writeShopForm.et")
			public String writeShopForm(HttpServletRequest request, Model model, HttpSession session ) {
				System.out.println("writeShopForm.et");			
	
				int num=0, ref=1, re_step=0, re_level=0;
				if(request.getParameter("num") != null) {
					num=Integer.parseInt(request.getParameter("num"));
					ref=Integer.parseInt(request.getParameter("ref"));
					re_step=Integer.parseInt(request.getParameter("re_step"));
					re_level=Integer.parseInt(request.getParameter("re_level"));
				}
				
				model.addAttribute("num", num);
				model.addAttribute("ref", ref);
				model.addAttribute("re_step", re_step);
				model.addAttribute("re_level", re_level);
				model.addAttribute("id", "admin");//테스트용id 보내주기(추후에는 session으로 처리하면 되니까 삭제하기)
				//model.addAttribute("id", session.getAttribute("sid"));
	
				return "board/writeShopForm";
			}
			
			// 상점소개 게시글 등록 처리 
			@RequestMapping("writeShopPro.et")
			public String writeShopPro(ShopDTO dto, HttpSession session, MultipartHttpServletRequest request, Model model) throws SQLException, IOException{
			System.out.println("writeShopPro.et");
				
				// dto.setId((String)session.getAttribute("sid"));												// sid세션 개발 후 활성화 필요
				dto.setId("admin");																				// sid세션 개발 후 삭제 필요
				dto.setCode(6);
				dto.setReadcount(0);
				
				String hashedPassword = BCrypt.hashpw(dto.getPw(), BCrypt.gensalt());
				//암호화 해서 pw 에넣기 
				dto.setPw(hashedPassword);
				 
				System.out.println("업로드파일 항목 : "+ dto.getUploadFile());
				MultipartFile mf = request.getFile("uploadFile");
				 	
				// 경로 
				String path = request.getRealPath("save");
				System.out.println("path : " + path);
				
				// uuid
				UUID uuid = UUID.randomUUID();  
				System.out.println("uuid : " + uuid.toString().replace("-",""));
				
				// 파일이름
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
					String imgPath = path + "/" + newName;
					System.out.println("imgPath ==>> " + imgPath);
					
					// 파일 객체 생성 및 저장
					File f = new File(imgPath); 
					mf.transferTo(f);
					
					// NoticeDTO에 저장
					dto.setImg(newName);
				}
				
				System.out.println("저장된 이미지파일 : "+ dto.getImg());
				
				int result = boardService.insertShop(dto);
				if(result == 1) {
					System.out.println("업로드 성공");
				}else {
					System.out.println("업로드 실패");
				}
				model.addAttribute("result", result);
						
				return "board/writeShopPro";
			}
			
			
		// 7. 행사 - 김하영
			// *행사 게시글 등록 페이지
			@RequestMapping("writeEventForm.et")
			public String writeEventForm(HttpServletRequest request, Model model, HttpSession session ) {
				System.out.println("writeEventForm.et");			

				int num=0, ref=1, re_step=0, re_level=0;
				if(request.getParameter("num") != null) {
					
					num=Integer.parseInt(request.getParameter("num"));
					ref=Integer.parseInt(request.getParameter("ref"));
					re_step=Integer.parseInt(request.getParameter("re_step"));
					re_level=Integer.parseInt(request.getParameter("re_level"));
					
				}
				
				model.addAttribute("num", num);
				model.addAttribute("ref", ref);
				model.addAttribute("re_step", re_step);
				model.addAttribute("re_level", re_level);
				model.addAttribute("id", "admin"); //테스트용id 보내주기(추후에는 session으로 처리하면 되니까 삭제하기)
				//model.addAttribute("id", session.getAttribute("sid"));

				return "board/writeEventForm";
			}
			
			// *행사 게시글 등록 처리 
			@RequestMapping("writeEventPro.et")
			public String writeEventPro(EventDTO dto, HttpSession session, MultipartHttpServletRequest request, Model model) throws SQLException, IOException{
				System.out.println("writeEventPro.et");
				
				// dto.setId((String)session.getAttribute("sid"));												// sid세션 개발 후 활성화 필요
				dto.setId("admin");																				// sid세션 개발 후 삭제 필요
				dto.setCode(7);
				dto.setReadcount(0);
				
				String hashedPassword = BCrypt.hashpw(dto.getPw(), BCrypt.gensalt());
				//암호화 해서 pw 에넣기 
				dto.setPw(hashedPassword);
				
				System.out.println("업로드파일 항목 : "+ dto.getUploadFile());
				MultipartFile mf = request.getFile("uploadFile");
				 	
				// 경로 
				String path = request.getRealPath("save");
				System.out.println("path : " + path);
				
				// uuid
				UUID uuid = UUID.randomUUID();  
				System.out.println("uuid : " + uuid.toString().replace("-",""));
				
				// 파일이름
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
					String imgPath = path + "/" + newName;
					System.out.println("imgPath ==>> " + imgPath);
					
					// 파일 객체 생성 및 저장
					File f = new File(imgPath); 
					mf.transferTo(f);
					
					// NoticeDTO에 저장
					dto.setImg(newName);
				}
				
				System.out.println("저장된 이미지파일 : "+ dto.getImg());
				
				int result = boardService.insertEvent(dto);
				if(result == 1) {
					System.out.println("업로드 성공");
				}else {
					System.out.println("업로드 실패");
				}
				model.addAttribute("result", result);
						
				return "board/writeEventPro";
			}
			
		// 8. 꿀팁 - 김하영
			// *꿀팁 게시글 등록 페이지
			@RequestMapping("writeTipForm.et")
			public String writeTipForm(HttpServletRequest request, Model model, HttpSession session ) {
				System.out.println("writeTipForm.et");			

				int num=0, ref=1, re_step=0, re_level=0;
				if(request.getParameter("num") != null) {
					num=Integer.parseInt(request.getParameter("num"));
					ref=Integer.parseInt(request.getParameter("ref"));
					re_step=Integer.parseInt(request.getParameter("re_step"));
					re_level=Integer.parseInt(request.getParameter("re_level"));
				}
				
				model.addAttribute("num", num);
				model.addAttribute("ref", ref);
				model.addAttribute("re_step", re_step);
				model.addAttribute("re_level", re_level);
				model.addAttribute("id", "admin");//테스트용id 보내주기( 추후에는 session으로 처리하면 되니까 삭제하기
				//model.addAttribute("id", session.getAttribute("sid"));

				return "board/writeTipForm";
			}
			
			// 꿀팁 게시글 등록 처리
			@RequestMapping("writeTipPro.et")
			public String writeTipPro(TipDTO dto, HttpSession session, MultipartHttpServletRequest request, Model model) throws SQLException, IOException{
			System.out.println("writeTipPro.et");
				
				// dto.setId((String)session.getAttribute("sid"));												// sid세션 개발 후 활성화 필요
				dto.setId("admin");																				// sid세션 개발 후 삭제 필요
				dto.setCode(8);
				dto.setReadcount(0);
				
				String hashedPassword = BCrypt.hashpw(dto.getPw(), BCrypt.gensalt());
				//암호화 해서 pw 에넣기 
				dto.setPw(hashedPassword);
				 
				System.out.println("업로드파일 항목 : "+ dto.getUploadFile());
				MultipartFile mf = request.getFile("uploadFile");
				 	
				// 경로 
				String path = request.getRealPath("save");
				System.out.println("path : " + path);
				
				// uuid
				UUID uuid = UUID.randomUUID();  
				System.out.println("uuid : " + uuid.toString().replace("-",""));
				
				// 파일이름
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
					String imgPath = path + "/" + newName;
					System.out.println("imgPath ==>> " + imgPath);
					
					// 파일 객체 생성 및 저장
					File f = new File(imgPath); 
					mf.transferTo(f);
					
					// NoticeDTO에 저장
					dto.setImg(newName);
				}
				
				System.out.println("저장된 이미지파일 : "+ dto.getImg());
				
				int result = boardService.insertTip(dto);
				if(result == 1) {
					System.out.println("업로드 성공");
				}else {
					System.out.println("업로드 실패");
				}
				model.addAttribute("result", result);
						
				return "board/writeTipPro";
			}
			
		// 9. 자유게시판 댓글 - 노현호
			// *자유게시판 댓글 등록 + 처리
			@RequestMapping("uploadFreeComment.et")
			public String uploadFreeComment(String pageNum, int boardnum, FreeCommentDTO dto) throws SQLException{
				System.out.println("uploadFreeComment.et");
				
				// dto.setWriter((String)session.getAttribute("sid"));										// sid세션 개발 후 활성화 필요
				dto.setWriter("javatest");																	// sid세션 개발 후 삭제 필요
				dto.setFreenum(boardnum);
				if(dto.getRef()==null || dto.getRef()==0)
					dto.setRef(1);
				else
					dto.setRef(dto.getRef() + 1);
				if(dto.getRelevel()==null || dto.getRelevel()==0)
					dto.setRelevel(1);

				int result = boardService.uploadFreeComment(dto);
				if(result == 1) {
					System.out.println("업로드 성공");
				}else {
					System.out.println("업로드 실패");
				}
				
				return "redirect:/board/freeContent.et?pageNum=" + pageNum + "&boardnum=" + boardnum + "&commentPageNum=1" + "#comment";
			}
			
			
		// 10. 환경일기 댓글 - 이다희
			
			
		// 11. 이달의 챌린지 댓글 - 이다희


			
	// Read(select)
		// 0. 통합기능 - 노현호
			
			
		// 1. 공지사항 - 노현호
			// *공지사항 목록 요청
			@RequestMapping("noticeList.et")
			public String notice(String pageNum, Model model, HttpSession session) throws SQLException{
				System.out.println("noticeList.et");
				
				// notice 테이블에서 전체 게시글 가져오기
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
				
				// 로그인 연결 후 수정해야되는 부분
				String id = "javatest";
				session.setAttribute("sid", id);
				
				return "board/noticeList";
			}
			
			// 공지사항 게시글 조회
			@RequestMapping("noticeContent.et")
			public String noticeContent(@ModelAttribute("pageNum") String pageNum, @ModelAttribute("boardnum") int boardnum, Model model) throws SQLException {
				System.out.println("공지사항 게시글 조회");
				
				// 글 고유번호(boardnum)를 가지고 게시글 하나의 정보 불러오기 + 조회수 1 올리기
				NoticeDTO article = boardService.getNoticeArticle(boardnum);
				model.addAttribute("article", article);
				
				return "board/noticeContent";
			}
			
				
		// 2. 자유게시판 - 노현호
			// *자유게시판 목록 요청
			@RequestMapping("freeList.et")
			public String freeList(String pageNum, Model model, HttpSession session) throws SQLException{
				System.out.println("freeList.et");
				
				// free 테이블에서 전체 게시글 가져오기
				int code = 2;
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
				
				// 로그인 연결 후 수정해야되는 부분
				String id = "javatest";
				session.setAttribute("sid", id);
				
				return "board/freeList";
			}
			
			// *자유게시판 게시글 조회(+댓글조회)
			@RequestMapping("freeContent.et")
			public String freeContent(@ModelAttribute("pageNum") String pageNum, @ModelAttribute("boardnum") int boardnum, int commentPageNum, Model model, HttpSession session) throws SQLException {
				System.out.println("freeContent.et");
				
				// 로그인 연결 후 수정해야하는 부분
				String id = "javatest";
				session.setAttribute("sid", id);
				
				int code = 9;
				
				if(pageNum == null) {
					pageNum = "1";
				}
				
				// 글 고유번호(boardnum)를 가지고 게시글 하나의 정보 불러오기 + 조회수 1 올리기
				FreeDTO article = boardService.getFreeArticle(boardnum);
				model.addAttribute("article", article);
				
				// 글 고유번호를 가지고 자유게시판 게시글 하나의 댓글 정보 불러오기
				Map<String, Object> result = boardService.getCommentList(commentPageNum, boardnum, code);
				
				// 컨트롤러에 보낼 정보 저장하기
				model.addAttribute("comment", result.get("comment"));
				model.addAttribute("pageSize", result.get("pageSize"));
				model.addAttribute("pageNum", pageNum);
				model.addAttribute("commentPageNum", result.get("commentPageNum"));
				model.addAttribute("currentPage", result.get("currentPage"));
				model.addAttribute("startRow", result.get("startRow"));
				model.addAttribute("endRow", result.get("endRow"));
				model.addAttribute("count", result.get("count"));
				model.addAttribute("number", result.get("number"));

				return "board/freeContent";
			}
			
			
		// 3. 환경일기 - 이다희
			
			
		// 4. 이달의 챌린지 - 이다희
			
			
		// 5. 오늘의 실천 - 노현호
			// *오늘의 실천 목록 요청
			@RequestMapping("dailyChallenge.et")
			public String dailyChallenge(String pageNum, Model model, HttpSession session) throws SQLException{
				System.out.println("dailyChallenge.et");
				
				int code = 5;
				// today 테이블에서 전체 게시글 가져오기
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
				
				// 로그인 연결 후 수정해야되는 부분
				String id = "javatest";
				session.setAttribute("sid", id);
				
				return "board/dailyChallenge";
			}
			
			
		// 6. 상점소개 - 김하영
			// 상점소개 목록 요청
			@RequestMapping("shop.et") 
			public String shop(String pageNum,String sel, String search, Model model, HttpSession session) throws SQLException {
				System.out.println("shop.et");
				
				int code = 6;
				
				Map<String, Object> result =null;
				
				// 전체 게시글 sel search == null (검색 안한 전체 글 보여주기) 
				if(sel == null || search == null) {
					result = boardService.getArticleList(pageNum, code);
				}else { // 검색 게시글 sel search != null
					result = boardService.getArticleSearch(pageNum, sel, search, code);
				}
					
				// view에 전달할 데이터 보내기 
				model.addAttribute("pageSize", result.get("pageSize"));
				model.addAttribute("pageNum", result.get("pageNum"));
				model.addAttribute("currentPage", result.get("currentPage"));
				model.addAttribute("startRow", result.get("startRow"));
				model.addAttribute("endRow", result.get("endRow"));
				model.addAttribute("articleList", result.get("articleList"));
				model.addAttribute("count", result.get("count"));
				model.addAttribute("number", result.get("number"));
					
				return "board/shop";
			}
			
			//상점소개 게시글 불러오기 (어드민만)  
			@RequestMapping("shopContent.et")
			public String shopContent(@ModelAttribute("pageNum") String pageNum, @ModelAttribute("boardnum") int boardnum, Model model, HttpServletRequest request) throws SQLException {
				System.out.println("shopContent.et");
				
				ShopDTO article = boardService.getShopArticle(boardnum);
				System.out.println(article.getBoardnum());

				model.addAttribute("article", article);
				
				return "board/shopContent";
			}
			
			
		// 7. 행사 - 김하영
			// 행사일정 목록 요청 
			@RequestMapping("event.et") 
			public String event(String pageNum,String sel, String search, Model model, HttpSession session) throws SQLException {
				System.out.println("event(List).et");
				
				// notice 테이블에서 전체 게시글 가져오기
				int code = 7;
				Map<String, Object> result = boardService.getArticleList(pageNum, code);
					
				// 전체 게시글 sel search == null (검색 안한 전체 글 보여주기) 
				if(sel == null || search == null) {
					result = boardService.getArticleList(pageNum, code);
				}else { // 검색 게시글 sel search != null
					result = boardService.getArticleSearch(pageNum, sel, search, code);
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
						
				return "board/event";
			}
			
			//행사 일정 콘텐츠 하나 
			@GetMapping("eventContent.et")
			public String eventContent(@ModelAttribute("pageNum") String pageNum, @ModelAttribute("boardnum") int boardnum, Model model, HttpServletRequest request) throws SQLException {
				System.out.println("eventContent.et");
				
				EventDTO article = boardService.getEventArticle(boardnum);
				model.addAttribute("article", article);
				
				String path = request.getRealPath("save");
				path += "\\" + article.getImg();
			
				System.out.println(path);
				model.addAttribute("path", path);
				
				return "board/eventContent";
			}
			
			
		// 8. 꿀팁 - 김하영
			// 꿀팁 목록 요청
			@RequestMapping("tip.et") 
			public String tip(String pageNum, String sel, String search, Model model, HttpSession session) throws SQLException {
				System.out.println("tip(List).et");
				
				// 해당 페이지에 맞는 화면에 뿌려줄 게시글 가져와서 view 전달 
				int code = 8;
				
				Map<String, Object> result = boardService.getArticleList(pageNum, code);

				// 전체 게시글 sel search == null (검색 안한 전체 글 보여주기) 
				if(sel == null || search == null) {
					result = boardService.getArticleList(pageNum, code);
				}else { // 검색 게시글 sel search != null
					result = boardService.getArticleSearch(pageNum, sel, search, code);
				}
				
			// view에 전달할 데이터 보내기 
				model.addAttribute("pageSize", result.get("pageSize"));
				model.addAttribute("pageNum", result.get("pageNum"));
				model.addAttribute("currentPage", result.get("currentPage"));
				model.addAttribute("startRow", result.get("startRow"));
				model.addAttribute("endRow", result.get("endRow"));
				model.addAttribute("articleList", result.get("articleList"));
				model.addAttribute("count", result.get("count"));
				model.addAttribute("number", result.get("number"));
					
				return "board/tip";
			}
			
			// 꿀팁  게시글 불러오기 (어드민만)  
			@RequestMapping("tipContent.et")
			public String tipContent(@ModelAttribute("pageNum") String pageNum, @ModelAttribute("boardnum") int boardnum, Model model, HttpServletRequest request) throws SQLException {
				System.out.println("tipContent.et");
				
				TipDTO article = boardService.getTipArticle(boardnum);
				System.out.println(article.getBoardnum());

				model.addAttribute("article", article);
				
				return "board/tipContent";
			}
			
			
		// 9. 자유게시판 댓글 - 노현호
			// 독립된 코드 없음
			
		// 10. 환경일기 댓글 - 이다희
			
			
		// 11. 이달의 챌린지 댓글 - 이다희
			
			
	

	// Update(modify)
		// 0. 통합기능 - 노현호
		// 1. 공지사항 - 노현호
			// *공지사항 게시글 수정
			@RequestMapping("noticeModifyForm.et")
			public String noticeModifyForm(@ModelAttribute("pageNum") String pageNum, @ModelAttribute("boardnum") int boardnum, Model model) throws SQLException {
				System.out.println("noticeModifyForm.et");
				
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
			
			// 공지사항 게시글 수정 처리(비밀번호 체크 기능 포함)
			@RequestMapping("noticeModifyPro.et")
			public String noticeModifyPro(NoticeDTO dto, Model model, MultipartHttpServletRequest request) throws SQLException, IOException {
				System.out.println("noticeModifyPro.et");
				
				int code = 1;
				String hashedPassword = boardService.getPw(dto.getBoardnum(), code);
				if(!BCrypt.checkpw(dto.getPw(), hashedPassword)) {
					// 비밀번호가 일치하지 않는경우
					model.addAttribute("result", 0);
					return "board/noticeModifyPro";
				}

				int result = boardService.updateNoticeArticle(dto);
				model.addAttribute("result", result);
				
				return "board/noticeModifyPro";

			}
			
			
		// 2. 자유게시판 - 노현호
			// 자유게시판 게시글 수정
			@RequestMapping("freeModifyForm.et")
			public String freeModifyForm(@ModelAttribute("pageNum") String pageNum, @ModelAttribute("boardnum") int boardnum, Model model) throws SQLException {
				System.out.println("공지사항 게시글 수정 요청");
				
				List<BracketsDTO> Brackets = boardService.getBrackets();
				model.addAttribute("Brackets", Brackets);
				
				FreeDTO article = boardService.getFreeArticle(boardnum);
				model.addAttribute("article", article);
				
				return "board/freeModifyForm";
			}
			
			// 자유게시판 게시글 수정 처리(비밀번호 체크 기능 포함)
			@RequestMapping("freeModifyPro.et")
			public String freeModifyPro(FreeDTO dto, Model model) throws SQLException, IOException {
				System.out.println("공지사항 게시글 수정 처리 요청");
				
				int code = 2;
				String hashedPassword = boardService.getPw(dto.getBoardnum(), code);
				if(!BCrypt.checkpw(dto.getPw(), hashedPassword)) {
					// 비밀번호가 일치하지 않는경우
					model.addAttribute("result", 0);
					return "board/freeModifyPro";
				}
				
				int result = boardService.updateFreeArticle(dto);
				model.addAttribute("result", result);
				
				return "board/freeModifyPro";
				
			}
			
			
		// 3. 환경일기 - 이다희
			
			
		// 4. 이달의 챌린지 - 이다희
			
			
		// 5. 오늘의 실천 - 노현호
			
			
		// 6. 상점소개 - 김하영
			// 상점소개 수정 
			@RequestMapping("shopModifyForm.et")
			public String shopModifyForm(@ModelAttribute("boardnum") int boardnum, @ModelAttribute("pageNum") String pageNum, Model model)throws SQLException{
				System.out.println("shopModifyForm.et ");
				
				ShopDTO article=boardService.getShopArticle(boardnum);
				model.addAttribute("article", article);
						
				return "board/shopModifyForm";
			}
			
			// *상점소개 수정 처리
			@RequestMapping("shopModifyPro.et")
			public String shopModifyPro(ShopDTO dto, Model model, MultipartHttpServletRequest request) throws SQLException, IOException {
					System.out.println("shopModifyPro.et");
					int code = 6;			
					
					String hashedPassword = boardService.getPw(dto.getBoardnum(), code);
					
					// 분기처리추가 필요(shopModifyPro.jsp페이지 result = 0 보내기)
					Boolean checkPw = BCrypt.checkpw(dto.getPw(), hashedPassword);
				
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
						String imgPath = path + "/" + newName;
						
						// 파일 객체 생성 및 저장
						File f = new File(imgPath); 
						mf.transferTo(f);
						
						// DTO에 저장
						dto.setImg(newName);
					}
					if(dto.getImg()==null) {
						// 이미지를 새로 올리지 않은 경우
						int result = boardService.updateShopArticle(dto);
						model.addAttribute("result", result);
						return "board/shopModifyPro";
					}else {
						// 이미지를 새로 올린 경우
						int result = boardService.updateShopArticleImg(dto);
						model.addAttribute("result", result);
						
						return "board/shopModifyPro";
					}
			}
			
			
		// 7. 행사 - 김하영
			// 행사일정 수정
			@RequestMapping("eventModifyForm.et")
			public String eventModifyForm(@ModelAttribute("boardnum") int boardnum, @ModelAttribute("pageNum") String pageNum, Model model)throws SQLException{
				System.out.println("eventModifyForm.et");
				
				EventDTO article=boardService.getEventArticle(boardnum);
				model.addAttribute("article", article);
					
				return "board/eventModifyForm";
			}
			
			// *행사일정 수정 처리
			@RequestMapping("eventModifyPro.et")
			public String eventModifyPro(EventDTO dto, Model model, MultipartHttpServletRequest request) throws SQLException, IOException {
					System.out.println("eventModifyPro.et");
					
					int code=7;
					String hashedPassword = boardService.getPw(dto.getBoardnum(), code);
					
					// 분기처리추가 필요(eventModifyPro.jsp페이지 result = 0 보내기)
					Boolean checkPw = BCrypt.checkpw(dto.getPw(), hashedPassword);
				
					
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
						String imgPath = path + "/" + newName;
						
						// 파일 객체 생성 및 저장
						File f = new File(imgPath); 
						mf.transferTo(f);
						
						// NoticeDTO에 저장
						dto.setImg(newName);
					}
					if(dto.getImg()==null) {
						// 이미지를 새로 올리지 않은 경우
						int result = boardService.updateEventArticle(dto);
						model.addAttribute("result", result);
						
						return "board/eventModifyPro";
					}else {
						// 이미지를 새로 올린 경우
						int result = boardService.updateEventArticleImg(dto);
						model.addAttribute("result", result);
						
						return "board/eventModifyPro";
					}
				}
			
		// 8. 꿀팁 - 김하영
			// 꿀팁  수정 
			@RequestMapping("tipModifyForm.et")
			public String tipModifyForm(@ModelAttribute("boardnum") int boardnum, @ModelAttribute("pageNum") String pageNum, Model model)throws SQLException{
				System.out.println("tipModifyForm.et");
				
				TipDTO article=boardService.getTipArticle(boardnum);
				model.addAttribute("article", article);
				model.addAttribute("pageNum", pageNum);
									
				return "board/tipModifyForm";
			}
			
			// *꿀팁 수정 처리
			@RequestMapping("tipModifyPro.et")
			public String tipModifyPro(TipDTO dto, Model model, MultipartHttpServletRequest request) throws SQLException, IOException {
					System.out.println("tipModifyPro.et");

					int code = 8;		
					
					String hashedPassword = boardService.getPw(dto.getBoardnum(), code);
					
					// 분기처리추가 필요(tipModifyPro.jsp페이지 result = 0 보내기)
					Boolean checkPw = BCrypt.checkpw(dto.getPw(), hashedPassword);
					
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
						String imgPath = path + "/" + newName;
						
						// 파일 객체 생성 및 저장
						File f = new File(imgPath); 
						mf.transferTo(f);
						
						// DTO에 저장
						dto.setImg(newName);
					}
					if(dto.getImg()==null) {
						// 이미지를 새로 올리지 않은 경우
						int result = boardService.updateTipArticle(dto);
						model.addAttribute("result", result);
						return "board/tipModifyPro";
					}else {
						// 이미지를 새로 올린 경우
						int result = boardService.updateTipArticleImg(dto);
						model.addAttribute("result", result);
						
						return "board/tipModifyPro";
					}
				}
			
		// 9. 자유게시판 댓글 - 노현호
			// 독립된 코드 없음
			
		// 10. 환경일기 댓글 - 이다희
			
			
		// 11. 이달의 챌린지 댓글 - 이다희
	
			
			

	// Delete(delete)
		// 0. 통합기능 - 노현호
			// 게시글 삭제 팝업 요청
			@RequestMapping("popupForm.et")
			public String popupForm(@ModelAttribute("boardnum") int boardnum, @ModelAttribute("code") int code, @ModelAttribute("uri") String uri, @ModelAttribute("pageNum") String pageNum, Model model) throws SQLException {
				// jsp페이지의 삭제 버튼으로부터 삭제에 필요한 boardnum과 code정보, 그리고 삭제 후 복귀에 필요한 uri 세가지를 파라미터로 받아와 popupForm에 그대로 전달합니다.
				
				model.addAttribute("boardnum", boardnum);
				model.addAttribute("code", code);
				model.addAttribute("uri", uri);
				model.addAttribute("pageNum", pageNum);
				
				return "/board/popupForm";
			}
			
			// CKEditor로 본문에 저장된 이미지(들)및 썸네일 지우기
			public void deleteFiles(int boardnum, int code, HttpServletRequest request) throws SQLException {
				System.out.println("deleteFiles 실행");
				
				String ctt = null;
				String img = null;
				ArrayList<String> array = null;
				
				// CKEditor로 본문을 작성하는 테이블의 경우 boardnum과 code로 ctt 가져오기
				if(code == 1 || code == 2 || code == 3 || code == 7 || code == 8) {
					ctt = boardService.getCtt(boardnum, code);			
				}
				
				// 이미지 컬럼이 있는 테이블의 경우 boardnum과 code로 img 가져오기
				if(code == 3 || code == 6 || code == 7 || code == 8) {
					img = boardService.getImg(boardnum, code);			
				}
				
				
				// 본문(ctt)에 저장된 이미지 파일이 존재하는지 검사
				if(ctt != null) {
					String data[] = ctt.split("\"");
					array = new ArrayList<String>();
					for(int i = 0; i < data.length; i++) {
						if(data[i].length() == 47) {
							array.add(data[i].substring(11));
						}
					}			
				}
				
				// 본문(ctt)에 저장된 파일이 존재하는 경우 삭제처리
				if(array != null) {
					if(!array.isEmpty()) {
						String path = request.getRealPath("img");
						// C:\Users\sdc00\Desktop\project\earth\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\earth\img
						
						ArrayList<File> files = new ArrayList<File>();
						for(int i = 0; i < array.size(); i++) {
							files.add(new File(path + "\\" + array.get(i)));
						}
						
						// 저장된 이미지 삭제
						System.out.println("삭제된 본문 이미지 파일 목록");
						for(int i = 0; i < files.size(); i++) {
							System.out.println(files.get(i));
							if(files.get(i).exists()) {
								files.get(i).delete();
							}
						}
					}
				}
				
				// 썸네일 이미지(DB의 img컬럼)가 존재하는 경우 삭제처리
				if(img != null || img != "") {
					System.out.println("삭제된 썸네일 이미지 파일");
					String path = request.getRealPath("save");
					// C:\Users\sdc00\Desktop\project\earth\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\earth\save
					File file = new File(path + "\\" + img);
					System.out.println(file);
					file.delete();
				}
				System.out.println("deleteFiles 종료");
			}
			
			// 게시글 삭제(게시판 무관) 처리 - 노현호
			@RequestMapping("delete.et")
			public String delete(@ModelAttribute("boardnum") int boardnum, @ModelAttribute("code") int code, @ModelAttribute("uri") String uri, @ModelAttribute("pageNum") String pageNum, @ModelAttribute("boardNum") String boardNum, Model model, String pw, HttpSession session, HttpServletRequest request) throws SQLException {
				// 파라미터 설명
				// boardnum : 이미지파일과 게시글을 삭제할 글을 불러올 때 사용하는 게시글 고유번호(댓글의 경우 해당 파라미터에 commentnum 등을 사용)
				// code : 이미지파일과 게시글을 삭제할 글의 DB테이블을 구분하는 DB구분번호
				// uri : 삭제 후 실행시킬(이동할) 페이지 주소 (예시. /earth/board/dailyChallenge.et)
				// pageNum : uri주소 사이트의 특정한 n페이지로 이동시키는 페이지번호
				// boardNum : 댓글 삭제 시 위의 boardnum을 대체하여 원본 게시글로 이동시키는 게시글 고유번호(댓글은 원본 글과 별개로 commentnum을 사용하므로)
				// model : uri정보와 결과정보를 전송
				// pw : 삭제 절차 실행을 위하여 확인하는 게시글 비밀번호
				// session : 접속자 정보를 확인하여 관리자 여부를 체크하기 위한 세션정보
				// request : 물리 서버경로 확인을 위한 HTTP리퀘스트 정보
				
				// 해당 메서드는 code번호와 일치하는 게시판을 찾아 해당 게시판의 boardnum(댓글의 경우 commentnum 등)의 값과 일치하는 데이터를 삭제합니다.
				// 이미지 및 게시글 삭제 처리가 끝나면 delete.jsp 페이지에서 해당 uri를 요청하여 페이지를 호출합니다.
				
				if(pageNum == null){
					pageNum = "1";
				}
				uri += "?pageNum=" + pageNum;
				
				// 댓글 삭제 시 원본 게시글로 바로 이동하기 위하여 사용됨
				if(boardNum != null) {
					uri += "&boardnum=" + boardNum + "&commentPageNum=1";
				}
				model.addAttribute("uri", uri);
		
				// 현재 창이 팝업이 아니거나 접속자가 관리자인 경우(비밀번호 없이 삭제되는 경우)
				if(session.getAttribute("sid").equals("admin") || code == 5 || code == 9 || code == 10 || code == 11) {
					
					// 이미지파일 삭제
					deleteFiles(boardnum, code, request);
					
					// 게시글 삭제
					int result = boardService.deleteArticle(boardnum, code);
					model.addAttribute("result", result);
					model.addAttribute("pop", 0);
					
					return "board/delete";
				}else {
					model.addAttribute("pop", 1);
				}
				
				// 비밀번호 일치여부 확인(비밀번호가 있어야 삭제되는 경우)
				String hashedPassword = boardService.getPw(boardnum, code);
				if(BCrypt.checkpw(pw, hashedPassword)) {
					// 비밀번호 일치
					
					// 이미지파일 삭제
					deleteFiles(boardnum, code, request);
					
					// 게시글 삭제
					int result = boardService.deleteArticle(boardnum, code);
					model.addAttribute("result", result);
				}else {
					// 비밀번호 불일치
					model.addAttribute("result", -1);
				}
				
				return "board/delete";
			}
}
