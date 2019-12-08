package dao.face;

import java.util.List;

import dto.Alert;

public interface AlertDao {
	/**
	 * 프로필 게시판에 글 쓴적 있는지 확인하기
	 * @param alert
	 * @return
	 */
	public int checkProfile(Alert alert);
	
	public void insertDeniedAlert(Alert alert);
	/**
	 * 프로필 게시판에서 정보를 알림 테이블로 저장
	 * @param alert
	 */
	public void insertAlert(Alert alert);

	/**
	 * 알림 리스트 가져오기
	 * 
	 * @param alert
	 * @return
	 */
	public List<Alert> selectMyAlert(Alert alert);

}
