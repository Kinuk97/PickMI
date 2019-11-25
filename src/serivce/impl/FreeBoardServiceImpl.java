package serivce.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.face.FreeBoardDao;
import dao.impl.FreeBoardDaoImpl;
import dto.FreeBoard;
import serivce.face.FreeBoardService;

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
	public List<FreeBoard> getBoardList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FreeBoard> getBoardListByCategory(int categoryno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FreeBoard> searchBoard(String searchStr, int filter) {
		// TODO Auto-generated method stub
		return null;
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
