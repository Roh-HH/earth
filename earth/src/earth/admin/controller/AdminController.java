package earth.admin.controller;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import earth.admin.dto.AdminQuestionDTO;
import earth.admin.service.AdminServiceImpl;
import earth.board.dto.DiaryDTO;
import earth.board.dto.FreeCommentDTO;
import earth.board.dto.FreeDTO;
import earth.board.dto.MonthDTO;
import earth.user.dto.ReportDTO;

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
	
	@RequestMapping("adminQuestionDeleteForm.et")
	public String adminQuestionDelete(String num, Model model) throws SQLException{
		
		model.addAttribute("num", num);
		
		return "adminmypage/adminQuestionDeleteForm";
		
	}
	
	@RequestMapping("adminQuestionDeletePro.et")
	public String adminQuestionDeletePro(String num, Model model) throws SQLException{
		
		adminService.deleteQuestion(num);		
		
		return "adminmypage/adminQuestionDeletePro";
		
	}
	
	
	
	@RequestMapping("adminQuestionReply.et")
	public String adminQuestionReply(@RequestParam("questionnum") int questionnum, Model model) throws SQLException {
		AdminQuestionDTO question = adminService.getQnAOne(questionnum);	// 1:1 문의글 고유번호로 문의글 한개의 정보 가져오기
		model.addAttribute("question", question);
		
		return "adminmypage/adminQuestionReply";
	}
	@RequestMapping("adminQuestionReplyPro.et")
	public String adminQuestionReplyPro(AdminQuestionDTO dto, Model model) throws SQLException {
		adminService.addQnAReply(dto);
		return "redirect:/adminmypage/adminQuestion.et";
	}
	
	// 이하 신고 관련 메서드 - 노현호
	// 신고 폼
	@RequestMapping("reportForm.et")
	public String reportForm(String id, String uri, int boardnum, Model model) throws SQLException{
		
		model.addAttribute("receiver", id);
		model.addAttribute("uri", uri);
		model.addAttribute("boardnum", boardnum);
		
		return "adminmypage/reportForm";
	}
	
	// 신고 처리
	@RequestMapping("reportPro.et")
	public String reportPro(String uri, ReportDTO report, Model model, HttpSession session) throws SQLException{
		
		report.setId((String)session.getAttribute("sid"));
		
		if(uri.equals("free")) {
			FreeDTO dto = boardService.getFreeArticle(report.getBoardnum());
			report.setCtt(dto.getCtt());
		}else if(uri.equals("freeComment")) {
			FreeCommentDTO dto = boardService.getFreeComment(report.getBoardnum());
			report.setCtt(dto.getCtt());
		}else if(uri.equals("diary")) {
			DiaryDTO dto = boardService.getDiaryArticle(report.getBoardnum());
			report.setCtt(dto.getCtt());
		}else if(uri.equals("diarycomment")) {
			DiaryDTO dto = boardService.getDiaryComment(report.getBoardnum());
			report.setCtt(dto.getCommen());
		}else if(uri.equals("challengecomment")){
			MonthDTO dto = boardService.getChallengeComment(report.getBoardnum());
			report.setCtt(dto.getCommen());
			
		}
		
		int result = adminService.insertReport(report);
		model.addAttribute("result", result);
		
		return "adminmypage/reportPro";
	}
	
	// 신고 현황목록 확인
	@RequestMapping("adminReport.et")
	public String adminReport(String pageNum, String process, String sel, String search, Model model, HttpSession session) throws SQLException{
		
		if(session.getAttribute("sid")==null || !session.getAttribute("sid").equals("admin")) {
			System.out.println("관리자가 아닌 사람이 공지사항 작성에 접근함");
			return "main/main.et";
		}
		
		Map<String, Object> result = null;
		
		if(pageNum == null){ 
			pageNum = "1";
		}
		
		if(process == null){ 
			process = "0";
		}
		
		if(sel == null || search == null) {
			result = adminService.getReportList(pageNum, process);
		}else {
			result = adminService.getReportSearch(pageNum, process , sel, search);
		}
		
		// view 에 전달할 데이터 보내기
		model.addAttribute("pageSize", result.get("pageSize"));
		model.addAttribute("pageNum", result.get("pageNum"));
		model.addAttribute("currentPage", result.get("currentPage"));
		model.addAttribute("startRow", result.get("startRow"));
		model.addAttribute("endRow", result.get("endRow"));
		model.addAttribute("reportList", result.get("reportList"));
		model.addAttribute("count", result.get("count"));
		model.addAttribute("number", result.get("number"));
		model.addAttribute("process", process);
		model.addAttribute("sel", sel);
		model.addAttribute("search", search);
		
		return "adminmypage/adminReport";
	}
	
	// 신고 처리 폼
	@RequestMapping("adminReportForm.et")
	public String adminReportForm(String pageNum, int reportnum, String sel, String search, Model model, HttpSession session) throws SQLException{
		
		if(session.getAttribute("sid")==null || !session.getAttribute("sid").equals("admin")) {
			System.out.println("관리자가 아닌 사람이 공지사항 작성에 접근함");
			return "main/main.et";
		}
		
		if(pageNum == null || pageNum == ""){ 
			pageNum = "1";
		}
		
		if(sel != null && search != null) {
			model.addAttribute("sel", sel);
			model.addAttribute("search", search);
		}else {
			model.addAttribute("sel", "");
			model.addAttribute("search", "");
		}

		ReportDTO report = adminService.getReport(reportnum);
		
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("report", report);
		
		return "adminmypage/adminReportForm";
	}
	
	// 신고 처리 프로
	@RequestMapping("adminReportPro.et")
	public String adminReportPro(String pageNum, String id, int reportnum, int punish, String sel, String search, Model model, HttpSession session) throws SQLException{
		
		if(session.getAttribute("sid")==null || !session.getAttribute("sid").equals("admin")) {
			System.out.println("관리자가 아닌 사람이 공지사항 작성에 접근함");
			return "main/main.et";
		}
		
		if(pageNum == null){ 
			pageNum = "1";
		}
		
		int result = adminService.processReport(id, reportnum, punish);
		
		model.addAttribute("result", result);
		model.addAttribute("pageNum", pageNum);
		
		if((sel != null && !sel.equals("")) && (search != null && !search.equals(""))) {
			model.addAttribute("sel", sel);
			model.addAttribute("search", search);
		}
		
		return "adminmypage/adminReportPro";
	}
	
	// 이상 신고 관련 메서드
	
	
	
}
	
	

