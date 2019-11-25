package dao.impl;

import java.util.List;

import dao.face.FreeBoardDao;
import dto.FreeBoard;

public class FreeBoardDaoImpl implements FreeBoardDao {

	private FreeBoardDaoImpl() {
	}

	private static class Singleton {
		private static final FreeBoardDao instance = new FreeBoardDaoImpl();
	}

	public static FreeBoardDao getInstance() {
		return Singleton.instance;
	}

	@Override
	public int getNextBoardno() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<FreeBoard> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FreeBoard> selectBoardByTitle(FreeBoard freeBoard) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FreeBoard> selectBoardByUser(FreeBoard freeBoard) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FreeBoard> selectBoardByTitleContent(FreeBoard freeBoard) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FreeBoard> selectBoardByCategory(FreeBoard freeBoard) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertBoard(FreeBoard freeBoard) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateBoard(FreeBoard freeBoard) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBoard(FreeBoard freeBoard) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public FreeBoard boardView(FreeBoard freeBoard) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void countViews(FreeBoard freeBoard) {
		// TODO Auto-generated method stub

	}

}
