package br.com.alura.livraria.bean;

import static br.com.alura.livraria.security.SecurityPaths.DEFAULT_PATH_AFTER_LOGIN;
import static br.com.alura.livraria.session.SecuritySessionAttributesNames.LAST_PATH_UNAUTHORIZED;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.alura.livraria.model.User;
import br.com.alura.livraria.service.LoginService;
import br.com.alura.livraria.session.SessionAttributes;
import br.com.alura.livraria.util.Messages;

@Named("loginMB")
@RequestScoped
public class LoginMB implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private User user;
	@Inject
	private LoginService loginService;
	@Inject
	private SessionAttributes sessionAttributes;
	
	public String login() {
		if(loginService.tryLogin(user)) {
			String redirectPath = (String) sessionAttributes.getAnyAttribute(LAST_PATH_UNAUTHORIZED);
			if(redirectPath == null) {
				redirectPath = DEFAULT_PATH_AFTER_LOGIN;
			}
			return new Redirection(redirectPath).toString();
		}
		Messages.addError("formLogin", "E-mail ou senha inválidos.", null);
		return new Redirection("").toString();
	}
	
	public String logout() {
		loginService.tryLogout();
		return new Redirection("/login.xhtml").toString();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
