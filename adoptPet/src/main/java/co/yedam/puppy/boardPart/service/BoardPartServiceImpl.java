package co.yedam.puppy.boardPart.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import co.yedam.puppy.comm.DataSource;
import co.yedam.puppy.vo.BoardPartVO;

public class BoardPartServiceImpl implements BoardPartService {
	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	@Override
	public List<BoardPartVO> boardPartList() {
		//게시판유형 리스트 불러오기
		List<BoardPartVO> list = new ArrayList<>();
		BoardPartVO vo;
		String sql = "select * from board_part";
		
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				vo = new BoardPartVO();
				vo.setBoardId(rs.getInt("BOARD_ID"));
				vo.setBoardName(rs.getString("BOARD_NAME"));
				
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return list;
	}
	
	private void close() {
		try {
			if(rs != null) rs.close();
			if(psmt != null) psmt.close();
			if(conn != null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
