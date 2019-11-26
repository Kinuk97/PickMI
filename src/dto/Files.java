package dto;

public class Files {
	
	private int fileno;
	private int postno;
	private String filename;
	private String filepath;
	private int boardno;
	
	@Override
	public String toString() {
		return "Files [fileno=" + fileno + ", postno=" + postno + ", filename=" + filename + ", filepath=" + filepath
				+ ", boardno=" + boardno + "]";
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

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public int getBoardno() {
		return boardno;
	}

	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}
	
	

}
