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
import serivce.face.AlertService;
import serivce.face.MateService;
import serivce.impl.AlertServiceImpl;
import serivce.impl.MateServiceImpl;

@WebServlet("/mate/list")
public class MateListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MateService mateService = MateServiceImpl.getInstance();
	private AlertService alertService = AlertServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String alertCheck = req.getParameter("alert");
		if (alertCheck != null && !"".equals(alertCheck)) {
			if (alertCheck.equals("true")) {
//				alertService.readAlert();
			}
		}
		
		//유저 정보 셋팅
		Mate mate = new Mate();
		HttpSession session = req.getSession();
		try {
			mate.setUserno((int)session.getAttribute("userno"));
		} catch (NullPointerException e) {
			System.out.println("로그인 안했음");
		}
//		mate = mateService.getProj_no(mate);
		
		//팀장인지 아닌지 확인하기
		boolean checkLeader = mateService.checkLeader(mate);
		
		if(checkLeader) {
			req.setAttribute("leader", true);
			//내가 팀장인 리스트 불러오기
			List<Mate> list = mateService.getProj_noByUserno(mate);
			req.setAttribute("leaderlist", list);
//			System.out.println("리더 팀리스트 : " + list);
		} else {
			req.setAttribute("leader", false);
		}
		
		//내가 가입한 프로젝트 리스트 불러오기
		List<ProjectBoard> list2 = mateService.getMyProjList(mate);
		req.setAttribute("joinTeamList", list2);
//			System.out.println("joinTeam LIst :" + list2);

			
		//내가 신청한 프로젝트 리스트 불러오기
		List<Mate> list3 = mateService.waitingAnswer(mate);
		req.setAttribute("waitTeamList", list3);
//			System.out.println("체크체크 : " + list3);
	
//			//신청한 사용자 정보 불러오기
//			mate.setProj_no(Integer.parseInt(req.getParameter("proj_no")));
//			List<Mate> list4 = mateService.appliedUser(mate);
//			//사용자 번호로 사용자들 조회하기
//			List<Mate> list5 = mateService.showUser(list4);
			
			
			//나를 초대한 목록 보기
			


		

		
//		System.out.println(req.getAttribute("leader"));
		
		req.getRequestDispatcher("/WEB-INF/views/mateMgt/mateList.jsp").forward(req, resp);
		
	}
	

}
