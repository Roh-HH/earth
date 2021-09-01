package earth.board.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import earth.board.dto.BoardDTO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	private SqlSessionTemplate sqlSession = null;
	
	@Override
	public int getArticleCount() throws SQLException {
		// 게시판 전체글 개수 가져오기 (test 용 : board table 활용)
		int result = sqlSession.selectOne("board.countAll");
		
		return result;
	}
	// 한 페이지 게시글 목록 가져오기
	@Override
	public List<BoardDTO> getArticles(int start, int end) throws SQLException {
		
		HashMap map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List<BoardDTO> boardList = sqlSession.selectList("board.getArticles", map);
		
		return boardList;
	}
	// 검색 게시글 수 가져오기
	@Override
	public int getSearchArticleCount(String sel, String search) throws SQLException {
		
		HashMap map = new HashMap();
		map.put("sel", sel);
		map.put("search", search);
		
		int result = sqlSession.selectOne("board.countSearch", map);
		
		return result;
	}
	// 검색 된 게시글 목록 가져오기
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
	
	// 게시글 저장 처리
	@Override
	public void insertArticle(BoardDTO dto) throws SQLException {
		// 새글, 답글 구분해서 처리 (DTO에서 필요한 Data 꺼내기 - 꼭 안꺼내도 처리 됨)
		int num = dto.getNum();				// 글번호 (새글 0, 댓글 1이상=원본글의 고유번호) 
		//int ref = dto.getRef();			// 글 그룹		0
		int re_step = dto.getRe_step();		// 정렬 순서	0
		int re_level = dto.getRe_level(); 	// 답글 레벨	0
		
		// 답글 : 같은 그룹상 먼저 작성 된 답글들의 re_step을 1씩 더해서 저장해주고 --> 글 등록
		if(num != 0) {	// 답글 일 때
		sqlSession.update("board.updateRestep", dto);
		dto.setRe_step(re_step + 1);
		dto.setRe_level(re_level + 1);
		}
		
		// 새글/답글 저장 시키기
		sqlSession.insert("board.insertArticle", dto);

	}
	// 게시글 1개 가져오기
	@Override
	public BoardDTO getArticle(int num) throws SQLException {
		BoardDTO article = sqlSession.selectOne("board.getOneArticle", num);
		
		return article;
	}
	// 조회수 1씩 증가시키기
	@Override
	public void readcountUp(int num) throws SQLException {
		sqlSession.update("board.readcountUp", num);
		
	}
	// 게시글 수정하기 위해서 pw 맞는지 체크하기
	@Override
	public int pwCheck(BoardDTO dto) throws SQLException {
		return sqlSession.selectOne("board.pwCheck", dto);
	}
	// 게시글 수정하기
	@Override
	public void updateArticle(BoardDTO dto) throws SQLException {
		sqlSession.update("board.updateArticle", dto);

	}
	// 게시글 삭제하기
	@Override
	public void deleteArticle(BoardDTO dto) throws SQLException {
		sqlSession.delete("board.deleteArticle", dto);
	}

}
