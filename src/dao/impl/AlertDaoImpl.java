package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.face.AlertDao;
import dbutil.DBConn;
import dto.Alert;
import dto.Mate;

public class AlertDaoImpl implements AlertDao {
	
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	private AlertDaoImpl() {
		conn = DBConn.getConnection();
	}
	
	private static class Singleton {
		private static final AlertDao instance = new AlertDaoImpl();
	}
	
	public static AlertDao getInstance() {
		return Singleton.instance;
	}
	
	@Override
	public List<Mate> invitedList(Alert alert) {
		String sql="";
		sql += "SELECT mate.*, (SELECT name FROM user_table WHERE mate.userno = user_table.userno) username FROM mate WHERE userno = ? AND mate = 3";
		
		List<Mate> list = new ArrayList<Mate>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, alert.getUserno());
			
			rs = ps.executeQuery();
			while (rs.next()) {
				Mate mate = new Mate();
				
				mate.setUserno(rs.getInt("userno"));
				mate.setProj_no(rs.getInt("proj_no"));
				mate.setMate(rs.getInt("mate"));
				mate.setUsername(rs.getString("username"));
			
				
				list.add(mate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
		
	}
	
	@Override
	public List<Alert> selectMyAlert(Alert alert) {
		String sql = "SELECT * FROM alert WHERE userno = ? ORDER BY alerttime";
		
		List<Alert> list = new ArrayList<Alert>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, alert.getUserno());
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Alert temp = new Alert();
				
				temp.setUserno(alert.getUserno());
				temp.setAlert(rs.getString("alert"));
				temp.setAlerttime(rs.getDate("alerttime"));
				temp.setSender(rs.getInt("sender"));
				temp.setCheckcheck(rs.getString("checkcheck").charAt(0));

				list.add(temp);
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
		
		return list;
	}
	
	@Override
	public int checkProfile(Alert alert) {
		String sql="";
		sql += "SELECT count(*) FROM profile where userno = ?";
		
		int cnt = 0;
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, alert.getSender());
			
			rs = ps.executeQuery();
			

			while(rs.next()) {
				cnt = rs.getInt(1);
			}
		} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if(rs!=null)rs.close();
					if(ps!=null)ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
//		System.out.println(cnt);
		return cnt;
	}
	
	@Override
	public void insertDeniedAlert(Alert alert) {
		
		String sql="";
		sql += "INSERT INTO alert (userno, alert, sender, checkcheck) VALUES (?, ?, ?, 0)";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1,  alert.getUserno());
			ps.setString(2, alert.getAlert());
			ps.setInt(3, alert.getSender());
			
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
	public void insertAlert(Alert alert) {
		String sql="";
		sql += "INSERT INTO alert (userno, alert, sender, checkcheck) VALUES (?, ?, ?, 0)";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1,  alert.getUserno());
			ps.setString(2, alert.getAlert());
			ps.setInt(3, alert.getSender());
			
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
