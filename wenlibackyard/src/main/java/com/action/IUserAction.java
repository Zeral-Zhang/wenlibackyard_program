package com.action;

/**
 * 用于用户请求处理，包括用户授权，模拟用户登陆
 * 
 * @author ZeralZhang
 *
 */
public interface IUserAction {

//	/**
//	 * 模拟用户登录，用于本地测试
//	 */
//	public void login();

	/**
	 * 修改用户信息
	 * 
	 * @return
	 */
	public String update();

	/**
	 * 初始化登陆
	 * 
	 * @return
	 */
	public String initLogin();

	/**
	 * 初始化用户信息界面
	 * 
	 * @return
	 */
	public String toUserInfo();

	/**
	 * 初始化用户详情页面
	 * 
	 * @return
	 */
	public String toUserDetail();

	/**
	 * 用户授权登陆
	 * @return
	 */
	String validateUser();

	/**
	 * 初始化用户正在售卖页面
	 * @return
	 */
	String toUserSaling();
	
	/**
	 * 初始化用户已经购买列表
	 * @return
	 */
	String toUserPayed();
	
}
