package serivce.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.face.ProjectBoardDao;
import dao.impl.ProjectBoardDaoImpl;
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


		// Board TB와 curPage 값을 이용한 Paging 객체를 생성하고 반환
		int totalCount = projectBoardDao.selectCntAll();

		// Paging 객체 생성
		Paging paging = new Paging(totalCount, curPage, 20);


		return paging;
	}

	@Override
	public List<ProjectBoard> getBoardList(Paging paging) {
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
	public ProjectBoard view(ProjectBoard viewBoard) {
		
		return null;
	}

}
