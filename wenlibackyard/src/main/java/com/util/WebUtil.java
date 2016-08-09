package com.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bean.PageBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/**
 * WebUtil <br/>
 * 1 提供发送Json字符串到前端的方法<br/>
 * 自动将对象或数组转换成Json格式的字符串，并提供对象属性过滤的功能 <br/>
 * 2 提供发送错误信息到浏览器的方法，格式为json <br/>
 * 
 * @author Zeral
 */
public class WebUtil {
	public final static Integer SERVER_ERROR = 500;

	/**
	 * 发送错误信息到客户端
	 * 
	 * @param errorMsg
	 *            错误信息
	 * @author Zeral
	 */
	public static void sendErrorMsg(final String errorMsg) {
		final HttpServletResponse response = ServletActionContext.getResponse();
		final Message message = new Message(errorMsg, Message.FAIL);
		response.setStatus(SERVER_ERROR);
		final JSONObject jsonMessage = JSONObject.fromObject(message);
		write(jsonMessage.toString(), response);
	}

	/**
	 * 发送成功信息到客户端
	 * 
	 * @author Rambo
	 * @date 2014-3-12
	 * @param msg
	 *            信息内容
	 * @param type
	 *            信息类型
	 */
	public static void sendMessage(final String msg, final String type) {
		final HttpServletResponse response = ServletActionContext.getResponse();
		final Message message = new Message(msg, type);
		final JSONObject jsonMessage = JSONObject.fromObject(message);
		write(jsonMessage.toString(), response);
	}

	public static void sendInfoMsg(final String msg) {
		sendMessage(msg, Message.INFO);
	}

	public static void sendSuccessMsg(final String msg) {
		sendMessage(msg, Message.SUCCESS);
	}

	/**
	 * 将对象转化为Json格式并发送到客户端
	 * 
	 * @author Zeral
	 */
	public static void sendJSONObjectResponse(final Object object) {
		sendJSONObjectResponse(object, null);
	}

	/**
	 * 将对象转化为Json格式并发送到客户端
	 * 
	 * @param excludes
	 *            除对象部分属性
	 * @author Zeral
	 */
	public static void sendJSONObjectResponse(final Object object, final String[] excludes) {
		final JsonConfig jsonConfig = new JsonConfig();
		if (excludes != null) {
			jsonConfig.setExcludes(excludes);
		}
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor());
		final JSONObject jsonObject = JSONObject.fromObject(object, jsonConfig);
		sendResponse(jsonObject.toString());
	}

	/**
	 * 将数组转化为Json格式并发送到客户端
	 * 
	 * @author Zeral
	 */
	public static void sendJSONArrayResponse(final Object array) {
		sendJSONArrayResponse(array, new String[] {});
	}

	/**
	 * 将对象转化为Json格式并发送到客户端 并提供去除对象部分属性的功能
	 * 
	 * @param excludes
	 *            除对象部分属性
	 * @author Zeral
	 */
	public static void sendJSONArrayResponse(final Object array, final String[] excludes) {
		sendJSONArrayResponse(array, excludes, null);
	}

	/**
	 * 将对象转化为Json格式并发送到客户端 并提供去除对象部分属性的功能 提供分页功能
	 * 
	 * @author Zeral
	 */
	public static void sendJSONArrayResponse(final Object array, final String[] excludes, final PageBean pageInfo) {
		final JsonConfig config = new JsonConfig();
		config.setExcludes(excludes);
		config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor());
		// 防止自包含
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT); 
		final JSONArray jsonArray = JSONArray.fromObject(array, config);
		if (pageInfo != null) {
			final JSONObject jsonObject = new JSONObject();
			jsonObject.put("total", pageInfo.getTotalCount());
			jsonObject.put("rows", jsonArray);
			sendResponse(jsonObject.toString());
		} else {
			sendResponse(jsonArray.toString());
		}
	}

	/**
	 * 发送字符串
	 * 
	 * @author Zeral
	 */
	public static void sendResponse(final String text) {
		HttpServletResponse response = ServletActionContext.getResponse();
		write(text, response);
	}

	/**
	 * 将字符串写到response writer流中
	 * 
	 * @author Zeral
	 */
	private static void write(final String context, final HttpServletResponse response) {
		PrintWriter writer = null;
		try {
			response.setContentType("text/plain");
			response.setCharacterEncoding("utf-8");
			writer = response.getWriter();
			writer.write(context);
		} catch (IOException e) {

		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	public static HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	public static HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}
	
	public static HttpSession getSession() {
		return getRequest().getSession();
	}

	public static int getParameter(String paramName, int defaultValue) {
		HttpServletRequest req = getRequest();
		return getParameter(req, paramName, defaultValue);
	}

	public static String getParameter(String paramName, String defaultValue) {
		HttpServletRequest req = getRequest();
		return getParameter(req, paramName, defaultValue);
	}

	public static int getParameter(HttpServletRequest request, String paramName, int defaultValue) {
		String value = request.getParameter(paramName);
		if (value != null) {
			try {
				return Integer.parseInt(value);
			} catch (NumberFormatException e) {
			}
		}
		return defaultValue;
	}

	public static String getParameter(HttpServletRequest request, String paramName, String defaultValue) {
		String value = request.getParameter(paramName);
		if (value != null) {
			return value;
		}
		return defaultValue;
	}

}
