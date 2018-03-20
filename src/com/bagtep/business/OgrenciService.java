package com.bagtep.business;

import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bagtep.domain.BireyselEgitimPlani;
import com.bagtep.domain.BireyselEgitimPlaniRaporSecim;
import com.bagtep.domain.Ders;
import com.bagtep.domain.DonemDegerlendirme;
import com.bagtep.domain.DonemDegerlendirmeKazanimCevap;
import com.bagtep.domain.KabaDegerlendirme;
import com.bagtep.domain.KabaDegerlendirmeKazanimCevap;
import com.bagtep.domain.Ogrenci;
import com.bagtep.domain.Sinif;
import com.bagtep.domain.User;
import com.bagtep.domain.YilSonuDegerlendirme;
import com.bagtep.domain.YilSonuDegerlendirmeKazanimCevap;
import com.bagtep.mbeans.MySessionScopedBean;

@Stateless

public class OgrenciService {
	@PersistenceContext
	private EntityManager entityManager;

	public void saveOgrenci(Ogrenci newOgrenci, int sinifId) {
		Sinif sinif = entityManager.find(Sinif.class, sinifId);
		newOgrenci.setSinif(sinif);
		entityManager.persist(newOgrenci);
	}

	public int getId(int ogrencino) {

		List<Ogrenci> ogrenciler = entityManager
				.createQuery("select o from Ogrenci o where o.ogrencino=:ogrencino", Ogrenci.class)
				.setParameter("ogrencino", ogrencino).getResultList();
		int id = ogrenciler.get(0).getId();
		return id;
	}

	public String getOgrenciAd(int oid) {
		return entityManager.find(Ogrenci.class, oid).getAd();
	}

	public String getOgrenciSoyad(int oid) {

		return entityManager.find(Ogrenci.class, oid).getSoyad();

	}

	public int getOgrenciNo(int oid) {

		return entityManager.find(Ogrenci.class, oid).getOgrencino();

	}

	public Sinif getOgrenciSinif(int oid) {

		return entityManager.find(Ogrenci.class, oid).getSinif();

	}
	
	public String getOgrenciDogum(int oid) {

		return entityManager.find(Ogrenci.class, oid).getDogumTarih();

	}

	public List<Ogrenci> getAllOgrenci() {
		return entityManager.createQuery("select o from Ogrenci o", Ogrenci.class).getResultList();
	}

	public void deleteOgrenci(int ogrenciId) {
		Ogrenci ogrenci = entityManager.find(Ogrenci.class, ogrenciId);
		
		Set<KabaDegerlendirme> kabaDegerlendirmeler = ogrenci.getKabadegerlendirmeler();
		if(kabaDegerlendirmeler != null){
			ogrenci.getKabadegerlendirmeler().clear();
			for (KabaDegerlendirme kabaDegerlendirme : kabaDegerlendirmeler) {
				
				List<KabaDegerlendirmeKazanimCevap>kabaDegerlendirmeKazanimCevaplar = kabaDegerlendirme.getKabaDegerlendirmeKazanimCevap();
				
				if (kabaDegerlendirmeKazanimCevaplar != null) {
					kabaDegerlendirme.getKabaDegerlendirmeKazanimCevap().clear();
					for (KabaDegerlendirmeKazanimCevap kabaDegerlendirmeKazanimCevap : kabaDegerlendirmeKazanimCevaplar) {
						kabaDegerlendirmeKazanimCevap.setKabaDegerlendirme(null);
						entityManager.remove(kabaDegerlendirmeKazanimCevap);
					}
				}
								
				kabaDegerlendirme.setOgrenci(null);
				entityManager.remove(kabaDegerlendirme);
			}
		}
		
		Set<BireyselEgitimPlani> bireyselDegerlendirmeler = ogrenci.getBireyselegitimplani();
		if(bireyselDegerlendirmeler != null){
			ogrenci.getBireyselegitimplani().clear();
			for (BireyselEgitimPlani bireyselDegerlendirme : bireyselDegerlendirmeler) {
				
				List<BireyselEgitimPlaniRaporSecim> bireyselCevaplar = bireyselDegerlendirme.getBireyselEgitimPlaniRaporSecim();
				
				if (bireyselCevaplar != null) {
					bireyselDegerlendirme.getBireyselEgitimPlaniRaporSecim().clear();
					for (BireyselEgitimPlaniRaporSecim bireyselCevaplaar : bireyselCevaplar) {
						bireyselCevaplaar.setBireyselEgitimPlani(null);
						entityManager.remove(bireyselCevaplaar);
					}
				}
								
				bireyselDegerlendirme.setOgrenci(null);
				entityManager.remove(bireyselDegerlendirme);
			}
		}
		
		Set<DonemDegerlendirme> donemDegerlendirmeler = ogrenci.getDonemdegerlendirmeler();
		if(donemDegerlendirmeler != null){
			ogrenci.getDonemdegerlendirmeler().clear();
			for (DonemDegerlendirme donemDegerlendirme : donemDegerlendirmeler) {
				
				List<DonemDegerlendirmeKazanimCevap> donemDegerlendirmeKazanimCevaplar = donemDegerlendirme.getDonemDegerlendirmeKazanimCevap();
				
				if (donemDegerlendirmeKazanimCevaplar != null) {
					donemDegerlendirme.getDonemDegerlendirmeKazanimCevap().clear();
					for (DonemDegerlendirmeKazanimCevap donemDegerlendirmeKazanimCevap : donemDegerlendirmeKazanimCevaplar) {
						donemDegerlendirmeKazanimCevap.setDonemDegerlendirme(null);
						entityManager.remove(donemDegerlendirmeKazanimCevap);
					}
				}
								
				donemDegerlendirme.setOgrenci(null);
				entityManager.remove(donemDegerlendirme);
			}
		}
		
		Set<YilSonuDegerlendirme> yilsonuDegerlendirmeler = ogrenci.getYilsonudegerlendirmeler();
		if(yilsonuDegerlendirmeler != null){
			ogrenci.getYilsonudegerlendirmeler().clear();
			for (YilSonuDegerlendirme yilsonuDegerlendirme : yilsonuDegerlendirmeler) {
				
				List<YilSonuDegerlendirmeKazanimCevap> yilsonuDegerlendirmeKazanimCevaplar = yilsonuDegerlendirme.getYilSonuDegerlendirmeKazanimCevap();
				
				if (yilsonuDegerlendirmeKazanimCevaplar != null) {
					yilsonuDegerlendirme.getYilSonuDegerlendirmeKazanimCevap().clear();
					for (YilSonuDegerlendirmeKazanimCevap yilsonuDegerlendirmeKazanimCevap : yilsonuDegerlendirmeKazanimCevaplar) {
						yilsonuDegerlendirmeKazanimCevap.setYilSonuDegerlendirme(null);
						entityManager.remove(yilsonuDegerlendirmeKazanimCevap);
					}
				}
								
				yilsonuDegerlendirme.setOgrenci(null);
				entityManager.remove(yilsonuDegerlendirme);
			}
		}
		
		
		
		List<Ders> dersler = ogrenci.getDersler();
		if(dersler != null){
			System.out.println("Dersler boş değil silinecek");
			ogrenci.getDersler().clear();
			for (Ders ders : dersler) {
				ders.setOgrenciler(null);
				entityManager.remove(ders);
				System.out.println("------ Dersler silindi");
			}
		}
		
//		Sinif sinif = ogrenci.getSinif();
//		if(sinif != null){
//			System.out.println("Sınıf boş değil silinecek");
//			ogrenci.setSinif(null);
//			entityManager.remove(sinif);
//			System.out.println("------ Sınıf silindi");
//
//		}
		
		entityManager.remove(ogrenci);
	}

	@SuppressWarnings("null")
	public User getUserInSession() {
		MySessionScopedBean mysess = null;
		return entityManager.find(User.class, mysess.getId());
	}

	public List<Ogrenci> getSelectedOgrenciForClass(String sinif) {
		String ogrencisinif = sinif;
		List<Ogrenci> ogrenciler = entityManager
				.createQuery("select o from Ogrenci o where o.sinif.sinifAd=:sinif", Ogrenci.class)
				.setParameter("sinif", sinif).getResultList();
		return ogrenciler;
	}

	public void updateOgrenci(Ogrenci ogrenci) {
		entityManager.merge(ogrenci);
	}

	public void addNewOgrenci(Ogrenci newOgrenci) {
		entityManager.persist(newOgrenci);
	}

	public void ogrenciyeDersAta(int ogrenciId, int dersId) {

		Ogrenci ogrenci = entityManager.find(Ogrenci.class, ogrenciId);
		Ders ders = entityManager.find(Ders.class, dersId);

		ogrenci.getDersler().add(ders);

		entityManager.merge(ogrenci);
		entityManager.merge(ders);

	}

	public boolean dahaOnceDegerlendirilmismi(int ogrenciId, String dersAd) {
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