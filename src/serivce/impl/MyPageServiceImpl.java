package serivce.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.face.MyPageDao;
import dao.face.UserDao;
import dao.impl.MyPageDaoImpl;
import dao.impl.UserDaoImpl;
import dto.CompBoard;
import dto.FreeBoard;
import dto.ProfileBoard;
import dto.ProjectBoard;
import dto.Reply;
import dto.User;
import serivce.face.MyPageService;
import util.Paging;

public class MyPageServiceImpl implements MyPageService {
	
	UserDao userDao = new UserDaoImpl();
	
	private MyPageDao myPageDao = MyPageDaoImpl.getInstance();
	
	public MyPageServiceImpl() {
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

	@Override
	public User getUserno(HttpServletRequest req) {
		return myPageDao.selectUserbyUserno(req);
	}

// ----- 비밀번호 수정	

	@Override
	public User getcurPwParam(HttpServletRequest req) {
		
		User user = new User();
		
		String param = null;
		
		param = req.getParameter("pw");
		user.setPw(param);
		
//		param = req.getParameter("pw1");
//		user.setPw(param);
		
		return user;
	}

	@Override
	public boolean eqPW(User pwparam) {
		
		int cnt = 0;
		cnt = myPageDao.selectCntUserByupw(pwparam);
		
		if(cnt == 1) {
			return true;
		}
		return false;
	}

	@Override
	public void modifyPw(User pwparam) {
		userDao.updatePw(pwparam);
	}

	//사용자 삭제
	@Override
	public boolean userDelete(User user) {
		
		int result = myPageDao.deleteUser(user);
		
		if( result == 1) {
			return true;
		} else {
			return false;			
		}
		
	}
	
	@Override
	public Paging getPaging(HttpServletRequest req, int i) {
		//요청파라미터 curPage를 파싱한다
		String param = req.getParameter("curPage");
		int curPage =0;
		if( param!=null && !"".equals(param)) {
			curPage =Integer.parseInt(param);
		}

		//Board TB와 curPage 값을 이용해 Paging 객체를 생성하고 반환
		int totalcount = myPageDao.selectCntAll(req, i);

		//Paging 객체 생성
		Paging paging = new Paging(totalcount, curPage);

		return paging;
	}

	// ----- 내가 작성한 게시글 가져오기

	@Override
	public List getList(Paging paging, User user, int i) {
		
		return myPageDao.selectboard(paging, user, i);
	}
	// 내가 작성한 게시글 가져오기 -------------------------

	
	// ----- 내가 찜한 게시글 가져오기
	
	public List getLikeList(Paging paging, User user, int i) {
		System.out.println("서비스임플(찜한게시글 : " + paging);
		return myPageDao.likeboard(paging, user, i);
	}
	// 내가 찜한 게시글 가져오기 ---------------------------
	

	// ----- 내가 작성한 댓글 가져오기
	@Override
	public List getReplyList(Paging paging, User user, int i) {
		System.out.println("서비스임플 : " + paging);
		System.out.println("서비스임플 : " + user);
		System.out.println("서비스임플 : " + i);
		return myPageDao.writeReply(paging, user, i);
	}
	// 내가 작성한 댓글 가져오기 ------------------------------

	
	// TEST -----------------------------------------------------
	@Override
	public Paging getPaging(HttpServletRequest req) {
		//요청파라미터 curPage를 파싱한다
		String param = req.getParameter("curPage");
		int curPage =0;
		if( param!=null && !"".equals(param)) {
			curPage =Integer.parseInt(param);
		}

		//Board TB와 curPage 값을 이용해 Paging 객체를 생성하고 반환
		int totalcount = myPageDao.selectCntAll(req);

		//Paging 객체 생성
		Paging paging = new Paging(totalcount, curPage);

		return paging;
	}

	// -----------------------------------------------------------
	
	
	
	
	
	
	
	
	
	
	
}