package earth.board.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	private SqlSessionTemplate sqlSession = null;
	
	// 공용 및 게시판 번호순 1차 정렬 CRUD순 2차 정렬

	// 0. 공용
        // 닉네임 관련 추가 =================== 이다희
        //댓글 닉네임 가져오기
        @Override
        public String getNicknamereply(String writer) throws SQLException {	
            String nicknamreply = sqlSession.selectOne("board.getNicknamereply", writer);	
            return nicknamreply;
        }
        //컨텐츠 닉네임 가져오기 
        @Override
        public String getNickname(String id) throws SQLException {	
            String nickname = sqlSession.selectOne("board.getNickname", id);	
            return nickname;
        }
        //search 아이디 가져오기 
        @Override
        public String getBoardid(String search) throws SQLException {	
            String boardid = sqlSession.selectOne("board.getBoardid", search);
            System.out.println("boardid " + boardid);	
            return boardid;
        }
        
        // 뱃지이미지 추가 =================== 이다희
        @Override
        public String getBadgeimg(String id) throws SQLException {
            String badgeimg = sqlSession.selectOne("board.getBadgeimg", id);
            return badgeimg;
        }

        @Override
        public String getBadgeimgreply(String writer) throws SQLException {
            String badgeimg = sqlSession.selectOne("board.getBadgeimgreply", writer);
            return badgeimg;
        }
    
    
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
	
		// 비밀번호 일치여부 확인(암호화) - 노현호
		@Override
		public String getPw(int boardnum, int code) throws SQLException {
			
			// Today는 비밀번호 사용하지 않음
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
		
		// 이미지 삭제를 위한 ctt 가져오기 - 노현호
		@Override
		public String getCtt(int boardnum, int code) throws SQLException {
			
			// 넘어오는 code : 1-Notice, 2-Free, 3-Diary, 7-Event, 8-Tip
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
			board.add("DiaryComment");
			board.add("ChallengeComment");
			
			String select = "board.getCtt";
			select += board.get(code-1);
			
			String ctt = sqlSession.selectOne(select, boardnum);
			return ctt;
		}
		
		// 이미지 삭제를 위한 img 가져오기 - 노현호
		@Override
		public String getImg(int boardnum, int code) throws SQLException {
			
			// 넘어오는 code : 3-Diary, 6-Shop, 7-Event, 8-Tip
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
			board.add("DiaryComment");
			board.add("ChallengeComment");
			
			String select = "board.getImg";
			select += board.get(code-1);
			
			String img = null;
			if(code == 5) {
				return img;
			}else {
				img = sqlSession.selectOne(select, boardnum);			
			}
			
			return img;
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
			board.add("DiaryComment");
			board.add("ChallengeComment");
			
			String delete = "board.deleteArticle";
			delete += board.get(code-1);
			
			int result = sqlSession.delete(delete, boardnum);
			return result;
		}
		
		
	// 1. 공지사항
		// 공지사항 게시글 업로드 - 노현호	
		@Override
		public int insertNotice(NoticeDTO dto) throws SQLException {
			int result = sqlSession.insert("board.insertNotice", dto);
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
		
		// 공지사항 글 1개 가져오기 - 노현호
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
		
		
	// 2. 자유게시판
		// 자유게시판 게시글 업로드 - 노현호
		@Override
		public int insertFree(FreeDTO dto) throws SQLException {
			int result = sqlSession.insert("board.insertFree", dto);
			return result;
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
			
			// 목록에서 댓글 수 표시
			for(int i = 0; i < boardList.size(); i++) {
				String refCount = sqlSession.selectOne("board.getRefCountFree", boardList.get(i).getBoardnum());
				if(refCount == null) {
					refCount = "0";
				}
				boardList.get(i).setRefCount(Integer.parseInt(refCount));
			}
			
			return boardList;
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
		
		// 자유게시판 말머리(brackets categList) 불러오기 - 노현호
		@Override
		public List<BracketsDTO> getBrackets() throws SQLException {
			List<BracketsDTO> Brackets = sqlSession.selectList("board.getBracketsDTO");
			return Brackets;
		}
		
		// 자유게시판 글 수정처리 - 노현호
		@Override
		public int updateFreeArticle(FreeDTO dto) throws SQLException {
			int result = sqlSession.update("board.updateFreeArticle", dto);
			return result;
		}
	
	
	// 3. 환경일기
		// 환경일기 게시글 업로드  - 이다희
		@Override
		public int insertDiary(DiaryDTO dto) throws SQLException {
			int result = sqlSession.insert("board.insertDiary", dto);
			
			String id = dto.getId();
			if(result == 1) {	 
				sqlSession.update("user.updateBoardPoint", id);
				System.out.println("result 1 일때, 글쓰기 boardcount에서 포인트 5 넣어주기 - boardcount = 0일때만 포인트 업  ");
			}
			
			return result;
		}
		
		// 환경일기 게시글 목록 가져오기 - 이다희
		@Override
		public List<DiaryDTO> getDiaryArticles(int startRow, int endRow, int code) throws SQLException {
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("start", startRow);
			map.put("end", endRow);
			
			List<DiaryDTO> boardList = sqlSession.selectList("board.getDiaryArticles", map);
			
			return boardList;
		}
		
		// 환경일기 글 1개 가져오기 - 이다희
		@Override
		public DiaryDTO getDiaryArticle(int boardnum) throws SQLException {
			// 조회수 1 증가
			sqlSession.update("board.addReadCountDairy", boardnum);
			// 글 불러오기
			DiaryDTO article = sqlSession.selectOne("board.getDiaryArticle", boardnum); 
			return article;
		}
		
		// 검색된 게시글 수 가져오기 - 이다희
		@Override
		public int getSearchDiaryArticleCount(String sel, String search) throws SQLException {
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("sel", sel);
			map.put("search", search);
			
			int result = sqlSession.selectOne("board.countDiarySearch", map);
			return result;
		}
		
		// 환경일기 검색된 게시글 목록 가져오기  - 이다희
		@Override
		public List<DiaryDTO> getSearchDiaryArticles(int start, int end, String sel, String search) throws SQLException {
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("start", start);
			map.put("end", end);
			map.put("sel", sel);
			map.put("search", search);
			
			List<DiaryDTO> boardList = sqlSession.selectList("board.getSearchDiaryArticles", map);
			return boardList;
		}
		
		// 환경일기 좋아요 아이디 체크  - 이다희
		@Override
		public int recidCheck(int boardnum, String recid) throws SQLException {
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("boardnum", boardnum);
			map.put("recid", recid);
			 
			int idCheck = sqlSession.selectOne("board.recidCheck", map);
			System.out.println("idCheck dao imp" + idCheck);
			return idCheck;
		}
		
		// 환경일기 글 수정처리 - 이다희
		@Override
		public int updateDiaryArticle(DiaryDTO dto) throws SQLException {
			int result = sqlSession.update("board.updateDiaryArticle", dto);
			return result;
		}
		
		// 환경일기 글 수정처리(이미지포함) - 이다희
		@Override
		public int updateDiaryArticleImg(DiaryDTO dto) throws SQLException {
			int result = sqlSession.update("board.updateDiaryArticleImg", dto);
			return result;
		}
		
		// 환경일기 좋아요 - 이다희
		@Override
		public void likeUp(int boardnum, String recid) throws SQLException {
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("boardnum", boardnum);
			map.put("recid", recid);
			 
			sqlSession.insert("board.likeup", map);	
			sqlSession.update("board.likeadd", boardnum);
			sqlSession.update("board.minusReadCountDairy", boardnum);
		}
		
		// 환경일기 좋아요 취소 - 이다희
		@Override
		public void likeCancel(int boardnum, String recid) throws SQLException {
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("boardnum", boardnum);
			map.put("recid", recid);
			 
			sqlSession.delete("board.likeCancel", map);		
			sqlSession.update("board.likedown", boardnum);
			sqlSession.update("board.minusReadCountDairy", boardnum);		
		}
	
		
	// 4. 이달의 챌린지
		// 이달의 챌린지 게시글 업로드  - 이다희
		@Override
		public void insertChallenge(MonthDTO dto) throws SQLException {
			sqlSession.insert("board.insertChallenge", dto);
		}
		
		// 이달의 챌린지 참여하기 - 이다희
		@Override
		public int insertChJoin(int boardnum, String id) throws SQLException {
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("boardnum", boardnum);
			map.put("id", id);
			 
			int result = sqlSession.insert("board.insertChJoin", map);	
			return result;
		}
		
		// 이달의 챌린지 게시글 목록 가져오기 - 이다희 
		@Override
		public List<MonthDTO> getChallengeArticles(int startRow, int endRow, int code) throws SQLException {
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("start", startRow);
			map.put("end", endRow);
				
			List<MonthDTO> boardList = sqlSession.selectList("board.getChallengeArticles", map);		
			return boardList;
		}
	
		//최신글 하나 가져오기 (추가)
		@Override
		public int getChallenge() throws SQLException {
			int boardnum = sqlSession.selectOne("board.getChallenge");
			return boardnum;
		}
		
		
		// 이달의 챌린지 글 1개 가져오기 - 이다희
		@Override
		public MonthDTO getChallengeArticle(int boardnum) throws SQLException {
			MonthDTO article = sqlSession.selectOne("board.getChallengeArticle", boardnum);
			return article;	 
		}
		
		// 이달의 챌린지 조인 아이디 체크 - 이다희
		@Override
		public int joinidCheck(int boardnum, String id) throws SQLException {
			 
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("boardnum", boardnum);
			map.put("id", id);
			
			int idCheck = sqlSession.selectOne("board.idCheck", map);
			return idCheck;
		}
		
		// 이달의 챌린지 마감일 확인 - 이다희
		@Override
		public int dateCheck(int boardnum) throws SQLException {
			int dateCheck = sqlSession.selectOne("board.dateCheck", boardnum); 
			return dateCheck;
		}
		
		//이달의 챌린지 글 수정처리 - 이다희
		@Override
		public int updateChallengeArticle(MonthDTO dto) throws SQLException {
			int result = sqlSession.update("board.updateChallengeArticle", dto);
			return result;
		}
		
		// 이달의 챌린지 글 수정처리(이미지포함) - 이다희
		@Override
		public int updateChallengeArticleImg(MonthDTO dto) throws SQLException {
			int result = sqlSession.update("board.updateChallengeArticleImg", dto);
			return result;
		}
		
		// 이달의 챌린지 참여인원 올리기 - 이다희
		@Override
		public void joinCountUp(int boardnum) throws SQLException {
			sqlSession.update("board.joinCountUp", boardnum);	
		}
		
	
	// 5. 오늘의 실천
		// 오늘의 실천 업로드 - 노현호
		@Override
		public int upload(TodayDTO dto) throws SQLException {
			int result = sqlSession.insert("board.upload", dto);
			return result;
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
	
		// 오늘의 실천 글 1개 가져오기 - 노현호
		@Override
		public TodayDTO getTodayArticle(int boardnum) throws SQLException {
			TodayDTO article = sqlSession.selectOne("board.getTodayArticle", boardnum);
			return article;
		}
		
		
	// 6. 상점소개
		// 상점소개 게시물 업로드 - 김하영
		@Override
		public int insertShop(ShopDTO dto) throws SQLException {
			int result = sqlSession.insert("board.insertShop", dto);
			return result;
		}
		
		// 상점소개 글 목록 가져오기  - 김하영
		@Override
		public List<ShopDTO> getShopArticles(int startRow, int endRow, int code) throws SQLException {
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("start", startRow);
			map.put("end", endRow);
			
			List<ShopDTO> boardList = sqlSession.selectList("board.getShopArticles", map);
			return boardList;
		} 
		
		// 검색된 상점소개 글 목록 가져오기 - 김하영
		@Override
		public List<ShopDTO> getShopSearch(int startRow, int endRow, String sel, String search, int code) throws SQLException{
				
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("start", startRow);
			map.put("end", endRow);
			map.put("sel", sel);
			map.put("search", search);
				
			List<ShopDTO> boardList = sqlSession.selectList("board.getShopSearch", map);
			return boardList;
		}

		// 검색된 상점소개 글 수 가져오기 - 김하영
		@Override
		public int getShopCount(String sel, String search) throws SQLException {
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("sel", sel);
			map.put("search", search);
				
			int result = sqlSession.selectOne("board.shopCount", map);
			return result;
		}
 
		// 상점소개 글 1개 가져오기 - 김하영
		@Override
		public ShopDTO getShopArticle(int boardnum) throws SQLException {
			sqlSession.update("board.addReadCountShop", boardnum);

			ShopDTO article = sqlSession.selectOne("board.getShopArticle", boardnum);
			return article;
		}

		// 상점소개 글 수정처리 - 김하영
		@Override
		public int updateShopArticle(ShopDTO dto) throws SQLException {
			int result = sqlSession.update("board.updateShopArticle", dto);
			return result;
		}

		// 상점소개 글 수정처리(이미지포함) - 김하영 
		@Override
		public int updateShopArticleImg(ShopDTO dto) throws SQLException {
			int result = sqlSession.update("board.updateShopArticleImg", dto);
			return result;
		}
		

	// 7. 행사
		// 행사일정 게시글 업로드 - 김하영
		@Override
		public int insertEvent(EventDTO dto) throws SQLException {
			int result = sqlSession.insert("board.insertEvent", dto);
			return result;
		}
		
		// 행사일정 글 목록 가져오기 - 김하영 
		@Override
		public List<EventDTO> getEventArticles(int startRow, int endRow, int code) throws SQLException {
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("start", startRow);
			map.put("end", endRow);
			
			List<EventDTO> boardList = sqlSession.selectList("board.getEventArticles", map);
			return boardList;
		}
		
		// 검색된 행사일정 글 목록 가져오기 - 김하영
		@Override
		public List<EventDTO> getEventSearch(int startRow, int endRow, String sel, String search,int code )throws SQLException{
					
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("start", startRow);
			map.put("end", endRow);
			map.put("sel", sel);
			map.put("search", search);
					
			List<EventDTO> boardList = sqlSession.selectList("board.getEventSearch", map);
			return boardList;
		}
		
		// 검색된 행사일정 글 수 가져오기 - 김하영
		@Override
		public int getEventCount(String sel, String search) throws SQLException {
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("sel", sel);
			map.put("search", search);
				
			int result = sqlSession.selectOne("board.eventCount", map);
			return result;
		}
		
		// *행사일정 글 1개 가져오기 - 김하영 
		@Override
		public EventDTO getEventArticle(int boardnum) throws SQLException {
			// 조회수 1 증가
			sqlSession.update("board.addReadCountEvent", boardnum);
			// 글 불러오기
			EventDTO article = sqlSession.selectOne("board.getEventArticle", boardnum); 
			return article;
		}
		
		// 행사일정 글 수정처리 - 김하영 
		@Override
		public int updateEventArticle(EventDTO dto) throws SQLException {
			int result = sqlSession.update("board.updateEventArticle", dto);
			return result;
		}
		
		// 행사일정 글 수정처리(이미지포함) - 김하영 
		@Override
		public int updateEventArticleImg(EventDTO dto) throws SQLException { 
			int result = sqlSession.update("board.updateEventArticleImg", dto);
			return result;
		}
		//행사일정 이전글 -김하영
		@Override
		public EventDTO prevBoard(int boardnum) throws SQLException {
			EventDTO prev = sqlSession.selectOne("board.prevBoard", boardnum); 

			return prev;
		}
		//행사일정 다음글-김하영
		@Override
		public EventDTO nextBoard(int boardnum) throws SQLException {
			EventDTO next = sqlSession.selectOne("board.nextBoard", boardnum); 

			return next;
		}
		
		
	// 8. 꿀팁
		// 꿀팁 게시글 업로드 - 김하영
		@Override
		public int insertTip(TipDTO dto) throws SQLException {
			int result = sqlSession.insert("board.insertTip", dto);
			return result;
		}
		
		// 꿀팁 글 목록 가져오기 - 김하영 
		@Override
		public List<TipDTO> getTipArticles(int startRow, int endRow, int code) throws SQLException {
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("start", startRow);
			map.put("end", endRow);
			
			List<TipDTO> boardList = sqlSession.selectList("board.getTipArticles", map);
			return boardList;
		}
		
		// 검색된 꿀팁 글 목록 가져오기 - 김하영
		@Override
		public List<TipDTO> getTipSearch(int startRow, int endRow, String sel, String search, int code) throws SQLException{
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("start", startRow);
			map.put("end", endRow);
			map.put("sel", sel);
			map.put("search", search);
					
			List<TipDTO> boardList = sqlSession.selectList("board.getTipSearch", map);
			return boardList;
		}
		
		// 검색된 꿀팁 글 수 가져오기 - 김하영
		@Override
		public int getTipCount(String sel, String search) throws SQLException {
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("sel", sel);
			map.put("search", search);
				
			int result = sqlSession.selectOne("board.tipCount", map);
			return result;
		}
		
		// *꿀팁 글 1개 가져오기 - 김하영  
		@Override
		public TipDTO getTipArticle(int boardnum) throws SQLException {
			// 조회수 1 증가
			sqlSession.update("board.addReadCountTip", boardnum);
			// 글 불러오기
			TipDTO article = sqlSession.selectOne("board.getTipArticle", boardnum); 
			return article;
		}
		
		// 꿀팁 글 수정처리 - 김하영 
		@Override
		public int updateTipArticle(TipDTO dto) throws SQLException {
			int result = sqlSession.update("board.updateTipArticle", dto);
			return result;
		}
		
		// 꿀팁 글 수정처리(이미지포함) - 김하영 
		@Override
		public int updateTipArticleImg(TipDTO dto) throws SQLException {
			int result = sqlSession.update("board.updateTipArticleImg", dto);
			return result;
		}
		
	
	// 9. 자유게시판 댓글
		// 자유게시판 댓글 업로드 - 노현호
		@Override
		public int uploadFreeComment(FreeCommentDTO dto) throws SQLException {
			// 조회수 1 감소
			sqlSession.update("board.subReadCountFree", dto.getFreenum());
			int result = sqlSession.insert("board.uploadFreeComment", dto);
			return result;
		}

		// 자유게시판 댓글 목록 가져오기 - 노현호
		@Override
		public List<FreeCommentDTO> getCommentListFree(int startRow, int endRow, int boardnum, int code) throws SQLException {
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("start", startRow);
			map.put("end", endRow);
			map.put("boardnum", boardnum);
			
			List<FreeCommentDTO> comment = sqlSession.selectList("board.getFreeComment", map);
			return comment;
		}
		
		// 자유게시판 댓글 갯수 가져오기 - 노현호
		@Override
		public int getCommentCount(int boardnum, int code) throws SQLException {
			int count = sqlSession.selectOne("board.countCommentFree", boardnum);
			return count;
		}
	
		// 자유게시판 댓글 하나 가져오기 - 노현호
		@Override
		public FreeCommentDTO getFreeComment(int commentnum) throws SQLException {
			FreeCommentDTO dto = sqlSession.selectOne("board.getFreeCommentOne", commentnum);
			return dto;
		}
	
	
	// 10. 환경일기 댓글
		// 환경일기 댓글 업로드 - 이다희
		@Override
		public void insertDiaryReply(int boardnum, String ctt, String writer, String receiver) throws SQLException {
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("boardnum", boardnum);
			map.put("ctt", ctt);
			map.put("writer", writer);
			map.put("receiver", receiver);
			
			int result = sqlSession.insert("board.insertDiaryReply", map);
			sqlSession.update("board.minusReadCountDairy", boardnum);
			if(result == 1) {
				// result 가 성공적으로 들어갔을때, 포인트 넣어주기 sql 에서 count = 0 일때만 포인트 적립하게 조건걸기 
				sqlSession.update("user.updateReplyPoint", map);
				System.out.println("result 1 일때, 환경일기 댓글에서 포인트 5 넣어주기 - commnetcount = 0일때만 포인트 업  ");
			}
			
		}
		// 환경일기 댓글 가져오기 - 이다희
		@Override
		public List<DiaryDTO> getDiaryReplyList(int startRow, int endRow, int boardnum) throws SQLException {
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("startRow", startRow); 
			map.put("endRow", endRow);
			map.put("boardnum", boardnum);
								 
			List<DiaryDTO> replyList = sqlSession.selectList("board.getDiaryReplyList", map);
			
			return replyList;
		}
		
		// 환경일기 댓글 갯수 가져오기 - 이다희 
		@Override
		public int getDiaryReplyListCount(int boardnum) throws SQLException {
			int count = sqlSession.selectOne("board.getDiaryReplyListCount", boardnum);
			return count;
		}
		
		
	// 11. 이달의 챌린지 댓글
		// 이달의 챌린지 댓글 업로드 - 이다희
		@Override
		public void insertChReply(int boardnum, String ctt, String writer) throws SQLException {
			 
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("boardnum", boardnum);
			map.put("ctt", ctt);
			map.put("writer", writer);
			
			//sqlSession.insert("board.insertChReply", map);
			
			int result = sqlSession.insert("board.insertChReply", map);
			System.out.println(" 댓글 인서트 result =====> " + result);
			
			if(result == 1) {
				// result 가 성공적으로 들어갔을때, 포인트 넣어주기 sql 에서 count = 0 일때만 포인트 적립하게 조건걸기 
				sqlSession.update("user.updateReplyPoint", map);
				System.out.println("result 1 일때, 챌린지 댓글에서 포인트 5 넣어주기 - commnetcount = 0일때만 포인트 업  ");
			}
			
		}
		
		// 이달의 챌린지 댓글 가져오기 - 이다희
		@Override
		public List<MonthDTO> getChReplyList(int startRow, int endRow, int boardnum) throws SQLException {
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("startRow", startRow); 
			map.put("endRow", endRow);
			map.put("boardnum", boardnum);
								 
			List<MonthDTO> replyList = sqlSession.selectList("board.getChReplyList", map);
			return replyList;
		}
		
		// 이달의 챌린지 댓글 갯수 가져오기 - 이다희 
		@Override
		public int getChReplyListCount(int boardnum) throws SQLException {
			int count = sqlSession.selectOne("board.getChReplyListCount", boardnum);
			return count;
		}
		
		// 이달의 챌린지 및 환경일기 댓글삭제 - 이다희
		@Override
		public int replydelete(int commentnum, String categ) throws SQLException {
			String delete = "board.replydelete" + categ;
			int result = sqlSession.delete(delete, commentnum);
			return result;
		}
	

}
