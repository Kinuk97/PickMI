package controller.Board.CompBoard;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dto.CompBoard;
import serivce.face.CompBoardService;
import serivce.impl.CompBoardServiceImpl;
import util.Paging;

@WebServlet("/compBoard/list")
public class CompBoardListController extends HttpServlet {

	private CompBoardService compBoardService = new CompBoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//요청파라미터에서 curPage를 구하고 Paging 객체 반환
		Paging paging = compBoardService.getPaging(req);
		
		
		System.out.println(paging);
		//Paging객체를 MODEL값으로 지정
		req.setAttribute("paging", paging);
		
		//VIEW지정
		req.getRequestDispatcher("/WEB-INF/views/board/compBoard/list.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		
		Paging paging = compBoardService.getPaging(req);
		
		Gson gson = new Gson();
		
		resp.getWriter().println(gson.toJson(compBoardService.getBoardList(paging)));
		
	}
	
	
	
}
