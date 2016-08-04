package com.biz.imp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import com.bean.req.MsgRequest;
import com.bean.resp.MsgResponseText;
import com.biz.ICoreBiz;
import com.util.MsgXmlUtil;
import com.util.PropertiesConfigUtil;
import com.util.SignUtil;

@Service("CoreBizImpl")
public class CoreBizImpl implements ICoreBiz {
	private static final Logger log = Logger.getLogger(CoreBizImpl.class);
	@Override
	public void checkSignature() {
		log.info("##### valid url ");
		try {
			Properties prop = PropertiesConfigUtil
					.getProperties("account.properties");
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			if (request.getParameter("timestamp") == null) {// 如果是空打印出首页
				request.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charset=UTF-8");
				response.setCharacterEncoding("utf-8");
				response.getWriter().write("文理后院测试平台");
			} else {
				String signature = request.getParameter("signature");// 微信加密签名
				String timestamp = request.getParameter("timestamp");// 时间戳
				String nonce = request.getParameter("nonce");// 随机数
				String echostr = request.getParameter("echostr");// 随机字符串

				// 校验成功返回 echostr，成功成为开发者；否则返回error，接入失败
				if (SignUtil.validSign(signature, prop.getProperty("token"),
						timestamp, nonce)) {
					response.getWriter().write(echostr);
				} else {
					response.getWriter().write("error");
				}
				
			}
		} catch (UnsupportedEncodingException e) {
			log.error("valid url UnsupportedEncodingException", e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			log.error("valid url IOException", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void handleRequest() throws IOException {
		log.info("##### process msg  ");
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			MsgRequest msgRequest = MsgXmlUtil.parseXml(request);// 获取发送的消息

			// 根据请求类型处理不同的请求
			if (MsgXmlUtil.REQ_MESSAGE_TYPE_EVENT.equals(msgRequest // 处理推送事件
					.getMsgType())) {
				// 订阅
				if (msgRequest.equals(MsgXmlUtil.EVENT_TYPE_SUBSCRIBE)) {
					responseText("感谢您的关注 /::) /::) /::)");
				}
				// 取消订阅
				else if (msgRequest.equals(MsgXmlUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复 消息
				}
				// 自定义菜单点击事件
				else if (msgRequest.equals(MsgXmlUtil.EVENT_TYPE_CLICK)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复 消息
				}
				
			} else if (MsgXmlUtil.REQ_MESSAGE_TYPE_LOCATION.equals(msgRequest // 处理用户位置信息
					.getMsgType())) {
				StringBuffer buffer = new StringBuffer();
				buffer.append("您的位置为：").append("\n\n");
				buffer.append("地理位置维度： " + msgRequest.getLocation_X()).append("\n");
				buffer.append("地理位置经度： " + msgRequest.getLocation_Y()).append("\n");
				buffer.append("地图缩放大小： " + msgRequest.getScale()).append("\n");
				buffer.append("地理位置信息： " + msgRequest.getLabel()).append("\n");
				responseText(buffer.toString());
			} else if (MsgXmlUtil.REQ_MESSAGE_TYPE_TEXT.equals(msgRequest // 处理文本消息
					.getMsgType())) {
				if ("?".equals(msgRequest.getContent())) {
					StringBuffer buffer = new StringBuffer();
					buffer.append("您好，这里是文理后院，请回复数字选择服务：").append("\n\n");
					buffer.append("1 最新商品").append("\n");
					buffer.append("2 发布商品").append("\n");
					buffer.append("3 我的订单").append("\n");
					buffer.append("回复“?”显示此帮助菜单");
					responseText(buffer.toString());
				} else if ("1".equals(msgRequest.getContent())) {
					responseText("这里是最新商品");
				} else if ("2".equals(msgRequest.getContent())) {
					responseText("这里可以发布商品");
				} else if ("3".equals(msgRequest.getContent())) {
					responseText("这里是你的订单");
				} else {
					StringBuffer buffer = new StringBuffer();
					buffer.append("您好，这里是文理后院，请回复数字选择服务：").append("\n\n");
					buffer.append("1 最新商品").append("\n");
					buffer.append("2 发布商品").append("\n");
					buffer.append("3 我的订单").append("\n");
					buffer.append("回复“?”显示此帮助菜单");
					responseText(buffer.toString());
				}
			} else {
				responseText("你的请求类型暂时无法处理，请尝试其它方式 /::) /::) /::)");
			}
		} catch (Exception e) {
			log.error("process msg exception", e);
			throw new RuntimeException(e);
		}
	}
	
	private void responseText(String responseStr) {
		log.info("##### response msg  ");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			MsgRequest msgRequest = MsgXmlUtil.parseXml(request);// 获取发送的消息
			MsgResponseText responseText = new MsgResponseText();
			responseText.setToUserName(msgRequest.getFromUserName());
			responseText.setFromUserName(msgRequest.getToUserName());
			responseText.setMsgType("text");
			responseText.setCreateTime(new Date().getTime());
			responseText.setContent(responseStr);
			String str = MsgXmlUtil.textToXml(responseText);
			log.info("##### response.write = " + str);

			response.getWriter().write(
					new String(str.getBytes("UTF-8"), "ISO8859-1"));
		} catch (UnsupportedEncodingException e) {
			log.error("response msg UnsupportedEncodingException", e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			log.error("response msg IOException", e);
			throw new RuntimeException(e);
		} catch (Exception e) {
			log.error("response msg Exception", e);
			throw new RuntimeException(e);
		}
	}
}
