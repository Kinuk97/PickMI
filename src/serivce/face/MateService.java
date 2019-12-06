package serivce.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Mate;
import dto.ProjectBoard;

public interface MateService {
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
	public Mate getUsernoByProjectno(Mate mate2);
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
	public Mate addMate(Mate mate);

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
