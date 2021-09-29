package earth.admin.service;

import java.sql.SQLException;
import java.util.Map;

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
	
	public Map<String, Object> getNoticeList(String pageNum) throws SQLException;
	
	public Map<String, Object> getNoticeSearch(String pageNum, String sel, String search) throws SQLException;
	
	
}
