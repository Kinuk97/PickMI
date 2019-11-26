package controller.Board.FreeBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.FreeBoard;
import serivce.face.FreeBoardService;
import serivce.impl.FreeBoardServiceImpl;

@WebServlet("/freeboard/view")
public class FreeBoardViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private FreeBoardService freeBoardService = FreeBoardServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		FreeBoard freeBoard = freeBoardService.getParam(req);
		
		freeBoard = freeBoardService.FreeBoardDetail(freeBoard);
		
		if (freeBoard != null) {
			req.setAttribute("board", freeBoard);
			
			req.getRequestDispatcher("/WEB-INF/views/board/freeboard/view.jsp").forward(req, resp);
		} else {
			resp.sendRedirect("/freeboard/list");
		}
		
	}
}
