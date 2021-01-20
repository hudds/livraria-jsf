package br.com.alura.livraria.dao;

import br.com.alura.livraria.model.User;

public class UserDAO extends GenericDAO<User, Integer> {

	private static final long serialVersionUID = 1L;

	public UserDAO() {
		super(User.class);
	}

}
