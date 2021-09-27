package earth.board.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import earth.board.dto.BracketsDTO;
import earth.board.dto.DiaryDTO;
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
    
		// ============= 닉네임 가져오기 다희 추가 
		//search 로 아이디 가져오기 
		public String getBoardid(String search) throws SQLException;
		//writer로 댓글 닉네임 가져오기
		public String getNicknamereply(String writer) throws SQLException;
		//id로 게시글, 리스트 닉네임 가져오기 
		public String getNickname(String id) throws SQLException;
    
		// ============= 뱃지이미지 가져오기 다희 추가 
		// 뱃지 이미지 (게시글 리스트)
		public String getBadgeimg(String id) throws SQLException;
		// 뱃지 이미지 가져오기 (댓글 리스트)
		public String getBadgeimgreply(String writer) throws SQLException;

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
		// 환경일기 게시글 업로드  - 이다희
		public int insertDiary(DiaryDTO dto) throws SQLException;
		// 환경일기 글 1개 가져오기 - 이다희
		public DiaryDTO getDiaryArticle(int boardnum)	throws SQLException;
		// 환경일기 검색된 게시글 목록 가져오기  - 이다희
		public Map<String, Object> getDiaryArticleSearch(String pageNum, String sel, String search, int code) throws SQLException;
		// 환경일기 좋아요 아이디 체크  - 이다희
		public int recidCheck(int boardnum, String recid) throws SQLException;
		// 환경일기 글 수정처리 - 이다희
		public int updateDiaryArticle(DiaryDTO dto) throws SQLException;
		// 환경일기 글 수정처리(이미지포함) - 이다희
		public int updateDiaryArticleImg(DiaryDTO dto) throws SQLException;
		// 환경일기 좋아요 - 이다희
		public void likeUp(int boardnum, String recid) throws SQLException;
		// 환경일기 좋아요 취소 - 이다희
		public void likeCancel(int boardnum, String recid) throws SQLException;
		
	// 4. 이달의 챌린지
		// 이달의 챌린지 게시글 업로드  - 이다희
		public void insertChallenge(MonthDTO dto) throws SQLException;
		// 이달의 챌린지 참여하기 - 이다희
		public int insertChJoin(int boardnum, String id) throws SQLException;
		// 이달의 챌린지 최신글 1개 가져오기 (추가)
		public int getChallenge() throws SQLException;
		// 이달의 챌린지 글 1개 가져오기 - 이다희
		public MonthDTO getChallengeArticle(int boardnum) throws SQLException;
		// 이달의 챌린지 조인 아이디 체크 - 이다희
		public int joinidCheck(int boardnum, String id) throws SQLException;		
		// 이달의 챌린지 dateCheck 마감데이트 확인 - 이다희 
		public int dateCheck(int boardnum) throws SQLException;
		//이달의 챌린지 글 수정처리 - 이다희
		public int updateChallengeArticle(MonthDTO dto) throws SQLException;
		// 이달의 챌린지 글 수정처리(이미지포함) - 이다희
		public int updateChallengeArticleImg(MonthDTO dto) throws SQLException;		
		
	// 5. 오늘의 실천
		// 오늘의 실천 업로드 - 노현호
		public int upload(TodayDTO dto) throws SQLException;
		// 오늘의 실천 글 1개 가져오기 - 노현호
		public TodayDTO getTodayArticle(int boardnum) throws SQLException;
	
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
		// 자유게시판 댓글 하나 불러오기 - 노현호
		public FreeCommentDTO getFreeComment(int commentnum) throws SQLException;
	
	// 10. 환경일기 댓글
		// 환경일기 댓글 업로드 - 이다희
		public void insertDiaryReply(int boardnum, String ctt, String writer, String receiver) throws SQLException;
		// 환경일기 댓글 가져오기 - 이다희
		public Map<String, Object> getDiaryReplyList(int boardnum, String pageN) throws SQLException;
		
	// 11. 이달의 챌린지 댓글
		// 이달의 챌린지 댓글 업로드 - 이다희
		public void insertChReply(int boardnum, String ctt, String writer) throws SQLException;
		// 이달의 챌린지 댓글 가져오기 - 이다희
		public Map<String, Object> getChReplyList(int boardnum, String pageN) throws SQLException;
		// 이달의 챌린지 및 환경일기 댓글삭제 - 이다희
		public int replydelete (int commentnum, String categ) throws SQLException;

}
