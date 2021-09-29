package earth.badge.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import earth.badge.dao.BadgeDAOImpl;
import earth.badge.dto.BadgeDTO;
import earth.user.dto.MybagDTO;

@Service
public class BadgeServiceImpl implements BadgeService {

	@Autowired
	private BadgeDAOImpl badgeDAO = null;
	
	// 뱃지샵 목록 불러오기
	@Override
	public Map<String, Object> getBadgeList(String pageNum, String filter) throws SQLException {
		
		// 한 페이지에서 보여줄 뱃지 갯수 
		int pageSize = 9;
		
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
		count = badgeDAO.getBadgeCount();					
		number = count - (currentPage-1) * pageSize; 			// 게시판 목록에 뿌려줄 가상의 글 번호 
		System.out.println("등록된 뱃지 개수 : " + count);
		
		Map<String, Object> result = new HashMap<String, Object>();

		if(count > 0){
			List<BadgeDTO> articleList = badgeDAO.getBadgeArticles(startRow, endRow, filter); 
			result.put("articleList", articleList);
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
	
	// 뱃지 하나 DTO 불러오기
	@Override
	public BadgeDTO getBadge(int num) throws SQLException {
		
		BadgeDTO result = badgeDAO.getBadge(num);
		
		return result;
	}
	
	// 뱃지 구매 완료
	@Override
	public int buyBadge(MybagDTO dto,String id) throws SQLException {
		
		int result = badgeDAO.buyBadge(dto,id); 
		
		return result;
	}
	
	// 뱃지 추가 (관리자)
	@Override
	public void addBadge(BadgeDTO dto) throws SQLException {
	
		badgeDAO.addBadge(dto);
	}
	
	
	// 뱃지 포인트 불러오기
	@Override
	public int getPoint(String id) throws SQLException {
	
		int point = badgeDAO.getPoint(id);
		
		return point;
	}
	
	// 뱃지 중복체크
	@Override
	public int checkBadge(int num, String id) throws SQLException {
		
		int result = badgeDAO.checkBadge(num,id);
		
		return result;
	}
	
	
}
