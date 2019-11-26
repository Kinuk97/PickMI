package serivce.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.face.ProfileBoardDao;
import dao.impl.ProfileBoardDaoImpl;
import dto.ProfileBoard;
import serivce.face.ProfileBoardService;
import util.Paging;

public class ProfileBoardServiceImpl implements ProfileBoardService {
	
	private ProfileBoardDao profileBoardDao = new ProfileBoardDaoImpl();
	
	
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
		
		System.out.println("profileBoardServiceImpl : " + profile);
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

		Paging paging = new Paging(totalCount, curPage);
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
