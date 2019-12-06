package controller.mateMgt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Mate;
import dto.ProjectBoard;
import serivce.face.MateService;
import serivce.face.ProjectBoardService;
import serivce.impl.MateServiceImpl;
import serivce.impl.ProjectBoardServiceImpl;


@WebServlet("/mate/delete")
public class MateDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MateService mateService = MateServiceImpl.getInstance();
	private ProjectBoardService projectBoardService = new ProjectBoardServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//로그인 한 사람 정보 받기
		HttpSession session = req.getSession();
		Mate mate = new Mate();
		mate.setUserno((int)session.getAttribute("userno"));

		System.out.println(req.getParameter("proj_no"));
		Mate mate2 = new Mate();
		mate2.setProj_no(Integer.parseInt(req.getParameter("proj_no")));
				
		mateService.getUsernoByProjectno(mate2);
		
		if (mate.getUserno() == mate2.getUserno()) {
			req.setAttribute("check", true); //삭제가능
			mate.setUserno(Integer.parseInt(req.getParameter("userno")));
			mateService.removeUserFromTeam(mate);
		} else {
			req.setAttribute("check", false); //삭제할수없음
		}
		
	}
}
