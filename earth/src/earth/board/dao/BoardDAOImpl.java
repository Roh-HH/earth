package earth.board.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import earth.board.dto.BoardDTO;
import earth.board.dto.MonthDTO;
import earth.board.dto.NoticeDTO;
import earth.board.dto.TodayDTO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	private SqlSessionTemplate sqlSession = null;
	
	// 전체 게시글 글 개수 가져오기(모든 테이블 사용 가능) - 노현호
	@Override
	public int getArticleCount(int code) throws SQLException {
		
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
		String select = "board.countAll";
		select += board.get(code-1);
		
		result = sqlSession.selectOne(select);
		return result;
	}
	
	// 공지사항 게시글 목록 가져오기 - 노현호
	@Override
	public List<NoticeDTO> getNoticeArticles(int startRow, int endRow, int code) throws SQLException {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", startRow);
		map.put("end", endRow);
		
		List<NoticeDTO> boardList = sqlSession.selectList("board.getNoticeArticles", map);
		return boardList;
	}
	
	// 오늘의실천 게시글 목록 가져오기 - 노현호
	@Override
	public List<TodayDTO> getTodayArticles(int startRow, int endRow, int code) throws SQLException {

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", startRow);
		map.put("end", endRow);
		
		List<TodayDTO> boardList = sqlSession.selectList("board.getTodayArticles", map);
		return boardList;
	}
	
	// 공지사항 게시글 업로드 - 노현호
	@Override
	public int insertNotice(NoticeDTO dto) throws SQLException {
		int result = sqlSession.insert("board.insertNotice", dto);
		return result;
	}
	
	// 오늘의 실천 업로드 - 노현호
	@Override
	public int upload(TodayDTO dto) throws SQLException {
		int result = sqlSession.insert("board.upload", dto);
		return result;
	}
	
	// 공지사항 게시글 조회 - 노현호
	@Override
	public NoticeDTO getNoticeArticle(int boardnum) throws SQLException {
		// 조회수 1 증가
		sqlSession.update("board.addReadCountNotice", boardnum);
		// 글 불러오기
		NoticeDTO article = sqlSession.selectOne("board.getNoticeArticle", boardnum); 
		return article;
	}
	
	// 공지사항 글 수정 처리 - 노현호
	@Override
	public int updateNoticeArticle(NoticeDTO dto) throws SQLException {
		// 이미지 업로드 안했을때
		int result = sqlSession.update("board.updateNoticeArticle", dto);
		return result;
	}
	
	// 공지사항 글 수정 처리(이미지 수정O) - 노현호
	@Override
	public int updateNoticeArticleImg(NoticeDTO dto) throws SQLException {
		// 이미지 업로드 했을때
		int result = sqlSession.update("board.updateNoticeArticleImg", dto);
		return result;
	}
	
	 // 이달의 챌린지 게시글 저장 처리 - 이다희 
	@Override
	public void insertChallenge(MonthDTO dto) throws SQLException {
		sqlSession.insert("board.insertChallenge", dto);
	}
	
	// 이달의 챌린지 게시글 목록가져오기 - 이다희 
	@Override
	public List<MonthDTO> getChallengeArticles(int startRow, int endRow, int code) throws SQLException {
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", startRow);
		map.put("end", endRow);
		
		List<MonthDTO> boardList = sqlSession.selectList("board.getChallengeArticles", map);
		
		return boardList;
	}
	
	// 이달의 챌린지 글 한개 가져오기 - 이다희 
	@Override
	public MonthDTO getChallengeArticle(int num) throws SQLException {
		MonthDTO article = sqlSession.selectOne("board.getChallengeArticle", num);
		return article;	 
	}
	
	// 이달의 챌린지 글 수정 1 - 이다희 
	@Override
	public int updateChallengeArticle(MonthDTO dto) throws SQLException {
		// 이미지 업로드 안했을때
		int result = sqlSession.update("board.updateChallengeArticle", dto);
		return result;
	}
	
	// 이달의 챌린지 글 수정 2 - 이다희 
	@Override
	public int updateChallengeArticleImg(MonthDTO dto) throws SQLException {
		// 이미지 업로드 했을때
		int result = sqlSession.update("board.updateChallengeArticleImg", dto);
		return result;
	}
	
	// 비밀번호 일치여부 확인(게시글 삭제용) - 노현호
	@Override
	public int pwCheck(int boardnum, int code, String pw) throws SQLException {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("boardnum", boardnum);
		map.put("pw", pw);
		
		ArrayList<String> board = new ArrayList<String>();
		board.add("Notice");
		board.add("Free");
		board.add("Diary");
		board.add("Challenge");
		board.add("Today");
		board.add("Shop");
		board.add("Event");
		board.add("Tip");
		
		String select = "board.pwCheck";
		select += board.get(code-1);
		
		int pwCheck = sqlSession.selectOne(select, map);
		return pwCheck;
	}
	
	// 게시글 삭제 처리 (게시판 통합) - 노현호
	@Override
	public int deleteArticle(int boardnum, int code) throws SQLException {
		
		ArrayList<String> board = new ArrayList<String>();
		board.add("Notice");
		board.add("Free");
		board.add("Diary");
		board.add("Challenge");
		board.add("Today");
		board.add("Shop");
		board.add("Event");
		board.add("Tip");
		
		String delete = "board.deleteArticle";
		delete += board.get(code-1);
		
		int result = sqlSession.delete(delete, boardnum);
		return result;
	}
	
	
	// 검색 게시글 수 가져오기 -- 삭제 예정(노현호)
	@Override
	public int getSearchArticleCount(String sel, String search) throws SQLException {
		
		HashMap map = new HashMap();
		map.put("sel", sel);
		map.put("search", search);
		
		int result = sqlSession.selectOne("board.countSearch", map);
		
		return result;
	}
	// 검색 된 게시글 목록 가져오기 -- 삭제 예정(노현호)
	@Override
	public List<BoardDTO> getSearchArticles(int start, int end, String sel, String search) throws SQLException {
		
		HashMap map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("sel", sel);
		map.put("search", search);
		
		List<BoardDTO> boardList = sqlSession.selectList("board.getSearchArticles", map);
		
		return boardList;
	}
	
	

}
