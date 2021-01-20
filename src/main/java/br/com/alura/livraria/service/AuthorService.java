package br.com.alura.livraria.service;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.alura.livraria.dao.AuthorDAO;
import br.com.alura.livraria.dao.GenericDAO;
import br.com.alura.livraria.model.Author;

@Transactional
public class AuthorService extends BasicCRUDService<Author, Integer> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private AuthorDAO authorDAO;
		
	@Override
	protected GenericDAO<Author, Integer> getDAO() {
		return authorDAO;
	}

}
