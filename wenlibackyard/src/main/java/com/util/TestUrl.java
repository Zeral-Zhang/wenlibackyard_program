package com.util;

public class TestUrl {
	public static void main(String[] args) {
		String original = "http://wenlibackyard.ngrok.natapp.cn/wenlibackyard/validate_User";
		System.out.println(HttpsUtil.urlEncodeUTF8(original));
	}
}
