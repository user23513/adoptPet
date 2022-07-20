package co.yedam.puppy.petList.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import co.yedam.puppy.comm.DataSource;
import co.yedam.puppy.vo.FilesVO;
import co.yedam.puppy.vo.PetListVO;

public class PetListServiceImpl implements PetListService {
	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	@Override
	public List<PetListVO> petListSelectList(int currentPage, int startRow, int pageSize) {
		//입양동물소개게시판 전체리스트조회
		List<PetListVO> list = new CopyOnWriteArrayList<>();
		PetListVO vo;
		
		//DB 데이터를 원하는만큼씩 잘라내기
		String sql = "SELECT *\r\n"
				+ "  FROM (\r\n"
				+ "        SELECT ROW_NUMBER() OVER (ORDER BY PET_LIST_NO DESC) NUM\r\n"
				+ "             , A.*\r\n"
				+ "          FROM PET_LIST A\r\n"
				+ "         ORDER BY PET_LIST_NO DESC\r\n"
				+ "        ) \r\n"
				+ " WHERE NUM BETWEEN ? AND ? ";
		
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, startRow); //시작행-1(시작 row 인덱스 번호)
			psmt.setInt(2, pageSize*currentPage); //페이지크기(한번에 출력되는 수)
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
				vo.setPetAddNo(rs.getInt("PET_ADD_NO"));
				
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
	public List<PetListVO> petListFiles(List<PetListVO> list) {
		//담아온 list_no를  뽑아서 파일을 찾아서 다시 vo객체에 담기
		//list가 pageSize만큼 뽑아온 게시판데이터
		String savePath = "C:\\Temp\\";
		String sql = "SELECT F.FILES_PATH\r\n"
				+ "FROM PET_LIST L, FILES F\r\n"
				+ "WHERE L.PET_LIST_NO = F.PET_LIST_NO\r\n"
				+ "AND F.PET_LIST_NO=?"
				+ " ORDER BY FILES_NO";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			
			for(PetListVO vo: list) {
				int petListNo = vo.getPetListNo();
				psmt.setInt(1, petListNo);
				rs = psmt.executeQuery();
				
				if(rs.next()) {
					vo.setFilesPath1(rs.getString("files_path").substring(savePath.length()));
					
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return list;
	}
	
	@Override
	public PetListVO petListSelectOne(PetListVO vo) {
		//입양동물소개게시판 단건조회
		String sql = "SELECT * FROM PET_LIST WHERE PET_LIST_NO=?";
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
					vo.setPetAddNo(rs.getInt("PET_ADD_NO"));
				
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
		String sql = "SELECT * FROM PET_LIST";
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
	public int petListInsert(PetListVO listVO) {
		//입양동물소개게시판 등록(파일까지) 파일이 없을경우에는 파일테이블에 입력안시켜야함
		int r = 0;
		int petListNo = 0;
		
		try {
			//시퀀스 => 회원번호
			String sql = "SELECT PET_LIST_SEQ.NEXTVAL FROM DUAL";
			int nextSeq = 0;
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			if(rs.next()) {
				nextSeq = rs.getInt(1); // 컬럼의 순서상으로 1
			}
			
			sql = "INSERT INTO PET_LIST"
					+ " VALUES(?,?,?,?,?,?,?,?)";
			psmt = conn.prepareStatement(sql);
				psmt.setInt(1, nextSeq);
				psmt.setInt(2, listVO.getBoardId());
				psmt.setString(3, listVO.getPetListTitle());
				psmt.setString(4, listVO.getPetListContent());
				psmt.setString(5, listVO.getPetListWriter());
				psmt.setString(6, listVO.getPetListState());
				psmt.setString(7, listVO.getPetListType());
				psmt.setInt(8, listVO.getPetAddNo());
				
				r = psmt.executeUpdate();
				if(r>0) {
					petListNo = nextSeq;
				}
				
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return petListNo;
	}

	@Override
	public int petListUpdate(PetListVO vo) {
		//입양동물소개게시판 수정
		int r = 0;
		String sqlList = "UPDATE PET_LIST "
				+ "SET BOARD_ID=?, PET_LIST_TITLE=?, PET_LIST_CONTENT=?, "
				+ " PET_LIST_WRITER=?, PET_LIST_STATE=?, PET_LIST_TYPE=? "
				+ " WHERE PET_LIST_NO=?";
		
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sqlList);
			psmt.setInt(1, vo.getBoardId());
			psmt.setString(2, vo.getPetListTitle());
			psmt.setString(3, vo.getPetListContent());
			psmt.setString(4, vo.getPetListWriter());
			psmt.setString(5, vo.getPetListState());
			psmt.setString(6, vo.getPetListType());
			psmt.setInt(7, vo.getPetListNo());
			psmt.executeUpdate();
			
			r = 1;
			
		} catch (SQLException se) {
				se.printStackTrace();
			
		} finally {
			close();
		}
		return r;
	}

	@Override
	public int petListDelete(int petListNo) {
		//입양동물소개게시판 삭제
		int r = 0;
		String sql = "DELETE FROM PET_LIST WHERE PET_LIST_NO=?";
		
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, petListNo);
			psmt.executeUpdate();
			
			r = 1;
			
		} catch (SQLException se) {
				se.printStackTrace();
			
		} finally {
			close();
		}
		return r;
	}

	@Override
	public List<PetListVO> petListSearchList(String key, String val) {
		//입양동물소개게시판 검색조회
		List<PetListVO> list = new ArrayList<PetListVO>();
		PetListVO vo;
		String sql = "SELECT * FROM PET_LIST WHERE " + key + " LIKE '%"+val+"%'";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
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
				vo.setPetAddNo(rs.getInt("PET_ADD_NO"));
				list.add(vo);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}

	@Override
	public List<PetListVO> petListSort(String petType) {
		//입양동물소개게시판 동물유형으로 정렬해서 보여주기
		
		return null;
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
