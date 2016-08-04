package com.util;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class ResourceUtil {
	public static InputStream getResourceAsStream(String name) {
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
	}
	
	public static URL getResource(String name) {
		return Thread.currentThread().getContextClassLoader().getResource(name);
	}
	
	public static void close(OutputStream... outputStreams) {
		for (OutputStream outputStream : outputStreams) {
			if(outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void close(InputStream...inputStreams) {
		for (InputStream inputStream : inputStreams) {
			if(inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
