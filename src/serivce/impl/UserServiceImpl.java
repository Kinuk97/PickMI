package serivce.impl;

import javax.servlet.http.HttpServletRequest;

import dao.face.UserDao;
import dao.impl.UserDaoImpl;
import dto.User;
import serivce.face.UserService;

public class UserServiceImpl implements UserService {
	
	UserDao userDao = new UserDaoImpl();
	
	@Override
	public User getLoginParam(HttpServletRequest req) {
		// 유저객체
		User user = new User();

		// 파마리터
		String param = null;

		// email 파라미터
		param = req.getParameter("email");
		user.setEmail(param);

		// pw 파라미터
		param = req.getParameter("pw");
		user.setPw(param);

		return user; // user로반환(email, pw)
	}

	@Override
	public boolean login(User user) {
		
		int cnt = 0;
		cnt = userDao.selectCntUserByUserid(user);
		
		return false;
	}

}
