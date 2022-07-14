package co.yedam.puppy.petList.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		return null;
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
	public int petListInsert(PetListVO listVO, FilesVO fileVO) {
		//입양동물소개게시판 등록(파일까지)
		int l = 0;
		int f = 0;
		int allInsert = 0;
		String sql = "insert all "
						+ " into add_list values (pet_list_seq,?,?,?,?,?,?) "
						+ " into files values (files_seq,?,?,?,?,?,?)"
					+ "select * from dual;";
		try {
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return allInsert;
	}

	@Override
	public int petListUpdate(PetListVO vo) {
		//입양동물소개게시판 수정
		
		return 0;
	}

	@Override
	public int petListDelete(PetListVO vo) {
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