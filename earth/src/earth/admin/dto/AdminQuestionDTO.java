package earth.admin.dto;

import java.sql.Timestamp;

public class AdminQuestionDTO {
	private Integer questionnum;
	private String subject;
	private String id, nickname;
	private Timestamp reg;
	private String ctt;
	private Integer reply;
	private String replyctt;
	private Timestamp replydate;
	
	public Integer getQuestionnum() {
		return questionnum;
	}
	public void setQuestionnum(Integer questionnum) {
		this.questionnum = questionnum;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Timestamp getReg() {
		return reg;
	}
	public void setReg(Timestamp reg) {
		this.reg = reg;
	}
	public String getCtt() {
		return ctt;
	}
	public void setCtt(String ctt) {
		this.ctt = ctt;
	}
	public Integer getReply() {
		return reply;
	}
	public void setReply(Integer reply) {
		this.reply = reply;
	}
	public String getReplyctt() {
		return replyctt;
	}
	public void setReplyctt(String replyctt) {
		this.replyctt = replyctt;
	}
	public Timestamp getReplydate() {
		return replydate;
	}
	public void setReplydate(Timestamp replydate) {
		this.replydate = replydate;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
	
}
