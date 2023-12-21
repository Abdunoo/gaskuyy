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
public class SrvChart {

	@Inject
	private EntityManager em;

	public void update(RcdChart rcdChart) {
		em.merge(rcdChart);
	}

	public void detach(Object record) {
		em.detach(record);
	}

	public List<RcdChart> getProductsFromChart() {
		try {
			TypedQuery<RcdChart> tq = em.createQuery("SELECT h FROM RcdChart h ORDER BY h.id DESC",
					RcdChart.class);
			return tq.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public RcdChart findById(int id) {
		RcdChart rcdChart = em.find(RcdChart.class, id);
		return rcdChart;
	}

	public void delete(int id) {
		System.out.println("remove chart");
		RcdChart r = findById(id);
		// detach(r);
		System.out.println("data " + r.getId());
		em.remove(r);
	}
}
