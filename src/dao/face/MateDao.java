package dao.face;

import java.util.List;

import dto.Mate;
import dto.ProjectBoard;

public interface MateDao {
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
	public Mate selectUsernoByProjectno(Mate mate2);
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

}
