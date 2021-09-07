package earth.board.dto;

import java.sql.Timestamp;

public class FreeCommentDTO {
	// 작성자 : 노현호
	// 자유게시판(free) 댓글 데이터 처리용 DTO
	private Integer commentnum;
	private Integer freenum;
	private Integer ref;
	private Integer relevel;
	private Timestamp reg;
	private String receiver;
	private String writer;
	private String ctt;
	
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
	public Integer getRef() {
		return ref;
	}
	public void setRef(Integer ref) {
		this.ref = ref;
	}
	public Integer getRelevel() {
		return relevel;
	}
	public void setRelevel(Integer re_level) {
		this.relevel = re_level;
	}
	public Timestamp getReg() {
		return reg;
	}
	public void setReg(Timestamp reg) {
		this.reg = reg;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
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
	public void setCtt(String content) {
		this.ctt = content;
	}

}
