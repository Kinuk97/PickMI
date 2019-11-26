package serivce.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.face.ManagerDao;
import dao.impl.ManagerDaoImpl;
import dto.Manager;
import dto.ProfileBoard;
import serivce.face.MgrService;

public class MgrServiceImpl implements MgrService{

	//ManagerDao 객체
	private ManagerDao managerDao = new ManagerDaoImpl();
	
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

	@Override
	public List<ProfileBoard> getpbList() {
		return managerDao.pbselectAll();
	}
	
	
	

}
