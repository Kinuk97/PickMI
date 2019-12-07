package serivce.impl;


import java.util.ArrayList;
import java.util.List;

import dao.face.MessageDao;
import dao.impl.MessageDaoImpl;
import dto.Chat;
import dto.Chatter;
import dto.User;
import serivce.face.MessageService;

public class MessageServiceImpl implements MessageService {
	
	private MessageDao messageDao = new MessageDaoImpl();

	@Override
	public List<Chat> getChatList() {

		return messageDao.selectMsgAll();
	}

	@Override
	public List<Chat> getLastChatList(Chatter chatter) {
		return messageDao.selectLastMsg(chatter);
	}

	@Override
	public List<User> getSearchList(String search, User user) {
		return messageDao.selectSearchMsgUser(search, user);
	}

	@Override
	public List<Chat> getChattingList(Chat chat) {
		return messageDao.selectChattingList(chat);
	}

	@Override
	public void insert(Chat chat) {
		messageDao.insert(chat);
	}

	@Override
	public void insert(Chatter chatter) {
//		messageDao.insert(chatter);
	}
	
	@Override
	public int selectNextChatno() {
		return messageDao.selectNextChat_no();
	}
	
	@Override
	public void makeRoom(Chatter chatter1, Chatter chatter2) {
		System.out.println(8);
		messageDao.insert(chatter1);
		messageDao.insert(chatter2);
	}
	
	@Override
	public Chat getExistsChatRoom(Chatter chatter1) {
		
		return messageDao.getExistsChatRoom(chatter1);
		
	}
	
	@Override
	public int chattingUserCheck(Chatter chatter1, Chatter chatter2) {
		
		List<Chatter> list = new ArrayList();
		
		list = messageDao.selectExistsChatUser(chatter1);
		
		int chat_no;
		System.out.println(5);
		for (int i=0; i<list.size(); i++) {
			int chat_user = list.get(i).getChat_user();
			
			if(chat_user == chatter2.getChat_user()) {
				chat_no = list.get(i).getChat_no();
				System.out.println(6);
				return chat_no;
			} else {
				int val = selectNextChatno(); //나랑 선택한 유저랑 채팅을 한 적이 없다면 해야하는 동작
				
				chatter1.setChat_no(val);
				chatter2.setChat_no(val);
				System.out.println(7);
				makeRoom(chatter1, chatter2);	
				System.out.println("val : " + val);
				return val;
			}
			
		}
		
		return 0;
		
	}
}
