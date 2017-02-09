package com.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URL;
import java.util.Properties;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.bean.AccessToken;
import com.bean.Menu;
import com.bean.WeixinOauth2Token;
import com.po.UserInfo;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * @author Zeral_Zhang
 *
 */
public class HttpsUtil {
	private static Logger log = Logger.getLogger(HttpsUtil.class);
	public final static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	public final static String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	
	/**
	 * 获取创建菜单凭证
	 * @param appid
	 * @param appsecret
	 * @return
	 */
	public static AccessToken getAccessToken(String appid, String appsecret) {
		AccessToken accessToken = null;
		String requestUrl = ACCESS_TOKEN_URL.replace("APPID", appid).replace("APPSECRET", appsecret);
		JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);
		// 如果请求成功
		if(null != jsonObject) {
			try {
				accessToken = new AccessToken();
				accessToken.setToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
			} catch (JSONException e) {
				accessToken = null;
				// 获取token失败
				log.error("获取 token 失败 errcode："+jsonObject.getInt("errcode")+" errmsg: " + jsonObject.getString("errmsg"));
			}
		}
		return accessToken;
	}
	
	/**
     * 获取网页授权凭证
     * 
     * @param appId 公众账号的唯一标识
     * @param appSecret 公众账号的密钥
     * @param code
     * @return WeixinAouth2Token
     */
    public static WeixinOauth2Token getOauth2AccessToken(String appId, String appSecret, String code) {
        WeixinOauth2Token wat = null;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        requestUrl = requestUrl.replace("APPID", appId);
        requestUrl = requestUrl.replace("SECRET", appSecret);
        requestUrl = requestUrl.replace("CODE", code);
        // 获取网页授权凭证
        JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);
        if (null != jsonObject) {
            try {
                wat = new WeixinOauth2Token();
                wat.setToken(jsonObject.getString("access_token"));
                wat.setExpiresIn(jsonObject.getInt("expires_in"));
                wat.setRefreshToken(jsonObject.getString("refresh_token"));
                wat.setOpenId(jsonObject.getString("openid"));
                wat.setScope(jsonObject.getString("scope"));
            } catch (Exception e) {
                wat = null;
                int errorCode = jsonObject.getInt("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                log.error("获取网页授权凭证失败 errcode:" + errorCode + " errmsg:" + errorMsg);
            }
        }
        return wat;
    }
    
	/**
     * 授权登录
     * 
     * @param originUrl 原始地址
     * 
     */
    public static String AuthLogin(String originUrl, String redirectUrl) {
    	Properties prop = PropertiesConfigUtil.getProperties("account.properties");
        // 拼接请求地址
        String requestUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
        requestUrl = requestUrl.replace("APPID", prop.getProperty("appid"));
        requestUrl = requestUrl.replace("REDIRECT_URI", HttpsUtil.urlEncodeUTF8(originUrl));
        requestUrl = requestUrl.replace("STATE", redirectUrl);
        return requestUrl;
    }
    
	public static UserInfo getSNSUserInfo(String accessToken, String openId) {
    	UserInfo userInfo = null;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        // 通过网页授权获取用户信息
        JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);

        if (null != jsonObject) {
            try {
            	userInfo = new UserInfo();
                // 用户的标识
                userInfo.setUserId(jsonObject.getString("openid"));
                // 昵称
                userInfo.setUserNickName(jsonObject.getString("nickname"));
                // 用户头像
                userInfo.setUserHeadImgUrl(jsonObject.getString("headimgurl"));
                // 性别（1是男性，2是女性，0是未知）
                userInfo.setUserGender(jsonObject.getInt("sex"));
                // 用户所在省份
                userInfo.setUserProvince(StringUtils.isEmpty(jsonObject.getString("province")) ? null : jsonObject.getString("province"));
                // 用户所在城市
                userInfo.setUserCity(StringUtils.isEmpty(jsonObject.getString("city")) ? null : jsonObject.getString("city"));
                // 获取用户的语言
                userInfo.setUserLanguage(jsonObject.getString("language"));
            } catch (Exception e) {
            	userInfo = null;
                int errorCode = jsonObject.getInt("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                e.printStackTrace();
                log.error("获取用户信息失败 errcode:" + errorCode + " errmsg:{}" + errorMsg);
            }
        }
        return userInfo;
    }
	
	public static UserInfo getUserInfo(String accessToken, String openId) {
    	UserInfo userInfo = null;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        // 通过网页授权获取用户信息
        JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);

        if (null != jsonObject) {
            try {
            	userInfo = new UserInfo();
                // 用户的标识
                userInfo.setUserId(jsonObject.getString("openid"));
                // 昵称
                userInfo.setUserNickName(jsonObject.getString("nickname"));
                // 用户头像
                userInfo.setUserHeadImgUrl(jsonObject.getString("headimgurl"));
                // 性别（1是男性，2是女性，0是未知）
                userInfo.setUserGender(jsonObject.getInt("sex"));
                // 用户所在省份
                userInfo.setUserProvince(StringUtils.isEmpty(jsonObject.getString("province")) ? null : jsonObject.getString("province"));
                // 用户所在城市
                userInfo.setUserCity(StringUtils.isEmpty(jsonObject.getString("city")) ? null : jsonObject.getString("city"));
                // 获取用户的语言
                userInfo.setUserLanguage(jsonObject.getString("language"));
            } catch (Exception e) {
            	userInfo = null;
                int errorCode = jsonObject.getInt("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                e.printStackTrace();
                log.error("获取用户信息失败 errcode:" + errorCode + " errmsg:{}" + errorMsg);
            }
        }
        return userInfo;
    }
	
	public static int createMenu(Menu menu, String accessToken) {
		int result = 0;
		
		// 拼装创建菜单的 Url
		String url = MENU_CREATE_URL.replace("ACCESS_TOKEN", accessToken);
		// 将菜单对象转换成 json字符串
		String jsonMenu = JSONObject.fromObject(menu).toString();
		// 调用接口创建菜单
		JSONObject jsonObject = httpsRequest(url, "POST", jsonMenu);
		
		if(null != jsonObject) {
			if(0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
				log.error("创建菜单失败 errorcode:" + jsonObject.getInt("errcode") + " errmsg:" + jsonObject.getString("errmsg"));
			}
		}
		return result;
	}
	
	/**
	 * 将Https请求内容转化为JSON对象
	 * 
	 * @param requestUrl
	 * @param requestMethod
	 * @param outputStr
	 * @return
	 */
	public static JSONObject httpsRequest(String requestUrl,
			String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer strbuf = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");

			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			
			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式(GET/POST)
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod)) {
				httpUrlConn.connect();
			}

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				strbuf.append(str);
			}
			// 释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(strbuf.toString());
		} catch (ConnectException e) {
			log.error("连接超时：{}", e);
		} catch (Exception e) {
			log.error("https 请求异常:{}", e);
		}
		return jsonObject;
	}
	
	/**
     * URL编码（utf-8）
     * 
     * @param source
     * @return
     */
    public static String urlEncodeUTF8(String source) {
        String result = source;
        try {
            result = java.net.URLEncoder.encode(source, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
