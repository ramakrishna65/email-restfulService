/**
 * 
 */
package com.smartweights.mail.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;

/**
 * @author lenovo
 *
 */

@Controller("dateUtils")
public class DateUtils {

	static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss");
	static SimpleDateFormat dayfr = new SimpleDateFormat("EEE MMM dd, YYYY", Locale.ENGLISH);
	
	
	public Date getStartofDay(float unixTime){
		Date dt = new java.util.Date((long) unixTime * 1000);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);	
		return calendar.getTime();	
	}
	
	
	public Date getEndofDay(float unixTime){
		Date dt = new java.util.Date((long) unixTime * 1000);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt);
		 
		return calendar.getTime();
	}
	
	
	// Fri Dec 11th, 2015
	@SuppressWarnings("deprecation")
	public String getDispayDate(Date dt){
		//Date dt = new java.util.Date((long) key * 1000);
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		
		String dayNumberSuffix = getDayNumberSuffix(day);
		DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd'" + dayNumberSuffix + "', yyyy", Locale.ENGLISH);
		
		return dateFormat.format(dt);
	}
	
	private String getDayNumberSuffix(int day) {
	    if (day >= 11 && day <= 13) {
	        return "th";
	    }
	    switch (day % 10) {
	    case 1:
	        return "st";
	    case 2:
	        return "nd";
	    case 3:
	        return "rd";
	    default:
	        return "th";
	    }
	}
	
	public Date getNextDay(Date currentDay, int index){
		Calendar c = Calendar.getInstance();
		c.setTime(currentDay);
		c.add(Calendar.DATE, index);
		return c.getTime();
	}
	
	public Date getlastWeekDate(){
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.add(Calendar.DAY_OF_MONTH, -6);
		Date sevenDaysAgo = calendar.getTime();
		
		return sevenDaysAgo;
	}
	
	public Date getlast2WeekDate(){
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.add(Calendar.DAY_OF_MONTH, -13);
		Date foutreenDaysAgo = calendar.getTime();
		
		return foutreenDaysAgo;
	}
	
	
	public List<String> getDayStrings(){
		List<String> dateString = new ArrayList();
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DAY_OF_MONTH, -7);		
		SimpleDateFormat sdf = new SimpleDateFormat("EE");
		
		for(int i=0; i < 14; i++){
			calendar.add(Calendar.DAY_OF_MONTH, 1);				 
			String dayOfTheWeek = sdf.format(calendar.getTime());
			dateString.add(dayOfTheWeek.toUpperCase());
		}
		
		return dateString;		
	}
	
	public Date getTodayDate(){
		Date dt = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 59);		
		return calendar.getTime();	
	}
	
	
	public int getCurrentYear(){
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		return year;
	}
	
}
