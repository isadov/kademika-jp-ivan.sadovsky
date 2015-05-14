package mainprojects.magazine.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Date {
	SimpleDateFormat sdf;
	Calendar calendar;
	public String currentData;

	public Date() {
		this.sdf = new SimpleDateFormat("yyyy-MM-dd");
		this.calendar = new GregorianCalendar(2013, 10, 28);
		calendar = Calendar.getInstance();
		currentData = sdf.format(calendar.getTime());
	}

	public void setCurrentDate(int year, int month, int day) {
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		currentData = sdf.format(calendar.getTime());
		System.out.println(currentData);
	}

	public Object getCurrentDate() {
		calendar = Calendar.getInstance();
		currentData = sdf.format(calendar.getTime());
		return currentData;
	}

	public void updateCalendar(int shift) {
		calendar.add(Calendar.DAY_OF_MONTH, shift);
		currentData = sdf.format(calendar.getTime());
	}

	public String[] weekDates() {
		String week[] = new String[7];
		Calendar tmpCalendar = new GregorianCalendar(2013, 10, 28);

		tmpCalendar = Calendar.getInstance();
		for (int i = 6; i >= 0; i--) {
			week[i] = sdf.format(tmpCalendar.getTime());
			tmpCalendar.add(Calendar.DAY_OF_MONTH, -1);
		}
		return week;

	}
}
