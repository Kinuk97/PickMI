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


@WebServlet("/mate/view")
public class MateViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MateService mateService = MateServiceImpl.getInstance();
	private ProfileBoardService profileBoardService = ProfileBoardServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		


	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Mate mate = new Mate();
		mate.setProj_no(Integer.parseInt(req.getParameter("proj_no")));
//		System.out.println(mate);
		//프로젝트 함께 하는 인원 리스트 불러오기
		List<Mate> mateList = mateService.getMateList(mate);
		req.setAttribute("mateList", mateList);
//		System.out.println("matelist : " + mateList);
		
		// 팀원 보기에서 내가 팀장인지 확인하기
		HttpSession session = req.getSession();
		try {
			mate.setUserno((int)session.getAttribute("userno"));
		} catch (NullPointerException e) {
			System.out.println("로그인 안했음");
		}
		
		boolean checkLeader = mateService.countProjectLeader(mate);
		
		if(checkLeader) {
			req.setAttribute("leader", true);
		} else {
			req.setAttribute("leader", false);
		}
		
		//유저 정보 보여주기
		ProfileBoard profile = new ProfileBoard();
		try {
			profile.setUserno((int)session.getAttribute("userno"));
		} catch(NullPointerException e) {
//			System.out.println("mateview controller : 로그인 정보...문제" );
		}
//
		ProfileBoard detailProfile = profileBoardService.view(profile);
		req.setAttribute("profile", detailProfile);
//		System.out.println("view :" + detailProfile);
		
		req.getRequestDispatcher("/WEB-INF/views/mateMgt/viewUser.jsp").forward(req, resp);
	}

}
