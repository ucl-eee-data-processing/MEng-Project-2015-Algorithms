package meng_project.algorithms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

public class TimeProcessor {

	int day_of_week;
	int hour_of_day;
	int minute_of_day;
	boolean today = false;
	boolean time = false;

	public TimeProcessor(int day, int hour, int minute, boolean today, boolean time) {
		this.day_of_week = day;
		this.hour_of_day = hour;
		this.minute_of_day = minute;
		this.today = today;
		this.time = time;
	}
	
	public void setTime(int hour,int minute){
		this.hour_of_day = hour;
		this.minute_of_day = minute;
	}
	
	public TimeProcessor(){
		
	}

	// public TimeProcessor() {
	//
	// //EMPTY CONSTRUCTOR
	// }

	Calendar cal = new GregorianCalendar();
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy-HH:mm");

	public double timeToDouble(String time) {

		// need to check if it's in the true format

		double timeInDouble = 0;

		try {
			Date timeOfData = formatter.parse(time);
			timeInDouble = timeOfData.getTime() / 1000;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return timeInDouble;
	}

	public String doubleToString(double timeInDouble) {

		long timeInLong = (long) timeInDouble * 1000;  //as we divided earlier by 1000 to convert to double
		@SuppressWarnings("deprecation")
		Date date = new Date(timeInLong);

		String timeInString = formatter.format(date);

		return timeInString;

	}

	public List<Double> timeListToDouble(List<String> stringList) {  //need to make these more clear

		List<Double> timeList = new ArrayList<Double>();

		for (Iterator<String> iter = stringList.iterator(); iter.hasNext();) {

			timeList.add(timeToDouble(iter.next()));
		}

		return timeList;
	}

	public boolean isDayOfWeek(String time, int dayOfWeek) {

		boolean day = false;

		try {
			Date timeOfData = formatter.parse(time);
			cal.setTime(timeOfData);

			if (cal.get(Calendar.DAY_OF_WEEK) == dayOfWeek) {
				day = true;
			} else {
				day = false;
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return day;

	}

	public boolean isTimeOfDay(String time) {

		boolean hhmm = false;

		try {
			Date timeOfData = formatter.parse(time);
			cal.setTime(timeOfData);

			if (cal.get(Calendar.HOUR_OF_DAY) == this.hour_of_day && cal.get(Calendar.MINUTE) == this.minute_of_day) {
				hhmm = true;
			} else {
				hhmm = false;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return hhmm;

	}

	

}
