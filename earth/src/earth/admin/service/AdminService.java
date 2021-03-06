package earth.admin.service;

import java.sql.SQLException;
import java.util.Map;

import earth.admin.dto.AdminQuestionDTO;
import earth.user.dto.ReportDTO;

public interface AdminService {

	public Map<String, Object> getUserList(String pageNum) throws SQLException;
	
	public Map<String, Object> getUserSearch(String pageNum, String sel, String search) throws SQLException;
	
	public void deleteUser(String id) throws SQLException;
	
	public Map<String, Object> getBoardList(String pageNum) throws SQLException;
	
	public Map<String, Object> getBoardSearchList(String pageNum, String sel, String search) throws SQLException;
	
	public void deleteFree(String num) throws SQLException;
	
	public void deleteDiary(String num) throws SQLException;
	
	public Map<String, Object> getCommentList(String pageNum) throws SQLException;
	
	public Map<String, Object> getCommentSearch(String pageNum, String sel, String search) throws SQLException;
	
	public void deleteComment(String num, String code) throws SQLException;
	
	public Map<String, Object> getQuestionList(String pageNum) throws SQLException;
	
	public Map<String, Object> getQuestionSearch(String pageNum, String sel, String search) throws SQLException;
	
	public void deleteQuestion(String num) throws SQLException;
	// 작성자 : 이영인
	// 1:1 문의 답변하기
	public AdminQuestionDTO getQnAOne(int questionnum) throws SQLException;
	public void addQnAReply(AdminQuestionDTO dto) throws SQLException;
	
	public Map<String, Object> getNoticeList(String pageNum) throws SQLException;
	
	public Map<String, Object> getNoticeSearch(String pageNum, String sel, String search) throws SQLException;
	
	//신고
	//작성자 : 현호형님
	public int insertReport(ReportDTO report) throws SQLException;
	
	public ReportDTO getReport(int reportnum) throws SQLException;
	
	public Map<String, Object> getReportList(String pageNum, String process) throws SQLException;
	
	public Map<String, Object> getReportSearch(String pageNum, String process, String sel, String search) throws SQLException;
	
	public int processReport(String id, int reportnum, int punish) throws SQLException;
	
	
}
