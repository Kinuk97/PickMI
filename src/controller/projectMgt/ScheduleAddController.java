package controller.projectMgt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Schedule;
import serivce.face.ScheduleService;
import serivce.impl.ScheduleServiceImpl;

@WebServlet("/schedule/add")
public class ScheduleAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ScheduleService scheduleService = ScheduleServiceImpl.getInstance();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Schedule schedule = scheduleService.getSchedule(req);

		scheduleService.putSchedule(schedule);
	}
}
