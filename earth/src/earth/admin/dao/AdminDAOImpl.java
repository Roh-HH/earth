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

import earth.board.dto.NoticeDTO;
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
	public void deleteUser(String id) throws SQLException {
		
		sqlSession.delete("admin.deleteUser", id);
		
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
		
		int result = sqlSession.selectOne("admin.getSearchBoardCount", map);
		
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
	public void deleteFree(String num) throws SQLException {
		
		sqlSession.delete("admin.deleteFree", num);
		
	}
	
	@Override
	public void deleteDiary(String num) throws SQLException {
		
		sqlSession.delete("admin.deleteDiary", num);
		
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
	public void deleteComment(String num, String code) throws SQLException {
		
		int i = Integer.parseInt(code);
		
		if(i == 9) {
			sqlSession.delete("admin.deleteFreeC", num);
		}else if(i == 10){
			sqlSession.delete("admin.deleteDiaryC", num);
		}else if(i == 11) {
			sqlSession.delete("admin.deleteChallengeC", num);
		}
		
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
	public void deleteQuestion(String num) throws SQLException {
		
		
		sqlSession.delete("admin.deleteQuestion", num);
		
		
	}
	
	// 작성자 : 이영인
	// 1:1 문의 답변하기
	@Override
	public AdminQuestionDTO getQnAOne(int questionnum) throws SQLException {
		AdminQuestionDTO question = sqlSession.selectOne("admin.getQnAOne", questionnum);
		return question;
	}

	@Override
	public void addQnAReply(AdminQuestionDTO dto) throws SQLException {
		sqlSession.update("admin.addQnAReply", dto);
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
		int result = 0;
		if(process.equals("0")) {
			result = sqlSession.selectOne("admin.getReportCount", process);			
		}else {
			result = sqlSession.selectOne("admin.getReportCountProcess");
		}
		return result;
	}
	
	@Override
	public List<ReportDTO> getReport(String process, int startRow, int endRow) throws SQLException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("process", process);
		map.put("startRow", startRow);
		map.put("endRow", endRow);
	
		List<ReportDTO> reportList = null;
		if(process.equals("0")) {
			reportList = sqlSession.selectList("admin.getReportList", map);			
		}else {
			reportList = sqlSession.selectList("admin.getReportListProcess", map);
		}
		
		return reportList;
	}
	
	@Override
	public int getSearchReportCount(String process, String sel, String search) throws SQLException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("process", process);
		map.put("sel", sel);
		map.put("search", search);
		
		int result = 0;
		if(process.equals("0")) {
			result = sqlSession.selectOne("admin.getSearchReportCount", map);		
		}else {
			result = sqlSession.selectOne("admin.getSearchReportCountProcess", map);
		}

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
		
		List<ReportDTO> reportList = null;
		if(process.equals("0")) {
			reportList = sqlSession.selectList("admin.getReportSearch", map);			
		}else {
			reportList = sqlSession.selectList("admin.getReportSearchProcess", map);
		}
		
		return reportList;
	}
	
	@Override
	public int processReport(String id, int reportnum, int punish) throws SQLException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("reportnum", reportnum);
		
		int result = 0;
		
		switch(punish) {
		case 0:
			map.put("process", 1);
			result = sqlSession.update("admin.processUpdate", map);
			return result;
		case 1:
			map.put("process", 2);
			sqlSession.update("admin.processOne", id);
			result = sqlSession.update("admin.processUpdate", map);
			return result;
		case 2:
			map.put("process", 3);
			sqlSession.delete("admin.processTwo", id);
			result = sqlSession.delete("admin.processUpdate", map);
			return result;
		}

		return result;
	}
	
}
