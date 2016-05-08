package com.tcc.model;

import java.io.Serializable;

import org.bson.Document;

public class Restaurant implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7260166447419288945L;
	
	private String addressBuilding;
	private String addressStreet;
	private String addressZipCode;
	private String borough;
	private String cuisine;
	
	private String name;
	private String restaurant_id;
	
	
	
	public String getAddressBuilding() {
		return addressBuilding;
	}
	public void setAddressBuilding(String addressBuilding) {
		this.addressBuilding = addressBuilding;
	}
	public String getAddressStreet() {
		return addressStreet;
	}
	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}
	public String getAddressZipCode() {
		return addressZipCode;
	}
	public void setAddressZipCode(String addressZipCode) {
		this.addressZipCode = addressZipCode;
	}
	public String getBorough() {
		return borough;
	}
	public void setBorough(String borough) {
		this.borough = borough;
	}
	public String getCuisine() {
		return cuisine;
	}
	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRestaurant_id() {
		return restaurant_id;
	}
	public void setRestaurant_id(String restaurant_id) {
		this.restaurant_id = restaurant_id;
	}
	
	public Document getDocument(){
		Document document = new Document();
		document.append("name", getName());
		document.append("address", 
								new Document("street", getAddressStreet())
						);
		document.append("borough", getBorough());
		document.append("cuisine", getCuisine());
		
		return document;
	}
	
	@Override
	public String toString() {
		StringBuilder bd = new StringBuilder("Dados do Restaurante").append("\n");
		
		bd.append("Nome: ").append(getName());
		bd.append("\n");
		bd.append("Endereço: ").append(getAddressStreet())
					.append(",")
					.append(getAddressBuilding())
					.append(" - Cod Postal: ")
					.append(addressZipCode);
		bd.append("\n");
		bd.append("Cidade: ").append(getBorough());
		bd.append("\n");
		bd.append("Cozinha: ").append(getCuisine());
		bd.append("\n");
		
		return bd.toString();
	}

}
