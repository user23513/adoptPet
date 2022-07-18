package co.yedam.puppy.calendar.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.puppy.comm.Command;
import co.yedam.puppy.vo.CalendarVO;

public class CalendarDelete implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 캘린더 삭제 페이지
		CalendarService dao = new CalendarServiceImpl();
		CalendarVO vo = new CalendarVO();
		int r = dao.calendarDelete(vo);
		
		String title = request.getParameter("title");
		
		if (r > 0) {
			System.out.println("DB에 1건이 삭제되었습니다.");
		} else {
			System.out.println("삭제 실패~!");
		}
		
		return "calendar/calendar";
	}

}
