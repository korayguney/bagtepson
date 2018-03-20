package com.bagtep.business;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.bagtep.domain.Ders;
import com.bagtep.domain.KabaDegerlendirme;
import com.bagtep.domain.KabaDegerlendirmeKazanimCevap;
import com.bagtep.domain.Ogrenci;
import com.bagtep.domain.OzelAmac;

@Stateless
public class KabaDegerlendirmeService {

	KabaDegerlendirmeKazanimCevap kdcevap;
	static int i = 0;
	static int evetCevap = 0;

	@PersistenceContext
	private EntityManager entityManager;

	public void degerlendirmeKaydet(int ogrenciId, String dersAd, String degerlendirici,
			Map<Integer, Boolean> ozelAmaclarMap, Map<Integer, String> yorum, Map<Integer, OzelAmac> ozelAmacIdMap) {

		Ders ders = entityManager.createQuery("select d from Ders d where d.dersAd=:dersAd", Ders.class)
				.setParameter("dersAd", dersAd).getSingleResult();
		Ogrenci ogrenci = entityManager.createQuery("select o from Ogrenci o where o.id=:ogrenciId", Ogrenci.class)
				.setParameter("ogrenciId", ogrenciId).getSingleResult();

		KabaDegerlendirme kd = new KabaDegerlendirme();
		kd.setDegerlendirmeTarihi(new Date());
		kd.setDegerlendirici(degerlendirici);
		kd.setDers(ders);
		kd.setOgrenci(ogrenci);
		kd.setSinifAd(ogrenci.getSinif().getSinifAd());

		entityManager.persist(kd);

		for (Integer key : ozelAmaclarMap.keySet()) {
			kdcevap = new KabaDegerlendirmeKazanimCevap();
			kdcevap.setKabaDegerlendirmeCevap(Boolean.parseBoolean("" + ozelAmaclarMap.get(key)));
			kdcevap.setYorum("" + yorum.get(key));
			kdcevap.setKabaDegerlendirme(kd);
			kdcevap.setOzelAmac(ozelAmacIdMap.get(key));

			entityManager.persist(kdcevap);
		}

	}

	public void degerlendirmeGoruntuleKaydet(int ogrenciId, String dersAd, String degerlendirici,
			Map<Integer, Boolean> ozelAmaclarMap, Map<Integer, String> yorum, Map<Integer, OzelAmac> ozelAmacIdMap) {

		Ders ders = entityManager.createQuery("select d from Ders d where d.dersAd=:dersAd", Ders.class)
				.setParameter("dersAd", dersAd).getSingleResult();
		Ogrenci ogrenci = entityManager.createQuery("select o from Ogrenci o where o.id=:ogrenciId", Ogrenci.class)
				.setParameter("ogrenciId", ogrenciId).getSingleResult();

		KabaDegerlendirme kd = kabaDegerlendirmeGetir(ogrenciId, ders.getId());

		kd.setDegerlendirmeTarihi(new Date());
		kd.setDegerlendirici(degerlendirici);
		kd.setDers(ders);
		kd.setOgrenci(ogrenci);
		kd.setSinifAd(ogrenci.getSinif().getSinifAd());

		entityManager.merge(kd);

		List<KabaDegerlendirmeKazanimCevap> kdcevap = (List<KabaDegerlendirmeKazanimCevap>) entityManager
				.createQuery("select k from KabaDegerlendirmeKazanimCevap k where k.kabaDegerlendirme.id=:kabaId")
				.setParameter("kabaId", kd.getId()).getResultList();

		for (Integer key : ozelAmaclarMap.keySet()) {
			kdcevap.get(i).setKabaDegerlendirmeCevap(Boolean.parseBoolean("" + ozelAmaclarMap.get(key)));
			kdcevap.get(i).setYorum("" + yorum.get(key));
			kdcevap.get(i).setKabaDegerlendirme(kd);
			kdcevap.get(i).setOzelAmac(ozelAmacIdMap.get(key));

			entityManager.merge(kdcevap.get(i));
			i++;
			if (i == ozelAmaclarMap.size()) {
				break;
			}
		}
		i = 0;
	}

	public void getDegerlendirme() {

	}

	public void getGelisim() {

	}

	public int degerlendirmePuanHesapla(int ogrenciId, String dersAd, String sinifAd) {
		Ders ders = entityManager.createQuery("select d from Ders d where d.dersAd=:dersAd", Ders.class)
				.setParameter("dersAd", dersAd).getSingleResult();
		KabaDegerlendirme kd = null;
		try {
			kd = (KabaDegerlendirme) entityManager
					.createQuery(
							"select k from KabaDegerlendirme k where k.ogrenci.id=:ogrenciId and k.ders.id=:dersId and k.sinifAd=:sinifAd")
					.setParameter("ogrenciId", ogrenciId).setParameter("dersId", ders.getId())
					.setParameter("sinifAd", sinifAd).getSingleResult();
		} catch (Exception e) {
		}

		double toplamCevap = kd.getKabaDegerlendirmeKazanimCevap().size();

		for (int i = 0; i < kd.getKabaDegerlendirmeKazanimCevap().size(); i++) {
			if (kd.getKabaDegerlendirmeKazanimCevap().get(i).isKabaDegerlendirmeCevap()) {
				evetCevap++;
			}
		}

		double sonuc = (evetCevap * 100) / toplamCevap;

		evetCevap = 0;
		return (int) sonuc;
	}

	public KabaDegerlendirme kabaDegerlendirmeGetir2(int ogrenciId, int dersId) {
		Ders ders = entityManager.createQuery("select d from Ders d where d.id=:dersId", Ders.class)
				.setParameter("dersId", dersId).getSingleResult();
		Ogrenci ogrenci = entityManager.createQuery("select o from Ogrenci o where o.id=:ogrenciId", Ogrenci.class)
				.setParameter("ogrenciId", ogrenciId).getSingleResult();

		KabaDegerlendirme kd2 = (KabaDegerlendirme) entityManager
				.createQuery(
						"select k from KabaDegerlendirme k where k.ogrenci.id=:ogrenciId and k.ders.id=:dersId and k.sinifAd=:sinifAd")
				.setParameter("ogrenciId", ogrenciId).setParameter("dersId", dersId)
				.setParameter("sinifAd", ogrenci.getSinif().getSinifAd()).getSingleResult();
		return kd2;
	}

	public KabaDegerlendirme kabaDegerlendirmeGetir(int ogrenciId, int dersId) {

		KabaDegerlendirme kd2 = (KabaDegerlendirme) entityManager
				.createQuery("select k from KabaDegerlendirme k where k.ogrenci.id=:ogrenciId and k.ders.id=:dersId")
				.setParameter("ogrenciId", ogrenciId).setParameter("dersId", dersId).getSingleResult();

		return kd2;
	}

	public List<KabaDegerlendirme> kabaDegerlendirmeleriGetir(int ogrenciId, int dersId) {

		List<KabaDegerlendirme> kd2 = entityManager
				.createQuery("select k from KabaDegerlendirme k where k.ogrenci.id=:ogrenciId and k.ders.id=:dersId")
				.setParameter("ogrenciId", ogrenciId).setParameter("dersId", dersId).getResultList();
		return kd2;
	}

	public List<OzelAmac> kabaDegerlendirmedeSadeceHayirDenilenOzelAmaclariGetir(int ogrenciId, int dersId) {

		KabaDegerlendirme kd2 = (KabaDegerlendirme) entityManager
				.createQuery("select k from KabaDegerlendirme k where k.ogrenci.id=:ogrenciId and k.ders.id=:dersId")
				.setParameter("ogrenciId", ogrenciId).setParameter("dersId", dersId).getSingleResult();
		List<OzelAmac> ozelAmaclar = null;
		boolean KDcevap = false;

		for (int i = 0; i < kd2.getKabaDegerlendirmeKazanimCevap().size(); i++) {
			KDcevap = kd2.getKabaDegerlendirmeKazanimCevap().get(i).isKabaDegerlendirmeCevap();
			OzelAmac ozelAmac = (OzelAmac) entityManager
					.createQuery(
							"select o from OzelAmac o where o.kabaDegerlendirmeKazanimCevap.kabaDegerlendirmeCevap=:KDcevap")
					.setParameter("KDcevap", KDcevap).getSingleResult();
			ozelAmaclar.add(ozelAmac);
		}
		return ozelAmaclar;
	}

	public List<Boolean> cevaplariGetir(String dersAd, int id) {
		List<Boolean> cevaplar = entityManager
				.createQuery(
						"select k.kabaDegerlendirmeCevap from KabaDegerlendirmeKazanimCevap k where k.ozelAmac.genelAmac.id=:genelAmacId and k.kabaDegerlendirme.ders.dersAd=:dersAd")
				.setParameter("genelAmacId", id).setParameter("dersAd", dersAd).getResultList();
		return cevaplar;
	}

	public List<String> yorumlariGetir(String dersAd, int id) {
		List<String> yorumlar = entityManager
				.createQuery(
						"select k.yorum from KabaDegerlendirmeKazanimCevap k where k.ozelAmac.genelAmac.id=:genelAmacId and k.kabaDegerlendirme.ders.dersAd=:dersAd")
				.setParameter("genelAmacId", id).setParameter("dersAd", dersAd).getResultList();
		return yorumlar;
	}

	public KabaDegerlendirmeKazanimCevap getKdcevap() {
		return kdcevap;
	}

	public void setKdcevap(KabaDegerlendirmeKazanimCevap kdcevap) {
		this.kdcevap = kdcevap;
	}

	public static int getI() {
		return i;
	}

	public static void setI(int i) {
		KabaDegerlendirmeService.i = i;
	}

	public static int getEvetCevap() {
		return evetCevap;
	}

	public static void setEvetCevap(int evetCevap) {
		KabaDegerlendirmeService.evetCevap = evetCevap;
	}

	public boolean dahaOnceDegerlendirilmismi(int ogrenciId, String dersAd) {
		Ders ders = entityManager.createQuery("select d from Ders d where d.dersAd=:dersAd", Ders.class)
				.setParameter("dersAd", dersAd).getSingleResult();
		Ogrenci ogrenci = entityManager.createQuery("select o from Ogrenci o where o.id=:ogrenciId", Ogrenci.class)
				.setParameter("ogrenciId", ogrenciId).getSingleResult();

		Query q = entityManager.createNativeQuery("select * from KabaDegerlendirme where ders_id= " + ders.getId()
				+ " and ogrenci_id=" + ogrenci.getId());
		List<KabaDegerlendirme> res = (List<KabaDegerlendirme>) q.getResultList();

		if (res.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public boolean dahaOnceKabaDegerlendirilmismi(int ogrenciId, String dersAd) {
		Ders ders = entityManager.createQuery("select d from Ders d where d.dersAd=:dersAd", Ders.class)
				.setParameter("dersAd", dersAd).getSingleResult();
		Ogrenci ogrenci = entityManager.createQuery("select o from Ogrenci o where o.id=:ogrenciId", Ogrenci.class)
				.setParameter("ogrenciId", ogrenciId).getSingleResult();

		List<KabaDegerlendirme> res = entityManager
				.createQuery(
						"SELECT k FROM KabaDegerlendirme k WHERE k.ders.id=:dersId AND k.ogrenci.id=:ogrenciId AND k.sinifAd=:ogrenciSinif")
				.setParameter("dersId", ders.getId()).setParameter("ogrenciId", ogrenci.getId())
				.setParameter("ogrenciSinif", ogrenci.getSinif().getSinifAd()).getResultList();

		if (res.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

}