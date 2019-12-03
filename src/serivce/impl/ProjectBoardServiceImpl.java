package serivce.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.face.ProjectBoardDao;
import dao.impl.ProjectBoardDaoImpl;
import dto.LikePost;
import dto.ProjectBoard;
import serivce.face.FreeBoardService;
import serivce.face.ProjectBoardService;
import util.Paging;

public class ProjectBoardServiceImpl implements ProjectBoardService {

	private ProjectBoardDao projectBoardDao = ProjectBoardDaoImpl.getInstance();
	
	private static class Singleton {
		private static final ProjectBoardService instance = new ProjectBoardServiceImpl();
	}

	public static ProjectBoardService getInstance() {

		return Singleton.instance;
	}

	@Override
	public Paging getPaging(HttpServletRequest req) {
		String param = req.getParameter("curPage");
		int curPage = 0;
		if (param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		}
		
		param = req.getParameter("proj_loc");
		String proj_loc = "";
		if (param != null && !"".equals(param)) {
			proj_loc = param;
		}
		
		param = req.getParameter("proj_progress");
		String proj_progress = "";
		if (param != null && !"".equals(param)) {
			proj_progress = param;
		}
		
		param = req.getParameter("proj_job");
		String proj_job = "";
		if (param != null && !"".equals(param)) {
			proj_job = param;
		}
		
		param = req.getParameter("proj_career");
		String proj_career = "";
		if (param != null && !"".equals(param)) {
			proj_career = param;
		}


		// Board TB와 curPage 값을 이용한 Paging 객체를 생성하고 반환
		int totalCount = projectBoardDao.selectCntAll();

		// Paging 객체 생성
		Paging paging = new Paging(totalCount, curPage, 20);
		
		paging.setProj_loc(proj_loc);
		paging.setProj_progress(proj_progress);
		paging.setProj_job(proj_job);
		paging.setProj_career(proj_career);

		return paging;
	}

	@Override
	public List<ProjectBoard> getBoardList(Paging paging) {
		
//		if(!paging.getProj_loc().equals(""))
//		projectBoardDao.selectBoardListByLoc(paging);
		
		return projectBoardDao.selectAll(paging);
	}

	@Override
	public ProjectBoard getProjectBoardno(HttpServletRequest req) {
		
		String param = null;	
		param = req.getParameter("proj_no");
		
		int projno = 0;
		if(param != null && !"".equals(param)) {
			projno = Integer.parseInt(param);
		}
		
		ProjectBoard projectBoard = new ProjectBoard();
		projectBoard.setProj_no(projno);
		
		return projectBoard;
	}

	@Override
	public ProjectBoard view(ProjectBoard projectBoard) {
		
		return projectBoardDao.selectBoardByProjectno(projectBoard);
	}

	@Override
	public void delete(ProjectBoard projectBoard) {
		projectBoardDao.deleteProjBoard(projectBoard);
		
	}

	@Override
	public LikePost getLike(HttpServletRequest req) {
		//전달파라미터 파싱
		int proj_no = 0;
		String param = req.getParameter("boardno");
		if( param!=null && !"".equals(param) ) {
			proj_no = Integer.parseInt(param);
		}

		LikePost like = new LikePost();
		like.setBoardno(proj_no);
		like.setPostno(2);
		
		HttpSession session = req.getSession();
		
		like.setUserno((int) session.getAttribute("userno"));
		

		return like;
	}

	@Override
	public boolean like(LikePost like) {
		if(isLike(like)) { // 추천한 상태
			projectBoardDao.deleteLike(like);
			
			return false;
		} else { // 추천하지 않은 상태
			projectBoardDao.insertLike(like);
			
			return true;
		}
		
	}

	@Override
	public int getTotalCntLike(LikePost like) {

		return projectBoardDao.selectTotalCntLike(like);
	}

	@Override
	public boolean isLike(LikePost like) {
		int cnt = projectBoardDao.selectCntLike(like);
		
		if(cnt > 0) { //추천
			return true;
		} else { // 추천X
			return false;			
		}
	}

}
