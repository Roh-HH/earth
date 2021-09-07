package earth.board.dto;

import java.sql.Timestamp;

public class TodayDTO {
	// 작성자 : 노현호
	// 오늘의 실천(today) 데이터 처리용 DTO
	private Integer boardnum;
	private String id;
	private String ctt;
	private Timestamp reg;
	private Integer code;
	private Integer condition;
	
	public Integer getBoardnum() {
		return boardnum;
	}
	public void setBoardnum(Integer boardnum) {
		this.boardnum = boardnum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCtt() {
		return ctt;
	}
	public void setCtt(String ctt) {
		this.ctt = ctt;
	}
	public Timestamp getReg() {
		return reg;
	}
	public void setReg(Timestamp reg) {
		this.reg = reg;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public Integer getCondition() {
		return condition;
	}
	public void setCondition(Integer condition) {
		this.condition = condition;
	}
	
}
