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
	
	
//	@Override
//	public void write(HttpServletRequest req, HttpServletResponse resp) {
//		
//		//응답 객체 Content-type설정
//		resp.setContentType("text/html; charset=UTF-8");
//		
//		//응답 객체 출력 스트림 얻기
//		PrintWriter out = null;
//		
//		try {
//			out = resp.getWriter();
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//			
//		}
//		
//		boolean isMultipart = false;
//		isMultipart = ServletFileUpload.isMultipartContent(req);
//
//		if (!isMultipart) {
//
//			return;
//		}
//
//		DiskFileItemFactory factory = null;
//		factory = new DiskFileItemFactory();
//
//		int maxMem = 1 * 1024 * 1024; // 1MB - 1MB를 넘으면 메모리에서 처리할 수 없음
//		factory.setSizeThreshold(maxMem);
//
//		ServletContext context = req.getServletContext();
//
//		String path = context.getRealPath("tmp");
//
//		File repository = new File(path);
//
//		factory.setRepository(repository);
//
//		int maxFile = 10 * 1024 * 1024; // 10MB
//
//		ServletFileUpload upload = null;
//
//		upload = new ServletFileUpload(factory);
//
//		upload.setFileSizeMax(maxFile);
//
//		List<FileItem> items = null;
//
//		try {
//			items = upload.parseRequest(req);
//		} catch (FileUploadException e) {
//			e.printStackTrace();
//		}
//
//		Iterator<FileItem> iter = items.iterator();
//
//		CompBoard compBoard = new CompBoard();
//		Files files = new Files();
//
//		// 모든 요청정보 처리
//		while (iter.hasNext()) {
//
//			FileItem item = iter.next();
//
//			// 1) 빈 파일 처리
//			if (item.getSize() <= 0)
//				continue;
//
//			// 2) 일반적인 요청 데이터 처리
//			if (item.isFormField()) {
//
//				String key = item.getFieldName();
//
//				if ("comp_title".equals(key)) {
//
//					try {
//						compBoard.setComp_title(item.getString("utf-8"));
//
//					} catch (UnsupportedEncodingException e) {
//						e.printStackTrace();
//						
//					} 
//					
//				} else if ("comp_name".equals(key)) {
//					
//					try {
//						compBoard.setComp_name(item.getString("utf-8"));
//						
//					} catch (UnsupportedEncodingException e) {
//						e.printStackTrace();
//					}
//					
//				} else if ("comp_member".equals(key)) {
//
//					try {
//						compBoard.setComp_member(Integer.parseInt(item.getString("utf-8")));
//
//					} catch (UnsupportedEncodingException e) {
//						e.printStackTrace();
//						
//					}
//			
//
//				} else if ("comp_startdate".equals(key)) {
//					
//					try {
//						compBoard.setComp_startdate(Integer.parseInt(item.getString("utf-8")));
//						
//					} catch (UnsupportedEncodingException e) {
//						e.printStackTrace();
//					
//					}
//					
//				} else if ("comp_enddate".equals(key)) {
//					
//					try {
//						compBoard.setComp_enddate(Integer.parseInt(item.getString("utf-8")));
//						
//					} catch (UnsupportedEncodingException e) {
//						e.printStackTrace();
//					
//					}
//					
//				} else if ("comp_content".equals(key)) {
//					
//					try {
//						compBoard.setComp_content(item.getString("utf-8"));
//						
//					} catch (UnsupportedEncodingException e) {
//						e.printStackTrace();
//					
//					}
//					
//				}// key값 비교 if
//
//			} else { 
//				
//				// --- UUID 생성 ---
//				UUID uuid = UUID.randomUUID(); // 랜덤 UID 생성
//
//				String u = uuid.toString().split("-")[4];
//
//				File up = new File(context.getRealPath("upload"), item.getName() + "_" + u);
//
////				files.setFilename(item.getName() + "_" + u);
////				files.setBoardno((int)item.getSize());
//
//				try {
//					item.write(up); // 실제업로드
//					item.delete(); // 임시파일 삭제
//
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//
//				// -------------------------------------------------------------------------------
//
//			} // 파일처리 if
//
//		} // 요청파라미터 처리 while
//		
//		int nextval = compBoardDao.selectCompBoardno();
//		
//		compBoard.setComp_no(nextval);
//		
//		compBoard.setUserno((int) req.getSession().getAttribute("userno"));
//		
//		write(compBoard);
//		
////		if (files.getFilename() != null) {
////			files.setBoardno(nextval);
////			
////			compBoardDao.insertFile(files);
////		}
//	}
//
}
