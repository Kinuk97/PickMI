package serivce.face;

import java.util.List;

import dto.Chat;
import dto.Chatter;
import dto.User;

public interface MessageService {

	/**
	 * 메세지 목록 조회
	 * 
	 * @return List - 메세지 목록
	 */
	public List<Chat> getChatList();

	/**
	 * 최근 메세지 목록 조회
	 * 
	 * @param chatter - 로그인 유저번호
	 * @return List - 메세지 목록
	 */
	public List<Chat> getLastChatList(Chatter chatter);
	
	/**
	 * 메시지를 보낼 사용자 검색
	 * 
	 * @param search - 사용자의 이름 또는 이메일을 검색
	 * @param user - 모든 사용자 유저번호
	 * @return List - 검색한 목록
	 */
	public List<User> getSearchList(String search, User user);

	/**
	 * 
	 * @param chat
	 * @return
	 */
	public List<Chat> getChattingList(Chat chat);
	
	public void insert(Chat chat);

}
