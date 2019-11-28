package serivce.impl;

import javax.servlet.http.HttpServletRequest;

import dao.face.FreeBoardDao;
import dao.impl.FreeBoardDaoImpl;
import serivce.face.FreeBoardService;
import serivce.face.ReplyService;

public class ReplyServiceImpl implements ReplyService {
//	private ReplyDao replyDao = ReplyDaoImpl.getInstance();

	private ReplyServiceImpl() {
	}

	private static class Singleton {
		private static final ReplyService instance = new ReplyServiceImpl();
	}

	public static ReplyService getInstance() {
		return Singleton.instance;
	}
	
//	@Override
//	public Reply getParam(HttpServletRequest req) {
//		
//		
//		
//		return null;
//	}
}
