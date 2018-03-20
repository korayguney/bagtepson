package com.bagtep.business;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bagtep.domain.GenelAmac;
import com.bagtep.domain.Kazanim;
import com.bagtep.domain.OzelAmac;

@Stateless
public class TestDataService3 {

	@PersistenceContext
	private EntityManager entityManager;

	public GenelAmac getGenelAmac(int dersId, int genelAmacId) {

		GenelAmac genelAmac = entityManager.find(GenelAmac.class, genelAmacId);
		for (OzelAmac o : genelAmac.getOzelAmaclar()) {
			for (Kazanim k : o.getKazanimlar()) {

			}
		}
		return genelAmac;
	}

}
