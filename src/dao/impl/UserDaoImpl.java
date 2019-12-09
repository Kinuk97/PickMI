package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.face.UserDao;
import dbutil.DBConn;
import dto.Files;
import dto.User;

public class UserDaoImpl implements UserDao {

	private Connection conn = null; // DB연결 객체
	private PreparedStatement ps = null; // SQL 수행 객체
	private ResultSet rs = null; // SQL 수행 결과 객체

	@Override
	public int selectCntUserByUserid(User user) {

		conn = DBConn.getConnection();

		if (user.getEmail() == null || user.getPw() == null) {
			return -1;

		}

		int cnt = -1;

		String sql = "";
		sql += "SELECT count(*) FROM user_table";
		sql += " WHERE email = ? AND pw = ?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPw());

			rs = ps.executeQuery();

			while (rs.next()) {
				cnt = rs.getInt("count(*)");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {

				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
//		System.out.println("login 확인 : " + cnt); //
		return cnt;
	}

	@Override
	public void insert(User user) {
		conn = DBConn.getConnection();

		String sql = "";
		sql += "INSERT INTO user_table(userno, email, pw, name)";
		sql += " VALUES(userno_seq.nextval, ?, ?, ?)";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPw());
			ps.setString(3, user.getName());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {

				if (ps != null)
					ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public int selectEmail(User user) {
		conn = DBConn.getConnection();

		int checkResult = -1;

		String sql = "";
		sql += "SELECT count(*) FROM user_table";
		sql += " WHERE email = ?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getEmail());

			rs = ps.executeQuery();

			while (rs.next()) {
				checkResult = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {

				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return checkResult;
	}

	@Override
	public User selectUserByEmail(User user) {
		conn = DBConn.getConnection();

		String sql = "";
		sql += "SELECT userno, name, photo_storedname FROM user_table";
		sql += " WHERE email = ?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, user.getEmail());

			rs = ps.executeQuery();

			while (rs.next()) {
				user.setUserno(rs.getInt("userno"));
				user.setPhoto_storedname(rs.getString("photo_storedname"));
				user.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {

				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return user;
	}

	@Override
	public User selectPwByEmail(User user) {
		
		conn = DBConn.getConnection();
		
		String sql = "";
		sql += "SELECT pw FROM user_table";
		sql += " WHERE email = ? AND name = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getName());
			
			rs = ps.executeQuery();
			while(rs.next()) {

				user.setPw(rs.getString("pw"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {

				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return user;
	}

	@Override
	public void updatePw(User user) {
		conn = DBConn.getConnection();
		
		String sql = "";
		
		sql += "UPDATE user_table SET pw = ?";
		sql += " WHERE email = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, user.getPw());
			ps.setString(2, user.getEmail());
			
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			try {

				if (ps != null)
					ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	@Override
	public User insertphoto(User user) {
//		System.out.println("userDAO에서의 uploadFile 매개변수가 갖고 있는것 : " + uploadFile); -- null
//		System.out.println("userDao에서의 user 매개변수가 갖고 있는 것 : " + user);
		
		conn = DBConn.getConnection();
		
		String sql = "";
		
		sql += "UPDATE user_table SET photo_originname = ?, photo_storedname = ?";
		sql += " WHERE userno = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, user.getPhoto_originname());
			ps.setString(2, user.getPhoto_storedname());
			ps.setInt(3, user.getUserno());
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			try {

				if (ps != null)
					ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} return user;
	}

	@Override
	public void deletephoto(User user) {
//		System.out.println("userDao에서의 user 매개변수가 갖고 있는 것 : " + user);
		
		conn = DBConn.getConnection();
		
		String sql = "";
		
		sql += "UPDATE user_table SET photo_originname = null, photo_storedname = null";
		sql += " WHERE userno = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, user.getUserno());
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			try {

				if (ps != null)
					ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public User selectUserPhotonameByuserno(User user) {

		conn = DBConn.getConnection();

		String sql = "";
		sql += "SELECT photo_originname, photo_storedname FROM user_table";
		sql += " WHERE userno = ?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, user.getUserno());

			rs = ps.executeQuery();

			while (rs.next()) {
				user.setPhoto_originname(rs.getString("photo_originname"));
				user.setPhoto_storedname(rs.getString("photo_storedname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {

				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return user;

		
}


		
	
}
