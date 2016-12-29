package com.biz;

import com.po.UserDetailInfo;
import com.po.UserInfo;

public interface IUserBiz {
	/**
	 * 模拟用户登录
	 * @param nickName
	 * @return
	 * @throws Exception 
	 */
	public UserInfo findUser(String userId);
	
	/**
	 * 修改用户信息，若用户信息不存在，则新建用户信息并保存
	 * @param userinfo
	 * @return UserDetailInfo 用户信息
	 */
	public UserDetailInfo update(UserDetailInfo userInfo);
}
