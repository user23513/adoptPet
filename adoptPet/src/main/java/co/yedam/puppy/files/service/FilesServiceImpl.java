package co.yedam.puppy.files.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import co.yedam.puppy.comm.DataSource;
import co.yedam.puppy.vo.FilesVO;

public class FilesServiceImpl implements FilesService {
	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	@Override
	public int filesInsert(FilesVO vo) {
		//첨부파일 등록
		int r = 0;
		String sql = "INSERT INTO FILES VALUES(FILES_SEQ.NEXTVAL,?,?,?,?,?,?)";
		
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getBoardId());
			psmt.setString(2, vo.getFilesName());
			psmt.setString(3, vo.getFilesPath());
			psmt.setString(4, vo.getFilesType());
			psmt.setInt(5, vo.getBoardNo());
			psmt.setInt(6, vo.getPetListNo());
			
			r = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return r;
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
