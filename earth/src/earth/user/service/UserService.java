package earth.user.service;

import java.sql.SQLException;
import java.util.Map;

import earth.board.dto.TodayDTO;
import earth.user.dto.QuestionDTO;
import earth.user.dto.UserDTO;
import earth.user.dto.WarningDTO;

public interface UserService {
	
	// 작성자 : 이영인		
	
	// 회원가입
	
		// 사용자 회원가입
		public void addUser(UserDTO dto) throws SQLException;
		// 아이디 중복 확인
		public int idCheck(UserDTO dto) throws SQLException;
		// 닉네임 중복 확인
		public int nickCheck(UserDTO dto) throws SQLException;

	// 로그인, 로그아웃
		
		// 로그인 : 아이디, 패스워드 확인 후 세션 아이디 추가
		public int idPwCheck(UserDTO dto) throws SQLException;		
		// 로그아웃 : 세션 삭제 (+.비밀번호 변경 활용)
		public void removeSession(String sessionName);

	// 암호화 된 비밀번호 확인 처리시 활용 (회원정보 수정, 비밀번호 변경 등)
		
		// 비밀번호 일치 여부 확인 : 암호화 된 비밀번호 확인 
		public int checkPw(UserDTO dto) throws SQLException;

	// 마이페이지 : 사용자 마이페이지의 메인 View X, 경고내역 확인 할 수 있는 페이지
		
		// 마이페이지 - 마이페이지 (사용자 마이페이지 메인 화면X. 경고 내역 확인 페이지) : 회원 한명의 경고내역 가져오기
		public WarningDTO getUserWarn() throws SQLException;
		
	// 마이페이지 : 내 프로필 보기	
		
		// 마이페이지 - 회원정보수정 : 회원 정보 하나만 가져오기 (+.마이페이지 모든 view 활용 : 뱃지 이미지, 닉네임, 경고내역 등 계속 보여줘야 함)
		public UserDTO getUser() throws SQLException;
		// 마이페이지 - 회원정보수정 : 회원 정보 수정 처리하기
		public void modifyUser(UserDTO dto) throws SQLException;
		// 마이페이지 - 회원정보수정 : 비밀번호 변경하기
		public void modifyPw(UserDTO dto) throws SQLException;	
		// 마이페이지 - 회원탈퇴처리
		public void deleteUser(UserDTO dto) throws SQLException;


	// 마이페이지 : 나의 실천 / 나의 환경일기 / 내 게시글 보기
		
		// 마이페이지 : 게시판의 나의 게시글 목록 가져오기 - 나의실천(오늘의실천) 목록 가져오기 / 나의 환경일기/ 내 게시글 보기(자유게시판) 목록 가져오기 활용
		public Map<String,Object> getMyArticleList(String pageNum, int code, String id) throws SQLException;

		// 마이페이지 - 나의 실천(오늘의 실천) : 게시글 삭제
		public void deleteMyToday(TodayDTO dto) throws SQLException;
		
		// 마이페이지 - 나의 환경일기 : 검색 된 환경일기 목록 가져오기
		public Map<String, Object> getMyDiarySearch(String pageNum, String sel, String search) throws SQLException;
		
	// 마이페이지 : 내가 작성한 댓글	
	
		// 마이페이지 - 내가 작성한 댓글 : 자유게시판/환경일기/이달의챌린지(게시글을 댓글 형태로 등록하는 게시판)
		public Map<String, Object> getMyCommentList(String pageNum, String id) throws SQLException;
		// 마이페이지 - 내가 작성한 댓글 : 자유게시판/환경일기/이달의챌린지의 검색 된 댓글 목록 가져오기
		public Map<String, Object> getMyCommentSearch(String pageNum, String sel, String search) throws SQLException;

	// 마이페이지 : 1:1문의	
		
		// 마이페이지 - 1:1 문의 목록 가져오기
		public Map<String, Object> getMyQnAList(String pageNum) throws SQLException;
		// 마이페이지 - 1:1 문의 검색 목록 가져오기
		public Map<String, Object> getMyQnASearch(String pageNum, String sel, String search) throws SQLException;
		// 마이페이지 - 1:1 문의글 등록
		public void addQnA(QuestionDTO dto) throws SQLException;
		// 마이페이지 - 1:1 문의글 수정 : 해당 문의글 정보 하나만 가져오기
		public QuestionDTO getMyQnAOne(int questionnum) throws SQLException;
		// 마이페이지 - 1:1 문의글 수정 처리
		public void modifyQnA(QuestionDTO dto) throws SQLException;
		// 마이페이지 - 1:1 문의글 삭제 처리
		public void deleteMyQnA(QuestionDTO dto) throws SQLException;
		
	// 마이페이지 - 신고내역
		public Map<String, Object> getMyReportList(String pageNum) throws SQLException;
	
}
