package controller.Board.CompBoard;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.CompBoard;
import serivce.face.CompBoardService;
import serivce.impl.CompBoardServiceImpl;

@WebServlet("/compBoard/list")
public class CompBoardListController extends HttpServlet {

	private CompBoardService CompBoardService = new CompBoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//게시글 목록  
		List<CompBoard> List = CompBoardService.getBoardList();
		
		req.setAttribute("list", List);
		
		req.getRequestDispatcher("/WEB-INF/views/board/compBoard/list.jsp").forward(req, resp);
		
	}
	
}
