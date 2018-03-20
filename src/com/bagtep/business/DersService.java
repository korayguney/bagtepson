package com.bagtep.business;

import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bagtep.domain.Ders;
import com.bagtep.domain.GenelAmac;
import com.bagtep.domain.Ogretmen;
import com.bagtep.domain.User;
import com.bagtep.mbeans.MySessionScopedBean;

@Stateless

public class DersService {
	@PersistenceContext
	private EntityManager entityManager;

	public void saveDers(Ders newDers) {

		List<Ders> dersler = entityManager.createQuery("select d from Ders d where d.dersAd=:dersAd", Ders.class)
				.setParameter("dersAd", newDers.getDersAd()).getResultList();
		entityManager.persist(newDers);
	}

	public int getId(int dersno) {
		List<Ders> dersler = entityManager.createQuery("select d from Ders d where d.dersno=:dersno", Ders.class)
				.setParameter("dersno", dersno).getResultList();
		int id = dersler.get(0).getId();
		return id;
	}

	public int getId(String dersAd) {
		List<Ders> dersler = entityManager.createQuery("select d from Ders d where d.dersAd=:dersAd", Ders.class)
				.setParameter("dersAd", dersAd).getResultList();
		if (dersler.size() > 0) {
			int id = dersler.get(0).getId();
			return id;
		} else if (dersler.size() == 0) {
			return 0;
		} else {
			return 0;
		}
	}

	public String getDersAd(int dersno) {

		List<Ders> dersler = entityManager.createQuery("select d from Ders d where d.dersno=:dersno", Ders.class)
				.setParameter("dersno", dersno).getResultList();
		String dersAd = dersler.get(0).getDersAd();
		return dersAd;
	}

	public List<Ders> getAllDers() {
		return entityManager.createQuery("select d from Ders d", Ders.class).getResultList();
	}

	public void deleteDers(int dersId) {
		Ders Ders = entityManager.find(Ders.class, dersId);
		entityManager.remove(Ders);

	}

	@SuppressWarnings("null")
	public User getUserInSession() {
		MySessionScopedBean mysess = null;
		return entityManager.find(User.class, mysess.getId());
	}

	public void updateDers(Ders ders) {
		entityManager.merge(ders);
	}

	public Set<Ders> ogretmeneAtananDersleriGetir(int ogretmenId) {
		Ogretmen ogretmen = (Ogretmen) entityManager.createQuery("SELECT o FROM Ogretmen o WHERE o.id=:ogretmenId")
				.setParameter("ogretmenId", ogretmenId).getSingleResult();
		Set<Ders> atanmisDersler = ogretmen.getDersler();

		return atanmisDersler;
	}

	public int genelAmacSayisiniBul(String dersAd) {
		List<GenelAmac> genelAmacSayisi = entityManager.createQuery("SELECT g FROM GenelAmac g WHERE g.ders.dersAd=:dersAd").setParameter("dersAd", dersAd).getResultList();
		return genelAmacSayisi.size();
	}

}