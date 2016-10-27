package com.test;

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

	public int printInt(String str) {
		System.out.println(str);
		return -2;
	}
}
