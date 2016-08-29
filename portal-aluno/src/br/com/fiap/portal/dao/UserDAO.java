package br.com.fiap.portal.dao;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.fiap.portal.model.User;

public class UserDAO extends GenericDAOJpaImpl<User, Long>{

	
	@SuppressWarnings("unchecked")
	public List<User> findByRole(String role) {
		EntityManagerFactory emf = 
    			Persistence.createEntityManagerFactory("jpaPU");        
        entityManager = emf.createEntityManager();
        
		Query query = entityManager
				.createQuery("FROM " + this.entityClass.getSimpleName() + " WHERE role = :role");		
		query.setParameter("role", role);		
		
		try {
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}
	
}
