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
import dto.User;

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
				chat.setChat_sendtime( rs.getString("chat_sendtime"));
//				chat.setChat_sendtime( rs.getDate("chat_sendtime"));
				
				chatList.add(chat);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				
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
		sql += "SELECT chat_no, chat_sender, chat_msg, TO_CHAR(chat_sendtime,'YYYY/MM/DD HH24:MI:SS') chat_sendtime, username FROM (";
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
				chat.setChat_sendtime( rs.getString("chat_sendtime"));
				chat.setUsername( rs.getString("username"));
				
				chatList.add(chat);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return chatList;
	}


	@Override
	public List<User> selectSearchMsgUser(String search, User user) {
		
		//DB연결
		conn = DBConn.getConnection();

		//수행할 SQL쿼리
		String sql = "";
		sql += "SELECT name, email, userno ";
		sql += "FROM user_table ";
		
		if ( search != null && !"".equals(search) ) {
			sql += "WHERE 1 = 1";
			sql += " AND ( email LIKE '%' || ? || '%' OR name LIKE '%' || ? || '%' )";
		}
				
		//결과 저장 리스트
		List<User> searchList = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql);

			if (search != null) {
				ps.setString(1, search);
				ps.setString(2, search);
			}

			rs = ps.executeQuery();

			while(rs.next()) {
				User users = new User();

				users.setEmail( rs.getString("email"));
				users.setName( rs.getString("name"));
				users.setUserno( rs.getInt("userno"));

				searchList.add(users);
//				System.out.println("users : " + users);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return searchList;
	}


	@Override
	public List<Chat> selectChattingList(Chat chat) {

		//DB연결
		conn = DBConn.getConnection();

		//수행할 SQL쿼리
		String sql = "";
		sql += "SELECT chat_no, chat_sender, chat_msg, TO_CHAR(chat_sendtime, 'YYYY/MM/DD HH24:MI:SS') chat_sendtime, ";
		sql += "	(SELECT name FROM user_table WHERE chat.chat_sender = userno) username "; 
		sql += "FROM chat ";
		sql += "WHERE chat_no = ? ";
		sql += "ORDER BY chat_sendtime";
				
		//결과 저장 리스트
		List<Chat> chattingList = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, chat.getChat_no());

			rs = ps.executeQuery();

			while(rs.next()) {
				Chat chats = new Chat();

				chats.setChat_no( rs.getInt("chat_no"));
				chats.setChat_sender( rs.getInt("chat_sender"));
				chats.setChat_msg( rs.getString("chat_msg"));
				chats.setChat_sendtime( rs.getString("chat_sendtime"));
				chats.setUsername( rs.getString("username"));

				chattingList.add(chats);
//				System.out.println("chats : " + chats);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return chattingList;
	}

	@Override
	public void insert(Chat chat) {
		//DB연결
		conn = DBConn.getConnection();

		//수행할 SQL쿼리
		String sql = "";
		sql += "INSERT INTO chat ( chat_no, chat_sender, chat_msg, chat_sendtime )";
		sql += " VALUES( ?, ?, ?, sysdate )";
				
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, chat.getChat_no());
			ps.setInt(2, chat.getChat_sender());
			ps.setString(3, chat.getChat_msg());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if(ps!=null) ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void insert(Chatter chatter) {
		//DB연결
		conn = DBConn.getConnection();

		//수행할 SQL쿼리
		String sql = "";
		sql += "INSERT INTO chatter (chat_no, chat_user)";
		sql += " VALUES(?, ?)";
				
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, chatter.getChat_no());
			ps.setInt(2, chatter.getChat_user());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if(ps!=null) ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public int selectNextChat_no() {
		//DB연결
		conn = DBConn.getConnection();
		
		//수행할 SQL쿼리
		String sql = "";
		sql += "SELECT chatter_seq.nextval FROM dual";
		
		int result = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	@Override
	public Chat getExistsChatRoom(Chatter chatter1) {
		//DB연결
		conn = DBConn.getConnection();

		//수행할 SQL쿼리
		String sql = "";
		sql += "SELECT * FROM chat, chatter ";
		sql += " WHERE chat.chat_no = chatter.chat_no";

		Chat chat = new Chat();
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while(rs.next()) {
				
				chat.setChat_no( rs.getInt("chat_no"));
				chat.setChat_sender( rs.getInt("chat_sender"));
				chat.setChat_msg( rs.getString("chat_msg"));
				chat.setChat_sendtime( rs.getString("chat_sendtime"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return chat;
	}
	
	@Override
	public List<Chatter> selectExistsChatUser(Chatter chatter) {

		//DB연결
		conn = DBConn.getConnection();

		//수행할 SQL쿼리
		String sql = "";
		sql += "SELECT chat_user, chat_no FROM chatter ";
		sql += "WHERE chat_no IN (";
		sql += "	SELECT chat_no FROM chatter WHERE chat_user = ? ) ";
		sql += "AND chat_user != ? ";

		//결과 저장 리스트
		List<Chatter> existsUser = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, chatter.getChat_user());
			ps.setInt(2, chatter.getChat_user());
			

			rs = ps.executeQuery();

			while(rs.next()) {
				
				Chatter chatters = new Chatter();

				chatters.setChat_no( rs.getInt("chat_no"));
				chatters.setChat_user( rs.getInt("chat_user"));

				existsUser.add(chatters);

			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return existsUser;
	}
	
}
