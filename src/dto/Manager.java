package dto;

public class Manager {
	
	public String mgrid;
	public String mgrpw;
	
	@Override
	public String toString() {
		return "Manager [mgrid=" + mgrid + ", mgrpw=" + mgrpw + "]";
	}

	public String getMgrid() {
		return mgrid;
	}

	public void setMgrid(String mgrid) {
		this.mgrid = mgrid;
	}

	public String getMgrpw() {
		return mgrpw;
	}

	public void setMgrpw(String mgrpw) {
		this.mgrpw = mgrpw;
	}
	
	
	
	
}
