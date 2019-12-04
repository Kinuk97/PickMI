package dto;

public class CheckList {
	private int checkno;
	private int scheduleno;
	private String check_content;
	private char do_check;

	@Override
	public String toString() {
		return "CheckList [checkno=" + checkno + ", scheduleno=" + scheduleno + ", check_content=" + check_content
				+ ", do_check=" + do_check + "]";
	}

	public int getCheckno() {
		return checkno;
	}

	public void setCheckno(int checkno) {
		this.checkno = checkno;
	}

	public int getScheduleno() {
		return scheduleno;
	}

	public void setScheduleno(int scheduleno) {
		this.scheduleno = scheduleno;
	}

	public String getCheck_content() {
		return check_content;
	}

	public void setCheck_content(String check_content) {
		this.check_content = check_content;
	}

	public char getDo_check() {
		return do_check;
	}

	public void setDo_check(char do_check) {
		this.do_check = do_check;
	}

}
