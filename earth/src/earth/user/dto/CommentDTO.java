package earth.user.dto;

import java.sql.Timestamp;

public class CommentDTO {
	
	private Integer code, commentnum, freenum, boardnum;
	private String writer,ctt,commen;
	private Timestamp reg, regcomm;
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public Integer getCommentnum() {
		return commentnum;
	}
	public void setCommentnum(Integer commentnum) {
		this.commentnum = commentnum;
	}
	public Integer getFreenum() {
		return freenum;
	}
	public void setFreenum(Integer freenum) {
		this.freenum = freenum;
	}
	public Integer getBoardnum() {
		return boardnum;
	}
	public void setBoardnum(Integer boardnum) {
		this.boardnum = boardnum;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getCtt() {
		return ctt;
	}
	public void setCtt(String ctt) {
		this.ctt = ctt;
	}
	public String getCommen() {
		return commen;
	}
	public void setCommen(String commen) {
		this.commen = commen;
	}
	public Timestamp getReg() {
		return reg;
	}
	public void setReg(Timestamp reg) {
		this.reg = reg;
	}
	public Timestamp getRegcomm() {
		return regcomm;
	}
	public void setRegcomm(Timestamp regcomm) {
		this.regcomm = regcomm;
	}


}
