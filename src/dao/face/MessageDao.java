package dao.face;

import java.util.List;

import dto.Chat;
import dto.Chatter;

public interface MessageDao {

	public List<Chat> selectMsgAll();

	public List<Chat> selectLastMsg(Chatter chatter);

}
