package controller.mateMgt;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dto.Mate;
import serivce.face.MateService;
import serivce.face.ProjectBoardService;
import serivce.impl.MateServiceImpl;
import serivce.impl.ProjectBoardServiceImpl;


@WebServlet("/mate/delete")
public class MateDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MateService mateService = MateServiceImpl.getInstance();
	private ProjectBoardService projectBoardService = new ProjectBoardServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int result = 0;
		int deleteAction = 0;
		
		//로그인 한 사람 정보 받기
		HttpSession session = req.getSession();
		Mate mate = new Mate();
		mate.setUserno((int) session.getAttribute("userno"));
		mate.setProj_no(Integer.parseInt(req.getParameter("proj_no")));
		
		// 내가 팀장인 프로젝트 리스트
		List<Mate> checkLeader = mateService.getProj_noByUserno(mate);
		
		// case 1-1 로그인한 사람이 팀장인데 자기 자신의 유저번호가 파라미터로 왔다면...
		for (int i = 0; i < checkLeader.size(); i++) {
			if (checkLeader.get(i).getProj_no() == mate.getProj_no()) {
				// 내가 팀장인 프로젝트라면 -1!!
				result = -1;
			}
		}
		
		// case 1 로그인한 사람과 파라미터값으로 받아온 유저번호가 같다면 삭제
		String reqUserno = req.getParameter("userno");
		if (reqUserno != null && !"".equals(reqUserno)) {
			try { 
				if (mate.getUserno() == Integer.parseInt(reqUserno)) {
					if (result != -1) {
						// 내가 팀장이 아니면서 내 유저번호가 파라미터로 왔고 세션이랑 같다면
						mate.setUserno(Integer.parseInt(reqUserno));
						mateService.removeUserFromTeam(mate);
						deleteAction = 1;
					}
				} else {
					// case 2 로그인한 사람이 이 프로젝트의 팀장이라면 삭제
					if (result == -1) {
						// 내가 팀장이고 팀원을 삭제하려고 한다면
						mate.setUserno(Integer.parseInt(reqUserno));
						mateService.removeUserFromTeam(mate);
						deleteAction = 2;
					}
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		
		// 응답??
		resp.getWriter().println(new Gson().toJson("{ \"result\" : " + deleteAction + " }"));
	}
}
