package serivce.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Reply;
import util.Paging;

public interface ReplyService {

	/**
	 * 받은 요청 파라미터에서 댓글 가져오기
	 * 
	 * @param req - 받은 요청
	 * @return Reply - 댓글DTO로 반환
	 */
	public Reply getParam(HttpServletRequest req);
	
	/**
	 * 댓글 총 개수로 페이징하는 메소드
	 * 
	 * @param req - 받은 요청
	 * @return Paging - 결과 페이징
	 */
	public Paging getPaging(HttpServletRequest req);
	
	/**
	 * 해당 게시글의 댓글 목록을 가져오는 서비스
	 * 
	 * @param paging - 페이징 util 객체
	 * @param reply - 게시글 번호와 게시판 번호가 담겨있는 DTO
	 * @return List<Reply> - 결과 목록
	 */
	public List<Reply> getReplyList(Paging paging, Reply reply);

	/**
	 * 댓글 작성하는 서비스
	 * 
	 * @param reply - 입력받은 댓글 DTO
	 */
	public void writeReply(Reply reply);
	
	/**
	 * 댓글 수정하는 서비스
	 * 
	 * @param reply - 입력받은 댓글 DTO
	 */
	public void updateReply(Reply reply);
	
	/**
	 * 댓글 삭제하는 서비스
	 * 
	 * @param reply - 게시판, 게시글, 댓글 번호가 담겨있는 DTO
	 */
	public void deleteReply(Reply reply);
}
