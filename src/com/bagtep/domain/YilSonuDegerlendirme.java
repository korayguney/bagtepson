package com.bagtep.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class YilSonuDegerlendirme implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Date degerlendirmeTarihi;
	private String degerlendirici;
	private String sinifAd;
	private double degerlendirmePuani;

	@OneToMany(mappedBy = "yilSonuDegerlendirme")
	private List<YilSonuDegerlendirmeKazanimCevap> yilSonuDegerlendirmeKazanimCevap;

	@ManyToOne
	private Ogrenci ogrenci;

	@ManyToOne
	private Ders ders;

	public YilSonuDegerlendirme() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Ogrenci getOgrenci() {
		return ogrenci;
	}

	public void setOgrenci(Ogrenci ogrenci) {
		this.ogrenci = ogrenci;
	}

	public Ders getDers() {
		return ders;
	}

	public void setDers(Ders ders) {
		this.ders = ders;
	}

	public Date getDegerlendirmeTarihi() {
		return degerlendirmeTarihi;
	}

	public void setDegerlendirmeTarihi(Date degerlendirmeTarihi) {
		this.degerlendirmeTarihi = degerlendirmeTarihi;
	}

	public String getDegerlendirici() {
		return degerlendirici;
	}

	public void setDegerlendirici(String degerlendirici) {
		this.degerlendirici = degerlendirici;
	}

	public List<YilSonuDegerlendirmeKazanimCevap> getYilSonuDegerlendirmeKazanimCevap() {
		return yilSonuDegerlendirmeKazanimCevap;
	}

	public void setYilSonuDegerlendirmeKazanimCevap(
			List<YilSonuDegerlendirmeKazanimCevap> yilSonuDegerlendirmeKazanimCevap) {
		this.yilSonuDegerlendirmeKazanimCevap = yilSonuDegerlendirmeKazanimCevap;
	}

	public String getSinifAd() {
		return sinifAd;
	}

	public void setSinifAd(String sinifAd) {
		this.sinifAd = sinifAd;
	}

	@Override
	public String toString() {
		Date ddtarihi = getDegerlendirmeTarihi();
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
		String date = DATE_FORMAT.format(ddtarihi);

		return "Yıl Sonu Değ.(" + sinifAd + ")-(" + date + ")";
	}

	public double getDegerlendirmePuani() {
		return degerlendirmePuani;
	}

	public void setDegerlendirmePuani(double degerlendirmePuani) {
		this.degerlendirmePuani = degerlendirmePuani;
	}

}
