package serivce.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.face.MateDao;
import dao.impl.MateDaoImpl;
import dto.Mate;
import dto.ProjectBoard;
import serivce.face.MateService;

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
	public boolean checkLeader(Mate mate) {
		
		int cnt = mateDao.countLeader(mate);
		
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
	public Mate getUsernoByProjectno(Mate mate2) {
		return mateDao.selectUsernoByProjectno(mate2);
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
	public Mate addMate(Mate mate) {
		// TODO Auto-generated method stub
		return null;
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
		
		char check = mate.getMate();
		if (check == '0') {
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
