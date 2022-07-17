package co.yedam.puppy.calendar.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.yedam.puppy.comm.Command;
import co.yedam.puppy.vo.CalendarVO;

public class CalendarList implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 캘린더 리스트 페이지
		CalendarService dao = new CalendarServiceImpl();
		List<CalendarVO> list = new ArrayList<CalendarVO>();
		List<CalendarVO> schedules = dao.calendarSelectList();
		CalendarVO vo = new CalendarVO();
		
		String title = request.getParameter("title");
		vo.setCalendarTitle(title);
		String start = request.getParameter("start");
		vo.setCalendarStartDate(Date.valueOf(start));
		String end = request.getParameter("end");
		vo.setCalendarEndDate(Date.valueOf(end));
		
		Gson gson = new GsonBuilder().create();
		response.getTitle().print(gson.toJson(schedules));
		
		return "calendar/calendar";
	}

}
