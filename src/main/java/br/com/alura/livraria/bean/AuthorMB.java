package br.com.alura.livraria.bean;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.alura.livraria.model.Author;
import br.com.alura.livraria.model.Book;
import br.com.alura.livraria.service.AuthorService;
import br.com.alura.livraria.service.BookService;
import br.com.alura.livraria.util.Messages;

@Named("authorMB")
@SessionScoped
public class AuthorMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Author author;

	@Inject
	private AuthorService authorService;

	@Inject
	private BookService bookDAO;

	private Integer authorId;

	public void loadAuthorById() {
		this.author = this.authorService.findById(authorId);
	}

	public void persist() {
		authorService.insertOrUpdate(author);
		clear();

	}

	public void remove(Author author) {
		List<Book> authorBooks = bookDAO.findByAuthorId(author.getId());
		if (authorBooks == null || authorBooks.isEmpty()) {
			authorService.deleteById(author.getId());
			return;
		}
		String msg = "Não é possível remover este autor porque";
		msg += authorBooks.size() > 1 ? " os seguintes livros estão registrados com ele:" : " o seguinte livro está registrado com ele:";
		msg += " " + String.join(", ", authorBooks.stream().map(Book::getTitle).collect(Collectors.toList()));
		Messages.addError(null, msg, null);

	}

	public void loadAuthor(Author author) {
		System.out.println("Loading author: " + author.getName());
		System.out.println("Books with author " + author.getName() + ":" + bookDAO.findByAuthorId(author.getId()));
		this.author = authorService.findById(author.getId());
	}

	public List<Author> getAuthors() {
		List<Author> authors = authorService.getAll();
		return authors;
	}

	public void clear() {
		author = new Author();
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

}
