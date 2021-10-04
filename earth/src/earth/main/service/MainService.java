package earth.main.service;

import java.sql.SQLException;
import java.util.Map;

import earth.main.dto.MessageDTO;

public interface MainService {
	
	// 받은 쪽지함 목록 가져오기 - 노현호
	public Map<String, Object> getMessageList(String id, int code, String pageNum) throws SQLException;
	// 검색된 받은 쪽지함 목록 가져오기 - 노현호
	public Map<String, Object> getMessageListSearch (String id, int code, String pageNum, String sel, String search) throws SQLException;
	// 쪽지 조회 - 노현호
	public MessageDTO getMessage(int messagenum) throws SQLException;
	// 쪽지 보내기 - 노현호
	public int sendMessage(MessageDTO dto) throws SQLException;
	// 쪽지 보관함 등록 - 노현호
	public void regRemind(int messagenum) throws SQLException;
	// 쪽지 보관함 등록 해제 - 노현호
	public void delRemind(int messagenum) throws SQLException;
	// 받은 쪽지 삭제(미표시 처리) - 노현호
	public void deleteReceiveMessage(int messagenum) throws SQLException;
	// 보낸 쪽지 삭제(미표시 처리) - 노현호
	public void deleteSendMessage(int messagenum) throws SQLException;
	// 휴지통 비우기 - 노현호
	public void deleteMessage(String id) throws SQLException;
	// 휴지통 복구 - 노현호
	public void reviveMessage(int messagenum) throws SQLException;
	
	//게시글 닉네임 가져오기 
	public String getNickname(String id) throws SQLException;
	// 뱃지 이미지 (게시글 리스트)
	public String getBadgeimg(String id) throws SQLException;		
	//메인 이달의 챌린지가져오기 - 김하영 
	public Map<String, Object> getMainList(String pageNum) throws SQLException;
	//메인 다이어리TOP3 = 김하영
	public Map<String, Object> getDiaryList(String pageNum) throws SQLException;
}
