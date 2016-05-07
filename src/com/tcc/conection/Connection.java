package com.tcc.conection;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class Connection {
	
	private static MongoClient mongoClient;

	private Connection(){
	}
	
	public static MongoDatabase getConnection(){
		mongoClient = new MongoClient();
		MongoDatabase db = mongoClient.getDatabase("test");
		return db;
	}
}
