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
import serivce.face.MateService;
import serivce.face.ProfileBoardService;
import serivce.impl.MateServiceImpl;
import serivce.impl.ProfileBoardServiceImpl;

@WebServlet("/mate/invite")
public class MateInviteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MateService mateService = MateServiceImpl.getInstance();
	private ProfileBoardService profileBoardService = ProfileBoardServiceImpl.getInstance();


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Mate mate = new Mate();
		ProfileBoard profile = new ProfileBoard();
		profile.setProf_no(Integer.parseInt(req.getParameter("prof_no")));
		profile = profileBoardService.view(profile);
//		System.out.println(profile);

		//게시판 번호받기
//		profile1.setProf_no(Integer.parseInt(req.getParameter("prof_no")));

		mate.setProj_no(Integer.parseInt(req.getParameter("proj_no")));
		mate.setUserno(Integer.parseInt(req.getParameter("userno"))); //초대받는사람 정보
//		System.out.println("메이트 인바이트 : " +mate);
//		boolean checkLeader = mateService.checkLeader(mate);
//		System.out.println("프로필뷰 :" + checkLeader);
//		if(checkLeader) {
//			req.setAttribute("leader", true);
			//mate테이블에 넣어주기
			mateService.inviteMate(mate);
//			System.out.println("dkdkdk" + project);
			
//		} else {
//			req.setAttribute("leader", false);
//		}
		
		resp.sendRedirect("/profileBoard/view?prof_no="+req.getParameter("prof_no"));
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
	}
	

}
