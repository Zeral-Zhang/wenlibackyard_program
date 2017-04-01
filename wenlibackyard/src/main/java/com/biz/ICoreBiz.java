package com.biz;

public interface ICoreBiz {
	/**
	 * 开发者Token验证
	 *
	 */
	public void checkSignature();
	
	/**
	 * 请求处理
	 * 
	 */
	public void handleRequest();
}
