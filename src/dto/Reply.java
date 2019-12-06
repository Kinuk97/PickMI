package dto;

import java.util.Date;

public class Reply {
	private int replyno;
	private int boardno;
	private int postno;
	private int userno;
	private String username;
	private String reply;
	private Date replytime;

	private String title;

	@Override
	public String toString() {
		return "Reply [replyno=" + replyno + ", boardno=" + boardno + ", postno=" + postno + ", userno=" + userno
				+ ", username=" + username + ", reply=" + reply + ", replytime=" + replytime + ", title=" + title + "]";
	}

	public int getReplyno() {
		return replyno;
	}

	public void setReplyno(int replyno) {
		this.replyno = replyno;
	}

	public int getBoardno() {
		return boardno;
	}

	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}

	public int getPostno() {
		return postno;
	}

	public void setPostno(int postno) {
		this.postno = postno;
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

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public Date getReplytime() {
		return replytime;
	}

	public void setReplytime(Date replytime) {
		this.replytime = replytime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
