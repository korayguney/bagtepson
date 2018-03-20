package com.bagtep.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class KabaDegerlendirme implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Date degerlendirmeTarihi;
	private String degerlendirici;
	private String sinifAd;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "kabaDegerlendirme")
	private List<KabaDegerlendirmeKazanimCevap> kabaDegerlendirmeKazanimCevap;

	@ManyToOne
	private Ogrenci ogrenci;

	@ManyToOne
	private Ders ders;

	public KabaDegerlendirme() {
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

	public List<KabaDegerlendirmeKazanimCevap> getKabaDegerlendirmeKazanimCevap() {
		return kabaDegerlendirmeKazanimCevap;
	}

	public void setKabaDegerlendirmeKazanimCevap(List<KabaDegerlendirmeKazanimCevap> kabaDegerlendirmeKazanimCevap) {
		this.kabaDegerlendirmeKazanimCevap = kabaDegerlendirmeKazanimCevap;
	}

	public String getDegerlendirici() {
		return degerlendirici;
	}

	public void setDegerlendirici(String degerlendirici) {
		this.degerlendirici = degerlendirici;
	}

	public String getSinifAd() {
		return sinifAd;
	}

	public void setSinifAd(String sinifAd) {
		this.sinifAd = sinifAd;
	}

	@Override
	public String toString() {
		Date kdtarihi = getDegerlendirmeTarihi();
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
		String date = DATE_FORMAT.format(kdtarihi);

		return "Kaba Değ.(" + sinifAd + ")-(" + date + ")";
	}

}
