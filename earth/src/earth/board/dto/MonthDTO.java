package earth.board.dto;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

public class MonthDTO {
	
	//컨텐츠 관련 
	private Integer boardnum; 
	private String subject;
	private String id;
	private String pw;
	private String ctt;
	private String img;
	private MultipartFile uploadFile;
	private Timestamp reg;
	
	private Integer joincount ;
	private Integer maxcount ;
	private Integer code ;
	private Timestamp actable;
	private String enddate;
	
	private Integer ck; // 가상컬럼 리스트 페이지에서 마감 시간 보여주기 위함 - 인원 마감 
	private Integer dateck; // 가상컬럼 리스트 페이지에서 마감 시간 보여주기 위함  - 시간 마감 
	
	//댓글관련 
	private Integer commentnum;
	private Timestamp regcomm;
	private String writer;
	private String commen;
	//댓글 리스트 불러올때 sql 에 추가되는 컬럼 
	private String nickname;
	private String badgeimg;
 	
	public String getBadgeimg() {
		return badgeimg;
	}
	public void setBadgeimg(String badgeimg) {
		this.badgeimg = badgeimg;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public Integer getDateck() {
		return dateck;
	}
	public void setDateck(Integer dateck) {
		this.dateck = dateck;
	}
	
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public Integer getCk() {
		return ck;
	}
	public void setCk(Integer check) {
		this.ck = check;
	}
	public String getCommen() {
		return commen;
	}
	public void setCommen(String commen) {
		this.commen = commen;
	}
	public Integer getCommentnum() {
		return commentnum;
	}
	public void setCommentnum(Integer commentnum) {
		this.commentnum = commentnum;
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
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	public Timestamp getReg() {
		return reg;
	}
	public void setReg(Timestamp reg) {
		this.reg = reg;
	}
	public Integer getJoincount() {
		return joincount;
	}
	public void setJoincount(Integer joincount) {
		this.joincount = joincount;
	}
	public Integer getMaxcount() {
		return maxcount;
	}
	public void setMaxcount(Integer maxcount) {
		this.maxcount = maxcount;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public Timestamp getActable() {
		return actable;
	}
	public void setActable(Timestamp actable) {
		this.actable = actable;
	}
	
	
}
