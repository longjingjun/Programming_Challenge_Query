package com.ljj;

import java.io.File;
import java.io.FileWriter;
import java.util.Date;

/**
 * Use to create files
 * */
public class FileBuilder {

	private static String filePath = "D:\\TDrive\\workspace_research\\Programing_Chal\\data\\linkMap.properties";
	private static final String LINE_SEPARATOR = System
			.getProperty("line.separator");

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			buildFile();
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
		

		FileWriter fileWritter = new FileWriter(filePath, true);

		for (int i = 0; i < 100000; i++) {
			double num = Math.random();
			String key = "K-" + num + "-" + i;
			String value = "/V-" + num + "/CurrentTile:"
					+ (new Date().getTime());
			String content = key + "=" + value + LINE_SEPARATOR;
			// System.out.print(content);
			fileWritter.write(content);
		}

		fileWritter.flush();
		fileWritter.close();
		System.out.println("Done");
	}
}
