package dto;

import java.util.Date;

public class FreeBoard {
	private int free_no;
	private int userno;
	private int categoryno;
	private String free_title;
	private String free_content;
	private Date free_time;
	private int views;

	@Override
	public String toString() {
		return "FreeBoard [free_no=" + free_no + ", userno=" + userno + ", categoryno=" + categoryno + ", free_title="
				+ free_title + ", free_content=" + free_content + ", free_time=" + free_time + ", views=" + views + "]";
	}

	public int getFree_no() {
		return free_no;
	}

	public void setFree_no(int free_no) {
		this.free_no = free_no;
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

	public String getFree_title() {
		return free_title;
	}

	public void setFree_title(String free_title) {
		this.free_title = free_title;
	}

	public String getFree_content() {
		return free_content;
	}

	public void setFree_content(String free_content) {
		this.free_content = free_content;
	}

	public Date getFree_time() {
		return free_time;
	}

	public void setFree_time(Date free_time) {
		this.free_time = free_time;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

}
