package earth.board.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import earth.board.dto.BracketsDTO;
import earth.board.dto.EventDTO;
import earth.board.dto.FreeCommentDTO;
import earth.board.dto.FreeDTO;
import earth.board.dto.MonthDTO;
import earth.board.dto.NoticeDTO;
import earth.board.dto.ShopDTO;
import earth.board.dto.TipDTO;
import earth.board.dto.TodayDTO;

public interface BoardService {
	
	// 공용 및 게시판 번호순 1차 정렬 CRUD순 2차 정렬
	
	// 0. 공용
		// 게시판의 게시글 목록 가져오기 - 노현호
		public Map<String, Object> getArticleList(String pageNum, int code) throws SQLException;
		// 게시판 검색한 게시글 목록 가져오기 - 김하영
		public Map<String, Object> getArticleSearch(String pageNum, String sel, String search, int code) throws SQLException;
		// 게시판 글 1개의 댓글 목록 가져오기 - 노현호
		public Map<String, Object> getCommentList(int commentPageNum, int boardnum, int code) throws SQLException;
		// 비밀번호 일치여부 확인(암호화) - 노현호
		public String getPw(int boardnum, int code) throws SQLException;
		// 이미지 삭제를 위한 ctt 가져오기 - 노현호
		public String getCtt(int boardnum, int code) throws SQLException;
		// 이미지 삭제를 위한 img 가져오기 - 노현호
		public String getImg(int boardnum, int code) throws SQLException;
		// 게시글 삭제 처리 - 노현호
		public int deleteArticle(int boardnum, int code) throws SQLException;

	// 1. 공지사항
		// 공지사항 게시글 업로드 - 노현호
		public int insertNotice(NoticeDTO dto) throws SQLException;
		// 공지사항 글 1개 가져오기 - 노현호
		public NoticeDTO getNoticeArticle(int boardnum)	throws SQLException;
		// 공지사항 글 수정 처리 - 노현호
		public int updateNoticeArticle(NoticeDTO dto) throws SQLException;
	
	// 2. 자유게시판
		// 자유게시판 게시글 업로드 - 노현호
		public int insertFree(FreeDTO dto) throws SQLException;	
		// 자유게시판 글 1개 가져오기 - 노현호
		public FreeDTO getFreeArticle(int boardnum) throws SQLException;
		// 자유게시판 말머리(brackets categList) 불러오기 - 노현호
		public List<BracketsDTO> getBrackets() throws SQLException;
		// 자유게시판 글 수정처리 - 노현호
		public int updateFreeArticle(FreeDTO dto) throws SQLException;
		
	// 3. 환경일기
		
	// 4. 이달의 챌린지
		
	// 5. 오늘의 실천
		// 오늘의 실천 업로드 - 노현호
		public int upload(TodayDTO dto) throws SQLException;
	
	// 6. 상점소개
		//상점소개 게시글 업로드 - 김하영 
		public int insertShop(ShopDTO dto) throws SQLException;
		//상점소개 글 1개 가져오기- 김하영
		public ShopDTO getShopArticle(int boardnum) throws SQLException;
		//상점소개 글 수정처리 - 김하영
		public int updateShopArticle(ShopDTO dto) throws SQLException;
		//상점소개 글 수정처리(이미지포함) - 김하영 
		public int updateShopArticleImg(ShopDTO dto) throws SQLException;
		
	// 7. 행사
		//행사일정 게시글 업로드 - 김하영
		public int insertEvent(EventDTO dto) throws SQLException;
		//행사일정 글 1개 가져오기 - 김하영
		public EventDTO getEventArticle(int boardnum) throws SQLException;
		//행사일정 글 수정처리 - 김하영
		public int updateEventArticle(EventDTO dto) throws SQLException;
		//행사일정 글 수정처리(이미지포함) - 김하영
		public int updateEventArticleImg(EventDTO dto) throws SQLException;
		
	// 8. 꿀팁
		//꿀팁 게시글 등록 - 김하영
		public int insertTip(TipDTO dto) throws SQLException;
		//꿀팁 글 1개 가져오기 - 김하영
		public TipDTO getTipArticle(int boardnum) throws SQLException;
		//꿀팁 글 수정처리 - 김하영 
		public int updateTipArticle(TipDTO dto) throws SQLException;
		//꿀팁 글 수정처리(이미지포함) - 김하영
		public int updateTipArticleImg(TipDTO dto) throws SQLException;
		
	// 9. 자유게시판 댓글
		// 자유게시판 댓글 업로드 - 노현호
		public int uploadFreeComment(FreeCommentDTO dto) throws SQLException;
	
	// 10. 환경일기 댓글
		
	// 11. 이달의 챌린지 댓글
		

}
