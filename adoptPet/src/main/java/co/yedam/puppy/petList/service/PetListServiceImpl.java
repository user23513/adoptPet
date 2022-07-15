package co.yedam.puppy.petList.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.yedam.puppy.comm.DataSource;
import co.yedam.puppy.vo.FilesVO;
import co.yedam.puppy.vo.PetListVO;

public class PetListServiceImpl implements PetListService {
	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	@Override
	public List<PetListVO> petListSelectList(int startRow, int pageSize) {
		//입양동물소개게시판 전체리스트조회
		List<PetListVO> list = new ArrayList<>();
		PetListVO vo;
		
		//DB 데이터를 원하는만큼씩 잘라내기
		String sql = "SELECT *\r\n"
				+ "  FROM (\r\n"
				+ "        SELECT ROW_NUMBER() OVER (ORDER BY PET_LIST_NO DESC) NUM\r\n"
				+ "             , A.*\r\n"
				+ "          FROM PET_LIST A\r\n"
				+ "         ORDER BY PET_LIST_NO DESC\r\n"
				+ "        ) \r\n"
				+ " WHERE NUM BETWEEN ? AND ?";
		
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, startRow-1); //시작행-1(시작 row 인덱스 번호)
			psmt.setInt(2, pageSize); //페이지크기(한번에 출력되는 수)
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				vo = new PetListVO();
				vo.setPetListNo(rs.getInt("PET_LIST_NO"));
				vo.setBoardId(rs.getInt("BOARD_ID"));
				vo.setPetListTitle(rs.getString("PET_LIST_TITLE"));
				vo.setPetListContent(rs.getString("PET_LIST_CONTENT"));
				vo.setPetListWriter(rs.getString("PET_LIST_WRITER"));
				vo.setPetListState(rs.getString("PET_LIST_STATE"));
				vo.setPetListType(rs.getString("PET_LIST_TYPE"));
				
				list.add(vo);
			}
			
			System.out.println("DAO:글정보 저장완료!" + list.size());
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return list;
	}

	@Override
	public PetListVO petListSelectOne(PetListVO vo) {
		//입양동물소개게시판 단건조회
		String sql = "select * from pet_list where PET_LIST_NO=?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getPetListNo());
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				vo.setPetListNo(rs.getInt("PET_LIST_NO"));
				vo.setBoardId(rs.getInt("BOARD_ID"));
				vo.setPetListTitle(rs.getString("PET_LIST_TITLE"));
				vo.setPetListContent(rs.getString("PET_LIST_CONTENT"));
				vo.setPetListWriter(rs.getString("PET_LIST_WRITER"));
				vo.setPetListState(rs.getString("PET_LIST_STATE"));
				vo.setPetListType(rs.getString("PET_LIST_TYPE"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return vo;
	}
	
	@Override
	public int petListCount() {
		//입양동물소개게시판에 있는 글 개수 확인
		int r = 0;
		String sql = "select * from pet_list";
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
	public int petListInsert(PetListVO listVO, FilesVO fileVO) {
		//입양동물소개게시판 등록(파일까지)
		int r = 0;
		String sql = "insert all "
						+ " into add_list values (pet_list_seq,?,?,?,?,?,?) "
						+ " into files values (files_seq,?,?,?,?,?,?)"
					+ "select * from dual;";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, listVO.getBoardId());
			psmt.setString(2, listVO.getPetListTitle());
			psmt.setString(3, listVO.getPetListContent());
			psmt.setString(4, listVO.getPetListWriter());
			psmt.setString(5, listVO.getPetListState());
			psmt.setString(6, listVO.getPetListType());
			psmt.setInt(7, fileVO.getBoardId());
			psmt.setString(8, fileVO.getFilesName());
			psmt.setString(9, fileVO.getFilesPath());
			psmt.setString(10, fileVO.getFilesType());
			psmt.setInt(11, fileVO.getBoardNo());
			psmt.setInt(12, fileVO.getPetListNo());
			
			r = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return r;
	}

	@Override
	public int petListUpdate(PetListVO listVO, FilesVO fileVO) {
		//입양동물소개게시판 수정
		int r = 0;
		String sqlList = "UPDATE PET_LIST "
				+ "SET BOARD_ID=?, PET_LIST_TITLE=?, PET_LIST_CONTENT=? "
				+ " PET_LIST_WRITER=?, PET_LIST_STATE=?, PET_LIST_TYPE=? "
				+ " WHERE PET_LIST_NO=?";
		String sqlFiles="UPDATE FILES "
				+ "SET FILES_NAME=?, FILES_PATH=?, FILES_TYPE=? "
				+ " WHERE FILES_NO=?";
		
		try {
			conn = dao.getConnection();
			conn.setAutoCommit(false);
			
			psmt = conn.prepareStatement(sqlList);
			psmt.setInt(1, listVO.getBoardId());
			psmt.setString(2, listVO.getPetListTitle());
			psmt.setString(3, listVO.getPetListContent());
			psmt.setString(4, listVO.getPetListWriter());
			psmt.setString(5, listVO.getPetListState());
			psmt.setString(6, listVO.getPetListType());
			psmt.setInt(7, listVO.getPetListNo());
			psmt.executeUpdate();
			
			psmt = conn.prepareStatement(sqlFiles);
			psmt.setString(1, fileVO.getFilesName());
			psmt.setString(2, fileVO.getFilesPath());
			psmt.setString(3, fileVO.getFilesType());
			psmt.setInt(4, fileVO.getFilesNo());
			psmt.executeUpdate();
			
			conn.commit();
			r = 1;
			
		} catch (SQLException se) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			se.printStackTrace();
			
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			close();
		}
		return r;
	}

	@Override
	public int petListDelete(PetListVO listVO, FilesVO fileVO) {
		//입양동물소개게시판 삭제
		
		return 0;
	}

	@Override
	public List<PetListVO> petListSearchList(String key, String val) {
		//입양동물소개게시판 검색조회
		
		return null;
	}

	@Override
	public List<PetListVO> petListSort(String petType) {
		//입양동물소개게시판 동물유형으로 정렬해서 보여주기
		
		return null;
	}
	
	private void close() {
		try {
			if(rs != null) rs.close();
			if(psmt != null) rs.close();
			if(conn != null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
