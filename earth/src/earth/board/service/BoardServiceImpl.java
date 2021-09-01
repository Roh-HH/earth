package earth.board.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import earth.board.dao.BoardDAOImpl;
import earth.board.dto.BoardDTO;
import earth.board.dto.TodayDTO;

// 구현
@Service		// component-scan 이용해 자동으로 빈으로 등록시킬 것임 (객체생성)
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAOImpl boardDAO = null;

	// 한페이지의 게시글 목록 가져오기 (List)
	@Override
	public Map<String, Object> getArticleList(String pageNum, int code) throws SQLException {
		
		Map<String, Object> result = null;
		
		if(code == 1) {
			/*
			// ** 게시글 페이지 관련 정보 세팅 ** 
			// 한페이지에 보여줄 게시글의 수 
			int pageSize = 10; 
			// 현재 페이지 번호  
			if(pageNum == null){ // list.jsp 라고만 요청했을때, 즉 pageNum 파라미터 안넘어왔을때.
				pageNum = "1";
			}
			
			// 현재 페이지에 보여줄 게시글 시작과 끝 등등 정보 세팅 
			int currentPage = Integer.parseInt(pageNum); // 계산을 위해 현재페이지 숫자로 변환하여 저장 
			int startRow = (currentPage - 1) * pageSize + 1; // 페이지 시작글 번호 
			int endRow = currentPage * pageSize; // 페이지 마지막 글번호
	
			// 밖에서 사용가능하게 if문 시작 전에 미리 선언
			List<BoardDTO> articleList = null;  	// 전체(검색된) 게시글들 담아줄 변수
			int count = 0; 				// 전체(검색된) 글의 개수 
			int number = 0; 			// 브라우저 화면에 뿌려줄 가상 글 번호  
			
			count = boardDAO.getArticleCount();   // DB에 저장되어있는 전체 글의 개수를 가져와 담기
			System.out.println("count : " + count);
			// 글이 하나라도 있으면 글들을 다시 가져오기 
			if(count > 0){
				articleList = boardDAO.getArticles(startRow, endRow); 
			}	
			number = count - (currentPage-1) * pageSize; 	// 게시판 목록에 뿌려줄 가상의 글 번호 
			
			// Controller 에게 전달해야 되는 데이터가 많으니, HashMap에 넘겨 줄 데이터를 저장한 후 한번에 전달하기
			result = new HashMap<String, Object>();
			result.put("pageSize", pageSize);
			result.put("pageNum", pageNum);
			result.put("currentPage", currentPage);
			result.put("startRow", startRow);
			result.put("endRow", endRow);
			result.put("articleList", articleList);
			result.put("count", count);
			result.put("number", number);
			*/
		}
		
		// 오늘의 실천 게시판 게시글 목록 가져오기 - 노현호
		if(code == 5) {
			// ** 게시글 페이지 관련 정보 세팅 ** 
			// 한페이지에 보여줄 게시글의 수 
			int pageSize = 10; 
			// 현재 페이지 번호  
			if(pageNum == null){ // list.jsp 라고만 요청했을때, 즉 pageNum 파라미터 안넘어왔을때.
				pageNum = "1";
			}
			
			// 현재 페이지에 보여줄 게시글 시작과 끝 등등 정보 세팅 
			int currentPage = Integer.parseInt(pageNum); 
			int startRow = (currentPage - 1) * pageSize + 1; 		// 페이지 시작글 번호 
			int endRow = currentPage * pageSize; 					// 페이지 마지막 글번호

			// 밖에서 사용가능하게 if문 시작 전에 미리 선언
			List<TodayDTO> articleList = null;  					// 전체(검색된) 게시글들 담아줄 변수
			int count = 0; 											// 전체(검색된) 글의 개수 
			int number = 0; 										// 브라우저 화면에 뿌려줄 가상 글 번호  
			
			// DB에 저장되어있는 전체 글의 개수를 가져와 담기
			count = boardDAO.getArticleCount(code);					
			System.out.println("전체 게시글 수 : " + count);
			
			// 글이 하나라도 있으면 글들을 다시 가져오기 
			if(count > 0){
				articleList = boardDAO.getArticles(startRow, endRow, code); 
			}	
			number = count - (currentPage-1) * pageSize; 	// 게시판 목록에 뿌려줄 가상의 글 번호 
			
			// Controller 에게 전달해야 되는 데이터가 많으니, HashMap에 넘겨 줄 데이터를 저장한 후 한번에 전달하기
			result = new HashMap<String, Object>();
			result.put("pageSize", pageSize);
			result.put("pageNum", pageNum);
			result.put("currentPage", currentPage);
			result.put("startRow", startRow);
			result.put("endRow", endRow);
			result.put("articleList", articleList);
			result.put("count", count);
			result.put("number", number);
		}
		return result;
	}

	@Override
	public int upload(TodayDTO dto) throws SQLException {
		int result = boardDAO.upload(dto);
		return result;
	}
	
	// 검색 한 글 목록 가져오기 (list 검색)
	@Override
	public Map<String, Object> getArticleSearch(String pageNum, String sel, String search, int code) throws SQLException {
		// ** 게시글 페이지 관련 정보 세팅 ** 
		// 한페이지에 보여줄 게시글의 수 
		int pageSize = 10; 
		// 현재 페이지 번호  
		if(pageNum == null){ // list.jsp 라고만 요청했을때, 즉 pageNum 파라미터 안넘어왔을때.
			pageNum = "1";
		}
				
		// 현재 페이지에 보여줄 게시글 시작과 끝 등등 정보 세팅 
		int currentPage = Integer.parseInt(pageNum); // 계산을 위해 현재페이지 숫자로 변환하여 저장 
		int startRow = (currentPage - 1) * pageSize + 1; // 페이지 시작글 번호 
		int endRow = currentPage * pageSize; // 페이지 마지막 글번호
					
		// 밖에서 사용가능하게 if문 시작 전에 미리 선언
		List<BoardDTO> articleList = null;  	// 전체(검색된) 게시글들 담아줄 변수
		int count = 0; 				// 전체(검색된) 글의 개수 
		int number = 0; 			// 브라우저 화면에 뿌려줄 가상 글 번호  
		
		// 검색된 글의 총 개수 가져오기
		count = boardDAO.getSearchArticleCount(sel, search);  
		System.out.println("검색 count : " + count);
		
		// 검색한 글이 하나라도 있으면 검색한 글 가져오기 
		if(count > 0){
			articleList = boardDAO.getSearchArticles(startRow, endRow, sel, search);  
		}
				
		number = count - (currentPage-1) * pageSize; 	// 게시판 목록에 뿌려줄 가상의 글 번호 
				
		// Controller 에게 전달해야 되는 데이터가 많으니, HashMap에 넘겨 줄 데이터를 저장한 후 한번에 전달하기
		Map<String, Object> result = new HashMap();
		result.put("pageSize", pageSize);
		result.put("pageNum", pageNum);
		result.put("currentPage", currentPage);
		result.put("startRow", startRow);
		result.put("endRow", endRow);
		result.put("articleList", articleList);
		result.put("count", count);
		result.put("number", number);
				
		return result;
		}

	// 글 저장하기
	@Override
	public void insertArticle(BoardDTO dto) throws SQLException {		
		boardDAO.insertArticle(dto);
	}
	// 글 1개 정보 가져오기
	@Override
	public BoardDTO getArticle(int num) throws SQLException {
		// 해당 글 조회수 처리
		boardDAO.readcountUp(num);
		// 해당 글 정보 가져오기
		BoardDTO article = boardDAO.getArticle(num);
		
		return article;
	}
	// modifyForm - 글 1개 전체 정보 가져오기
	@Override
	public BoardDTO getUpdateArticle(int num) throws SQLException {
		// 수정처리위해 해당 글 전체 정보 불러오기
		return boardDAO.getArticle(num);
	}
	// modifyPro - 게시글 수정처리
	@Override
	public int updateArticle(BoardDTO dto) throws SQLException {
		int result = boardDAO.pwCheck(dto);
		// 게시글 수정 처리하기
		if(result == 1) {
			boardDAO.updateArticle(dto);
		}
		return result;
	}
	// deleteForm/pro - 게시글 삭제처리
	@Override
	public int deleteArticle(BoardDTO dto) throws SQLException {
		int result = boardDAO.pwCheck(dto);
		if(result == 1) {
			boardDAO.deleteArticle(dto);
		}
		return result;
	}

}
