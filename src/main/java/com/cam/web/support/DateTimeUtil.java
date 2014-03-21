package com.cam.web.support;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateTimeUtil {



	public Date provideDate(String dateString){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date dateObj=null;

		try {
			dateObj = df.parse(dateString);
		} catch (ParseException e) {
		}
		return dateObj;
	}

	public String formatDate(Date date,String format){
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat(format);
		String dateAsString = simpleDateFormat.format(date);
		return dateAsString;
	}

	public Date provideDate(Date date,String dateOfMonth){
		return getMonthsFirstOrLastDate(date, dateOfMonth);
	}

	public Date getMonthsFirstOrLastDate(Date date,String dateOfMonth ){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		if(dateOfMonth.equals("first")){			
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);

		}
		else{			
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		return calendar.getTime();
	}	

	public Date getNextMonthDate(Date date){		
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	public String getMonthYearString(Date date){		
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1);

	}

	public String getDayMonthString(Date date){		
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH)+"-"+(calendar.get(Calendar.MONTH)+1);

	}

	public String getHourDayStringWithDate(Date date){		
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		String minut=calendar.get(Calendar.MINUTE)+"";
		String hours=calendar.get(Calendar.HOUR)+"";
		if(minut.length()==1){
			minut=0+minut;
		}
		if(hours.length()==1){
			hours=0+hours;
		}
		return calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.DAY_OF_MONTH)+"-"+calendar.get(Calendar.MONTH)+1+"  from"+hours +":"+minut+" "+calendar.get(Calendar.AM_PM);
	}

	public String getHourDayString(Date date){		
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		String minut=calendar.get(Calendar.MINUTE)+"";
		String hours=calendar.get(Calendar.HOUR)+"";
		if(minut.length()==1){
			minut=0+minut;
		}
		if(hours.length()==1){
			hours=0+hours;
		}
		return "  to"+hours +":"+minut+" "+calendar.get(Calendar.AM_PM);
	}

	public Date getYearsFirstOrLastDate(Date date, String firstOrLast){

		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		if(firstOrLast.equals("first")){
			calendar.set(Calendar.MONTH, 0);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
		}
		else{
			calendar.set(Calendar.MONTH, 11);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		}
		return calendar.getTime();
	}

	public Date getNextDay(Date date){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();		
	}
	public Date getPrevDay(Date date){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return calendar.getTime();		
	}

	public Date getNextHour(Date date){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, 1);
		return calendar.getTime();		
	}

	public Date getLastHourDate(Date date){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();	
	}

	public Date getLastTwelveHoursDate(Date date){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, -12);
		return calendar.getTime();	
	}

	public Date dateByTimezone(Date date,String timeZone) throws ParseException{
		long diff=TimeZone.getTimeZone(timeZone).getRawOffset();
		return new Date(date.getTime()-diff);		
	}
	
	public Date dateByTimezoneAddDiff(Date date,String timeZone) throws ParseException{
		long diff=TimeZone.getTimeZone(timeZone).getRawOffset();
		return new Date(date.getTime()+diff);		
	}
	
	public Integer getMonth(Date date){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);		
		return calendar.get(Calendar.MONTH);	
	}
}
