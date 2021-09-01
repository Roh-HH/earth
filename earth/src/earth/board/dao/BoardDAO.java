package earth.board.dao;

import java.sql.SQLException;
import java.util.List;

import earth.board.dto.BoardDTO;

public interface BoardDAO {

	// CRUD
	
	// 전체 게시글 글 개수 가져오기
	public int getArticleCount() throws SQLException;	
	// 해당 페이지마다 게시글 목록 가져오기 (startRow, endRow)
	public List<BoardDTO> getArticles(int start, int end) throws SQLException;
	
	// 검색 된 게시글 개수 가져오기
	public int getSearchArticleCount(String sel, String search) throws SQLException;
	// 검색 된 게시글 목록 가져오기
	public List<BoardDTO> getSearchArticles(int start, int end, String sel, String search) throws SQLException;
	
	// 게시글 저장
	public void insertArticle(BoardDTO dto) throws SQLException;
	// 게시글 1개 정보 가져오기
	public BoardDTO getArticle(int num) throws SQLException;
	// 조회수 1씩 올리기
	public void readcountUp(int num) throws SQLException;
	
	// 게시글 수정 위해서 pw 맞는지 체크하기
	public int pwCheck(BoardDTO dto) throws SQLException;
	// 게시글 수정하기 (modifyPro)
	public void updateArticle(BoardDTO dto) throws SQLException;
	// 게시글 삭제하기 (deletePro)
	public void deleteArticle(BoardDTO dto) throws SQLException;
	
}
