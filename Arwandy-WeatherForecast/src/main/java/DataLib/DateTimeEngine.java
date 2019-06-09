package DataLib;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class DateTimeEngine {
	
	public static String convertToTodayEODTimeStamp() {
		LocalDateTime ldt = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59, 59));
		Date dt = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
		
		return Long.toString(dt.getTime()/1000);
	}
	
	public static Date convertToTodayEODDate() {
		LocalDateTime ldt = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59, 59));
		Date dt = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
		
		return dt;
	}
	
	public static Date convertToTodaySODDate() {
		LocalDateTime ldt = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(23, 59, 59));
		Date dt = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
		
		return dt;
	}
}
