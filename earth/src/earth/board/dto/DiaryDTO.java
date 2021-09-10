package earth.board.dto;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

public class DiaryDTO {
	
	// 환경일기 DTO - 이다희 
	private Integer boardnum;
	private String subject;
	private String id;
	private String pw;
	private String ctt;
	private String img;
	private Timestamp reg;
	private Integer readcount;
	private Integer code;
	private Integer recommend;
	
	//좋아요 
	private String recid;
	 
	//환경일기 댓글 
	private Integer commentnum;
	private Integer ref;
	private Integer relevel;
	private Timestamp regcomm;
	private String receiver;
	private String writer;
	private String commen;
	
 
	public Integer getCommentnum() {
		return commentnum;
	}
	public void setCommentnum(Integer commentnum) {
		this.commentnum = commentnum;
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
	public void setRelevel(Integer relevel) {
		this.relevel = relevel;
	}
	public Timestamp getRegcomm() {
		return regcomm;
	}
	public void setRegcomm(Timestamp regcomm) {
		this.regcomm = regcomm;
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
	public String getCommen() {
		return commen;
	}
	public void setCommen(String commen) {
		this.commen = commen;
	}
	public String getRecid() {
		return recid;
	}
	public void setRecid(String recid) {
		this.recid = recid;
	}
	private MultipartFile uploadFile;
	
	public Integer getBoardnum() {
		return boardnum;
	}
	public void setBoardnum(Integer boardnum) {
		this.boardnum = boardnum;
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
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getCtt() {
		return ctt;
	}
	public void setCtt(String ctt) {
		this.ctt = ctt;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Timestamp getReg() {
		return reg;
	}
	public void setReg(Timestamp reg) {
		this.reg = reg;
	}
	public Integer getReadcount() {
		return readcount;
	}
	public void setReadcount(Integer readcount) {
		this.readcount = readcount;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public Integer getRecommend() {
		return recommend;
	}
	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	
	
	
	

}
