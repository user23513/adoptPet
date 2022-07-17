package co.yedam.puppy.calendar.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import co.yedam.puppy.comm.Command;

public class Calendar implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {

//		// 캘린더 페이지
//		CalendarService calendarDao = new CalendarServiceImpl(); // 인스턴스 생성
//		List<CalendarVO> list = calendarDao.calendarSelectList();
//		CalendarVO vo = new CalendarVO();
		
////		String calendarNo = request.getParameter("calendarNo");
//		vo.setCalendarNo(Integer.parseInt(calendarNo));
//		vo.setCalendarWriter(request.getParameter("calendarWriter"));
//		System.out.println(request.getParameter("calendarWriter"));
//		vo.setCalendarTitle(request.getParamㅊeter("calendarTitle"));
//		String startDate = request.getParameter("startDate");
//		vo.setCalendarStartDate(Date.valueOf(startDate));
//		String endDate = request.getParameter("endDate");
//		vo.setCalendarEndDate(Date.valueOf("endDate"));
//		Gson gson = new GsonBuilder().create();
//		//response.getWriter().print(gson.toJson(schedules));

//		
//		request.setAttribute("list", list);
		return "calendar/calendar";
	}

}
