package controller.Alert;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Alert;
import dto.ProfileBoard;
import serivce.face.AlertService;
import serivce.face.ProfileBoardService;
import serivce.impl.AlertServiceImpl;
import serivce.impl.ProfileBoardServiceImpl;


@WebServlet("/alert/fromprofile")
public class AlertFromProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AlertService alertService = AlertServiceImpl.getInstance();
	private ProfileBoardService profileBoardService = ProfileBoardServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Alert alert = new Alert();
		HttpSession session = req.getSession();
		try {
		alert.setSender((int)session.getAttribute("userno"));
		} catch (NullPointerException e) {
			System.out.println("로그인 안했음");
		}
		ProfileBoard profile = new ProfileBoard();
		//게시판 번호 view페이지에서 받아오기
		profile.setProf_no(Integer.parseInt(req.getParameter("prof_no")));
		//게시판 번호로 게시자 정보 조회하기
		profileBoardService.view(profile);
		//게시자(받는사람) 유저번호 알림 테이블에 저장하기
		alert.setUserno(profile.getUserno());
		alert.setAlert("당신을 팀원으로 초대합니다!");
		//알림테이블에 저장하기
		alertService.sendInvite(alert);
		
		
		resp.sendRedirect("/profileBoard/view?prof_no="+req.getParameter("prof_no"));
	}
}
