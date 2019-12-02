package serivce.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Schedule;

public interface ScheduleService {
	
	/**
	 * 요청에서 파라미터를 일정 DTO로 만들어주는 서비스
	 * 
	 * @param req - 받은 요청
	 * @return 받은 일정 DTO
	 */
	public Schedule getSchedule(HttpServletRequest req);
	
	/**
	 * 프로젝트의 일정을 가져오는 서비스
	 * 
	 * @param schedule - 일정 DTO (프로젝트 번호가 담겨있는 DTO)
	 * @return 결과 리스트
	 */
	public List<Schedule> getScheduleList(Schedule schedule);
	
	/**
	 * 일정 하나를 가져오는 서비스
	 * 
	 * @param schedule - 일정 번호가 담겨있는 DTO
	 * @return 조회된 일정 DTO
	 */
	public Schedule getSchedule(Schedule schedule);
	
	/**
	 * 프로젝트의 일정을 추가하는 서비스
	 * 
	 * @param schedule - 일정 DTO (입력 데이터 저장)
	 */
	public void putSchedule(Schedule schedule);
	
	/**
	 * 일정을 수정하는 서비스
	 * 
	 * @param schedule - 일정 DTO
	 */
	public void modifySchedule(Schedule schedule);
	
	/**
	 * 일정을 삭제하는 서비스
	 * 
	 * @param schedule - 스케줄 번호가 담겨있는 DTO
	 */
	public void removeSchedule(Schedule schedule);
	
	
}
