package com.ljj;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Use to create files
 * */
public class RamdomFileBuilder {

	private static String filePath = "D:\\TDrive\\workspace_research\\Programing_Chal\\data\\linkMapRandom.properties";
	private static final String LINE_SEPARATOR = System
			.getProperty("line.separator");

	private static final int LINE_LENGTH = 100;

	private static int size = 1000000;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			buildFile();
			// readFile();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void buildFile() throws Exception {
		File file = new File(filePath);
		if (file.exists()) {
			file.delete();
			file.createNewFile();
		} else {
			file.createNewFile();
		}

		SortedMap<String, String> data = new TreeMap<String, String>();

		RandomAccessFile randomFile = new RandomAccessFile(filePath, "rw");
		randomFile.setLength((size + 1) * (LINE_LENGTH + 2)); // +1 is because
																// we put
		// the data number in
		// the first line.
		writeString(randomFile, (size + LINE_SEPARATOR), LINE_LENGTH);

		for (int i = 0; i < size; i++) {
			double num = Math.random();
			String key = "K-" + num + "-" + i;
			String value = "/V-" + num + "/CurrentTile:"
					+ (new Date().getTime());
			data.put(key, value);
			if (i % 100000 == 0) {
				System.out.println("i: " + i + " key:" + key + " value:"
						+ value);
			}
		}

		Set<Entry<String, String>> entry1 = data.entrySet();
		Iterator<Entry<String, String>> it = entry1.iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			String content = entry.getKey() + "=" + entry.getValue()
					+ LINE_SEPARATOR;
			writeString(randomFile, content, LINE_LENGTH);
		}
		randomFile.close();

		System.out.println("Done");
	}

	public static void writeString(RandomAccessFile file, String str,
			int fieldLength) throws IOException {
		file.writeUTF(str);
		int length = str.getBytes("UTF-8").length;
		file.skipBytes(fieldLength - length);
	}

	// public static void readFile() throws Exception {
	// RandomAccessFile randomFile = new RandomAccessFile(filePath, "r");
	// randomFile.seek(10 * (LINE_LENGTH + 2));
	//
	// String content = readString(randomFile, LINE_LENGTH);
	// System.out.println(content);
	// randomFile.close();
	//
	// }
	//
	// public static String readString(RandomAccessFile file, int fieldLength)
	// throws IOException {
	// String name = file.readUTF();
	// int length = name.getBytes("UTF-8").length;
	// file.skipBytes(fieldLength - length);
	// return name;
	// }
}
