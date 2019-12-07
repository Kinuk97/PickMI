package controller.Alert;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Alert;
import dto.Mate;
import dto.ProjectBoard;
import serivce.face.AlertService;
import serivce.face.MateService;
import serivce.face.ProjectBoardService;
import serivce.impl.AlertServiceImpl;
import serivce.impl.MateServiceImpl;
import serivce.impl.ProjectBoardServiceImpl;


@WebServlet("/alert/fromproject")
public class AlertFromProjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProjectBoardService projectBoardService = new ProjectBoardServiceImpl();
	private AlertService alertService = AlertServiceImpl.getInstance();
	private MateService mateService = MateServiceImpl.getInstance();



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Alert alert = new Alert();
		Mate mate = new Mate();
		
		HttpSession session = req.getSession();
		try {
		alert.setSender((int)session.getAttribute("userno"));
		mate.setUserno((int)session.getAttribute("userno"));
		} catch (NullPointerException e) {
			System.out.println("로그인 안했음");
		}
		
		
		//게시판 정보 받아오기
		ProjectBoard projectBoard = projectBoardService.getProjectBoardno(req);
		projectBoard = projectBoardService.view(projectBoard);
		//게시자(받는사람) 유저번호 알림 테이블에 저장하기
		alert.setUserno(projectBoard.getUserno());
		alert.setAlert("팀원이 되고싶어요!");
		mate.setProj_no(projectBoard.getProj_no());
		//기존에 참가 신청을 했는지 확인하기
		int check = mateService.checkJoin(mate);
		System.out.println(check);
		if(check == 0) {
			req.setAttribute("waiting", true);
		} else if (check == 1) {
			req.setAttribute("already", true);
		} else if( check ==2 ) {
			req.setAttribute("leader", true);
		} else {
			//알림테이블에 저장하기
			alertService.sendInvite(alert);
			//팀원관리 테이블에 저장하기
			mateService.wantToJoin(mate);
		}
		
		
//		System.out.println(req.getParameter("proj_no"));
		resp.sendRedirect("/projectBoard/view?proj_no="+req.getParameter("proj_no"));

	}
}
