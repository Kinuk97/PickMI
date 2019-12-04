package dto;

import java.util.Date;

public class Chat {
	
	private int chat_no;
	private int chat_sender;
	private String chat_msg;
	private String chat_sendtime;
//	private Date chat_sendtime;
	private String username;
	

	@Override
	public String toString() {
		return "Chat [chat_no=" + chat_no + ", chat_sender=" + chat_sender + ", chat_msg=" + chat_msg
				+ ", chat_sendtime=" + chat_sendtime + ", username=" + username + "]";
	}

	public int getChat_no() {
		return chat_no;
	}

	public void setChat_no(int chat_no) {
		this.chat_no = chat_no;
	}

	public int getChat_sender() {
		return chat_sender;
	}

	public void setChat_sender(int chat_sender) {
		this.chat_sender = chat_sender;
	}

	public String getChat_msg() {
		return chat_msg;
	}

	public void setChat_msg(String chat_msg) {
		this.chat_msg = chat_msg;
	}

	public String getChat_sendtime() {
		return chat_sendtime;
	}

	public void setChat_sendtime(String chat_sendtime) {
		this.chat_sendtime = chat_sendtime;
	}
//	public Date getChat_sendtime() {
//		return chat_sendtime;
//	}
//	
//	public void setChat_sendtime(Date chat_sendtime) {
//		this.chat_sendtime = chat_sendtime;
//	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
}
