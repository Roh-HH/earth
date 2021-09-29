package earth.board.dto;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

public class TipDTO {

	private Integer boardnum;
	private String subject;
	private String id;
	private String pw;
	private String ctt;
	private String img;
	private Timestamp reg;
	private Integer readcount;
	private Integer code;
	private MultipartFile uploadFile;
	private String nickname;
	private Integer prevnum;
	private Integer nextnum;
	private String prevsub;
	private String nextsub;
	 
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
	// 뱃지 이미지 추가 
	private String badgeimg;
	public String getBadgeimg() {
		return badgeimg;
	}
	public void setBadgeimg(String badgeimg) {
		this.badgeimg = badgeimg;
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
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	public Integer getPrevnum() {
		return prevnum;
	}
	public void setPrevnum(Integer prevnum) {
		this.prevnum = prevnum;
	}
	public Integer getNextnum() {
		return nextnum;
	}
	public void setNextnum(Integer nextnum) {
		this.nextnum = nextnum;
	}
	public String getPrevsub() {
		return prevsub;
	}
	public void setPrevsub(String prevsub) {
		this.prevsub = prevsub;
	}
	public String getNextsub() {
		return nextsub;
	}
	public void setNextsub(String nextsub) {
		this.nextsub = nextsub;
	}
	
	
}