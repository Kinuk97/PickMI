package dto;

import java.util.Date;

public class Schedule {
	private int scheduleno;
	private int userno;
	private int proj_no;
	private String content;
	private String task;
	private String place;
	private Date term_start;
	private Date term_last;
	private Date write_date;

	@Override
	public String toString() {
		return "Schedule [scheduleno=" + scheduleno + ", userno=" + userno + ", proj_no=" + proj_no + ", content="
				+ content + ", task=" + task + ", place=" + place + ", term_start=" + term_start + ", term_last="
				+ term_last + ", write_date=" + write_date + "]";
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

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Date getTerm_start() {
		return term_start;
	}

	public void setTerm_start(Date term_start) {
		this.term_start = term_start;
	}

	public Date getTerm_last() {
		return term_last;
	}

	public void setTerm_last(Date term_last) {
		this.term_last = term_last;
	}

	public Date getWrite_date() {
		return write_date;
	}

	public void setWrite_date(Date write_date) {
		this.write_date = write_date;
	}

}
