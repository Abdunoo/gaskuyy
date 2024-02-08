package com.abdun.srv;

import java.util.List;

import com.abdun.rcd.RcdUser;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

/**
 *
 * @author abdun
 */
@ApplicationScoped
@Transactional
public class SrvUser {

	@Inject
	private EntityManager em;

	public void update(RcdUser rcdUser) {
			em.merge(rcdUser);
	}

	public void detach(Object record) {
		em.detach(record);
	}
	
	public List<RcdUser> getUsers() {
		try {
			TypedQuery<RcdUser> user = em.createQuery("SELECT h FROM RcdUser h",RcdUser.class);
			return user.getResultList();
		} catch (Exception e) {
			return null;
		}
	}
	
	public RcdUser findByUniqueKey(String uniqueKey) {
		try {
			TypedQuery<RcdUser> user = em.createQuery("SELECT h FROM RcdUser h WHERE h.uniqueKey = :uniqueKey",RcdUser.class);
			user.setParameter("uniqueKey", uniqueKey);
			return user.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
	public RcdUser findById(int id) {
		try {
			TypedQuery<RcdUser> user = em.createQuery("SELECT h FROM RcdUser h WHERE h.id = :id",RcdUser.class);
			user.setParameter("id", id);
			return user.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
	public void deleteById(int id) {
		try {
			RcdUser rcdUser = findById(id);
			if (rcdUser != null) {
				em.remove(rcdUser);
			}
		} catch (Exception e) {
			throw e;
		}
	}
}
