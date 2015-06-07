package com.ljj;

import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class MongoDBFinder {
	private static MongoClient mongoClient;
	private static DB db;
	private static DBCollection coll;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		try {
			mongoClient = new MongoClient("localhost", 27017);
			db = mongoClient.getDB("test");
			coll = db.getCollection("challenge");

			
			String Key = "K-0.1406674169933615-998835";
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

	/**
	 * Find Path by Key
	 * 
	 * @param key
	 * @return Corresponding value of the key. Return null if the key is not
	 *         found.
	 * 
	 * */
	public static String findPathByKey(String key) throws Exception {
		String value = null;
		
				
		BasicDBObject query = new BasicDBObject("key", key);

		DBCursor cursor = coll.find(query);

		try {
		   while(cursor.hasNext()) {
		       System.out.println(cursor.next());
		   }
		} finally {
		   cursor.close();
		}
		
		return value;
	}
}
