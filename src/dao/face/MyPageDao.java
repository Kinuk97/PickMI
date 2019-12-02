package dao.face;

import dto.User;

public interface MyPageDao {

// ----- User Info -----
	// email에 해당하는 사용자 조회하기
	User selectOne(String email);

	public void updateUser(User user);


}
