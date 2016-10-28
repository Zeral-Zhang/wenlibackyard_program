package com.util;

public class TestUrl {
	public static void main(String[] args) {
		String original = "http://wenlibackyard.tunnel.qydev.com/wenlibackyard/validateUser";
		System.out.println(HttpsUtil.urlEncodeUTF8(original));
	}
}
