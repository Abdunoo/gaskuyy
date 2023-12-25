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
		} catch (NoResultException e) {
			return null;
		}
	}

	public RcdCart findById(int id) {
		RcdCart rcdCart = em.find(RcdCart.class, id);
		return rcdCart;
	}

	public void delete(int id) {
		System.out.println("remove cart");
		RcdCart r = findById(id);
		// detach(r);
		System.out.println("data " + r.getId());
		em.remove(r);
	}
}
