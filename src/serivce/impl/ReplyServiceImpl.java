package serivce.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.face.ReplyDao;
import dao.impl.ReplyDaoImpl;
import dto.Reply;
import serivce.face.ReplyService;
import util.Paging;

public class ReplyServiceImpl implements ReplyService {

	private ReplyDao replyDao = ReplyDaoImpl.getInstance();

	private ReplyServiceImpl() {
	}

	private static class Singleton {
		private static final ReplyService instance = new ReplyServiceImpl();
	}

	public static ReplyService getInstance() {
		return Singleton.instance;
	}

	@Override
	public Reply getParam(HttpServletRequest req) {
		Reply reply = new Reply();

		String param = req.getParameter("replyno");
		if (param != null && !"".equals(param)) {
			try {
				reply.setReplyno(Integer.parseInt(param));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}

		Object userno = req.getSession().getAttribute("userno");
		if (userno != null && !"".equals(userno)) {
			try {
				reply.setUserno((Integer) userno);
			} catch (ClassCastException e) {
				e.printStackTrace();
			}
		}

		param = req.getParameter("boardno");
		if (param != null && !"".equals(param)) {
			try {
				reply.setBoardno(Integer.parseInt(param));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}

		param = req.getParameter("reply");
		if (param != null && !"".equals(param)) {
			reply.setReply(param);
		} else {
			reply.setReply("내용없음");
		}

		return reply;
	}

	@Override
	public Paging getPaging(HttpServletRequest req) {
		Reply reply = new Reply();

		String param = req.getParameter("curPage");
		int curPage = 0;
		if (param != null && !"".equals(param)) {
			try {
				curPage = Integer.parseInt(param);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}

		Object postno = req.getAttribute("postno");
		if (postno != null && !"".equals(postno)) {
			try {
				reply.setPostno((Integer) postno);
			} catch (ClassCastException e) {
				e.printStackTrace();
			}
		}

		Object boardno = req.getAttribute("boardno");
		if (boardno != null && !"".equals(boardno)) {
			try {
				reply.setBoardno((Integer) boardno);
			} catch (ClassCastException e) {
				e.printStackTrace();
			}
		}

//		Reply TB와 curPage 값을 이용한 Paging 객체를 생성하고 반환
		int totalCount = replyDao.selectCntAll(reply);

//		Paging 객체 생성
		Paging paging = new Paging(totalCount, curPage);

		return paging;
	}

	@Override
	public List<Reply> getReplyList(Paging paging, Reply reply) {
		return replyDao.selectAll(paging, reply);
	}

	@Override
	public void writeReply(Reply reply) {
		replyDao.insertReply(reply);
	}

	@Override
	public void updateReply(Reply reply) {
		replyDao.updateReply(reply);
	}

	@Override
	public void deleteReply(Reply reply) {
		replyDao.deleteReply(reply);
	}

	@Override
	public int CountReply(Reply reply) {
		return replyDao.selectCntAll(reply);
	}

	
	// ----- TEST -------------------------------
	
	@Override
	public Paging getPaging(HttpServletRequest req, int i) {

		Reply reply = new Reply();

		String param = req.getParameter("curPage");
		int curPage = 0;
		if (param != null && !"".equals(param)) {
			try {
				curPage = Integer.parseInt(param);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		
		Object userno = req.getSession().getAttribute("userno");
		if (userno != null && !"".equals(userno)) {
			try {
				reply.setUserno((Integer) userno);
			} catch (ClassCastException e) {
				e.printStackTrace();
			}
		}

		Object postno = req.getAttribute("postno");
		if (postno != null && !"".equals(postno)) {
			try {
				reply.setPostno((Integer) postno);
			} catch (ClassCastException e) {
				e.printStackTrace();
			}
		}

		Object boardno = req.getAttribute("boardno");
		if (boardno != null && !"".equals(boardno)) {
			try {
				reply.setBoardno((Integer) boardno);
			} catch (ClassCastException e) {
				e.printStackTrace();
			}
		}

//		Reply TB와 curPage 값을 이용한 Paging 객체를 생성하고 반환

//		System.out.println("리플리서비스임플 겟페이징 : " + reply);
//		System.out.println("리플리서비스임플 겟페이징 : " + i);
		int totalCount = replyDao.selectCntAll(reply, i);


//		Paging 객체 생성
		Paging paging = new Paging(totalCount, curPage);
		return paging;
	}
	
	@Override
	public List<Reply> getReplyList(Paging paging, Reply reply, int i) {
		return replyDao.selectAll(paging, reply, i);
	}
// --------------------------------------------------------------------------	
	
}
