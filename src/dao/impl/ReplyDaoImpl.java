package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.face.ReplyDao;
import dbutil.DBConn;
import dto.Reply;
import util.Paging;

public class ReplyDaoImpl implements ReplyDao {

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	private ReplyDaoImpl() {
		conn = DBConn.getConnection();
	}

	private static class Singleton {
		private static final ReplyDao instance = new ReplyDaoImpl();
	}

	public static ReplyDao getInstance() {
		return Singleton.instance;
	}

	@Override
	public int selectCntAll(Reply reply) {
		String sql = "SELECT count(*) cnt FROM reply WHERE postno = ? AND boardno = ?";

		int totalCount = 0;

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, reply.getPostno());
			ps.setInt(2, reply.getBoardno());

			rs = ps.executeQuery();

			if (rs.next()) {
				totalCount = rs.getInt("cnt");
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

		return totalCount;
	}

	@Override
	public List<Reply> selectAll(Paging paging, Reply reply) {
		String sql = "";
		sql += "select * from (";
		sql += "  select rownum rnum, B.* FROM(";
		sql += "   select replyno, boardno, userno, reply, replytime, "
				+ " (SELECT name FROM user_table WHERE reply.userno = userno) username" + " from reply";

		sql += " WHERE postno = ? AND boardno = ?";

		sql += "   order by replyno desc";
		sql += "  ) B";
		sql += "  ORDER BY rnum";
		sql += " ) BOARD";
		sql += " WHERE rnum BETWEEN ? AND ?";

		List<Reply> list = new ArrayList<Reply>();

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, reply.getPostno());
			ps.setInt(2, reply.getBoardno());
			ps.setInt(3, paging.getStartNo());
			ps.setInt(4, paging.getEndNo());

			rs = ps.executeQuery();

			while (rs.next()) {
				Reply rstReply = new Reply();

				rstReply.setReplyno(rs.getInt("replyno"));
				rstReply.setUserno(rs.getInt("userno"));
				rstReply.setReply(rs.getString("reply"));
				rstReply.setReplytime(rs.getDate("replytime"));
				rstReply.setUsername(rs.getString("username"));

				list.add(rstReply);
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
	public void insertReply(Reply reply) {
		String sql = "INSERT INTO reply VALUES (reply_seq.nextval, ?, ?, ?, sysdate, ?)";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, reply.getUserno());
			ps.setInt(2, reply.getBoardno());
			ps.setString(3, reply.getReply());
			ps.setInt(4, reply.getPostno());

			ps.executeQuery();
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
	public void updateReply(Reply reply) {
		String sql = "UPDATE reply SET reply = ? WHERE postno = ? AND boardno = ? AND replyno = ?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, reply.getReply());
			ps.setInt(2, reply.getPostno());
			ps.setInt(3, reply.getBoardno());
			ps.setInt(4, reply.getReplyno());

			ps.executeQuery();
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
	public void deleteReply(Reply reply) {
		String sql = "DELETE FROM reply WHERE postno = ? AND boardno = ? AND replyno = ?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, reply.getPostno());
			ps.setInt(2, reply.getBoardno());
			ps.setInt(3, reply.getReplyno());

			ps.executeQuery();
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

}
