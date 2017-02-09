package com.biz;

import com.po.UserDetailInfo;
import com.po.UserInfo;

public interface IUserBiz {
	/**
	 * 通过openId查找用户详情
	 * @param openId
	 * @return
	 */
	public UserDetailInfo findUserDetail(String openId);
	
	/**
	 * 修改用户信息，若用户信息不存在，则新建用户信息并保存
	 * @param userinfo
	 * @return UserDetailInfo 用户信息
	 */
	public UserDetailInfo update(UserDetailInfo userInfo);
	
	/**
	 * 通过openId查找用户
	 * @param openId
	 * @return
	 */
	public UserInfo findByOpenId(String openId);
}
