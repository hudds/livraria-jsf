package br.com.alura.livraria.dao;

import br.com.alura.livraria.model.Author;

public class AuthorDAO extends GenericDAO<Author, Integer> {

	private static final long serialVersionUID = 5737899721908357845L;

	public AuthorDAO() {
		super(Author.class);
	}

}
