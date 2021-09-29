package earth.badge.service;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.stereotype.Service;

import earth.badge.dto.BadgeDTO;
import earth.user.dto.MybagDTO;

@Service
public interface BadgeService {

	// 보유 뱃지 목록 불러오기
	//public Map<String, Object> getBadgeList(String pageNum, String filter) throws SQLException;

	// 상점 뱃지 목록 불러오기
	public Map<String, Object> getBadgeList(String pageNum, String filter) throws SQLException;

	// 뱃지 하나 불러오기
	public BadgeDTO getBadge(int num) throws SQLException;

	// 뱃지 구매 완료
	public int buyBadge(MybagDTO dto, String id) throws SQLException;
	
	// 뱃지 추가하기 (관리자)
	public void addBadge(BadgeDTO dto) throws SQLException;
	
	// 뱃지 포인트 불러오기
	public int getPoint(String id) throws SQLException;
	
	// 뱃지 중복체크
	public int checkBadge(int num,String id) throws SQLException;
}
