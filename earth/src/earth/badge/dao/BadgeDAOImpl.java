package earth.badge.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import earth.badge.dto.BadgeDTO;
import earth.user.dto.MybagDTO;

@Repository
public class BadgeDAOImpl implements BadgeDAO{

	@Autowired
	private SqlSessionTemplate sqlSession = null;

	@Override
	public int getBadgeCount() throws SQLException {
		
		int result = sqlSession.selectOne("badge.countAll");
				
		return result;
	}

	@Override			
	public List<BadgeDTO> getBadgeArticles(int startRow, int endRow, String filter) throws SQLException {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<BadgeDTO> badgeList;

		map.put("start", startRow);
		map.put("end", endRow);
		
		String pop = "popul";
		String pri = "price";
		
		
		System.out.println(filter);
		
		
		if(filter.equals(pop)) {
			badgeList = sqlSession.selectList("badge.getPopulBadgeArticles", map);
		} else if(filter.equals(pri)) {
			badgeList = sqlSession.selectList("badge.getPriceBadgeArticles", map);	
		} else {
			badgeList = sqlSession.selectList("badge.getBadgeArticles", map);
		}
		
	
		
		return badgeList;
	}
	
	@Override
	public BadgeDTO getBadge(int num) throws SQLException {
		
		BadgeDTO badge = sqlSession.selectOne("badge.getBadge", num);
		
		return badge;
	}
	
	// 뱃지 구매
	@Override
	public int buyBadge(MybagDTO dto,String id) throws SQLException {
	
		// 뱃지 구매 완료
		int result = sqlSession.insert("badge.buyBadge",dto);
	
		// 포인트 사용
		HashMap<String, Object> map = new HashMap<String, Object>();
		int num = dto.getBadgenum();
		map.put("num", num);
		map.put("id", id);
		
		sqlSession.update("badge.usePoint",map);
		
		// 구매 횟수 +1
		sqlSession.update("badge.countUp",num);
		
		return result;
	}
	
	// 뱃지 추가
	@Override
	public void addBadge(BadgeDTO dto) throws SQLException {
		
		sqlSession.insert("badge.addBadge",dto);
		
	}
	
	// 뱃지 포인트 불러오기
	@Override
	public int getPoint(String id) throws SQLException {
	
		int point = sqlSession.selectOne("badge.getPoint", id);
		
		return point;
	}
	
	// 뱃지 중복체크
	@Override
	public int checkBadge(int num,String id) throws SQLException {

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("num",num);
		map.put("uid",id);
		
		int result = 0;
		int check = sqlSession.selectOne("badge.checkBadge", map);
		
		if(check >= 1) {
			result = 1;
		}
		return result;
	}
}
