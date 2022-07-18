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
		//캘린더 페이지 이동
		return "calendar/calendar";
	}

}
