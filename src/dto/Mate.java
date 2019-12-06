package dto;

public class Mate {
	
	private int userno;
	private int proj_no;
	private char mate;
	private String username;
	private String proj_title;
	
	
	
	
	
	
	
	@Override
	public String toString() {
		return "Mate [userno=" + userno + ", proj_no=" + proj_no + ", mate=" + mate + ", username=" + username
				+ ", proj_title=" + proj_title + "]";
	}


	public String getProj_title() {
		return proj_title;
	}


	public void setProj_title(String proj_title) {
		this.proj_title = proj_title;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
	public char getMate() {
		return mate;
	}
	public void setMate(char mate) {
		this.mate = mate;
	}
	
	

}
