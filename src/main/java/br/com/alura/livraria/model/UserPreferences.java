package br.com.alura.livraria.model;

import java.io.Serializable;

public class UserPreferences implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String theme = "aristo";

	public String getTheme() {
		return theme;
	}

	public void setTheme(String chosenTheme) {
		this.theme = chosenTheme;
	}
	
	

}
