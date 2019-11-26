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

@WebServlet("/freeboard/write")
public class FreeBoardWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private FreeBoardService freeBoardService = FreeBoardServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/board/freeboard/write.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		FreeBoard freeBoard = freeBoardService.getParam(req);
		
		if (freeBoardService.writeBoard(freeBoard)) {
			
			// 일단 list 나중에 view페이지로 보내주기
			resp.sendRedirect("/freeboard/list");
		} else {
			resp.sendRedirect("/login");
		}
	}
}
