package br.com.alura.livraria.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Book implements Serializable, Identifiable<Integer>{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String title;
	private String isbn;
	private BigDecimal price;
	private LocalDateTime publishDate;
	@ManyToMany
	private Set<Author> authors;
	@Override
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String titulo) {
		this.title = titulo;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal preco) {
		this.price = preco;
	}
	public LocalDateTime getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(LocalDateTime dataLancamento) {
		this.publishDate = dataLancamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Set<Author> getAuthors() {
		return this.authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	public void removeAuthor(Author author) {
		if(this.authors != null) {
			this.authors.remove(author);
		}
	}
	
	public void removeAuthors(Author ... authors) {
		if(this.authors != null) {
			for(Author author : authors) {			
				this.authors.remove(author);
			}
		}
	}
	
	public void addAuthors(Author... authors) {
		if(this.authors == null) {
			this.authors = new HashSet<>();
		}
		this.authors.addAll(Set.of(authors));
	}
	
}
