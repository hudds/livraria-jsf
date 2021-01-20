package br.com.alura.livraria.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.alura.livraria.model.Author;
import br.com.alura.livraria.model.Book;
import br.com.alura.livraria.model.BookDataModel;
import br.com.alura.livraria.service.AuthorService;
import br.com.alura.livraria.service.BookService;
import br.com.alura.livraria.util.Messages;

@Named("bookMB")
@ViewScoped
public class BookMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Book book;
	
	private List<Book> books;

	private Integer idAuthor;

	@Inject
	private BookService bookService;
	@Inject
	private AuthorService authorService;
	@Inject
	private BookDataModel bookDataModel;
	
	private Integer bookId;

	public void loadBookById() {
		this.book = this.bookService.findByIdWithAuthors(bookId);
	}
	
	public void persist() {
		if (book.getAuthors() == null || book.getAuthors().isEmpty()) {
			Messages.addError("author", "O livro deve ter pelo menos 1 autor.", null);
			return;
		}
		bookService.insertOrUpdate(book);
		clear();
	}
	
	public void loadBook(Book book) {
		System.out.println("Loading book");
		this.book = bookService.findByIdWithAuthors(book.getId());
	}
	
	public void remove(Book book) {
		System.out.println("Removing book");
		bookService.deleteById(book.getId());
		clearCache();
	}

	public void addAuthor() {
		Author author = authorService.findById(getIdAuthor());
		book.addAuthors(author);
	}
	
	private void clearCache() {
		this.books = null;
	}

	public List<Book> getBooks() {
		if(books == null) {
			books = bookService.getAll();
		}
		return books;
	}

	public Set<Author> getAuthors() {
		return book.getAuthors();
	}

	private void clear() {
		book = new Book();
		clearCache();
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book livro) {
		this.book = livro;
	}

	public Integer getIdAuthor() {
		return idAuthor;
	}

	public void setIdAuthor(Integer idAuthor) {
		this.idAuthor = idAuthor;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public BookDataModel getBookDataModel() {
		return bookDataModel;
	}

}
