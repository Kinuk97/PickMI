package controller.Board.ProjectBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import serivce.face.ProjectBoardService;
import serivce.impl.ProjectBoardServiceImpl;
import util.Paging;

@WebServlet("/projectBoard/list")
public class ProjectBoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProjectBoardService projectBoardService = new ProjectBoardServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Paging paging = projectBoardService.getPaging(req);

		req.setAttribute("paging", paging);
		req.setAttribute("boardList", projectBoardService.getBoardList(paging));

		req.getRequestDispatcher("/WEB-INF/views/board/projectBoard/list.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");

		Paging paging = projectBoardService.getPaging(req);

		Gson gson = new Gson();

		resp.getWriter().println(gson.toJson(projectBoardService.getBoardList(paging)));
	}
}
