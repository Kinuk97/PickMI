package serivce.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.CheckList;
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
	 * 요청에서 파라미터를 CheckList DTO로 만들어주는 서비스
	 * 
	 * @param req - 받은 요청
	 * @return DTO
	 */
	public CheckList getCheck(HttpServletRequest req);

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

	/**
	 * 날짜 하나에 일정 하나이기 떄문에 이미 등록된 스케줄이 있는 scheduledate인지 확인하기
	 * 
	 * @return 확인값
	 */
	public boolean checkSchedule(Schedule schedule);

	/**
	 * 일정의 체크리스트 가져오기
	 * 
	 * @param selectSchedule - 스케줄번호가 담겨있는 DTO
	 * @return - 결과 List
	 */
	public List<CheckList> getCheckList(Schedule selectSchedule);
	

	/**
	 * 체크리스트의 항목을 추가하는 메소드
	 * 
	 * @param checkList - 체크리스트 DTO
	 */
	public void addCheck(CheckList checkList);
	
	/**
	 * 체크여부를 수정해주는 메소드
	 * 
	 * @param checkList - checkno가 담겨있는 dto
	 */
	public void modifyCheckDo_check(CheckList checkList);
	
	/**
	 * 체크 항목의 이름을 바꿔주는 메소드
	 * 
	 * @param checkList - content와 checkno가 담겨있는 DTO
	 */
	public void modifyCheckContent(CheckList checkList);
	
	/**
	 * 체크항목을 삭제하는 메소드
	 * 
	 * @param checkList - checkno가 담겨있는 DTO
	 */
	public void removeCheck(CheckList checkList);
	
	/**
	 * 체크리스트를 삭제하는 메소드
	 * 
	 * @param checkList - scheduleno가 담겨있는 DTO
	 */
	public void removeCheckList(CheckList checkList);
}
