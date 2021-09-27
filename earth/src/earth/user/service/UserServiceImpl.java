package earth.user.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import earth.badge.dto.BadgeDTO;
import earth.board.dto.DiaryDTO;
import earth.board.dto.FreeDTO;
import earth.board.dto.TodayDTO;
import earth.user.dao.UserDAOImpl;
import earth.user.dto.CommentDTO;
import earth.user.dto.QuestionDTO;
import earth.user.dto.ReportDTO;
import earth.user.dto.UserDTO;
import earth.user.dto.WarningDTO;

@Service
public class UserServiceImpl implements UserService {

	// 작성자 : 이영인
	@Autowired
	private UserDAOImpl userDAO = null;

	// 회원가입
	
		// 사용자 회원가입 : 데이터 넘겨 받은 후, DB에 저장
		@Override
		public void addUser(UserDTO dto) throws SQLException {
			userDAO.insertUser(dto);
		}
		// 회원가입 : 아이디 중복여부 체크
		@Override
		public int idCheck(UserDTO dto) throws SQLException {
			int result = userDAO.idCheck(dto);
			return result;
		}
		// 회원가입 : 닉네임 중복여부 체크
		@Override
		public int nickCheck(UserDTO dto) throws SQLException {
			int result = userDAO.nickCheck(dto);
			return result;
		}

	// 로그인, 로그아웃	
		
		// 로그인 : 아이디, 패스워드 확인 후 세션 아이디 추가
		@Override
		public int idPwCheck(UserDTO dto) throws SQLException {
			String hashedPassword = userDAO.getPw(dto.getId());
			if(BCrypt.checkpw(dto.getPw(), hashedPassword)) {			// 사용자가 입력한 비밀번호와 DB에 암호화 된 비밀번호가 일치하는 경우
				dto.setPw(hashedPassword);
			}		
			int result = userDAO.idPwCheck(dto);
			
			if(result == 1) {											// ID,PW 확인 후 일치하는 데이터가 존재하는 경우 세션 아이디 추가
				RequestContextHolder.getRequestAttributes().setAttribute("sid", dto.getId(), RequestAttributes.SCOPE_SESSION);
			}
			return result;
		}
	
		
		// 로그아웃 : 세션 삭제 (*비밀번호 변경, 회원탈퇴 활용)
		@Override
		public void removeSession(String sessionName) {
			RequestContextHolder.getRequestAttributes().removeAttribute(sessionName, RequestAttributes.SCOPE_SESSION);		
		}

	// 암호화 된 비밀번호 확인 처리시 활용 (회원정보 수정, 비밀번호 변경 등)	
		
		// 비밀번호 일치 여부 확인 : 암호화 된 비밀번호 확인 
		@Override
		public int checkPw(UserDTO dto) throws SQLException {
			String id = (String)RequestContextHolder.getRequestAttributes().getAttribute("sid", RequestAttributes.SCOPE_SESSION);
			dto.setId(id);
			String hashedPassword = userDAO.getPw(id);
			
			int result = 0;
			
			if(BCrypt.checkpw(dto.getPw(), hashedPassword)) {						// 사용자가 입력한 비밀번호와 DB 비밀번호가 일치하는 경우
				dto.setPw(hashedPassword);
				result = userDAO.idPwCheck(dto);
			}		
			return result;
		}
		

	//******** 사용자 마이페이지 ********
		
	// 마이페이지 : 마이페이지, 내 프로필 보기	
		
		// 마이페이지 - 마이페이지 (사용자 마이페이지 메인 화면X. 경고 내역 확인 페이지) : 회원 한명의 경고내역 가져오기
		@Override
		public WarningDTO getUserWarn() throws SQLException {
			String id = (String)RequestContextHolder.getRequestAttributes().getAttribute("sid", RequestAttributes.SCOPE_SESSION);
			WarningDTO warn = userDAO.selectUserWarn(id);
			return warn;
		}	
		
		// 마이페이지 - 회원정보수정 : 회원 정보 하나만 가져오기
		@Override
		public UserDTO getUser() throws SQLException {
			String id = (String)RequestContextHolder.getRequestAttributes().getAttribute("sid", RequestAttributes.SCOPE_SESSION);
			UserDTO user = userDAO.selectUserOne(id);		
			return user;
		}
		// 마이페이지 - 회원정보수정 : 회원 정보 수정 처리하기
		@Override
		public void modifyUser(UserDTO dto) throws SQLException {
			userDAO.modifyUser(dto);
		}
		// 마이페이지 - 회원정보수정 : 비밀번호 변경하기
		@Override
		public void modifyPw(UserDTO dto) throws SQLException {
			userDAO.modifyPw(dto);
		}
		
		// 마이페이지 - 회원탈퇴처리 : Controller에서 ID,PW 체크
		@Override
		public void deleteUser(UserDTO dto) throws SQLException {
			userDAO.deleteUser(dto);
		}

	
	// 마이페이지 : 나의 실천 / 나의 환경일기 / 내 게시글 보기
		
		// 마이페이지 : 게시판의 나의 게시글 목록 가져오기 - 나의실천(오늘의실천) 목록 가져오기 / 나의 환경일기/ 내 게시글 보기(자유게시판) 목록 가져오기 활용
		@Override
		public Map<String, Object> getMyArticleList(String pageNum, int code, String id) throws SQLException {
			// 한 페이지에 보여줄 게시글의 수
			int pageSize = 5;
			
			// 현재 페이지 번호 : pageNum 파라미터 안넘어왔을때 1 페이지 세팅
			if(pageNum == null) {
				pageNum = "1";
			}
			
			// 현재 페이지에서 보여줄 게시글 시작과 끝 정보 세팅
			int currentPage = Integer.parseInt(pageNum);		// 현재페이지 숫자로 형변환
			int startRow = (currentPage - 1) * pageSize + 1;	// 페이지 시작 글 번호
			int endRow = currentPage * pageSize;				// 페이지 마지막 글 번호
			int count = 0;										// 전체 글의 개수
			int number = 0;										// 브라우저 화면에 보여줄 가상의 글 번호
			
			// DB에 저장되어 있는 전체 글의 개수 가져와 담기
			count = userDAO.getMyArticleCount(code, id);
			number = count - (currentPage - 1) * pageSize;
			System.out.println("전체 게시글 수 : " + count);
			
			Map<String, Object> result = new HashMap<String, Object>();
			
			// 글이 하나라도 있으면 글 가져오기
			switch(code) {
			case 1:
				break;
			case 2:
				if(count > 0) {
					List<FreeDTO> freeArticleList = userDAO.getMyFreeList(startRow, endRow, code, id);
					result.put("freeArticleList", freeArticleList);
				}
				break;
			case 3:
				if(count > 0) {
					List<DiaryDTO> diaryArticleList = userDAO.getMyDiaryList(startRow, endRow, code, id);
					result.put("diaryArticleList", diaryArticleList);
				}
				break;
			case 4 :
				break;
			case 5:
				if(count > 0) {
					List<TodayDTO> articleList = userDAO.getMyTodayList(startRow, endRow, code, id);
					result.put("articleList", articleList);
				}
				break;
			}
			
			// controller 에게 전달
			result.put("pageSize", pageSize);
			result.put("pageNum", pageNum);
			result.put("currentPage", currentPage);
			result.put("startRow", startRow);
			result.put("endRow", endRow);
			result.put("count", count);
			result.put("number", number);
			
			return result;		
		}
	
		
		// 마이페이지 - 나의 실천 : 게시글 삭제
		@Override
		public void deleteMyToday(TodayDTO dto) throws SQLException {
			userDAO.deleteMyToday(dto);		
		}
	
		
		// 마이페이지 - 나의 환경일기 : 검색 된 환경일기 목록 가져오기
		@Override
		public Map<String, Object> getMyDiarySearch(String pageNum, String sel, String search) throws SQLException {
			String id = (String)RequestContextHolder.getRequestAttributes().getAttribute("sid", RequestAttributes.SCOPE_SESSION);
			// **게시글 페이지 관련 정보 세팅**
			// 한 페이지에 보여줄 게시글의 수
			int pageSize = 5;
			
			// 현재 페이지 번호 : pageNum 파라미터 안넘어왔을때 1 페이지 세팅
			if(pageNum == null) {
				pageNum = "1";
			}
			
			// 현재 페이지에서 보여줄 게시글 시작과 끝 정보 세팅
			int currentPage = Integer.parseInt(pageNum);		// 현재페이지 숫자로 형변환
			int startRow = (currentPage - 1) * pageSize +1;		// 페이지 시작 글 번호
			int endRow = currentPage * pageSize;				// 페이지 마지막 글 번호
			int count = 0;										// 전체 검색 된 글의 개수
			int number = 0;										// 브라우저 화면에 보여줄 가상의 글 번호
			
			// DB에 저장되어 있는 전체 글의 개수 가져와 담기
			count = userDAO.getMyDiarySearchCount(sel, search, id);
			number = count - (currentPage - 1) * pageSize;
			System.out.println("검색 된 게시글 수 : " + count);
			
			Map<String, Object> result = new HashMap<String, Object>();
			
			// 검색한 글이 0 이상일 경우 검색 된 게시글 가져오기
			if(count > 0) {
				List<DiaryDTO> diaryArticleList = userDAO.getMyDiarySearch(startRow, endRow, sel, search, id);
				result.put("diaryArticleList", diaryArticleList);
			}		
			
			// controller 에게 전달
			result.put("pageSize", pageSize);
			result.put("pageNum", pageNum);
			result.put("currentPage", currentPage);
			result.put("startRow", startRow);
			result.put("endRow", endRow);
			result.put("count", count);
			result.put("number", number);
			
			return result;		
		}

	// 마이페이지 : 내가 작성한 댓글	
	
		// 마이페이지 - 내가 작성한 댓글 : 자유게시판/환경일기/이달의챌린지(게시글을 댓글 형태로 등록하는 게시판)	
		@Override
		public Map<String, Object> getMyCommentList(String pageNum, String id) throws SQLException {
			// **게시글 페이지 관련 정보 세팅**
			// 한 페이지에 보여줄 게시글의 수
			int pageSize = 5;
			
			// 현재 페이지 번호 : pageNum 파라미터 안넘어왔을때 1 페이지 세팅
			if(pageNum == null) {
				pageNum = "1";
			}
			
			// 현재 페이지에서 보여줄 게시글 시작과 끝 정보 세팅
			int currentPage = Integer.parseInt(pageNum);		// 현재페이지 숫자로 형변환
			int startRow = (currentPage - 1) * pageSize +1;		// 페이지 시작 글 번호
			int endRow = currentPage * pageSize;				// 페이지 마지막 글 번호
			int count = 0;										// 전체 글의 개수
			int number = 0;										// 브라우저 화면에 보여줄 가상의 글 번호
			
			// DB에 저장되어 있는 전체 글의 개수 가져와 담기
			count = userDAO.getMyCommentCount(id);
			number = count - (currentPage - 1) * pageSize;
			System.out.println("전체 게시글 수 : " + count);
			
			Map<String, Object> result = new HashMap<String, Object>();
			
			if(count > 0) {
				List<CommentDTO> commentList = userDAO.getMyCommentList(startRow, endRow, id);
				result.put("commentList", commentList);
			}		
			
			// controller 에게 전달
			result.put("pageSize", pageSize);
			result.put("pageNum", pageNum);
			result.put("currentPage", currentPage);
			result.put("startRow", startRow);
			result.put("endRow", endRow);
			result.put("count", count);
			result.put("number", number);
			
			return result;		
		}
		
		// 마이페이지 - 내가 작성한 댓글 : 자유게시판/환경일기/이달의챌린지의 검색 된 댓글 목록 가져오기
		@Override
		public Map<String, Object> getMyCommentSearch(String pageNum, String sel, String search) throws SQLException {
			String id = (String)RequestContextHolder.getRequestAttributes().getAttribute("sid", RequestAttributes.SCOPE_SESSION);
			// **게시글 페이지 관련 정보 세팅**
			// 한 페이지에 보여줄 게시글의 수
			int pageSize = 5;
			
			// 현재 페이지 번호 : pageNum 파라미터 안넘어왔을때 1 페이지 세팅
			if(pageNum == null) {
				pageNum = "1";
			}
			
			// 현재 페이지에서 보여줄 게시글 시작과 끝 정보 세팅
			int currentPage = Integer.parseInt(pageNum);		// 현재페이지 숫자로 형변환
			int startRow = (currentPage - 1) * pageSize +1;		// 페이지 시작 글 번호
			int endRow = currentPage * pageSize;				// 페이지 마지막 글 번호
			int count = 0;										// 전체 검색 된 글의 개수
			int number = 0;										// 브라우저 화면에 보여줄 가상의 글 번호
			
			// DB에 저장되어 있는 전체 글의 개수 가져와 담기
			count = userDAO.getMyCommentSearchCount(sel, search, id); 
			number = count - (currentPage - 1) * pageSize;
			System.out.println("검색 된 게시글 수 : " + count);
			
			Map<String, Object> result = new HashMap<String, Object>();
			
			// 검색한 글이 0 이상일 경우 검색 된 게시글 가져오기
			if(count > 0) {
				List<CommentDTO> commentList = userDAO.getMyCommentSearch(startRow, endRow, sel, search, id);
				result.put("commentList", commentList);
			}		
			
			// controller 에게 전달
			result.put("pageSize", pageSize);
			result.put("pageNum", pageNum);
			result.put("currentPage", currentPage);
			result.put("startRow", startRow);
			result.put("endRow", endRow);
			result.put("count", count);
			result.put("number", number);
			
			return result;	
		}
	
	// 마이페이지 : 1:1문의
		
		// 마이페이지 - 1:1문의
		@Override
		public Map<String, Object> getMyQnAList(String pageNum) throws SQLException {
			String id = (String)RequestContextHolder.getRequestAttributes().getAttribute("sid", RequestAttributes.SCOPE_SESSION);
			// **게시글 페이지 관련 정보 세팅**
			// 한 페이지에 보여줄 게시글의 수
			int pageSize = 3;
			
			// 현재 페이지 번호 : pageNum 파라미터 안넘어왔을때 1 페이지 세팅
			if(pageNum == null) {
				pageNum = "1";
			}
			
			// 현재 페이지에서 보여줄 게시글 시작과 끝 정보 세팅
			int currentPage = Integer.parseInt(pageNum);		// 현재페이지 숫자로 형변환
			int startRow = (currentPage - 1) * pageSize +1;		// 페이지 시작 글 번호
			int endRow = currentPage * pageSize;				// 페이지 마지막 글 번호
			int count = 0;										// 전체 글의 개수
			int number = 0;										// 브라우저 화면에 보여줄 가상의 글 번호
			
			// DB에 저장되어 있는 전체 글의 개수 가져와 담기
			count = userDAO.getMyQnACount(id);
			number = count - (currentPage - 1) * pageSize;
			System.out.println("전체 게시글 수 : " + count);
			
			Map<String, Object> result = new HashMap<String, Object>();
			
			if(count > 0) {
				List<QuestionDTO> qtArticleList = userDAO.getMyQnAList(startRow, endRow, id);
				result.put("qtArticleList", qtArticleList);
			}		
			
			// controller 에게 전달
			result.put("pageSize", pageSize);
			result.put("pageNum", pageNum);
			result.put("currentPage", currentPage);
			result.put("startRow", startRow);
			result.put("endRow", endRow);
			result.put("count", count);
			result.put("number", number);
			
			return result;		
		}
		
		// 마이페이지 - 1:1 문의 검색 목록 가져오기
		@Override
		public Map<String, Object> getMyQnASearch(String pageNum, String sel, String search) throws SQLException {
			String id = (String)RequestContextHolder.getRequestAttributes().getAttribute("sid", RequestAttributes.SCOPE_SESSION);
			// **게시글 페이지 관련 정보 세팅**
			// 한 페이지에 보여줄 게시글의 수
			int pageSize = 3;
			
			// 현재 페이지 번호 : pageNum 파라미터 안넘어왔을때 1 페이지 세팅
			if(pageNum == null) {
				pageNum = "1";
			}
			
			// 현재 페이지에서 보여줄 게시글 시작과 끝 정보 세팅
			int currentPage = Integer.parseInt(pageNum);		// 현재페이지 숫자로 형변환
			int startRow = (currentPage - 1) * pageSize +1;		// 페이지 시작 글 번호
			int endRow = currentPage * pageSize;				// 페이지 마지막 글 번호
			int count = 0;										// 전체 검색 된 글의 개수
			int number = 0;										// 브라우저 화면에 보여줄 가상의 글 번호
			
			// DB에 저장되어 있는 전체 글의 개수 가져와 담기
			count = userDAO.getMyQnASearchCount(sel, search, id);
			number = count - (currentPage - 1) * pageSize;
			System.out.println("검색 된 게시글 수 : " + count);
			
			Map<String, Object> result = new HashMap<String, Object>();
			
			// 검색한 글이 0 이상일 경우 검색 된 게시글 가져오기
			if(count > 0) {
				List<QuestionDTO> qtArticleList = userDAO.getMyQnASearch(startRow, endRow, sel, search, id);
				result.put("qtArticleList", qtArticleList);
			}		
			
			// controller 에게 전달
			result.put("pageSize", pageSize);
			result.put("pageNum", pageNum);
			result.put("currentPage", currentPage);
			result.put("startRow", startRow);
			result.put("endRow", endRow);
			result.put("count", count);
			result.put("number", number);
			
			return result;		
		}
		
		// 마이페이지 - 1:1문의글 등록
		@Override
		public void addQnA(QuestionDTO dto) throws SQLException {
			String id = (String)RequestContextHolder.getRequestAttributes().getAttribute("sid", RequestAttributes.SCOPE_SESSION);
			dto.setId(id);
			userDAO.addQnA(dto);
		}
		
		// 마이페이지 - 1:1 문의글 수정 : 해당 문의글 정보 하나만 가져오기
		@Override
		public QuestionDTO getMyQnAOne(int questionnum) throws SQLException {
			QuestionDTO question = userDAO.getMyQnAOne(questionnum);
			return question;
		}
		// 마이페이지 - 1:1문의글 수정 처리
		@Override
		public void modifyQnA(QuestionDTO dto) throws SQLException {
			String id = (String)RequestContextHolder.getRequestAttributes().getAttribute("sid", RequestAttributes.SCOPE_SESSION);
			dto.setId(id);
			userDAO.modifyQnA(dto);
		}
		
		// 마이페이지 - 1:1 문의글 삭제 처리	
		@Override
		public void deleteMyQnA(QuestionDTO dto) throws SQLException {
			userDAO.deleteMyQna(dto);
		}
	
	
	// 마이페이지 - 신고내역
		@Override
		public Map<String, Object> getMyReportList(String pageNum) throws SQLException {
			String id = (String)RequestContextHolder.getRequestAttributes().getAttribute("sid", RequestAttributes.SCOPE_SESSION);
			// **게시글 페이지 관련 정보 세팅**
			// 한 페이지에 보여줄 게시글의 수
			int pageSize = 5;
			
			// 현재 페이지 번호 : pageNum 파라미터 안넘어왔을때 1 페이지 세팅
			if(pageNum == null) {
				pageNum = "1";
			}
			
			// 현재 페이지에서 보여줄 게시글 시작과 끝 정보 세팅
			int currentPage = Integer.parseInt(pageNum);		// 현재페이지 숫자로 형변환
			int startRow = (currentPage - 1) * pageSize +1;		// 페이지 시작 글 번호
			int endRow = currentPage * pageSize;				// 페이지 마지막 글 번호
			int count = 0;										// 전체 글의 개수
			int number = 0;										// 브라우저 화면에 보여줄 가상의 글 번호
			
			// DB에 저장되어 있는 전체 글의 개수 가져와 담기
			count = userDAO.getMyReportCount(id);
			number = count - (currentPage - 1) * pageSize;
			System.out.println("전체 게시글 수 : " + count);
			
			Map<String, Object> result = new HashMap<String, Object>();
			
			if(count > 0) {
				List<ReportDTO> rpArticleList = userDAO.getMyReportList(startRow, endRow, id);
				result.put("rpArticleList", rpArticleList);
			}		
			
			// controller 에게 전달
			result.put("pageSize", pageSize);
			result.put("pageNum", pageNum);
			result.put("currentPage", currentPage);
			result.put("startRow", startRow);
			result.put("endRow", endRow);
			result.put("count", count);
			result.put("number", number);
			
			return result;		
		}
		
	// 작성자 : 김예찬	
	// 마이페이지 : 나의 에코백

		// 나의 뱃지 보유목록 가져오기 - 김예찬
		@Override
		public Map<String, Object> getMyBadgeList(String pageNum, String id) throws SQLException {
			
			// 한 페이지에서 보여줄 뱃지 갯수 
			int pageSize = 5;
			
			// 현재 페이지 번호  
			if(pageNum == null){ // pageNum 파라미터 안넘어왔을때.
				pageNum = "1";
			}
			
			// 현재 페이지에 보여줄 게시글 시작과 끝 등등 정보 세팅 
			int currentPage = Integer.parseInt(pageNum); 
			int startRow = (currentPage - 1) * pageSize + 1; 		// 페이지 시작글 번호 
			int endRow = currentPage * pageSize; 					// 페이지 마지막 글번호
			int count = 0; 											// 전체(검색된) 글의 개수 
			int number = 0; 										// 브라우저 화면에 뿌려줄 가상 글 번호
			
			// DB에 저장되어있는 전체 글의 개수를 가져와 담기
			count = userDAO.getMyBadgeCount(id);					
			number = count - (currentPage-1) * pageSize; 			// 게시판 목록에 뿌려줄 가상의 글 번호 
			System.out.println("등록된 뱃지 개수 : " + count);
			
			Map<String, Object> result = new HashMap<String, Object>();

			if(count > 0){
				List<BadgeDTO> articleList = userDAO.getMyBadgeArticles(startRow, endRow, id); 
				result.put("articleList", articleList);
			}
				
			// Controller 에게 전달
			result.put("pageSize", pageSize);
			result.put("pageNum", pageNum);
			result.put("currentPage", currentPage);
			result.put("startRow", startRow);
			result.put("endRow", endRow);
			result.put("count", count);
			result.put("number", number);
			
			return result;
		}
		
		// 내 적용중 뱃지 가져오기 - 김예찬
		@Override
		public BadgeDTO getMyBadge(String id) throws SQLException {
			BadgeDTO result = userDAO.getMyBadge(id); 

			return result;
		}
		
		// 착용중인 뱃지 해제하기 (1- Default)
		@Override
		public void releaseBadge(String id) throws SQLException {
			
			userDAO.releaseBadge(id);
				
		}
		
		// 뱃지 장착하기
		@Override
		public void equipBadge(String id, int badgenum) throws SQLException {
			
			userDAO.equipBadge(id,badgenum);
			
		}		
	
	// 작성자 : 이다희 김하영
	// 마이페이지 : 출석체크
			
		//출석 인서트
		@Override
		public int insertCheck(String id, String date) throws SQLException {

			int result = userDAO.insertCheck(id, date);

			return result;
		}

		//출석 리스트 받아오기
		@Override
		public ArrayList<String> getAttendList(String id) throws SQLException {		
			int count = 0; 

			ArrayList<String> attendList = null;  	

			count = userDAO.getAttendListCount(id); 

			if(count > 0){
				attendList = userDAO.getAttendList(id); 
			}

			System.out.println(count);

			return attendList;
		}

}
