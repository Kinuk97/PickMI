package serivce.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.face.FreeBoardDao;
import dao.impl.FreeBoardDaoImpl;
import dto.FreeBoard;
import serivce.face.FreeBoardService;
import util.Paging;

public class FreeBoardServiceImpl implements FreeBoardService {
	private FreeBoardDao freeBoardDao = FreeBoardDaoImpl.getInstance();

	private FreeBoardServiceImpl() {
	}

	private static class Singleton {
		private static final FreeBoardService instance = new FreeBoardServiceImpl();
	}

	public static FreeBoardService getInstance() {
		return Singleton.instance;
	}

	@Override
	public FreeBoard getParam(HttpServletRequest req) {
		FreeBoard freeBoard = new FreeBoard();

		String param = req.getParameter("free_no");
		if (param != null && !"".equals(param)) {
			try {
				freeBoard.setFree_no(Integer.parseInt(param));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}

		Object userno = req.getSession().getAttribute("userno");
		if (userno != null && !"".equals(userno)) {
			try {
				freeBoard.setUserno((Integer) userno);
			} catch (ClassCastException e) {
				e.printStackTrace();
			}
		}

		param = req.getParameter("categoryno");
		if (param != null && !"".equals(param)) {
			try {
				freeBoard.setCategoryno(Integer.parseInt(param));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}

		param = req.getParameter("free_title");
		if (param != null && !"".equals(param)) {
			freeBoard.setFree_title(param);
		}

		param = req.getParameter("free_content");
		if (param != null && !"".equals(param)) {
			freeBoard.setFree_content(param);
		}

		return freeBoard;
	}

	@Override
	public Paging getPaging(HttpServletRequest req) {
		String param = req.getParameter("curPage");
		int curPage = 0;
		if (param != null && !"".equals(param)) {
			try {
				curPage = Integer.parseInt(param);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}

		param = req.getParameter("search");
		String search = null;
		if (param != null && !"".equals(param)) {
			search = param;
		}

		param = req.getParameter("searchno");
		int searchno = 0;
		if (param != null && !"".equals(param)) {
			try {
				searchno = Integer.parseInt(param);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}

		param = req.getParameter("categoryno");
		int categoryno = 0;
		if (param != null && !"".equals(param)) {
			try {
				categoryno = Integer.parseInt(param);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}

		// Board TB와 curPage 값을 이용한 Paging 객체를 생성하고 반환
		int totalCount = freeBoardDao.selectCntAll(search, searchno, categoryno);

		// Paging 객체 생성
		Paging paging = new Paging(totalCount, curPage, 20);

		paging.setSearch(search);
		paging.setSearchno(searchno);
		paging.setCategoryno(categoryno);

		return paging;
	}

	@Override
	public List<FreeBoard> getBoardList(Paging paging) {
		return freeBoardDao.selectAll(paging);
	}

	// 파일 처리가 없는 글쓰기 (사용 x, FileService로 대체)
	@Override
	public boolean writeBoard(FreeBoard freeBoard) {
//		 일반적인 게시글 요청
		if (freeBoard.getUserno() == 0) {
			return false;
		}

		int queryResult = freeBoardDao.insertBoard(freeBoard);
		boolean result = false;

		if (queryResult == 1) {
			result = true;
		} else {
			result = false;
		}

		return result;
	}

	@Override
	public boolean modifyBoard(FreeBoard freeBoard) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeBoard(FreeBoard freeBoard) {
		int result = freeBoardDao.deleteBoard(freeBoard);

		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public FreeBoard FreeBoardDetail(FreeBoard freeBoard) {
		return freeBoardDao.boardView(freeBoard);
	}

	@Override
	public void viewCounting(FreeBoard freeBoard) {
		freeBoardDao.countViews(freeBoard);
	}

}
