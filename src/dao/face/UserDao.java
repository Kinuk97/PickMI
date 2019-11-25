package dao.face;

import dto.User;

public interface UserDao {
	
	/**
	 * email와 pw가 일치하는 회원 수 조회
	 * 
	 * @param user - email와 pw를 가진 회원
	 * @return int - 1(존재하는 회원), 0(존재하지않는회원), -1(에러)
	 */
	int selectCntUserByUserid(User user);
	
	/**
	 * 회원가입
	 * 
	 * @param user - 회원가입 정보
	 */
	void insert(User user);

}
