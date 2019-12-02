package serivce.face;

import java.util.List;

import dto.User;

public interface MyPageService {

// ----- User info	-----

	/**
	 * email에 해당하는 사용자 정보 가져오기
	 * @param email - 사용자 이메일이 담겨있는 객체
	 * @return 입력받은 email에 해당하는 데이터
	 */
	public User getUser(String email);

	/**
	 * 회원정보 수정해주는 기능
	 * @param email
	 * @param pw
	 * @param name
	 */
	public boolean userUpdate(String email, String pw, String name);
// --------------------------------------
	
	
}
