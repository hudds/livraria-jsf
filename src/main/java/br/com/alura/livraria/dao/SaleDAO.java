package br.com.alura.livraria.dao;

import br.com.alura.livraria.model.Sale;

public class SaleDAO extends GenericDAO<Sale, Long> {

	private static final long serialVersionUID = 1L;

	public SaleDAO() {
		super(Sale.class);
	}

}
