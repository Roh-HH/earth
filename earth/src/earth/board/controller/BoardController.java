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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import earth.board.dto.BoardDTO;
import earth.board.dto.BracketsDTO;
import earth.board.dto.FreeCommentDTO;
import earth.board.dto.FreeDTO;
import earth.board.dto.MonthDTO;
import earth.board.dto.NoticeDTO;
import earth.board.dto.TodayDTO;
import earth.board.service.BoardServiceImpl;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	// 자동주입
	@Autowired
	private BoardServiceImpl boardService = null;
	
	// 게시글 목록 조회
	// 공지사항(1) 목록 요청 - 노현호
	@RequestMapping("noticeList.et")
	public String notice(String pageNum, Model model, HttpSession session) throws SQLException{
		System.out.println("공지사항 목록 요청");
		
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
	
	// 자유게시판(2) 목록 요청 - 노현호
	@RequestMapping("freeList.et")
	public String freeList(String pageNum, Model model, HttpSession session) throws SQLException{
		System.out.println("자유게시판 목록 요청");
		
		// free 테이블에서 전체 게시글 가져오기
		int code = 2;
		Map<String, Object> result = boardService.getArticleList(pageNum, code);
		
		// freecomment 테이블에서 전체 ref 갯수 가져오기
		
		
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
	
	// 오늘의 실천(5) 요청 - 노현호
	@RequestMapping("dailyChallenge.et")
	public String dailyChallenge(String pageNum, Model model, HttpSession session) throws SQLException{
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
		
		// 로그인 연결 후 수정해야되는 부분
		String id = "javatest";
		session.setAttribute("sid", id);
		
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
		String hashedPassword = BCrypt.hashpw(dto.getPw(), BCrypt.gensalt());
		dto.setPw(hashedPassword);
		
		/*
		System.out.println("업로드파일 항목 : "+ dto.getUploadFile());
		MultipartFile mf = request.getFile("uploadFile");
		 	
		String path = request.getRealPath("save");
		System.out.println("path : " + path);
		// C:\Users\sdc00\Desktop\project\earth\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\earth\save

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
		*/
		
		int result = boardService.insertNotice(dto);
		if(result == 1) {
			System.out.println("업로드 성공");
		}else {
			System.out.println("업로드 실패");
		}
		model.addAttribute("result", result);
		return "board/noticeWritePro";
	}
	
	// 자유게시판 게시글 등록 페이지 - 노현호
	@RequestMapping("freeWriteForm.et")
	public String freeWriteForm(HttpServletRequest request, Model model, HttpSession session) throws SQLException{
		System.out.println("자유게시판 게시글 등록 페이지 요청");
		
		List<BracketsDTO> Brackets = boardService.getBrackets();
		model.addAttribute("Brackets", Brackets);
		
		// model.addAttribute("id", session.getAttribute("sid"));										// sid세션 개발 후 활성화 필요
		model.addAttribute("id", "javatest");
		return "board/freeWriteForm";
	}
	
	// 자유게시판 게시글 등록 처리 - 노현호
	@RequestMapping("freeWritePro.et")
	public String freeWritePro(FreeDTO dto, HttpSession session, MultipartHttpServletRequest request, Model model) throws SQLException, IOException {
		System.out.println("자유게시판 게시글 등록 처리 요청");
		
		// dto.setId((String)session.getAttribute("sid"));												// sid세션 개발 후 활성화 필요
		model.addAttribute("id", "javatest");
		dto.setId("javatest");																			// sid세션 개발 후 삭제 필요
		dto.setCode(2);
		dto.setReadcount(0);
		String hashedPassword = BCrypt.hashpw(dto.getPw(), BCrypt.gensalt());
		dto.setPw(hashedPassword);
		
		/*
		System.out.println("업로드파일 항목 : "+ dto.getUploadFile());
		MultipartFile mf = request.getFile("uploadFile");
		 	
		String path = request.getRealPath("save");
		System.out.println("path : " + path);
		// C:\Users\sdc00\Desktop\project\earth\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\earth\save

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
		*/
		int result = boardService.insertFree(dto);
		if(result == 1) {
			System.out.println("업로드 성공");
		}else {
			System.out.println("업로드 실패");
		}
		model.addAttribute("result", result);
		return "board/freeWritePro";
	}
	
	// 자유게시판 댓글 등록
	@RequestMapping("uploadFreeComment.et")
	public String uploadFreeComment(String pageNum, int boardnum, FreeCommentDTO dto) throws SQLException{
		System.out.println("자유게시판 댓글 등록 요청");
		
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
		return "redirect:/board/freeContent.et?pageNum=" + pageNum + "&boardnum=" + boardnum + "#comment";
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
	public String noticeContent(@ModelAttribute("pageNum") String pageNum, @ModelAttribute("boardnum") int boardnum, Model model) throws SQLException {
		System.out.println("공지사항 게시글 조회");
		
		// 글 고유번호(boardnum)를 가지고 게시글 하나의 정보 불러오기 + 조회수 1 올리기
		NoticeDTO article = boardService.getNoticeArticle(boardnum);
		model.addAttribute("article", article);
		
		return "board/noticeContent";
	}
	
	// 자유게시판 게시글 조회(+댓글) - 노현호
	@RequestMapping("freeContent.et")
	public String freeContent(@ModelAttribute("pageNum") String pageNum, @ModelAttribute("boardnum") int boardnum, Model model, HttpSession session) throws SQLException {
		System.out.println("자유게시판 게시글 조회");
		
		String id = "javatest";
		session.setAttribute("sid", id);
		int code = 2;
		
		// 글 고유번호(boardnum)를 가지고 게시글 하나의 정보 불러오기 + 조회수 1 올리기
		FreeDTO article = boardService.getFreeArticle(boardnum);
		model.addAttribute("article", article);
		
		// 글 고유번호를 가지고 게시글 하나의 댓글 정보 불러오기
		List<FreeCommentDTO> comment = boardService.getFreeComment(boardnum);
		String ref = boardService.getRef(boardnum, code);
		model.addAttribute("ref", Integer.parseInt(ref));
		model.addAttribute("comment", comment);
		return "board/freeContent";
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
	
	// 공지사항 게시글 수정 처리(비밀번호 체크 기능 포함) - 노현호
	@RequestMapping("noticeModifyPro.et")
	public String noticeModifyPro(NoticeDTO dto, Model model, MultipartHttpServletRequest request) throws SQLException, IOException {
		System.out.println("공지사항 게시글 수정 처리 요청");
		int code = 1;
		String hashedPassword = boardService.getPw(dto.getBoardnum(), code);
		if(!BCrypt.checkpw(dto.getPw(), hashedPassword)) {
			// 비밀번호가 일치하지 않는경우
			model.addAttribute("result", 0);
			return "board/noticeModifyPro";
		}
		/*
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
		*/
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
	
	// 게시글 내용 수정
	// 자유게시판 게시글 수정 - 노현호
	@RequestMapping("freeModifyForm.et")
	public String freeModifyForm(@ModelAttribute("pageNum") String pageNum, @ModelAttribute("boardnum") int boardnum, Model model) throws SQLException {
		System.out.println("공지사항 게시글 수정 요청");
		
		List<BracketsDTO> Brackets = boardService.getBrackets();
		model.addAttribute("Brackets", Brackets);
		
		FreeDTO article = boardService.getFreeArticle(boardnum);
		model.addAttribute("article", article);
		return "board/freeModifyForm";
	}
	
	// 자유게시판 게시글 수정 처리(비밀번호 체크 기능 포함) - 노현호
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
	
	// 이달의 챌린지 code:4 시작 
    // 이달의 챌린지 글쓰기 폼 - 이다희 
	@RequestMapping("challengeWriteForm.et")
	public String writeFormCh(HttpServletRequest request, Model model, HttpSession session) {
		
		//String id = (String)session.getAttribute("sid");
		//String id = "admin";
		//model.addAttribute("sid", id); // 테스트용 id 보내주기 (추후, session으로 처리하면되니 삭제하기) 
		
		return "board/challengeWriteForm";
	}
	// 이달의 챌린지 글쓰기 프로  - 이다희 
	@RequestMapping("challengeWritePro.et")
	public String writeProCh (MonthDTO dto, HttpSession session, MultipartHttpServletRequest request) throws SQLException, IOException {
		MultipartFile mf =request.getFile("uploadFile");
		if(mf.isEmpty()) {
			String img = null;
			dto.setImg(img);
		}else {
			//서버에 이미지 파일 저장 ( webContent > save 폴더 생성 ) save.img,css,js 는 WEN-INF 에 넣지 말것 !
			//파일 정보 꺼내기 	
			String path = request.getRealPath("save");
			System.out.println("path : " + path);
			//---------------- 방법 1 
			//UUID
			UUID uuid = UUID.randomUUID();
			//string 에있는 문자열 기능  - 을 "" 로 바꾸기  
			System.out.println("uuid : " + uuid.toString().replace("-",""));
			
			String orgName = mf.getOriginalFilename();
			System.out.println("orgName : " + orgName); //space.jpeg
			// 확장자를 뺀 파일이름 
			String imgName = orgName.substring(0, orgName.lastIndexOf('.'));
			System.out.println("imgName :" + imgName);
			//확장자 가져오기
			String ext = orgName.substring(orgName.lastIndexOf('.')); //lastIndexof 뒤에서부터 찾
			System.out.println("ext : " +  ext);
			
			String newName = uuid.toString().replace("-", "") + ext;
			String imgPath = path + "/" + newName;
			System.out.println("imgPath==>> " + imgPath);
			
			// 파일 객체 생성 
			File f = new File(imgPath); 
			// 이미지 서버에 저장 ! 
			mf.transferTo(f);
			//디티오에 유유아이디로 저장된 뉴이름 저장 
			dto.setImg(newName);
		}
  
		//String id = (String)session.getAttribute("sid");
		//dto.setId("admin");
		
		boardService.insertChallenge(dto);
		 
		return "redirect:/board/challengeList.et"; 
	}
	
	//지난 챌린지 리스트 가져오기 - 이다희 
	@RequestMapping("challengeList.et") 
	public String listCh(String pageNum, Model model, HttpSession session) throws SQLException {
		// 해당 페이지에 맞는 화면에 뿌려줄 게시글 가져와서 view 전달 
		Map<String, Object> result = null;
		int code = 4;
		result = boardService.getArticleList(pageNum, code);
		 
		
		// view에 전달할 데이터 보내기 
		model.addAttribute("pageSize", result.get("pageSize"));
		model.addAttribute("pageNum", result.get("pageNum"));
		model.addAttribute("currentPage", result.get("currentPage"));
		model.addAttribute("startRow", result.get("startRow"));
		model.addAttribute("endRow", result.get("endRow"));
		model.addAttribute("articleList", result.get("articleList"));
		model.addAttribute("count", result.get("count"));
		model.addAttribute("number", result.get("number"));
		
		return "board/challengeList";
	}
	
	// 이달의 챌린지 게시글 가져오기   - 이다희 
	@RequestMapping("challengeContent.et")
	public String monthlyChallenge(int num, String pageNum, Model model) throws SQLException {
		System.out.println("이달의 챌린지 요청");
		
		MonthDTO article = boardService.getChallengeArticle(num); 
		
		model.addAttribute("article", article);	
		model.addAttribute("pageNum", pageNum);
		
		return "board/challengeContent"; 
	}
	// 이달의 챌린지 게시글 수정 폼   - 이다희 
	@RequestMapping("challengeModifyForm.et")
	public String challengeModifyForm(int challengenum, String pageNum, Model model) throws SQLException {
		
		MonthDTO article = boardService.getChallengeArticle(challengenum);
		
		model.addAttribute("article", article);
		model.addAttribute("pageNum", pageNum);
		
		System.out.println("modiform pageNum : " + pageNum);
		System.out.println("modiform challegenum: " + challengenum);
		
		return "board/challengeModifyForm";
	}

	// 이달의 챌린지 게시글 수정 프로   - 이다희 
	@RequestMapping("challengeModifyPro.et")
	public String noticeModifyPro(MonthDTO dto, Model model, MultipartHttpServletRequest request) throws SQLException, IOException {
		System.out.println("챌린지 게시글 수정 처리 요청");
		System.out.println(dto.getChallengenum());
		
		MultipartFile mf =request.getFile("uploadFile");
		
		if(mf.isEmpty()) {
			String img = null;
			dto.setImg(img);
		}else {
			//서버에 이미지 파일 저장 ( webContent > save 폴더 생성 ) save.img,css,js 는 WEN-INF 에 넣지 말것 !
			//파일 정보 꺼내기 	
			String path = request.getRealPath("save");
			System.out.println("path : " + path);
			//---------------- 방법 1 
			//UUID
			UUID uuid = UUID.randomUUID();
			//string 에있는 문자열 기능  - 을 "" 로 바꾸기  
			System.out.println("uuid : " + uuid.toString().replace("-",""));
			
			String orgName = mf.getOriginalFilename();
			System.out.println("orgName : " + orgName); //space.jpeg
			// 확장자를 뺀 파일이름 
			String imgName = orgName.substring(0, orgName.lastIndexOf('.'));
			System.out.println("imgName :" + imgName);
			//확장자 가져오기
			String ext = orgName.substring(orgName.lastIndexOf('.')); //lastIndexof 뒤에서부터 찾
			System.out.println("ext : " +  ext);
			
			String newName = uuid.toString().replace("-", "") + ext;
			String imgPath = path + "/" + newName;
			System.out.println("imgPath==>> " + imgPath);
			
			// 파일 객체 생성 
			File f = new File(imgPath); 
			// 이미지 서버에 저장 ! 
			mf.transferTo(f);
			//디티오에 유유아이디로 저장된 뉴이름 저장 
			dto.setImg(newName);
		}
		if(dto.getImg()==null) {
			// 이미지를 새로 올리지 않은 경우
			int result = boardService.updateChallengeArticle(dto);
			model.addAttribute("result", result);
			return "board/challengeModifyPro";
		}else {
			// 이미지를 새로 올린 경우
			int result = boardService.updateChallengeArticleImg(dto);
			model.addAttribute("result", result);
			return "board/challengeModifyPro";
		}
	}
	
	
	// 게시글 삭제 팝업 요청 - 노현호
	@RequestMapping("popupForm.et")
	public String popupForm(@ModelAttribute("boardnum") int boardnum, @ModelAttribute("code") int code, @ModelAttribute("uri") String uri, @ModelAttribute("pageNum") String pageNum, Model model) throws SQLException {
		// jsp페이지의 삭제 버튼으로부터 삭제에 필요한 boardnum과 code정보, 그리고 삭제 후 복귀에 필요한 uri 세가지를 파라미터로 받아와 popupForm에 그대로 전달합니다.
		
		model.addAttribute("boardnum", boardnum);
		model.addAttribute("code", code);
		model.addAttribute("uri", uri);
		model.addAttribute("pageNum", pageNum);
		
		return "/board/popupForm";
	}
	
	// 게시글 삭제(게시판 무관) 처리 - 노현호
	@RequestMapping("delete.et")
	public String delete(@ModelAttribute("boardnum") int boardnum, @ModelAttribute("code") int code, @ModelAttribute("uri") String uri, @ModelAttribute("pageNum") String pageNum, @ModelAttribute("boardNum") String boardNum, Model model, String pw, HttpSession session, HttpServletRequest request) throws SQLException {
		// 해당 메서드는 code번호와 일치하는 게시판을 찾아 해당 게시판의 boardnum의 값과 일치하는 데이터를 삭제합니다.
		// 넘어오는 uri의 형태는 다음 예시와 같습니다. uri=/earth/board/dailyChallenge.et
		// 삭제 처리가 끝나면 delete.jsp 페이지에서 해당 uri를 요청하여 페이지를 호출합니다.
		
		if(pageNum == null){
			pageNum = "1";
		}

		uri += "?pageNum=" + pageNum;
		
		// 댓글 삭제 시 사용됨
		if(boardNum != null) {
			uri += "&boardnum=" + boardNum;
		}
		model.addAttribute("uri", uri);

		// 현재 창이 팝업이 아니거나 접속자가 관리자인 경우(비밀번호 없이 삭제되는 경우)
		if(session.getAttribute("sid").equals("admin") || code == 5 || code == 9) {
			
			// CKEditor로 저장된 이미지 지우기
			
			// boardnum과 code로 ctt 가져오기
			String ctt = boardService.getCtt(boardnum, code);
			String data[] = ctt.split("\"");
			ArrayList<String> array = new ArrayList<String>();
			
			// 저장된 이미지 파일이 존재하는지 검사
			for(int i = 0; i < data.length; i++) {
				if(data[i].length() == 47) {
					array.add(data[i].substring(11));
				}
			}
			
			// 저장된 파일이 존재하는 경우
			if(!array.isEmpty()) {
				String path = request.getRealPath("img");
				// C:\Users\sdc00\Desktop\project\earth\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\earth\img
				
				ArrayList<File> files = new ArrayList<File>();
				for(int i = 0; i < array.size(); i++) {
					files.add(new File(path + "\\" + array.get(i)));
				}
				
				// 저장된 이미지 삭제
				for(int i = 0; i < files.size(); i++) {
					System.out.println(files.get(i));
					if(files.get(i).exists()) {
						files.get(i).delete();
					}
				}
			}
			
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
			
			// CKEditor로 저장된 이미지 지우기
			
			// boardnum과 code로 ctt 가져오기
			String ctt = boardService.getCtt(boardnum, code);
			String data[] = ctt.split("\"");
			ArrayList<String> array = new ArrayList<String>();
			
			// 저장된 이미지 파일이 존재하는지 검사
			for(int i = 0; i < data.length; i++) {
				if(data[i].length() == 47) {
					array.add(data[i].substring(11));
				}
			}
			
			// 저장된 파일이 존재하는 경우
			if(!array.isEmpty()) {
				String path = request.getRealPath("img");
				// C:\Users\sdc00\Desktop\project\earth\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\earth\img
				
				ArrayList<File> files = new ArrayList<File>();
				for(int i = 0; i < array.size(); i++) {
					files.add(new File(path + "\\" + array.get(i)));
				}
				
				// 저장된 이미지 삭제
				for(int i = 0; i < files.size(); i++) {
					System.out.println(files.get(i));
					if(files.get(i).exists()) {
						files.get(i).delete();
					}
				}
			}
			
			int result = boardService.deleteArticle(boardnum, code);
			model.addAttribute("result", result);
		}else {
			// 비밀번호 불일치
			model.addAttribute("result", -1);
		}
		
		return "board/delete";
	}

}
