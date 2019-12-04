package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.face.MessageDao;
import dbutil.DBConn;
import dto.Chat;
import dto.Chatter;

public class MessageDaoImpl implements MessageDao {
	
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public List<Chat> selectMsgAll() {
		
		//DB연결
		conn = DBConn.getConnection();
		
		//수행할 SQL쿼리
		String sql = "";
		sql += "SELECT * FROM chat ORDER BY chat_sendtime";
		
		//결과 저장 리스트
		List<Chat> chatList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Chat chat = new Chat();

				chat.setChat_no( rs.getInt("chat_no"));
				chat.setChat_sender( rs.getInt("chat_sender"));
				chat.setChat_msg( rs.getString("chat_msg"));
				chat.setChat_sendtime( rs.getDate("chat_sendtime"));
				
				chatList.add(chat);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(ps!=null) ps.close();
				if(rs!=null) rs.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return chatList;
	}


	@Override
	public List<Chat> selectLastMsg(Chatter chatter) {
		
		//DB연결
		conn = DBConn.getConnection();
		
		//수행할 SQL쿼리
		String sql = "";
		sql += "SELECT chat_no, chat_sender, chat_msg, chat_sendtime, username FROM (";
		sql += "	SELECT";
		sql += "		chat.*";
		sql += "		, (SELECT name FROM user_table U WHERE chat.chat_sender = U.userno) username";
		sql += "		, rank() over(partition by chat_no order by chat_sendtime desc) top";
		sql += "	FROM chat";
		sql += "	WHERE 1=1";
		sql += "		AND chat_no IN ( SELECT chat_no FROM chatter WHERE chat_user = ? )";
//		sql += "		AND chat_sender <> ?";
		sql += " )";
		sql += " WHERE top = 1";
		sql += " ORDER BY chat_sendtime DESC";

		
		//결과 저장 리스트
		List<Chat> chatList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, chatter.getChat_user());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Chat chat = new Chat();
				
				chat.setChat_no( rs.getInt("chat_no"));
				chat.setChat_sender( rs.getInt("chat_sender"));
				chat.setChat_msg( rs.getString("chat_msg"));
				chat.setChat_sendtime( rs.getDate("chat_sendtime"));
				chat.setUsername( rs.getString("username"));
				
				chatList.add(chat);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(ps!=null) ps.close();
				if(rs!=null) rs.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return chatList;
	}
}
