package serivce.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.face.CompBoardDao;
import dao.impl.CompBoardDaoImpl;
import dto.CompBoard;
import serivce.face.CompBoardService;

public class CompBoardServiceImpl implements CompBoardService {
	
	private CompBoardDao compBoardDao = new CompBoardDaoImpl();

	@Override
	public CompBoard getParam(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CompBoard> getBoardList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CompBoard> searchBoard(String searchStr, int categoryNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean writeBoard(CompBoard compBoard) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifyBoard(CompBoard compBoard) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBoard(int comp_no) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CompBoard compBoardDetail(int comp_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void boardLikePost(CompBoard compBoard) {
		// TODO Auto-generated method stub
		
	}

}
