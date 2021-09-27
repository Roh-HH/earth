package earth.admin.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import earth.admin.dto.AdminBoardDTO;
import earth.admin.dto.AdminCommentDTO;
import earth.admin.dto.AdminQuestionDTO;
import earth.admin.dto.NoticeDTO;
import earth.user.dto.ReportDTO;
import earth.user.dto.UserDTO;

@Repository
public class AdminDAOImpl implements AdminDAO {

	@Autowired
	private SqlSessionTemplate sqlSession = null;
	
	@Override
	public int getUserCount() throws SQLException {
		
		int result = sqlSession.selectOne("admin.getUserCount");
		
		return result;
	}

	@Override
	public List<UserDTO> getUser(int startRow, int endRow) throws SQLException {
		
		HashMap map = new HashMap();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
	
		List<UserDTO> UserList = sqlSession.selectList("admin.getUser", map);
		
		return UserList;
	}

	@Override
	public int getSearchUserCount(String sel, String search) throws SQLException {
		
		System.out.println("sel : " + sel);
		
		HashMap map = new HashMap();
		map.put("sel", sel);
		map.put("search", search);
		
		int result = sqlSession.selectOne("admin.getSearchUserCount", map);
		
		return result;
	}

	@Override
	public List<UserDTO> getSearchUser(int startRow, int endRow, String sel, String search) throws SQLException {
		
		System.out.println("sel : " + sel);
		
		HashMap map = new HashMap();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("sel", sel);
		map.put("search", search);
		
		List<UserDTO> UserList = sqlSession.selectList("admin.getSearchUser", map);
		
		return UserList;
	}

	@Override
	public int getBoardCount() throws SQLException {
		
		int result = sqlSession.selectOne("admin.getBoardCount");
		
		return result;
	}

	@Override
	public List<AdminBoardDTO> getBoard(int startRow, int endRow) throws SQLException {
		
		HashMap map = new HashMap();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		
		List<AdminBoardDTO> BoardList = sqlSession.selectList("admin.getBoard", map);
		
		return BoardList;
	}

	@Override
	public int getSearchBoardCount(String sel, String search) throws SQLException {
		
		HashMap map = new HashMap();
		map.put("sel", sel);
		map.put("search", search);
		
		int result = sqlSession.selectOne("admin.getSearchBoardCount");
		
		return result;
	}

	@Override
	public List<AdminBoardDTO> getSearchBoard(int startRow, int endRow, String sel, String search) throws SQLException {
		
		HashMap map = new HashMap();
		map.put("sel", sel);
		map.put("search", search);
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		
		List<AdminBoardDTO> BoardList = sqlSession.selectList("admin.getSearchBoard", map);
		
		return BoardList;
	}

	@Override
	public void deleteUser(String id) throws SQLException {
		
		sqlSession.delete("admin.deleteUser", id);
		
	}

	@Override
	public int getCommentCount() throws SQLException {
		int result = sqlSession.selectOne("admin.getCommentCount");
		
		return result;
	}

	@Override
	public List<AdminCommentDTO> getComment(int startRow, int endRow) throws SQLException {
		HashMap map = new HashMap();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
	
		List<AdminCommentDTO> commentList = sqlSession.selectList("admin.getComment", map);
		
		return commentList;
	}

	@Override
	public int getSearchCommentCount(String sel, String search) throws SQLException {
		
		HashMap map = new HashMap();
		map.put("sel", sel);
		map.put("search", search);
		
		int result = sqlSession.selectOne("admin.getSearchCommentCount", map);
		
		return result;
	}

	@Override
	public List<AdminCommentDTO> getSearchComment(int startRow, int endRow, String sel, String search)throws SQLException {
		
		HashMap map = new HashMap();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("sel", sel);
		map.put("search", search);
		
		List<AdminCommentDTO> commentList = sqlSession.selectList("admin.getSearchComment", map);
		
		return commentList;
	}

	@Override
	public int getQuestionCount() throws SQLException {
		
		int result = sqlSession.selectOne("admin.getQuestionCount");
		
		return result;
	}

	@Override
	public List<AdminQuestionDTO> getQuestion(int startRow, int endRow) throws SQLException {
		
		HashMap map = new HashMap();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
	
		List<AdminQuestionDTO> questionList = sqlSession.selectList("admin.getQuestion", map);
		
		return questionList;
	}

	@Override
	public int getSearchQuestionCount(String sel, String search) throws SQLException {
		
		HashMap map = new HashMap();
		map.put("sel", sel);
		map.put("search", search);
		
		int result = sqlSession.selectOne("admin.getSearchQuestionCount", map);
		
		return result;
	}

	@Override
	public List<AdminQuestionDTO> getSearchQuestion(int startRow, int endRow, String sel, String search)throws SQLException {
		
		HashMap map = new HashMap();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("sel", sel);
		map.put("search", search);
		
		List<AdminQuestionDTO> questionList = sqlSession.selectList("admin.getSearchQuestion", map);
		
		return questionList;
		
	}
	
	
	@Override
	public int getNoticeCount() throws SQLException {
		
		int result = sqlSession.selectOne("admin.getNoticeCount");
		
		return result;
	}

	@Override
	public List<NoticeDTO> getNotice(int startRow, int endRow) throws SQLException {
		
		HashMap map = new HashMap();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
	
		List<NoticeDTO> noticeList = sqlSession.selectList("admin.getNotice", map);
		
		return noticeList;
	}

	@Override
	public int getSearchNoticeCount(String sel, String search) throws SQLException {
		
		HashMap map = new HashMap();
		map.put("sel", sel);
		map.put("search", search);
		
		int result = sqlSession.selectOne("admin.getSearchNoticeCount", map);
		
		return result;
	}

	@Override
	public List<NoticeDTO> getSearchNotice(int startRow, int endRow, String sel, String search)throws SQLException {
		
		HashMap map = new HashMap();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("sel", sel);
		map.put("search", search);
		
		List<NoticeDTO> noticeList = sqlSession.selectList("admin.getSearchNotice", map);
		
		return noticeList;
		
	}

	// 신고
	@Override
	public int insertReport(ReportDTO report) throws SQLException {
		int result = sqlSession.insert("admin.insertReport", report);
		return result;
	}
	
	@Override
	public ReportDTO getReport(int reportnum) throws SQLException {
		ReportDTO report = sqlSession.selectOne("admin.getReport", reportnum);
		return report;
	}
	
	@Override
	public int getReportCount(String process) throws SQLException {
		int result = sqlSession.selectOne("admin.getReportCount", process);
		return result;
	}
	
	@Override
	public List<ReportDTO> getReport(String process, int startRow, int endRow) throws SQLException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("process", process);
		map.put("startRow", startRow);
		map.put("endRow", endRow);
	
		List<ReportDTO> reportList = sqlSession.selectList("admin.getReportList", map);
		return reportList;
	}
	
	@Override
	public int getSearchReportCount(String process, String sel, String search) throws SQLException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("process", process);
		map.put("sel", sel);
		map.put("search", search);
		
		int result = sqlSession.selectOne("admin.getSearchReportCount", map);
		return result;
	}
	
	@Override
	public List<ReportDTO> getReportSearch(String process, int startRow, int endRow, String sel, String search) throws SQLException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("process", process);
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("sel", sel);
		map.put("search", search);
		
		List<ReportDTO> reportList = sqlSession.selectList("admin.getReportSearch", map);
		return reportList;
	}
	
	@Override
	public int processReport(String id, int reportnum, int punish) throws SQLException {		
		switch(punish) {
		case 1:
			sqlSession.update("admin.processOne", id);
			break;
		case 2:
			sqlSession.update("admin.processTwo", id);
			break;
		}
		
		int result = sqlSession.update("admin.processUpdate", reportnum);
		return result;
	}
	
}