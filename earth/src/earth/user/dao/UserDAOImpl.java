package earth.user.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import earth.board.dto.DiaryDTO;
import earth.board.dto.FreeDTO;
import earth.board.dto.TodayDTO;
import earth.user.dto.CommentDTO;
import earth.user.dto.QuestionDTO;
import earth.user.dto.ReportDTO;
import earth.user.dto.UserDTO;
import earth.user.dto.WarningDTO;

@Repository
public class UserDAOImpl implements UserDAO {
	
	// 작성자 : 이영인
	// 자동주입
	@Autowired
	private SqlSessionTemplate sqlSession = null;

	// 회원가입, 로그인
	
		// 사용자 회원가입 : 회원가입 입력 데이터 전달받고 최종 저장 메서드로 데이터 전달하기 (회원가입시 point 테이블에도 회원정보 추가)
		@Override
		public void insertUser(UserDTO dto) throws SQLException {
			sqlSession.insert("user.insertUser", dto);
			sqlSession.insert("user.insertPoint", dto);
		}
		// 닉네임 중복여부 확인
		@Override
		public int nickCheck(UserDTO dto) throws SQLException {
			int result = sqlSession.selectOne("user.nickCheck", dto);
			return result;
		}
				
		// 로그인 : 아이디, 패스워드 확인
		// DB에 ID,PW 같은 데이터 존재하는지 확인하고 결과는 존재 = 1 , 존재X = 0 리턴
		@Override
		public int idPwCheck(UserDTO dto) throws SQLException {
			int result = sqlSession.selectOne("user.idPwCheck", dto);		
			return result;
		}
		// 암호화 된 비밀번호 가져오기
		@Override
		public String getPw(String id) throws SQLException {
			String hashedPassword = sqlSession.selectOne("user.getPw",id);
			return hashedPassword;
		}
		
	// 마이페이지 : 마이페이지, 내 프로필 보기

		// 마이페이지 - 마이페이지 (사용자 마이페이지 메인 화면X. 경고 내역 확인 페이지) : 회원 한명의 경고내역 가져오기	
		@Override
		public WarningDTO selectUserWarn(String id) throws SQLException {
			WarningDTO warn = sqlSession.selectOne("user.selectUserWarn", id);
			return warn;
		}
		
		// 회원정보수정 : 회원 한명의 정보 가져오기 
		@Override
		public UserDTO selectUserOne(String id) throws SQLException {
			UserDTO user = sqlSession.selectOne("user.selectUserOne", id);	// 쿼리문 실행하고 resultType 에 맞춰서 데이터 넣어서 리턴	
			return user;
		}
		// 회원정보수정 : 회원 정보 수정 처리하기
		@Override
		public void modifyUser(UserDTO dto) throws SQLException {
			sqlSession.update("user.updateUser", dto);
		}
		// 회원정보수정 : 비밀번호 변경
		@Override
		public void modifyPw(UserDTO dto) throws SQLException {
			sqlSession.update("user.modifyPw", dto);
		}
		// 회원탈퇴처리
		@Override
		public void deleteUser(UserDTO dto) throws SQLException {
			sqlSession.delete("user.deleteUser", dto);
		}
	

	// 마이페이지 : 나의 실천 / 나의 환경일기 / 내 게시글 보기

		// 전체 게시판 사용 가능 : 해당 게시판의 나의 게시글 개수 가져오기	
		@Override
		public int getMyArticleCount(int code, String id) throws SQLException {
			
			ArrayList<String> board = new ArrayList<String>();
			board.add("Notice");
			board.add("Free");
			board.add("Diary");
			board.add("Challenge");
			board.add("Today");
			board.add("Shop");
			board.add("Event");
			board.add("Tip");
			
			int result = 0;
			String select = "user.countAll";
			select += board.get(code-1);
			
			result = sqlSession.selectOne(select, id);
			return result;
		}
		
		// 마이페이지 - 나의 실천(오늘의실천) 목록 가져오기
		@Override
		public List<TodayDTO> getMyTodayList(int startRow, int endRow, int code, String id) throws SQLException {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("start", startRow);
			map.put("end", endRow);
			map.put("id", id);
			
			List<TodayDTO> myTodayList = sqlSession.selectList("user.getMyToday",map); 
			return myTodayList;
		}
		//마이페이지 - 나의 실천 : 게시글 삭제 처리
		@Override
		public void deleteMyToday(TodayDTO dto) throws SQLException {
			sqlSession.delete("user.deleteMyToday", dto);
		}
		
		// 마이페이지 - 내 게시글 보기 : 자유게시판 목록 가져오기
		@Override
		public List<FreeDTO> getMyFreeList(int startRow, int endRow, int code, String id) throws SQLException {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("start", startRow);
			map.put("end", endRow);
			map.put("id", id);
			
			List<FreeDTO> myFreeList = sqlSession.selectList("user.getMyFree", map);
			return myFreeList;
		}
		
		// 마이페이지 - 나의 환경일기 : 환경일기 목록 가져오기
		@Override
		public List<DiaryDTO> getMyDiaryList(int startRow, int endRow, int code, String id) throws SQLException {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("start", startRow);
			map.put("end", endRow);
			map.put("id", id);
			
			List<DiaryDTO> myDiaryList = sqlSession.selectList("user.getMyDiary", map);
			return myDiaryList;
		}
		// 마이페이지 - 나의 환경일기 : 검색 된 환경일기 개수 가져오기	
		@Override
		public int getMyDiarySearchCount(String sel, String search, String id) throws SQLException {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("sel", sel);
			map.put("search", search);
			map.put("id", id);
			
			int result = sqlSession.selectOne("user.countDiarySearch", map);
			
			return result;
		}
		// 마이페이지 - 나의 환경일기 : 검색 된 환경일기 목록 가져오기 
		@Override
		public List<DiaryDTO> getMyDiarySearch(int startRow, int endRow, String sel, String search, String id) throws SQLException {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("start", startRow);
			map.put("end", endRow);
			map.put("sel", sel);
			map.put("search", search);
			map.put("id", id);
			
			List<DiaryDTO> myDiaryList = sqlSession.selectList("user.getMyDiarySearch", map);
			return myDiaryList;
		}

	// 마이페이지 : 내가 작성한 댓글
		
		// 마이페이지 : 내가 작성한 댓글 개수 가져오기
		@Override
		public int getMyCommentCount(String id) throws SQLException {
			int result = sqlSession.selectOne("user.countComment", id);
			return result;
		}
		// 마이페이지 - 내가 작성한 댓글 : 자유게시판/환경일기/이달의챌린지(게시글을 댓글 형태로 등록하는 게시판)
		@Override
		public List<CommentDTO> getMyCommentList(int startRow, int endRow, String id) throws SQLException {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("start", startRow);
			map.put("end", endRow);
			map.put("id", id);
			
			List<CommentDTO> commentList = sqlSession.selectList("user.getMyComment", map);
			return commentList;
		}
		// 마이페이지 : 내가 작성한 댓글 검색 된 개수 가져오기
		@Override
		public int getMyCommentSearchCount(String sel, String search, String id) throws SQLException {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("sel", sel);
			map.put("search", search);
			map.put("id", id);
			
			int result = sqlSession.selectOne("user.countCommentSearch", map);
			
			return result;
		}
		// 마이페이지 : 내가 작성한 댓글 검색 된 목록 가져오기
		@Override
		public List<CommentDTO> getMyCommentSearch(int startRow, int endRow, String sel, String search, String id) throws SQLException {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("start", startRow);
			map.put("end", endRow);
			map.put("sel", sel);
			map.put("search", search);
			map.put("id", id);
			
			List<CommentDTO> commentList = sqlSession.selectList("user.getMyCommentSearch", map);
			return commentList;
		}	

	// 마이페이지 : 1:1문의
		
		// 마이페이지 - 1:1문의글 개수 가져오기
		@Override
		public int getMyQnACount(String id) throws SQLException {
			int result = sqlSession.selectOne("user.countQnA", id);
			return result;
		}	
		// 마이페이지 : 1:1문의글 검색 된 게시글 개수 가져오기
		@Override
		public int getMyQnASearchCount(String sel, String search, String id) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("sel", sel);
			map.put("search", search);
			map.put("id", id);
			
			int result = sqlSession.selectOne("user.countQnASearch", map);
			
			return result;
		}
			
		// 마이페이지 - 1:1문의글 목록 가져오기
		@Override
		public List<QuestionDTO> getMyQnAList(int startRow, int endRow, String id) throws SQLException {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("start", startRow);
			map.put("end", endRow);
			map.put("id", id);
			
			List<QuestionDTO> myQnAList = sqlSession.selectList("user.getMyQnA", map);
			return myQnAList;
		}
		// 마이페이지 : 1:1문의글 검색 된 목록 가져오기
		@Override
		public List<QuestionDTO> getMyQnASearch(int startRow, int endRow, String sel, String search, String id) throws SQLException {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("start", startRow);
			map.put("end", endRow);
			map.put("sel", sel);
			map.put("search", search);
			map.put("id", id);
			
			List<QuestionDTO> myQnAList = sqlSession.selectList("user.getMyQnASearch", map);
			return myQnAList;
		}
		
		// 마이페이지 - 1:1문의글 등록하기
		@Override
		public void addQnA(QuestionDTO dto) throws SQLException {
			sqlSession.insert("user.addQnA", dto);		
		}
		// 마이페이지 : 1:1문의글 한개 조회	
		@Override
		public QuestionDTO getMyQnAOne(int questionnum) throws SQLException {
			QuestionDTO question = sqlSession.selectOne("user.getMyQnAOne", questionnum);
			return question;
		}	
		// 마이페이지 - 1:1문의글 수정 처리
		@Override
		public void modifyQnA(QuestionDTO dto) throws SQLException {
			sqlSession.update("user.updateMyQnA", dto);
		}
		// 마이페이지 - 1:1 문의글 삭제 처리		
		@Override
		public void deleteMyQna(QuestionDTO dto) throws SQLException {
			sqlSession.delete("user.deleteMyQnA", dto);
		}

	// 마이페이지 : 신고내역
		
		// 마이페이지 : 신고내역 개수 가져오기	
		@Override
		public int getMyReportCount(String id) throws SQLException {
			int result = sqlSession.selectOne("user.countReport", id);
			return result;
		}
	
		// 마이페이지 : 신고내역 가져오기
		@Override
		public List<ReportDTO> getMyReportList(int startRow, int endRow, String id) throws SQLException {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("start", startRow);
			map.put("end", endRow);
			map.put("id", id);
			
			List<ReportDTO> myReportList = sqlSession.selectList("user.getMyReport", map);
			return myReportList;
		}

	// 작성자 : 김예찬	
	// 마이페이지 : 나의 에코백	
		
		// 나의 뱃지 총 개수 - 김예찬
		@Override
		public int getMyBadgeCount(String id) throws SQLException {		
			int result = sqlSession.selectOne("user.countUserBadge",id);	
			
			return result;
		}
		
		// 나의 뱃지 보유목록 가져오기 - 김예찬
		@Override
		public List<BadgeDTO> getMyBadgeArticles(int startRow, int endRow, String id) throws SQLException {
			HashMap<String, Object> map = new HashMap<String, Object>();
			List<BadgeDTO> badgeList;

			map.put("start", startRow);
			map.put("end", endRow);
			map.put("id", id);
			
			badgeList = sqlSession.selectList("user.getMyBadgeArticles", map);
		
			return badgeList;
			
			
		}
		
		// 내 적용중 뱃지 가져오기 - 김예찬
		@Override
		public BadgeDTO getMyBadge(String id) throws SQLException {
			BadgeDTO result = sqlSession.selectOne("user.getMyBadge", id);
			
			return result;
		}
		
		// 착용중인 뱃지 해제하기 (1- Default)
		@Override
		public void releaseBadge(String id) throws SQLException {
			sqlSession.update("user.releaseBadge", id);
		}
		
		// 뱃지 장착하기 - 김예찬
		@Override
		public void equipBadge(String id, int badgenum) throws SQLException {
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			map.put("id", id);
			map.put("badgenum", badgenum);
			
			sqlSession.update("user.equipBadge", map);
		}			

}
