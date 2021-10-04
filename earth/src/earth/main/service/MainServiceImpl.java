package earth.main.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import earth.board.dto.DiaryDTO;
import earth.board.dto.MonthDTO;
import earth.main.dao.MainDAOImpl;
import earth.main.dto.MessageDTO;

@Service
public class MainServiceImpl implements MainService {
	
	@Autowired
	private MainDAOImpl mainDAO = null;
	
	// 쪽지
		// 받은 쪽지함 목록 가져오기 - 노현호
		@Override
		public Map<String, Object> getMessageList(String id, int code, String pageNum) throws SQLException {
			
			// code 분류
			// 받은 쪽지함 : 1
			// 보낸 쪽지함 : 2
			// 쪽지 보관함 : 3
			// 휴지통 : 4
			
			// 한페이지에 보여줄 쪽지의 수 
			int pageSize = 10; 

			// 현재 페이지 번호  
			if(pageNum == null) pageNum = "1";
			
			// 현재 페이지에 보여줄 게시글 시작과 끝 등등 정보 세팅 
			int currentPage = Integer.parseInt(pageNum); 
			int startRow = (currentPage - 1) * pageSize + 1; 		// 페이지 시작글 번호 
			int endRow = currentPage * pageSize; 					// 페이지 마지막 글번호
			int count = 0; 											// 전체(검색된) 글의 개수 
			int number = 0; 										// 브라우저 화면에 뿌려줄 가상 글 번호
			
			// DB에 저장되어있는 전체 쪽지의 개수를 가져와 담기
			count = mainDAO.getMessageCount(id, code);
			number = count - (currentPage-1) * pageSize; 			// 게시판 목록에 뿌려줄 가상의 글 번호 
			System.out.println("전체 쪽지 수 : " + count);
			
			Map<String, Object> result = new HashMap<String, Object>();
			
			// 전체 쪽지함별 쪽지 개수 가져와 담기
			int countReceive = mainDAO.getMessageCount(id, 1);
			int countSend = mainDAO.getMessageCount(id, 2);
			int countRemind = mainDAO.getMessageCount(id, 3);
			int countDelete = mainDAO.getMessageCount(id, 4);
			
			// 글이 하나라도 있으면 글들을 다시 가져오기
			switch(code) {
			case 1:
				// 받은 쪽지함 - 노현호
				if(count > 0){
					List<MessageDTO> messageList = mainDAO.getReceiveMessages(startRow, endRow, id); 
					result.put("messageList", messageList);
				}
				break;
			case 2:
				// 보낸 쪽지함 - 노현호
				if(count > 0){
					List<MessageDTO> messageList = mainDAO.getSendMessages(startRow, endRow, id); 
					result.put("messageList", messageList);
				}
				break;
			case 3:
				// 쪽지 보관함 - 노현호
				if(count > 0){
					List<MessageDTO> messageList = mainDAO.getRemindMessages(startRow, endRow, id); 
					result.put("messageList", messageList);
				}
				break;
			case 4:
				// 휴지통 - 노현호
				if(count > 0){
					List<MessageDTO> messageList = mainDAO.getDeleteMessages(startRow, endRow, id); 
					result.put("messageList", messageList);
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
			
			result.put("countReceive", countReceive);
			result.put("countSend", countSend);
			result.put("countRemind", countRemind);
			result.put("countDelete", countDelete);
			
			return result;
		}
		
		// 검색된 받은 쪽지함 목록 가져오기 - 노현호
		@Override
		public Map<String, Object> getMessageListSearch(String id, int code, String pageNum, String sel, String search) throws SQLException {
			// 받은 쪽지함 : 1
			// 보낸 쪽지함 : 2
			// 쪽지 보관함 : 3
			
			// 한페이지에 보여줄 쪽지의 수 
			int pageSize = 10; 

			// 현재 페이지 번호  
			if(pageNum == null) pageNum = "1";
			
			// 현재 페이지에 보여줄 게시글 시작과 끝 등등 정보 세팅 
			int currentPage = Integer.parseInt(pageNum); 
			int startRow = (currentPage - 1) * pageSize + 1; 		// 페이지 시작글 번호 
			int endRow = currentPage * pageSize; 					// 페이지 마지막 글번호
			int count = 0; 											// 전체(검색된) 글의 개수 
			int number = 0; 										// 브라우저 화면에 뿌려줄 가상 글 번호
			
			// DB에 저장되어있는 전체 쪽지의 개수를 가져와 담기
			count = mainDAO.getMessageCountSearch(id, code, sel, search);
			number = count - (currentPage-1) * pageSize; 			// 게시판 목록에 뿌려줄 가상의 글 번호 
			System.out.println("검색된 쪽지 수 : " + count);
			
			Map<String, Object> result = new HashMap<String, Object>();
			
			// 전체 쪽지함별 쪽지 개수 가져와 담기
			int countReceive = mainDAO.getMessageCount(id, 1);
			int countSend = mainDAO.getMessageCount(id, 2);
			int countRemind = mainDAO.getMessageCount(id, 3);
			int countDelete = mainDAO.getMessageCount(id, 4);
			
			// 글이 하나라도 있으면 글들을 다시 가져오기
			switch(code) {
			case 1:
				// 받은 쪽지함 - 노현호
				if(count > 0){
					List<MessageDTO> messageList = mainDAO.getReceiveMessagesSearch(startRow, endRow, id, sel, search); 
					result.put("messageList", messageList);
				}
				break;
			case 2:
				// 보낸 쪽지함 - 노현호
				if(count > 0){
					List<MessageDTO> messageList = mainDAO.getSendMessagesSearch(startRow, endRow, id, sel, search); 
					result.put("messageList", messageList);
				}
				break;
			case 3:
				// 쪽지 보관함 - 노현호
				if(count > 0){
					List<MessageDTO> messageList = mainDAO.getRemindMessagesSearch(startRow, endRow, id, sel, search); 
					result.put("messageList", messageList);
				}
				break;
			case 4:
				// 휴지통 - 노현호
				if(count > 0){
					List<MessageDTO> messageList = mainDAO.getDeleteMessages(startRow, endRow, id); 
					result.put("messageList", messageList);
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
			
			result.put("countReceive", countReceive);
			result.put("countSend", countSend);
			result.put("countRemind", countRemind);
			result.put("countDelete", countDelete);
			
			return result;
		}
		
		// 쪽지 조회 - 노현호
		@Override
		public MessageDTO getMessage(int messagenum) throws SQLException {
			MessageDTO message = mainDAO.getMessage(messagenum);
			return message;
		}
		
		// 쪽지 보내기 - 노현호
		@Override
		public int sendMessage(MessageDTO dto) throws SQLException {
			int result = mainDAO.sendMessage(dto);
			return result;
		}
		
		// 쪽지 보관함 등록 - 노현호
		@Override
		public void regRemind(int messagenum) throws SQLException {
			mainDAO.regRemind(messagenum);
		}
		
		// 쪽지 보관함 등록 해제 - 노현호
		@Override
		public void delRemind(int messagenum) throws SQLException {
			mainDAO.delRemind(messagenum);
		}
		
		// 받은 쪽지 삭제(미표시 처리) - 노현호
		@Override
		public void deleteReceiveMessage(int messagenum) throws SQLException {
			mainDAO.deleteReceiveMessage(messagenum);
		}
		
		// 보낸 쪽지 삭제(미표시 처리) - 노현호
		@Override
		public void deleteSendMessage(int messagenum) throws SQLException {
			mainDAO.deleteSendMessage(messagenum);
		}
		
		// 휴지통 비우기 - 노현호
		@Override
		public void deleteMessage(String id) throws SQLException{
			mainDAO.deleteMessage(id);
		}
		
		// 휴지통 복구 - 노현호
		@Override
		public void reviveMessage(int messagenum) throws SQLException{
			mainDAO.reviveMessage(messagenum);
		}

		
		
		
		//메인에서 이달의 챌린지 하나 가져오기 - 김하영
		@Override
		public Map<String, Object> getMainList(String pageNum) throws SQLException {
			int pageSize = 1; 
			
			if(pageNum == null) pageNum = "1";
			
			// 현재 페이지에 보여줄 게시글 시작과 끝 등등 정보 세팅 
			int currentPage = Integer.parseInt(pageNum); 
			int startRow = (currentPage - 1) * pageSize + 1; 		// 페이지 시작글 번호 
			int endRow = currentPage * pageSize; 					// 페이지 마지막 글번호
			int count = 0; 											// 전체(검색된) 글의 개수 
			int number = 0; 						// 브라우저 화면에 뿌려줄 가상 글 번호  

			count = mainDAO.getMainCount(pageNum);					
			System.out.println("count : " + count);
			
			List<MonthDTO> mainList = null;  	

			if(count > 0){
				mainList = mainDAO.getMainArticles(startRow, endRow);  
			}
			
			number = count - (currentPage-1) * pageSize; 
			Map<String, Object> result = new HashMap<>(); 
			result.put("pageSize", pageSize);
			result.put("pageNum", pageNum);
			result.put("currentPage", currentPage);
			result.put("startRow", startRow);
			result.put("endRow", endRow);
			result.put("count", count);
			result.put("number", number);
			result.put("mainList", mainList);
			
			
			return result;
		}

		@Override
		public Map<String, Object> getDiaryList(String pageNum) throws SQLException {
			int pageSize = 3; 
			
			if(pageNum == null) pageNum = "1";
			
			int currentPage = Integer.parseInt(pageNum); 
			int startRow = (currentPage - 1) * pageSize + 1; 		// 페이지 시작글 번호 
			int endRow = currentPage * pageSize; 					// 페이지 마지막 글번호
			int count = 0; 											// 전체(검색된) 글의 개수 
			int number = 0; 						// 브라우저 화면에 뿌려줄 가상 글 번호  
			// DB에 저장되어있는 전체 글의 개수를 가져와 담기
			count = mainDAO.getDiaryCount(pageNum);					
			System.out.println("count : " + count);
			
			List<DiaryDTO> diaryList = null;  	

			if(count > 0){
				diaryList = mainDAO.getDiaryArticles(startRow, endRow);  
			}
			
			number = count - (currentPage-1) * pageSize; 
			Map<String, Object> result = new HashMap<>(); 
			result.put("pageSize", pageSize);
			result.put("pageNum", pageNum);
			result.put("currentPage", currentPage);
			result.put("startRow", startRow);
			result.put("endRow", endRow);
			result.put("count", count);
			result.put("number", number);
			result.put("diaryList", diaryList);
			
			
			return result;
		}
		@Override
		public String getNickname(String id) throws SQLException {
			String nickname = mainDAO.getNickname(id);
			return nickname;
		}
		@Override
		public String getBadgeimg(String id) throws SQLException {
			String badgeimg = mainDAO.getBadgeimg(id);
			return badgeimg;
		}
}
