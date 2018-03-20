package com.bagtep.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.bagtep.domain.Ders;
import com.bagtep.domain.DonemDegerlendirme;
import com.bagtep.domain.Ogrenci;
import com.bagtep.domain.OzelAmac;
import com.bagtep.domain.OzelAmacEK;
import com.bagtep.domain.Sinif;
import com.bagtep.domain.YilSonuDegerlendirme;
import com.bagtep.domain.YilSonuDegerlendirmeKazanimCevap;

@Stateless
public class YilSonuDegerlendirmeService {

	YilSonuDegerlendirmeKazanimCevap ysdcevap;
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

		YilSonuDegerlendirme ysd = new YilSonuDegerlendirme();
		ysd.setDegerlendirmeTarihi(new Date());
		ysd.setDegerlendirici(degerlendirici);
		ysd.setDers(ders);
		ysd.setOgrenci(ogrenci);
		ysd.setSinifAd(ogrenci.getSinif().getSinifAd());

		entityManager.persist(ysd);

		for (Integer key : ozelAmaclarMap.keySet()) {
			ysdcevap = new YilSonuDegerlendirmeKazanimCevap();

			ysdcevap.setYorum("" + yorum.get(key));
			ysdcevap.setYilSonuDegerlendirme(ysd);
			ysdcevap.setOzelAmac(ozelAmacIdMap.get(key));
			double puan = 0.0;
			try {
				puan = Double.parseDouble("" + ozelAmaclarMap.get(key))
						* Double.parseDouble("" + ozelAmacIdMap.get(key).getDegerlendirmePuani());
				ysdcevap.setYilSonuDegerlendirmeCevap(puan);
				entityManager.persist(ysdcevap);
			} catch (Exception e) {
				break;
			}

		}

		for (Integer key : ozelAmaclarMapEK.keySet()) {
			ysdcevap = new YilSonuDegerlendirmeKazanimCevap();

			double puan2 = Double.parseDouble("" + ozelAmaclarMapEK.get(key))
					* Double.parseDouble("" + ozelAmacIdMapEK.get(key).getDegerlendirmePuani());
			ysdcevap.setYilSonuDegerlendirmeCevap(puan2);
			ysdcevap.setYorum("" + ozelAmacYorumEK.get(key));
			ysdcevap.setYilSonuDegerlendirme(ysd);
			ysdcevap.setOzelAmacEK(ozelAmacIdMapEK.get(key));

			entityManager.persist(ysdcevap);
		}

		List<YilSonuDegerlendirme> yilSonuDegerlendirmeler = entityManager
				.createQuery("select y from YilSonuDegerlendirme y where y.ogrenci.id=:ogrenciId",
						YilSonuDegerlendirme.class)
				.setParameter("ogrenciId", ogrenciId).getResultList();
		List<Ders> dersler = entityManager.createQuery("select d from Ders d", Ders.class).getResultList();

		/*
		 * Aşağıdaki kısım tüm derslerden yıl sonu değerlendirmeleri yapılan öğrencinin şubesini otomatik 1 sene atlatır.
		 */
		if (yilSonuDegerlendirmeler.size() == dersler.size()) {
			String sinifAd = ogrenci.getSinif().getSinifAd();
			int sinifAd1 = Integer.valueOf(sinifAd.substring(0, 1));
			String sinifAd2 = sinifAd.substring(1);
			int sinifAd3 = sinifAd1 + 1;
			String yeniSinifAd = sinifAd3 + sinifAd2;
			System.out.println(ogrenci.getAd() + " YENI SINIFI ::::: " + yeniSinifAd);

			Sinif sinif = entityManager.createQuery("select s from Sinif s where s.sinifAd=:sinifAd", Sinif.class)
					.setParameter("sinifAd", yeniSinifAd).getSingleResult();
			ogrenci.setSinif(sinif);
		} else {
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

		YilSonuDegerlendirme ysd = yilSonuDegerlendirmeGetir(ogrenciId, ders.getId());

		ysd.setDegerlendirmeTarihi(new Date());
		ysd.setDegerlendirici(degerlendirici);
		ysd.setDers(ders);
		ysd.setOgrenci(ogrenci);
		ysd.setSinifAd(ogrenci.getSinif().getSinifAd());

		entityManager.merge(ysd);

		List<YilSonuDegerlendirmeKazanimCevap> ysdcevap = (List<YilSonuDegerlendirmeKazanimCevap>) entityManager
				.createQuery(
						"select y from YilSonuDegerlendirmeKazanimCevap y where y.yilSonuDegerlendirme.id=:yilSonuId")
				.setParameter("yilSonuId", ysd.getId()).getResultList();

		for (Integer key : ozelAmaclarMap.keySet()) {
			ysdcevap.get(i).setYilSonuDegerlendirmeCevap(Double.parseDouble("" + ozelAmaclarMap.get(key)));
			ysdcevap.get(i).setYorum("" + yorum.get(key));
			ysdcevap.get(i).setYilSonuDegerlendirme(ysd);
			ysdcevap.get(i).setOzelAmac(ozelAmacIdMap.get(key));

			entityManager.merge(ysdcevap.get(i));
			i++;
			if (i == ozelAmaclarMap.size()) {
				break;
			}
		}

		for (Integer key : ozelAmaclarMapEK.keySet()) {

			ysdcevap.get(i).setYilSonuDegerlendirmeCevap(Double.parseDouble("" + ozelAmaclarMapEK.get(key)));
			ysdcevap.get(i).setYorum("" + ozelAmacYorumEK.get(key));
			ysdcevap.get(i).setYilSonuDegerlendirme(ysd);
			ysdcevap.get(i).setOzelAmacEK(ozelAmacIdMapEK.get(key));

			entityManager.merge(ysdcevap.get(i));
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

		Query q = entityManager.createNativeQuery("select * from YilSonuDegerlendirme where ders_id= " + ders.getId()
				+ " and ogrenci_id=" + ogrenci.getId());
		List<YilSonuDegerlendirmeKazanimCevap> res = (List<YilSonuDegerlendirmeKazanimCevap>) q.getResultList();

		if (res.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public boolean dahaOnceYilSonuDegerlendirilmismi(int ogrenciId, String dersAd) {
		Ders ders = entityManager.createQuery("select d from Ders d where d.dersAd=:dersAd", Ders.class)
				.setParameter("dersAd", dersAd).getSingleResult();
		Ogrenci ogrenci = entityManager.createQuery("select o from Ogrenci o where o.id=:ogrenciId", Ogrenci.class)
				.setParameter("ogrenciId", ogrenciId).getSingleResult();

		List<YilSonuDegerlendirme> res = entityManager
				.createQuery(
						"SELECT y FROM YilSonuDegerlendirme y WHERE y.ders.id=:dersId AND y.ogrenci.id=:ogrenciId AND y.sinifAd=:ogrenciSinif")
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
		YilSonuDegerlendirme ysd = null;
		try {
			ysd = (YilSonuDegerlendirme) entityManager
					.createQuery(
							"select y from YilSonuDegerlendirme y where y.ogrenci.id=:ogrenciId and y.ders.id=:dersId and y.sinifAd=:sinifAd")
					.setParameter("ogrenciId", ogrenciId).setParameter("dersId", ders.getId())
					.setParameter("sinifAd", sinifAd).getSingleResult();
		} catch (Exception e) {
		}

		for (int i = 0; i < ysd.getYilSonuDegerlendirmeKazanimCevap().size(); i++) {
			cevap = cevap + ysd.getYilSonuDegerlendirmeKazanimCevap().get(i).getYilSonuDegerlendirmeCevap();
			try {
				setOzelAmacPuan(getOzelAmacPuan() + ysd.getYilSonuDegerlendirmeKazanimCevap().get(i).getOzelAmac().getDegerlendirmePuani());	
			} catch (Exception e) {
				setOzelAmacPuan(getOzelAmacPuan() + ysd.getYilSonuDegerlendirmeKazanimCevap().get(i).getOzelAmacEK().getDegerlendirmePuani());		
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

	public YilSonuDegerlendirme yilSonuDegerlendirmeGetir2(int ogrenciId, int dersId) {
		Ders ders = entityManager.createQuery("select d from Ders d where d.id=:dersId", Ders.class)
				.setParameter("dersId", dersId).getSingleResult();
		Ogrenci ogrenci = entityManager.createQuery("select o from Ogrenci o where o.id=:ogrenciId", Ogrenci.class)
				.setParameter("ogrenciId", ogrenciId).getSingleResult();

		YilSonuDegerlendirme ysd = (YilSonuDegerlendirme) entityManager
				.createQuery(
						"select y from YilSonuDegerlendirme y where y.ogrenci.id=:ogrenciId and y.ders.id=:dersId and y.sinifAd=:sinifAd")
				.setParameter("ogrenciId", ogrenciId).setParameter("dersId", dersId)
				.setParameter("sinifAd", ogrenci.getSinif().getSinifAd()).getSingleResult();
		return ysd;
	}

	public YilSonuDegerlendirme yilSonuDegerlendirmeGetir(int ogrenciId, int dersId) {
		YilSonuDegerlendirme ysd = (YilSonuDegerlendirme) entityManager
				.createQuery("select y from YilSonuDegerlendirme y where y.ogrenci.id=:ogrenciId and y.ders.id=:dersId")
				.setParameter("ogrenciId", ogrenciId).setParameter("dersId", dersId).getSingleResult();
		return ysd;
	}

	public List<YilSonuDegerlendirme> yilSonuDegerlendirmeleriGetir(int ogrenciId, int dersId) {

		List<YilSonuDegerlendirme> kd2 = entityManager
				.createQuery("select y from YilSonuDegerlendirme y where y.ogrenci.id=:ogrenciId and y.ders.id=:dersId")
				.setParameter("ogrenciId", ogrenciId).setParameter("dersId", dersId).getResultList();
		return kd2;
	}

	public YilSonuDegerlendirmeKazanimCevap getYsdcevap() {
		return ysdcevap;
	}

	public void setYsdcevap(YilSonuDegerlendirmeKazanimCevap ysdcevap) {
		this.ysdcevap = ysdcevap;
	}

	public static int getI() {
		return i;
	}

	public static void setI(int i) {
		YilSonuDegerlendirmeService.i = i;
	}

	public static double getCevap() {
		return cevap;
	}

	public static void setCevap(double cevap) {
		YilSonuDegerlendirmeService.cevap = cevap;
	}

	public static double getOzelAmacPuan() {
		return ozelAmacPuan;
	}

	public static void setOzelAmacPuan(double ozelAmacPuan) {
		YilSonuDegerlendirmeService.ozelAmacPuan = ozelAmacPuan;
	}
	
	public String degerlendirmePuanKaydet(int ogrenciId, String dersAd) {
		Ogrenci ogrenci = entityManager.createQuery("select o from Ogrenci o where o.id=:ogrenciId", Ogrenci.class)
				.setParameter("ogrenciId", ogrenciId).getSingleResult();
		List<Double> ydpuan = degerlendirmePuanHesapla(ogrenciId, dersAd, ogrenci.getSinif().getSinifAd());
		System.out.println("YD PUAN :" + ydpuan.get(0));
		YilSonuDegerlendirme yd = (YilSonuDegerlendirme) entityManager.createQuery("select d from YilSonuDegerlendirme d where d.ders.dersAd=:dersAd and d.ogrenci.id=:ogrenciId").setParameter("dersAd", dersAd).setParameter("ogrenciId", ogrenciId).getSingleResult();
		yd.setDegerlendirmePuani(ydpuan.get(0));
		entityManager.merge(yd);
		return ""+ydpuan.get(0);
	}

}