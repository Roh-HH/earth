package earth.admin.dao;

import java.sql.SQLException;
import java.util.List;

import earth.admin.dto.AdminBoardDTO;
import earth.admin.dto.AdminCommentDTO;
import earth.admin.dto.AdminQuestionDTO;
import earth.board.dto.NoticeDTO;
import earth.user.dto.ReportDTO;
import earth.user.dto.UserDTO;

public interface AdminDAO {

	//홈페이지에 가입된 유저의 수를 가져오기
	public int getUserCount() throws SQLException;
	
	//홈페이지에 가입된 유저를 리스트로 가져오기
	public List<UserDTO> getUser(int startRow, int endRow) throws SQLException;
	
	//홈페이지에 가입된 유저의 수를 가져오기 단, 검색된 조건에 한함
	public int getSearchUserCount(String sel, String search) throws SQLException;
	
	//홈페이지에 가입된 유저를 리스트로 가져오기 단, 검색된 조건에 한함
	public List<UserDTO> getSearchUser(int startRow, int endRow, String sel, String search) throws SQLException;
	
	public int getBoardCount() throws SQLException;
	
	public List<AdminBoardDTO> getBoard(int startRow, int endRow) throws SQLException;
	
	public int getSearchBoardCount(String sel, String search) throws SQLException;
	
	public List<AdminBoardDTO> getSearchBoard(int startRow, int endRow, String sel, String search) throws SQLException;
	
	public void deleteUser(String id) throws SQLException;
	
	public int getCommentCount() throws SQLException;
	
	public List<AdminCommentDTO> getComment(int startRow, int endRow) throws SQLException;
	
	public int getSearchCommentCount(String sel, String search) throws SQLException;
	
	public List<AdminCommentDTO> getSearchComment(int startRow, int endRow, String sel, String search) throws SQLException;
	
	public int getQuestionCount() throws SQLException;
	
	public List<AdminQuestionDTO> getQuestion(int startRow, int endRow) throws SQLException;
	
	public int getSearchQuestionCount(String sel, String search) throws SQLException;
	
	public List<AdminQuestionDTO> getSearchQuestion(int startRow, int endRow, String sel, String search) throws SQLException;
	
	public int getNoticeCount() throws SQLException;
	
	public List<NoticeDTO> getNotice(int startRow, int endRow) throws SQLException;
	
	public int getSearchNoticeCount(String sel, String search) throws SQLException;
	
	public List<NoticeDTO> getSearchNotice(int startRow, int endRow, String sel, String search) throws SQLException;
	
	public int insertReport(ReportDTO report) throws SQLException;
	
	public ReportDTO getReport(int reportnum) throws SQLException;
	
	public int getReportCount(String process) throws SQLException;
	
	public List<ReportDTO> getReport(String process, int startRow, int endRow) throws SQLException;
		
	public int getSearchReportCount(String process, String sel, String search) throws SQLException;
	
	public List<ReportDTO> getReportSearch(String process, int startRow, int endRow, String sel, String search) throws SQLException;
	
	public int processReport(String id, int reportnum, int punish) throws SQLException;
}
