package com.util;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bean.AccessToken;
import com.bean.Button;
import com.bean.CommenButton;
import com.bean.ComplexButton;
import com.bean.Menu;

/**
 * @author Zeral_Zhang
 *
 */
public class MenuCreateUtil {
	private static final Logger log = LoggerFactory
			.getLogger(MenuCreateUtil.class);

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
				log.info("菜单创建成功！");
			} else {
				log.info("菜单创建失败，错误码：{}", result);
			}
		}

	}

	private static Menu getMenu() {
		CommenButton btn11 = new CommenButton();
		btn11.setName("旧物出售");
		btn11.setType("click");
		btn11.setKey("11");

		CommenButton btn12 = new CommenButton();
		btn12.setName("分类查询");
		btn12.setType("click");
		btn12.setKey("12");

		CommenButton btn13 = new CommenButton();
		btn13.setName("进入后院");
		btn13.setType("click");
		btn13.setType("view");
		btn13.setUrl("http://wenlibackyard.ngrok.natapp.cn/wenlibackyard/list.jsp");

		CommenButton btn21 = new CommenButton();
		btn21.setName("我的订单");
		btn21.setType("click");
		btn21.setKey("21");

		CommenButton btn22 = new CommenButton();
		btn22.setName("我的收藏");
		btn22.setType("click");
		btn22.setKey("22");

		CommenButton btn23 = new CommenButton();
		btn23.setName("发布商品");
		btn23.setType("click");
		btn23.setKey("23");

		CommenButton btn31 = new CommenButton();
		btn31.setName("团队介绍");
		btn31.setType("click");
		btn31.setKey("31");

		CommenButton btn32 = new CommenButton();
		btn32.setName("意见建议");
		btn32.setType("click");
		btn32.setKey("32");

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("文理后院");
		mainBtn1.setSub_button(new CommenButton[] { btn11, btn12, btn13 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("我的后院");
		mainBtn2.setSub_button(new CommenButton[] { btn21, btn22, btn23 });

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("关于我们");
		mainBtn3.setSub_button(new CommenButton[] { btn31, btn32 });

		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });
		return menu;
	}

}
