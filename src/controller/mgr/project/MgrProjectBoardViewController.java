package controller.mgr.project;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ProjectBoard;
import serivce.face.ProjectBoardService;
import serivce.impl.ProjectBoardServiceImpl;

/**
 * Servlet implementation class MgrProjectBoardViewController
 */
@WebServlet("/mgr/projectview")
public class MgrProjectBoardViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ProjectBoardService projectBoardService = new ProjectBoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 로그인 됐을 경우
		if(req.getSession().getAttribute("mgrlogin") != null) {
			
			ProjectBoard projectBoard = projectBoardService.getProjectBoardno(req);
			
			projectBoard = projectBoardService.view(projectBoard);
			
			req.setAttribute("projectBoard", projectBoard);
			//menuTab 모델값
			req.setAttribute("boardno", 3);
			
			req.getRequestDispatcher("/WEB-INF/views/mgr/project/projectview.jsp").forward(req, resp);
		} else {
			// 로그인 안됐을 경우
			req.getRequestDispatcher("/WEB-INF/views/mgr/layouts/mustlogin.jsp")
			.forward(req, resp);

		}
	}
}
