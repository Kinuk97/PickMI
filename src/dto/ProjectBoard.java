package dto;

import java.util.Date;

public class ProjectBoard {
	private int proj_no;
	private int userno;
	private String proj_title;
	private String proj_name;
	private Date proj_time;
	private String proj_loc;
	private String proj_career;
	private int proj_member;
	private int proj_apply;
	private Date proj_sdate;
	private Date proj_ddate;
	private Date proj_rec_date;
	private String proj_progress;
	private String proj_content;
	private int proj_like;
	private String proj_job;

	@Override
	public String toString() {
		return "ProjectBoard [proj_no=" + proj_no + ", userno=" + userno + ", proj_title=" + proj_title + ", proj_name="
				+ proj_name + ", proj_time=" + proj_time + ", proj_loc=" + proj_loc + ", proj_career=" + proj_career
				+ ", proj_member=" + proj_member + ", proj_apply=" + proj_apply + ", proj_sdate=" + proj_sdate
				+ ", proj_ddate=" + proj_ddate + ", proj_rec_date=" + proj_rec_date + ", proj_progress=" + proj_progress
				+ ", proj_content=" + proj_content + ", proj_like=" + proj_like +  ", proj_job=" + proj_job + "]";
	}

	public int getProj_no() {
		return proj_no;
	}

	public void setProj_no(int proj_no) {
		this.proj_no = proj_no;
	}

	public int getUserno() {
		return userno;
	}

	public void setUserno(int userno) {
		this.userno = userno;
	}

	public String getProj_title() {
		return proj_title;
	}

	public void setProj_title(String proj_title) {
		this.proj_title = proj_title;
	}

	public String getProj_name() {
		return proj_name;
	}

	public void setProj_name(String proj_name) {
		this.proj_name = proj_name;
	}

	public Date getProj_time() {
		return proj_time;
	}

	public void setProj_time(Date proj_time) {
		this.proj_time = proj_time;
	}

	public String getProj_loc() {
		return proj_loc;
	}

	public void setProj_loc(String proj_loc) {
		this.proj_loc = proj_loc;
	}

	public String getProj_career() {
		return proj_career;
	}

	public void setProj_career(String proj_career) {
		this.proj_career = proj_career;
	}

	public int getProj_member() {
		return proj_member;
	}

	public void setProj_member(int proj_member) {
		this.proj_member = proj_member;
	}

	public int getProj_apply() {
		return proj_apply;
	}

	public void setProj_apply(int proj_apply) {
		this.proj_apply = proj_apply;
	}

	public Date getProj_sdate() {
		return proj_sdate;
	}

	public void setProj_sdate(Date proj_sdate) {
		this.proj_sdate = proj_sdate;
	}

	public Date getProj_ddate() {
		return proj_ddate;
	}

	public void setProj_ddate(Date proj_ddate) {
		this.proj_ddate = proj_ddate;
	}

	public Date getProj_rec_date() {
		return proj_rec_date;
	}

	public void setProj_rec_date(Date proj_rec_date) {
		this.proj_rec_date = proj_rec_date;
	}

	public String getProj_progress() {
		return proj_progress;
	}

	public void setProj_progress(String proj_progress) {
		this.proj_progress = proj_progress;
	}

	public String getProj_content() {
		return proj_content;
	}

	public void setProj_content(String proj_content) {
		this.proj_content = proj_content;
	}

	public int getProj_like() {
		return proj_like;
	}

	public String getProj_job() {
		return proj_job;
	}

	public void setProj_job(String proj_job) {
		this.proj_job = proj_job;
	}

	public void setProj_like(int proj_like) {
		this.proj_like = proj_like;
	}

}
