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
import util.Paging;

@WebServlet("/compBoard/list")
public class CompBoardListController extends HttpServlet {

	private CompBoardService compBoardService = new CompBoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//요청파라미터에서 curPage를 구하고 Paging 객체 반환
		Paging paging = compBoardService.getPaging(req);
		
		//Paging객체를 MODEL값으로 지정
		req.setAttribute("paging", paging);
		
		//게시글 목록 조회
		List<CompBoard> List = compBoardService.getBoardList(paging);
		
		req.setAttribute("compList", List);
		
		System.out.println(List);
		
		//VIEW지정
		req.getRequestDispatcher("/WEB-INF/views/board/compBoard/list.jsp").forward(req, resp);
		
	}
	
}
