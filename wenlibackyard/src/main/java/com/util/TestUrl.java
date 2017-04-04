package com.util;

import com.constant.WenlibackyardConstant;

public class TestUrl {
	public static void main(String[] args) {
		System.out.println(HttpsUtil.AuthLogin(WenlibackyardConstant.VALIDATE_URL, "toProductAdd"));
	}
}
