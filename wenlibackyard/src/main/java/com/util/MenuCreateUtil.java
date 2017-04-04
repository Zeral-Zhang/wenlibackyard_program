package com.util;

import java.util.Properties;

import org.apache.log4j.Logger;

import com.bean.AccessToken;
import com.bean.Button;
import com.bean.CommenButton;
import com.bean.ComplexButton;
import com.bean.Menu;
import com.constant.WenlibackyardConstant;

/**
 * @author Zeral_Zhang
 *
 */
public class MenuCreateUtil {
	private final static Logger log = Logger.getLogger(MenuCreateUtil.class);
	
	public static void main(String[] args) {
		Properties prop = PropertiesConfigUtil
				.getProperties("account.properties");
		// 调用接口获取 access_token
		AccessToken at = HttpsUtil.getAccessToken(prop.getProperty("appid"),
				prop.getProperty("appsecret"));

		if (null != at) {
			// 调用接口创建菜单
			int result = HttpsUtil.createMenu(getMenu(), at.getToken());

			// 判断菜单创建结果
			if (0 == result) {
				System.out.println("菜单创建成功！");
				log.info("菜单创建成功！");
			} else {
				System.out.println("菜单创建失败！");
				log.info("菜单创建失败,错误码："+result);
			}
		}

	}

	private static Menu getMenu() {
		CommenButton btn11 = new CommenButton();
		btn11.setName("旧物出售");
		btn11.setType("click");
		btn11.setType("view");
		btn11.setUrl(WenlibackyardConstant.SERVER_URL+"wenlibackyard/toProductAdd");

		CommenButton btn12 = new CommenButton();
		btn12.setName("学院查询");
		btn12.setType("click");
		btn12.setType("view");
		btn12.setUrl(WenlibackyardConstant.SERVER_URL+"wenlibackyard/toDiscovery");

		CommenButton btn13 = new CommenButton();
		btn13.setName("进入后院");
		btn13.setType("click");
		btn13.setType("view");
		btn13.setUrl(WenlibackyardConstant.SERVER_URL+"wenlibackyard/toProductList");

		CommenButton btn21 = new CommenButton();
		btn21.setName("我的订单");
		btn21.setType("click");
		btn21.setType("view");
		btn21.setUrl(WenlibackyardConstant.SERVER_URL+"wenlibackyard/toUserPayed");

		CommenButton btn22 = new CommenButton();
		btn22.setName("我的旧物");
		btn22.setType("click");
		btn22.setType("view");
		btn22.setUrl(WenlibackyardConstant.SERVER_URL+"wenlibackyard/toUserSaling");
		
		CommenButton btn23 = new CommenButton();
		btn23.setName("我的购物车");
		btn23.setType("click");
		btn23.setType("view");
		btn23.setUrl(WenlibackyardConstant.SERVER_URL+"wenlibackyard/shopCar");

		CommenButton btn31 = new CommenButton();
		btn31.setName("团队介绍");
		btn31.setType("click");
		btn31.setKey("31");

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("文理后院");
		mainBtn1.setSub_button(new CommenButton[] { btn11, btn12, btn13 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("我的后院");
		mainBtn2.setSub_button(new CommenButton[] { btn21, btn22, btn23 });

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("关于我们");
		mainBtn3.setSub_button(new CommenButton[] { btn31 });

		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });
		return menu;
	}

}
