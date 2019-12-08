package controller.mateMgt;

import java.io.IOException;
import java.util.List;

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
//		profile1.setProf_no(Integer.parseInt(req.getParameter("prof_no")));

		mate.setProj_no(Integer.parseInt(req.getParameter("proj_no")));
		mate.setUserno((Integer) req.getSession().getAttribute("userno"));
		
		// 다시 초대하면 또 db에 입력되는 문제 해결해야함.
		
		boolean checkLeader = mateService.checkLeader(mate);
		mate.setUserno(Integer.parseInt(req.getParameter("userno"))); //초대받는사람 정보
//		System.out.println("프로필뷰 :" + checkLeader);
		if(checkLeader) {
			req.setAttribute("leader", true);
			//mate테이블에 넣어주기
			mateService.inviteMate(mate);
//			System.out.println("dkdkdk" + project);
			
		} else {
			req.setAttribute("leader", false);
		}
		
		resp.sendRedirect("/profileBoard/view?prof_no="+req.getParameter("prof_no"));
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
	}
	

}
