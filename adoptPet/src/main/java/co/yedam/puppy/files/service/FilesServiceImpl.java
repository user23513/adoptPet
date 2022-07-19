package co.yedam.puppy.files.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import co.yedam.puppy.comm.DataSource;
import co.yedam.puppy.vo.FilesVO;
import co.yedam.puppy.vo.PetListVO;

public class FilesServiceImpl implements FilesService {
	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	@Override
	public int filesInsert(FilesVO vo) {
		//첨부파일 등록
		int r = 0;
		String sql = "INSERT INTO FILES VALUES(FILES_SEQ.NEXTVAL,?,?,?,?,'',?)";
		
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getBoardId());
			psmt.setString(2, vo.getFilesName());
			psmt.setString(3, vo.getFilesPath());
			psmt.setString(4, vo.getFilesType());
			psmt.setInt(5, vo.getPetListNo());
			
			r = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return r;
	}
	
	@Override
	public List<String> filesSelect(PetListVO vo) {
		//파일조회
		String savePath = "C:\\Temp\\";
		List<String> list = new ArrayList<String>();
		String sql = "SELECT F.FILES_PATH\r\n"
				+ "FROM PET_LIST L, FILES f\r\n"
				+ "WHERE L.PET_LIST_NO = F.PET_LIST_NO\r\n"
				+ "AND L.PET_LIST_NO=?\r\n"
				+ "ORDER BY F.FILES_NO";
		
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getPetListNo());
			rs = psmt.executeQuery();
			while(rs.next()) {
				list.add("fileup/"+rs.getString("FILES_PATH").substring(savePath.length()));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	
	@Override
	public List<FilesVO> fileNoSelect(int petListNo) {
		//선택한 게시글에 파일넘버 가지고오기
		List<FilesVO> list = new ArrayList<FilesVO>();
		FilesVO vo;
		
		String sql = "SELECT F.FILES_NO "
				+ "FROM PET_LIST L, FILES F "
				+ "WHERE L.PET_LIST_NO = F.PET_LIST_NO "
				+ "AND L.PET_LIST_NO=? "
				+ "ORDER BY F.FILES_NO";
		
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, petListNo);
			rs = psmt.executeQuery();
			while(rs.next()) {
				vo = new FilesVO();
				vo.setFilesNo(rs.getInt(1));
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
	public int filesDelete(PetListVO vo) {
		// 파일삭제
		int r = 0;
		String sql = "DELETE FROM FILES WHERE PET_LIST_NO=? AND BOARD_ID=?";
		
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getPetListNo());
			psmt.setInt(2, vo.getBoardId());
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
