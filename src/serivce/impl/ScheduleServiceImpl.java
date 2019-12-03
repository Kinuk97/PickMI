package serivce.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Schedule;
import serivce.face.ScheduleService;

public class ScheduleServiceImpl implements ScheduleService {

	private ScheduleServiceImpl() { }

	private static class Singleton {
		private static final ScheduleService instance = new ScheduleServiceImpl();
	}

	public static ScheduleService getInstance() {
		return Singleton.instance;
	}

	@Override
	public Schedule getSchedule(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Schedule> getScheduleList(Schedule schedule) {
		
		return null;
	}

	@Override
	public Schedule getSchedule(Schedule schedule) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putSchedule(Schedule schedule) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modifySchedule(Schedule schedule) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeSchedule(Schedule schedule) {
		// TODO Auto-generated method stub

	}

}
