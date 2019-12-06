package dao.face;

import java.util.List;

import dto.Chat;
import dto.Chatter;
import dto.User;

public interface MessageDao {

	public List<Chat> selectMsgAll();

	public List<Chat> selectLastMsg(Chatter chatter);

	public List<User> selectSearchMsgUser(String search, User user);

	public List<Chat> selectChattingList(Chat chat);

}
