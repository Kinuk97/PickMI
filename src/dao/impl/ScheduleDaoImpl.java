package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.face.ScheduleDao;
import dbutil.DBConn;
import dto.Schedule;

public class ScheduleDaoImpl implements ScheduleDao {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	private ScheduleDaoImpl() {
		conn = DBConn.getConnection();
	}

	private static class Singleton {
		private static final ScheduleDao instance = new ScheduleDaoImpl();
	}

	public static ScheduleDao getInstance() {
		return Singleton.instance;
	}

	@Override
	public int selectScheduleno() {
		String sql = "SELECT schedule_seq.nextval FROM dual";

		int result = 0;

		try {
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				result = rs.getInt(1);
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

		return result;
	}

	@Override
	public List<Schedule> selectAll(Schedule schedule) {
		String sql = "select * from schedule WHERE proj_no = ?";

		List<Schedule> list = new ArrayList<Schedule>();

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, schedule.getProj_no());

			rs = ps.executeQuery();

			while (rs.next()) {
				Schedule temp = new Schedule();

				temp.setScheduleno(rs.getInt("scheduleno"));
				temp.setProj_no(rs.getInt("proj_no"));
				temp.setUserno(rs.getInt("userno"));
				temp.setTask(rs.getString("task"));
				temp.setContent(rs.getString("content"));
				temp.setPlace(rs.getString("place"));
				temp.setTerm_start(rs.getDate("term_start"));
				temp.setTerm_last(rs.getDate("term_last"));
				temp.setWrite_date(rs.getDate("write_date"));

				list.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public Schedule selectByScheduleno(Schedule schedule) {
		String sql = "select * from schedule WHERE scheduleno = ?";

		Schedule result = null;

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, schedule.getScheduleno());

			rs = ps.executeQuery();

			while (rs.next()) {
				result = new Schedule();

				result.setScheduleno(rs.getInt("scheduleno"));
				result.setProj_no(rs.getInt("proj_no"));
				result.setUserno(rs.getInt("userno"));
				result.setTask(rs.getString("task"));
				result.setContent(rs.getString("content"));
				result.setPlace(rs.getString("place"));
				result.setTerm_start(rs.getDate("term_start"));
				result.setTerm_last(rs.getDate("term_last"));
				result.setWrite_date(rs.getDate("write_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public void insertSchedule(Schedule schedule) {
		String sql = "INSERT INTO schedule VALUES (?, ?, ?, ?, ?, ?, ?, ?, sysdate)";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, schedule.getScheduleno());
			ps.setInt(2, schedule.getProj_no());
			ps.setInt(3, schedule.getUserno());
			ps.setString(4, schedule.getContent());
			ps.setString(5, schedule.getTask());
			ps.setString(6, schedule.getPlace());
			ps.setDate(7, new java.sql.Date(schedule.getTerm_start().getTime()));
			ps.setDate(8, new java.sql.Date(schedule.getTerm_last().getTime()));

			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (ps != null)
				ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateSchedule(Schedule schedule) {
		String sql = "UPDATE schedule SET content = ?, task = ?, place = ?, term_start = ?, term_last = ? WHERE scheduleno = ?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, schedule.getContent());
			ps.setString(2, schedule.getTask());
			ps.setString(3, schedule.getPlace());
			ps.setDate(4, new java.sql.Date(schedule.getTerm_start().getTime()));
			ps.setDate(5, new java.sql.Date(schedule.getTerm_last().getTime()));
			ps.setInt(6, schedule.getScheduleno());

			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (ps != null)
				ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteSchedule(Schedule schedule) {
		String sql = "DELETE FROM schedule WHERE scheduleno = ?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, schedule.getScheduleno());

			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (ps != null)
				ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
