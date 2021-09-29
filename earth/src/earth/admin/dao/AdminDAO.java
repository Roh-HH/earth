package earth.admin.dao;

import java.sql.SQLException;
import java.util.List;

import earth.admin.dto.AdminBoardDTO;
import earth.admin.dto.AdminCommentDTO;
import earth.admin.dto.AdminQuestionDTO;
import earth.board.dto.NoticeDTO;
import earth.user.dto.UserDTO;

public interface AdminDAO {

	public int getUserCount() throws SQLException;
	
	public List<UserDTO> getUser(int startRow, int endRow) throws SQLException;
	
	public int getSearchUserCount(String sel, String search) throws SQLException;
	
	public List<UserDTO> getSearchUser(int startRow, int endRow, String sel, String search) throws SQLException;
	
	public void deleteUser(String id) throws SQLException;
	
	
	public int getBoardCount() throws SQLException;
	
	public List<AdminBoardDTO> getBoard(int startRow, int endRow) throws SQLException;
	
	public int getSearchBoardCount(String sel, String search) throws SQLException;
	
	public List<AdminBoardDTO> getSearchBoard(int startRow, int endRow, String sel, String search) throws SQLException;
	
	public void deleteFree(String num) throws SQLException;
	
	public void deleteDiary(String num) throws SQLException;
	
	public int getCommentCount() throws SQLException;
	
	public List<AdminCommentDTO> getComment(int startRow, int endRow) throws SQLException;
	
	public int getSearchCommentCount(String sel, String search) throws SQLException;
	
	public List<AdminCommentDTO> getSearchComment(int startRow, int endRow, String sel, String search) throws SQLException;
	
	public void deleteComment(String num, String code) throws SQLException;
	
	public int getQuestionCount() throws SQLException;
	
	public List<AdminQuestionDTO> getQuestion(int startRow, int endRow) throws SQLException;
	
	public int getSearchQuestionCount(String sel, String search) throws SQLException;
	
	public List<AdminQuestionDTO> getSearchQuestion(int startRow, int endRow, String sel, String search) throws SQLException;
	
	public int getNoticeCount() throws SQLException;
	
	public List<NoticeDTO> getNotice(int startRow, int endRow) throws SQLException;
	
	public int getSearchNoticeCount(String sel, String search) throws SQLException;
	
	public List<NoticeDTO> getSearchNotice(int startRow, int endRow, String sel, String search) throws SQLException;
	
}
