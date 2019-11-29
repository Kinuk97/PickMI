package controller.mgr.free;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.FreeBoard;
import serivce.face.FreeBoardService;
import serivce.impl.FreeBoardServiceImpl;

/**
 * Servlet implementation class MgrFreeBoardViewController
 */
@WebServlet("/mgr/freeview")
public class MgrFreeBoardViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private FreeBoardService freeBoardService = FreeBoardServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	if(req.getSession().getAttribute("mgrlogin") != null) {
		
		FreeBoard freeBoard = freeBoardService.getParam(req);
		
		freeBoard = freeBoardService.FreeBoardDetail(freeBoard);
		
		
		if (freeBoard != null) {
			req.setAttribute("board", freeBoard);
			//		System.out.println("자유게시판: " + freeBoard);
			
			//menuTab 모델값
			req.setAttribute("boardno", 5);
			req.getRequestDispatcher("/WEB-INF/views/mgr/free/freeview.jsp").forward(req, resp);
			} else {
				resp.sendRedirect("/mgr/main");
			}
		
		} else {
			// 에러페이지
			req.getRequestDispatcher("/WEB-INF/views/mgr/layouts/mustlogin.jsp")
			.forward(req, resp);
		}
	}	
}
