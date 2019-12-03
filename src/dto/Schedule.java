package dto;

import java.util.Date;
import java.util.List;

public class Schedule {
	private int scheduleno;
	private int userno;
	private int proj_no;
	private String content;
	private String place;
	private Date due_date;
	private Date write_date;
	List<CheckList> checkList;

	@Override
	public String toString() {
		return "Schedule [scheduleno=" + scheduleno + ", userno=" + userno + ", proj_no=" + proj_no + ", content="
				+ content + ", place=" + place + ", due_date=" + due_date + ", write_date=" + write_date + "]";
	}

	public int getScheduleno() {
		return scheduleno;
	}

	public void setScheduleno(int scheduleno) {
		this.scheduleno = scheduleno;
	}

	public int getUserno() {
		return userno;
	}

	public void setUserno(int userno) {
		this.userno = userno;
	}

	public int getProj_no() {
		return proj_no;
	}

	public void setProj_no(int proj_no) {
		this.proj_no = proj_no;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Date getDue_date() {
		return due_date;
	}

	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}

	public Date getWrite_date() {
		return write_date;
	}

	public void setWrite_date(Date write_date) {
		this.write_date = write_date;
	}

}
