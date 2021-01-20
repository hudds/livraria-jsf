package br.com.alura.livraria.session;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.alura.livraria.model.User;
import br.com.alura.livraria.model.UserPreferences;

@Named("userSession")
@SessionScoped
public class UserSession implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private User user;
	
	private final UserPreferences userPreferences = new UserPreferences();
	
	public boolean isAuthenticated() {
		return this.user != null;
	}
	
	public void setUser(User user) {
		if(user.getEmail() != null) {
			this.user = user;
		}
	}

	public String getUserEmail() {
		return this.user.getEmail();
	}
	
	public boolean removeUser() {
		boolean r = this.isAuthenticated();
		this.user = null;
		return r;
	}

	public UserPreferences getUserPreferences() {
		return userPreferences;
	}
}
