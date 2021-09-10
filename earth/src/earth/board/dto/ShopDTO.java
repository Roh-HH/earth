package earth.board.dto;
//상점소개 

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

public class ShopDTO {
	private Integer boardnum;
	private String subject;
	private String id;
	private String pw;
	private String img;
	private String hyperlink;
	private Timestamp reg;
	private Integer readcount;
	private Integer code;
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getHyperlink() {
		return hyperlink;
	}

	public void setHyperlink(String hyperlink) {
		this.hyperlink = hyperlink;
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
