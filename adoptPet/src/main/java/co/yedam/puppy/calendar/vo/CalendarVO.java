package co.yedam.puppy.calendar.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalendarVO {
	
	// 필드
	private int calendarNo;
	private String calendarWriter;
	private Date calendarTitle;
	private Date calendarStartDate;
	private Date calendarEndDate;
	
	
}
