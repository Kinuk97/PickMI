package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.face.FileDao;
import dbutil.DBConn;
import dto.Files;

public class FileDaoImpl implements FileDao {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	private FileDaoImpl() {
		conn = DBConn.getConnection();
	}

	private static class Singleton {
		private static final FileDao instance = new FileDaoImpl();
	}

	public static FileDao getInstance() {
		return Singleton.instance;
	}

	@Override
	public void insertFile(Files uploadFile) {
		String sql = "INSERT INTO files VALUES (files_seq.nextval, ?, ?, ?, ?, ?)";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, uploadFile.getPostno());
			ps.setInt(2, uploadFile.getBoardno());
			ps.setString(3, uploadFile.getOriginName());
			ps.setString(4, uploadFile.getStoredName());
			ps.setString(5, uploadFile.getFileSize());

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
	public Files selectFile(Files file) {
		String sql = "SELECT * FROM files WHERE postno = ? AND boardno = ? ORDER BY fileno";

		Files selectFile = null;

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, file.getPostno());
			ps.setInt(2, file.getBoardno());

			rs = ps.executeQuery();

			while (rs.next()) {
				selectFile = new Files();

				selectFile.setFileno(rs.getInt("fileno"));
				selectFile.setPostno(rs.getInt("postno"));
				selectFile.setBoardno(rs.getInt("boardno"));
				selectFile.setOriginName(rs.getString("originname"));
				selectFile.setStoredName(rs.getString("storedname"));
				selectFile.setFileSize(rs.getString("filesize"));
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

		return selectFile;
	}

	@Override
	public Files selectFileByFileno(Files file) {
		String sql = "SELECT * FROM files WHERE fileno = ?";

		Files selectFile = null;

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, file.getFileno());

			rs = ps.executeQuery();

			while (rs.next()) {
				selectFile = new Files();

				selectFile.setFileno(rs.getInt("fileno"));
				selectFile.setPostno(rs.getInt("postno"));
				selectFile.setBoardno(rs.getInt("boardno"));
				selectFile.setOriginName(rs.getString("originname"));
				selectFile.setStoredName(rs.getString("storedname"));
				selectFile.setFileSize(rs.getString("filesize"));
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

		return selectFile;
	}

	@Override
	public void deleteFile(Files file) {
		String sql = "DELETE FROM files WHERE postno = ? AND boardno = ?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, file.getPostno());
			ps.setInt(2, file.getBoardno());

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

}
