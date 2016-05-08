package com.tcc.bean;

import static java.util.Arrays.asList;

import java.util.List;

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
	private String searchText;
	private RestaurantDAO restDao;
	@PostConstruct
	public void init(){
		restDao = new RestaurantDAO();
		restaurants = restDao.getAllDocuments(100);
	}
	
	public void findByQuery(){
		String searchRegex = "";
		if(searchText != null || !searchText.isEmpty() )
			searchRegex = "/*"+searchText+"*/";
		
		Document document = new Document("$text", new Document("$search", searchRegex));
		restaurants = restDao.getDocumentsByCriteria(document, 100);	
	}
	public List<Restaurant> getRestaurants() {
		return restaurants;
	}
	
	public String getSearchName() {
		if(searchText == null)
			searchText = "";
		return searchText;
	}

	public void setSearchName(String searchName) {
		this.searchText = searchName;
	}

	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}


	
}
