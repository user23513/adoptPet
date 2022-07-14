package co.yedam.puppy.calendar.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.yedam.puppy.comm.Command;

public class Calendar implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 캘린더
		CalendarService dao = new CalendarServiceImpl(); // 인스턴스 생성
		Gson gson = new GsonBuilder().create();
		
		return "calendar/calendar";
	}

}
