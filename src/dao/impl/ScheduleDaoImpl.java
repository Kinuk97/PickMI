package dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.face.ScheduleDao;
import dbutil.DBConn;
import dto.CheckList;
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

		String sql = "SELECT schedule.*,"
				+ " (SELECT count(*) FROM checklist WHERE schedule.scheduleno = checklist.scheduleno AND do_check = '1') cntChecked,"
				+ " (SELECT count(*) FROM checklist WHERE schedule.scheduleno = checklist.scheduleno) cntCheckList"
				+ " FROM schedule WHERE proj_no = ? AND EXTRACT(YEAR FROM schedule_date) = ? AND EXTRACT(MONTH FROM schedule_date) = ? ORDER BY schedule_date";

		List<Schedule> list = new ArrayList<Schedule>();

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, schedule.getProj_no());
			ps.setString(2, schedule.getCurYear());
			ps.setString(3, schedule.getCurMonth());

			rs = ps.executeQuery();

			while (rs.next()) {
				Schedule temp = new Schedule();

				temp.setScheduleno(rs.getInt("scheduleno"));
				temp.setProj_no(rs.getInt("proj_no"));
				temp.setUserno(rs.getInt("userno"));
				temp.setTitle(rs.getString("title"));
				temp.setContent(rs.getString("content"));
				temp.setPlace(rs.getString("place"));
				temp.setSchedule_date(rs.getDate("schedule_date"));
				temp.setDue_date(rs.getDate("due_date"));
				temp.setWrite_date(rs.getDate("write_date"));

				temp.setCntChecked(rs.getInt("cntChecked"));
				temp.setCntCheckList(rs.getInt("cntCheckList"));

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
				result.setTitle(rs.getString("title"));
				result.setContent(rs.getString("content"));
				result.setPlace(rs.getString("place"));
				result.setSchedule_date(rs.getDate("schedule_date"));
				result.setDue_date(rs.getDate("due_date"));
				result.setWrite_date(rs.getDate("write_date"));
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
	public void insertSchedule(Schedule schedule) {
		String sql = "INSERT INTO schedule (scheduleno, proj_no, userno, title, content, schedule_date) VALUES (?, ?, ?, ?, ?, ?)";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, schedule.getScheduleno());
			ps.setInt(2, schedule.getProj_no());
			ps.setInt(3, schedule.getUserno());
			ps.setString(4, schedule.getTitle());
			ps.setString(5, schedule.getContent());
			ps.setDate(6, new Date(schedule.getSchedule_date().getTime()));
//			ps.setString(5, schedule.getPlace());
//			ps.setDate(6, new java.sql.Date(schedule.getDue_date().getTime()));

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
	public void updateSchedule(Schedule schedule) {
		String sql = "UPDATE schedule SET title = ?, content = ?, place = ?";
		
		// 수정하는 기한이 있다면 수정
		if (schedule.getDue_date() != null) {
			sql += " , due_date = ?";
		} else {
			// 수정하는 기한이 없다면 null
			sql += " , due_date = null";
		}
		
		sql += " WHERE scheduleno = ?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, schedule.getTitle());
			ps.setString(2, schedule.getContent());
			ps.setString(3, schedule.getPlace());
			if (schedule.getDue_date() != null) {
				// 기한을 추가, 수정할 때
				ps.setDate(4, new java.sql.Date(schedule.getDue_date().getTime()));
				ps.setInt(5, schedule.getScheduleno());
			} else {
				ps.setInt(4, schedule.getScheduleno());
			}

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
	public void deleteSchedule(Schedule schedule) {
		String sql = "DELETE FROM schedule WHERE scheduleno = ?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, schedule.getScheduleno());

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
	public int selectCntScheduleDate(Schedule schedule) {
		String sql = "SELECT count(*) FROM schedule WHERE proj_no = ? AND schedule_date = ?";

		int result = 0;

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, schedule.getProj_no());
			ps.setDate(2, new java.sql.Date(schedule.getSchedule_date().getTime()));

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

	// ===================================================== 체크리스트

	@Override
	public List<CheckList> selectCheckList(Schedule selectSchedule) {
		String sql = "SELECT * FROM checklist WHERE scheduleno = ? ORDER BY scheduleno ASC";

		List<CheckList> list = new ArrayList<CheckList>();

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, selectSchedule.getScheduleno());

			rs = ps.executeQuery();

			while (rs.next()) {
				CheckList checklist = new CheckList();

				checklist.setCheckno(rs.getInt("checkno"));
				checklist.setScheduleno(rs.getInt("scheduleno"));
				checklist.setCheck_content(rs.getString("check_content"));
				checklist.setDo_check(rs.getString("do_check").charAt(0));

				list.add(checklist);
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
	public void insertCheck(CheckList checkList) {
		String sql = "INSERT INTO checkList VALUES (checklist_seq.nextval, ?, ?, '0')";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, checkList.getScheduleno());
			ps.setString(2, checkList.getCheck_content());

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
	public void updateCheckDo_check(CheckList checkList) {
		String sql = "UPDATE checkList SET do_check = ? WHERE checkno = ?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, String.valueOf(checkList.getDo_check()));
			ps.setInt(2, checkList.getCheckno());

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
	public void updateCheckContent(CheckList checkList) {
		String sql = "UPDATE checkList SET check_content = ? WHERE checkno = ?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, checkList.getCheck_content());
			ps.setInt(2, checkList.getCheckno());

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
	public void deleteCheck(CheckList checkList) {
		String sql = "DELETE FROM checklist WHERE checkno = ?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, checkList.getCheckno());

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
	public void deleteCheckList(CheckList checkList) {
		String sql = "DELETE FROM checklist WHERE scheduleno = ?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, checkList.getScheduleno());

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

	// ======================================================================================================================
}
