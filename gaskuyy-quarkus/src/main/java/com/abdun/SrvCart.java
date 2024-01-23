package com.abdun;

import java.util.List;

import javax.management.RuntimeErrorException;

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

	public List<RcdCart> getProductsFromCart() {
			try {
				TypedQuery<RcdCart> tq = em.createQuery("SELECT h FROM RcdCart h ORDER BY h.id DESC",
					RcdCart.class);
			return tq.getResultList();
			} catch (Exception e) {
				return null;
			}
	}

	public RcdCart findById(int id) {
		try {
			RcdCart rcdCart = em.find(RcdCart.class, id);
			return rcdCart;
		} catch (Exception e) {
			return null;
		}
	}
	
	public RcdCart findByProductId(int id) {
		try {
			TypedQuery<RcdCart> tq = em.createQuery("SELECT h FROM RcdCart h WHERE h.productId = :id",
					RcdCart.class);	
					tq.setParameter("id", id);
			return tq.getSingleResult();
		} catch (Exception e) {
			return null;
		}
		
	}

	public void delete(int id) {
			RcdCart r = findById(id);
			if (r != null) {
				em.remove(r);	
			} else {
				throw new WebApplicationException("Gagal hapus data produk",Response.Status.NOT_FOUND); 
			}
	}
}
