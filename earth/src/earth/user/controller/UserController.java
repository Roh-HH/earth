package earth.user.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import earth.badge.dto.BadgeDTO;
import earth.board.dto.TodayDTO;
import earth.user.dto.QuestionDTO;
import earth.user.dto.UserDTO;
import earth.user.dto.WarningDTO;
import earth.user.service.UserServiceImpl;

@Controller
@RequestMapping("/user/*")
public class UserController {	

	// 작성자 : 이영인		
	@Autowired
	private UserServiceImpl userService = null;
	
	
	// 회원가입
	
		// 회원가입 폼 요청, 처리 요청 메서드
		@RequestMapping("signupForm.et")
		public String signupForm() {
			return "user/signupForm";
		}
		
		@RequestMapping("signupPro.et")
		@ResponseBody
		public String signupPro(UserDTO dto) throws SQLException {
			System.out.println("회원가입 ID : " + dto.getId());
			String hashedPassword = BCrypt.hashpw(dto.getPw(), BCrypt.gensalt());	// 회원가입처리 할 때, 회원이 입력한 패스워드 암호화 하여 저장하기
			dto.setPw(hashedPassword);												// 암호화 된 패스워드 PW 컬럼에 세팅하기
	
			userService.addUser(dto); 												// 회원가입 처리 : 사용자 회원가입 입력정보 DB에 저장하기
			
			String resultMsg = "<script>alert('welcome EARTH!!');location.href='/earth/user/loginForm.et'</script>";
			return resultMsg;														// 회원가입 처리 완료 후 별도의 signupPro.jsp 페이지 거치지 않고 팝업창 띄운 후 로그인 페이지로 이동
		}
		
		// 회원가입 : ajox 아이디 중복 확인
		@RequestMapping("ajaxIdAvail.et")
		public ResponseEntity<String> ajaxIdAvail(UserDTO dto) throws SQLException { //@ResponseBody 사용 X , ResponseEntity<String> 으로 리턴받기 (한글깨짐 방지)
			System.out.println("controller id : " + dto.getId());
			int result = userService.idCheck(dto);									// ID 확인. 중복 = 1 , 중복X = 0
			
			String data = "";
			if(result == 1) {
				data = "이미 사용중인 아이디입니다.";
			}else if(result != 1 && !dto.getId().equals("")){
				data = "사용가능한 아이디입니다.";
			}  
			HttpHeaders respHeaders = new HttpHeaders();								// 헤더 객체 만들어서,
			respHeaders.add("Content-Type", "text/html;charset=utf-8"); 				// 헤더 정보 추가 (charset=utf-8로 한글깨짐 방지하여 결과물 응답해주기)
			
			return new ResponseEntity<String>(data, respHeaders, HttpStatus.OK);
		}	

		// 회원가입 : ajox 닉네임 중복 확인 (+.마이페이지 : 내 프로필 보기 : 닉네임 수정 활용)
		@RequestMapping("ajaxNicknameAvail.et")
		public ResponseEntity<String> ajaxNicknameAvail(UserDTO dto) throws SQLException {
			System.out.println("controller Nickname : " + dto.getNickname());	   
			int result = userService.nickCheck(dto);									// 닉네임 확인. 중복 = 1 , 중복X = 0
			
			String data = "";
			if(result == 1) {
				data = "이미 사용중인 닉네임입니다.";
			}else if(result != 1 && !dto.getNickname().equals("")){
				data = "사용가능한 닉네임입니다."; 
			}  
			HttpHeaders respHeaders = new HttpHeaders();
			respHeaders.add("Content-Type", "text/html;charset=utf-8");	
			
			return new ResponseEntity<String>(data, respHeaders, HttpStatus.OK);   
		}
		   
		// 전화번호 인증 - 노현호
		@RequestMapping("verifyPhone.et")
		public ResponseEntity<String> verifyPhone(String phone, String code) {
			HttpHeaders respHeaders = new HttpHeaders();
			respHeaders.add("Content-type", "test/html;charset=utf-8");
			return new ResponseEntity<String>(userService.verifyPhone(phone, code), respHeaders, HttpStatus.OK); 
		}
	   
	// 로그인, 로그아웃  

		// 로그인 폼 페이지, 로그인 처리 요청 메서드
		@RequestMapping("loginForm.et")
		public String loginForm() {
			return "user/loginForm";
		}
		
		@RequestMapping("loginPro.et")
		public String loginPro(UserDTO dto, Model model, HttpSession session) throws SQLException {
			int idCheck = userService.idCheck(dto);					// ID 확인. 존재 = 1 , 존재X = 0
			if(idCheck != 1) {										// ID가 존재하지 않는 경우 : 회원가입 내역이 없는 경우
				model.addAttribute("loginResult", -1);
			}
			
			if(idCheck == 1) {										// ID가 존재하는 경우 : 회원가입 내역이 존재하는 경우
				int result = userService.idPwCheck(dto);			// 로그인 처리 위해 ID와 PW 일치여부 확인 (세션 아이디 추가)
				model.addAttribute("loginResult", result);			
			}
			System.out.println("로그인 처리 후 sessionId : " + session.getAttribute("sid"));
			
			return "user/userTotalPro";
		}
		
		// 로그아웃 처리
		@RequestMapping("logout.et")
		public String logout(HttpSession session) {
			userService.removeSession("sid");										// 로그아웃 처리 : 세션 삭제
			System.out.println("세션에 있는 ID : " + session.getAttribute("sid")); 	// null 확인용
			
			return "redirect:/main/main.et";	// main 페이지 컨트롤러로 다시 요청하는 것 <c:redirect url="/member/main.do" /> 와 같은 처리. jsp를 거치지 않고 main 페이지 요청
		}


	//******** 사용자 마이페이지 ********
		
	   // 마이페이지 : 사용자 마이페이지의 메인 View X, 경고내역 확인 할 수 있는 페이지
		@RequestMapping("mypage.et")
	    public String etmypage(Model model) throws SQLException {
			UserDTO user = userService.getUser();
			model.addAttribute("user", user);
			
			WarningDTO warn = userService.getUserWarn();							// 회원 한명의 경고내역 가져오기
			model.addAttribute("warn", warn);
			
			// 내 적용된 뱃지 가져오기
			BadgeDTO mybadge = userService.getMyBadge(user.getId());
			model.addAttribute("mybadge", mybadge.getBadgeimg());
			
			return "user/mypage"; 
	   }
		
	// 마이페이지 : 내 프로필 보기
		
		// 마이페이지 - 내프로필보기 : 회원정보 수정 폼 페이지 요청 : 회원정보 하나 가져오는 메서드 활용해 DB에서 회원정보 가져오기
		@RequestMapping("userModifyForm.et")
		public String etuserModifyForm(Model model, HttpSession session) throws SQLException {
			UserDTO user = userService.getUser(); 									// (*id 값 컨트롤러에서 안넘겨주는 방법 : id 매개변수 안넘김(로직 처리 할 때 세션 아이디 추가))
			model.addAttribute("user", user);
			
			// 내 적용된 뱃지 가져오기
			BadgeDTO mybadge = userService.getMyBadge(user.getId());
			model.addAttribute("mybadge", mybadge.getBadgeimg());
			
			return "user/userModifyForm";
		}
		
		// 마이페이지 - 내프로필보기 : 회원정보 수정처리 요청 : 암호화 된 비밀번호 확인 후 result 값 넘겨서 회원정보 수정 처리
		@RequestMapping("userModifyPro.et")
		public String userModifyPro(Model model, UserDTO dto) throws SQLException {
			int result = userService.checkPw(dto);			
			if(result == 1) {
				userService.modifyUser(dto);
			}			
			model.addAttribute("result", result);
			
			return "user/userModifyPro";
		}
		
		// 마이페이지 - 내프로필보기 : 비밀번호 수정 폼/ 처리 요청
		@RequestMapping("userPwModifyForm.et")
		public String etuserPwModifyForm(Model model) throws SQLException {
			UserDTO user = userService.getUser();
			model.addAttribute("user", user);
			
			// 내 적용된 뱃지 가져오기
			BadgeDTO mybadge = userService.getMyBadge(user.getId());
			model.addAttribute("mybadge", mybadge.getBadgeimg());
			
			return "user/userPwModifyForm";
		}
		@RequestMapping("userPwModifyPro.et")
		public String userPwModifyPro(UserDTO dto, Model model, @RequestParam("pw") String pw, @RequestParam("newpw") String newpw) throws SQLException{
			int result = userService.checkPw(dto);
			if(result == 1) {
				String newHashedPw = BCrypt.hashpw(newpw, BCrypt.gensalt());			// 비밀번호 변경 : 회원이 새로 입력한 패스워드 암호화 하여 변수에 저장하기
				dto.setPw(newHashedPw);													// 암호화 된 패스워드 PW 컬럼에 세팅하기
				userService.modifyPw(dto);												// 로그아웃 처리. 세션삭제
			}
			model.addAttribute("pwResult", result);
			
			// 입력 비밀번호와 새비밀번호 같으면 result = -1 리턴하기
			if(pw.equals(newpw)) {
				model.addAttribute("pwResult", -1);
			}
			
			return "user/userTotalPro";
		}	
			
		// 마이페이지 - 내프로필보기 : 회원탈퇴 폼 페이지 요청 : 별도 비지니스로직 처리 할게 없으므로 바로 view 페이지 띄어주기
		@RequestMapping("userDeleteForm.et")
		public String etuserDeleteForm(Model model) throws SQLException{
			UserDTO user = userService.getUser();
			model.addAttribute("user", user);
			
			// 내 적용된 뱃지 가져오기
			BadgeDTO mybadge = userService.getMyBadge(user.getId());
			model.addAttribute("mybadge", mybadge.getBadgeimg());
			
			return "user/userDeleteForm";
		}
		// 마이페이지 - 내프로필보기 : 회원탈퇴처리하기 : dto에 패스워드 담아서 파라미터값 넘어오고, ID/PW 체크
		@RequestMapping("userDeletePro.et")
		public String userDeletePro(UserDTO dto, Model model) throws SQLException {
			int result = userService.checkPw(dto);
			// 입력 비밀번호와 암호화되서 저장 된 비밀번호가 같은 경우
			if(result == 1) {
				userService.deleteUser(dto);
				userService.removeSession("sid");										// 기존 로그인 상태의 세션 삭제 : 로그아웃 세션삭제 메서드 활용
			}
			// 입력 비밀번호와(현재 사용중인 비밀번호) 암호화되서 저장 된 비밀번호가 같지않은 경우 result = 0 리턴
			model.addAttribute("deleteResult", result);
			
			return "user/userTotalPro";
		}

	// 마이페이지 : 나의 실천
		
		// 마이페이지 - 나의 실천 : 오늘의 실천 게시판
		@RequestMapping("myToday.et")
		public String etmyToday(String pageNum, Model model, HttpSession session) throws SQLException {
			System.out.println("******myToday 실행******");
			
			// 해당 ID가 작성한 오늘의 실천 게시판 내 게시글만 가져오기 (today 테이블. code == 5)
			String id = (String)session.getAttribute("sid");

			int code = 5;
			Map<String, Object> result = userService.getMyArticleList(pageNum, code, id);
		
			// 세션 아이디로 해당 사용자 정보 가져오기
			UserDTO user = userService.getUser();
			
			// 내 적용된 뱃지 가져오기
			BadgeDTO mybadge = userService.getMyBadge(user.getId());
			
			// view 에 전달할 데이터 보내기
			model.addAttribute("user", user);
			model.addAttribute("pageSize", result.get("pageSize"));
			model.addAttribute("pageNum", result.get("pageNum"));
			model.addAttribute("currentPage", result.get("currentPage"));
			model.addAttribute("startRow", result.get("startRow"));
			model.addAttribute("endRow", result.get("endRow"));
			model.addAttribute("articleList", result.get("articleList"));
			model.addAttribute("count", result.get("count"));
			model.addAttribute("number", result.get("number"));		
			model.addAttribute("mybadge", mybadge.getBadgeimg());
			
			return "user/myToday";
		}
		
		// 마이페이지 - 나의 실천 : 글 삭제 폼 요청 (오늘의 실천 게시판은 오늘 날짜 기준으로만 올라와서 BoardController 통합 삭제 메서드 사용X)
		@RequestMapping("myTodayDeleteForm.et")
		public String etmyTodayDeleteForm(@ModelAttribute("boardnum") int boardnum, @ModelAttribute("id") String id, Model model) {
	
			// view 에 전달할 데이터 보내기		
			model.addAttribute("boardnum", boardnum);
			model.addAttribute("id", id);
			
			return "user/myTodayDeleteForm";
		}
		// 마이페이지 - 나의 실천 : 글 삭제 처리 (별도의 jsp 페이지 존재X)
		@RequestMapping("myTodayDeletePro.et")
		public String myTodayDeletePro(@ModelAttribute("boardnum") int boardnum, @ModelAttribute("id") String id, Model model, TodayDTO dto) throws SQLException{
			userService.deleteMyToday(dto);	
			model.addAttribute("delete", 1);		// 삭제 완료 팝업창을 띄우기 위해 myToday view에 데이터 전달
			return "user/myToday";
		}
		
	// 마이페이지 : 나의 환경일기
		
		// 마이페이지 - 나의 환경일기 : 환경일기 해당 ID가 작성한 게시글 목록 전부 가져오기
		@RequestMapping("myDiary.et")
		public String etmyDiary(String pageNum, HttpSession session, Model model, String sel, String search) throws SQLException {
			System.out.println("******myDiary 실행******");
			
			// 해당 id가 작성한 환경일기 게시판 내 게시글만 가져오기(diary 테이블. code == 3)
			String id = (String)session.getAttribute("sid");
			
			int code = 3;		
			Map<String, Object> result = userService.getMyArticleList(pageNum, code, id);
			
			// 세션 아이디로 해당 사용자 정보 가져오기
			UserDTO user = userService.getUser();
			
			// 내 적용된 뱃지 가져오기
			BadgeDTO mybadge = userService.getMyBadge(user.getId());
						
			// 검색 기능 사용X : 전체 게시글 가져오기
			if(sel == null || search == null) {
				result = userService.getMyArticleList(pageNum, code, id);
			}else { // 검색 된 게시글만 가져오기
				result = userService.getMyDiarySearch(pageNum, sel, search); 
			}				
			
			// view 에 전달할 데이터 보내기
			model.addAttribute("user", user);
			model.addAttribute("pageSize", result.get("pageSize"));
			model.addAttribute("pageNum", result.get("pageNum"));
			model.addAttribute("currentPage", result.get("currentPage"));
			model.addAttribute("startRow", result.get("startRow"));
			model.addAttribute("endRow", result.get("endRow"));
			model.addAttribute("diaryArticleList", result.get("diaryArticleList"));
			model.addAttribute("count", result.get("count"));
			model.addAttribute("number", result.get("number"));		
			model.addAttribute("sel", sel);
			model.addAttribute("search", search);	
			model.addAttribute("mybadge", mybadge.getBadgeimg());
			
			return "user/myDiary";
		}
				
	// 마이페이지 : 내 게시글 보기	
	
		// 마이페이지 - 내 게시글 보기 : 자유게시판 해당 id가 작성한 게시글 목록 전부 가져오기
		@RequestMapping("myBoard.et")
		public String etmyBoard(String pageNum, HttpSession session, Model model) throws SQLException{
			System.out.println("******myBoard 실행******");
		
			// 해당 ID가 작성한 자유게시판 내 게시글만 가져오기 (free 테이블. code == 2)
			String id = (String)session.getAttribute("sid");
			
			int code = 2;
			Map<String, Object> result = userService.getMyArticleList(pageNum, code, id);

			// 세션 아이디로 해당 사용자 정보 가져오기
			UserDTO user = userService.getUser();	
			
			// 내 적용된 뱃지 가져오기
			BadgeDTO mybadge = userService.getMyBadge(user.getId());
			
			// view 에 전달할 데이터 보내기
			model.addAttribute("user", user);
			model.addAttribute("pageSize", result.get("pageSize"));
			model.addAttribute("pageNum", result.get("pageNum"));
			model.addAttribute("currentPage", result.get("currentPage"));
			model.addAttribute("startRow", result.get("startRow"));
			model.addAttribute("endRow", result.get("endRow"));
			model.addAttribute("freeArticleList", result.get("freeArticleList"));
			model.addAttribute("count", result.get("count"));
			model.addAttribute("number", result.get("number"));		
			model.addAttribute("mybadge", mybadge.getBadgeimg());
	
			return "user/myBoard";
		}


	// 마이페이지 : 내가 작성한 댓글
		
		// 마이페이지 - 내가 작성한 댓글 : 자유게시판/환경일기/이달의챌린지(게시글을 댓글 형태로 등록하는 게시판) 세 게시판의 내가 작성한 댓글 가져오기
		@RequestMapping("myComment.et")
		public String etmyComment(HttpSession session, Model model, String pageNum, String sel, String search) throws SQLException{
			System.out.println("******myComment 실행******");
			
			String id = (String)session.getAttribute("sid");
			
			Map<String, Object> result = userService.getMyCommentList(pageNum, id);
	
			// 검색 기능 사용X : 전체 게시글 가져오기
			if(sel == null || search == null) {
				result = userService.getMyCommentList(pageNum, id);
			}else { // 검색 된 게시글만 가져오기
				result = userService.getMyCommentSearch(pageNum, sel, search); 
			}		
			
			// 세션 아이디로 해당 사용자 정보 가져오기
			UserDTO user = userService.getUser();
			
			// 내 적용된 뱃지 가져오기
			BadgeDTO mybadge = userService.getMyBadge(user.getId());
			
			// view 에 전달할 데이터 보내기
			model.addAttribute("user", user);
			model.addAttribute("pageSize", result.get("pageSize"));
			model.addAttribute("pageNum", result.get("pageNum"));
			model.addAttribute("currentPage", result.get("currentPage"));
			model.addAttribute("startRow", result.get("startRow"));
			model.addAttribute("endRow", result.get("endRow"));
			model.addAttribute("commentList", result.get("commentList"));
			model.addAttribute("count", result.get("count"));
			model.addAttribute("number", result.get("number"));
			model.addAttribute("sel", sel);
			model.addAttribute("search", search);	
			model.addAttribute("mybadge", mybadge.getBadgeimg());
					
			return "user/myComment";
		}
		
	// 마이페이지 : 1:1 문의	
	
		// 마이페이지 - 1:1 문의 : 1:1문의 내에서 내가 작성한 문의글만 가져오기
		@RequestMapping("myOnetoOne.et")
		public String etmyOnetoOne(Model model, String pageNum, String sel, String search) throws SQLException {
			Map<String, Object> result = userService.getMyQnAList(pageNum);
			
			// 검색 기능 사용X : 전체 게시글 가져오기
			if(sel == null || search == null) {
				result = userService.getMyQnAList(pageNum);
			}else { // 검색 된 1:1 문의글만 가져오기
				result = userService.getMyQnASearch(pageNum, sel, search); 
			}		
			
			// 세션 아이디로 해당 사용자 정보 가져오기
			UserDTO user = userService.getUser();
			
			// 내 적용된 뱃지 가져오기
			BadgeDTO mybadge = userService.getMyBadge(user.getId());
			
			// view 에 전달할 데이터 보내기
			model.addAttribute("user", user);
			model.addAttribute("pageSize", result.get("pageSize"));
			model.addAttribute("pageNum", result.get("pageNum"));
			model.addAttribute("currentPage", result.get("currentPage"));
			model.addAttribute("startRow", result.get("startRow"));
			model.addAttribute("endRow", result.get("endRow"));
			model.addAttribute("qtArticleList", result.get("qtArticleList"));
			model.addAttribute("count", result.get("count"));
			model.addAttribute("number", result.get("number"));	
			model.addAttribute("sel", sel);
			model.addAttribute("search", search);		
			model.addAttribute("mybadge", mybadge.getBadgeimg());
			
			return "user/myOnetoOne";
		}
		
		// 마이페이지 - 1:1 문의글 작성 폼
		@RequestMapping("myQnAWriteForm.et")
		public String etmyQnAWriteForm(Model model) throws SQLException {
			UserDTO user = userService.getUser();
			model.addAttribute("user", user);
			
			// 내 적용된 뱃지 가져오기
			BadgeDTO mybadge = userService.getMyBadge(user.getId());
			model.addAttribute("mybadge", mybadge.getBadgeimg());
			
			return "user/myQnAWriteForm";
		}		
		// 마이페이지 - 1:1 문의글 등록 처리
		@RequestMapping("myQnAWritePro.et")
		public String etmyQnAWritePro(QuestionDTO dto) throws SQLException {
			userService.addQnA(dto);
			return "redirect:/user/myOnetoOne.et";	// 1:1 문의글 등록 후 1:1문의 페이지로 이동
		}
		
		// 마이페이지 - 1:1 문의글 수정 폼
		@RequestMapping("myQnAModifyForm.et")
		public String etmyQnAModifyForm(Model model, HttpSession session, @RequestParam("questionnum") int questionnum) throws SQLException {
			UserDTO user = userService.getUser();
			model.addAttribute("user", user);	
			
			QuestionDTO question = userService.getMyQnAOne(questionnum);	// 1:1 문의글 고유번호로 문의글 한개의 정보 가져오기
			model.addAttribute("question", question);
			
			// 내 적용된 뱃지 가져오기
			BadgeDTO mybadge = userService.getMyBadge(user.getId());
			model.addAttribute("mybadge", mybadge.getBadgeimg());
			
			return "user/myQnAModifyForm";
		}
		// 마이페이지 - 1:1 문의글 수정 처리
		@RequestMapping("myQnAModifyPro.et")
		public String myQnAModifyPro(QuestionDTO dto) throws SQLException {
			userService.modifyQnA(dto);
			return "redirect:/user/myOnetoOne.et";
		}
		
		// 마이페이지 - 1:1 문의글 삭제 폼
		@RequestMapping("myQnADeleteForm.et")
		public String etmyQnADeleteForm(@ModelAttribute("questionnum") int questionnum, @ModelAttribute("id") String id, Model model) throws SQLException {		
			model.addAttribute("questionnum", questionnum);
			model.addAttribute("id", id);
			
			return "user/myQnADeleteForm";
		}
		// 마이페이지 - 1:1문의글 삭제 처리 (별도의 jsp 페이지 존재하지X)
		@RequestMapping("myQnADeletePro.et")
		public String myQnADeletePro(@ModelAttribute("questionnum") int questionnum, @ModelAttribute("id") String id, Model model, QuestionDTO dto) throws SQLException {
			userService.deleteMyQnA(dto);	
			model.addAttribute("delete", 1);		// 삭제 완료 팝업창을 띄우기 위해 myOnetoOne view에 데이터 전달
			
			return "user/myOnetoOne";
		}
		// 마이페이지 - 1:1문의글 답변확인 폼
		@RequestMapping("myQnAReplyCheck.et")
		public String myQnAReplyCheck(Model model, HttpSession session, @RequestParam("questionnum") int questionnum) throws SQLException {
			UserDTO user = userService.getUser();
			model.addAttribute("user", user);	
			
			QuestionDTO question = userService.getMyQnAOne(questionnum);	// 1:1 문의글 고유번호로 문의글 한개의 정보 가져오기
			model.addAttribute("question", question);
			
			// 내 적용된 뱃지 가져오기
			BadgeDTO mybadge = userService.getMyBadge(user.getId());
			model.addAttribute("mybadge", mybadge.getBadgeimg());
			
			return "user/myQnAReplyCheck";
		}

	// 마이페이지 : 신고내역
		
		// 마이페이지 - 신고내역 : 해당 사용자가 다른 사용자를 신고한 내역
		@RequestMapping("myReport.et")
		public String etmyReport(Model model, String pageNum) throws SQLException {
			Map<String, Object> result = userService.getMyReportList(pageNum);
			
			// 세션 아이디로 해당 사용자 정보 가져오기
			UserDTO user = userService.getUser();

			// 내 적용된 뱃지 가져오기
			BadgeDTO mybadge = userService.getMyBadge(user.getId());
			
			// view 에 전달할 데이터 보내기
			model.addAttribute("user", user);
			model.addAttribute("pageSize", result.get("pageSize"));
			model.addAttribute("pageNum", result.get("pageNum"));
			model.addAttribute("currentPage", result.get("currentPage"));
			model.addAttribute("startRow", result.get("startRow"));
			model.addAttribute("endRow", result.get("endRow"));
			model.addAttribute("rpArticleList", result.get("rpArticleList"));
			model.addAttribute("count", result.get("count"));
			model.addAttribute("number", result.get("number"));				
			model.addAttribute("mybadge", mybadge.getBadgeimg());
					
			return "user/myReport";
		}
	
	// 작성자 : 김예찬	
	// 마이페이지 : 나의 에코백

		// mybag - 김예찬
		@RequestMapping("myBag.et")	
		public String etmyBag(String pageNum,HttpSession session, Model model) throws SQLException{
			System.out.println("나의 에코백 요청");
			
			// 세션에서 유저 아이디 불러오기 
			String id = (String)session.getAttribute("sid");
			
			// 세션 아이디로 해당 사용자 정보 가져오기
			UserDTO user = userService.getUser();
			model.addAttribute("user", user);
		
			Map<String, Object> result;
			
			// 보유 뱃지 목록 가져오기 
			result = userService.getMyBadgeList(pageNum,id);

			// 내 적용된 뱃지 가져오기
			BadgeDTO mybadge = userService.getMyBadge(id);
			
			// 내 포인트 가져오기
			int point = userService.getPoint(id);
			
			// view 에 전달할 데이터 보내기
			model.addAttribute("pageSize", result.get("pageSize"));
			model.addAttribute("pageNum", result.get("pageNum"));
			model.addAttribute("currentPage", result.get("currentPage"));
			model.addAttribute("startRow", result.get("startRow"));
			model.addAttribute("endRow", result.get("endRow"));
			model.addAttribute("articleList", result.get("articleList"));
			model.addAttribute("count", result.get("count"));
			model.addAttribute("number", result.get("number"));
			model.addAttribute("mybadge", mybadge.getBadgeimg());
			model.addAttribute("badgeName", mybadge.getBadgename());
			model.addAttribute("point", point);

			return "user/myBag";
		}
		
		// 뱃지 해제하기 - 김예찬
		@RequestMapping("releaseBadge.et")	
		public String releaseBadge(HttpSession session, Model model) throws SQLException{
			System.out.println("해제하기 요청");
			
			// 세션에서 유저 아이디 불러오기 
			String id = (String)session.getAttribute("sid");
					
			userService.releaseBadge(id);
			
			return "redirect:/user/myBag.et";
		}
		
		// 뱃지 착용하기 - 김예찬
		@RequestMapping("equipBadge.et")	
		public String equipBadge(int badgenum, HttpSession session, Model model) throws SQLException{
			System.out.println("해제하기 요청");
			
			// 세션에서 유저 아이디 불러오기 
			String id = (String)session.getAttribute("sid");
			
			userService.equipBadge(id,badgenum);
			
			return "redirect:/user/myBag.et";
		}		
	
		
	// 작성자 : 이다희 김하영	
	// 마이페이지 : 출석체크
	
		//출석체크 페이지 불러오기
		@RequestMapping("myCheck.et")
		public String etmyCheck(String date, Model model) throws SQLException {
			System.out.println("출석체크 페이지 요청 ");

			UserDTO user = userService.getUser();
			model.addAttribute("user", user);	
			
			// 내 적용된 뱃지 가져오기
			BadgeDTO mybadge = userService.getMyBadge(user.getId());
			model.addAttribute("mybadge", mybadge.getBadgeimg());			

			return "user/myCheck";
		}

		//출석체크 인서트 
		@RequestMapping("ajaxCkPo.et")
			public ResponseEntity<String> ajaxCkPo(Model model, String date, HttpSession session) throws SQLException {

			System.out.println("date" + date);
			String id = (String)session.getAttribute("sid");

			int result = userService.insertCheck(id, date); 

			String data = "";  
			if(result == 1) {	 
				data = "1";  
			}else if(result ==0){				 
				data ="0";	 
			}
			System.out.println("result" + result);


			model.addAttribute("result", result);
			HttpHeaders respHeaders = new HttpHeaders();
			respHeaders.add("Content-Type", "test/html;charset=UTF-8");

			return new ResponseEntity<String>(data, respHeaders, HttpStatus.OK);
		}

		//출석체크 리스트 가져오기
		@RequestMapping("attend.et")
		public ResponseEntity<ArrayList<String>> attend(String id, HttpSession session) throws SQLException{
			System.out.println("attendList.et 요청 ");
			ArrayList<String> attendList = new ArrayList<>();

			id = (String)session.getAttribute("sid");
			System.out.println("세션아이디로 바꿔주기 -> "+ id);

			attendList = userService.getAttendList(id);
			System.out.println("attendList============." + attendList);

			HttpHeaders respHeaders = new HttpHeaders();
			respHeaders.add("Content-Type", "test/html;charset=UTF-8");

			return new ResponseEntity<ArrayList<String>>(attendList, respHeaders, HttpStatus.OK);
		}
		
}
