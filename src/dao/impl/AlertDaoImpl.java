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
import dto.ProfileBoard;
import dto.ProjectBoard;

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
	public List<Alert> invitedList(Alert alert) {
		String sql="";
		sql += "SELECT sender FROM alert WHERE  userno = ? AND checkcheck = 0";
		
		List<Alert> list = new ArrayList<Alert>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, alert.getUserno());
			
			rs = ps.executeQuery();
			while (rs.next()) {
				Alert temp = new Alert();
				
				temp.setSender(rs.getInt("sender"));
			
				
				list.add(temp);
//				System.out.println("matedaoimpl : " + list);
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
