package earth.main.dao;

import java.sql.SQLException;
import java.util.List;

import earth.board.dto.DiaryDTO;
import earth.board.dto.MonthDTO;
import earth.main.dto.MessageDTO;

public interface MainDAO {
	
	// 전체 쪽지 개수 가져오기 - 노현호
	public int getMessageCount(String id, int code) throws SQLException;
	// 검색된 쪽지 개수 가져오기 - 노현호
	public int getMessageCountSearch(String id, int code, String sel, String search) throws SQLException;
	// 받은쪽지함 목록 요청 - 노현호
	public List<MessageDTO> getReceiveMessages(int startRow, int endRow, String id) throws SQLException;
	// 검색된 받은쪽지함 목록 요청 - 노현호
	public List<MessageDTO> getReceiveMessagesSearch(int startRow, int endRow, String id, String sel, String search) throws SQLException;
	// 보낸쪽지함 목록 요청 - 노현호
	public List<MessageDTO> getSendMessages(int startRow, int endRow, String id) throws SQLException;
	// 검색된 보낸쪽지함 목록 요청 - 노현호
	public List<MessageDTO> getSendMessagesSearch(int startRow, int endRow, String id, String sel, String search) throws SQLException;
	// 쪽지 보관함 목록 요청 - 노현호
	public List<MessageDTO> getRemindMessages(int startRow, int endRow, String id) throws SQLException;
	// 검색된 쪽지 보관함 목록 요청 - 노현호
	public List<MessageDTO> getRemindMessagesSearch(int startRow, int endRow, String id, String sel, String search) throws SQLException;
	// 쪽지 조회 - 노현호
	public MessageDTO getMessage(int messagenum) throws SQLException;
	// 쪽지 보내기 - 노현호
	public int sendMessage(MessageDTO dto) throws SQLException;
	// 쪽지 보관함 등록 - 노현호
	public void regRemind(int messagenum) throws SQLException;
	// 쪽지 보관함 등록 해제 - 노현호
	public void delRemind(int messagenum) throws SQLException;
	// 보낸 쪽지 삭제(미표시 처리) - 노현호
	public void deleteSendMessage(int messagenum) throws SQLException;
	// 받은 쪽지 삭제(미표시 처리) - 노현호
	public void deleteReceiveMessage(int messagenum) throws SQLException;

	
	//메인 이달의 챌린지 - 김하영 
	public int getMainCount(String pageNum) throws SQLException;
	//메인 이달의 챌린지 리스트 -김하영 
	public List<MonthDTO> getMainArticles(int startRow, int endRow) throws SQLException;
	//메인 다이어리 TOP3- 김하영 
	public int getDiaryCount(String pageNum) throws SQLException;
	//메인 다이어리 리스트 - 김하영
	public List<DiaryDTO> getDiaryArticles(int startRow, int endRow) throws SQLException;
	//게시글 닉네임 
	public String getNickname(String id) throws SQLException;
	//뱃지 이미지 가져오기 (게시글 리스트)
	public String getBadgeimg(String id) throws SQLException;
}
