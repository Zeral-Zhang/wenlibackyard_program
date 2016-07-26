package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

/**
 * 负责日期由string和Date类型之间的转换
 * @author ZeralZhang
 *
 */
public class DateConverter extends StrutsTypeConverter {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		try {
			if(values.length <= 1) {
				return sdf.parseObject(values[0]);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String convertToString(Map context, Object o) {
		return sdf.format(o);
	}

}
