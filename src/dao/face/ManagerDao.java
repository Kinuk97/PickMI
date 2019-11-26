package dao.face;

import java.util.List;

import dto.Manager;
import dto.ProfileBoard;

public interface ManagerDao {
	

	/**
	 * mgrid와 mgrpw가 일치하는 관리자 수 조회
	 * @param mgr - mgrid와 mgrpw를 가진 관리자
	 * @return int -1(존재하는 회원), 0(존재하지않는회원), -1(에러)
	 */
	public int selectCntMemberByMgridAndMgrpw(Manager mgr);
	
	/**
	 * mgrid로 관리자 정보 조회
	 * @param mgr - 조회할 관리자, mgrid 필수
	 * @return Manager 조회된 결과
	 */
	public Manager selectMgrByMgr(Manager mgr);

	/**
	 * 게시글 전체 목록 조회
	 * @return List<ProfileBoard> - 조회된 게시글 목록
	 */
	public List<ProfileBoard> pbselectAll();
	
	
	
	
}
