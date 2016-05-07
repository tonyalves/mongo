package com.tcc.bean;

import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.bson.Document;

import com.tcc.dao.RestaurantDAO;
import com.tcc.model.Restaurant;

@ManagedBean
@ViewScoped
public class RestaurantBean {
	private List<Restaurant> restaurants;
	private String searchName;
	private RestaurantDAO restDao;
	@PostConstruct
	public void init(){
		restDao = new RestaurantDAO();
		restaurants = restDao.getAllDocuments(100);
	}
	
	public void findByName(){
		String searchRegex = "";
		if(searchName == null || !searchName.isEmpty() )
			searchRegex = "/*"+searchName+"*/";
		Document document = new Document("name", Pattern.compile(searchRegex));
		restaurants = restDao.getDocumentsByCriteria(document, 100);	
	}
	public List<Restaurant> getRestaurants() {
		return restaurants;
	}
	
	public String getSearchName() {
		if(searchName == null)
			searchName = "";
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}	
}
