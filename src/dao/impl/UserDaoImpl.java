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
				cnt = rs.getInt(1);
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

		return cnt;
	}

}
