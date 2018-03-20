package com.bagtep.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.bagtep.domain.BireyselEgitimPlani;
import com.bagtep.domain.Ders;
import com.bagtep.domain.DonemDegerlendirme;
import com.bagtep.domain.DonemDegerlendirmeKazanimCevap;
import com.bagtep.domain.Ogrenci;
import com.bagtep.domain.OzelAmac;
import com.bagtep.domain.OzelAmacEK;

@Stateless
public class DonemDegerlendirmeService {

	DonemDegerlendirmeKazanimCevap ddcevap;
	static int i = 0;
	static double cevap = 0;
	private static double ozelAmacPuan = 0;

	@PersistenceContext
	private EntityManager entityManager;

	public String degerlendirmeKaydet(int ogrenciId, String dersAd, String degerlendirici,
			Map<Integer, Double> ozelAmaclarMap, Map<Integer, String> yorum, Map<Integer, OzelAmac> ozelAmacIdMap,
			Map<Integer, Double> ozelAmaclarMapEK, Map<Integer, String> ozelAmacYorumEK,
			Map<Integer, OzelAmacEK> ozelAmacIdMapEK) {

		Ders ders = entityManager.createQuery("select d from Ders d where d.dersAd=:dersAd", Ders.class)
				.setParameter("dersAd", dersAd).getSingleResult();
		Ogrenci ogrenci = entityManager.createQuery("select o from Ogrenci o where o.id=:ogrenciId", Ogrenci.class)
				.setParameter("ogrenciId", ogrenciId).getSingleResult();

		DonemDegerlendirme dd = new DonemDegerlendirme();
		dd.setDegerlendirmeTarihi(new Date());
		dd.setDegerlendirici(degerlendirici);
		dd.setDers(ders);
		dd.setOgrenci(ogrenci);
		dd.setSinifAd(ogrenci.getSinif().getSinifAd());
		
		entityManager.persist(dd);

		for (Integer key : ozelAmaclarMap.keySet()) {
			ddcevap = new DonemDegerlendirmeKazanimCevap();

			ddcevap.setYorum("" + yorum.get(key));
			ddcevap.setDonemDegerlendirme(dd);
			ddcevap.setOzelAmac(ozelAmacIdMap.get(key));
			double puan = 0.0;
			try {
				puan = Double.parseDouble("" + ozelAmaclarMap.get(key))
						* Double.parseDouble("" + ozelAmacIdMap.get(key).getDegerlendirmePuani());
				ddcevap.setDonemDegerlendirmeCevap(puan);
				entityManager.persist(ddcevap);
			} catch (Exception e) {
				break;
			}

		}

		for (Integer key : ozelAmaclarMapEK.keySet()) {
			ddcevap = new DonemDegerlendirmeKazanimCevap();

			double puan2 = Double.parseDouble("" + ozelAmaclarMapEK.get(key))
					* Double.parseDouble("" + ozelAmacIdMapEK.get(key).getDegerlendirmePuani());
			ddcevap.setDonemDegerlendirmeCevap(puan2);
			ddcevap.setYorum("" + ozelAmacYorumEK.get(key));
			ddcevap.setDonemDegerlendirme(dd);
			ddcevap.setOzelAmacEK(ozelAmacIdMapEK.get(key));

			entityManager.persist(ddcevap);
		}

		return "";

	}

	public void degerlendirmeGoruntuleKaydet(int ogrenciId, String dersAd, String degerlendirici,
			Map<Integer, Double> ozelAmaclarMap, Map<Integer, String> yorum, Map<Integer, OzelAmac> ozelAmacIdMap,
			Map<Integer, Double> ozelAmaclarMapEK, Map<Integer, String> ozelAmacYorumEK,
			Map<Integer, OzelAmacEK> ozelAmacIdMapEK) {

		Ders ders = entityManager.createQuery("select d from Ders d where d.dersAd=:dersAd", Ders.class)
				.setParameter("dersAd", dersAd).getSingleResult();
		Ogrenci ogrenci = entityManager.createQuery("select o from Ogrenci o where o.id=:ogrenciId", Ogrenci.class)
				.setParameter("ogrenciId", ogrenciId).getSingleResult();

		DonemDegerlendirme dd = donemDegerlendirmeGetir(ogrenciId, ders.getId());

		dd.setDegerlendirmeTarihi(new Date());
		dd.setDegerlendirici(degerlendirici);
		dd.setDers(ders);
		dd.setOgrenci(ogrenci);
		dd.setSinifAd(ogrenci.getSinif().getSinifAd());

		entityManager.merge(dd);

		List<DonemDegerlendirmeKazanimCevap> ddcevap = (List<DonemDegerlendirmeKazanimCevap>) entityManager
				.createQuery("select d from DonemDegerlendirmeKazanimCevap d where d.donemDegerlendirme.id=:donemId")
				.setParameter("donemId", dd.getId()).getResultList();

		for (Integer key : ozelAmaclarMap.keySet()) {
			ddcevap.get(i).setDonemDegerlendirmeCevap(Double.parseDouble("" + ozelAmaclarMap.get(key)));
			ddcevap.get(i).setYorum("" + yorum.get(key));
			ddcevap.get(i).setDonemDegerlendirme(dd);
			ddcevap.get(i).setOzelAmac(ozelAmacIdMap.get(key));

			entityManager.merge(ddcevap.get(i));
			i++;
			if (i == ozelAmaclarMap.size()) {
				break;
			}
		}

		for (Integer key : ozelAmaclarMapEK.keySet()) {

			ddcevap.get(i).setDonemDegerlendirmeCevap(Double.parseDouble("" + ozelAmaclarMapEK.get(key)));
			ddcevap.get(i).setYorum("" + ozelAmacYorumEK.get(key));
			ddcevap.get(i).setDonemDegerlendirme(dd);
			ddcevap.get(i).setOzelAmacEK(ozelAmacIdMapEK.get(key));

			entityManager.merge(ddcevap.get(i));
			i++;
			if (i == ozelAmaclarMapEK.size()) {
				break;
			}
		}
		i = 0;
	}

	public boolean dahaOnceDegerlendirilmismi(int ogrenciId, String dersAd) {
		Ders ders = entityManager.createQuery("select d from Ders d where d.dersAd=:dersAd", Ders.class)
				.setParameter("dersAd", dersAd).getSingleResult();
		Ogrenci ogrenci = entityManager.createQuery("select o from Ogrenci o where o.id=:ogrenciId", Ogrenci.class)
				.setParameter("ogrenciId", ogrenciId).getSingleResult();

		Query q = entityManager.createNativeQuery("select * from DonemDegerlendirme where ders_id= " + ders.getId()
				+ " and ogrenci_id=" + ogrenci.getId());
		List<DonemDegerlendirme> res = (List<DonemDegerlendirme>) q.getResultList();

		if (res.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public boolean dahaOnceDonemDegerlendirilmismi(int ogrenciId, String dersAd) {
		Ders ders = entityManager.createQuery("select d from Ders d where d.dersAd=:dersAd", Ders.class)
				.setParameter("dersAd", dersAd).getSingleResult();
		Ogrenci ogrenci = entityManager.createQuery("select o from Ogrenci o where o.id=:ogrenciId", Ogrenci.class)
				.setParameter("ogrenciId", ogrenciId).getSingleResult();

		List<DonemDegerlendirme> res = entityManager
				.createQuery(
						"SELECT d FROM DonemDegerlendirme d WHERE d.ders.id=:dersId AND d.ogrenci.id=:ogrenciId AND d.sinifAd=:ogrenciSinif")
				.setParameter("dersId", ders.getId()).setParameter("ogrenciId", ogrenci.getId())
				.setParameter("ogrenciSinif", ogrenci.getSinif().getSinifAd()).getResultList();

		if (res.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean dahaOnceBireyselEgitimPlaniOlusturulmusmu(int ogrenciId, String dersAd) {
		Ders ders = entityManager.createQuery("select d from Ders d where d.dersAd=:dersAd", Ders.class)
				.setParameter("dersAd", dersAd).getSingleResult();
		Ogrenci ogrenci = entityManager.createQuery("select o from Ogrenci o where o.id=:ogrenciId", Ogrenci.class)
				.setParameter("ogrenciId", ogrenciId).getSingleResult();

		List<BireyselEgitimPlani> res = entityManager
				.createQuery(
						"SELECT d FROM BireyselEgitimPlani d WHERE d.ders.id=:dersId AND d.ogrenci.id=:ogrenciId AND d.sinifAd=:ogrenciSinif", BireyselEgitimPlani.class)
				.setParameter("dersId", ders.getId()).setParameter("ogrenciId", ogrenci.getId())
				.setParameter("ogrenciSinif", ogrenci.getSinif().getSinifAd()).getResultList();

		if (res.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public void getDegerlendirme() {
	}

	public void getGelisim() {

	}

	public List<Double> degerlendirmePuanHesapla(int ogrenciId, String dersAd, String sinifAd) {
		Ders ders = entityManager.createQuery("select d from Ders d where d.dersAd=:dersAd", Ders.class)
				.setParameter("dersAd", dersAd).getSingleResult();
		DonemDegerlendirme dd = null;
		try {
			dd = (DonemDegerlendirme) entityManager
					.createQuery(
							"select d from DonemDegerlendirme d where d.ogrenci.id=:ogrenciId and d.ders.id=:dersId and d.sinifAd=:sinifAd")
					.setParameter("ogrenciId", ogrenciId).setParameter("dersId", ders.getId())
					.setParameter("sinifAd", sinifAd).getSingleResult();
		} catch (Exception e) {
		}
		
		for (int i = 0; i < dd.getDonemDegerlendirmeKazanimCevap().size(); i++) {
			cevap = cevap + dd.getDonemDegerlendirmeKazanimCevap().get(i).getDonemDegerlendirmeCevap();
			try {
					ozelAmacPuan = ozelAmacPuan + dd.getDonemDegerlendirmeKazanimCevap().get(i).getOzelAmac().getDegerlendirmePuani();	
			} catch (Exception e) {
					ozelAmacPuan = ozelAmacPuan + dd.getDonemDegerlendirmeKazanimCevap().get(i).getOzelAmacEK().getDegerlendirmePuani();	
			}
		}

		double sonuc100 =((cevap / ders.getGenelAmaclar().size())*100)/(ozelAmacPuan / ders.getGenelAmaclar().size());
		double sonuc40 = 52 + (sonuc100 * 0.48);
		
		List<Double> sonuclar = new ArrayList<>();
		sonuclar.add(sonuc100);
		sonuclar.add(sonuc40);
		cevap = 0;
		ozelAmacPuan = 0;
		return sonuclar;
	}

	public DonemDegerlendirme donemDegerlendirmeGetir2(int ogrenciId, int dersId) {
		Ders ders = entityManager.createQuery("select d from Ders d where d.id=:dersId", Ders.class)
				.setParameter("dersId", dersId).getSingleResult();
		Ogrenci ogrenci = entityManager.createQuery("select o from Ogrenci o where o.id=:ogrenciId", Ogrenci.class)
				.setParameter("ogrenciId", ogrenciId).getSingleResult();

		DonemDegerlendirme dd2 = (DonemDegerlendirme) entityManager
				.createQuery(
						"select d from DonemDegerlendirme d where d.ogrenci.id=:ogrenciId and d.ders.id=:dersId and d.sinifAd=:sinifAd")
				.setParameter("ogrenciId", ogrenciId).setParameter("dersId", dersId)
				.setParameter("sinifAd", ogrenci.getSinif().getSinifAd()).getSingleResult();
		return dd2;
	}

	public DonemDegerlendirme donemDegerlendirmeGetir(int ogrenciId, int dersId) {
		DonemDegerlendirme dd = (DonemDegerlendirme) entityManager
				.createQuery("select d from DonemDegerlendirme d where d.ogrenci.id=:ogrenciId and d.ders.id=:dersId")
				.setParameter("ogrenciId", ogrenciId).setParameter("dersId", dersId).getSingleResult();
		return dd;
	}

	public List<DonemDegerlendirme> donemDegerlendirmeleriGetir(int ogrenciId, int dersId) {

		List<DonemDegerlendirme> kd2 = entityManager
				.createQuery("select d from DonemDegerlendirme d where d.ogrenci.id=:ogrenciId and d.ders.id=:dersId")
				.setParameter("ogrenciId", ogrenciId).setParameter("dersId", dersId).getResultList();
		return kd2;
	}

	public DonemDegerlendirmeKazanimCevap getDdcevap() {
		return ddcevap;
	}

	public void setDdcevap(DonemDegerlendirmeKazanimCevap ddcevap) {
		this.ddcevap = ddcevap;
	}

	public static int getI() {
		return i;
	}

	public static void setI(int i) {
		DonemDegerlendirmeService.i = i;
	}

	public static double getCevap() {
		return cevap;
	}

	public static void setCevap(double cevap) {
		DonemDegerlendirmeService.cevap = cevap;
	}

	public static double getOzelAmacPuan() {
		return ozelAmacPuan;
	}

	public static void setOzelAmacPuan(double ozelAmacPuan) {
		DonemDegerlendirmeService.ozelAmacPuan = ozelAmacPuan;
	}

	public String degerlendirmePuanKaydet(int ogrenciId, String dersAd) {
		Ogrenci ogrenci = entityManager.createQuery("select o from Ogrenci o where o.id=:ogrenciId", Ogrenci.class)
				.setParameter("ogrenciId", ogrenciId).getSingleResult();
		List<Double> ddpuan = degerlendirmePuanHesapla(ogrenciId, dersAd, ogrenci.getSinif().getSinifAd());
		System.out.println("DD PUAN :" + ddpuan.get(0));
		DonemDegerlendirme dd = (DonemDegerlendirme) entityManager.createQuery("select d from DonemDegerlendirme d where d.ders.dersAd=:dersAd and d.ogrenci.id=:ogrenciId").setParameter("dersAd", dersAd).setParameter("ogrenciId", ogrenciId).getSingleResult();
		dd.setDegerlendirmePuani(ddpuan.get(0));
		entityManager.merge(dd);
		return ""+ddpuan.get(0);
	}
}