package br.com.alura.livraria.model;

import java.lang.reflect.Field;
import java.util.Comparator;

import org.primefaces.model.SortOrder;

public class LazyBookSorter implements Comparator<Book> {	
	
	private Field sortField;
	private SortOrder sortOrder;

	public LazyBookSorter(String sortField, SortOrder sortOrder) {
		try {
			this.sortField = Book.class.getDeclaredField(sortField);
		} catch (NoSuchFieldException | SecurityException e) {
			throw new RuntimeException(e);
		}
		this.sortOrder = sortOrder;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int compare(Book b1, Book b2) {
		sortField.setAccessible(true);
		try {
			Object v1 = sortField.get(b1);
			Object v2 = sortField.get(b2);
			@SuppressWarnings("unchecked")
			Integer value = ((Comparable) v1).compareTo(v2);
			sortField.setAccessible(false);
			return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
		} catch (IllegalArgumentException | IllegalAccessException e) {
			sortField.setAccessible(false);
			throw new RuntimeException(e);
		}
	}

}
