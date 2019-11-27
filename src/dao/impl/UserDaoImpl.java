package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.face.UserDao;
import dbutil.DBConn;
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
		}

		return checkResult;
	}

	@Override
	public User selectUserByEmail(User user) {
		conn = DBConn.getConnection();

		String sql = "";
		sql += "SELECT userno FROM user_table";
		sql += " WHERE email = ?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, user.getEmail());

			rs = ps.executeQuery();

			while (rs.next()) {
				user.setUserno(rs.getInt("userno"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

}
