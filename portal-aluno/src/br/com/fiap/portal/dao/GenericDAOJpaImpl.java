package br.com.fiap.portal.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public abstract class GenericDAOJpaImpl<T, PK extends Serializable> 
	implements GenericDAO<T, PK> {

	protected Class<T> entityClass;
	
    protected EntityManager entityManager;
			
	@SuppressWarnings("unchecked")
	public GenericDAOJpaImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
             .getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass
             .getActualTypeArguments()[0];               
    }
	
	@Override
	public T insert(T entity) {
		EntityManagerFactory emf = 
    			Persistence.createEntityManagerFactory("jpaPU");        
        entityManager = emf.createEntityManager();
        
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(entity);
		this.entityManager.getTransaction().commit();		
		this.entityManager.close();
		
	    return entity;
	}

	@Override
	public T update(T entity) {
		EntityManagerFactory emf = 
    			Persistence.createEntityManagerFactory("jpaPU");        
        entityManager = emf.createEntityManager();
        
		this.entityManager.getTransaction().begin();
		entity = this.entityManager.merge(entity);
		this.entityManager.getTransaction().commit();
		this.entityManager.close();
		
		return entity;
	}

	@Override
	public void delete(T entity) {
		EntityManagerFactory emf = 
    			Persistence.createEntityManagerFactory("jpaPU");        
        entityManager = emf.createEntityManager();
        
		this.entityManager.getTransaction().begin();
		entity = this.entityManager.merge(entity);
        this.entityManager.remove(entity);
        this.entityManager.getTransaction().commit();
        
        this.entityManager.close();
	}

	@Override
	public T find(PK id) {
		EntityManagerFactory emf = 
    			Persistence.createEntityManagerFactory("jpaPU");        
        entityManager = emf.createEntityManager();
        
		return this.entityManager.find(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findByRm(String rm) {	
		EntityManagerFactory emf = 
    			Persistence.createEntityManagerFactory("jpaPU");        
        entityManager = emf.createEntityManager();
        
		Query query = entityManager
				.createQuery("FROM " + this.entityClass.getSimpleName() + " WHERE rm = :rm");		
		query.setParameter("rm", rm);		
		
		try {
			return (T) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}					 
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		EntityManagerFactory emf = 
    			Persistence.createEntityManagerFactory("jpaPU");        
        entityManager = emf.createEntityManager();
        
		return this.entityManager
				.createQuery("FROM " + this.entityClass.getSimpleName())
				.getResultList();
	}

}
