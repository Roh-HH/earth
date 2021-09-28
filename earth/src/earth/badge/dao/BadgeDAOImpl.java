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

		String pop = "popul";
		String pri = "price";
		
		map.put("start", startRow);
		map.put("end", endRow);
		
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
	
	@Override
	public int buyBadge(MybagDTO dto) throws SQLException {
	
		int result = sqlSession.insert("badge.buyBadge",dto);
		
		return result;
	}
	
	@Override
	public void addBadge(BadgeDTO dto) throws SQLException {
		
		sqlSession.insert("badge.addBadge",dto);
		
	}
	
	// 뱃지 포인트 불러오기
	@Override
	public int getPoint(String uid) throws SQLException {
	
		int point = sqlSession.selectOne("badge.getPoint", uid);
		
		return point;
	}
}
