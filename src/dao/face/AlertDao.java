package dao.face;

import dto.Alert;

public interface AlertDao {
	/**
	 * 프로필 게시판에서 정보를 알림 테이블로 저장
	 * @param alert
	 */
	public void insertAlert(Alert alert);

}
