package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.face.ManagerDao;
import dbutil.DBConn;
import dto.Manager;
import dto.ProfileBoard;

public class ManagerDaoImpl implements ManagerDao{

	// DB관련 객체
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public int selectCntMemberByMgridAndMgrpw(Manager mgr) {

		// DB연결
		conn = DBConn.getConnection();
		
		if( mgr.getMgrid()==null || mgr.getMgrpw() == null ) {
			
			// -1 = 에러
			return -1;			
		}
		
		// 쿼리작성
		String sql = "";
		sql += "SELECT COUNT(*) FROM manager";
		sql += " WHERE 1=1"; // 줄 맞추기
		sql += " AND mgrid = ?";
		sql += " AND mgrpw = ?";
		
		int cnt = -1;
		
		try {
			// DB작업
			ps=conn.prepareStatement(sql);
			ps.setString(1, mgr.getMgrid());
			ps.setString(2, mgr.getMgrpw());
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				cnt = rs.getInt(1);
				
			}
			
		} catch (SQLException e) {	
			e.printStackTrace();
		} finally {
			try {
				//DB객체 닫기
				rs.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return cnt;
		 
	}

	@Override
	public Manager selectMgrByMgr(Manager mgr) {
		
		conn = DBConn.getConnection();
		
		if ( mgr.getMgrid() == null ) {			
			return null; //?
		}
		
		//쿼리 작성
		String sql = "";
		sql += "SELECT * FROM manager";
		sql += " WHERE 1=1";
		sql += " AND mgrid = ?";
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1,  mgr.getMgrid());
			
			rs=ps.executeQuery();
			
			while(rs.next()) {
				mgr.setMgrid(rs.getString("mgrid"));
				mgr.setMgrpw(rs.getString("mgrpw"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return mgr;
	}

	@Override
	public List<ProfileBoard> pbselectAll() {
		
		conn = DBConn.getConnection(); // DB연결
		
		//수행할 SQL
		String sql="";
		sql += "SELECT";
		sql += " prof_no, userno, prof_time,";
		sql += " prof_interest, prof_job, prof_state, prof_loc, prof_career,";
		sql += " prof_content, prof_like FROM profile";
		sql += " ORDER BY prof_no DESC";
		
		// 결과 저장 리스트
		List<ProfileBoard> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ProfileBoard pb = new ProfileBoard();
				pb.setProf_no(rs.getInt("prof_no"));
				pb.setUserno(rs.getInt("userno"));
				pb.setProf_time(rs.getDate("prof_time"));
				pb.setProf_interest(rs.getString("prof_interest"));
				pb.setProf_job(rs.getString("prof_job"));
				pb.setProf_state(rs.getString("prof_state"));
				pb.setProf_loc(rs.getString("prof_loc"));
				pb.setProf_career(rs.getString("prof_career"));
				pb.setProf_content(rs.getString("prof_content"));
				pb.setProf_like(rs.getInt("prof_like"));
				
				list.add(pb);
				
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
	
		
		
		
		
		return list;
	}

}
