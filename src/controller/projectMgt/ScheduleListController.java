package controller.projectMgt;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dto.Mate;
import dto.ProjectBoard;
import dto.Schedule;
import serivce.face.MateService;
import serivce.face.ProjectBoardService;
import serivce.face.ScheduleService;
import serivce.impl.MateServiceImpl;
import serivce.impl.ProjectBoardServiceImpl;
import serivce.impl.ScheduleServiceImpl;

@WebServlet("/schedule/list")
public class ScheduleListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProjectBoardService projectBoardService = ProjectBoardServiceImpl.getInstance();
	private ScheduleService scheduleService = ScheduleServiceImpl.getInstance();
	private MateService mateService = MateServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 로그인 처리??
		// 내 프로젝트인 경우에만??
		
		Mate mate = mateService.getParam(req);
		
		Object obj = req.getSession().getAttribute("userno");
		
		if (obj != null && !obj.equals("")) {
			try {
				mate.setUserno((Integer) obj);
			} catch (ClassCastException e) {
				e.printStackTrace();
			}
		}
		
		ProjectBoard projectBoard = new ProjectBoard();
		
		projectBoard.setProj_no(mate.getProj_no());
		
		projectBoardService.view(projectBoard);
		
		if (projectBoard.getProj_title() == null) {
			// 없는 게시판 번호라면
			resp.sendRedirect("/mypage");
		} else {
			// 있는 게시판 번호라면
			
			// 내가 참여하고 있는 프로젝트 번호
			List<ProjectBoard> list = mateService.getMyProjList(mate);
			
			for (int i = 0; i < list.size(); i++) {
				// 만약 내가 참여하고 있는 프로젝트의 팀원이라면
				if (list.get(i).getProj_no() == projectBoard.getProj_no()) {
					req.getRequestDispatcher("/WEB-INF/views/projectMgt/schedule.jsp").forward(req, resp);
					return;
				}
			}
			
			// 내가 참여하고 있는 프로젝트가 아니라면
			resp.sendRedirect("/mypage");
		}
			
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");

		Schedule schedule = scheduleService.getSchedule(req);
		Gson gson = new Gson();

		resp.getWriter().println(gson.toJson(scheduleService.getScheduleList(schedule)));
	}

}
