package controller.mateMgt;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dto.Mate;
import dto.ProfileBoard;
import dto.User;
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
//		System.out.println("team send list controller : " + mate);
		
		//신청한 사용자 정보 불러오기
		//사용자 번호로 사용자들 조회하기
		List<ProfileBoard> list5 = mateService.showUser(mate);
		req.setAttribute("myTeamList", list5);
//		System.out.println("team send list controller :" + list5);
		resp.getWriter().println(new Gson().toJson(list5));
		
		//사용자 프로필내역이 없을 경우를 대비해 이름만 조회함
//		List<User> list6 = mateService.showUserName(mate);
//		req.setAttribute("onlyName", list6);
		
//		Map<String, List> map = new HashMap<>();
//		map.put("myTeamList", list5);
//		map.put("onlyName", list6);
		 
		
//		resp.getWriter().println(new Gson().toJson(map));
	}
}
