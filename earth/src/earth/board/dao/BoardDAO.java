package earth.board.dao;

import java.sql.SQLException;
import java.util.List;

import earth.board.dto.BoardDTO;
import earth.board.dto.TodayDTO;

public interface BoardDAO {

	// CRUD
	
	// 전체 게시글 글 개수 가져오기 - 노현호
	public int getArticleCount(int code) throws SQLException;
	
	// 해당 페이지마다 게시글 목록 가져오기 (startRow, endRow) - 노현호
	public List<TodayDTO> getArticles(int startRow, int endRow, int code) throws SQLException;
	
	// 오늘의 실천 업로드 - 노현호
	public int upload(TodayDTO dto) throws SQLException;
	
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
