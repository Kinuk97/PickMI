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
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Paging getPaging(HttpServletRequest req) {
		String param = req.getParameter("curPage");
		int curPage = 0;
		if (param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		}
		
		param = req.getParameter("search");
		String search = null;
		if (param != null && !"".equals(param)) {
			search = param;
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
		int totalCount = freeBoardDao.selectCntAll(search, categoryno);

		// Paging 객체 생성
		Paging paging = new Paging(totalCount, curPage, 20);
		
		paging.setSearch(search);
		paging.setCategoryno(categoryno);

		return paging;
	}

	@Override
	public List<FreeBoard> getBoardList(Paging paging) {
		return freeBoardDao.selectAll(paging);
	}

	@Override
	public boolean writeBoard(FreeBoard freeBoard) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifyBoard(FreeBoard freeBoard) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeBoard(FreeBoard freeBoard) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public FreeBoard FreeBoardDetail(FreeBoard freeBoard) {
		// TODO Auto-generated method stub
		return null;
	}


}
