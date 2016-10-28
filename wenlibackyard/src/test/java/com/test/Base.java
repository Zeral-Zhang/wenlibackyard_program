package com.test;

import java.util.Arrays;

import org.junit.Test;

public class Base {

	private static int baseStaticInt = 3;

	{
		System.out.println("Base Static Block");
	}
	private int baseVar = printInt("BaseVar");

	public Base() {
		printInt("Base constructor");
		printInt(" baseVar " + baseVar + " baseStaticInt " + baseStaticInt);
	}

	public static int print(String str) {
		System.out.println(str);
		return 1;
	}

	public int printInt(String str) {
		System.out.println(str);
		return -1;
	}

	public static void main(String[] args) {
		new Child();
	}
	
	@Test
	public void test1() {
		String s = "1627207:3232483:1627207:3232484:";
		System.out.println(Arrays.asList(s.substring(0, s.length() - 1).split(":")).toString());
	}
}

class Child extends Base {
	private static int childStaticInt = print("ChildStaticInt") + 1;

	private static int baseStaticInt = print("Child baseStaticInt");

	{
		System.out.println("Child Block");
	}

	private int baseVar = printInt("Child BaseVar");

	private int childVar = printInt("ChildVar") + 1;

	public Child() {
		printInt(" Base constructor");
		printInt(" Child baseVar " + baseVar + " Child baseStaticInt " + baseStaticInt + " childStaticInt "
				+ childStaticInt + " childVar " + childVar);
	}

	public static int print(String str) {
		System.out.println(str);
		return 2;
	}

	@Override
	public int printInt(String str) {
		System.out.println(str);
		return -2;
	}
}
