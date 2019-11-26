package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.face.FreeBoardDao;
import dbutil.DBConn;
import dto.FreeBoard;
import util.Paging;

public class FreeBoardDaoImpl implements FreeBoardDao {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	private FreeBoardDaoImpl() {
		conn = DBConn.getConnection();
	}

	private static class Singleton {
		private static final FreeBoardDao instance = new FreeBoardDaoImpl();
	}

	public static FreeBoardDao getInstance() {
		return Singleton.instance;
	}

	@Override
	public int getNextBoardno() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectCntAll(String search, int categoryno) {
		String sql = "SELECT count(*) FROM freeboard";
		
		// 검색어가 존재하거나 카테고리가 존재한다면
		if (search != null || categoryno != 0) {
			// where 추가
			sql += " WHERE 1 = 1";
			
			// 검색어가 존재한다면 검색조건 추가
			if (search != null) {
				sql += " AND free_title LIKE '%' || ? || '%'";
			}
			
			// 카테고리가 존재한다면 카테고리 추가
			if (categoryno != 0) {
				sql += " AND categoryno = ?";
			}
		}
		
		int total = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			if (search != null && categoryno == 0) {
				ps.setString(1, search);
			} else if (categoryno != 0 && search == null) {
				ps.setInt(1, categoryno);
			} else if (search != null && categoryno != 0) {
				ps.setString(1, search);
				ps.setInt(2, categoryno);
			}
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				total = rs.getInt(1);
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
		
		return total;
	}

	@Override
	public List<FreeBoard> selectAll(Paging paging) {
		String sql = "";
		sql += "select * from (";
		sql += "  select rownum rnum, B.* FROM(";
		sql += "   select * from freeboard";
		
		// 검색어가 존재할 때
		if (paging.getSearch() != null || paging.getCategoryno() != 0) {
			sql += " WHERE 1 = 1";
			
			if (paging.getSearch() != null) {
				sql += " AND free_title LIKE '%' || ? || '%'";
			}
			
			if (paging.getCategoryno() != 0) {
				sql += " AND categoryno = ?";
			}
		}
		
		sql += "   order by free_no desc";
		sql += "  ) B";
		sql += "  ORDER BY rnum";
		sql += " ) BOARD";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		List<FreeBoard> list = new ArrayList<FreeBoard>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			if (paging.getSearch() != null && paging.getCategoryno() == 0) {
				ps.setString(1, paging.getSearch());
				ps.setInt(2, paging.getStartNo());
				ps.setInt(3, paging.getEndNo());
			} else if (paging.getCategoryno() != 0 && paging.getSearch() == null) {
				ps.setInt(1, paging.getCategoryno());
				ps.setInt(2, paging.getStartNo());
				ps.setInt(3, paging.getEndNo());
			} else if (paging.getSearch() != null && paging.getCategoryno() != 0) {
				ps.setString(1, paging.getSearch());
				ps.setInt(2, paging.getCategoryno());
				ps.setInt(3, paging.getStartNo());
				ps.setInt(4, paging.getEndNo());
			} else {
				ps.setInt(1, paging.getStartNo());
				ps.setInt(2, paging.getEndNo());
			}
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				FreeBoard freeBoard = new FreeBoard();
				
				freeBoard.setFree_no(rs.getInt("free_no"));
				freeBoard.setCategoryno(rs.getInt("categoryno"));
				freeBoard.setUserno(rs.getInt("userno"));
				freeBoard.setFree_title(rs.getString("free_title"));
				freeBoard.setFree_content(rs.getString("free_content"));
				freeBoard.setFree_time(rs.getDate("free_time"));
				freeBoard.setViews(rs.getInt("views"));
				
				list.add(freeBoard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}try {
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
	public int insertBoard(FreeBoard freeBoard) {
		String sql = "INSERT INTO freeboard VALUES (freeboard_seq.nextval, ?, ?, ?, ?, sysdate, 0)";
		
		int result = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, freeBoard.getCategoryno());
			ps.setInt(2, freeBoard.getUserno());
			ps.setString(3, freeBoard.getFree_title());
			ps.setString(4, freeBoard.getFree_content());
			
			result = ps.executeUpdate();
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
	public int updateBoard(FreeBoard freeBoard) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBoard(FreeBoard freeBoard) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public FreeBoard boardView(FreeBoard freeBoard) {
		String sql = "SELECT * FROM freeboard WHERE free_no = ?";
		
		FreeBoard result = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, freeBoard.getFree_no());
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				result = new FreeBoard();
				
				result.setFree_no(rs.getInt("free_no"));
				result.setCategoryno(rs.getInt("categoryno"));
				result.setUserno(rs.getInt("userno"));
				result.setFree_title(rs.getString("free_title"));
				result.setFree_content(rs.getString("free_content"));
				result.setFree_time(rs.getDate("free_time"));
				result.setViews(rs.getInt("views"));
				
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
	public void countViews(FreeBoard freeBoard) {
		// TODO Auto-generated method stub

	}

}
