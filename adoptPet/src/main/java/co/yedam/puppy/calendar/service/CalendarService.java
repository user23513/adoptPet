package co.yedam.puppy.calendar.service;

import java.util.List;

import co.yedam.puppy.vo.CalendarVO;



public interface CalendarService {
	List<CalendarVO> calendarSelectList(); // 목록
	CalendarVO calendarSelectOne(CalendarVO vo); // 단건조회
	int calendarUpdate(CalendarVO vo); // 수정
	int calendarDelete(CalendarVO vo); // 삭제
	int calendarInsert(CalendarVO vo); // 추가

	
}
