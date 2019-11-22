package dto;

import java.util.Date;

public class ProfileBoardDto {
	
	private int prof_no;
	private int userno;
	private Date prof_time;
	private String prof_interest;
	private String prof_job;
	private String prof_state;
	private String prof_loc;
	private String prof_career;
	private String prof_content;
	private int prof_like;
	
	
	@Override
	public String toString() {
		return "ProfileBoardDto [prof_no=" + prof_no + ", userno=" + userno + ", prof_time=" + prof_time
				+ ", prof_interest=" + prof_interest + ", prof_job=" + prof_job + ", prof_state=" + prof_state
				+ ", prof_loc=" + prof_loc + ", prof_career=" + prof_career + ", prof_content=" + prof_content
				+ ", prof_like=" + prof_like + "]";
	}
	
	public int getProf_no() {
		return prof_no;
	}
	
	public void setProf_no(int prof_no) {
		this.prof_no = prof_no;
	}
	
	public int getUserno() {
		return userno;
	}
	
	public void setUserno(int userno) {
		this.userno = userno;
	}
	
	public Date getProf_time() {
		return prof_time;
	}
	
	public void setProf_time(Date prof_time) {
		this.prof_time = prof_time;
	}
	
	public String getProf_interest() {
		return prof_interest;
	}
	
	public void setProf_interest(String prof_interest) {
		this.prof_interest = prof_interest;
	}
	
	public String getProf_job() {
		return prof_job;
	}
	
	public void setProf_job(String prof_job) {
		this.prof_job = prof_job;
	}
	
	public String getProf_state() {
		return prof_state;
	}
	
	public void setProf_state(String prof_state) {
		this.prof_state = prof_state;
	}
	
	public String getProf_loc() {
		return prof_loc;
	}
	
	public void setProf_loc(String prof_loc) {
		this.prof_loc = prof_loc;
	}
	public String getProf_career() {
		return prof_career;
	}
	
	public void setProf_career(String prof_career) {
		this.prof_career = prof_career;
	}
	
	public String getProf_content() {
		return prof_content;
	}
	
	public void setProf_content(String prof_content) {
		this.prof_content = prof_content;
	}
	
	public int getProf_like() {
		return prof_like;
	}
	
	public void setProf_like(int prof_like) {
		this.prof_like = prof_like;
	}
	
	

}
