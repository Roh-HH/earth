package earth.admin.controller;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import earth.admin.service.AdminServiceImpl;

@Controller
@RequestMapping("/adminmypage/*")
public class AdminController {
	
	
	@Autowired
	private AdminServiceImpl adminService = null;
	
	//adminUserForm
	
	@RequestMapping("adminUser.et")
	public String adminUser(String pageNum, String sel, String search, Model model) throws SQLException{
	
		
		Map<String, Object> result = null;
		
		if(pageNum == null){
			pageNum = "1";
		}
		
		if(sel == null || search == null) {
			result = adminService.getUserList(pageNum);
		}else {
			result = adminService.getUserSearch(pageNum, sel, search);
		}
		
		model.addAttribute("pageSize", result.get("pageSize"));
		model.addAttribute("pageNum", result.get("pageNum"));
		model.addAttribute("currentPage", result.get("currentPage"));
		model.addAttribute("startRow", result.get("startRow"));
		model.addAttribute("endRow", result.get("endRow"));
		model.addAttribute("UserList", result.get("UserList"));
		model.addAttribute("count", result.get("count"));
		model.addAttribute("number", result.get("number"));
		model.addAttribute("sel", sel);
		model.addAttribute("search", search);
		
		return "adminmypage/adminUser";
		
	}
	
	@RequestMapping("adminUserDeleteForm.et")
	public String adminUserDelete(String id, Model model) throws SQLException{
		
		model.addAttribute("id", id);
		
		return "adminmypage/adminUserDeleteForm";
		
	}
	
	@RequestMapping("adminUserDeletePro.et")
	public String adminUserDeletePro(String id, Model model) throws SQLException{
		
		adminService.deleteUser(id);
		
			
		
		return "adminmypage/adminUserDeletePro";
		
	}
	
	/*
	 * @RequestMapping("adminUserDeleteForm.et")
	public String adminUserDelete(String[] info, Model model) throws SQLException{
		
		model.addAttribute("info", info);
		
		return "adminmypage/adminUserDeleteForm";
		
	}
	
	@RequestMapping("adminUserDeletePro.et")
	public String adminUserDeletePro(String[] info, Model model) throws SQLException{
		
		for(int i = 0 ; i < info.length; i++){
			String id = info[i];
			adminService.deleteUser(id);
		}
		
		return "adminmypage/adminUserDeletePro";
		
	}
	 */
	
	
	
	
	@RequestMapping("adminBoard.et")
	public String adminBoard(String pageNum, String sel, String search, Model model) throws SQLException{
		
		Map<String, Object> result = null;
		
		if(pageNum == null){ 
			pageNum = "1";
		}
		
		if(sel == null || search == null) {
			result = adminService.getBoardList(pageNum);
		}else {
			result = adminService.getBoardSearchList(pageNum, sel, search);
		}
		
		
		
		model.addAttribute("pageSize", result.get("pageSize"));
		model.addAttribute("pageNum", result.get("pageNum"));
		model.addAttribute("currentPage", result.get("currentPage"));
		model.addAttribute("startRow", result.get("startRow"));
		model.addAttribute("endRow", result.get("endRow"));
		model.addAttribute("BoardList", result.get("BoardList"));
		model.addAttribute("count", result.get("count"));
		model.addAttribute("number", result.get("number"));
		model.addAttribute("sel", sel);
		model.addAttribute("search", search);
		
		return "adminmypage/adminBoard";
		
		
	}
	
	@RequestMapping("adminFreeDeleteForm.et")
	public String adminFreeDelete(String num, Model model) throws SQLException{
		
		model.addAttribute("num", num);
		
		return "adminmypage/adminFreeDeleteForm";
		
	}
	
	@RequestMapping("adminFreeDeletePro.et")
	public String adminFreeDeletePro(String num, Model model) throws SQLException{
		
		adminService.deleteFree(num);
		
		return "adminmypage/adminFreeDeletePro";
		
	}
	
	@RequestMapping("adminDiaryDeleteForm.et")
	public String adminDiaryDelete(String num, Model model) throws SQLException{
		
		model.addAttribute("num", num);
		
		return "adminmypage/adminDiaryDeleteForm";
		
	}
	
	@RequestMapping("adminDiaryDeletePro.et")
	public String adminDiaryDeletePro(String num, Model model) throws SQLException{
		
		adminService.deleteDiary(num);		
		
		return "adminmypage/adminDiaryDeletePro";
		
	}
	/*
	@RequestMapping("adminModify.et")
	public String adminModify(String pageNum, String sel, String search, Model model) throws SQLException{
	
		
		Map<String, Object> result = null;
		
		if(pageNum == null){ 
			pageNum = "1";
		}
		
		if(sel == null || search == null) {
			result = adminService.getUserList(pageNum);
		}else {
			result = adminService.getUserSearch(pageNum, sel, search);
		}
		
		model.addAttribute("pageSize", result.get("pageSize"));
		model.addAttribute("pageNum", result.get("pageNum"));
		model.addAttribute("currentPage", result.get("currentPage"));
		model.addAttribute("startRow", result.get("startRow"));
		model.addAttribute("endRow", result.get("endRow"));
		model.addAttribute("UserList", result.get("UserList"));
		model.addAttribute("count", result.get("count"));
		model.addAttribute("number", result.get("number"));
		model.addAttribute("sel", sel);
		model.addAttribute("search", search);
		
		return "adminmypage/adminModify";
		
	}
	*/
	@RequestMapping("adminComment.et")
	public String adminComment(String pageNum, String sel, String search, Model model) throws SQLException{
	
		
		Map<String, Object> result = null;
		
		if(pageNum == null){ 
			pageNum = "1";
		}
		
		if(sel == null || search == null) {
			result = adminService.getCommentList(pageNum);
		}else {
			result = adminService.getCommentSearch(pageNum, sel, search);
		}
		
		model.addAttribute("pageSize", result.get("pageSize"));
		model.addAttribute("pageNum", result.get("pageNum"));
		model.addAttribute("currentPage", result.get("currentPage"));
		model.addAttribute("startRow", result.get("startRow"));
		model.addAttribute("endRow", result.get("endRow"));
		model.addAttribute("commentList", result.get("commentList"));
		model.addAttribute("count", result.get("count"));
		model.addAttribute("number", result.get("number"));
		model.addAttribute("sel", sel);
		model.addAttribute("search", search);
		
		return "adminmypage/adminComment";
		
	}
	
	@RequestMapping("adminCommentDeleteForm.et")
	public String adminCommentDelete(String num, String code, Model model) throws SQLException{
		
		model.addAttribute("num", num);
		model.addAttribute("code", code);
		
		return "adminmypage/adminCommentDeleteForm";
		
	}
	
	@RequestMapping("adminCommentDeletePro.et")
	public String adminCommentDeletePro(String num, String code, Model model) throws SQLException{
		
		adminService.deleteComment(num, code);		
		
		return "adminmypage/adminCommentDeletePro";
		
	}
	
	@RequestMapping("adminQuestion.et")
	public String adminQuestion(String pageNum, String sel, String search, Model model) throws SQLException{
	
		
		Map<String, Object> result = null;
		
		if(pageNum == null){ 
			pageNum = "1";
		}
		
		if(sel == null || search == null) {
			result = adminService.getQuestionList(pageNum);
		}else {
			result = adminService.getQuestionSearch(pageNum, sel, search);
		}
		
		model.addAttribute("pageSize", result.get("pageSize"));
		model.addAttribute("pageNum", result.get("pageNum"));
		model.addAttribute("currentPage", result.get("currentPage"));
		model.addAttribute("startRow", result.get("startRow"));
		model.addAttribute("endRow", result.get("endRow"));
		model.addAttribute("questionList", result.get("questionList"));
		model.addAttribute("count", result.get("count"));
		model.addAttribute("number", result.get("number"));
		model.addAttribute("sel", sel);
		model.addAttribute("search", search);
		
		return "adminmypage/adminQuestion";
		
	}
	
	@RequestMapping("adminNotice.et")
	public String adminNotice(String pageNum, String sel, String search, Model model) throws SQLException{
	
		
		Map<String, Object> result = null;
		
		if(pageNum == null){ 
			pageNum = "1";
		}
		
		if(sel == null || search == null) {
			result = adminService.getNoticeList(pageNum);
		}else {
			result = adminService.getNoticeSearch(pageNum, sel, search);
		}
		
		model.addAttribute("pageSize", result.get("pageSize"));
		model.addAttribute("pageNum", result.get("pageNum"));
		model.addAttribute("currentPage", result.get("currentPage"));
		model.addAttribute("startRow", result.get("startRow"));
		model.addAttribute("endRow", result.get("endRow"));
		model.addAttribute("noticeList", result.get("noticeList"));
		model.addAttribute("count", result.get("count"));
		model.addAttribute("number", result.get("number"));
		model.addAttribute("sel", sel);
		model.addAttribute("search", search);
		
		return "adminmypage/adminNotice";
		
	}
	
	
	
	
	
}
	
	

