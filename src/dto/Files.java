package dto;

import java.text.DecimalFormat;

public class Files {

	private int fileno;
	private int postno;
	private int boardno;
	private String originName;
	private String storedName;
	private long fileLength;
	private String fileSize;

	@Override
	public String toString() {
		return "Files [fileno=" + fileno + ", postno=" + postno + ", boardno=" + boardno + ", originName=" + originName
				+ ", storedName=" + storedName + ", fileLength=" + fileLength + ", fileSize=" + fileSize + "]";
	}

	public int getFileno() {
		return fileno;
	}

	public void setFileno(int fileno) {
		this.fileno = fileno;
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

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getStoredName() {
		return storedName;
	}

	public void setStoredName(String storedName) {
		this.storedName = storedName;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	// long으로 받은 파일사이즈를 KB MB를 붙여서 저장한다.
	public void setFileSize(long size) {
		final String[] units = new String[] { "B", "kB", "MB", "GB", "TB" };
		int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
		this.fileSize = new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " "
				+ units[digitGroups];
	}

}
