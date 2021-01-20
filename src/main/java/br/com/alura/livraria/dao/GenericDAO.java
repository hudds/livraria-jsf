package br.com.alura.livraria.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.alura.livraria.model.Identifiable;

public abstract class GenericDAO<E extends Identifiable<I>, I> implements DataAccesObject<E, I>, Serializable {

	private static final long serialVersionUID = 1L;

	private final Class<E> entityClass;

	@PersistenceContext
	private EntityManager em;

	public GenericDAO(Class<E> entityClass) {
		if (entityClass == null) {
			throw new NullPointerException("Entity class cannot be null");
		}
		this.entityClass = entityClass;

	}

	@Override
	public E findById(I id) {
		E entity = em.find(entityClass, id);
		return entity;
	}

	public E findById(I id, String fetchProperty) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(entityClass);
		Root<E> root = criteriaQuery.from(entityClass);
		root.fetch(fetchProperty);

		Predicate equalToId = criteriaBuilder.equal(root.get(getIdFieldName(entityClass)), id);
		criteriaQuery = criteriaQuery.where(equalToId);
		return em.createQuery(criteriaQuery).getSingleResult();
	}

	private String getIdFieldName(Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(Id.class)) {
				return field.getName();
			}
		}
		return null;
	}

	@Override
	public List<E> getAll() {
		String jpql = "select e from " + entityClass.getSimpleName() + " e";
		TypedQuery<E> query = em.createQuery(jpql, entityClass);
		query.setHint("org.hibernate.cacheable", true);
		return query.getResultList();
	}

	@Override
	public List<E> getAll(Integer firstResult, Integer maxResults) {
		String jpql = "select e from " + entityClass.getSimpleName() + " e";
		TypedQuery<E> query = em.createQuery(jpql, entityClass);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		query.setHint("org.hibernate.cacheable", true);
		return query.getResultList();
	}

	public List<E> getAllLike(String attributeName, Object value, Integer firstResult, Integer maxResults) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(entityClass);
		Root<E> root = criteriaQuery.from(entityClass);
		if (value != null && !value.toString().isBlank()) {
			if (value instanceof String) {
				criteriaQuery = criteriaQuery.where(criteriaBuilder.like(root.get(attributeName), "%" + value + "%"));
			} else {
				criteriaQuery = criteriaQuery.where(criteriaBuilder.equal(root.get(attributeName), value));
			}
		} else {
			criteriaQuery = criteriaQuery.select(root);
		}
		TypedQuery<E> query = em.createQuery(criteriaQuery);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		query.setHint("org.hibernate.cacheable", true);
		System.out.println(query.getResultList());
		return query.getResultList();
	}

	@Override
	public I insert(E entity) {
		em.persist(entity);
		return entity.getId();
	}

	@Override
	public void deleteById(I id) {
		try {
			E entity = findById(id);
			if (entity == null) {
				throw new EntityNotFoundException("Could not find entity with id " + id);
			}
			em.remove(entity);
		} catch (Exception e) {
			System.out.println(e.getClass());
		}

	}

	@Override
	public void edit(E entity) {
		if (findById(entity.getId()) == null) {
			throw new EntityNotFoundException("Could not find entity with id " + entity.getId());
		}
		em.merge(entity);
	}

	public Long getTotalElementCount() {
		String jpql = "select count(e) from " + entityClass.getSimpleName() + " e";
		TypedQuery<Long> query = em.createQuery(jpql, Long.class);
		query.setHint("org.hibernate.cacheable", true);
		return query.getSingleResult();
	}

	public List<E> findByProperty(String propertyName, Object propertyValue) {
		return findByProperty(propertyName, propertyValue, null);

	}

	public List<E> findByProperty(String propertyName, Object propertyValue, String fetchProperty) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(entityClass);
		Root<E> root = criteriaQuery.from(entityClass);
		if (fetchProperty != null) {
			root.fetch(fetchProperty);
		}
		Predicate equal = criteriaBuilder.equal(root.get(propertyName), propertyValue);
		criteriaQuery = criteriaQuery.where(equal);
		TypedQuery<E> query = this.em.createQuery(criteriaQuery);
		return query.getResultList();

	}

	protected EntityManager getEntityManager() {
		return em;
	}

}
