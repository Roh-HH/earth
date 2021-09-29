package earth.admin.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import earth.admin.dao.AdminDAOImpl;
import earth.admin.dto.AdminBoardDTO;
import earth.admin.dto.AdminCommentDTO;
import earth.admin.dto.AdminQuestionDTO;
import earth.user.dto.ReportDTO;
import earth.user.dto.UserDTO;

import earth.board.dto.NoticeDTO;

@Service
public class AdminServiceImpl implements AdminService{

		@Autowired
		private AdminDAOImpl adminDAO = null;

		@Override
		public Map<String, Object> getUserList(String pageNum) throws SQLException {
			
			int pageSize = 10; 
			
			
			
			int currentPage = Integer.parseInt(pageNum);  
			int startRow = (currentPage - 1) * pageSize + 1; 
			int endRow = currentPage * pageSize; 

			
			List<UserDTO> UserList = null;  	
			int count = 0; 				
			int number = 0; 			
			
			count = adminDAO.getUserCount();   
			System.out.println("count : " + count);
		
			if(count > 0){
				UserList = adminDAO.getUser(startRow, endRow); 
			}	
			number = count - (currentPage-1) * pageSize; 	
			
			
			Map<String, Object> result = new HashMap();
			result.put("pageSize", pageSize);
			result.put("pageNum", pageNum);
			result.put("currentPage", currentPage);
			result.put("startRow", startRow);
			result.put("endRow", endRow);
			result.put("UserList", UserList);
			result.put("count", count);
			result.put("number", number);
			
			return result;
		}

		@Override
		public Map<String, Object> getUserSearch(String pageNum, String sel, String search) throws SQLException {
			
			int pageSize = 10; 
			
			
				
			int currentPage = Integer.parseInt(pageNum); 
			int startRow = (currentPage - 1) * pageSize + 1;
			int endRow = currentPage * pageSize; 
						
			
			List<UserDTO> UserList = null;  	
			int count = 0; 				
			int number = 0; 			
					
			count = adminDAO.getSearchUserCount(sel, search);
			System.out.println("검색 count : " + count);
			// 검색한 글이 하나라도 있으면 검색한 글 가져오기 
			if(count > 0){
				UserList = adminDAO.getSearchUser(startRow, endRow, sel, search);  
			}
					
			number = count - (currentPage-1) * pageSize; 	
					
			Map<String, Object> result = new HashMap();
			
			result.put("pageSize", pageSize);
			result.put("pageNum", pageNum);
			result.put("currentPage", currentPage);
			result.put("startRow", startRow);
			result.put("endRow", endRow);
			result.put("UserList", UserList);
			result.put("count", count);
			result.put("number", number);
					
			return result;
		}

		@Override
		public void deleteUser(String id) throws SQLException {
			
			adminDAO.deleteUser(id);
			
		}
		
		
		@Override
		public Map<String, Object> getBoardList(String pageNum) throws SQLException {
			
			int pageSize = 10; 
			
			int currentPage = Integer.parseInt(pageNum);  
			int startRow = (currentPage - 1) * pageSize + 1; 
			int endRow = currentPage * pageSize; 

			
			List<AdminBoardDTO> BoardList = null;  	
			int count = 0; 				
			int number = 0; 			 
			
			count = adminDAO.getBoardCount();  
			System.out.println("count : " + count);
		
			if(count > 0){
				BoardList = adminDAO.getBoard(startRow, endRow); 
			}	
			number = count - (currentPage-1) * pageSize; 	
			
			Map<String, Object> result = new HashMap();
			result.put("pageSize", pageSize);
			result.put("pageNum", pageNum);
			result.put("currentPage", currentPage);
			result.put("startRow", startRow);
			result.put("endRow", endRow);
			result.put("BoardList", BoardList);
			result.put("count", count);
			result.put("number", number);
			
			return result;
		}

		@Override
		public Map<String, Object> getBoardSearchList(String pageNum, String sel, String search) throws SQLException {
			
			int pageSize = 10; 
			

			int currentPage = Integer.parseInt(pageNum);
			int startRow = (currentPage - 1) * pageSize + 1; 
			int endRow = currentPage * pageSize; 
						
			List<AdminBoardDTO> BoardList = null;  
			int count = 0; 				
			int number = 0; 			
					
			count = adminDAO.getSearchBoardCount(sel, search); 			
			
			System.out.println("\n\n\n\n\n\n\n검색 count : " + count);
			if(count > 0){
				BoardList = adminDAO.getSearchBoard(startRow, endRow, sel, search);  
			}
					
			number = count - (currentPage-1) * pageSize; 	
					
			Map<String, Object> result = new HashMap();
			result.put("pageSize", pageSize);
			result.put("pageNum", pageNum);
			result.put("currentPage", currentPage);
			result.put("startRow", startRow);
			result.put("endRow", endRow);
			result.put("BoardList", BoardList);
			result.put("count", count);
			result.put("number", number);
					
			return result;
			
			
		}
		
		@Override
		public void deleteFree(String num) throws SQLException {
			
			adminDAO.deleteFree(num);
			
		}
		
		@Override
		public void deleteDiary(String num) throws SQLException {
			
			adminDAO.deleteDiary(num);
			
		}

		@Override
		public Map<String, Object> getCommentList(String pageNum) throws SQLException {
			
			int pageSize = 10; 
			
			int currentPage = Integer.parseInt(pageNum);  
			int startRow = (currentPage - 1) * pageSize + 1; 
			int endRow = currentPage * pageSize; 

			
			List<AdminCommentDTO> commentList = null;  	
			int count = 0; 				
			int number = 0; 			
			
			count = adminDAO.getCommentCount();  
			System.out.println("count : " + count);

			if(count > 0){
				commentList = adminDAO.getComment(startRow, endRow); 
			}	
			number = count - (currentPage-1) * pageSize; 	
			
			Map<String, Object> result = new HashMap();
			result.put("pageSize", pageSize);
			result.put("pageNum", pageNum);
			result.put("currentPage", currentPage);
			result.put("startRow", startRow);
			result.put("endRow", endRow);
			result.put("commentList", commentList);
			result.put("count", count);
			result.put("number", number);
			
			return result;
			
		}

		@Override
		public Map<String, Object> getCommentSearch(String pageNum, String sel, String search) throws SQLException {
			
			int pageSize = 10; 
			 
			if(pageNum == null){
				pageNum = "1";
			}
					
			int currentPage = Integer.parseInt(pageNum); 
			int startRow = (currentPage - 1) * pageSize + 1; 
			int endRow = currentPage * pageSize; 
						
			List<AdminCommentDTO> commentList = null;  	
			int count = 0; 				
			int number = 0; 			
					
			count = adminDAO.getSearchCommentCount(sel, search); 
			System.out.println("검색 count : " + count);

			if(count > 0){
				commentList = adminDAO.getSearchComment(startRow, endRow, sel, search);  
			}
					
			number = count - (currentPage-1) * pageSize; 	
					
			Map<String, Object> result = new HashMap();
			result.put("pageSize", pageSize);
			result.put("pageNum", pageNum);
			result.put("currentPage", currentPage);
			result.put("startRow", startRow);
			result.put("endRow", endRow);
			result.put("commentList", commentList);
			result.put("count", count);
			result.put("number", number);
					
			return result;
			
		}
		
		@Override
		public void deleteComment(String num, String code) throws SQLException {
			
			adminDAO.deleteComment(num, code);
			
		}

		@Override
		public Map<String, Object> getQuestionList(String pageNum) throws SQLException {
			int pageSize = 10; 

			if(pageNum == null){
				pageNum = "1";
			}
					
			
			int currentPage = Integer.parseInt(pageNum); 
			int startRow = (currentPage - 1) * pageSize + 1; 
			int endRow = currentPage * pageSize; 
						
			
			List<AdminQuestionDTO> questionList = null;  	
			int count = 0; 				
			int number = 0; 			 
					
			count = adminDAO.getQuestionCount(); 
			System.out.println("검색 count : " + count);
			 
			if(count > 0){
				questionList = adminDAO.getQuestion(startRow, endRow);  
			}
					
			number = count - (currentPage-1) * pageSize; 	
					
			
			Map<String, Object> result = new HashMap();
			result.put("pageSize", pageSize);
			result.put("pageNum", pageNum);
			result.put("currentPage", currentPage);
			result.put("startRow", startRow);
			result.put("endRow", endRow);
			result.put("questionList", questionList);
			result.put("count", count);
			result.put("number", number);
					
			return result;
		}

		@Override
		public Map<String, Object> getQuestionSearch(String pageNum, String sel, String search) throws SQLException {
			
			int pageSize = 10; 
	 
			if(pageNum == null){ 
				pageNum = "1";
			}
					
		
			int currentPage = Integer.parseInt(pageNum);  
			int startRow = (currentPage - 1) * pageSize + 1; 
			int endRow = currentPage * pageSize; 
						
			List<AdminQuestionDTO> questionList = null;  	
			int count = 0; 				 
			int number = 0; 			
					
			count = adminDAO.getSearchQuestionCount(sel, search); 
			System.out.println("검색 count : " + count);
			
			if(count > 0){
				questionList = adminDAO.getSearchQuestion(startRow, endRow, sel, search);  
			}
					
			number = count - (currentPage-1) * pageSize; 	
					
			Map<String, Object> result = new HashMap();
			result.put("pageSize", pageSize);
			result.put("pageNum", pageNum);
			result.put("currentPage", currentPage);
			result.put("startRow", startRow);
			result.put("endRow", endRow);
			result.put("questionList", questionList);
			result.put("count", count);
			result.put("number", number);
					
			return result;
		}
		
		@Override
		public void deleteQuestion(String num) throws SQLException {
			
			adminDAO.deleteQuestion(num);
			
		}
		
		// 작성자 : 이영인
		// 1:1 문의 답변하기
		@Override
		public AdminQuestionDTO getQnAOne(int questionnum) throws SQLException {
			AdminQuestionDTO question = adminDAO.getQnAOne(questionnum);
			return question;
		}

		@Override
		public void addQnAReply(AdminQuestionDTO dto) throws SQLException {
			adminDAO.addQnAReply(dto);
		} 
			
		
		
		@Override
		public Map<String, Object> getNoticeList(String pageNum) throws SQLException {
			
			int pageSize = 10; 
			
			if(pageNum == null){ 
				pageNum = "1";
			}
					
			int currentPage = Integer.parseInt(pageNum); 
			int startRow = (currentPage - 1) * pageSize + 1; 
			int endRow = currentPage * pageSize; 
						
			
			List<NoticeDTO> noticeList = null;  	
			int count = 0; 				 
			int number = 0; 			
					
			count = adminDAO.getNoticeCount();
			System.out.println("검색 count : " + count);
			if(count > 0){
				noticeList = adminDAO.getNotice(startRow, endRow);  
			}
					
			number = count - (currentPage-1) * pageSize; 	
					

			Map<String, Object> result = new HashMap();
			result.put("pageSize", pageSize);
			result.put("pageNum", pageNum);
			result.put("currentPage", currentPage);
			result.put("startRow", startRow);
			result.put("endRow", endRow);
			result.put("noticeList", noticeList);
			result.put("count", count);
			result.put("number", number);
					
			return result;
		}

		@Override
		public Map<String, Object> getNoticeSearch(String pageNum, String sel, String search) throws SQLException {
			
			int pageSize = 10; 
			
			if(pageNum == null){ 
				pageNum = "1";
			}
					
			
			int currentPage = Integer.parseInt(pageNum); 
			int startRow = (currentPage - 1) * pageSize + 1; 
			int endRow = currentPage * pageSize;
						
			List<NoticeDTO> noticeList = null;  	
			int count = 0; 				
			int number = 0; 			
					
			count = adminDAO.getSearchNoticeCount(sel, search); 
			System.out.println("검색 count : " + count);

			if(count > 0){
				noticeList = adminDAO.getSearchNotice(startRow, endRow, sel, search);  
			}
					
			number = count - (currentPage-1) * pageSize; 	
					
			Map<String, Object> result = new HashMap();
			result.put("pageSize", pageSize);
			result.put("pageNum", pageNum);
			result.put("currentPage", currentPage);
			result.put("startRow", startRow);
			result.put("endRow", endRow);
			result.put("noticeList", noticeList);
			result.put("count", count);
			result.put("number", number);
					
			return result;
		}

	// 신고 기능
	@Override
	public int insertReport(ReportDTO report) throws SQLException {
		int result = adminDAO.insertReport(report);
		return result;
	}
	
	@Override
	public ReportDTO getReport(int reportnum) throws SQLException {
		ReportDTO report = adminDAO.getReport(reportnum);
		return report;
	}

	@Override
	public Map<String, Object> getReportList(String pageNum, String process) throws SQLException {

		int pageSize = 10; 

		int currentPage = Integer.parseInt(pageNum);  
		int startRow = (currentPage - 1) * pageSize + 1; 
		int endRow = currentPage * pageSize; 

		List<ReportDTO> reportList = null;
		int count = 0; 
		int number = 0;  

		count = adminDAO.getReportCount(process); 
		if(count > 0){
			reportList = adminDAO.getReport(process, startRow, endRow); 
		}	
		number = count - (currentPage-1) * pageSize; 

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("pageSize", pageSize);
		result.put("pageNum", pageNum);
		result.put("currentPage", currentPage);
		result.put("startRow", startRow);
		result.put("endRow", endRow);
		result.put("reportList", reportList);
		result.put("count", count);
		result.put("number", number);

		return result;
	}

	@Override
	public Map<String, Object> getReportSearch(String pageNum, String process, String sel, String search) throws SQLException {
		int pageSize = 10; 

		if(pageNum == null){
			pageNum = "1";
		}

		int currentPage = Integer.parseInt(pageNum); 
		int startRow = (currentPage - 1) * pageSize + 1; 
		int endRow = currentPage * pageSize;

		List<ReportDTO> reportList = null;
		int count = 0; 
		int number = 0;  

		count = adminDAO.getSearchReportCount(process, sel, search);
		if(count > 0){
			reportList = adminDAO.getReportSearch(process, startRow, endRow, sel, search);  
		}

		number = count - (currentPage-1) * pageSize; 

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("pageSize", pageSize);
		result.put("pageNum", pageNum);
		result.put("currentPage", currentPage);
		result.put("startRow", startRow);
		result.put("endRow", endRow);
		result.put("reportList", reportList);
		result.put("count", count);
		result.put("number", number);

		return result;
	}

	@Override
	public int processReport(String id, int reportnum, int punish) throws SQLException {
		int result = adminDAO.processReport(id, reportnum, punish);
		return result;
	}

	
}
