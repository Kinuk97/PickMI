package serivce.impl;

import java.util.List;

import dao.face.ProfileBoardDao;
import dao.impl.ProfileBoardDaoImpl;
import dto.ProfileBoard;
import serivce.face.ProfileBoardService;

public class ProfileBoardServiceImpl implements ProfileBoardService {
	
	private ProfileBoardDao profileBoardDao = new ProfileBoardDaoImpl();
	@Override
	public List<ProfileBoard> getProfileList() {
		return profileBoardDao.selectProfileList();
	}

}
