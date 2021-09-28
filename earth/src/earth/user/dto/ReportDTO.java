package earth.user.dto;

import java.sql.Timestamp;

public class ReportDTO {
	private Integer reportnum;
	private String id;
	private String receiver;
	private Timestamp reg;
	private String ctt;
	private Integer condition;
	private Integer boardnum;
	private Integer process;
	private String reportctt;
	
	public String getReportctt() {
		return reportctt;
	}
	public void setReportctt(String reportctt) {
		this.reportctt = reportctt;
	}
	public Integer getReportnum() {
		return reportnum;
	}
	public void setReportnum(Integer reportnum) {
		this.reportnum = reportnum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public Timestamp getReg() {
		return reg;
	}
	public void setReg(Timestamp reg) {
		this.reg = reg;
	}
	public String getCtt() {
		return ctt;
	}
	public void setCtt(String ctt) {
		this.ctt = ctt;
	}
	public Integer getCondition() {
		return condition;
	}
	public void setCondition(Integer condition) {
		this.condition = condition;
	}
	public Integer getBoardnum() {
		return boardnum;
	}
	public void setBoardnum(Integer boardnum) {
		this.boardnum = boardnum;
	}
	public Integer getProcess() {
		return process;
	}
	public void setProcess(Integer process) {
		this.process = process;
	}
	

}
