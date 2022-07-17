package co.yedam.puppy.calendar.service;

import java.util.List;

import co.yedam.puppy.vo.CalendarVO;



public interface CalendarService {
	List<CalendarVO> calendarSelectList(); // 목록
	int calendarInsert(CalendarVO vo); // 추가
	int calendarDelete(CalendarVO vo); // 삭제

	
}
