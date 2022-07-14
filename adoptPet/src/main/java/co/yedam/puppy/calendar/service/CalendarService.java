package co.yedam.puppy.calendar.service;

import java.util.List;

import co.yedam.puppy.calendar.vo.CalendarVO;


public interface CalendarService {
	List<CalendarVO> calendarSelectList(); // 목록
	CalendarVO calendarSelectOne(CalendarVO vo); // 조회
	int calendarUpdate(CalendarVO vo); // 수정
	int calendarDelete(CalendarVO vo); // 삭제
	int calendarInsert(CalendarVO vo); // 추가
	
	List<CalendarVO> calendarAddSelectList(int startRow, int pageSize); // 봉사활동 전체조회
	
}
