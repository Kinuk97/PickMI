package serivce.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.face.MateDao;
import dao.face.ProfileBoardDao;
import dao.impl.MateDaoImpl;
import dao.impl.ProfileBoardDaoImpl;
import dto.Mate;
import dto.ProfileBoard;
import dto.ProjectBoard;
import dto.User;
import serivce.face.AlertService;
import serivce.face.MateService;
import sun.java2d.cmm.Profile;

public class MateServiceImpl implements MateService {
	
	private MateDao mateDao = MateDaoImpl.getInstance();

	
	public MateServiceImpl() {
		
	}
	
	private static class Singleton {
		private static final MateService instance = new MateServiceImpl();
	}

	public static MateService getInstance() {
		return Singleton.instance;
	}
	
	@Override
	public List<User> showUserName(Mate mate) {
		return mateDao.showUserName(mate);
	}
	
	@Override
	public List<ProfileBoard> showUser(Mate mate) {
		
		
		return mateDao.showUser(mate);
	}
	
	@Override
	public List<Mate> appliedUser(Mate mate) {
		
		return mateDao.selectUsers(mate);
	}
	
	@Override
	public int checkJoin(Mate mate) {
		//전에가입한적 있는지 확인하기
		int cnt = mateDao.countMyTeam(mate);
		
		if (cnt == 0) {
			return -1; // 가입한 적 없음
		} else {
		//메이트 상태 확인하기
		mateDao.selectMylog(mate);
		
		int check = mate.getMate();
		
		return check;
		}
	}
	
	@Override
	public List<Mate> waitingAnswer(Mate mate) {
		return mateDao.selectWantToJoinList(mate);
	}
	
	@Override
	public void wantToJoin(Mate mate) {
		mateDao.insertMate(mate);
		
	}
	
	@Override
	public boolean checkLeader(Mate mate) {
		
		int cnt = mateDao.countLeader(mate);
//		System.out.println("mate service , check leader  : " + cnt);
		if ( cnt > 0 ) {
			return true; //팀장인 유저
			}
		return false; //팀장이 아님
	}
	
	@Override
	public void removeUserFromTeam(Mate mate) {
		mateDao.deleteUserFromTeam(mate);
	}
	/**
	 * 프로젝트게시번호로 작성자(유저번호)를 조회한다
	 */
	@Override
	public List<Mate> getUsernoByProjectno(Mate mate) {
		return mateDao.selectUsernoByProjectno(mate);
	}
	@Override
	public int getProj_no(Mate mate) {
		return mateDao.selectProj_no(mate);
	}
	
	@Override
	public List<Mate> getProj_noByUserno(Mate mate) {
		return mateDao.selectProj_noByUserno(mate);
	}
	
	@Override
	public void addMate(Mate mate) {
		mateDao.updateMate(mate);
	}
	
	@Override
	public List<Mate> getMateList(Mate mate) {
		return mateDao.selectMateList(mate);
	}
	
	@Override
	public Mate getParam(HttpServletRequest req) {
		String param = req.getParameter("userno");
		
		int userno = 0;
		if (param != null && !"".equals(param)) {
			userno = Integer.parseInt(param);
		}
		
		param = req.getParameter("proj_no");
		int proj_no = 0;
		if (param != null && !"".equals(param)) {
			try {
				proj_no = Integer.parseInt(param);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		
		param = req.getParameter("mate");
		char mate_check = 0;
		if (param != null && !"".equals(param)) {
			mate_check = param.charAt(0);
		}
		
		Mate mate = new Mate();
		mate.setUserno(userno);
		mate.setProj_no(proj_no);
		mate.setMate(mate_check);
		return mate;
	}
	
	@Override
	public boolean mateCheck(Mate mate) {
		
		int check = mate.getMate();
		if (check == 0 ) {
			return false; //가입 안되있음		
		} else {
			return true; //가입 되있음
		}
	}
	
	@Override
	public List<ProjectBoard> getMyProjList(Mate mate) {
		return mateDao.myProjectList(mate);
	}

}
