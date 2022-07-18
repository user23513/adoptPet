package co.yedam.puppy.board.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.yedam.puppy.comm.DataSource;
import co.yedam.puppy.vo.BoardVO;
import co.yedam.puppy.vo.FilesVO;

public class BoardServiceImpl implements BoardService {
	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	@Override
	public List<BoardVO> volReviewSelectList(int startRow, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardVO volReviewSelectOne(BoardVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int volReviewUpdate(BoardVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int volReviewDelete(BoardVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int volReviewInsert(BoardVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BoardVO> volReviewAddSelectList(String key, String val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BoardVO> boardSelectList(int startRow, int pageSize) {
		// 공지 전체목록
		List<BoardVO> list = new ArrayList<BoardVO>();
		BoardVO vo;
		String sql = "SELECT * FROM BOARD where board_id=10 ORDER BY BOARD_NO DESC";

		conn = dao.getConnection();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				vo = new BoardVO();
				vo.setBoardNo(rs.getInt("board_no"));
				vo.setBoardId(rs.getInt("board_Id"));
				vo.setBoardTitle(rs.getString("board_title"));
				vo.setBoardWriter(rs.getString("board_writer"));
				vo.setBoardContent(rs.getString("board_content"));
				vo.setBoardDate(rs.getDate("board_date"));
				vo.setBoardHit(rs.getInt("board_hit"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	@Override
	public BoardVO boardSelect(BoardVO bvo, FilesVO fvo) {
		// 공지 상세보기
		String sql = "SELECT * FROM BOARD WHERE BOARD_NO = ?";

		conn = dao.getConnection();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, bvo.getBoardNo());
			rs = psmt.executeQuery();
			if (rs.next()) {
				bvo.setBoardNo(rs.getInt("board_no"));
				bvo.setBoardId(rs.getInt("board_Id"));
				bvo.setBoardTitle(rs.getString("board_title"));
				bvo.setBoardWriter(rs.getString("board_writer"));
				bvo.setBoardContent(rs.getString("board_content"));
				bvo.setBoardDate(rs.getDate("board_date"));
				bvo.setBoardHit(rs.getInt("board_hit"));
				fvo.setFilesNo(rs.getInt("files_no"));
				fvo.setFilesName(rs.getString("files_name"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return bvo;
	}

	@Override
	public int noticeInsert(BoardVO bvo, FilesVO fvo) {
		// 공지 글쓰기
		int n = 0;
		String sql = "INSERT ALL INTO BOARD VALUES(BOARD_SEQ.NEXTVAL,?,?,?,?,SYSDATE,0)\r\n"
				+ "            INTO FILES VALUES(?,?,?,?,?)\r\n" + "SELECT * FROM DUAL";

		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
//			psmt.setInt(1, bvo.getBoardNo());
			psmt.setInt(1, bvo.getBoardId());
			psmt.setString(2, bvo.getBoardTitle());
			psmt.setString(3, bvo.getBoardWriter());
			psmt.setString(4, bvo.getBoardContent());
			psmt.setInt(5, fvo.getFilesNo());
			psmt.setInt(6, bvo.getBoardId());
			psmt.setString(7, fvo.getFilesName());
			psmt.setString(8, fvo.getFilesPath());
			psmt.setString(9, fvo.getFilesType());

			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return n;
	}

	@Override
	public int noticeUpdate(BoardVO bvo, FilesVO fvo) {
		// 공지 수정
		int n = 0;
		String sqlB = "UPDATE BOARD SET  BOARD_ID=?, BOARD_TITLE=?, BOARD_CONTENT=? WHERE BOARD_NO=?";
		String sqlF = "UPDATE FILES SET  FILES_NAME=?, FILES_PATH=?, FILES_TYPE=? WHERE FILES_NO=?";

		try {
			conn = dao.getConnection();
			conn.setAutoCommit(false);

			psmt = conn.prepareStatement(sqlB);
			psmt.setInt(1, bvo.getBoardId());
			psmt.setString(2, bvo.getBoardTitle());
			psmt.setString(3, bvo.getBoardContent());
			psmt.setInt(4, bvo.getBoardNo());

			psmt = conn.prepareStatement(sqlF);
			psmt.setString(1, fvo.getFilesName());
			psmt.setString(2, fvo.getFilesPath());
			psmt.setInt(3, fvo.getBoardNo());
			psmt.executeUpdate();

			conn.commit();
			n = 1;
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException ee) {
				ee.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			close();
		}
		return n;
	}

	@Override
	public int noticeDelete(BoardVO bvo, FilesVO fvo) {
		// 공지 삭제
		int n = 0;
		String sqlB = "DELETE FROM BOARD WHERE BOARD_NO=?";
		String sqlF = "DELETE FROM FILES WHERE FILES_NO=?";
		conn = dao.getConnection();
		try {
			psmt = conn.prepareStatement(sqlB);
			psmt.setInt(1, bvo.getBoardNo());

			psmt = conn.prepareStatement(sqlF);
			psmt.setInt(1, fvo.getFilesNo());

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	@Override
	public List<BoardVO> noticeSerarchList(String key, String val) {
		// 공지 검색
		List<BoardVO> list = new ArrayList<BoardVO>();
		BoardVO vo;
		String sql = "select * from where" + key + "like'%" + val + "%'";

		conn = dao.getConnection();
		try {
			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery();
			while (rs.next()) {
				vo = new BoardVO();
				vo.setBoardNo(rs.getInt("board_no"));
				vo.setBoardId(rs.getInt("board_id"));
				vo.setBoardTitle(rs.getString("board_Title"));
				vo.setBoardWriter(rs.getString("board_Writer"));
				vo.setBoardContent(rs.getString("board_content"));
				vo.setBoardDate(rs.getDate("board_date"));
				vo.setBoardHit(rs.getInt("board_hit"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	@Override
	public List<BoardVO> adoptReviewSelectList(int startRow, int pageSize) {
		// 후기게시판 목록
		List<BoardVO> list = new ArrayList<BoardVO>();
		BoardVO vo;
		String sql = "SELECT * FROM BOARD where board_id=20 ORDER BY BOARD_NO DESC";

		conn = dao.getConnection();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				vo = new BoardVO();
				vo.setBoardNo(rs.getInt("board_no"));
				vo.setBoardId(rs.getInt("board_Id"));
				vo.setBoardTitle(rs.getString("board_title"));
				vo.setBoardWriter(rs.getString("board_writer"));
				vo.setBoardContent(rs.getString("board_content"));
				vo.setBoardDate(rs.getDate("board_date"));
				vo.setBoardHit(rs.getInt("board_hit"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;

	}

	@Override
	public BoardVO adoptReviewSelect(BoardVO bvo,FilesVO fvo) {
		//후기 상세보기
String sql = "SELECT * FROM BOARD WHERE BOARD_NO = ?";
		
		conn = dao.getConnection();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, bvo.getBoardNo());
			rs = psmt.executeQuery();
			if(rs.next()) {
				bvo.setBoardNo(rs.getInt("board_no"));
				bvo.setBoardId(rs.getInt("board_Id"));
				bvo.setBoardTitle(rs.getString("board_title"));
				bvo.setBoardWriter(rs.getString("board_writer"));
				bvo.setBoardContent(rs.getString("board_content"));
				bvo.setBoardDate(rs.getDate("board_date"));
				bvo.setBoardHit(rs.getInt("board_hit"));
				fvo.setFilesNo(rs.getInt("files_no"));
				fvo.setFilesName(rs.getString("files_name"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return bvo;
	}

	@Override
	public int adoptReviewInsert(BoardVO bvo,FilesVO fvo) {
		// 후기 글쓰기
		int n = 0;
		String sql = "INSERT ALL INTO BOARD VALUES(BOARD_SEQ.NEXTVAL,?,?,?,?,SYSDATE,0)\r\n"
				+ "            INTO FILES VALUES(?,?,?,?,?)\r\n"
				+ "SELECT * FROM DUAL";
		
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
//			psmt.setInt(1, bvo.getBoardNo());
			psmt.setInt(1, bvo.getBoardId());
			psmt.setString(2, bvo.getBoardTitle());
			psmt.setString(3, bvo.getBoardWriter());
			psmt.setString(4, bvo.getBoardContent());
			psmt.setInt(5, fvo.getFilesNo());
			psmt.setInt(6, bvo.getBoardId());
			psmt.setString(7, fvo.getFilesName());
			psmt.setString(8, fvo.getFilesPath());
			psmt.setString(9, fvo.getFilesType());
			
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
	}

	@Override
	public int adoptReviewUpdate(BoardVO bvo,FilesVO fvo) {
		// 후기 수정
		int n = 0;
		String sqlB = "UPDATE BOARD SET  BOARD_ID=?, BOARD_TITLE=?, BOARD_CONTENT=? WHERE BOARD_NO=?";
		String sqlF ="UPDATE FILES SET  FILES_NAME=?, FILES_PATH=?, FILES_TYPE=? WHERE FILES_NO=?";
		
		try {
			conn = dao.getConnection();
			conn.setAutoCommit(false);
			
			psmt = conn.prepareStatement(sqlB);
			psmt.setInt(1, bvo.getBoardId());
			psmt.setString(2, bvo.getBoardTitle());
			psmt.setString(3, bvo.getBoardContent());
			psmt.setInt(4, bvo.getBoardNo());
			
			psmt = conn.prepareStatement(sqlF);
			psmt.setString(1, fvo.getFilesName());
			psmt.setString(2, fvo.getFilesPath());
			psmt.setInt(3, fvo.getBoardNo());
			psmt.executeUpdate();
			
			conn.commit();
			n = 1;
		} catch (SQLException e) {
			try {
				conn.rollback();
			}catch(SQLException ee) {
				ee.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			close();
		}
		return n;
	}

	@Override
	public int adoptReviewDelete(BoardVO bvo,FilesVO fvo) {
		// 후기 삭제
		int n = 0;
		String sqlB = "DELETE FROM BOARD WHERE BOARD_NO=?";
		String sqlF ="DELETE FROM FILES WHERE FILES_NO=?";
		conn = dao.getConnection();
		try {
			psmt = conn.prepareStatement(sqlB);
			psmt.setInt(1, bvo.getBoardNo());
			
			psmt = conn.prepareStatement(sqlF);
			psmt.setInt(1, fvo.getFilesNo());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
	}

	@Override
	public List<BoardVO> adoptReviewSearchList(String key, String val) {
		// 후기 검색
		List<BoardVO> list = new ArrayList<BoardVO>();
		BoardVO vo;
		String sql = "select * from where"+key+"like'%" + val +"%'";
		
		conn = dao.getConnection();
		try {
			psmt = conn.prepareStatement(sql);
			
			rs = psmt.executeQuery();
			while(rs.next()) {
				vo = new BoardVO();
				vo.setBoardNo(rs.getInt("board_no"));
				vo.setBoardId(rs.getInt("board_id"));
				vo.setBoardTitle(rs.getString("board_Title"));
				vo.setBoardWriter(rs.getString("board_Writer"));
				vo.setBoardContent(rs.getString("board_content"));
				vo.setBoardDate(rs.getDate("board_date"));
				vo.setBoardHit(rs.getInt("board_hit"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}

	private void close() {
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}