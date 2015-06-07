package com.ljj;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;

public class RamdomFinder {
	private static String filePath = "D:\\TDrive\\workspace_research\\Programing_Chal\\data\\linkMapRandom.properties";
	private static final String LINE_SEPARATOR = System
			.getProperty("line.separator");
	private static final int LINE_LENGTH = 100;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String Key = "K-0.6496075828318547-900000";
			Date date1 = new Date();
			String value = findPathByKey(Key);
			Date date2 = new Date();

			long dif = date2.getTime() - date1.getTime();
			System.out.println("Key:" + Key + ", Value:" + value);
			System.out.println("date1 - date2 = " + dif + "(Milliseconds )");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String findPathByKey(String key) throws Exception {
		String value = null;

		RandomAccessFile randomFile = new RandomAccessFile(filePath, "r");
		int size = 0;

		// get size
		randomFile.seek(0);
		String sizeString = readString(randomFile, LINE_LENGTH);
		sizeString = sizeString.trim();
		size = Integer.parseInt(sizeString);

		// find
		int low = 1;
		int high = size;
		while (low <= high) {
			int middle = (low + high) / 2;
			randomFile.seek(middle * (LINE_LENGTH + 2));
			String content = readString(randomFile, LINE_LENGTH);
			content = content.trim();
			String tempKey = content.substring(0, content.indexOf("="));
			if (key.equals(tempKey)) {
				value = content.substring(content.indexOf("=") + 1);
				break;
			} else if (key.compareTo(tempKey) > 0) {
				low = middle + 1;
			} else {
				high = middle - 1;
			}
		}

		randomFile.close();
		return value;
	}

	public static String readString(RandomAccessFile file, int fieldLength)
			throws IOException {
		String name = file.readUTF();
		int length = name.getBytes("UTF-8").length;
		file.skipBytes(fieldLength - length);
		return name;
	}

}
