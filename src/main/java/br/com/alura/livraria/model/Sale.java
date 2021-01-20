package br.com.alura.livraria.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Sale implements Identifiable<Long>, Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Book book;
	
	private Integer quantity = 1;
	
	public Sale() {
		
	}
	
	public Sale(Book book, Integer quantity) {
		this.setBook(book);
		this.setQuantity(quantity);
	}
	
	@Override
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public BigDecimal getTotalValue() {
		if(this.book != null && this.quantity != null) {
			return book.getPrice().multiply(BigDecimal.valueOf(quantity));
		}
		return null;
	}

}
