package com.constant;

/**
 * 文理后院常量类
 * @author: ZeralZhang
 * @date: 2016年12月29日
 */
public class WenlibackyardConstant {
	/**
	 * 服务器地址
	 * 授权验证要求地址必须为网站，不能为ip
	 */
	public static final String SERVER_URL = "https://42.96.153.31/";
	/**
	 * 用户认证地址
	 */
	public static final String VALIDATE_URL = SERVER_URL+"wenlibackyard/validateUser";
	 /**
	 * 微信token
	 */
	public static final String ACCESS_TOKEN = "accessToken";
	/**
	 * 凭证超时时间
	 */
	public static final String TOKEN_EXPIREIN_TIME = "tokenExpiresInTime";
}
