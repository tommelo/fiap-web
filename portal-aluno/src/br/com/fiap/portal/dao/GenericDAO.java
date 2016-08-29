package br.com.fiap.portal.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T, PK extends Serializable> {
	T insert(T entity);
	T update(T entity);
	void delete(T entity);
	T find(PK id);
	T findByRm(String rm);
	List<T> findAll();	
}
