package controller.Board.FreeBoard;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import serivce.face.FreeBoardService;
import serivce.impl.FreeBoardServiceImpl;
import util.Paging;

@WebServlet("/freeboard/list")
public class FreeBoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private FreeBoardService freeBoardService = FreeBoardServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Paging paging = freeBoardService.getPaging(req);
		
		req.setAttribute("postno", 3);
		req.setAttribute("search", paging.getSearch());
		req.setAttribute("categoryno", paging.getCategoryno());
		req.setAttribute("boardList", freeBoardService.getBoardList(paging));
		
		req.getRequestDispatcher("/WEB-INF/views/board/list.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		
		Paging paging = freeBoardService.getPaging(req);
		
		Gson gson = new Gson();
		
		resp.getWriter().println(gson.toJson(freeBoardService.getBoardList(paging)));
	}

}
