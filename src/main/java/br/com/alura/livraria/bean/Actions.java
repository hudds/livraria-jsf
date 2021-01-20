package br.com.alura.livraria.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("actions")
@RequestScoped
public class Actions {

	public String redirectAuthorForm() {
		return "autor?faces-redirect=true";
	}
	
	public String redirectBookForm() {
		return "livro?faces-redirect=true";
	}
	
	public String redirection(String url) {
		return new Redirection(url).toString();
	}
	
	
}
