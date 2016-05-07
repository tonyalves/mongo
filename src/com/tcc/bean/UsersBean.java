package com.tcc.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.tcc.dao.UserDAO;
import com.tcc.model.User;

@ManagedBean
@ViewScoped
public class UsersBean {
	private List<User> users;
	private User us;
	private UserDAO dao = new UserDAO();
	
	@PostConstruct
	public void init(){
		us = new User();
		users = dao.getAllUsers(100);
	}

	public void createUser(){
		dao.insertUser(us);
		users = dao.getAllUsers(100);
	}
	
	public void deleteUser(User user){
		dao.deleteUser(user);
		users = dao.getAllUsers(100);
	}
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User getUs() {
		return us;
	}

	public void setUs(User us) {
		this.us = us;
	}
	
}
