package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.face.MyPageDao;
import dbutil.DBConn;
import dto.User;

public class MyPageDaoImpl implements MyPageDao{

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	private MyPageDaoImpl() {
		conn = DBConn.getConnection();
	}
	
	private static class Singleton{
		private static final MyPageDao instance = new MyPageDaoImpl();
	}
	
	public static MyPageDao getInstance() {
		return Singleton.instance;
	}

// ----- User Info -----

	@Override
	public User selectOne(String email) {

		String sql = "select * from user_table where email = ?";
		
		User user = null;

		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, email);
			
			
			rs=ps.executeQuery();
			
			if(rs.next()) {
				user = new User();
				user.setUserno(rs.getInt("userno"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPw(rs.getString("pw"));
				user.setPhoto_originname(rs.getString("photo_originname"));
				user.setPhoto_storedname(rs.getString("photo_storedname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null)ps.close();
				if(rs!=null)rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return user;
	}

	@Override
	public void updateUser(User user) {
		
		String sql = "update user set pw = ?, name = ? email = ? where email = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getPw());
			ps.setString(2, user.getName());
			ps.setString(3, user.getEmail());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null)ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
