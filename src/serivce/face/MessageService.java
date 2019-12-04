package serivce.face;

import java.util.List;

import dto.Chat;
import dto.Chatter;

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
	
	

}
