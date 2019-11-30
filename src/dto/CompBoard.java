package dto;

import java.util.Date;

public class CompBoard {
	
//	private int rownum;
	private int comp_no;
	private int categoryno;
	private int userno;
	private String username;
	private String comp_title;
	private String comp_name;
	private String comp_content;
	private int comp_member;
	private Date comp_date;
	private int comp_view;
	private int comp_reply;
	private int comp_like;
	private int comp_startdate;
	private int comp_enddate;
	

	@Override
	public String toString() {
		return "CompBoard [comp_no=" + comp_no + ", categoryno=" + categoryno + ", userno=" + userno + ", username="
				+ username + ", comp_title=" + comp_title + ", comp_name=" + comp_name + ", comp_content="
				+ comp_content + ", comp_member=" + comp_member + ", comp_date=" + comp_date + ", comp_view="
				+ comp_view + ", comp_reply=" + comp_reply + ", comp_like=" + comp_like + ", comp_startdate="
				+ comp_startdate + ", comp_enddate=" + comp_enddate + "]";
	}

	public int getComp_no() {
		return comp_no;
	}

	public void setComp_no(int comp_no) {
		this.comp_no = comp_no;
	}

	public int getCategoryno() {
		return categoryno;
	}

	public void setCategoryno(int categoryno) {
		this.categoryno = categoryno;
	}

	public int getUserno() {
		return userno;
	}

	public void setUserno(int userno) {
		this.userno = userno;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getComp_title() {
		return comp_title;
	}

	public void setComp_title(String comp_title) {
		this.comp_title = comp_title;
	}

	public String getComp_name() {
		return comp_name;
	}

	public void setComp_name(String comp_name) {
		this.comp_name = comp_name;
	}

	public String getComp_content() {
		return comp_content;
	}

	public void setComp_content(String comp_content) {
		this.comp_content = comp_content;
	}

	public int getComp_member() {
		return comp_member;
	}

	public void setComp_member(int comp_member) {
		this.comp_member = comp_member;
	}

	public Date getComp_date() {
		return comp_date;
	}

	public void setComp_date(Date comp_date) {
		this.comp_date = comp_date;
	}

	public int getComp_view() {
		return comp_view;
	}

	public void setComp_view(int comp_view) {
		this.comp_view = comp_view;
	}

	public int getComp_reply() {
		return comp_reply;
	}

	public void setComp_reply(int comp_reply) {
		this.comp_reply = comp_reply;
	}

	public int getComp_like() {
		return comp_like;
	}

	public void setComp_like(int comp_like) {
		this.comp_like = comp_like;
	}

	public int getComp_startdate() {
		return comp_startdate;
	}

	public void setComp_startdate(int comp_startdate) {
		this.comp_startdate = comp_startdate;
	}

	public int getComp_enddate() {
		return comp_enddate;
	}

	public void setComp_enddate(int comp_enddate) {
		this.comp_enddate = comp_enddate;
	}
	
	

}
