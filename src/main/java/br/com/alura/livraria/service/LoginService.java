package br.com.alura.livraria.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.alura.livraria.model.User;
import br.com.alura.livraria.session.UserSession;

public class LoginService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UserSession userSession;
	
	@Inject
	private UserService userService;
	
	public boolean tryLogin(User user) {
		if(userService.credentialsAreCorrect(user)) {
			this.userSession.setUser(user);
			return true;
		}
		return false;
	}
	
	public boolean tryLogout() {
		return this.userSession.removeUser();
	}

}
