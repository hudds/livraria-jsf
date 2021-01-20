package br.com.alura.livraria.bean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.alura.livraria.exception.EmailAlreadyRegisteredException;
import br.com.alura.livraria.model.User;
import br.com.alura.livraria.service.UserService;
import br.com.alura.livraria.util.Messages;

@Named("userMB")
@RequestScoped
public class UserMB implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private User user;
	@Inject
	private UserService userService;

	public String persist(){
		try {
			userService.insert(user);
		} catch (EmailAlreadyRegisteredException e) {
			Messages.addError("formCadastro:email", "Já existe um cliente cadastrado com este email", null);
			return "";
		}
		clear();
		return new Redirection("login").toString();
	}
	
	private void clear() {
		this.user = new User();
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
