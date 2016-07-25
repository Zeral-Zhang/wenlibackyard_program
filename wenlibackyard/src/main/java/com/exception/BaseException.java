package com.exception;

public class BaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BaseException(Throwable root) {
		super(root);
	}

	public BaseException(String string, Throwable root) {
		super(string, root);
	}

	public BaseException(String s) {
		super(s);
	}
}
