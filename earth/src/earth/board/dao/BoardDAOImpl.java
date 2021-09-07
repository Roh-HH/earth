package earth.board.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import earth.board.dto.BoardDTO;
import earth.board.dto.BracketsDTO;
import earth.board.dto.FreeCommentDTO;
import earth.board.dto.FreeDTO;
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
	
	// 자유게시판 게시글 목록 가져오기 - 노현호
	@Override
	public List<FreeDTO> getFreeArticles(int startRow, int endRow, int code) throws SQLException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", startRow);
		map.put("end", endRow);
		
		List<FreeDTO> boardList = sqlSession.selectList("board.getFreeArticles", map);
		List<String> categList = sqlSession.selectList("board.getBrackets");

		HashMap<Integer, String> categMap = new HashMap<Integer, String>();
		int key = 1;
		for(String categ : categList) {
			categMap.put(key, categ);
			key++;
		}
		
		for(int i = 0; i < boardList.size(); i++) {
			boardList.get(i).setCategStr(categMap.get(boardList.get(i).getCateg()));
		}
		
		for(int i = 0; i < boardList.size(); i++) {
			String refCount = sqlSession.selectOne("board.getRefCountFree", boardList.get(i).getBoardnum());
			if(refCount == null) {
				refCount = "0";
			}
			boardList.get(i).setRefCount(Integer.parseInt(refCount));
		}
		
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
	
	// 글머리 가져오기(자유게시판용) - 노현호
	@Override
	public List<BracketsDTO> getBrackets() throws SQLException {
		List<BracketsDTO> Brackets = sqlSession.selectList("board.getBracketsDTO");
		return Brackets;
	}
	
	// 자유게시판 게시글 업로드 - 노현호
	@Override
	public int insertFree(FreeDTO dto) throws SQLException {
		int result = sqlSession.insert("board.insertFree", dto);
		return result;
	}
	
	// 자유게시판 댓글 업로드 - 노현호
	@Override
	public int uploadFreeComment(FreeCommentDTO dto) throws SQLException {
		// 조회수 1 감소
		sqlSession.update("board.subReadCountFree", dto.getFreenum());
		int result = sqlSession.insert("board.uploadFreeComment", dto);
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
	
	// 자유게시판 게시글 조회 - 노현호
	@Override
	public FreeDTO getFreeArticle(int boardnum) throws SQLException {
		// 조회수 1 증가
		sqlSession.update("board.addReadCountFree", boardnum);
		// 글 불러오기
		FreeDTO article = sqlSession.selectOne("board.getFreeArticle", boardnum); 
		return article;
	}
	
	// 자유게시판 댓글 조회 - 노현호
	@Override
	public List<FreeCommentDTO> getFreeComment(int boardnum) throws SQLException {
		List<FreeCommentDTO> comment = sqlSession.selectList("board.getFreeComment", boardnum);
		return comment;
	}
	
	// 자유게시판 댓글 ref 가져오기(조회) - 노현호
	@Override
	public String getRef(int boardnum, int code) throws SQLException {
		
		ArrayList<String> board = new ArrayList<String>();
		board.add("Notice");
		board.add("Free");
		board.add("Diary");
		board.add("Challenge");
		board.add("Today");
		board.add("Shop");
		board.add("Event");
		board.add("Tip");
		
		String select = "board.getRef";
		select += board.get(code-1);
		
		String ref = sqlSession.selectOne(select, boardnum);
		if(ref == null)
			ref = "0";
		return ref;
	}
	
	@Override
	public int updateFreeArticle(FreeDTO dto) throws SQLException {
		int result = sqlSession.update("board.updateFreeArticle", dto);
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
	
	// 비밀번호 일치여부 확인(암호화) - 노현호
	@Override
	public String getPw(int boardnum, int code) throws SQLException {
		
		ArrayList<String> board = new ArrayList<String>();
		board.add("Notice");
		board.add("Free");
		board.add("Diary");
		board.add("Challenge");
		board.add("Today");
		board.add("Shop");
		board.add("Event");
		board.add("Tip");
		
		String select = "board.getPw";
		select += board.get(code-1);
		
		String hashedPassword = sqlSession.selectOne(select, boardnum);
		return hashedPassword;
	}
	
	@Override
	public String getCtt(int boardnum, int code) throws SQLException {
		
		ArrayList<String> board = new ArrayList<String>();
		board.add("Notice");
		board.add("Free");
		board.add("Diary");
		board.add("Challenge");
		board.add("Today");
		board.add("Shop");
		board.add("Event");
		board.add("Tip");
		board.add("FreeComment");
		
		String select = "board.getCtt";
		select += board.get(code-1);
		
		String ctt = sqlSession.selectOne(select, boardnum);
		return ctt;
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
		board.add("FreeComment");
		
		String delete = "board.deleteArticle";
		delete += board.get(code-1);
		
		int result = sqlSession.delete(delete, boardnum);
		return result;
	}

}
