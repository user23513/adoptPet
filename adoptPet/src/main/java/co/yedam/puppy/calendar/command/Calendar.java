package co.yedam.puppy.calendar.command;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.yedam.puppy.comm.Command;
import co.yedam.puppy.vo.CalendarVO;

public class Calendar implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		
	
//		// 캘린더 페이지
//		CalendarService calendarDao = new CalendarServiceImpl(); // 인스턴스 생성
//		List<CalendarVO> list = calendarDao.calendarSelectList();
//		CalendarVO vo = new CalendarVO();
//		
////		String calendarNo = request.getParameter("calendarNo");
//		vo.setCalendarNo(Integer.parseInt(calendarNo));
//		vo.setCalendarWriter(request.getParameter("calendarWriter"));
//		System.out.println(request.getParameter("calendarWriter"));
//		vo.setCalendarTitle(request.getParameter("calendarTitle"));
//		String startDate = request.getParameter("startDate");
//		vo.setCalendarStartDate(Date.valueOf(startDate));
//		String endDate = request.getParameter("endDate");
//		vo.setCalendarEndDate(Date.valueOf("endDate"));
//		Gson gson = new GsonBuilder().create();
//		//response.getWriter().print(gson.toJson(schedules));
//		
		// 캘린더 폼 등록
////		int n = 0;
////		try {
////			n = calendarDao.calendarInsert(vo);
////			vo.setCalendarNo(n);
////		} catch (IOException e) {
////			e.printStackTrace();
////		}
////		
//		
//		request.setAttribute("list", list);
		return "calendar/calendar";
	}

}
