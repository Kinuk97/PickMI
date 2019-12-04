package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.face.MyPageDao;
import dbutil.DBConn;
import dto.CompBoard;
import dto.FreeBoard;
import dto.ProfileBoard;
import dto.ProjectBoard;
import dto.User;
import util.Paging;

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

	@Override
	public User selectUserbyUserno(HttpServletRequest req) {
		String sql = "";
		sql +="SELECT email, userno, name FROM user_table WHERE userno=?";
		
		User user = new User();
		
		try {
			ps=conn.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public int selectCntAll(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ProfileBoard> selectPf(Paging paging, HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProjectBoard> selectPj(Paging paging, HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CompBoard> selectComp(Paging paging, HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FreeBoard> selectFree(Paging paging, HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}
// ----- 비밀번호 수정

	@Override
	public int selectCntUserByupw(User pwparam) {
		conn = DBConn.getConnection();

		if ((Integer)pwparam.getUserno() == null || pwparam.getPw() == null) {
			return -1;

		}

		int cnt = -1;

		String sql = "";
		sql += "SELECT count(*) FROM user_table";
		sql += " WHERE userno = ? AND pw = ?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, pwparam.getUserno());
			ps.setString(2, pwparam.getPw());

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
		System.out.println("비밀번호 일치 여부 확인 : " + cnt); //
		return cnt;
	}
}
