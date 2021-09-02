package earth.board.service;

import java.sql.SQLException;
import java.util.Map;

import earth.board.dto.BoardDTO;
import earth.board.dto.NoticeDTO;
import earth.board.dto.TodayDTO;

// 선언
public interface BoardService {
	
	// CRUD (Create, Read, Update, Delete)
	
	// 게시판 list 페이지 목록 가져오기 (list) - 노현호
	public Map<String, Object> getArticleList(String pageNum, int code) throws SQLException;
	// 게시판 검색한 list 페이지 목록 가져오기 (list 검색) - 노현호
	public Map<String, Object> getArticleSearch(String pageNum, String sel, String search, int code) throws SQLException;
	
	// 공지사항 게시글 업로드 - 노현호
	public int insertNotice(NoticeDTO dto) throws SQLException;
	// 오늘의 실천 업로드 - 노현호
	public int upload(TodayDTO dto) throws SQLException;
	
	// 게시판 글 1개 가져오기 (조회) - 노현호
	public BoardDTO getArticle(int num) throws SQLException;
	
	// 공지사항 글 1개 가져오기 (조회) - 노현호
	public NoticeDTO getNoticeArticle(int boardnum)	throws SQLException;
	// 공지사항 글 수정 처리(이미지 수정X)
	public int updateNoticeArticle(NoticeDTO dto) throws SQLException;
	// 공지사항 글 수정 처리(이미지 수정O)
	public int updateNoticeArticleImg(NoticeDTO dto) throws SQLException;
	
	// 게시글 등록 처리 (writePro)
	public void insertArticle(BoardDTO dto) throws SQLException;
	
	// 게시글 수정폼 처리 (modifyForm)
	public BoardDTO getUpdateArticle(int num) throws SQLException;
	// 게시글 수정프로 (modifyPro)
	public int updateArticle(BoardDTO dto) throws SQLException;
	// 게시글 삭제 처리 (deletePro)
	public int deleteArticle(BoardDTO dto) throws SQLException;
}
