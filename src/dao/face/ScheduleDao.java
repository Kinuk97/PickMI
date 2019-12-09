package dao.face;

import java.util.List;

import dto.CheckList;
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

	/**
	 * proj_no와 scheduledate로 검색된 행의 갯수
	 * 
	 * @param schedule - proj_no와 scheduledate가 있어야한다.
	 * @return 결과값
	 */
	public int selectCntScheduleDate(Schedule schedule);

	/**
	 * 일정의 체크리스트 가져오기
	 * 
	 * @param selectSchedule - 스케줄번호가 담겨있는 DTO
	 * @return - 결과 List
	 */
	public List<CheckList> selectCheckList(Schedule selectSchedule);
	

	/**
	 * 체크리스트의 항목을 추가하는 메소드
	 * 
	 * @param checkList - 체크리스트 DTO
	 */
	public void insertCheck(CheckList checkList);
	
	/**
	 * 체크여부를 수정해주는 메소드
	 * 
	 * @param checkList - checkno가 담겨있는 dto
	 */
	public void updateCheckDo_check(CheckList checkList);
	
	/**
	 * 체크 항목의 이름을 바꿔주는 메소드
	 * 
	 * @param checkList - content와 checkno가 담겨있는 DTO
	 */
	public void updateCheckContent(CheckList checkList);
	
	/**
	 * 체크항목을 삭제하는 메소드
	 * 
	 * @param checkList
	 */
	public void deleteCheck(CheckList checkList);
	
	/**
	 * 체크리스트를 삭제하는 메소드
	 * 
	 * @param checkList - scheduleno가 담겨있는 DTO
	 */
	public void deleteCheckList(CheckList checkList);

}
