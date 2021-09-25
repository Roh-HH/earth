package earth.board.service;

import java.sql.SQLException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import earth.board.dao.BoardDAOImpl;
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

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAOImpl boardDAO = null;
	
	// 공용 및 게시판 번호순 1차 정렬 CRUD순 2차 정렬

	// 0. 공용
    
        //// ============= 닉네임 가져오기 다희 추가
        @Override
        public String getNickname(String id) throws SQLException {
            String nickname = boardDAO.getNickname(id);
            return nickname;
        }

        @Override
        public String getBoardid(String search) throws SQLException {
            String boardid = boardDAO.getBoardid(search);
            return boardid;
        }

         @Override
         public String getNicknamereply(String writer) throws SQLException {	
             String nicknamereply = boardDAO.getNicknamereply(writer);
             return nicknamereply;
         }

        @Override
        public String getBadgeimg(String id) throws SQLException {
            String badgeimg = boardDAO.getBadgeimg(id);
            return badgeimg;
        }
        
        //// ============= 뱃지이미지 가져오기 다희 추가
        @Override
        public String getBadgeimgreply(String writer) throws SQLException {
            String badgeimg = boardDAO.getBadgeimgreply(writer);
            return badgeimg;
        }
    
		// 게시판의 게시글 목록 가져오기 - 노현호
		@Override
		public Map<String, Object> getArticleList(String pageNum, int code) throws SQLException {
			
			// 한페이지에 보여줄 게시글의 수 
			int pageSize = 10; 
			if(code == 3 || code == 6 || code == 7 || code == 8) {
				pageSize = 9;
			}
			// 현재 페이지 번호  
			if(pageNum == null) pageNum = "1";
			
			// 현재 페이지에 보여줄 게시글 시작과 끝 등등 정보 세팅 
			int currentPage = Integer.parseInt(pageNum); 
			int startRow = (currentPage - 1) * pageSize + 1; 		// 페이지 시작글 번호 
			int endRow = currentPage * pageSize; 					// 페이지 마지막 글번호
			int count = 0; 											// 전체(검색된) 글의 개수 
			int number = 0; 										// 브라우저 화면에 뿌려줄 가상 글 번호
			
			// DB에 저장되어있는 전체 글의 개수를 가져와 담기
			count = boardDAO.getArticleCount(code);					
			number = count - (currentPage-1) * pageSize; 			// 게시판 목록에 뿌려줄 가상의 글 번호 
			System.out.println("전체 게시글 수 : " + count);
			
			Map<String, Object> result = new HashMap<String, Object>();
			
			// 글이 하나라도 있으면 글들을 다시 가져오기
			switch(code) {
			case 1:
				// 공지사항 - 노현호
				if(count > 0){
					List<NoticeDTO> articleList = boardDAO.getNoticeArticles(startRow, endRow, code); 
					result.put("articleList", articleList);
				}
				break;
			case 2:
				// 자유게시판 - 노현호
				if(count > 0){
					List<FreeDTO> articleList = boardDAO.getFreeArticles(startRow, endRow, code); 
					result.put("articleList", articleList);
				}
				break;
			case 3:
				// 환경일기 - 이다희
				if(count > 0){
					List<DiaryDTO> articleList = boardDAO.getDiaryArticles(startRow, endRow, code); 
					result.put("articleList", articleList);
				}
				break;
			case 4:
				// 이달의 챌린지 - 이다희
				if(count > 0){
					List<MonthDTO> articleList = boardDAO.getChallengeArticles(startRow, endRow, code); 
					result.put("articleList", articleList);
				}
				break;
			case 5:
				// 오늘의 실천 - 노현호
				if(count > 0){
					List<TodayDTO> articleList = boardDAO.getTodayArticles(startRow, endRow, code); 
					result.put("articleList", articleList);
				}
				break;
			case 6:
				// 상점소개 - 김하영
				if(count > 0) {
					List<ShopDTO> articleList = boardDAO.getShopArticles(startRow, endRow, code);
					result.put("articleList", articleList);
				}
				break;
			case 7:
				// 행사 - 김하영
				if(count > 0) {
					List<EventDTO> articleList = boardDAO.getEventArticles(startRow, endRow, code);
					result.put("articleList", articleList);
				}
				break;
			case 8:
				// 꿀팁 - 김하영
				if(count > 0) {
					List<TipDTO> articleList = boardDAO.getTipArticles(startRow, endRow, code);
					result.put("articleList", articleList);
				}
				break;
			}
				
			// Controller 에게 전달
			result.put("pageSize", pageSize);
			result.put("pageNum", pageNum);
			result.put("currentPage", currentPage);
			result.put("startRow", startRow);
			result.put("endRow", endRow);
			result.put("count", count);
			result.put("number", number);
			
			return result;
		}
		
		// 게시판 검색한 게시글 목록 가져오기 - 김하영
		@Override
		public Map<String, Object> getArticleSearch(String pageNum, String sel, String search, int code) throws SQLException {
			
			// 한페이지에 보여줄 게시글의 수 
			int pageSize = 9;
			
			// 현재 페이지 번호  
			if(pageNum == null) pageNum = "1";
			
			// 현재 페이지에 보여줄 게시글 시작과 끝 등등 정보 세팅 
			int currentPage = Integer.parseInt(pageNum); 
			int startRow = (currentPage - 1) * pageSize + 1; 
			int endRow = currentPage * pageSize;
			int count = 0; 
			int number = 0;
			
			// DB에 저장되어있는 전체 글의 개수를 가져와 담기
			number = count - (currentPage-1) * pageSize;  
			System.out.println("검색된 게시글 수 : " + count);
			
			Map<String, Object> result = new HashMap<String, Object>(); 
			
			// 글이 하나라도 있으면 글들을 다시 가져오기
			switch(code) {
			case 6:
				// 상점소개 - 김하영
				count = boardDAO.getShopCount(sel, search); // 검색된 글의 총 개수 가져오기 
				if(count > 0){
					List<ShopDTO> articleList = boardDAO.getShopSearch(startRow, endRow, sel, search,code );
					result.put("articleList", articleList);
				}
				break;		
			case 7:
				// 행사 - 김하영
				count = boardDAO.getEventCount(sel, search); // 검색된 글의 총 개수 가져오기 
				if(count > 0){
					List<EventDTO> articleList = boardDAO.getEventSearch(startRow, endRow, sel, search, code); 
					result.put("articleList", articleList);
				}
				break;	
			case 8:
				// 꿀팁 - 김하영
				count = boardDAO.getTipCount(sel, search); // 검색된 글의 총 개수 가져오기 
				if(count > 0){
					List<TipDTO> articleList = boardDAO.getTipSearch(startRow, endRow, sel, search, code); 
					result.put("articleList", articleList);
				}
				break;
			}

			// Controller 에게 전달 
			result.put("pageSize", pageSize);
			result.put("pageNum", pageNum);
			result.put("currentPage", currentPage);
			result.put("startRow", startRow);
			result.put("endRow", endRow);
			result.put("count", count);
			result.put("number", number);

			return result;
		}
		
		// 게시판 글 1개의 댓글 목록 가져오기 - 노현호
		@Override
		public Map<String, Object> getCommentList(int commentPageNum, int boardnum, int code) throws SQLException {
			
			// 한페이지에 보여줄 댓글의 수 
			int pageSize = 10; 

			// 현재 댓글 페이지 번호  
			if(commentPageNum == 0) commentPageNum = 1;
			
			// 현재 페이지에 보여줄 댓글의 시작과 끝 등등 정보 세팅 
			int currentPage = commentPageNum; 
			int startRow = (currentPage - 1) * pageSize + 1; 		// 페이지 시작글 번호 
			int endRow = currentPage * pageSize; 					// 페이지 마지막 글번호
			int count = 0; 											// 전체(검색된) 글의 개수 
			int number = 0; 										// 브라우저 화면에 뿌려줄 가상 글 번호
			
			// DB에 저장되어있는 해당 글의 전체 댓글의 개수를 가져와 담기
			count = boardDAO.getCommentCount(boardnum, code);					
			number = count - (currentPage-1) * pageSize; 			// 게시판 목록에 뿌려줄 가상의 글 번호 
			System.out.println("전체 게시글 수 : " + count);
			
			Map<String, Object> result = new HashMap<String, Object>();
			
			// 글이 하나라도 있으면 글들을 다시 가져오기
			switch(code) {
			case 9:
				// 자유게시판 댓글 - 노현호
				if(count > 0){
					List<FreeCommentDTO> comment = boardDAO.getCommentListFree(startRow, endRow, boardnum, code);
					result.put("comment", comment);
				}
				break;
			case 10:
				// 환경일기 댓글 - 이다희
				
				break;
			case 11:
				// 이달의 챌린지 댓글 - 이다희
				
				break;
			}
			
			// Controller 에게 전달
			result.put("pageSize", pageSize);
			result.put("commentPageNum", commentPageNum);
			result.put("currentPage", currentPage);
			result.put("startRow", startRow);
			result.put("endRow", endRow);
			result.put("count", count);
			result.put("number", number);
			
			return result;
		}
		
		// 비밀번호 일치여부 확인(암호화) - 노현호
		@Override
		public String getPw(int boardnum, int code) throws SQLException {
			String hashedPassword = boardDAO.getPw(boardnum, code);
			return hashedPassword;
		}
		
		// 이미지 삭제를 위한 ctt 가져오기 - 노현호
		@Override
		public String getCtt(int boardnum, int code) throws SQLException {
			String ctt = boardDAO.getCtt(boardnum, code);
			return ctt;
		}
		
		// 이미지 삭제를 위한 img 가져오기 - 노현호
		@Override
		public String getImg(int boardnum, int code) throws SQLException {
			String img = boardDAO.getImg(boardnum, code);
			return img;
		}
		
		// 게시글 삭제 처리 (게시판 통합) - 노현호
		@Override
		public int deleteArticle(int boardnum, int code) throws SQLException {
			int result = boardDAO.deleteArticle(boardnum, code);
			return result;
		}
		
		
	// 1. 공지사항
		// 공지사항 게시글 업로드 - 노현호
		@Override
		public int insertNotice(NoticeDTO dto) throws SQLException {
			int result = boardDAO.insertNotice(dto);
			return result;
		}
		
		// 공지사항 글 1개 가져오기 - 노현호
		@Override
		public NoticeDTO getNoticeArticle(int boardnum) throws SQLException {
			// 조회수 1 증가시기키 및 해당 글 정보 가져오기
			NoticeDTO article = boardDAO.getNoticeArticle(boardnum);
			return article;
		}

		// 공지사항 글 수정 처리 - 노현호
		@Override
		public int updateNoticeArticle(NoticeDTO dto) throws SQLException {
			int result = boardDAO.updateNoticeArticle(dto);
			return result;
		}
		
		
	// 2. 자유게시판
		// 자유게시판 게시글 업로드 - 노현호
		@Override
		public int insertFree(FreeDTO dto) throws SQLException {
			int result = boardDAO.insertFree(dto);
			return result;
		}
		
		// 자유게시판 글 1개 가져오기 - 노현호
		@Override
		public FreeDTO getFreeArticle(int boardnum) throws SQLException {
			FreeDTO article = boardDAO.getFreeArticle(boardnum);
			return article;
		}
		
		// 자유게시판 말머리(brackets categList) 불러오기 - 노현호
		@Override
		public List<BracketsDTO> getBrackets() throws SQLException {
			List<BracketsDTO> categList = boardDAO.getBrackets();
			return categList;
		}
		
		// 자유게시판 글 수정처리 - 노현호
		@Override
		public int updateFreeArticle(FreeDTO dto) throws SQLException {
			int result = boardDAO.updateFreeArticle(dto);
			return result;
		}
		
		
	// 3. 환경일기
		// 환경일기 게시글 업로드  - 이다희
		@Override
		public int insertDiary(DiaryDTO dto) throws SQLException {
			int result = boardDAO.insertDiary(dto);
			return result;
		}
		
		// 환경일기 글 1개 가져오기 - 이다희
		@Override
		public DiaryDTO getDiaryArticle(int boardnum) throws SQLException {
			DiaryDTO article = boardDAO.getDiaryArticle(boardnum);
			return article;
		}
		
		// 환경일기 검색한 게시글 목록 가져오기  - 이다희
		@Override									 
		public Map<String, Object> getDiaryArticleSearch (String pageNum, String sel, String search, int code) throws SQLException {
			 
			int pageSize = 9; 
			// 현재 페이지 번호  
			//String pageNum = request.getParameter("pageNum");
			// 이미 넘어왔으니까 위에꺼 필요없음 !
			if(pageNum == null){ // list.jsp 라고만 요청했을때, 즉 pageNum 파라미터 안넘어왔을때.
				pageNum = "1";
			}
			
			// 현재 페이지에 보여줄 게시글 시작과 끝 등등 정보 세팅 
			int currentPage = Integer.parseInt(pageNum); // 계산을 위해 현재페이지 숫자로 변환하여 저장 
			int startRow = (currentPage - 1) * pageSize + 1; // 페이지 시작글 번호 
			int endRow = currentPage * pageSize; // 페이지 마지막 글번호
			
	 		
			// 밖에서 사용가능하게 if문 시작 전에 미리 선언
			List<DiaryDTO> articleList = null;  	// 검색된 게시글들 담아줄 변수
			int count = 0; 				// (검색된 글의 개수 
			int number = 0; 			// 브라우저 화면에 뿌려줄 가상 글 번호  
			
			 
			count = boardDAO.getSearchDiaryArticleCount(sel, search); // 검색된 글의 총 개수 가져오기 
			System.out.println("검색 count : " + count);
			// 검색한 글이 하나라도 있으면 검색한 글 가져오기 
				if(count > 0){
					articleList = boardDAO.getSearchDiaryArticles(startRow, endRow, sel, search); 
				}
			 
			number = count - (currentPage-1)*pageSize; 	// 게시판 목록에 뿌려줄 가상의 글 번호  
			
			//Controller 에게 전달해야되는 데이터가 많으니 HashMap 에넘겨줄 데이터를 저장해서 한번에 전달
			// 이름가지고 꺼낼꺼면맵타입, 순서대로 정리해서 꺼낼거면 list
			Map<String, Object> result = new HashMap<>();
			result.put("pageSize", pageSize);
			result.put("pageNum", pageNum);
			result.put("currentPage", currentPage);
			result.put("startRow", startRow);
			result.put("endRow", endRow);
			result.put("articleList", articleList);
			result.put("count", count);
			result.put("number", number);
			//result.put("sel", sel);
			//result.put("search", search);
				// sel, search 는 비지니스 로직에 바뀌는게 없으니까 추가 안해줘도 된다. 컨프롤러에서 추가 해서 뷰로 보내줄것 
			return result;
		}
		
		// 환경일기 좋아요 아이디 체크  - 이다희 
		@Override
		public int recidCheck(int boardnum, String recid) throws SQLException {
			int idCheck = boardDAO.recidCheck(boardnum, recid);
			return idCheck;
		}
		
		// 환경일기 글 수정처리 - 이다희
		@Override
		public int updateDiaryArticle(DiaryDTO dto) throws SQLException {
			int result = boardDAO.updateDiaryArticle(dto);
			return result;
		}
		
		// 환경일기 글 수정처리(이미지포함) - 이다희
		@Override
		public int updateDiaryArticleImg(DiaryDTO dto) throws SQLException {
			int result = boardDAO.updateDiaryArticleImg(dto);
			return result;
		}
		
		// 환경일기 좋아요 - 이다희
		@Override
		public void likeUp(int boardnum, String recid) throws SQLException {
			boardDAO.likeUp(boardnum, recid);	 
		}
		
		// 환경일기 좋아요 취소 - 이다희
		@Override
		public void likeCancel(int boardnum, String recid) throws SQLException {
			boardDAO.likeCancel(boardnum, recid); 
		}
		
		
	// 4. 이달의 챌린지
		// 이달의 챌린지 게시글 업로드  - 이다희
		@Override
		public void insertChallenge(MonthDTO dto) throws SQLException {
			boardDAO.insertChallenge(dto);
		}
		
		// 이달의 챌린지 참여하기 - 이다희
		@Override
		public int insertChJoin(int boardnum, String id) throws SQLException {
			boardDAO.joinCountUp(boardnum);
			int result = boardDAO.insertChJoin(boardnum, id);
			return result;
		}
	
		//이달의 챌린지 최신글 가져오기 
		@Override
		public MonthDTO getChallenge() throws SQLException {
			MonthDTO article = boardDAO.getChallenge();
			return article;
		}

		
		// 이달의 챌린지 글 1개 가져오기 - 이다희
		@Override
		public MonthDTO getChallengeArticle(int boardnum) throws SQLException {
			MonthDTO article = boardDAO.getChallengeArticle(boardnum);
			return article;
		}
		
		// 이달의 챌린지 조인 아이디 체크 - 이다희
		@Override
		public int joinidCheck(int boardnum, String id) throws SQLException {
			int idCheck = boardDAO.joinidCheck(boardnum, id);
			return idCheck;
		}
		
		// 이달의 챌린지 마감데이트 확인 - 이다희
		@Override
		public int dateCheck(int boardnum) throws SQLException {
			int dateCheck = boardDAO.dateCheck(boardnum);
			return dateCheck;
		}
		
		//이달의 챌린지 글 수정처리 - 이다희
		@Override
		public int updateChallengeArticle(MonthDTO dto) throws SQLException {
			int result = boardDAO.updateChallengeArticle(dto);
			return result;
		}
		
		// 이달의 챌린지 글 수정처리(이미지포함) - 이다희
		@Override
		public int updateChallengeArticleImg(MonthDTO dto) throws SQLException {
			int result = boardDAO.updateChallengeArticleImg(dto);
			return result;
		}
		
		
	// 5. 오늘의 실천
		// 오늘의 실천 업로드 - 노현호
		@Override
		public int upload(TodayDTO dto) throws SQLException {
			int result = boardDAO.upload(dto);
			return result;
		}
	
		
	// 6. 상점소개
		//상점소개 게시글 업로드 - 김하영 
		@Override
		public int insertShop(ShopDTO dto) throws SQLException {
			int result = boardDAO.insertShop(dto);
			return result;
		}
		
		//상점소개 글 1개 가져오기- 김하영
		@Override
		public ShopDTO getShopArticle(int boardnum) throws SQLException {
			ShopDTO article = boardDAO.getShopArticle(boardnum);
			return article;
		}
		
		//상점소개 글 수정처리 - 김하영
		@Override
		public int updateShopArticle(ShopDTO dto) throws SQLException {
			int result = boardDAO.updateShopArticle(dto);
			return result;
		}
		
		//상점소개 글 수정처리(이미지포함) - 김하영 
		@Override
		public int updateShopArticleImg(ShopDTO dto) throws SQLException {
			int result = boardDAO.updateShopArticleImg(dto);
			return result;
		}
		
		
	// 7. 행사
		//행사일정 게시글 업로드 - 김하영
		@Override
		public int insertEvent(EventDTO dto) throws SQLException {
			int result = boardDAO.insertEvent(dto);
			return result;
		}
		
		//행사일정 글 1개 가져오기 - 김하영
		@Override
		public EventDTO getEventArticle(int boardnum) throws SQLException {
			EventDTO article = boardDAO.getEventArticle(boardnum);
			return article;
		}
		
		//행사일정 글 수정처리 - 김하영
		@Override
		public int updateEventArticle(EventDTO dto) throws SQLException {
			int result = boardDAO.updateEventArticle(dto);
			return result;
		}

		//행사일정 글 수정처리(이미지포함) - 김하영
		@Override
		public int updateEventArticleImg(EventDTO dto) throws SQLException {
			int result = boardDAO.updateEventArticleImg(dto);
			return result;
		}
		
		
	// 8. 꿀팁
		//꿀팁 게시글 등록 - 김하영
		@Override
		public int insertTip(TipDTO dto) throws SQLException {
			int result = boardDAO.insertTip(dto);
			return result;
		}
		
		//꿀팁 글 1개 가져오기 - 김하영 
		@Override
		public TipDTO getTipArticle(int boardnum) throws SQLException {
			TipDTO article = boardDAO.getTipArticle(boardnum);
			return article;
		}
		
		//꿀팁 글 수정처리 - 김하영 
		@Override
		public int updateTipArticle(TipDTO dto) throws SQLException {
			int result = boardDAO.updateTipArticle(dto);
			return result;
		}

		//꿀팁 글 수정처리(이미지포함) - 김하영
		@Override
		public int updateTipArticleImg(TipDTO dto) throws SQLException {
			int result = boardDAO.updateTipArticleImg(dto);
			return result;
		}
		
		
	// 9. 자유게시판 댓글
		// 자유게시판 댓글 업로드 - 노현호
		@Override
		public int uploadFreeComment(FreeCommentDTO dto) throws SQLException {
			int result = boardDAO.uploadFreeComment(dto);
			return result;
		}
		
	
	// 10. 환경일기 댓글
		// 환경일기 댓글 업로드 - 이다희
		@Override
		public void insertDiaryReply(int boardnum, String ctt, String writer, String receiver) throws SQLException {
			boardDAO.insertDiaryReply(boardnum, ctt, writer, receiver);
		}
		
		// 환경일기 댓글 가져오기 - 이다희
		@Override
		public Map<String, Object> getDiaryReplyList(int boardnum, String pageN) throws SQLException {
			
			//테스트용 
			//int pageSize = 5; 
			int pageSize= 20;
			
			if(pageN == null){ // list.jsp 라고만 요청했을때, 즉 pageNum 파라미터 안넘어왔을때.
				pageN = "1";
			}
			
			int currentPage = Integer.parseInt(pageN); // 계산을 위해 현재페이지 숫자로 변환하여 저장 
			int startRow = (currentPage - 1) * pageSize + 1; // 페이지 시작글 번호
			int endRow = currentPage * pageSize; // 페이지 마지막 글번호 
			int count = 0; 
			int number = 0; 
			
			List<DiaryDTO> replyList = null;  	// 전체(검색된) 게시글들 담아줄 변수
			// 전체(검색된) 글의 개수 
			count = boardDAO.getDiaryReplyListCount(boardnum); 
			if(count > 0){
			replyList = boardDAO.getDiaryReplyList(startRow, endRow, boardnum); 
			
			}
			number = count - (currentPage-1) * pageSize; 
			
			Map<String, Object> map = new HashMap<>();
			map.put("pageSize", pageSize);
			map.put("pageN", pageN);
			map.put("currentPage", currentPage);
			map.put("startRow", startRow);
			map.put("endRow", endRow);
			map.put("replyList", replyList);
			map.put("count", count);	
			map.put("number", number);	
		 
			return map;
		}
		
		
	// 11. 이달의 챌린지 댓글
		// 이달의 챌린지 댓글 업로드 - 이다희
		@Override
		public void insertChReply(int boardnum, String ctt, String writer) throws SQLException {
			boardDAO.insertChReply(boardnum, ctt, writer);
		}
		
		// 이달의 챌린지 댓글 가져오기 - 이다희
		@Override
		public Map<String, Object> getChReplyList(int boardnum, String pageN) throws SQLException {
			
			//테스트용 
			//int pageSize = 5; 
			int pageSize= 20;
			if(pageN == null){ // list.jsp 라고만 요청했을때, 즉 pageNum 파라미터 안넘어왔을때.
				pageN = "1";
			}
			
			int currentPage = Integer.parseInt(pageN); // 계산을 위해 현재페이지 숫자로 변환하여 저장 
			int startRow = (currentPage - 1) * pageSize + 1; // 페이지 시작글 번호 
			int endRow = currentPage * pageSize; // 페이지 마지막 글번호
			int count = 0; 
			int number = 0; 
			
			
			List<MonthDTO> replyList = null;  	// 전체(검색된) 게시글들 담아줄 변수
							// 전체(검색된) 글의 개수 
			count = boardDAO.getChReplyListCount(boardnum); 
			if(count > 0){
				replyList = boardDAO.getChReplyList(startRow, endRow, boardnum); 

			}
			number = count - (currentPage-1) * pageSize; 
			
			Map<String, Object> map = new HashMap<>();
			map.put("pageSize", pageSize);
			map.put("pageN", pageN);
			map.put("currentPage", currentPage);
			map.put("startRow", startRow);
			map.put("endRow", endRow);
			map.put("replyList", replyList);
			map.put("count", count);	
			map.put("number", number);	
		
			System.out.println("------reply-----------");
			System.out.println("number" + number);
			System.out.println("pageN" + pageN);
			System.out.println("-----------------");
			 
			return map;
		}
		
		// 이달의 챌린지 및 환경일기 댓글삭제 - 이다희
		@Override
		public int replydelete(int commentnum, String categ) throws SQLException {
			int result = boardDAO.replydelete(commentnum, categ);
			return result;
		}
	
	
}
