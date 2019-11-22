package dto;

public class User {
	private int userno;
	private String email;
	private String pw;
	private String name;
	private String photo_originname;
	private String photo_storedname;

	@Override
	public String toString() {
		return "User [userno=" + userno + ", email=" + email + ", pw=" + pw + ", name=" + name + ", photo_originname="
				+ photo_originname + ", photo_storedname=" + photo_storedname + "]";
	}

	public int getUserno() {
		return userno;
	}

	public void setUserno(int userno) {
		this.userno = userno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoto_originname() {
		return photo_originname;
	}

	public void setPhoto_originname(String photo_originname) {
		this.photo_originname = photo_originname;
	}

	public String getPhoto_storedname() {
		return photo_storedname;
	}

	public void setPhoto_storedname(String photo_storedname) {
		this.photo_storedname = photo_storedname;
	}

}
