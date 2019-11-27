package controller.Board.CompBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.face.CompBoardDao;
import dao.impl.CompBoardDaoImpl;
import dto.CompBoard;
import serivce.face.CompBoardService;
import serivce.impl.CompBoardServiceImpl;

@WebServlet("/compBoard/view")
public class CompBoardViewController extends HttpServlet {
	
	private CompBoardService compBoardService = new CompBoardServiceImpl();
	private CompBoardDao compBoardDao = new CompBoardDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//요청 파라미터 얻기
		CompBoard compBoard = compBoardService.getParam(req);
		
		compBoardDao.countViews(compBoard);
		
		System.out.println(compBoard);
		
		CompBoard compBoardView = compBoardService.compBoardDetail(compBoard);
		
		req.setAttribute("compBoard", compBoardView);
		
		req.getRequestDispatcher("/WEB-INF/views/board/compBoard/view.jsp").forward(req, resp);
	
	}
	
	
	
	
}
