package br.com.alura.livraria.model;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.alura.livraria.dao.BookDAO;

public class BookDataModel extends LazyDataModel<Book>{

	private static final long serialVersionUID = 1L;
	
	private BookDAO bookDAO;
	
	@Inject
	public BookDataModel(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
		super.setRowCount(bookDAO.getTotalElementCount().intValue());
	}
	
	@Override
	public List<Book> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, FilterMeta> filterBy) {
		// TODO Auto-generated method stub
		System.out.println("filter by: " + filterBy);
		String filterColumn = null;
		Object filterValue = null;
		for(FilterMeta filterMeta : filterBy.values()) {
			if(filterMeta.getFilterValue() != null && !filterMeta.getFilterValue().toString().isBlank()) {
				filterColumn = filterMeta.getFilterField();
				filterValue = filterMeta.getFilterValue();
				System.out.println("Filter value type: " + filterValue.getClass());
				if(filterColumn.equals("price")) {
					filterValue = new BigDecimal((String) filterValue);
				}
			}
		}
		List<Book> books = bookDAO.getAllLike(filterColumn, filterValue, first, pageSize);
		if(sortField != null) {
			Collections.sort(books, new LazyBookSorter(sortField, sortOrder));
		}
		System.out.println("Get all like: " + books);
		return books;
	}

}
