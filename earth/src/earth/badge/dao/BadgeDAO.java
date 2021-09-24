package earth.badge.dao;

import java.sql.SQLException;
import java.util.List;

import earth.badge.dto.BadgeDTO;
import earth.mybag.dto.MybagDTO;

public interface BadgeDAO {

	// 등록된 뱃지 총 개수
	public int getBadgeCount() throws SQLException;
	
	// 등록된 뱃지들 가져오기
	public List<BadgeDTO> getBadgeArticles(int startRow,int endRow, String filter) throws SQLException;
	
	// 뱃지 하나 가져오기
	public BadgeDTO getBadge(int num) throws SQLException;
	
	// 뱃지 구매 완료
	public int buyBadge(MybagDTO dto) throws SQLException;
	
	// 뱃지 등록
	public void addBadge(BadgeDTO dto) throws SQLException;
	
	// 포인트 불러오기
	public int getPoint(String uid) throws SQLException;
}
