package earth.board.service;

import java.sql.SQLException;
import java.util.Map;

import earth.board.dto.BoardDTO;

// 선언
public interface BoardService {
	
	// CRUD (Create, Read, Update, Delete)
	

	// 게시판 list 페이지 목록 가져오기 (list)
	public Map<String, Object> getArticleList(String pageNum) throws SQLException;
	// 게시판 검색한 list 페이지 목록 가져오기 (list 검색)
	public Map<String, Object> getArticleSearch(String pageNum, String sel, String search) throws SQLException;
	
	// 게시판 글 1개 가져오기 (content)
	public BoardDTO getArticle(int num) throws SQLException;
	// 게시글 등록 처리 (writePro)
	public void insertArticle(BoardDTO dto) throws SQLException;
	
	// 게시글 수정폼 처리 (modifyForm)
	public BoardDTO getUpdateArticle(int num) throws SQLException;
	// 게시글 수정프로 (modifyPro)
	public int updateArticle(BoardDTO dto) throws SQLException;
	// 게시글 삭제 처리 (deletePro)
	public int deleteArticle(BoardDTO dto) throws SQLException;
}
