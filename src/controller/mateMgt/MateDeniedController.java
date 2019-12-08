package controller.mateMgt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Alert;
import dto.Mate;
import serivce.face.AlertService;
import serivce.face.MateService;
import serivce.impl.AlertServiceImpl;
import serivce.impl.MateServiceImpl;

@WebServlet("/mate/denied")
public class MateDeniedController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MateService mateService = MateServiceImpl.getInstance();
	private AlertService alertService = AlertServiceImpl.getInstance();


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		// 팅잠일 때에만 동작하기
		
		// proj_no, userno 필요
		Mate mate = mateService.getParam(req);
		
		mateService.removeUserFromTeam(mate);
		
		resp.sendRedirect("/mat	e/list?proj_no=" + mate.getProj_no());
	}
}
