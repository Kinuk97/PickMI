package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.face.CheckListDao;
import dbutil.DBConn;
import dto.CheckList;
import dto.Schedule;

public class CheckListDaoImpl implements CheckListDao {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	private CheckListDaoImpl() {
		conn = DBConn.getConnection();
	}

	private static class Singleton {
		private static final CheckListDao instance = new CheckListDaoImpl();
	}

	public static CheckListDao getInstance() {
		return Singleton.instance;
	}

	@Override
	public int selectCheckno() {
		String sql = "SELECT checklist_seq.nextval FROM dual";

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
	public List<CheckList> selectByScheduleno(Schedule schedule) {
		String sql = "select * from checkList WHERE scheduleno = ?";

		List<CheckList> list = new ArrayList<CheckList>();

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, schedule.getProj_no());

			rs = ps.executeQuery();

			while (rs.next()) {
				CheckList temp = new CheckList();

				temp.setCheckno(rs.getInt("checkno"));
				temp.setScheduleno(rs.getInt("scheduleno"));
				temp.setCheck_content(rs.getString("check_content"));
				temp.setDo_check(rs.getString("do_check").charAt(0));

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
	public void insertCheckList(CheckList checkList) {
		String sql = "INSERT INTO checklist VALUES (checklist_seq.nextval, ?, ?, '0')";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, checkList.getScheduleno());
			ps.setString(2, checkList.getCheck_content());

			ps.executeUpdate();
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
	public void updateCheckList(CheckList checkList) {
		String sql = "UPDATE checkList SET checkcontent = ? AND do_check = ? WHERE checkno = ?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, checkList.getCheck_content());
			ps.setString(2, String.valueOf(checkList.getDo_check()));
			ps.setInt(3, checkList.getCheckno());

			ps.executeUpdate();
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
	public void deleteCheckList(CheckList checkList) {
		String sql = "DELETE FROM checklist WHERE checkno = ?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, checkList.getCheckno());

			ps.executeUpdate();
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
