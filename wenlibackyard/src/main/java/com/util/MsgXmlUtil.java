package com.util;

import java.io.InputStream;
import java.io.Writer;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.bean.req.MsgRequest;
import com.bean.resp.Article;
import com.bean.resp.MsgResponseNews;
import com.bean.resp.MsgResponseText;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * 对请求数据和返回数据进行处理类
 * 
 * @author Runaway
 *
 */
@SuppressWarnings("unchecked")
public class MsgXmlUtil {
	/**  
	 *  返回消息类型：文本 
	 *
	 */
	public static final String RESP_MESSAGE_TYPE_TEXT = "text"; 
	/** 
	 * 返回消息类型：音乐
	 *
	 */ 
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";
	/** 
	 * 返回消息类型：图文 
	 * 
	 */
	public static final String RESP_MESSAGE_TYPE_NEWS = "news"; 
	/**  
	 *  请求消息类型：文本 
	 *
	 */
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";
	/** 
	 * 请求消息类型：图片
	 *  
	 */ 
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
	/** 
	 *  请求消息类型：链接 
	 *   
	 */ 
	public static final String REQ_MESSAGE_TYPE_LINK = "link"; 
	/** 
	 * 请求消息类型：地理位置
	 *  
	 */
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location"; 
	/**
	 * 请求消息类型：音频
	 *  
	 */ 
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice"; 
	 /** 
	  * 请求消息类型：推送
	  *  
	  */
	public static final String REQ_MESSAGE_TYPE_EVENT = "event"; 
	/** 
	 * 事件类型：subscribe(订阅)
	 *  
	 */ 
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe"; 
	/**
	 * 事件类型：unsubscribe(取消订阅)
	 *  
	 */
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
	/**
	 * 事件类型：CLICK(自定义菜单点击事件)
	 *  
	 */
	public static final String EVENT_TYPE_CLICK = "CLICK";

	//将request 消息 转换成 请求消息对象
	public static MsgRequest parseXml(HttpServletRequest request) throws Exception {
		MsgRequest msgReq = new MsgRequest();
		
		// 解析XML
		InputStream inputStream = request.getInputStream();
		
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		Element root = document.getRootElement();
		List<Element> elementList = root.elements();
		
		// 遍历节点，封装成对象
		for (Element e : elementList){
			String name = e.getName();
			String text = e.getText();
			
			if("MsgType".equals(name)){//消息类型
				msgReq.setMsgType(text);
			}else if("MsgId".equals(name)){
				msgReq.setMsgId(text);
			}else if("FromUserName".equals(name)){
				msgReq.setFromUserName(text);
			}else if("ToUserName".equals(name)){
				msgReq.setToUserName(text);
			}else if("CreateTime".equals(name)){
				msgReq.setCreateTime(text);
			}else if("Content".equals(name)){//文本消息
				msgReq.setContent(text);
			}else if("PicUrl".equals(name)){//图片消息
				msgReq.setPicUrl(text);
			}else if("Location_X".equals(name)){//地理位置消息
				msgReq.setLocation_X(text);
			}else if("Location_Y".equals(name)){
				msgReq.setLocation_Y(text);
			}else if("Scale".equals(name)){
				msgReq.setScale(text);
			}else if("Label".equals(name)){
				msgReq.setLabel(text);
			}else if("Event".equals(name)){//事件消息
				msgReq.setEvent(text);
			}else if("EventKey".equals(name)){
				msgReq.setEventKey(text);
			}
		}
		inputStream.close();
		inputStream = null;
		return msgReq;
	}
	
	
	/**
	 * 文本消息转换成xml
	 * 
	 * @param text
	 * @return
	 */
	public static String textToXml(MsgResponseText text) {
		xstream.alias("xml", text.getClass());
		return xstream.toXML(text);
	}
	
	
	/**
	 * 图文消息转换成xml
	 * 
	 * @param news
	 * @return
	 */
	public static String newsToXml(MsgResponseNews news) {
		xstream.alias("xml", news.getClass());
		xstream.alias("item", new Article().getClass());
		return xstream.toXML(news);
	}
	
	/**
	 * 扩展xstream，让xml节点增加CDATA标记
	 */
	public static XStream xstream = new XStream(new XppDriver() {
		@Override
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				boolean CDATA = true;
				
				@Override
				@SuppressWarnings("rawtypes")
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}
				@Override
				protected void writeText(QuickWriter writer, String text) {
					if (CDATA) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});
	
}

