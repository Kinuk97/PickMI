package controller.projectMgt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dto.Schedule;
import serivce.face.ScheduleService;
import serivce.impl.ScheduleServiceImpl;
import util.Paging;

@WebServlet("/schedule/list")
public class ScheduleListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ScheduleService scheduleService = ScheduleServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 로그인 처리??
		// 내 프로젝트인 경우에만??
		req.getRequestDispatcher("/WEB-INF/views/projectMgt/schedule.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");

		Schedule schedule = scheduleService.getSchedule(req);
		Gson gson = new Gson();

		resp.getWriter().println(gson.toJson(scheduleService.getScheduleList(schedule)));
//		System.out.println("schedule list controller : " + scheduleService.getScheduleList(schedule));
	}

}
