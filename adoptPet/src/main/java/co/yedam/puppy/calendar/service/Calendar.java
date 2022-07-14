package co.yedam.puppy.calendar.service;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.yedam.puppy.comm.Command;
import co.yedam.puppy.vo.CalendarVO;

public class Calendar implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 캘린더
		CalendarService dao = new CalendarServiceImpl(); // 인스턴스 생성
		CalendarVO vo = new CalendarVO();
		String calendarNo = request.getParameter("calendarNo");
		vo.setCalendarNo(Integer.parseInt(calendarNo));
		vo.setCalendarWriter(request.getParameter("calendarWriter"));
		vo.setCalendarTitle(request.getParameter("calendarTitle"));
		String startDate = request.getParameter("startDate");
		vo.setCalendarStartDate(Date.valueOf(startDate));
		String endDate = request.getParameter("endDate");
		vo.setCalendarEndDate(Date.valueOf("endDate"));
		Gson gson = new GsonBuilder().create();
		
		return "calendar/calendar";
	}

}
