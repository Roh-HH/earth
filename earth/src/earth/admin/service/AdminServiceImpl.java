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
import earth.admin.dto.NoticeDTO;
import earth.user.dto.UserDTO;

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

			
			List<UserDTO> UserList = null;  	// 전체(검색된) 게시글들 담아줄 변수
			int count = 0; 				// 전체(검색된) 글의 개수 
			int number = 0; 			// 브라우저 화면에 뿌려줄 가상 글 번호  
			
			count = adminDAO.getUserCount();   // DB에 저장되어있는 전체 글의 개수를 가져와 담기
			System.out.println("count : " + count);
			// 글이 하나라도 있으면 글들을 다시 가져오기 
			if(count > 0){
				UserList = adminDAO.getUser(startRow, endRow); 
			}	
			number = count - (currentPage-1) * pageSize; 	// 게시판 목록에 뿌려줄 가상의 글 번호 
			
			// Controller 에게 전달해야 되는 데이터가 많으니, HashMap에 넘겨 줄 데이터를 저장한 후 한번에 전달하기
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
			// 현재 페이지 번호  
			
					
			// 현재 페이지에 보여줄 게시글 시작과 끝 등등 정보 세팅 
			int currentPage = Integer.parseInt(pageNum); // 계산을 위해 현재페이지 숫자로 변환하여 저장 
			int startRow = (currentPage - 1) * pageSize + 1; // 페이지 시작글 번호 
			int endRow = currentPage * pageSize; // 페이지 마지막 글번호
						
			// 밖에서 사용가능하게 if문 시작 전에 미리 선언
			List<UserDTO> UserList = null;  	// 전체(검색된) 게시글들 담아줄 변수
			int count = 0; 				// 전체(검색된) 글의 개수 
			int number = 0; 			// 브라우저 화면에 뿌려줄 가상 글 번호  
					
			count = adminDAO.getSearchUserCount(sel, search); // 검색된 글의 총 개수 가져오기 
			System.out.println("검색 count : " + count);
			// 검색한 글이 하나라도 있으면 검색한 글 가져오기 
			if(count > 0){
				UserList = adminDAO.getSearchUser(startRow, endRow, sel, search);  
			}
					
			number = count - (currentPage-1) * pageSize; 	// 게시판 목록에 뿌려줄 가상의 글 번호 
					
			// Controller 에게 전달해야 되는 데이터가 많으니, HashMap에 넘겨 줄 데이터를 저장한 후 한번에 전달하기
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
		public Map<String, Object> getBoardList(String pageNum) throws SQLException {
			
			int pageSize = 10; 
			
			int currentPage = Integer.parseInt(pageNum);  
			int startRow = (currentPage - 1) * pageSize + 1; 
			int endRow = currentPage * pageSize; 

			
			List<AdminBoardDTO> BoardList = null;  	// 전체(검색된) 게시글들 담아줄 변수
			int count = 0; 				// 전체(검색된) 글의 개수 
			int number = 0; 			// 브라우저 화면에 뿌려줄 가상 글 번호  
			
			count = adminDAO.getBoardCount();   // DB에 저장되어있는 전체 글의 개수를 가져와 담기
			System.out.println("count : " + count);
			// 글이 하나라도 있으면 글들을 다시 가져오기 
			if(count > 0){
				BoardList = adminDAO.getBoard(startRow, endRow); 
			}	
			number = count - (currentPage-1) * pageSize; 	// 게시판 목록에 뿌려줄 가상의 글 번호 
			
			// Controller 에게 전달해야 되는 데이터가 많으니, HashMap에 넘겨 줄 데이터를 저장한 후 한번에 전달하기
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
			// 현재 페이지 번호  
					
			// 현재 페이지에 보여줄 게시글 시작과 끝 등등 정보 세팅 
			int currentPage = Integer.parseInt(pageNum); // 계산을 위해 현재페이지 숫자로 변환하여 저장 
			int startRow = (currentPage - 1) * pageSize + 1; // 페이지 시작글 번호 
			int endRow = currentPage * pageSize; // 페이지 마지막 글번호
						
			// 밖에서 사용가능하게 if문 시작 전에 미리 선언
			List<AdminBoardDTO> BoardList = null;  	// 전체(검색된) 게시글들 담아줄 변수
			int count = 0; 				// 전체(검색된) 글의 개수 
			int number = 0; 			// 브라우저 화면에 뿌려줄 가상 글 번호  
					
			count = adminDAO.getSearchBoardCount(sel, search); // 검색된 글의 총 개수 가져오기 
			System.out.println("검색 count : " + count);
			// 검색한 글이 하나라도 있으면 검색한 글 가져오기 
			if(count > 0){
				BoardList = adminDAO.getSearchBoard(startRow, endRow, sel, search);  
			}
					
			number = count - (currentPage-1) * pageSize; 	// 게시판 목록에 뿌려줄 가상의 글 번호 
					
			// Controller 에게 전달해야 되는 데이터가 많으니, HashMap에 넘겨 줄 데이터를 저장한 후 한번에 전달하기
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
		public void deleteUser(String id) throws SQLException {
			
			adminDAO.deleteUser(id);
			
		}

		@Override
		public Map<String, Object> getCommentList(String pageNum) throws SQLException {
			
			int pageSize = 10; 
			
			int currentPage = Integer.parseInt(pageNum);  
			int startRow = (currentPage - 1) * pageSize + 1; 
			int endRow = currentPage * pageSize; 

			
			List<AdminCommentDTO> commentList = null;  	// 전체(검색된) 게시글들 담아줄 변수
			int count = 0; 				// 전체(검색된) 글의 개수 
			int number = 0; 			// 브라우저 화면에 뿌려줄 가상 글 번호  
			
			count = adminDAO.getCommentCount();   // DB에 저장되어있는 전체 글의 개수를 가져와 담기
			System.out.println("count : " + count);
			// 글이 하나라도 있으면 글들을 다시 가져오기 
			if(count > 0){
				commentList = adminDAO.getComment(startRow, endRow); 
			}	
			number = count - (currentPage-1) * pageSize; 	// 게시판 목록에 뿌려줄 가상의 글 번호 
			
			// Controller 에게 전달해야 되는 데이터가 많으니, HashMap에 넘겨 줄 데이터를 저장한 후 한번에 전달하기
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
			// 현재 페이지 번호  
			if(pageNum == null){ //pageNum 파라미터 안넘어왔을때.
				pageNum = "1";
			}
					
			// 현재 페이지에 보여줄 게시글 시작과 끝 등등 정보 세팅 
			int currentPage = Integer.parseInt(pageNum); // 계산을 위해 현재페이지 숫자로 변환하여 저장 
			int startRow = (currentPage - 1) * pageSize + 1; // 페이지 시작글 번호 
			int endRow = currentPage * pageSize; // 페이지 마지막 글번호
						
			// 밖에서 사용가능하게 if문 시작 전에 미리 선언
			List<AdminCommentDTO> commentList = null;  	// 전체(검색된) 게시글들 담아줄 변수
			int count = 0; 				// 전체(검색된) 글의 개수 
			int number = 0; 			// 브라우저 화면에 뿌려줄 가상 글 번호  
					
			count = adminDAO.getSearchCommentCount(sel, search); // 검색된 글의 총 개수 가져오기 
			System.out.println("검색 count : " + count);
			// 검색한 글이 하나라도 있으면 검색한 글 가져오기 
			if(count > 0){
				commentList = adminDAO.getSearchComment(startRow, endRow, sel, search);  
			}
					
			number = count - (currentPage-1) * pageSize; 	// 게시판 목록에 뿌려줄 가상의 글 번호 
					
			// Controller 에게 전달해야 되는 데이터가 많으니, HashMap에 넘겨 줄 데이터를 저장한 후 한번에 전달하기
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
		public Map<String, Object> getQuestionList(String pageNum) throws SQLException {
			int pageSize = 10; 
			// 현재 페이지 번호  
			if(pageNum == null){ //pageNum 파라미터 안넘어왔을때.
				pageNum = "1";
			}
					
			// 현재 페이지에 보여줄 게시글 시작과 끝 등등 정보 세팅 
			int currentPage = Integer.parseInt(pageNum); // 계산을 위해 현재페이지 숫자로 변환하여 저장 
			int startRow = (currentPage - 1) * pageSize + 1; // 페이지 시작글 번호 
			int endRow = currentPage * pageSize; // 페이지 마지막 글번호
						
			// 밖에서 사용가능하게 if문 시작 전에 미리 선언
			List<AdminQuestionDTO> questionList = null;  	// 전체(검색된) 게시글들 담아줄 변수
			int count = 0; 				// 전체(검색된) 글의 개수 
			int number = 0; 			// 브라우저 화면에 뿌려줄 가상 글 번호  
					
			count = adminDAO.getQuestionCount(); // 검색된 글의 총 개수 가져오기 
			System.out.println("검색 count : " + count);
			// 검색한 글이 하나라도 있으면 검색한 글 가져오기 
			if(count > 0){
				questionList = adminDAO.getQuestion(startRow, endRow);  
			}
					
			number = count - (currentPage-1) * pageSize; 	// 게시판 목록에 뿌려줄 가상의 글 번호 
					
			// Controller 에게 전달해야 되는 데이터가 많으니, HashMap에 넘겨 줄 데이터를 저장한 후 한번에 전달하기
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
			// 현재 페이지 번호  
			if(pageNum == null){ //pageNum 파라미터 안넘어왔을때.
				pageNum = "1";
			}
					
			// 현재 페이지에 보여줄 게시글 시작과 끝 등등 정보 세팅 
			int currentPage = Integer.parseInt(pageNum); // 계산을 위해 현재페이지 숫자로 변환하여 저장 
			int startRow = (currentPage - 1) * pageSize + 1; // 페이지 시작글 번호 
			int endRow = currentPage * pageSize; // 페이지 마지막 글번호
						
			// 밖에서 사용가능하게 if문 시작 전에 미리 선언
			List<AdminQuestionDTO> questionList = null;  	// 전체(검색된) 게시글들 담아줄 변수
			int count = 0; 				// 전체(검색된) 글의 개수 
			int number = 0; 			// 브라우저 화면에 뿌려줄 가상 글 번호  
					
			count = adminDAO.getSearchQuestionCount(sel, search); // 검색된 글의 총 개수 가져오기 
			System.out.println("검색 count : " + count);
			// 검색한 글이 하나라도 있으면 검색한 글 가져오기 
			if(count > 0){
				questionList = adminDAO.getSearchQuestion(startRow, endRow, sel, search);  
			}
					
			number = count - (currentPage-1) * pageSize; 	// 게시판 목록에 뿌려줄 가상의 글 번호 
					
			// Controller 에게 전달해야 되는 데이터가 많으니, HashMap에 넘겨 줄 데이터를 저장한 후 한번에 전달하기
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
		public Map<String, Object> getNoticeList(String pageNum) throws SQLException {
			
			int pageSize = 10; 
			// 현재 페이지 번호  
			if(pageNum == null){ //pageNum 파라미터 안넘어왔을때.
				pageNum = "1";
			}
					
			// 현재 페이지에 보여줄 게시글 시작과 끝 등등 정보 세팅 
			int currentPage = Integer.parseInt(pageNum); // 계산을 위해 현재페이지 숫자로 변환하여 저장 
			int startRow = (currentPage - 1) * pageSize + 1; // 페이지 시작글 번호 
			int endRow = currentPage * pageSize; // 페이지 마지막 글번호
						
			// 밖에서 사용가능하게 if문 시작 전에 미리 선언
			List<NoticeDTO> noticeList = null;  	// 전체(검색된) 게시글들 담아줄 변수
			int count = 0; 				// 전체(검색된) 글의 개수 
			int number = 0; 			// 브라우저 화면에 뿌려줄 가상 글 번호  
					
			count = adminDAO.getNoticeCount(); // 검색된 글의 총 개수 가져오기 
			System.out.println("검색 count : " + count);
			// 검색한 글이 하나라도 있으면 검색한 글 가져오기 
			if(count > 0){
				noticeList = adminDAO.getNotice(startRow, endRow);  
			}
					
			number = count - (currentPage-1) * pageSize; 	// 게시판 목록에 뿌려줄 가상의 글 번호 
					
			// Controller 에게 전달해야 되는 데이터가 많으니, HashMap에 넘겨 줄 데이터를 저장한 후 한번에 전달하기
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
			// 현재 페이지 번호  
			if(pageNum == null){ //pageNum 파라미터 안넘어왔을때.
				pageNum = "1";
			}
					
			// 현재 페이지에 보여줄 게시글 시작과 끝 등등 정보 세팅 
			int currentPage = Integer.parseInt(pageNum); // 계산을 위해 현재페이지 숫자로 변환하여 저장 
			int startRow = (currentPage - 1) * pageSize + 1; // 페이지 시작글 번호 
			int endRow = currentPage * pageSize; // 페이지 마지막 글번호
						
			// 밖에서 사용가능하게 if문 시작 전에 미리 선언
			List<NoticeDTO> noticeList = null;  	// 전체(검색된) 게시글들 담아줄 변수
			int count = 0; 				// 전체(검색된) 글의 개수 
			int number = 0; 			// 브라우저 화면에 뿌려줄 가상 글 번호  
					
			count = adminDAO.getSearchNoticeCount(sel, search); // 검색된 글의 총 개수 가져오기 
			System.out.println("검색 count : " + count);
			// 검색한 글이 하나라도 있으면 검색한 글 가져오기 
			if(count > 0){
				noticeList = adminDAO.getSearchNotice(startRow, endRow, sel, search);  
			}
					
			number = count - (currentPage-1) * pageSize; 	// 게시판 목록에 뿌려줄 가상의 글 번호 
					
			// Controller 에게 전달해야 되는 데이터가 많으니, HashMap에 넘겨 줄 데이터를 저장한 후 한번에 전달하기
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