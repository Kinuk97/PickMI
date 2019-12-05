package dto;

import java.util.Date;
import java.util.List;

public class Schedule {
	private int scheduleno;
	private int userno;
	private int proj_no;
	private String title;
	private String content;
	private String place;
	private Date schedule_date;
	private Date due_date;
	private Date write_date;
	private int cntChecked;
	private int cntCheckList;
	private List<CheckList> checkList;

	private String curYear;
	private String curMonth;

	@Override
	public String toString() {
		return "Schedule [scheduleno=" + scheduleno + ", userno=" + userno + ", proj_no=" + proj_no + ", title=" + title
				+ ", content=" + content + ", place=" + place + ", schedule_date=" + schedule_date + ", due_date="
				+ due_date + ", write_date=" + write_date + ", cntChecked=" + cntChecked + ", cntCheckList="
				+ cntCheckList + ", checkList=" + checkList + ", curYear=" + curYear + ", curMonth=" + curMonth + "]";
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Date getSchedule_date() {
		return schedule_date;
	}

	public void setSchedule_date(Date schedule_date) {
		this.schedule_date = schedule_date;
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

	public int getCntChecked() {
		return cntChecked;
	}

	public void setCntChecked(int cntChecked) {
		this.cntChecked = cntChecked;
	}

	public int getCntCheckList() {
		return cntCheckList;
	}

	public void setCntCheckList(int cntCheckList) {
		this.cntCheckList = cntCheckList;
	}

	public List<CheckList> getCheckList() {
		return checkList;
	}

	public void setCheckList(List<CheckList> checkList) {
		this.checkList = checkList;
	}

	public String getCurYear() {
		return curYear;
	}

	public void setCurYear(String curYear) {
		this.curYear = curYear;
	}

	public String getCurMonth() {
		return curMonth;
	}

	public void setCurMonth(String curMonth) {
		this.curMonth = curMonth;
	}

}
