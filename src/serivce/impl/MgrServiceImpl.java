package serivce.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.face.ManagerDao;
import dao.impl.ManagerDaoImpl;
import dto.Manager;
import dto.ProfileBoard;
import dto.ProjectBoard;
import dto.User;
import serivce.face.MgrService;
import util.Paging;

public class MgrServiceImpl implements MgrService{
	
	//ManagerDao 객체
	private ManagerDao managerDao = ManagerDaoImpl.getInstance();

	private MgrServiceImpl() {		
	}
	
	private static class Singleton{
		private static final MgrService instance = new MgrServiceImpl();
	}
	
	public static MgrService getInstance() {
		return Singleton.instance;
	}
	
// Login -----
	
	@Override
	public Manager getLoginMgr(HttpServletRequest req) {

		Manager mgr = new Manager();
		
		mgr.setMgrid(req.getParameter("mgrid"));
		mgr.setMgrpw(req.getParameter("mgrpw"));
		
		return mgr;
	}

	@Override
	public boolean login(Manager mgr) {
		
		int cnt=0;
		cnt = managerDao.selectCntMemberByMgridAndMgrpw(mgr);
		
//		System.out.println("로그인 정보 존재여부: " + cnt);
		
		// 존재하면 true 반환
		//	아니면 false 반환
		if(cnt == 1	) {
			return true;
		} else {
			return false;
		}
		
	}

	@Override
	public Manager getMgrByMgrid(Manager mgr) {
			
		return managerDao.selectMgrByMgr(mgr);
	}
// ----- Login

	
// ----- User List 
	
	@Override
	public User getParam(HttpServletRequest req) {
		User user = new User();
		
		String param = req.getParameter("userno"); //getParameter는 반환타입이 String
		if (param != null && !"".equals(param)){
			try {
				user.setUserno(Integer.parseInt(param)); //parseint를 하다가 숫자가 아니면				
			} catch (NumberFormatException e) {				// NumberFormatException
				e.printStackTrace();					 
			}
		}
		
		param = req.getParameter("email");
		if (param != null && !"".equals(param)) {
			try {
				user.setEmail(param);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		
		param = req.getParameter("name");
		if (param != null && !"".equals(param)) {
			try {
				user.setName(param);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}

		
		
		return user;
	}

	
	@Override
	public Paging getPaging(HttpServletRequest req) {
		
		// 요청파라미터 curPage 파싱
		String param = req.getParameter("curPage");
		int curPage = 0;
		if ( param!=null && !"".equals(param)) {
			try {
				curPage = Integer.parseInt(param);		
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
//		System.out.println("curPage : " + curPage);
		
		param = req.getParameter("search");
		String search = null;
		if (param != null && !"".equals(param)) {
			search = param;
		}
//		System.out.println(search);
		
		// ProfileBoard TB와 curPage 값을 이용한 Paging 객체를 생성하고 반환
		int totalCount = managerDao.selectCntAll(search);
			
		// Paging 객체 생성
		Paging paging = new Paging(totalCount, curPage, 20);
		
		return paging;
	}
	
	@Override
	public List<User> getuserList(Paging paging) {
		return managerDao.userselectAll(paging);
	}
	
	@Override
	public boolean removeUser(User user) {

		int result = managerDao.deleteUser(user);
		
		if (result == 1) {
			return true;
		} else {
			return false;
		}		
	}


// ----- User List
	
//	@Override
//	public List<ProfileBoard> getPfBoardList() {
//		return managerDao.profileselectAll();
//	}

	@Override
	public ProfileBoard getPfParam(HttpServletRequest req) {
		
		ProfileBoard profileBoard = new ProfileBoard();

		String param = req.getParameter("prof_no");
		if (param != null && !"".equals(param)) {
			try {
				profileBoard.setProf_no(Integer.parseInt(param));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		
		Object userno = req.getSession().getAttribute("userno");
		if (userno != null && !"".equals(userno)) {
			try {
				profileBoard.setUserno((Integer) userno);
			} catch (ClassCastException e) {
				e.printStackTrace();
			}
		}
		return profileBoard;
	}
	
	@Override
	public List<ProfileBoard> getPfBoardList(Paging paging) {
		return managerDao.profileselectAll(paging);
	}
// ProfileBoard -----

// ----- ProjectBoard
	@Override
	public List<ProjectBoard> getPjBoardList() {
		return managerDao.projectselectAll();
	}

	@Override
	public List<ProjectBoard> getPjBoardList(Paging paging) {
		return managerDao.projectselectAll(paging);
	}



	

	
	
	

}
