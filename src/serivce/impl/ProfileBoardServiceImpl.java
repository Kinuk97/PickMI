package serivce.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.face.ProfileBoardDao;
import dao.impl.ProfileBoardDaoImpl;
import dto.Files;
import dto.ProfileBoard;
import serivce.face.ProfileBoardService;
import util.Paging;

public class ProfileBoardServiceImpl implements ProfileBoardService {
	
	private ProfileBoardDao profileBoardDao = new ProfileBoardDaoImpl();
	
	
	@Override
	public ProfileBoard getNameByUserno(HttpServletRequest req) {
		
		String param = req.getParameter("userno");

		int userno = 0;
		if (param != null && !"".equals(param)) {
			userno = Integer.parseInt(param);
		}
		// DTO에 저장
		ProfileBoard profile = new ProfileBoard();
		profile.setUserno(userno);
		
		System.out.println("profileBoardServiceImpl : " + profile);
		return profile;
	}
	@Override
	public void write(HttpServletRequest req) {
		
		boolean isMultipart = false;
		isMultipart = ServletFileUpload.isMultipartContent(req);

		if (!isMultipart) {

			return;
		}
		DiskFileItemFactory factory = null;
		factory = new DiskFileItemFactory();
		
		int maxMem = 1 * 1024 * 1024; // 1mb
		factory.setSizeThreshold(maxMem);
		
		ServletContext context = req.getServletContext();
		String path = context.getRealPath("tmp");

		File repository = new File(path);

		factory.setRepository(repository);
		
		int maxFile = 10 * 1024 * 1024; // 10MB
		
		// 파일 업로드 객체 생성
		ServletFileUpload upload = null;
		upload = new ServletFileUpload(factory);
		
		upload.setFileSizeMax(maxFile);
		
		List<FileItem> items = null;
		
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		Iterator<FileItem> iter = items.iterator();
		
		ProfileBoard profile = new ProfileBoard();
		Files file = null;
		
		while (iter.hasNext()) {

			FileItem item = iter.next();

			// 1) 빈파일 처리
			if (item.getSize() <= 0)
				continue;

			// 2) 일반적인 요청 데이터 처리
			if (item.isFormField()) {
				String key = item.getFieldName();

				if ("prof_interest".equals(key)) {
					try {
						profile.setProf_interest(item.getString("utf-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				} else if ("prof_job".equals(key)) {
					try {
						profile.setProf_job(item.getString("utf-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				} else if ("prof_state".equals(key)) {
					try {
						profile.setProf_state(item.getString("utf-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				} else if ("prof_loc".equals(key)) {
					try {
						profile.setProf_loc(item.getString("utf-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				} else if ("prof_career".equals(key)) {
					try {
						profile.setProf_career(item.getString("utf-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				} else if ("prof_content".equals(key)) {
					try {
						profile.setProf_content(item.getString("utf-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
			} else {
				file = new Files();
			
			//uuid 생성
			UUID uuid = UUID.randomUUID();	
			String u = uuid.toString().split("-")[4];
		
			File up = new File(context.getRealPath("upload"), item.getName() + "_" + u);

			file.setFilename(item.getName());
//			file.setStoredname(item.getName() + "_" + u);

			try {
				item.write(up);
				item.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
		int profileno = profileBoardDao.selectProfileno();
		
		HttpSession session = req.getSession();
		
		profile.setUserno((int)session.getAttribute("userno"));
		profile.setProf_no(profileno);
		profileBoardDao.insertProfile(profile);
		if(file != null) {
			
			file.setPostno(1);
			file.setBoardno(profileno);
			profileBoardDao.insertFile(file);
		}
	
	}
	
	@Override
	public ProfileBoard view(ProfileBoard profile) {
		
		return profileBoardDao.selectProfileByProfileno(profile);
	}
	/**
	 * 요청파라미터 받기
	 */
	@Override
	public ProfileBoard getProfileno(HttpServletRequest req) {
		// 전달 파라미터 받기
		String param = req.getParameter("profileno");

		int prof_no = 0;
		if (param != null && !"".equals(param)) {
			prof_no = Integer.parseInt(param);
		}
		// DTO에 저장
		ProfileBoard profile = new ProfileBoard();
		profile.setProf_no(prof_no);
		
//		System.out.println("profileBoardServiceImpl : " + profile);
		return profile;
	}
	
	/**
	 * 페이징을 이용한 전체 목록 조회
	 */
	@Override
	public List<ProfileBoard> getBoardList(Paging paging) {
		return profileBoardDao.selectAll(paging);
	}
	
	@Override
	public Paging getPaging(HttpServletRequest req) {
		// 요청 파라미터 파싱하기
		String param = req.getParameter("curPage");
		int curPage = 0;
		if (param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		}
//		System.out.println("curPage :" + curPage);

//		String search = req.getParameter("search");
		
		// paging객체를 생성하고 반환
		int totalCount = profileBoardDao.selectCntAll();

		Paging paging = new Paging(totalCount, curPage, 20);
		// Paging 객체 생성
		return paging;
	}
	
	/**
	 * 프로필 목록을 가져오는 메소드
	 */
	@Override
	public List<ProfileBoard> getProfileList() {
		return profileBoardDao.selectProfileList();
	}
	
	

}
