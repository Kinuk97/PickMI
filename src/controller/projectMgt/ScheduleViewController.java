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


@WebServlet("/schedule/view")
public class ScheduleViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ScheduleService scheduleService = ScheduleServiceImpl.getInstance();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");

		Schedule schedule = scheduleService.getSchedule(req);
		
		Gson gson = new Gson();
		
		schedule = scheduleService.getSchedule(schedule);
		schedule.setCheckList(scheduleService.getCheckList(schedule));
		
		resp.getWriter().println(gson.toJson(schedule));
	}

}
