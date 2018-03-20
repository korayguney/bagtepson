package com.bagtep.business;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bagtep.domain.BireyselEgitimPlani;
import com.bagtep.domain.BireyselEgitimPlaniRaporSecim;
import com.bagtep.domain.Ders;
import com.bagtep.domain.KabaDegerlendirme;
import com.bagtep.domain.Ogrenci;
import com.bagtep.domain.OzelAmac;

@Stateless
public class BireyselEgitimPlaniService {

	BireyselEgitimPlaniRaporSecim bepCevap;

	@PersistenceContext
	private EntityManager entityManager;

	// Kaba Değerlendirmede "HAYIR" verilen özel amaçlar listelenir.
	public List<OzelAmac> kabaDegerlendirmedeHayirVerilenCevaplariGetir(int dersId, int ogrenciId) {

		Ogrenci ogrenci = entityManager.find(Ogrenci.class, ogrenciId);
		Ders ders = entityManager.find(Ders.class, dersId);

		List<OzelAmac> ozelAmaclarHayirVerilen = entityManager
				.createQuery(
						"SELECT k.ozelAmac FROM KabaDegerlendirmeKazanimCevap k WHERE k.kabaDegerlendirme.ogrenci.id =:ogrenciId and k.kabaDegerlendirme.ders.id =:dersId and k.kabaDegerlendirme.sinifAd=:sinifAd and k.kabaDegerlendirmeCevap=0")
				.setParameter("ogrenciId", ogrenciId).setParameter("dersId", dersId)
				.setParameter("sinifAd", ogrenci.getSinif().getSinifAd()).getResultList();

		return ozelAmaclarHayirVerilen;

	}

	// Bireysel Eğitim Planında "EVET" verilerek BEP'e dahil edilen özel amaçlar listelenir.
	public List<OzelAmac> bireyselEgitimPlaninaDahilEdilenleriGetir(int dersId, int ogrenciId) {

		Ogrenci ogrenci = entityManager.find(Ogrenci.class, ogrenciId);
		Ders ders = entityManager.find(Ders.class, dersId);

		List<OzelAmac> bepteEvetVerilerekDahilEdilen = entityManager
				.createQuery(
						"SELECT k.ozelAmac FROM BireyselEgitimPlaniRaporSecim k WHERE k.bireyselEgitimPlani.ogrenci.id =:ogrenciId and k.bireyselEgitimPlani.ders.id =:dersId and k.bireyselEgitimPlani.sinifAd=:sinifAd and k.bireyselEgitimPlaniCevap=1")
				.setParameter("ogrenciId", ogrenciId).setParameter("dersId", dersId)
				.setParameter("sinifAd", ogrenci.getSinif().getSinifAd()).getResultList();

		return bepteEvetVerilerekDahilEdilen;

	}
	
	
	public List<OzelAmac> kabaDegerlendirmedeEvetVerilenCevaplariGetir(int dersId, int ogrenciId) {

		Ogrenci ogrenci = entityManager.find(Ogrenci.class, ogrenciId);
		Ders ders = entityManager.find(Ders.class, dersId);

		List<OzelAmac> ozelAmaclarEvetVerilen = entityManager
				.createQuery(
						"SELECT k.ozelAmac FROM KabaDegerlendirmeKazanimCevap k WHERE k.kabaDegerlendirme.ogrenci.id =:ogrenciId and k.kabaDegerlendirme.ders.id =:dersId and k.kabaDegerlendirme.sinifAd=:sinifAd and k.kabaDegerlendirmeCevap=1")
				.setParameter("ogrenciId", ogrenciId).setParameter("dersId", dersId)
				.setParameter("sinifAd", ogrenci.getSinif().getSinifAd()).getResultList();

		return ozelAmaclarEvetVerilen;

	}

	// bepevetverilenlerigetir
	public List<OzelAmac> BEPEvetVerilenCevaplariGetir(int dersId, int ogrenciId) {

		Ogrenci ogrenci = entityManager.find(Ogrenci.class, ogrenciId);
		Ders ders = entityManager.find(Ders.class, dersId);

		List<OzelAmac> BEPozelAmaclarEvetVerilen = entityManager
				.createQuery(
						"SELECT b.ozelAmac FROM BireyselEgitimPlaniRaporSecim b WHERE b.bireyselEgitimPlani.ogrenci.id =:ogrenciId and b.bireyselEgitimPlani.ders.id =:dersId and b.bireyselEgitimPlani.sinifAd=:sinifAd and b.bireyselEgitimPlaniCevap=1")
				.setParameter("ogrenciId", ogrenciId).setParameter("dersId", dersId)
				.setParameter("sinifAd", ogrenci.getSinif().getSinifAd()).getResultList();
		return BEPozelAmaclarEvetVerilen;

	}

	public void bireyselEgitimPlaniAl(int ogrenciId, String dersAd, String degerlendirici,
			Map<Integer, Boolean> ozelAmaclarMap, Map<Integer, OzelAmac> ozelAmacIdMap) {

		Ders ders = entityManager.createQuery("select d from Ders d where d.dersAd=:dersAd", Ders.class)
				.setParameter("dersAd", dersAd).getSingleResult();
		Ogrenci ogrenci = entityManager.createQuery("select o from Ogrenci o where o.id=:ogrenciId", Ogrenci.class)
				.setParameter("ogrenciId", ogrenciId).getSingleResult();

		KabaDegerlendirme kabaDegerlendirme = (KabaDegerlendirme) entityManager
				.createQuery(
						"SELECT k FROM KabaDegerlendirme k WHERE k.ogrenci.id=:ogrenciId and k.ders.id=:dersId and k.sinifAd=:sinifAd")
				.setParameter("ogrenciId", ogrenci.getId()).setParameter("dersId", ders.getId())
				.setParameter("sinifAd", ogrenci.getSinif().getSinifAd()).getSingleResult();

		BireyselEgitimPlani bep = new BireyselEgitimPlani();
		bep.setDegerlendirmeTarihi(new Date());
		bep.setDegerlendirici(degerlendirici);
		bep.setDers(ders);
		bep.setOgrenci(ogrenci);
		bep.setSinifAd(ogrenci.getSinif().getSinifAd());
		bep.setKabaDegerlendirmeTarihi(kabaDegerlendirme.getDegerlendirmeTarihi());

		entityManager.persist(bep);

		for (Integer key : ozelAmaclarMap.keySet()) {
			bepCevap = new BireyselEgitimPlaniRaporSecim();
			bepCevap.setBireyselEgitimPlaniCevap(Boolean.parseBoolean("" + ozelAmaclarMap.get(key)));
			bepCevap.setBireyselEgitimPlani(bep);
			bepCevap.setOzelAmac(ozelAmacIdMap.get(key));

			if (Boolean.parseBoolean("" + ozelAmaclarMap.get(key))) {
				entityManager.persist(bepCevap);
			} else {
				continue;
			}
		}

	}

	public boolean dahaOnceDegerlendirilmismi(int ogrenciId, String dersAd) {
		Ders ders = entityManager.createQuery("select d from Ders d where d.dersAd=:dersAd", Ders.class)
				.setParameter("dersAd", dersAd).getSingleResult();
		Ogrenci ogrenci = entityManager.createQuery("select o from Ogrenci o where o.id=:ogrenciId", Ogrenci.class)
				.setParameter("ogrenciId", ogrenciId).getSingleResult();

		List<BireyselEgitimPlani> bep = entityManager
				.createQuery(
						"SELECT b FROM BireyselEgitimPlani b WHERE b.ders.id=:dersId AND b.ogrenci.id=:ogrenciId AND b.sinifAd=:ogrenciSinif")
				.setParameter("dersId", ders.getId()).setParameter("ogrenciId", ogrenci.getId())
				.setParameter("ogrenciSinif", ogrenci.getSinif().getSinifAd()).getResultList();

		if (bep.isEmpty()) {
			return false;
			//
		} else {
			return true;
		}
	}
}
