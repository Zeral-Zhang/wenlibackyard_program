package com.biz;

import com.po.Userinfo;

public interface IUserBiz {
	/**
	 * 模拟用户登录
	 * @param nickName
	 * @return
	 */
	public Userinfo login(String userId);
	
	/**
	 * 修改用户信息
	 * @param userinfo
	 */
	public void update(Userinfo userinfo);
}
