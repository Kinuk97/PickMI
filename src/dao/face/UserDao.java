package dao.face;

import dto.User;

public interface UserDao {
	/**
	 * email와 pw가 일치하는 회원 수 조회
	 * 
	 * @param user - email와 pw를 가진 회원
	 * @return int - 1(존재하는 회원), 0(존재하지않는회원), -1(에러)
	 */
	public int selectCntUserByUserid(User user);
	
	/**
	 * 회원가입
	 * 
	 * @param user - 회원가입 정보
	 */
	public void insert(User user);
	
	/**
	 * 아이디 중복 체크
	 * 
	 * @param user - 아이디 정보
	 * @return boolean - true(존재하는 아이디), false(존재하지 않는 아이디)
	 * 
	 */
	public int selectEmail(User user);
	
	/**
	 * email로 userno 가져오기(글쓰기 작성시 user를 불러오기 위함)
	 * 
	 * @param user - 이메일
	 * @return user - 유저번호
	 */
	public User selectUserByEmail(User user);
	
	/**
	 * 유저의 이메일과 이름으로 비밀번호를 가져온다
	 * 
	 * @param user - 이메일, 이름
	 * @return User - 비밀번호
	 */
	public User selectPwByEmail(User user);

	public void updatePw(User user);

}
