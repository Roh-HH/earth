package earth.board.service;

import java.io.File;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import earth.board.dao.BoardDAOImpl;
import earth.board.dto.BoardDTO;
import earth.board.dto.BracketsDTO;
import earth.board.dto.FreeCommentDTO;
import earth.board.dto.FreeDTO;
import earth.board.dto.MonthDTO;
import earth.board.dto.NoticeDTO;
import earth.board.dto.TodayDTO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAOImpl boardDAO = null;

	// 한페이지의 게시글 목록 가져오기 (List)
	@Override
	public Map<String, Object> getArticleList(String pageNum, int code) throws SQLException {
		
		// 한페이지에 보여줄 게시글의 수 
		int pageSize = 10; 
		if(code == 3 || code == 6 || code == 7 || code == 8) {
			pageSize = 9;
		}
		// 현재 페이지 번호  
		if(pageNum == null){ // pageNum 파라미터 안넘어왔을때.
			pageNum = "1";
		}
		
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
			if(count > 0){
				List<NoticeDTO> articleList = boardDAO.getNoticeArticles(startRow, endRow, code); 
				result.put("articleList", articleList);
			}
			break;
		case 2:
			if(count > 0){
				List<FreeDTO> articleList = boardDAO.getFreeArticles(startRow, endRow, code); 
				result.put("articleList", articleList);
			}
			break;
		case 3:
			
			break;
		case 4:
			if(count > 0){
				List<MonthDTO> articleList = boardDAO.getChallengeArticles(startRow, endRow, code); 
				result.put("articleList", articleList);
			}
			break;
		case 5:
			if(count > 0){
				List<TodayDTO> articleList = boardDAO.getTodayArticles(startRow, endRow, code); 
				result.put("articleList", articleList);
			}
			break;
		case 6:
			
			break;
		case 7:
			
			break;
		case 8:
			
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

	@Override
	public int insertNotice(NoticeDTO dto) throws SQLException {
		int result = boardDAO.insertNotice(dto);
		return result;
	}
	
	@Override
	public int insertFree(FreeDTO dto) throws SQLException {
		int result = boardDAO.insertFree(dto);
		return result;
	}
	
	@Override
	public int uploadFreeComment(FreeCommentDTO dto) throws SQLException {
		int result = boardDAO.uploadFreeComment(dto);
		return result;
	}
	
	@Override
	public int upload(TodayDTO dto) throws SQLException {
		int result = boardDAO.upload(dto);
		return result;
	}
	
	@Override
	public NoticeDTO getNoticeArticle(int boardnum) throws SQLException {
		// 조회수 1 증가시기키 및 해당 글 정보 가져오기
		NoticeDTO article = boardDAO.getNoticeArticle(boardnum);
		return article;
	}
	
	@Override
	public FreeDTO getFreeArticle(int boardnum) throws SQLException {
		FreeDTO article = boardDAO.getFreeArticle(boardnum);
		return article;
	}
	
	@Override
	public List<FreeCommentDTO> getFreeComment(int boardnum) throws SQLException {
		List<FreeCommentDTO> comment = boardDAO.getFreeComment(boardnum);
		return comment;
	}
	
	@Override
	public String getRef(int boardnum, int code) throws SQLException {
		String ref = boardDAO.getRef(boardnum, code);
		return ref;
	}
	
	@Override
	public int updateNoticeArticle(NoticeDTO dto) throws SQLException {
		int result = boardDAO.updateNoticeArticle(dto);
		return result;
	}
	
	@Override
	public int updateNoticeArticleImg(NoticeDTO dto) throws SQLException {
		int result = boardDAO.updateNoticeArticleImg(dto);
		return result;
	}
	
	@Override
	public int updateFreeArticle(FreeDTO dto) throws SQLException {
		int result = boardDAO.updateFreeArticle(dto);
		return result;
	}
	
	@Override
	public List<BracketsDTO> getBrackets() throws SQLException {
		List<BracketsDTO> categList = boardDAO.getBrackets();
		return categList;
	}
	
	// 이달의 챌린지 글 등록 - 이다희 
	@Override
	public void insertChallenge(MonthDTO dto) throws SQLException {
		boardDAO.insertChallenge(dto);
	}
	// 이달의 챌린지 글 가져오기 - 이다희 
	@Override
	public MonthDTO getChallengeArticle(int num) throws SQLException {
		MonthDTO article = boardDAO.getChallengeArticle(num);
		return article;
	}
	// 이달의 챌린지 수정 1 - 이다희 
	@Override
	public int updateChallengeArticle(MonthDTO dto) throws SQLException {
		int result = boardDAO.updateChallengeArticle(dto);
		return result;
	}
	// 이달의 챌린지 수정 2 - 이다희 
	@Override
	public int updateChallengeArticleImg(MonthDTO dto) throws SQLException {
		int result = boardDAO.updateChallengeArticleImg(dto);
		return result;
	}
	
	@Override
	public String getPw(int boardnum, int code) throws SQLException {
		String hashedPassword = boardDAO.getPw(boardnum, code);
		return hashedPassword;
	}
	
	@Override
	public String getCtt(int boardnum, int code) throws SQLException {
		String ctt = boardDAO.getCtt(boardnum, code);
		return ctt;
	}

	@Override
	public int deleteArticle(int boardnum, int code) throws SQLException {
		int result = boardDAO.deleteArticle(boardnum, code);
		return result;
	}

}
