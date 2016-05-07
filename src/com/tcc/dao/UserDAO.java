package com.tcc.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.tcc.conection.Connection;
import com.tcc.model.User;

public class UserDAO {
	private MongoDatabase db = Connection.getConnection();
	
	public boolean authenticate(User us){
		Document d = new Document();
		d.put("name", us.getName());
		d.put("password", us.getPassword());
		
		FindIterable<Document> result = db.getCollection("users").find(d);
		if(result.first() == null)
			return false;
		
		return true;
	}
	
	public void insertUser(User us){
		Document d = new Document();
		d.put("name", us.getName());
		d.put("password", us.getPassword());
		
		db.getCollection("users").insertOne(d);
	}
	
	public List<User> getAllUsers(int limit){
		FindIterable<Document> it = db.getCollection("users").find().sort(new Document("name", -1)).limit(limit);
		List<User> users = new ArrayList<>();
		it.forEach(new Block<Document>() {
			@Override
			public void apply(final Document t) {
				User us = new User();			
				us.setName(t.get("name").toString());
				
				users.add(us);
			}
		});
		
		return users;
	}

	public void deleteUser(User us) {
		Document d = new Document();
		d.put("name", us.getName());
		
		db.getCollection("users").deleteOne(d);
		
	}
}
