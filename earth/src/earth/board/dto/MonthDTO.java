package earth.board.dto;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

public class MonthDTO {
	
	private Integer challengenum; 
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
	public Integer getChallengenum() {
		return challengenum;
	}
	public void setChallengenum(Integer challengenum) {
		this.challengenum = challengenum;
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
