package earth.user.dto;

import java.sql.Timestamp;

public class WarningDTO {
	private Integer warningnum;
	private String id;
	private String ctt;
	private Timestamp suspend;
	
	public Integer getWarningnum() {
		return warningnum;
	}
	public void setWarningnum(Integer warningnum) {
		this.warningnum = warningnum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCtt() {
		return ctt;
	}
	public void setCtt(String ctt) {
		this.ctt = ctt;
	}
	public Timestamp getSuspend() {
		return suspend;
	}
	public void setSuspend(Timestamp suspend) {
		this.suspend = suspend;
	}
	

}
