package controller.mateMgt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Mate;
import dto.ProfileBoard;
import dto.ProjectBoard;
import serivce.face.MateService;
import serivce.impl.MateServiceImpl;

@WebServlet("/mate/invite")
public class MateInviteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MateService mateService = MateServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Mate mate = new Mate();
		ProfileBoard profile = new ProfileBoard();
		//게시판 번호받기
		profile.setProf_no(Integer.parseInt(req.getParameter("prof_no")));

//		mate.setProj_no(Integer.parseInt(req.getParameter("proj_no")));
		HttpSession session = req.getSession();
		try {
			mate.setUserno((int)session.getAttribute("userno")); //초대자 정보저장
		} catch (NullPointerException e) {
			System.out.println("로그인 안했음");
		}
		
		boolean checkLeader = mateService.countProjectLeader(mate);
		
		if(checkLeader) {
			req.setAttribute("leader", true);
			//로그인 정보로, 팀장이 가지고 있는 프로젝트 조회
			ProjectBoard project = mateService.selectMyproject(mate);
			req.setAttribute("project", project);
			System.out.println("dkdkdk" + project);
			
		} else {
			req.setAttribute("leader", false);
		}
		
		resp.sendRedirect("/profileBoard/view?prof_no="+req.getParameter("prof_no"));
//		req.getRequestDispatcher("/WEB-INF/views/board//profileBoard/view?prof_no=${prof_no}.jsp").forward(req, resp);


		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
	}
	

}
