package earth.main.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import earth.board.dto.DiaryDTO;
import earth.board.service.BoardServiceImpl;
import earth.main.dto.MessageDTO;
import earth.main.service.MainServiceImpl;

@Controller
@RequestMapping("/main/*")
//@SuppressWarnings("deprecation")
public class MainController {
	
		@Autowired
		private MainServiceImpl mainService = null;
	    
		
		// 메인페이지 - 김하영
		@RequestMapping("main.et")
		public String main(String pageNum, Model model, HttpSession session)throws SQLException {
			System.out.println("main의 이달의 챌린지 요청");
			
			Map<String, Object>result = null;
			result = mainService.getMainList(pageNum);
			
			Map<String, Object>article = null;
			article = mainService.getDiaryList(pageNum);
			
			// 리스트 아이디 닉네임으로 변경, 리스트 뱃지이미지 
			if (article.get("diaryList") != null) {
				List<DiaryDTO> diaryList = (List<DiaryDTO>)article.get("diaryList");
				for(int i=0; i<diaryList.size(); i++) {
					diaryList.get(i).setNickname(mainService.getNickname(diaryList.get(i).getId()));
					diaryList.get(i).setBadgeimg(mainService.getBadgeimg(diaryList.get(i).getId()));
				}
			}
			
			// view에 전달할 데이터 보내기 
			model.addAttribute("pageSize", result.get("pageSize"));
			model.addAttribute("pageNum", result.get("pageNum"));
			model.addAttribute("currentPage", result.get("currentPage"));
			model.addAttribute("startRow", result.get("startRow"));
			model.addAttribute("endRow", result.get("endRow"));
			model.addAttribute("mainList", result.get("mainList"));
			model.addAttribute("count", result.get("count"));
			model.addAttribute("number", result.get("number"));
			model.addAttribute("diaryList", article.get("diaryList"));
			
			
			
			
			
			return "main/main";
		}

		// 쪽지
			// 받은 쪽지함 요청 - 노현호
			@RequestMapping("messageReceiveList.et")
			public String messageReceiveList(String pageNum, Model model, HttpSession session, String sel, String search) throws SQLException {
				System.out.println("messageReceiveList.et");
				
				// 받은 쪽지함 : 1
				// 보낸 쪽지함 : 2
				// 쪽지 보관함 : 3
				
				if(session.getAttribute("sid")==null) {
					System.out.println("비로그인 상태로 쪽지함에 접근함");
					return "user/loginForm";
				}
				
				int code = 1;
				String id = (String)session.getAttribute("sid");
				model.addAttribute("id", id);
				
				// message 테이블에서 receiver가 id인 데이터 전부 가져오기
				Map<String, Object> result = null;
				if(sel == null || search == null) {
					// 검색하지 않은 경우
					result = mainService.getMessageList(id, code, pageNum);
				}else {
					// 검색한 경우
					result = mainService.getMessageListSearch(id, code, pageNum, sel, search);		
				}
				
				// view 에 전달할 데이터 보내기
				model.addAttribute("pageSize", result.get("pageSize"));
				model.addAttribute("pageNum", result.get("pageNum"));
				model.addAttribute("currentPage", result.get("currentPage"));
				model.addAttribute("startRow", result.get("startRow"));
				model.addAttribute("endRow", result.get("endRow"));
				model.addAttribute("messageList", result.get("messageList"));
				model.addAttribute("count", result.get("count"));
				model.addAttribute("number", result.get("number"));
				model.addAttribute("sel", sel);
				model.addAttribute("search", search);
				
				model.addAttribute("countReceive", result.get("countReceive"));
				model.addAttribute("countSend", result.get("countSend"));
				model.addAttribute("countRemind", result.get("countRemind"));
				
				return "main/messageReceiveList";
			}
			
			// 보낸 쪽지함 요청 - 노현호
			@RequestMapping("messageSendList.et")
			public String messageSendList(String pageNum, Model model, HttpSession session, String sel, String search) throws SQLException {
				System.out.println("messageSendList.et");
				
				// 받은 쪽지함 : 1
				// 보낸 쪽지함 : 2
				// 쪽지 보관함 : 3
				
				if(session.getAttribute("sid")==null) {
					System.out.println("비로그인 상태로 쪽지함에 접근함");
					return "user/loginForm";
				}
				
				int code = 2;
				String id = (String)session.getAttribute("sid");
				
				// message 테이블에서 receiver가 id인 데이터 전부 가져오기
				Map<String, Object> result = null;
				if(sel == null || search == null) {
					// 검색하지 않은 경우
					result = mainService.getMessageList(id, code, pageNum);
				}else {
					// 검색한 경우
					result = mainService.getMessageListSearch(id, code, pageNum, sel, search);		
				}
				
				// view 에 전달할 데이터 보내기
				model.addAttribute("pageSize", result.get("pageSize"));
				model.addAttribute("pageNum", result.get("pageNum"));
				model.addAttribute("currentPage", result.get("currentPage"));
				model.addAttribute("startRow", result.get("startRow"));
				model.addAttribute("endRow", result.get("endRow"));
				model.addAttribute("messageList", result.get("messageList"));
				model.addAttribute("count", result.get("count"));
				model.addAttribute("number", result.get("number"));
				model.addAttribute("sel", sel);
				model.addAttribute("search", search);
				
				model.addAttribute("countReceive", result.get("countReceive"));
				model.addAttribute("countSend", result.get("countSend"));
				model.addAttribute("countRemind", result.get("countRemind"));
				
				return "main/messageSendList";
			}
			
			// 쪽지 보관함 요청 - 노현호
			@RequestMapping("messageRemindList.et")
			public String messageRemindList(String pageNum, Model model, HttpSession session, String sel, String search) throws SQLException {
				System.out.println("messageRemindList.et");
				
				// 받은 쪽지함 : 1
				// 보낸 쪽지함 : 2
				// 쪽지 보관함 : 3
				
				if(session.getAttribute("sid")==null) {
					System.out.println("비로그인 상태로 쪽지함에 접근함");
					return "user/loginForm";
				}
				
				int code = 3;
				String id = "java";
				//String id = (String)session.getAttribute("sid");
				
				// message 테이블에서 receiver가 id인 데이터 전부 가져오기
				Map<String, Object> result = null;
				if(sel == null || search == null) {
					// 검색하지 않은 경우
					result = mainService.getMessageList(id, code, pageNum);
				}else {
					// 검색한 경우
					result = mainService.getMessageListSearch(id, code, pageNum, sel, search);		
				}
				
				// view 에 전달할 데이터 보내기
				model.addAttribute("pageSize", result.get("pageSize"));
				model.addAttribute("pageNum", result.get("pageNum"));
				model.addAttribute("currentPage", result.get("currentPage"));
				model.addAttribute("startRow", result.get("startRow"));
				model.addAttribute("endRow", result.get("endRow"));
				model.addAttribute("messageList", result.get("messageList"));
				model.addAttribute("count", result.get("count"));
				model.addAttribute("number", result.get("number"));
				model.addAttribute("sel", sel);
				model.addAttribute("search", search);
				
				model.addAttribute("countReceive", result.get("countReceive"));
				model.addAttribute("countSend", result.get("countSend"));
				model.addAttribute("countRemind", result.get("countRemind"));
				
				return "main/messageRemindList";
			}
			
			// 쪽지 쓰기 - 노현호
			@RequestMapping("messageWriteForm.et")
			public String messageWriteForm(String receiver, String pageNum, String uri, Model model, HttpSession session) {
				System.out.println("messageWriteForm.et");
				
				String id = (String)session.getAttribute("sid");
				
				model.addAttribute("id", id);
				model.addAttribute("receiver", receiver);
				model.addAttribute("pageNum", pageNum);
				model.addAttribute("uri", uri);
				
				return "main/messageWriteForm";
			}
			
			// 쪽지 쓰기 처리 - 노현호
			@RequestMapping("messageWritePro.et")
			public String messageWritePro(String uri, String pageNum, Model model, MessageDTO dto, HttpSession session) throws SQLException {
				System.out.println("messageWritePro.et");
				
				String id = (String)session.getAttribute("sid");
				dto.setId(id);
				
				if(pageNum == null || pageNum == "") {
					pageNum = "1";
				}
				uri += "?pageNum=" + pageNum;
				
				int result = mainService.sendMessage(dto);
				model.addAttribute("result", result);
				model.addAttribute("uri", uri);
				
				return "main/messageWritePro";
			}
			
			// 쪽지 보관/취소 처리 - 노현호
			@RequestMapping("messageRemind.et")
			public String messageRemind(String pageNum, int messagenum, String uri, Model model) throws SQLException {
				System.out.println("messageRemind.et");
				
				if(uri.equals("/main/messageRemindList.et")) {
					// 쪽지 보관함에서 요청함(보관 취소 요청)
					mainService.delRemind(messagenum);
				}else if(uri.equals("/main/messageReceiveList.et")) {
					// 쪽지 보관함 외에서 요청함(보관 요청)
					mainService.regRemind(messagenum);
				}
				
				// uri = main/messageReceiveList.et 등
				String redirect = "redirect:";
				redirect += uri + "?pageNum" + pageNum;

				return redirect;
			}
			
			// 쪽지 삭제 처리 - 노현호
			// URI 파라미터에 따라 리다이렉트로 받은 쪽지함/보낸 쪽지함/쪽지 보관함 재호출할 것
			@RequestMapping("messageDelete.et")
			public String messageDelete(String pageNum, int messagenum, String uri, Model model) throws SQLException {
				System.out.println("messageDelete.et");
				
				if(uri.equals("/main/messageSendList.et")) {
					// 보낸 쪽지함에서 요청함
					mainService.deleteSendMessage(messagenum);
				}else {
					// 보낸 쪽지함 외에서 요청함
					mainService.deleteReceiveMessage(messagenum);
				}
				
				// uri = main/messageReceiveList.et 등
				String redirect = "redirect:";
				redirect += uri + "?pageNum" + pageNum;

				return redirect;
			}
			
			// 쪽지 내용 조회 - 노현호
			@RequestMapping("messageContent.et")
			public String messageContent(String pageNum, String uri, int messagenum, Model model) throws SQLException {
				System.out.println("messageContent.et");
				
				MessageDTO message = mainService.getMessage(messagenum);
				model.addAttribute("pageNum", pageNum);
				model.addAttribute("uri", uri);
				model.addAttribute("message", message);
				
				return "main/messageContent";
			}
			
}
