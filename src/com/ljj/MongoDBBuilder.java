package com.ljj;

import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoDBBuilder {

	private static int size = 1000000;

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
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		DB db = mongoClient.getDB("test");

		DBCollection coll = db.getCollection("challenge");

		for (int i = 0; i < size; i++) {
			double num = Math.random();
			String key = "K-" + num + "-" + i;
			String value = "/V-" + num + "/CurrentTile:"
					+ (new Date().getTime());

			BasicDBObject doc = new BasicDBObject("key", key).append("value",
					value);
			coll.insert(doc);
			if(i%51 == 0){
				System.out.println("key:" + key + " value:" + value);
			}
		}

		System.out.println("Done");
	}

}
