package earth.badge.dto;

import java.sql.Timestamp;

public class BadgeDTO {

	private Integer badgenum;
	private String badgename;
	private Integer badgeprice;
	private String badgeimg;
	private Integer sellnum;
	private Timestamp reg;
	
	public Integer getBadgenum() {
		return badgenum;
	}
	public void setBadgenum(Integer badgenum) {
		this.badgenum = badgenum;
	}
	public String getBadgename() {
		return badgename;
	}
	public void setBadgename(String badgename) {
		this.badgename = badgename;
	}
	public Integer getBadgeprice() {
		return badgeprice;
	}
	public void setBadgeprice(Integer badgeprice) {
		this.badgeprice = badgeprice;
	}
	public String getBadgeimg() {
		return badgeimg;
	}
	public void setBadgeimg(String badgeimg) {
		this.badgeimg = badgeimg;
	}
	public Integer getSellnum() {
		return sellnum;
	}
	public void setSellnum(Integer sellnum) {
		this.sellnum = sellnum;
	}
	public Timestamp getReg() {
		return reg;
	}
	public void setReg(Timestamp reg) {
		this.reg = reg;
	}
}
