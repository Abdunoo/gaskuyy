package com.abdun.srv;

import java.util.List;

import javax.management.RuntimeErrorException;

import com.abdun.rcd.RcdCart;

import io.vertx.mutiny.ext.web.handler.ErrorHandler;
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
public class SrvCart {

	@Inject
	private EntityManager em;

	public void update(RcdCart rcdCart) {
			em.merge(rcdCart);
	}

	public void detach(Object record) {
		em.detach(record);
	}

	public List<RcdCart> getProductsFromCart(int userId) {
			try {
				TypedQuery<RcdCart> tq = em.createQuery("SELECT h FROM RcdCart h WHERE h.userId = :userId ORDER BY h.id DESC",
					RcdCart.class);
					tq.setParameter("userId", userId);
			return tq.getResultList();
			} catch (Exception e) {
				return null;
			}
	}

	public RcdCart findById(int id, int userId) {
		try {
			TypedQuery<RcdCart> tq = em.createQuery("SELECT h FROM RcdCart h WHERE h.id = :id AND h.userId = :userId", RcdCart.class);
			tq.setParameter("id", id);
			tq.setParameter("userId", userId);
			return tq.getSingleResult();

		} catch (Exception e) {
			return null;
		}
	}
	
	public RcdCart findByProductId(int id, int userId) {
		try {
			TypedQuery<RcdCart> tq = em.createQuery("SELECT h FROM RcdCart h WHERE h.productId = :id AND h.userId = :userId",
					RcdCart.class);
					tq.setParameter("id", id);
					tq.setParameter("userId", userId);
			return tq.getSingleResult();
		} catch (Exception e) {
			return null;
		}
		
	}

	public void delete(int id, int userId) {
			RcdCart r = findById(id, userId);
			if (r != null) {
				em.remove(r);	
			} else {
				throw new WebApplicationException("Gagal hapus data produk",Response.Status.NOT_FOUND); 
			}
	}
}
