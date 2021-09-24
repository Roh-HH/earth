package earth.main.dto;

import java.sql.Timestamp;

public class MessageDTO {
	// 작성자 : 노현호
	// 쪽지(message) 데이터 처리용 DTO
	private Integer messagenum;
	private String id;
	private String receiver;
	private String ctt;
	private Integer delReceiver;
	private Integer delWriter;
	private Timestamp reg;
	private Integer remind;
	
	public Integer getMessagenum() {
		return messagenum;
	}
	public void setMessagenum(Integer messagenum) {
		this.messagenum = messagenum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public Integer getDelReceiver() {
		return delReceiver;
	}
	public void setDelReceiver(Integer delReceiver) {
		this.delReceiver = delReceiver;
	}
	public Integer getDelWriter() {
		return delWriter;
	}
	public void setDelWriter(Integer delWriter) {
		this.delWriter = delWriter;
	}
	public Timestamp getReg() {
		return reg;
	}
	public void setReg(Timestamp reg) {
		this.reg = reg;
	}
	public Integer getRemind() {
		return remind;
	}
	public void setRemind(Integer remind) {
		this.remind = remind;
	}
	public String getCtt() {
		return ctt;
	}
	public void setCtt(String ctt) {
		this.ctt = ctt;
	}
	
}