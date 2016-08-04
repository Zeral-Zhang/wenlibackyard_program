package com.action;

/**
 * 
 * 核心请求处理，包括开发者授权，消息处理
 * @author ZeralZhang
 *
 */
public interface ICoreAction {

	/**
	 * 处理所有请求，包括消息处理，基本请求处理
	 * @return
	 */
	public String handle();
}
