package controller.mateMgt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Mate;
import serivce.face.MateService;
import serivce.impl.MateServiceImpl;

/**
 * Servlet implementation class MateAcceptController
 */
@WebServlet("/mate/accept")
public class MateAcceptController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MateService mateService = MateServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 팅잠일 때에만 동작하기

		
		// 프로젝트 번호와 유저번호가 필요
		Mate mate = mateService.getParam(req);
		mateService.addMate(mate);
		
		resp.sendRedirect("/mate/list?proj_no=" + mate.getProj_no());
	}
}
