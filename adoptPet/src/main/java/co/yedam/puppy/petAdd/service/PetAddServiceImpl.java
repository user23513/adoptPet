package co.yedam.puppy.petAdd.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.yedam.puppy.comm.DataSource;
import co.yedam.puppy.vo.PetAddVO;
import co.yedam.puppy.vo.PetListVO;

public class PetAddServiceImpl implements PetAddService {
	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	@Override
	public List<PetAddVO> petAddSelectList(int currentPage,int startRow, int pageSize) {
		//입양동물 전체조회(페이징처리)
		List<PetAddVO> list = new ArrayList<>();
		PetAddVO vo;
		
		//DB 데이터를 원하는만큼씩 잘라내기
				String sql = "SELECT *\r\n"
						+ "  FROM (\r\n"
						+ "        SELECT ROW_NUMBER() OVER (ORDER BY PET_ADD_NO DESC) NUM\r\n"
						+ "             , A.*\r\n"
						+ "          FROM PET_ADD A\r\n"
						+ "         ORDER BY PET_ADD_NO DESC\r\n"
						+ "        ) \r\n"
						+ " WHERE NUM BETWEEN ? AND ?";
				
				try {
					conn = dao.getConnection();
					psmt = conn.prepareStatement(sql);
					psmt.setInt(1, startRow); //시작행-1(시작 row 인덱스 번호)
					psmt.setInt(2, pageSize*currentPage); //페이지크기(한번에 출력되는 수)
					rs = psmt.executeQuery();
					
					while(rs.next()) {
						vo = new PetAddVO();
						vo.setPetAddNo(rs.getInt("PET_ADD_NO"));
						vo.setPetAddName(rs.getString("PET_ADD_NAME"));
						vo.setPetAddAge(rs.getString("PET_ADD_AGE"));
						vo.setPetAddGender(rs.getString("PET_ADD_GENDER"));
						vo.setPetAddWeight(rs.getString("PET_ADD_WEIGHT"));
						vo.setPetAddHealth(rs.getString("PET_ADD_HEALTH"));
						vo.setPetAddAdoptState(rs.getString("PET_ADD_ADOPT_STATE"));
						vo.setPetAddType(rs.getString("PET_ADD_TYPE"));
						
						list.add(vo);
					}
					
					System.out.println("DAO:입양동물정보 저장완료!" + list.size());
					
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					close();
				}
		
		return list;
	}
	
	@Override
	public int petAddCount() {
		//DB에 있는 입양동물List 글 갯수 확인
		int r = 0;
		String sql = "select * from pet_add";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				r++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return r;
	}

	@Override
	public PetAddVO petAddSelectOne(PetAddVO vo) {
		//입양동물 단건조회
		String sql = "select * from pet_add where pet_add_no=?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getPetAddNo());
			rs = psmt.executeQuery();
			if(rs.next()) {
				vo.setPetAddName(rs.getString("PET_ADD_NAME"));
				vo.setPetAddAge(rs.getString("PET_ADD_AGE"));
				vo.setPetAddGender(rs.getString("PET_ADD_GENDER"));
				vo.setPetAddWeight(rs.getString("PET_ADD_WEIGHT"));
				vo.setPetAddHealth(rs.getString("PET_ADD_HEALTH"));
				vo.setPetAddAdoptState(rs.getString("PET_ADD_ADOPT_STATE"));
				vo.setPetAddType(rs.getString("PET_ADD_TYPE"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return vo;
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
