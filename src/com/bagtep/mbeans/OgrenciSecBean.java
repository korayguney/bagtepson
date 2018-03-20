package com.bagtep.mbeans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.bagtep.business.DersService;
import com.bagtep.business.DonemDegerlendirmeService;
import com.bagtep.business.KabaDegerlendirmeService;
import com.bagtep.business.OgrenciService;
import com.bagtep.business.OgretmenService;
import com.bagtep.business.SinifService;
import com.bagtep.domain.Ders;
import com.bagtep.domain.Ogrenci;
import com.bagtep.domain.Ogretmen;
import com.bagtep.domain.Sinif;

@ManagedBean
@ViewScoped
public class OgrenciSecBean implements Serializable {

	private String ders;
	private String sinif;
	private String ogrenci;
	private Map<String, String> dersler;
	private Map<String, String> siniflar;
	private List<Sinif> tumsiniflar;
	private List<Ders> tumdersler;
	private Set<Ders> ogretmeneAtananDersler;
	private Set<Sinif> ogretmeneAtananSiniflar;
	private Ogretmen ogretmen;

	private Map<String, String> ogrenciler2;
	private List<Ogrenci> ogrenciler;
	private List<Ogrenci> ogrenciler3;
	static int x = 0;

	@EJB
	private SinifService sinifService;
	@EJB
	private DersService dersService;
	@EJB
	private OgrenciService ogrenciService;
	@EJB
	private KabaDegerlendirmeService kabaDegerlendirmeService;
	@EJB
	private OgretmenService ogretmenService;
	@EJB
	private DonemDegerlendirmeService donemDegerlendirmeService;

	@ManagedProperty("#{mySessionScopedBean}")
	private MySessionScopedBean mySessionScopedBean;

	// SINIF-Ö�?RENCİ MAP YAPMAMIZ GEREK! DERSLE MAP LEMEYE GEREK YOK!!

	@PostConstruct
	public void init() {
		this.tumsiniflar = sinifService.getAllSinif();
		this.tumdersler = dersService.getAllDers();
		if (mySessionScopedBean.getRole().equals("Öğretmen")) {
			this.ogretmen = ogretmenService.ogretmeniGetir(mySessionScopedBean.getFirstname(),
					mySessionScopedBean.getLastname());
			mySessionScopedBean.setOgretmen(ogretmen);
			this.ogretmeneAtananDersler = dersService.ogretmeneAtananDersleriGetir(this.ogretmen.getId());
			this.ogretmeneAtananSiniflar = sinifService.ogretmeneAtananSiniflariGetir(this.ogretmen.getId());
		}

	}

	public String getDers() {
		return ders;
	}

	public void setDers(String ders) {
		this.ders = ders;
	}

	public Map<String, String> getDersler() {
		return dersler;
	}

	public void setDersler(Map<String, String> dersler) {
		this.dersler = dersler;
	}

	public String getSinif() {
		return sinif;
	}

	public void setSinif(String sinif) {
		this.sinif = sinif;
	}

	public Map<String, String> getSiniflar() {
		return siniflar;
	}

	public void setSiniflar(Map<String, String> siniflar) {
		this.siniflar = siniflar;
	}

	public String getOgrenci() {
		return ogrenci;
	}

	public void setOgrenci(String ogrenci) {
		this.ogrenci = ogrenci;
	}

	public Map<String, String> getOgrenciler2() {
		return ogrenciler2;
	}

	public void setOgrenciler2(Map<String, String> ogrenciler2) {
		this.ogrenciler2 = ogrenciler2;
	}

	public List<Sinif> getTumsiniflar() {
		return tumsiniflar;
	}

	public void setTumsiniflar(List<Sinif> tumsiniflar) {
		this.tumsiniflar = tumsiniflar;
	}

	public List<Ders> getTumdersler() {
		return tumdersler;
	}

	public void setTumdersler(List<Ders> tumdersler) {
		this.tumdersler = tumdersler;
	}

	public List<Ogrenci> getOgrenciler() {
		return ogrenciler;
	}

	public void setOgrenciler(List<Ogrenci> ogrenciler) {
		this.ogrenciler = ogrenciler;
	}

	public List<Ogrenci> getOgrenciler3() {
		return ogrenciler3;
	}

	public void setOgrenciler3(List<Ogrenci> ogrenciler3) {
		this.ogrenciler3 = ogrenciler3;
	}

	public MySessionScopedBean getMySessionScopedBean() {
		return mySessionScopedBean;
	}

	public void setMySessionScopedBean(MySessionScopedBean mySessionScopedBean) {
		this.mySessionScopedBean = mySessionScopedBean;
	}

	public void ogrencilisteleBEP() {
		mySessionScopedBean.setSinif(sinif);
		ogrenciler = ogrenciService.getSelectedOgrenciForClass(sinif);
		final int ogrencilerSayisi = ogrenciler.size();

		for (int i = 0; i < ogrencilerSayisi; i++) {
			Ogrenci ogrenci = ogrenciler.get(i - x);

			boolean degerlendirmeSonuc = ogrenciService.dahaOnceKabaDegerlendirilmismi(ogrenci.getId(), ders);

			if (!degerlendirmeSonuc) {
				ogrenciler.remove(ogrenci);
				x++;
			}
		}

		if (ogrenciler.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "",
					sinif + " sınıfında " + ders + " için Kaba Değerlendirmesi yapılmış öğrenci yoktur."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "", sinif + " sınıfında " + ders
							+ " için Kaba Değerlendirmesi yapılmış öğrenci sayısı : " + ogrenciler.size()));
		}

		x = 0;
	}

	public void ogrencilisteleDonemDegerlendirme() {
		mySessionScopedBean.setSinif(sinif);
		ogrenciler = ogrenciService.getSelectedOgrenciForClass(sinif);

		final int ogrencilerSayisiKD = ogrenciler.size();

		for (int i = 0; i < ogrencilerSayisiKD; i++) {
			Ogrenci ogrenci = ogrenciler.get(i - x);

			boolean degerlendirmeSonuc = ogrenciService.dahaOnceKabaDegerlendirilmismi(ogrenci.getId(), ders);
			boolean degerlendirmeSonucBEP = donemDegerlendirmeService.dahaOnceBireyselEgitimPlaniOlusturulmusmu(ogrenci.getId(), ders);

			if (!degerlendirmeSonuc || !degerlendirmeSonucBEP) {
				ogrenciler.remove(ogrenci);
				x++;
			}
		}
		x = 0;

		if (ogrenciler.isEmpty()) {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", sinif
					+ " sınıfında " + ders
					+ " için Kaba Değerlendirmesi veya Bireysel Eğitim Planlaması yapılmış öğrenci yoktur.\nLütfen önce Kaba Değerlendirmeleri veya Bireysel Eğitim Planlamalarını tamamlayınız!"));
		} else {

			final int ogrencilerSayisiDD = ogrenciler.size();

			for (int i = 0; i < ogrencilerSayisiDD; i++) {
				Ogrenci ogrenci = ogrenciler.get(i - x);

				boolean degerlendirmeSonuc = ogrenciService.dahaOnceDegerlendirilmismi(ogrenci.getId(), ders);

				if (degerlendirmeSonuc) {
					ogrenciler.remove(ogrenci);
					x++;
				}
			}

			if (ogrenciler.isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "",
						sinif + " sınıfında " + ders + " için Dönem Değerlendirmesi yapılmamış öğrenci yoktur."));
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "", sinif + " sınıfında " + ders
								+ " için Dönem Değerlendirmesi yapılabilir öğrenci sayısı : " + ogrenciler.size()));
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
						" Diğer öğrencilerin Dönem Değerlendirmesini yapabilmek için öncelikle Kaba Değerlendirmeleri ve Bireysel Eğitim Planlamalarını tamamlayınız!"));
			}

			x = 0;
		}
	}

	public void ogrencilisteleYilSonuDegerlendirmeGoruntule() {
		mySessionScopedBean.setSinif(sinif);
		ogrenciler = ogrenciService.getSelectedOgrenciForClass(sinif);
		final int ogrencilerSayisi = ogrenciler.size();

		for (int i = 0; i < ogrencilerSayisi; i++) {
			Ogrenci ogrenci = ogrenciler.get(i - x);

			boolean degerlendirmeSonuc = ogrenciService.dahaOnceYilSonuDegerlendirilmismi(ogrenci.getId(), ders);

			if (!degerlendirmeSonuc) {
				ogrenciler.remove(ogrenci);
				x++;
			}
		}

		if (ogrenciler.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "",
					sinif + " sınıfında " + ders + " için Yıl Sonu Değerlendirmesi yapılmış öğrenci yoktur."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "", sinif + " sınıfında " + ders
							+ " için Yıl Sonu Değerlendirmesi yapılmış öğrenci sayısı : " + ogrenciler.size()));
		}

		x = 0;
	}

	public void ogrencilisteleYilSonuDegerlendirme() {
		mySessionScopedBean.setSinif(sinif);
		ogrenciler = ogrenciService.getSelectedOgrenciForClass(sinif);
		final int ogrencilerSayisiKD = ogrenciler.size();

		for (int i = 0; i < ogrencilerSayisiKD; i++) {
			Ogrenci ogrenci = ogrenciler.get(i - x);

			boolean degerlendirmeSonuc = ogrenciService.dahaOnceKabaDegerlendirilmismi(ogrenci.getId(), ders);
			boolean degerlendirmeSonucBEP = donemDegerlendirmeService.dahaOnceBireyselEgitimPlaniOlusturulmusmu(ogrenci.getId(), ders);

			if (!degerlendirmeSonuc || !degerlendirmeSonucBEP) {
				ogrenciler.remove(ogrenci);
				x++;
			}
		}
		x = 0;

		if (ogrenciler.isEmpty()) {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", sinif
					+ " sınıfında " + ders
					+ " için Kaba Değerlendirmesi veya Bireysel Eğitim Planlaması yapılmış öğrenci yoktur.\nLütfen önce Kaba Değerlendirmeleri veya Bireysel Eğitim Planlamalarını tamamlayınız!"));
		
		} else {

			final int ogrencilerSayisiYSD = ogrenciler.size();

			for (int i = 0; i < ogrencilerSayisiYSD; i++) {
				Ogrenci ogrenci = ogrenciler.get(i - x);

				boolean degerlendirmeSonuc = ogrenciService.dahaOnceYilSonuDegerlendirilmismi(ogrenci.getId(), ders);

				if (degerlendirmeSonuc) {
					ogrenciler.remove(ogrenci);
					x++;
				}
			}

			if (ogrenciler.isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "",
						sinif + " sınıfında " + ders + " için Yıl Sonu Değerlendirmesi yapılmamış öğrenci yoktur."));
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "", sinif + " sınıfında " + ders
								+ " için Yıl Sonu Değerlendirmesi yapılabilir öğrenci sayısı : " + ogrenciler.size()));
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",
						" Diğer öğrencilerin Yıl Sonu Değerlendirmesini yapabilmek için öncelikle Kaba Değerlendirmeleri ve Bireysel Eğitim Planlamalarını tamamlayınız!"));
			}

			x = 0;
		}
	}

	public void ogrencilisteleDonemDegerlendirmeGoruntule() {
		mySessionScopedBean.setSinif(sinif);
		ogrenciler = ogrenciService.getSelectedOgrenciForClass(sinif);
		final int ogrencilerSayisi = ogrenciler.size();

		for (int i = 0; i < ogrencilerSayisi; i++) {
			Ogrenci ogrenci = ogrenciler.get(i - x);

			boolean degerlendirmeSonuc = ogrenciService.dahaOnceDegerlendirilmismi(ogrenci.getId(), ders);

			if (!degerlendirmeSonuc) {
				ogrenciler.remove(ogrenci);
				x++;
			}
		}

		if (ogrenciler.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "",
					sinif + " sınıfında " + ders + " için Dönem Değerlendirmesi yapılmış öğrenci yoktur."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "", sinif + " sınıfında " + ders
							+ " için Dönem Değerlendirmesi yapılmış öğrenci sayısı : " + ogrenciler.size()));
		}

		x = 0;
	}

	public void ogrencilisteleKabaDegerlendirme() {
		mySessionScopedBean.setSinif(sinif);
		ogrenciler = ogrenciService.getSelectedOgrenciForClass(sinif);
		final int ogrencilerSayisi = ogrenciler.size();

		for (int i = 0; i < ogrencilerSayisi; i++) {
			Ogrenci ogrenci = ogrenciler.get(i - x);

			boolean degerlendirmeSonuc = ogrenciService.dahaOnceKabaDegerlendirilmismi(ogrenci.getId(), ders);

			if (degerlendirmeSonuc) {
				ogrenciler.remove(ogrenci);
				x++;
			}
		}

		if (ogrenciler.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "",
					sinif + " sınıfında " + ders + " için Kaba Değerlendirmesi yapılmamış öğrenci yoktur."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "", sinif + " sınıfında " + ders
							+ " için Kaba Değerlendirmesi yapılmamış öğrenci sayısı : " + ogrenciler.size()));
		}
		x = 0;
	}

	public void ogrencilisteleKabaDegerlendirmeGoruntule() {
		mySessionScopedBean.setSinif(sinif);
		ogrenciler = ogrenciService.getSelectedOgrenciForClass(sinif);
		final int ogrencilerSayisi = ogrenciler.size();

		for (int i = 0; i < ogrencilerSayisi; i++) {
			Ogrenci ogrenci = ogrenciler.get(i - x);

			boolean degerlendirmeSonuc = ogrenciService.dahaOnceKabaDegerlendirilmismi(ogrenci.getId(), ders);

			if (!degerlendirmeSonuc) {
				ogrenciler.remove(ogrenci);
				x++;
			}
		}
		if (ogrenciler.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "",
					sinif + " sınıfında " + ders + " için Kaba Değerlendirmesi yapılmış öğrenci yoktur."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "", sinif + " sınıfında " + ders
							+ " için Kaba Değerlendirmesi yapılmış öğrenci sayısı : " + ogrenciler.size()));
		}
		x = 0;
	}

	public void ogrencilisteleKabaDegerlendirmeGelisimGoruntule() {
		mySessionScopedBean.setSinif(sinif);
		ogrenciler = ogrenciService.getSelectedOgrenciForClass(sinif);
		final int ogrencilerSayisi = ogrenciler.size();

		for (int i = 0; i < ogrencilerSayisi; i++) {
			Ogrenci ogrenci = ogrenciler.get(i - x);

			boolean degerlendirmeSonuc = ogrenciService.dahaOnceKabaDegerlendirilmismi(ogrenci.getId(), ders);

			if (!degerlendirmeSonuc) {
				ogrenciler.remove(ogrenci);
				x++;
			}
		}

		if (ogrenciler.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "",
					sinif + " sınıfında " + ders + " için Kaba Değerlendirmesi yapılmış öğrenci yoktur."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "", sinif + " sınıfında " + ders
							+ " için Kaba Değerlendirmesi yapılmış öğrenci sayısı : " + ogrenciler.size()));
		}
		x = 0;
	}

	public void ogrencilistele() {
		mySessionScopedBean.setSinif(sinif);
		ogrenciler = ogrenciService.getSelectedOgrenciForClass(sinif);
	}

	public void degerlendirmegoruntule() {
		kabaDegerlendirmeService.getDegerlendirme();
	}

	public void gelisimGoruntule() {
		kabaDegerlendirmeService.getGelisim();
	}

	public String onay(Ogrenci ogrenci) {

		mySessionScopedBean.setAd(ogrenci.getAd());
		mySessionScopedBean.setSoyad(ogrenci.getSoyad());
		mySessionScopedBean.setOgrenciId(ogrenci.getId());
		mySessionScopedBean.setSinif(ogrenci.getSinif().toString());
		mySessionScopedBean.setOgrencino(ogrenci.getOgrencino());

		return "users/admin/adminkabadegerlendirme";

	}

	public Set<Ders> getOgretmeneAtananDersler() {
		return ogretmeneAtananDersler;
	}

	public void setOgretmeneAtananDersler(Set<Ders> ogretmeneAtananDersler) {
		this.ogretmeneAtananDersler = ogretmeneAtananDersler;
	}

	public SinifService getSinifService() {
		return sinifService;
	}

	public void setSinifService(SinifService sinifService) {
		this.sinifService = sinifService;
	}

	public DersService getDersService() {
		return dersService;
	}

	public void setDersService(DersService dersService) {
		this.dersService = dersService;
	}

	public OgrenciService getOgrenciService() {
		return ogrenciService;
	}

	public void setOgrenciService(OgrenciService ogrenciService) {
		this.ogrenciService = ogrenciService;
	}

	public KabaDegerlendirmeService getKabaDegerlendirmeService() {
		return kabaDegerlendirmeService;
	}

	public void setKabaDegerlendirmeService(KabaDegerlendirmeService kabaDegerlendirmeService) {
		this.kabaDegerlendirmeService = kabaDegerlendirmeService;
	}

	public Ogretmen getOgretmen() {
		return ogretmen;
	}

	public void setOgretmen(Ogretmen ogretmen) {
		this.ogretmen = ogretmen;
	}

	public Set<Sinif> getOgretmeneAtananSiniflar() {
		return ogretmeneAtananSiniflar;
	}

	public void setOgretmeneAtananSiniflar(Set<Sinif> ogretmeneAtananSiniflar) {
		this.ogretmeneAtananSiniflar = ogretmeneAtananSiniflar;
	}

	public OgretmenService getOgretmenService() {
		return ogretmenService;
	}

	public void setOgretmenService(OgretmenService ogretmenService) {
		this.ogretmenService = ogretmenService;
	}

}
