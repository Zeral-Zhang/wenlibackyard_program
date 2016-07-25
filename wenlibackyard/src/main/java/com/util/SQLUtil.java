package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SQLUtil {

	public static List<String> parseSQLFile(String sqlClassPath){
		InputStream is = null;
		try {
			is = ResourceUtil.getResourceAsStream(sqlClassPath);
			byte[] bs = new byte[is.available()];
			is.read(bs);
			String str = new String(bs, "utf-8");
			str = str.replaceAll("/\\*[\\s\\S]+\\*/", "");// 去掉多行注释
			String[] strs = str.split("\\;");// SQL分割符，以;分开逐条执行
			List<String> sqls = new ArrayList<String>(strs.length);
			for (String s : strs) {
				if (s.trim().isEmpty()) {
					continue;
				}
				sqls.add(s);
			}
			return sqls;
		} catch (Exception e) {
			throw new RuntimeException("初始化建表时出错:" + e.getMessage(), e);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
	}
}
