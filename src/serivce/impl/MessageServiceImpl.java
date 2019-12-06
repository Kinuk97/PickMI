package serivce.impl;


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
		messageDao.insert(chatter1);
		messageDao.insert(chatter2);
	}
	
	@Override
	public Chat getExistsChatRoom(Chatter chatter1) {
		
		return messageDao.getExistsChatRoom(chatter1);
		
	}
}
