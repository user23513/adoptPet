package co.yedam.puppy.calendar.command;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.puppy.calendar.service.CalendarService;
import co.yedam.puppy.calendar.service.CalendarServiceImpl;
import co.yedam.puppy.comm.Command;
import co.yedam.puppy.vo.CalendarVO;

public class CalendarInsert implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 캘린더 등록 페이지
		CalendarService dao =  new CalendarServiceImpl(); // 인스턴스 (구현체) 생성
		CalendarVO vo = new CalendarVO();
		int r = dao.calendarInsert(vo); // int 타입 0건입력 1건입력
		
		String title = request.getParameter("title"); // 
		vo.setCalendarTitle(title);
		String start = request.getParameter("start");
		vo.setCalendarStartDate(Date.valueOf(start));
		String end = request.getParameter("end");
		vo.setCalendarEndDate(Date.valueOf(end));
		
		if(r > 0) {
			System.out.println("DB에 1건 입력되었습니다.");
		} else {
			System.out.println("입력 실패!!ㅜㅜ ");
		}
		
		return "calendar/calendar"; // 페이지 이동 않고 캘린더를 보여줘야하므로 insert 페이지가 아닌 캘린더
	}

}
