package earth.user.dao;

import java.sql.SQLException;
import java.util.List;

import earth.board.dto.DiaryDTO;
import earth.board.dto.FreeDTO;
import earth.board.dto.TodayDTO;
import earth.user.dto.CommentDTO;
import earth.user.dto.QuestionDTO;
import earth.user.dto.ReportDTO;
import earth.user.dto.UserDTO;
import earth.user.dto.WarningDTO;

public interface UserDAO {

	// 작성자 : 이영인
	
	// 회원가입, 로그인
	
		// 사용자 회원가입
		public void insertUser(UserDTO dto) throws SQLException;
		// 회원가입 : 닉네임 중복여부 확인
		public int nickCheck(UserDTO dto) throws SQLException;

		// 암호화 된 비밀번호 가져오기
		public String getPw(String id) throws SQLException;
		
		// 로그인 : 아이디, 패스워드 확인 (+.회원가입 : 아이디 중복여부 확인 / 암호화 비밀번호 확인시 활용)
		public int idPwCheck(UserDTO dto) throws SQLException;

	// 마이페이지 : 마이페이지, 내 프로필 보기	
		
		// 마이페이지 : 마이페이지 (사용자 마이페이지 메인 화면X. 경고 내역 확인 페이지) : 회원 한명의 경고내역 가져오기	
		public WarningDTO selectUserWarn(String id) throws SQLException;
		
		// 마이페이지 - 회원정보수정 : 회원 한명 전체정보 조회
		public UserDTO selectUserOne(String id) throws SQLException;
		// 마이페이지 - 회원정보수정 : 회원 한명 정보 수정
		public void modifyUser(UserDTO dto) throws SQLException;
		// 마이페이지 - 회원정보수정 : 비밀번호 변경
		public void modifyPw(UserDTO dto)throws SQLException;	
		// 마이페이지 - 회원탈퇴처리 : 회원정보전체삭제
		public void deleteUser(UserDTO dto) throws SQLException;


	// 마이페이지 : 나의 실천 / 나의 환경일기 / 내 게시글 보기
	
		// 마이페이지 -  나의 게시글 개수 가져오기
		public int getMyArticleCount(int code, String id) throws SQLException;
	
		
		// 마이페이지 -  나의 실천(오늘의실천) 목록 가져오기
		public List<TodayDTO> getMyTodayList(int startRow, int endRow, int code, String id) throws SQLException;
		//마이페이지 - 나의 실천 : 게시글 삭제 처리
		public void deleteMyToday(TodayDTO dto) throws SQLException;
	
		
		// 내 게시글 보기 : 자유게시판 목록 가져오기 
		public List<FreeDTO> getMyFreeList(int startRow, int endRow, int code, String id) throws SQLException;
	
		
		// 마이페이지 -  나의 환경일기 : 환경일기 목록 가져오기
		public List<DiaryDTO> getMyDiaryList(int startRow, int endRow, int code, String id) throws SQLException;
		// 마이페이지 - 나의 환경일기 : 검색 된 환경일기 개수 가져오기
		public int getMyDiarySearchCount(String sel, String search, String id) throws SQLException;
		// 마이페이지 - 나의 환경일기 : 검색 된 환경일기 목록 가져오기 
		public List<DiaryDTO> getMyDiarySearch(int startRow, int endRow, String sel, String search, String id) throws SQLException;


	// 마이페이지 : 내가 작성한 댓글	
		
		// 마이페이지 : 내가 작성한 댓글 개수 가져오기
		public int getMyCommentCount(String id) throws SQLException;	
		// 마이페이지 - 내가 작성한 댓글 : 자유게시판/환경일기/이달의챌린지(게시글을 댓글 형태로 등록하는 게시판)
		public List<CommentDTO> getMyCommentList(int startRow, int endRow, String id) throws SQLException;
		// 마이페이지 : 내가 작성한 댓글 검색 된 개수 가져오기
		public int getMyCommentSearchCount(String sel, String search, String id) throws SQLException;
		// 마이페이지 : 내가 작성한 댓글 검색 된 목록 가져오기
		public List<CommentDTO> getMyCommentSearch(int startRow, int endRow, String sel, String search, String id) throws SQLException;

	// 마이페이지 : 1:1문의
		
		// 마이페이지 : 1:1문의글 개수 가져오기
		public int getMyQnACount(String id) throws SQLException;
		// 마이페이지 : 1:1문의글 검색 된 게시글 개수 가져오기
		public int getMyQnASearchCount(String sel, String search, String id);
		// 마이페이지 : 1:1 문의글 목록 가져오기
		public List<QuestionDTO> getMyQnAList(int startRow, int endRow, String id) throws SQLException;
		// 마이페이지 : 1:1문의글 검색 된 목록 가져오기
		public List<QuestionDTO> getMyQnASearch(int startRow, int endRow, String sel, String search, String id) throws SQLException;
		// 마이페이지 : 1:1문의글 등록하기
		public void addQnA(QuestionDTO dto) throws SQLException;
		// 마이페이지 : 1:1문의글 한개 조회
		public QuestionDTO getMyQnAOne(int questionnum) throws SQLException;
		// 마이페이지 - 1:1문의글 수정 처리
		public void modifyQnA(QuestionDTO dto) throws SQLException;
		// 마이페이지 - 1:1 문의글 삭제 처리	
		public void deleteMyQna(QuestionDTO dto) throws SQLException;
	
	// 마이페이지 : 신고내역
		
		// 마이페이지 : 신고내역 개수 가져오기
		public int getMyReportCount(String id) throws SQLException;
		// 마이페이지 : 신고내역 목록 가져오기
		public List<ReportDTO> getMyReportList(int startRow, int endRow, String id) throws SQLException;

	// 작성자 : 김예찬	
	// 마이페이지 : 나의 에코백

		// 나의 뱃지 총 개수 - 김예찬
		public int getMyBadgeCount(String id) throws SQLException;
		
		// 나의 뱃지 보유목록 가져오기 - 김예찬
		public List<BadgeDTO> getMyBadgeArticles(int startRow, int endRow, String id) throws SQLException;
		
		// 내 적용중 뱃지 가져오기 - 김예찬
		public BadgeDTO getMyBadge(String id) throws SQLException;
		
		// 착용중인 뱃지 해제하기 (1- Default) - 김예찬
		public void releaseBadge(String id) throws SQLException;
		
		// 뱃지 장착하기 - 김예찬
		public void equipBadge(String id, int badgenum) throws SQLException;	
			
	// 작성자 : 이다희 김하영
	// 마이페이지 : 출석체크
	
		//출석 인서트
		public int insertCheck(String id, String date) throws SQLException;
		// 출석 : 출석현황 개수 가져오기 
		public int getAttendListCount(String id)throws SQLException;
		//출석 : 출석현황 리스트 가져오기
		public ArrayList<String> getAttendList(String id) throws SQLException;


}
