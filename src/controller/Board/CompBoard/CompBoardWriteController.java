package controller.Board.CompBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import serivce.face.CompBoardService;
import serivce.impl.CompBoardServiceImpl;

@WebServlet("/compBoard/write")
public class CompBoardWriteController extends HttpServlet {
	
	private CompBoardService compBoardService = new CompBoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/views/board/compBoard/write.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
//		compBoardService.write(compBoard);
		
		resp.sendRedirect("/compBoard/list");
		
		
	}
	
	
	
	

}
