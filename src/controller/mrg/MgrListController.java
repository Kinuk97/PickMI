package controller.mrg;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ProfileBoard;
import serivce.face.MgrService;
import serivce.impl.MgrServiceImpl;

/**
 * Servlet implementation class MgrMain
 */
@WebServlet("/mgr/list")
public class MgrListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MgrService mgrService = new MgrServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//로그인 세션정보 확인
//		HttpSession s = req.getSession();		
//		System.out.println(s.getAttribute("login"));
//		System.out.println(s.getAttribute("mgrid"));
		
		//ProfileBoard 테이블의 목록 조회
		List<ProfileBoard> pbList = mgrService.getpbList();
		
		//게시글 목록을 MODEL값으로 지정
		req.setAttribute("pblist", pbList);
		
		req.getRequestDispatcher("/WEB-INF/views/mgr/pblist.jsp")
		.forward(req, resp);
		
		
		
	}
	
}
