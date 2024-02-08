package com.abdun.srv;

import java.util.List;

import com.abdun.rcd.RcdProducts;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

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

	public List<RcdProducts> getAllProducts(int start, int limit, String category, String searchQuery, int userId) {
		try {
			String sql = "SELECT h FROM RcdProducts h ";

			sql = sql + "WHERE h.userId = :userId ";

			if (category != null) {
				sql = sql+"AND h.category = :category ";
			}

			if (searchQuery != null) {
				sql = sql+"AND h.title LIKE :searchQuery ";
			}
			

			sql = sql + "ORDER BY h.id DESC";
			TypedQuery<RcdProducts> tq = em.createQuery(sql, RcdProducts.class);
			tq.setParameter("userId", userId);
			if (searchQuery != null) {
				tq.setParameter("searchQuery", "%" + searchQuery + "%");
			}
			
			if (category != null) {
				tq.setParameter("category", category);
			}
			tq.setFirstResult(start);
			tq.setMaxResults(limit);
			return tq.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public RcdProducts findById(int id, int userId) {
			try {
				TypedQuery<RcdProducts> prd = em.createQuery("SELECT h FROM RcdProducts h WHERE h.id = :id AND h.userId = :userId",
					RcdProducts.class);
			prd.setParameter("id", id);
			prd.setParameter("userId", userId);
			return prd.getSingleResult();
			} catch (Exception e) {
				return null;
			}
		}

	public void delete(int id, int userId) {
		RcdProducts prd = findById(id, userId);
		if (prd != null) {
			em.remove(prd);	
		}
	}

}
