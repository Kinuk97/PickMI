package dto;

public class Chatter {

	private int chat_no;
	private int chat_user;
	
	@Override
	public String toString() {
		return "Chatter [chat_no=" + chat_no + ", chat_user=" + chat_user + "]";
	}

	public int getChat_no() {
		return chat_no;
	}

	public void setChat_no(int chat_no) {
		this.chat_no = chat_no;
	}

	public int getChat_user() {
		return chat_user;
	}

	public void setChat_user(int chat_user) {
		this.chat_user = chat_user;
	}

	
	
	
}
