package br.com.alura.livraria.dao;

import java.util.List;

import br.com.alura.livraria.model.Identifiable;

public interface DataAccesObject<E extends Identifiable<I>, I> {
	
	public E findById(I id);
	public List<E> getAll();
	public List<E> getAll(Integer firstResult, Integer maxResults);
	public I insert(E entity);
	public void deleteById(I id);
	public void edit(E entity);

}
