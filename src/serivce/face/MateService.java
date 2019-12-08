package serivce.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Mate;
import dto.ProfileBoard;
import dto.ProjectBoard;

public interface MateService {
	/**
	 * 사용자들 프로필 조회
	 * @param list4
	 * @return
	 */
	public List<ProfileBoard> showUser(Mate mate);
	/**
	 * 참가 신청 한 사용자들의 정보를 불러온다
	 * @param mate
	 * @return
	 */
	public List<Mate> appliedUser(Mate mate);
	/**
	 * 기존에 신청한 적이 있는지 확인하기
	 * @param mate
	 * @return
	 */
	public int checkJoin(Mate mate);
	
	/**
	 * 내가 참가 신청 한 목록 보기
	 * @param mate
	 */
	public List<Mate> waitingAnswer(Mate mate);
	/**
	 * 팀원 참가 신청하면 테이블에 추가하기
	 * @param mate
	 */
	public void wantToJoin(Mate mate);
	/**
	 * 내가 팀장인 프로젝트가 있는지 확인하기
	 * @param mate
	 * @return
	 */
	public boolean checkLeader(Mate mate);
	
	/**
	 * 내가 이 프로젝트의 팀장인지 확인
	 * 
	 * @return
	 */
	public boolean countProjectLeader(Mate mate);
	
	/**
	 * mate테이블에서 유저 삭제하기
	 * @param mate
	 */
	public void removeUserFromTeam(Mate mate);
	/**
	 * 프로젝트 번호로 작성자(유저번호)를 조회한다
	 * @param mate
	 * @return
	 */
	public List<Mate> getUsernoByProjectno(Mate mate);
	/**
	 * 유저번호로 사용자정보 받아오기
	 * @param mate
	 * @return
	 */
	public int getProj_no(Mate mate);
	/**
	 * 유저번호로 팀 번호 받아오기
	 * @param mate
	 * @return
	 */
	public List<Mate> getProj_noByUserno(Mate mate);
	/**
	 * 가입여부 확인하기
	 * @param mate
	 * @return
	 */
	public boolean mateCheck(Mate mate);
	/**
	 * 팀원 신청 수락하기
	 * @param mate
	 * @return
	 */
	public void addMate(Mate mate);

	/**
	 * 팀관리 리스트들을 가져온다
	 * @return
	 */
	public List<Mate> getMateList(Mate mate);

	/**
	 * 요청 파라미터 파싱
	 * @param req
	 * @return
	 */
	public Mate getParam(HttpServletRequest req);
	
	/**
	 * 유저번호로 내가 참여하고 있는 프로젝트 리스트 불러오기
	 * 
	 * @param Mate mate - 검색할 유저번호
	 * @return - 결과 리스트
	 */
	public List<ProjectBoard> getMyProjList(Mate mate);


}
