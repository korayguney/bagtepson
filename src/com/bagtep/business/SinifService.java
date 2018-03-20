package com.bagtep.business;

import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bagtep.domain.Ders;
import com.bagtep.domain.Ogretmen;
import com.bagtep.domain.Sinif;
import com.bagtep.domain.User;
import com.bagtep.mbeans.MySessionScopedBean;

@Stateless

public class SinifService {
	@PersistenceContext
	private EntityManager entityManager;

	public void saveSinif(Sinif newSinif) {

		List<Sinif> siniflar = entityManager.createQuery("select s from Sinif s where s.sinifAd=:sinifAd", Sinif.class)
				.setParameter("sinifAd", newSinif.getSinifAd()).getResultList();
		entityManager.persist(newSinif);
	}

	public int getId(int sinifno) {

		List<Sinif> siniflar = entityManager.createQuery("select s from Sinif s where s.sinifno=:sinifno", Sinif.class)
				.setParameter("sinifno", sinifno).getResultList();
		int id = siniflar.get(0).getId();
		return id;
	}

	public String getSinifAd(int sinifno) {

		List<Sinif> siniflar = entityManager.createQuery("select s from Sinif s where s.sinifno=:sinifno", Sinif.class)
				.setParameter("sinifno", sinifno).getResultList();
		String sinifAd = siniflar.get(0).getSinifAd();
		return sinifAd;
	}

	public List<Sinif> getAllSinif() {
		return entityManager.createQuery("select s from Sinif s", Sinif.class).getResultList();
	}

	public boolean deleteSinif(int sinifId) {
		try {
			Sinif sinif = entityManager.find(Sinif.class, sinifId);
			entityManager.remove(sinif);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@SuppressWarnings("null")
	public User getUserInSession() {
		MySessionScopedBean mysess = null;
		return entityManager.find(User.class, mysess.getId());
	}

	public void updateSinif(Sinif sinif) {
		entityManager.merge(sinif);
	}

	public void ogretmeneSinifAta(int ogretmenId, int sinifId) {
		Ogretmen ogretmen = entityManager.find(Ogretmen.class, ogretmenId);
		Sinif sinif = entityManager.find(Sinif.class, sinifId);

		ogretmen.getSiniflar().add(sinif);
		entityManager.merge(ogretmen);
		entityManager.merge(sinif);
	}

	public void derseSinifAta(int dersId, int sinifId) {
		Ders ders = entityManager.find(Ders.class, dersId);
		Sinif sinif = entityManager.find(Sinif.class, sinifId);

		sinif.getDersler().add(ders);

		entityManager.merge(ders);
		entityManager.merge(sinif);
	}

	public Set<Sinif> ogretmeneAtananSiniflariGetir(int ogretmenId) {
		Ogretmen ogretmen = (Ogretmen) entityManager.createQuery("SELECT o FROM Ogretmen o WHERE o.id=:ogretmenId")
				.setParameter("ogretmenId", ogretmenId).getSingleResult();

		Set<Sinif> atanmisDersler = ogretmen.getSiniflar();
		return atanmisDersler;
	}

}