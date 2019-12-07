package serivce.impl;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.face.CompBoardDao;
import dao.impl.CompBoardDaoImpl;
import dto.CompBoard;
import dto.Files;
import dto.LikePost;
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
		
//		System.out.println("curPage : " + curPage);

		// Board TB와 curPage값을 이용한 Paging객체를 생성하고 반환
		int totalCount = compBoardDao.selectCntAll(search, searchno);

		// Paging객체 생성
		Paging paging = new Paging(totalCount, curPage, 20);

		paging.setSearch(search);
		paging.setSearchno(searchno);
		
		return paging;
	}

	
	@Override
	public CompBoard getParam(HttpServletRequest req) {
		
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

	
	@Override
	public void write(CompBoard compBoard) {
		
		compBoardDao.insert(compBoard);
	}


	@Override
	public void delete(CompBoard compBoard) {
		
		compBoardDao.deleteboard(compBoard);
		
	}


	@Override
	public boolean checkLike(LikePost like) {
		
		int check = compBoardDao.checkCountUserByUserno(like);
		
		if (check == 0) {
			return true; //전에 찜을 한 적 없으면 함.
			
		} else {
			return false; //전에 찜을 한 적 있으면 안 함.
		}
		
	}


	@Override
	public void like(LikePost like) {
		
		boolean check = checkLike(like);
		
		if (check) {
			compBoardDao.insertLike(like);
		} else {
			compBoardDao.deleteLike(like);
		}
	}


	@Override
	public int countLike(LikePost like) {
		
		return compBoardDao.selectCountLike(like);
	}


	@Override
	public List<CompBoard> getMainCompList() {
		
		return compBoardDao.selectListToMain();
	}

}
