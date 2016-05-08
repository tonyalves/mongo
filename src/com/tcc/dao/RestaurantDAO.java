package com.tcc.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

import com.tcc.conection.Connection;
import com.tcc.model.Restaurant;

public class RestaurantDAO {
	private MongoDatabase db = Connection.getConnection();
	
	public List<Restaurant> getAllDocuments(int limit){
		FindIterable<Document> it = db.getCollection("restaurants").find().sort(new Document("name", -1)).limit(limit);
		List<Restaurant> rests = new ArrayList<>();
		it.forEach(new Block<Document>() {
			@Override
			public void apply(final Document t) {
				Restaurant rest = new Restaurant();
	
				rest.setAddressBuilding((String) ((Document) t.get("address")).getString("building"));
				rest.setAddressStreet((String) ((Document) t.get("address")).getString("street"));
				rest.setAddressZipCode((String) ((Document)t.get("address")).getString("zipcode"));
				rest.setBorough((String) t.get("borough"));
				rest.setCuisine((String) t.get("cuisine"));
				
				rest.setName(t.get("name").toString());
				
				rests.add(rest);
			}
		});
		
		return rests;
	}
	
	
	public List<Restaurant> getDocumentsByCriteria(Document document, int limit){
		FindIterable<Document> it = db.getCollection("restaurants").find(document).limit(limit);
		List<Restaurant> rests = new ArrayList<>();
		it.forEach(new Block<Document>() {
			@Override
			public void apply(final Document t) {
				Restaurant rest = new Restaurant();
				
				rest.setAddressBuilding((String) ((Document) t.get("address")).getString("building"));
				rest.setAddressStreet((String) ((Document) t.get("address")).getString("street"));
				rest.setAddressZipCode((String) ((Document)t.get("address")).getString("zipcode"));
				rest.setBorough((String) t.get("borough"));
				rest.setCuisine((String) t.get("cuisine"));
				
				rest.setName(t.get("name").toString());
				
				rests.add(rest);
				
			}
		});
		return rests;
	}
	
	public void updateRestaurant(Document criteria, Document toUpdate){
		db.getCollection("restaurants").updateMany(criteria, new Document("$set", toUpdate));
	}
	
	public void replaceRestaurant(Document criteria, Document toReplace){
		db.getCollection("restaurants").replaceOne(criteria, toReplace);
	}
	
	public void deleteRestaurant(Document criteria){
		db.getCollection("restaurants").deleteMany(criteria);
	}
	
	public void insertRestaurant(Document t){
		db.getCollection("restaurants").insertOne(t);
	}
	
}
