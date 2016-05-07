package com.tcc.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.tcc.dao.UserDAO;
import com.tcc.model.User;

@ManagedBean
@ViewScoped
public class UserLoginView {
	private User us = new User();
	
	private UserDAO dao;

	public String login(){
		dao = new UserDAO();
		boolean userOk = dao.authenticate(us);
		if(userOk)
			return "index.xhtml?faces-redirect=true";
		FacesContext.getCurrentInstance()
		.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção", "Usuário ou senha inválido."));
		return "";
	}
	
	public User getUs() {
		
		return us;
	}

	public void setUs(User us) {
		this.us = us;
	}
	
	
}
