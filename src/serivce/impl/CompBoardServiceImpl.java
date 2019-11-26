package serivce.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.face.CompBoardDao;
import dao.impl.CompBoardDaoImpl;
import dto.CompBoard;
import dto.ProfileBoard;
import serivce.face.CompBoardService;
import util.Paging;

public class CompBoardServiceImpl implements CompBoardService {
	
	private CompBoardDao compBoardDao = new CompBoardDaoImpl();
	

	@Override
	public Paging getPaging(HttpServletRequest req) {

		// 요청파라미터 curPage를 파싱한다.
		String param = req.getParameter("curPage");

		int curPage = 0;

		if (param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		}

		System.out.println("curPage : " + curPage);

		// Board TB와 curPage값을 이용한 Paging객체를 생성하고 반환
		int totalCount = compBoardDao.selectCntAll();

		// Paging객체 생성
		Paging paging = new Paging(totalCount, curPage);

		return paging;
	}

	@Override
	public CompBoard getParam(HttpServletRequest req) {
		
		System.out.println(req.getParameter("comp_no"));
		
		//요청 파라미터 comp_no를 파싱
		String param = req.getParameter("comp_no");
		
		int comp_no = 0;
		//전달 파라미터를 int형으로 변환
		if (param != null && !"".equals(param)) {
			comp_no = Integer.parseInt(param);
		}
		
		//전달 파라미터를 DTO에 담기
		CompBoard compBoard = new CompBoard();
		compBoard.setComp_no(comp_no);
		
		compBoard = compBoardDao.boardViewByComp_no(compBoard);
		
		return compBoard;
	}

	@Override
	public List<CompBoard> getBoardList(Paging paging) {
		
		return compBoardDao.compList(paging);
	}

	@Override
	public CompBoard compBoardDetail(CompBoard compBoard) {
		
		return compBoardDao.boardViewByComp_no(compBoard);
	}


}
