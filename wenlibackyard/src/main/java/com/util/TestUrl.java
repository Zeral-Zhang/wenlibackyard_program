package com.util;

public class TestUrl {
	public static void main(String[] args) {
		String original = "https://wenlibackyard.tunnel.qydev.com/wenlibackyard/validateUser";
		System.out.println(HttpsUtil.AuthLogin(original, "toProductList"));
	}
}
