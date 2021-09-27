package earth.board.task;

import java.sql.SQLException;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import earth.board.dto.MonthDTO;

@Component
public class EarthTask {
	
	@Autowired
	private SqlSessionTemplate sqlSession = null;
 
	//매달 1일 00시에 이달의 챌린지 등록 
	//@Scheduled(cron = "0 0 0 1 * *")
	public void monthlyChInsert() throws SQLException {
	
		System.out.println("Quartz 실행 , 이달의 챌린지 등록 / 매달 1일 00시에 실행  ");
		MonthDTO dto = new MonthDTO();
		dto.setSubject("[테스트] 9/18 쿼츠 등록 24시 15분 ");
		dto.setId("admin");
		dto.setPw("1111");
		dto.setCtt("[테스트] 서버 온, 등록체크 - 0 15 0  "
				+ "18일부터 진행중인 챌린지   ");
		dto.setImg("ch02.jpeg");
		dto.setMaxcount(5);
		dto.setCode(4);
		dto.setEnddate("21/09/31");
		sqlSession.insert("board.insertChallenge", dto);
	}
	
	//매일 00시에 포인트 0 으로 업데이트
	//@Scheduled(cron = "0 0 0 * * *") 
	public void pointupdate() throws SQLException {
		System.out.println("=======> Quartz 실행, 매일 00시에 포인트 카운트 3개 0으로 업데이트 ");
		sqlSession.update("user.updatePoint");
	}
	
	@Scheduled(cron = "0 0 4 * * * ")
	public void deleteMessage() throws SQLException {
		// 매일 오전 04시 수신자/발신자 모두 삭제처리한 쪽지 데이터 영구 제거
		sqlSession.delete("main.deleteMessage");
	}

	@Scheduled(cron = "0 0 * * * * ")
	public void updateReportCount() throws SQLException {
		// 매 시 정각 reportcount 3회인 유저들 warncount 1 증가  
		sqlSession.update("admin.updateReportCount");
	}
	
}
