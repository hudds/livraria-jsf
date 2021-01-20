package br.com.alura.livraria.service;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.alura.livraria.dao.GenericDAO;
import br.com.alura.livraria.dao.SaleDAO;
import br.com.alura.livraria.model.Sale;

@Transactional
public class SaleService extends BasicCRUDService<Sale, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private SaleDAO saleDAO;
	
	@Override
	public Long insert(Sale sale) {
		if(sale.getBook() == null) {
			throw new IllegalArgumentException("sale must contain a book");
		}
		
		if(sale.getQuantity() == null || sale.getQuantity() < 1) {
			throw new IllegalArgumentException("Sale quantity must be at least 1. Current quantity value is: " + sale.getQuantity());
		}
		
		return super.insert(sale);
	}
	
	@Override
	protected GenericDAO<Sale, Long> getDAO() {
		return saleDAO;
	}
	
}
