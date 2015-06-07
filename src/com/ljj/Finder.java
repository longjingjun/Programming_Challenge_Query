package com.ljj;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

public class Finder {
	private static String filePath = "D:\\TDrive\\workspace_research\\Programing_Chal\\data\\linkMap.properties";
	private static final String LINE_SEPARATOR = System
			.getProperty("line.separator");

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String Key = "K-0.98876371ss90223257-0";
			Date date1 = new Date();
			String value = findPathByKey2(Key);
			Date date2 = new Date();

			long dif = date2.getTime() - date1.getTime();
			System.out.println("Key:" + Key + ", Value:" + value);
			System.out.println("date1 - date2 = " + dif + "(Milliseconds )");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Find Path by Key
	 * 
	 * @param key 
	 * @return Corresponding value of the key. Return null if the key is not found.
	 * 
	 * */
	public static String findPathByKey1(String key) throws Exception {
		String value = null;

		Properties props = new Properties();
		InputStream in = new BufferedInputStream(new FileInputStream(filePath));
		props.load(in);

		Enumeration en = props.propertyNames();
		while (en.hasMoreElements()) {
			String k = (String) en.nextElement();
			String property = props.getProperty(k);
			if (k.equals(key)) {
				value = property;
			}
		}

		return value;
	}

	public static String findPathByKey2(String key) throws Exception {
		String value = null;

		Properties props = new Properties();
		InputStream in = new BufferedInputStream(new FileInputStream(filePath));
		props.load(in);

		value = props.getProperty(key);
		return value;
	}

}
