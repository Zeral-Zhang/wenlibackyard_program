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
	 * 修改用户信息，若用户信息不存在，则新建用户信息并保存
	 * @param userinfo
	 */
	public void update(Userinfo userinfo);
}
