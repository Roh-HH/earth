package earth.admin.dao;

import java.sql.SQLException;
import java.util.List;

import earth.admin.dto.AdminBoardDTO;
import earth.admin.dto.UserDTO;

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
	
}
