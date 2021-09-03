package earth.board.dao;

import java.sql.SQLException;
import java.util.List;

import earth.board.dto.BoardDTO;
import earth.board.dto.MonthDTO;
import earth.board.dto.NoticeDTO;
import earth.board.dto.TodayDTO;

public interface BoardDAO {

	// CRUD
	
	// 전체 게시글 글 개수 가져오기(모든 테이블 사용 가능) - 노현호
	public int getArticleCount(int code) throws SQLException;
	
	// 공지사항 게시글 목록 가져오기 - 노현호
	public List<NoticeDTO> getNoticeArticles(int startRow, int endRow, int code) throws SQLException;
	// 공지사항 게시글 업로드 - 노현호
	public int insertNotice(NoticeDTO dto) throws SQLException;
	// 공지사항 게시글 조회 - 노현호
	public NoticeDTO getNoticeArticle(int boardnum) throws SQLException;
	// 공지사항 글 수정 처리 - 노현호
	public int updateNoticeArticle(NoticeDTO dto) throws SQLException;
	// 공지사항 글 수정 처리(이미지 수정O) - 노현호
	public int updateNoticeArticleImg(NoticeDTO dto) throws SQLException;
	
	// 오늘의실천 게시글 목록 가져오기 - 노현호
	public List<TodayDTO> getTodayArticles(int startRow, int endRow, int code) throws SQLException;
	// 오늘의 실천 업로드 - 노현호
	public int upload(TodayDTO dto) throws SQLException;
	
	
	// 검색 된 게시글 개수 가져오기
	public int getSearchArticleCount(String sel, String search) throws SQLException;
	// 검색 된 게시글 목록 가져오기
	public List<BoardDTO> getSearchArticles(int start, int end, String sel, String search) throws SQLException;
	
	// 이달의챌린지 게시글 저장 - 이다희 
	public void insertChallenge(MonthDTO dto) throws SQLException;
	// 이달의 챌린지 게시글목록 가져오기 - 이다희 
	public List<MonthDTO> getChallengeArticles(int startRow, int endRow, int code) throws SQLException;
	// 이달의 챌린지 게시글 하나 가져오기 - 이다희 
	public MonthDTO getChallengeArticle(int num) throws SQLException;
	// 공지사항 글 수정 처리
	public int updateChallengeArticle(MonthDTO dto) throws SQLException;
	// 공지사항 글 수정 처리(이미지 수정O)
	public int updateChallengeArticleImg(MonthDTO dto) throws SQLException;

	
	// 비밀번호 일치여부 확인(게시글 삭제용) - 노현호
	public int pwCheck(int boardnum, int code, String pw) throws SQLException;
	// 게시글 삭제 처리 (게시판 통합) - 노현호
	public int deleteArticle(int boardnum, int code) throws SQLException;
	
}
