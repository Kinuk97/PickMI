package controller.mateMgt;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dto.Mate;
import dto.ProfileBoard;
import serivce.face.MateService;
import serivce.impl.MateServiceImpl;

@WebServlet("/mate/mymate")
public class MateTeamSendList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MateService mateService = MateServiceImpl.getInstance();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		
		Mate mate = mateService.getParam(req);
		
		//신청한 사용자 정보 불러오기
		//사용자 번호로 사용자들 조회하기
		List<ProfileBoard> list5 = mateService.showUser(mate);
		req.setAttribute("myTeamList", list5);
		
		resp.getWriter().println(new Gson().toJson(list5));
	}
}
