package co.yedam.puppy.calendar.command;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.yedam.puppy.calendar.service.CalendarService;
import co.yedam.puppy.calendar.service.CalendarServiceImpl;
import co.yedam.puppy.comm.Command;
import co.yedam.puppy.vo.CalendarVO;

public class CalendarList implements Command {

   @Override
   public String exec(HttpServletRequest request, HttpServletResponse response) {
      // 캘린더 리스트 페이지
      CalendarService dao = new CalendarServiceImpl();
      List<CalendarVO> list = new ArrayList<>();
      ObjectMapper mapper = new ObjectMapper(); //jackson 라이브러리 사용(json)
      list = dao.calendarSelectList();
      CalendarVO vo = new CalendarVO();
      
      String title = request.getParameter("title");
      vo.setCalendarTitle(title);
      String start = request.getParameter("start");
      vo.setCalendarStartDate(Date.valueOf(start));
      String end = request.getParameter("end");
      vo.setCalendarEndDate(Date.valueOf(end));
      
      String jsonList = null;
      try {
         jsonList = mapper.writeValueAsString(list);
      } catch (JsonProcessingException e) {
         e.printStackTrace();
      }
      
     // Gson gson = new GsonBuilder().create();
      
      return "ajax:"+jsonList;
   }

}