package dao.face;

import java.util.List;

import dto.Mate;
import dto.ProfileBoard;
import dto.ProjectBoard;
import dto.User;

public interface MateDao {
	
	public void inviteMate (Mate mate);
	/**
	 * 사용자가 가지고 있는 프로젝트 조회
	 * @param mate
	 * @return
	 */
	public List<ProjectBoard> selectMyproject(Mate mate);
	/**
	 * 사용자의 프로필이 있는지 확인한다
	 * @param mate
	 * @return
	 */
	public int countProfile(Mate mate);
	/**
	 * 사용자의 프로필이 없는 경우를 위해 이름만 조회
	 * @param mate
	 * @return
	 */
	public List<User> showUserName(Mate mate);
	
	public List<ProfileBoard> showUser(Mate	mate);
	/**
	 * 가입 신청한 사용자 정보 불러오기
	 * @param mate
	 * @return
	 */
	public List<Mate> selectUsers(Mate mate);
	
	/**
	 * 전에 가입했었는지 체크
	 * @param mate
	 * @return
	 */
	public int countMyTeam(Mate mate);
	/**
	 * 내가 팀 등급 확인하기
	 * @param mate
	 * @return
	 */
	public Mate selectMylog(Mate mate);
	/**
	 * 내가 참가 신청한 팀 목록 보기
	 * @param mate
	 * @return
	 */
	public List<Mate> selectWantToJoinList(Mate mate);
	/**
	 * 팀 참가 신청 하면 팀관리 테이블에 추가하기
	 * @param mate
	 */
	public void insertMate(Mate mate);
	/**
	 * 사용자가 몇개의 팀장을 맡고 있는지 확인한다
	 * @param mate
	 * @return
	 */
	public int countLeader(Mate mate);
	/**
	 * 프로젝트 게시판에 글이 작성될때 팀이 형성된다
	 * @param mate
	 */
	public void insertTeam(ProjectBoard projectBoard);
	/**
	 * mate테이블에서 유저를 삭제한다
	 * @param mate
	 */
	public void deleteUserFromTeam(Mate mate);
	/**
	 *프로젝트 게시글의 작성자(유저번호)를 알아낸다
	 * @param mate2
	 * @return
	 */
	public List<Mate> selectUsernoByProjectno(Mate mate2);
	/**
	 * 유저번호로 프로젝트 번호를 조회한다
	 * @param mate
	 * @return
	 */
	public int selectProj_no(Mate mate);
	/**
	 * 유저번호로 팀 가입현황을 조회한다
	 * @param mate
	 * @return
	 */
	public List<Mate> selectProj_noByUserno(Mate mate);
	
	/**
	 * 팀원목록을 불러온다
	 * @return
	 */
	public List<Mate> selectMateList(Mate mate);
	
	/**
	 * 내가 참여하고 있는 프로젝트 리스트
	 * 
	 * @param mate - userno가 담겨있는 DTO
	 * @return
	 */
	List<ProjectBoard> myProjectList(Mate mate);
	
	/**
	 * 팀원초대 수락
	 * 
	 * @param mate
	 */
	public void updateMate(Mate mate);
	
	/**
	 * 내가 이 프로젝트의 팀장인지 확인하는 메소드
	 * 
	 * @param mate
	 * @return
	 */
	public int countProjectLeader(Mate mate);

}
