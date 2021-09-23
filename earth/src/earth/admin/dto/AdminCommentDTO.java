package earth.admin.dto;

import java.sql.Timestamp;

public class AdminCommentDTO {

	private Integer code, commentnum;
	private String commen, writer, nickname;
	private Timestamp regcomm;
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getCommen() {
		return commen;
	}
	public void setCommen(String commen) {
		this.commen = commen;
	}
	public Timestamp getRegcomm() {
		return regcomm;
	}
	public void setRegcomm(Timestamp regcomm) {
		this.regcomm = regcomm;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getCommentnum() {
		return commentnum;
	}
	public void setCommentnum(Integer commentnum) {
		this.commentnum = commentnum;
	}
	
	
}
