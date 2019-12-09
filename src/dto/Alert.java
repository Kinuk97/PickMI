package dto;

import java.util.Date;

public class Alert {
	
	private int userno;
	private String alert;
	private Date alerttime;
	private int sender;
	private char checkcheck;
	
	@Override
	public String toString() {
		return "Alert [userno=" + userno + ", alert=" + alert + ", alerttime=" + alerttime + ", sender=" + sender
				+ ", checkcheck=" + checkcheck + "]";
	}
	
	public int getUserno() {
		return userno;
	}
	public void setUserno(int userno) {
		this.userno = userno;
	}
	public String getAlert() {
		return alert;
	}
	public void setAlert(String alert) {
		this.alert = alert;
	}
	public Date getAlerttime() {
		return alerttime;
	}
	public void setAlerttime(Date alerttime) {
		this.alerttime = alerttime;
	}
	public int getSender() {
		return sender;
	}
	public void setSender(int sender) {
		this.sender = sender;
	}
	public char getCheckcheck() {
		return checkcheck;
	}
	public void setCheckcheck(char checkcheck) {
		this.checkcheck = checkcheck;
	}
	
	

}
