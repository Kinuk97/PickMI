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
		
		param = req.getParameter("categoryno");
		int categoryno = 0;
		if (param != null && !"".equals(param)) {
			try {
				categoryno = Integer.parseInt(param);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		
//		System.out.println("curPage : " + curPage);

		// Board TB와 curPage값을 이용한 Paging객체를 생성하고 반환
		int totalCount = compBoardDao.selectCntAll(search, categoryno);

		// Paging객체 생성
		Paging paging = new Paging(totalCount, curPage, 20);

		paging.setSearch(search);
		paging.setCategoryno(categoryno);
		
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

	
	@Override
	public void write(CompBoard compBoard) {
		
		compBoardDao.insert(compBoard);
	}
	
	
	@Override
	public void write(HttpServletRequest req, HttpServletResponse resp) {
		
		//응답 객체 Content-type설정
		resp.setContentType("text/html; charset=UTF-8");
		
		//응답 객체 출력 스트림 얻기
		PrintWriter out = null;
		
		try {
			out = resp.getWriter();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
		// 1. 파일업로드 형태의 데이터가 맞는지 확인
		// enctype이 multipart/form-data가 맞는지 확인
		boolean isMultipart = false;
		isMultipart = ServletFileUpload.isMultipartContent(req);
		// tomcat.util은 servlet자체에 있는 기본 기능, 반환타입같은 거 달라지므로 쓰지말것
		// 다운받은 commons가 더 이득이다.

		// 1-1. multipart/form-data 인코딩으로 전송되지 않았을 경우
		if (!isMultipart) {

			out.append("<h1>enctype이 multipart/form-data가 아님</h1>");
			out.append("<a href='/commons/fileupload'>이동</a>");

			return;

		}

		// 1.2 여기 이후는 multipart/form-data로 요청된 상황임
		// 파일이 전송되었음

		// 2. 업로드된 파일을 처리하는 아이템팩토리 객체 생성

		// ItemFactory : 업로드된 파일을 처리하는 방식을 정하는 클래스

		// FileItem : 클라이언트로부터 전송된 데이터를 객체화시킨 것

		// DiskFileItemFactory class
		// -> 디스크기반(HDD)의 파일 아이템 처리 API
		// -> 업로드된 파일을 디스크에 임시저장하고 후처리한다.
		DiskFileItemFactory factory = null;
		factory = new DiskFileItemFactory();

		// 3. 업로드된 아이템이 용량이 적당히 작으면 메모리에서 처리
		int maxMem = 1 * 1024 * 1024; // 1MB - 1MB를 넘으면 메모리에서 처리할 수 없음
		factory.setSizeThreshold(maxMem);

		// 4. 용량이 적당히 크면 임시파일을 만들어서 처리(디스크)
		ServletContext context = req.getServletContext();

		String path = context.getRealPath("tmp");

		File repository = new File(path);

		// test코드
//		System.out.println("repository : " + repository);

		factory.setRepository(repository);

		// 5. 업로드 허용 용량 기준을 넘지 않을 경우에만 업로드 처리
		int maxFile = 10 * 1024 * 1024; // 10MB

		// 파일 업로드 객체 생성 - DiskFileItemFactory 이용해서 생성함
		ServletFileUpload upload = null;

		upload = new ServletFileUpload(factory);

		// 파일 업로드 용량제한 설정 : 10MB
		upload.setFileSizeMax(maxFile);

		// --- 파일 업로드 준비 완료 ---

		// 6. 업로드된 데이터 추출(파싱)
		// 임시 파일 업로드도 같이 수행함
		List<FileItem> items = null;

		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		// 7. 파싱된 데이터 처리하기
		// items 리스트에 요청파라미터가 파싱되어있음

		// 요청정보의 형태 3가지
		// 1. 빈파일 (용량이 0인 파일)
		// 2. form-data (일반적인 요청 파라미터)
		// 3. 파일

		Iterator<FileItem> iter = items.iterator();

		CompBoard compBoard = new CompBoard();
		Files files = new Files();

		// 모든 요청정보 처리
		while (iter.hasNext()) {

			FileItem item = iter.next();

			// 1) 빈 파일 처리
			if (item.getSize() <= 0)
				continue;

			// 2) 일반적인 요청 데이터 처리
			if (item.isFormField()) {

				// form-data일 경우
				// key:value 쌍으로 전달된 요청 파라미터

				// key - getFieldName()
				// value - getString()

				// 기본 처리 방식
//				out.println(" - - - 폼 필드 - - - <br>");
//				out.println("키 : " + item.getFieldName() + "<br>" );
//				out.println("값 : " + item.getString() + "<br>" );

				// key값에 따라 처리방식 다르게 하기
				String key = item.getFieldName();

				if ("title".equals(key)) {

					try {
						compBoard.setComp_title(item.getString("utf-8"));

					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}

				} 
				else if ("content".equals(key)) {

//					try {
////						CompBoard.setComp_content(item.getString("utf-8"));
//
//					} catch (UnsupportedEncodingException e) {
//						e.printStackTrace();
//					}

				

				} // key값 비교 if

			} else { // 3) 파일 처리

				// 업로드 파일 처리 방법 2가지

				// 1. 웹 서버의 로컬 디스크에 저장, 파일의 정보는 DB에 기록
				// 파일의 정보 = (fileno(pk), userid, boardno, title, upload_date, path)

				// 2. DB의 테이블에 컬럼으로 저장

				// -----------------------------------------------------------------------------

				// 로컬파일로 저장하고 DB에 기록하는 방식으로 진행

				// --- UUID 생성 ---
				UUID uuid = UUID.randomUUID(); // 랜덤 UID 생성

				// 12자리 uid얻기
				String u = uuid.toString().split("-")[4];

				// -----------------------------------------------------------------------------

				// 로컬파일 저장소에 파일 저장하기

				// 로컬 저장소 파일 객체
				File up = new File(context.getRealPath("upload"), item.getName() + "_" + u);

				// 파일의 경로는 "/upload"
				// 파일의 이름은 "원본명_uid"

//				System.out.println(up);

				// - - - DB에 업로드 정보 기록하기 - - -
				// 파일번호 fileno
				// 원본파일명 origin_name
				// 저장파일명 stored_name
				// - - - - - - - - - - - - - - - - - - -

				files.setFilename(item.getName() + "_" + u);
//				files.setBoardno((int)item.getSize());
				// ------------------------------------------------------------------------------

				// - - - 처리가 완료된 파일 업로드 하기 - - -
				// 로컬 저장소에 실제 저장

				try {
					item.write(up); // 실제업로드
					item.delete(); // 임시파일 삭제

				} catch (Exception e) {
					e.printStackTrace();
				}

				// -------------------------------------------------------------------------------

			} // 파일처리 if

		} // 요청파라미터 처리 while
		
		int nextval = compBoardDao.selectCompBoardno();
		
		compBoard.setComp_no(nextval);
		
		compBoard.setUserno((int) req.getSession().getAttribute("userno"));
		
		write(compBoard);
		
		if (files.getFilename() != null) {
			files.setBoardno(nextval);
			
			compBoardDao.insertFile(files);
		}
	}



	


}
