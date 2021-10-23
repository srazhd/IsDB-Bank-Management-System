package com.citybank.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public final class DateUtils {
	private DateUtils() {

	}

	public static Integer getYearFromCurrentDate() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	public static Integer getYearFromDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}

	public static Integer getMonthFromCurrentDate() {
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	public static String getStringDate(Date date) {
		return getStringDate(date, "dd-MM-yyyy");
	}

	public static String getStringDate(Date date, String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	public static Date getDateFromString(String date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return dateFormat.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	public static Date getExpirationTime(Long expireHours) {
		Date now = new Date();
		Long expireInMilis = TimeUnit.HOURS.toMillis(expireHours);
		return new Date(expireInMilis + now.getTime());
	}

	public static Date calculateExpiryDate(int expiryTimeInMinutes) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Timestamp(cal.getTime().getTime()));
		cal.add(Calendar.MINUTE, expiryTimeInMinutes);
		return new Date(cal.getTime().getTime());
	}

	public static String getDayMonthFromDate(String date) {
		String[] strings = date.split("-");
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(strings[0]).append("-").append(strings[1]);
		return stringBuilder.toString();
	}
}
