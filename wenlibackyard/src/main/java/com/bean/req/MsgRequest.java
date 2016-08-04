package com.bean.req;

/**
 * 用户发送给公众号的消息；
 * 
 */

public class MsgRequest {

	private String MsgType; 	// 消息类型（text/image/location/link/event） 
	private String MsgId;		// 消息id，64位整型
	private String FromUserName; // 发送方帐号（一个 OpenID） 
	private String ToUserName;	// 开发者微信号 
	private String CreateTime;  // 消息创建时间 （整型） 
	
	/******************文本消息*******************/
	// 文本消息
	private String Content;

	/******************图片消息*******************/
	// 图片消息
	private String PicUrl;

	/******************地理位置消息*******************/
	// 地理位置维度
	private String Location_X;
	// 地理位置经度
	private String Location_Y;
	// 地图缩放大小
	private String Scale;
	// 地理位置信息
	private String Label;
	
	
	/******************链接消息*******************/
	// 事件消息
	private String Event;	// 事件类型，subscribe(订阅)、unsubscribe(取消订阅)、
							// SCAN(已关注扫描二维码)、LOCATION(用户同意上传地理位置信息)
							// CLICK(点击自定义菜单)、VIEW(点击菜单跳转链接时的事件推送)
	private String EventKey;	// 事件KEY值，qrscene_为前缀，后面为二维码的参数值

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public String getMsgId() {
		return MsgId;
	}

	public void setMsgId(String msgId) {
		MsgId = msgId;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getLocation_X() {
		return Location_X;
	}

	public void setLocation_X(String location_X) {
		Location_X = location_X;
	}

	public String getLocation_Y() {
		return Location_Y;
	}

	public void setLocation_Y(String location_Y) {
		Location_Y = location_Y;
	}

	public String getScale() {
		return Scale;
	}

	public void setScale(String scale) {
		Scale = scale;
	}

	public String getLabel() {
		return Label;
	}

	public void setLabel(String label) {
		Label = label;
	}

	public String getEvent() {
		return Event;
	}

	public void setEvent(String event) {
		Event = event;
	}

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

}
