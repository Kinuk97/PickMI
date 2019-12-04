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

/**
 * Servlet implementation class ScheduleModifyController
 */
@WebServlet("/schedule/modify/due_date")
public class ScheduleModifyDue_DateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ScheduleService scheduleService = ScheduleServiceImpl.getInstance();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Schedule schedule = scheduleService.getSchedule(req);
		
		Schedule selectSchedule = scheduleService.getSchedule(schedule);
		
		selectSchedule.setDue_date(schedule.getDue_date());
		
		scheduleService.modifySchedule(selectSchedule);
	}
}
