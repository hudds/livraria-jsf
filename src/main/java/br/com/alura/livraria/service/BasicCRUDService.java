package br.com.alura.livraria.service;

import java.io.Serializable;
import java.util.List;

import br.com.alura.livraria.dao.GenericDAO;
import br.com.alura.livraria.log.Log;
import br.com.alura.livraria.model.Identifiable;
import br.com.alura.livraria.tx.FakeTransactional;

public abstract class BasicCRUDService<E extends Identifiable<I>, I> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	protected abstract GenericDAO<E, I> getDAO();
	
	@Log
	@FakeTransactional
	public E findById(I id) {
		return this.getDAO().findById(id);
	}
	
	@Log
	@FakeTransactional
	public List<E> getAll(){
		return this.getDAO().getAll();
	}
	
	@Log
	@FakeTransactional
	public List<E> getAll(Integer firstResult, Integer maxResults){
		return this.getDAO().getAll(firstResult, maxResults);
	}
	
	@Log
	@FakeTransactional
	public I insert(E entity) {
		
		return this.getDAO().insert(entity);
	}
	
	@Log
	@FakeTransactional
	public void deleteById(I id) {
		this.getDAO().deleteById(id);
	}
	
	@Log
	@FakeTransactional
	public void update(E entity) {
		this.getDAO().edit(entity);
	}
	
	@Log
	@FakeTransactional
	public I insertOrUpdate(E entity) {
		if(entity.getId() == null) {
			return this.insert(entity);
		}
		this.update(entity);
		return entity.getId();
	}

}
