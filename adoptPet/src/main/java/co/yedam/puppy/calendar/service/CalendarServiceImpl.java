package co.yedam.puppy.calendar.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.yedam.puppy.comm.DataSource;
import co.yedam.puppy.vo.CalendarVO;

public class CalendarServiceImpl implements CalendarService {
	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	
	@Override
	public List<CalendarVO> calendarSelectList() {
		// 전체목록
		List<CalendarVO> list = new ArrayList<CalendarVO>();
		CalendarVO vo;
		String sql = "SELECT * FROM CALENDAR";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				vo = new CalendarVO();
				vo.setCalendarNo(rs.getInt("calendar_no"));
				vo.setCalendarWriter(rs.getString("calendar_writer"));
				vo.setCalendarTitle(rs.getString("calendar_title"));
				vo.setCalendarStartDate(rs.getDate("calendar_start_date"));
				vo.setCalendarEndDate(rs.getDate("calendar_end_date"));
				list.add(vo);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		} 
		return list;
	}

	@Override
	public CalendarVO calendarSelectOne(CalendarVO vo) {
		// 단건조회
		String sql = "SELECT * FROM CALENDAR WHERE CALENDAR_NO = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getCalendarNo());
			rs = psmt.executeQuery();
			if(rs.next()) {
				vo.setCalendarWriter(rs.getString("calendar_writer"));
				vo.setCalendarTitle(rs.getString("calendar_title"));
				vo.setCalendarStartDate(rs.getDate("calendar_start_date"));
				vo.setCalendarEndDate(rs.getDate("calendar_end_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return vo;
	}

	@Override
	public int calendarUpdate(CalendarVO vo) {
		// 수정(변경)
		int n = 0;
		String sql = "UPDATE CALENDAR SET CALENDAR_WRITER, CALENDAR_TITLE, CALENDAR_START_DATE, CALENDAR_END_DATE";
		return n;
	}

	@Override
	public int calendarDelete(CalendarVO vo) {
		// 삭제
		int n = 0;
		String sql = "DELETE FROM CALENDAR WHERE CALENDAR_NO =?";
		return n;
	}

	@Override
	public int calendarInsert(CalendarVO vo) {
		// 추가
		int n = 0;
		String sql = "INSERT INTO CALENDAR VALUES(CALENDAR_SEQ.NEXTVAL,'ADMIN',?,?,?)";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
//			psmt.setString(1, vo.getCalendarWriter());
			psmt.setString(1, vo.getCalendarTitle());
			psmt.setDate(2, vo.getCalendarStartDate());
			psmt.setDate(3, vo.getCalendarEndDate());
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	private void close() {
		try {
			if(rs != null) rs.close();
			if(psmt != null) psmt.close();
			if(conn != null) conn.close();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
