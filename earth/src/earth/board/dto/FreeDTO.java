package earth.board.dto;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

public class FreeDTO {
	// 작성자 : 노현호
	// 자유게시판(free) 데이터 처리용 DTO
	private Integer boardnum;
	private String subject;
	private Integer categ;
	private String categStr;
	private String id;
	private String pw;
	private String ctt;
	private String img;
	private Timestamp reg;
	private Integer readcount;
	private Integer code;
	private MultipartFile uploadFile;
	private Integer refCount;
	private String nickname;
	private String badgeimg;
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getBadgeimg() {
		return badgeimg;
	}
	public void setBadgeimg(String badgeimg) {
		this.badgeimg = badgeimg;
	}
	public Integer getRefCount() {
		return refCount;
	}
	public void setRefCount(Integer refCount) {
		this.refCount = refCount;
	}
	public String getCategStr() {
		return categStr;
	}
	public void setCategStr(String categStr) {
		this.categStr = categStr;
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
	public Integer getCateg() {
		return categ;
	}
	public void setCateg(Integer categ) {
		this.categ = categ;
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
	
}
