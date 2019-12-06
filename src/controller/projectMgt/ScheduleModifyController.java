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
@WebServlet("/schedule/modify")
public class ScheduleModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ScheduleService scheduleService = ScheduleServiceImpl.getInstance();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Schedule schedule = scheduleService.getSchedule(req);
		
		Schedule selectSchedule = scheduleService.getSchedule(schedule);
		
		String kinds = req.getParameter("kinds");
		
		if (kinds != null && kinds.equals("1")) {
			// title 수정
			selectSchedule.setTitle(schedule.getTitle());
		} else if (kinds.equals("2")) {
			// content 수정
			selectSchedule.setContent(schedule.getContent());
		} else if (kinds.equals("3")) {
			// due_date 수정
			selectSchedule.setDue_date(schedule.getDue_date());
		} 
		
		scheduleService.modifySchedule(selectSchedule);
	}
}
