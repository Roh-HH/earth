package earth.admin.service;

import java.sql.SQLException;
import java.util.Map;

public interface AdminService {

	public Map<String, Object> getUserList(String pageNum) throws SQLException;
	
	public Map<String, Object> getUserSearch(String pageNum, String sel, String search) throws SQLException;
	
	public Map<String, Object> getBoardList(String pageNum) throws SQLException;
	
	public Map<String, Object> getBoardSearchList(String pageNum, String sel, String search) throws SQLException;
	
	public void deleteUser(String id) throws SQLException;
	
	
}
