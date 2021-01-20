package br.com.alura.livraria.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.alura.livraria.dao.BookDAO;
import br.com.alura.livraria.dao.GenericDAO;
import br.com.alura.livraria.model.Book;

@Transactional
public class BookService extends BasicCRUDService<Book, Integer> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private BookDAO bookDAO;
		
	@Override
	protected GenericDAO<Book, Integer> getDAO() {
		return bookDAO;
	}

	@Override
	public Integer insert(Book book) {
		if(book.getAuthors() == null || book.getAuthors().isEmpty()) {
			throw new IllegalArgumentException("Book must have at least one author");
		}
		return super.insert(book);
	}
	
	@Override
	public void update(Book book) {
		if(book.getAuthors() == null || book.getAuthors().isEmpty()) {
			throw new IllegalArgumentException("Book must have at least one author");
		}
		super.update(book);
	}
	
	public List<Book> findByAuthorId(Integer id) {
		return this.bookDAO.findByAuthorId(id);
	}

	public Book findByIdWithAuthors(Integer bookId) {
		return this.getDAO().findById(bookId, "authors");
	}

}
