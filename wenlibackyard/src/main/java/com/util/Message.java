package com.util;

public class Message {
	
	public static final String SUCCESS = "success",
					INFO = "info",
					FAIL = "fail";
	
	String type = INFO;

	String msg;
	
	public Message(String msg){
		this.msg = msg;
	}
	
	public Message(String msg,String type) {
		this(msg);
		this.type = type;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
}
