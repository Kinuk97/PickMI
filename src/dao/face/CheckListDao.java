package dao.face;

import java.util.List;

import dto.CheckList;
import dto.Schedule;

public interface CheckListDao {

	/**
	 * 시퀀스.nextval하는 메소드
	 * 
	 * @return schedule_seq.nextval
	 */
	public int selectCheckno();

	/**
	 * 스케줄번호로 조회하기
	 * 
	 * @param schedule - scheduleno가 담긴 DTO
	 * @return 조회된 일정
	 */
	public List<CheckList> selectByScheduleno(Schedule schedule);

	/**
	 * 체크리스트 입력하는 메소드
	 * 
	 * @param schedule - 체크리스트 DTO
	 */
	public void insertCheckList(CheckList checkList);

	/**
	 * 일정을 수정하는 메소드
	 * 
	 * @param schedule - 체크리스트 DTO
	 */
	public void updateCheckList(CheckList checkList);

	/**
	 * 일정을 삭제하는 메소드
	 * 
	 * @param schedule - 체크리스트 넘버가 담긴 DTO
	 */
	public void deleteCheckList(CheckList checkList);

}
