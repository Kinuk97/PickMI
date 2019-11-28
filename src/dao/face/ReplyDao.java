package dao.face;

import java.util.List;

import dto.Reply;
import util.Paging;

public interface ReplyDao {

	/**
	 * 게시글의 댓글 총 개수를 구하는 메소드
	 * 
	 * @param reply - 게시글, 게시판 번호가 있는 DTO
	 * @return int - 게시글의 댓글 수
	 */
	public int selectCntAll(Reply reply);
	
	/**
	 * 해당 게시글의 댓글 목록을 가져오는 메소드
	 * 
	 * @param paging - 페이징 Util 객체
	 * @param reply  - 게시판 번호 게시글 번호가 담겨있는 DTO
	 * @return List<Reply> - 댓글 목록
	 */
	public List<Reply> selectAll(Paging paging, Reply reply);

	/**
	 * 댓글을 DB에 저장하는 메소드
	 * 
	 * @param reply - 작성한 댓글 정보 DTO
	 */
	public void insertReply(Reply reply);

	/**
	 * 댓글을 수정하는 메소드
	 * 
	 * @param reply - 수정한 댓글 DTO
	 */
	public void updateReply(Reply reply);

	/**
	 * 댓글을 DB에서 삭제하는 메소드
	 * 
	 * @param reply - 게시판, 게시글, 댓글 번호가 담겨있는 DTO
	 */
	public void deleteReply(Reply reply);

}
