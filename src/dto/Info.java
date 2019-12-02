package dto;

import java.util.Date;

public class Info {
	
	private int chat_no;
	private Date chat_maketime;
	
	@Override
	public String toString() {
		return "Info [chat_no=" + chat_no + "]";
	}

	public int getChat_no() {
		return chat_no;
	}

	public void setChat_no(int chat_no) {
		this.chat_no = chat_no;
	}

	public Date getChat_maketime() {
		return chat_maketime;
	}

	public void setChat_maketime(Date chat_maketime) {
		this.chat_maketime = chat_maketime;
	}

	
	
	
	
	
}
