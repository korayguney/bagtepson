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

@Stateless
public class OgretmenService {

	Ogretmen ogretmen;
	Ders ders;
	Sinif sinif;

	@PersistenceContext
	private EntityManager entityManager;

	public List<Ogretmen> tumOgretmenleriGetir() {
		return entityManager.createQuery("select o from Ogretmen o", Ogretmen.class).getResultList();
	}

	public Ogretmen ogretmeneDersAta(int ogretmenId, int dersId) {
		this.ogretmen = entityManager.find(Ogretmen.class, ogretmenId);
		this.ders = entityManager.find(Ders.class, dersId);
		
		ogretmen.getDersler().add(ders);
		
		entityManager.merge(ogretmen);
		entityManager.merge(ders);

		return ogretmen;
	}

	public void ogretmenKaydet(String firstname, String lastname) {
		Ogretmen ogretmen = new Ogretmen();
		ogretmen.setAd(firstname);
		ogretmen.setSoyad(lastname);
		entityManager.persist(ogretmen);
	}

	public Ogretmen ogretmeneAtananDersleriGetir(int ogretmenId, int dersId) {

		Ogretmen ogretmen = entityManager.find(Ogretmen.class, ogretmenId);
		Ders ders = entityManager.find(Ders.class, dersId);

		return ogretmen;
	}

	public Ogretmen ogretmeniGetir(String ad, String soyad) {

		Ogretmen ogretmen = (Ogretmen) entityManager
				.createQuery("SELECT o FROM Ogretmen o WHERE o.ad=:ogretmenAd and o.soyad=:ogretmenSoyad")
				.setParameter("ogretmenAd", ad).setParameter("ogretmenSoyad", soyad).getSingleResult();

		return ogretmen;
	}

	public void ogretmeniSil(String firstname, String lastname) {
		Ogretmen ogretmen = ogretmeniGetir(firstname, lastname);
		System.out.println("silinecek öğretmen : " + ogretmen.getAd());
		
		Set<Ders> dersler = ogretmen.getDersler();
		if(dersler != null){
			ogretmen.getDersler().clear();
			for (Ders ders : dersler) {
				ders.setOgretmenler(null);
				entityManager.remove(ders);
			}
		}
		
		Set<Sinif> siniflar = ogretmen.getSiniflar();
		if(siniflar != null){
			ogretmen.getSiniflar().clear();
			for (Sinif sinif : siniflar) {
				sinif.setOgretmen(null);
				entityManager.remove(sinif);
			}
		}
//		ogretmen.setDersler(null);
		entityManager.remove(ogretmen);
	}

	public int dahaOnceDersAtanmismi(int ogretmenId, int dersId) {
		List result = entityManager.createNativeQuery("select * from ogretmen_ders where ogretmenler_id=? and dersler_id=?").setParameter(1, ogretmenId).setParameter(2, dersId).getResultList();
		return result.size();
	}

	public void ogretmenGuncelle(User user) {
		int result = entityManager.createQuery("update Ogretmen o set o.ad=:ogretmenAd, o.soyad=:ogretmenSoyad").setParameter("ogretmenAd", user.getFirstname()).setParameter("ogretmenSoyad",user.getLastname()).executeUpdate();
		if (result > 0) {
			System.out.println("Öğretmen update tamam");
		} else {
			System.err.println("Öğretmen update PROBLEMİ");
		}
	}

}