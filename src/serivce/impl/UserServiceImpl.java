package serivce.impl;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import dao.face.UserDao;
import dao.impl.UserDaoImpl;
import dto.User;
import serivce.face.UserService;

public class UserServiceImpl implements UserService {

	UserDao userDao = new UserDaoImpl();
	
	// 유저객체
	User user = new User();

	@Override
	public User getLoginParam(HttpServletRequest req) {

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

		if (cnt == 1) {
			userDao.selectUserByEmail(user);
			
			return true;
		} else {
			return false;
		}

	}

	@Override
	public User getJoinParam(HttpServletRequest req) {
		
		
		String param = null;
		
		try {
			req.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		// email
		param = req.getParameter("email");
		user.setEmail(param);
		
		// pw
		param = req.getParameter("pw");
		user.setPw(param);
		
		// name
		param = req.getParameter("name");
		user.setName(param);
		
		return user; // user로 반환(email, pw, name)
	}

	@Override
	public boolean emailCheck(User user) {
		
		int checkResult = 0;
		checkResult = userDao.selectEmail(user);
		
		if(checkResult == 1) {
			return true;
		} else {
			return false;			
		}
	}
	
	@Override
	public void join(User user) {
		userDao.insert(user);
		
	}

	@Override
	public User getUserno(User user) {
		
		return userDao.selectUserByEmail(user);
	}

	@Override
	public User getPwParam(HttpServletRequest req) {
		String param = null;
		
		param = req.getParameter("email");
		user.setEmail(param);
		
		param = req.getParameter("name");
		user.setName(param);
		
		return user;
	}

	@Override
	public User findPw(User user) {
		
		return userDao.selectPwByEmail(user);
	}

	@Override
	public User getNewParam(HttpServletRequest req) {
		
		String param = null;
		
		param = req.getParameter("pw1");
		user.setPw(param);
		
		return user;
	}

	@Override
	public void updatePw(User newPw) {
		userDao.updatePw(newPw);
	}

	

}
