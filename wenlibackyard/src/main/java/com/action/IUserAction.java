package com.action;

import com.po.Userinfo;

/**
 * 用于用户请求处理，包括用户授权，模拟用户登陆
 * @author ZeralZhang
 *
 */
public interface IUserAction {

	/**
	 * 模拟用户登录，用于本地测试 
	 */
	public void login();
	
	/**
	 * 修改用户信息
	 * @return
	 */
	public String update();
	
	/**
	 * 初始化登陆
	 * @return
	 */
	public String initLogin();
	
	/**
	 * 初始化用户信息界面
	 * @return
	 */
	public String initUserInfo();
}
