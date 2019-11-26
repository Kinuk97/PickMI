package dto;

public class LikePost {
	
	private int userno;
	private int postno;
	private int boardno;
	
	@Override
	public String toString() {
		return "LikePost [userno=" + userno + ", postno=" + postno + ", boardno=" + boardno + "]";
	}
	
	public int getUserno() {
		return userno;
	}
	
	public void setUserno(int userno) {
		this.userno = userno;
	}
	
	public int getPostno() {
		return postno;
	}
	
	public void setPostno(int postno) {
		this.postno = postno;
	}
	
	public int getBoardno() {
		return boardno;
	}
	
	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}
	
	

}
