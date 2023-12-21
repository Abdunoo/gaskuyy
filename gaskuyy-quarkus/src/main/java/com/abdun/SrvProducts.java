package com.abdun;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

/**
 *
 * @author abdun
 */
@ApplicationScoped
@Transactional
public class SrvProducts {

	@Inject
	private EntityManager em;

	public void update(RcdProducts rcdProducts) {
		em.merge(rcdProducts);
	}

	public void detach(Object record) {
		em.detach(record);
	}

	public List<RcdProducts> getAllProducts(int start, int end) {
		try {
			TypedQuery<RcdProducts> tq = em.createQuery("SELECT h FROM RcdProducts h ORDER BY h.id DESC",
					RcdProducts.class);
			tq.setFirstResult(start);
			tq.setMaxResults(end);
			return tq.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public RcdProducts findById(int id) {
		TypedQuery<RcdProducts> prd = em.createQuery("SELECT h FROM RcdProducts h WHERE h.id = :id",
				RcdProducts.class);
		prd.setParameter("id", id);
		return prd.getSingleResult();
	}

	public List<RcdProducts> findByCategory(String category) {
		try {
			TypedQuery<RcdProducts> tq = em.createQuery(
					"SELECT h FROM RcdProducts h WHERE h.category = :category ORDER BY h.id DESC",
					RcdProducts.class);
			tq.setParameter("category", category);
			return tq.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public void delete(int id) {
		try {
			em.remove(findById(id));

		} catch (Exception e) {
			System.out.println("error = " + e);
			throw e;
		}
	}

}
