package br.com.alura.livraria.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.alura.livraria.model.Book;

public class BookDAO extends GenericDAO<Book, Integer>{

	private static final long serialVersionUID = 1L;

	public BookDAO() {
		super(Book.class);
	}
	
	public List<Book> findByAuthorId(Integer authorId){
		String jpql = "select b from " + Book.class.getSimpleName() + " b right join fetch b.authors a where a.id = :pId ";
		TypedQuery<Book> query = getEntityManager().createQuery(jpql, Book.class);
		query.setParameter("pId", authorId);
		query.setHint("org.hibernate.cacheable", true);
		
		List<Book> resultList = query.getResultList();
		
		// for some reason hibernate is returning a List with a single null value when no results are found 
		// this piece of code checks if the result list contains only a null value, if so, returns null
		if(resultList.size() == 1) {
			return resultList.get(0) == null ? null : resultList;
		}
		return resultList;
	}

}
