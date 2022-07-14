package co.yedam.puppy.vo;


import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalendarVO {
	
	// 필드
	private int calendarNo;
	private String calendarWriter;
	private String calendarTitle;
	private Date calendarStartDate;
	private Date calendarEndDate;
	
}
