package co.yedam.puppy.petAdd.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import co.yedam.puppy.comm.DataSource;
import co.yedam.puppy.vo.PetAddVO;

public class PetAddServiceImpl implements PetAddService {
	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	@Override
	public List<PetAddVO> petAddSelectList(int startRow, int pageSize) {
		//입양동물 전체조회(페이징처리)
		return null;
	}

	@Override
	public int petAddInsert(PetAddVO vo) {
		//입양동물 등록
		int r = 0;
		String sql = "INSERT INTO PET_ADD VALUES(PET_ADD_SEQ.NEXTVAL,?,?,?,?,?,?,?)";
		
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getPetAddName());
			psmt.setString(2, vo.getPetAddAge());
			psmt.setString(3, vo.getPetAddGender());
			psmt.setString(4, vo.getPetAddWeight());
			psmt.setString(5, vo.getPetAddHealth());
			psmt.setString(6, vo.getPetAddAdoptState());
			psmt.setString(7, vo.getPetAddType());
			
			r = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return r;
	}

	@Override
	public int petAddUpdate(PetAddVO vo) {
		//입양동물 수정
		int r = 0;
		String sql = "UPDATE PET_ADD "
				+ " SET PET_ADD_NAME=?, PET_ADD_AGE=?, PET_ADD_GENDER=?, "
				+ " PET_ADD_WEIGHT=?, PET_ADD_HEALTH=?, PET_ADD_ADOPT_STATE=?, PET_ADD_TYPE=? "
				+ "WHERE PET_ADD_NO=?";
		
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getPetAddName());
			psmt.setString(2, vo.getPetAddAge());
			psmt.setString(3, vo.getPetAddGender());
			psmt.setString(4, vo.getPetAddWeight());
			psmt.setString(5, vo.getPetAddHealth());
			psmt.setString(6, vo.getPetAddAdoptState());
			psmt.setString(7, vo.getPetAddType());
			psmt.setInt(8, vo.getPetAddNo());
			
			r = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return r;
	}

	@Override
	public int petAddDelete(PetAddVO vo) {
		//입양동물 삭제
		int r = 0;
		String sql = "DELETE FROM PET_ADD WHERE PET_ADD_NO=?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getPetAddNo());
			
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
