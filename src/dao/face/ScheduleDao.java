package dao.face;

import java.util.List;

import dto.Schedule;

public interface ScheduleDao {
	
	/**
	 * 시퀀스.nextval하는 메소드
	 * 
	 * @return schedule_seq.nextval
	 */
	public int selectScheduleno();
	
	/**
	 * 일정목록을 가져오는 메소드
	 * 
	 * @param schedule - 프로젝트 번호가 담긴 DTO
	 * @return 일정 목록
	 */
	public List<Schedule> selectAll(Schedule schedule);
	
	/**
	 * 스케줄번호로 조회하기
	 * 
	 * @param schedule - scheduleno가 담긴 DTO
	 * @return 조회된 일정
	 */
	public Schedule selectByScheduleno(Schedule schedule);
	
	/**
	 * 일정 입력하는 메소드
	 * 
	 * @param schedule - 일정 DTO
	 */
	public void insertSchedule(Schedule schedule);
	
	/**
	 * 일정을 수정하는 메소드
	 * 
	 * @param schedule - 일정 DTO
	 */
	public void updateSchedule(Schedule schedule);
	
	/**
	 * 일정을 삭제하는 메소드
	 * 
	 * @param schedule - 일정번호가 담긴 DTO
	 */
	public void deleteSchedule(Schedule schedule);
	
}
