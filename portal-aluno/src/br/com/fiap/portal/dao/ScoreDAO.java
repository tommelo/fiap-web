package br.com.fiap.portal.dao;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.fiap.portal.model.Score;

public class ScoreDAO extends GenericDAOJpaImpl<Score, Long>{
	
	@SuppressWarnings("unchecked")
	public List<Score> findByUserId(long userId) {
		EntityManagerFactory emf = 
    			Persistence.createEntityManagerFactory("jpaPU");        
        entityManager = emf.createEntityManager();
        
		Query query = entityManager
				.createQuery("FROM " + this.entityClass.getSimpleName() + " WHERE student.id = :id");		
		query.setParameter("id", userId);		
		
		try {
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}				
	}
	
}
