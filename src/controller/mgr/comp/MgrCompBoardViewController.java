package controller.mgr.comp;

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

/**
 * Servlet implementation class MgrCompBoardViewController
 */
@WebServlet("/mgr/compview")
public class MgrCompBoardViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CompBoardService compBoardService = new CompBoardServiceImpl();
	private CompBoardDao compBoardDao = new CompBoardDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 관리자 로그인 됐을 경우
		if(req.getSession().getAttribute("mgrlogin") != null) {
			
//		System.out.println("두겟 확인용 :");
			
			//요청 파라미터 얻기
			CompBoard compBoard = compBoardService.getParam(req);
			
			// 조회수올리기
//		compBoardDao.countViews(compBoard);	
//		System.out.println("compBoard : " + compBoard);
			
			CompBoard compBoardView = compBoardService.compBoardDetail(compBoard);
			req.setAttribute("compBoard", compBoardView);
//		System.out.println("compBoardView : " + compBoardView);
			
			req.getRequestDispatcher("/WEB-INF/views/mgr/comp/compview.jsp").forward(req, resp);

		} else {
			// 관리자 로그인 안됐을 경우
			req.getRequestDispatcher("/WEB-INF/views/mgr/layouts/mustlogin.jsp")
			.forward(req, resp);
		}

		
	}
	
}
