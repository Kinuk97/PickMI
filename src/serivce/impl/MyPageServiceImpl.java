package serivce.impl;

import dao.face.MyPageDao;
import dao.impl.MyPageDaoImpl;
import dto.User;
import serivce.face.MyPageService;

public class MyPageServiceImpl implements MyPageService {
	
	private MyPageDao myPageDao = MyPageDaoImpl.getInstance();
	
	private MyPageServiceImpl() {
	}
	
	private static class Singleton{
		private static final MyPageService instance = new MyPageServiceImpl();
	}
	
	public static MyPageService getInstance() {
		return Singleton.instance;
	}

	// ----- User Info -----
	@Override
	public User getUser(String email) {
		return myPageDao.selectOne(email);
	}

	@Override
	public boolean userUpdate(String email, String pw, String name) {

		User user = new User();
		user.setEmail(email);
		user.setPw(pw);
		User originUser = myPageDao.selectOne(pw);
		if(originUser.getPw().equals(user.getPw())) {//비밀번호가 맞으면
			myPageDao.updateUser(user);
			return true;
		} else 
			return false;
	}
	
	
	
}
