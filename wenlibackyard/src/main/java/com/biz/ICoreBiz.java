package com.biz;

import java.io.IOException;

public interface ICoreBiz {
	/**
	 * 开发者Token验证
	 * @throws IOException
	 */
	public void checkSignature();
	
	/**
	 * 请求处理
	 * @throws IOException
	 */
	public void handleRequest();
}
