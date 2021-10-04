package earth.main.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import earth.board.dto.DiaryDTO;
import earth.board.dto.MonthDTO;
import earth.main.dto.MessageDTO;

@Repository
public class MainDAOImpl implements MainDAO {

	@Autowired
	private SqlSessionTemplate sqlSession = null;
	
	// 쪽지
		// 전체 쪽지 개수 가져오기 - 노현호
		@Override
		public int getMessageCount(String id, int code) throws SQLException {
			
			ArrayList<String> board = new ArrayList<String>();
			board.add("Receive");
			board.add("Send");
			board.add("Remind");
			board.add("Delete");
			
			int result = 0;
			String select = "main.messageCount";
			select += board.get(code-1);
			
			result = sqlSession.selectOne(select, id);
			return result;
		}
		
		// 검색된 쪽지 개수 가져오기 - 노현호
		@Override
		public int getMessageCountSearch(String id, int code, String sel, String search) throws SQLException{
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("sel", sel);
			map.put("search", search);
			
			ArrayList<String> board = new ArrayList<String>();
			board.add("Receive");
			board.add("Send");
			board.add("Remind");
			board.add("Delete");
			
			int result = 0;
			String select = "main.messageCountSearch";
			select += board.get(code-1);
			
			result = sqlSession.selectOne(select, map);
			return result;
		}
		
		// 받은쪽지함 목록 요청 - 노현호
		@Override
		public List<MessageDTO> getReceiveMessages(int startRow, int endRow, String id) throws SQLException {
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("start", startRow);
			map.put("end", endRow);
			map.put("id", id);
			
			List<MessageDTO> messageList = sqlSession.selectList("main.getReceiveMessages", map);
			return messageList;
		}
		
		// 검색된 받은쪽지함 목록 요청 - 노현호
		@Override
		public List<MessageDTO> getReceiveMessagesSearch(int startRow, int endRow, String id, String sel, String search) throws SQLException{
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("start", startRow);
			map.put("end", endRow);
			map.put("id", id);
			map.put("sel", sel);
			map.put("search", search);
			
			List<MessageDTO> messageList = sqlSession.selectList("main.getReceiveMessagesSearch", map);
			return messageList;
		}
		
		// 보낸쪽지함 목록 요청 - 노현호
		@Override
		public List<MessageDTO> getSendMessages(int startRow, int endRow, String id) throws SQLException {
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("start", startRow);
			map.put("end", endRow);
			map.put("id", id);
			
			List<MessageDTO> messageList = sqlSession.selectList("main.getSendMessages", map);
			return messageList;
		}
		
		// 검색된 보낸쪽지함 목록 요청 - 노현호
		@Override
		public List<MessageDTO> getSendMessagesSearch(int startRow, int endRow, String id, String sel, String search) throws SQLException{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("start", startRow);
			map.put("end", endRow);
			map.put("id", id);
			map.put("sel", sel);
			map.put("search", search);
			
			List<MessageDTO> messageList = sqlSession.selectList("main.getSendMessagesSearch", map);
			return messageList;
		}
		
		// 쪽지 보관함 목록 요청 - 노현호
		@Override
		public List<MessageDTO> getRemindMessages(int startRow, int endRow, String id) throws SQLException {
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("start", startRow);
			map.put("end", endRow);
			map.put("id", id);
			
			List<MessageDTO> messageList = sqlSession.selectList("main.getRemindMessages", map);
			return messageList;
		}
		
		// 검색된 쪽지 보관함 목록 요청 - 노현호
		@Override
		public List<MessageDTO> getRemindMessagesSearch(int startRow, int endRow, String id, String sel, String search) throws SQLException{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("start", startRow);
			map.put("end", endRow);
			map.put("id", id);
			map.put("sel", sel);
			map.put("search", search);
			
			List<MessageDTO> messageList = sqlSession.selectList("main.getRemindMessagesSearch", map);
			return messageList;
		}
		
		// 휴지통 목록 요청 - 노현호
		@Override
		public List<MessageDTO> getDeleteMessages(int startRow, int endRow, String id) throws SQLException {
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("start", startRow);
			map.put("end", endRow);
			map.put("id", id);
			
			List<MessageDTO> messageList = sqlSession.selectList("main.getDeleteMessages", map);
			return messageList;
		}
		
		// 검색된 휴지통 목록 요청 - 노현호
		@Override
		public List<MessageDTO> getDeleteMessagesSearch(int startRow, int endRow, String id, String sel, String search) throws SQLException{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("start", startRow);
			map.put("end", endRow);
			map.put("id", id);
			map.put("sel", sel);
			map.put("search", search);
			
			List<MessageDTO> messageList = sqlSession.selectList("main.getDeleteMessagesSearch", map);
			return messageList;
		}
		
		// 쪽지 조회 - 노현호
		@Override
		public MessageDTO getMessage(int messagenum) throws SQLException {
			MessageDTO message = sqlSession.selectOne("main.getMessage", messagenum);
			return message;
		}
		
		// 쪽지 보내기 - 노현호
		@Override
		public int sendMessage(MessageDTO dto) throws SQLException {
			int result = sqlSession.insert("main.sendMessage", dto);
			return result;
		}
		
		// 쪽지 보관함 등록 - 노현호
		@Override
		public void regRemind(int messagenum) throws SQLException {
			sqlSession.update("main.regRemind", messagenum);
		}
		
		// 쪽지 보관함 등록 해제 - 노현호
		@Override
		public void delRemind(int messagenum) throws SQLException {
			sqlSession.update("main.delRemind", messagenum);
		}
		
		// 받은 쪽지 삭제(휴지통 이동) - 노현호
		@Override
		public void deleteReceiveMessage(int messagenum) throws SQLException {
			sqlSession.update("main.deleteReceiveMessage", messagenum);
		}
		
		// 보낸 쪽지 삭제(미표시 처리) - 노현호
		@Override
		public void deleteSendMessage(int messagenum) throws SQLException {
			sqlSession.update("main.deleteSendMessage", messagenum);
		}
		
		// 휴지통 비우기 - 노현호
		@Override
		public void deleteMessage(String id) throws SQLException {
			sqlSession.update("main.deleteMessage", id);
		}
		
		// 휴지통 복구 - 노현호
		@Override
		public void reviveMessage(int messagenum) throws SQLException {
			sqlSession.update("main.reviveMessage", messagenum);
		}
		
		
		
		// 메인에서 챌린지 받아와요 - 김하영
		@Override
		public int getMainCount(String pageNum) throws SQLException {
			int result = sqlSession.selectOne("board.countAllChallenge"); 
			
			return result;
		}

		//메인 - 챌린지 - 김하영 
		@Override
		public List<MonthDTO> getMainArticles(int startRow, int endRow) throws SQLException {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("start", startRow);
			map.put("end", endRow);
				
			List<MonthDTO> mainList = sqlSession.selectList("main.getMainArticles", map);	
			
			return mainList;
		}
		//메인 - 다이어리 TOP3 - 김하영 
		@Override
		public int getDiaryCount(String pageNum) throws SQLException {
			int result = sqlSession.selectOne("board.countAllDiary"); 
			
			return result;
		}
		//메인 - 다이어리 TOP3 - 김하영 
		@Override
		public List<DiaryDTO> getDiaryArticles(int startRow, int endRow) throws SQLException {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("start", startRow);
			map.put("end", endRow);
				
			List<DiaryDTO> diaryList = sqlSession.selectList("main.getDiaryArticles", map);	
			
			return diaryList;
		}
		
		//메인 다이어리 닉네임 - 김하영
		@Override
		public String getNickname(String id) throws SQLException {	
			String nickname = sqlSession.selectOne("board.getNickname", id);	
			return nickname;
		}
		//메인 다이어리 뱃지 - 김하영 
		@Override
		public String getBadgeimg(String id) throws SQLException {
			String badgeimg = sqlSession.selectOne("board.getBadgeimg", id);
			return badgeimg;
		}
				
}
