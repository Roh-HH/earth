package earth.user.dto;

import java.sql.Timestamp;

public class UserDTO {
	
	private String id;
	private String pw;
	private String name;
	private String nickname;
	private String phone;
	private String email;
	private Timestamp reg;
	private Integer reportcount;
	private Integer warncount;
	private Integer badge;
	private Integer admin;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Timestamp getReg() {
		return reg;
	}
	public void setReg(Timestamp reg) {
		this.reg = reg;
	}
	public Integer getReportcount() {
		return reportcount;
	}
	public void setReportcount(Integer reportcount) {
		this.reportcount = reportcount;
	}
	public Integer getWarncount() {
		return warncount;
	}
	public void setWarncount(Integer warncount) {
		this.warncount = warncount;
	}
	public Integer getBadge() {
		return badge;
	}
	public void setBadge(Integer badge) {
		this.badge = badge;
	}
	public Integer getAdmin() {
		return admin;
	}
	public void setAdmin(Integer admin) {
		this.admin = admin;
	}
	

}