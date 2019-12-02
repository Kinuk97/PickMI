package dto;

public class Chat {
	
	private int chat_no;
	private int chat_sender;
	
	@Override
	public String toString() {
		return "Chat [chat_no=" + chat_no + ", chat_sender=" + chat_sender + "]";
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
	
	
}
