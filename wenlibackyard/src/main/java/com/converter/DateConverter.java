package com.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

public class DateConverter extends StrutsTypeConverter {
	private static String DATE_TIME_FOMART = "yyyy-MM-dd";

	@SuppressWarnings("rawtypes")
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		Date date = null;
		String dateString = null;
		if (values != null && values.length > 0) {
			dateString = values[0];
			if (dateString != null) {
				SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FOMART);
				try {
					date = format.parse(dateString);
				} catch (ParseException e) {
					date = null;
				}
			}
		}
		return date;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String convertToString(Map context, Object o) {
		Date date = (Date) o;
		SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FOMART);
        String dateTimeString = format.format(date);
        return dateTimeString;
	}

}
