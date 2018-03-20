package com.bagtep.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Ders implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToMany(mappedBy = "ders")
	private List<GenelAmac> genelAmaclar;

	@ManyToMany(mappedBy = "dersler")
	private List<Ogrenci> ogrenciler;

	@ManyToMany(mappedBy = "dersler")
	private List<Ogretmen> ogretmenler;

	@ManyToMany(mappedBy = "dersler")
	private List<Sinif> siniflar;

	@OneToMany(mappedBy = "ders")
	private List<KabaDegerlendirme> kabadegerlendirmeler;

	private String dersAd;

	public Ders(String dersAd) {
		super();
		this.dersAd = dersAd;
	}

	public Ders() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<GenelAmac> getGenelAmaclar() {
		return genelAmaclar;
	}

	public void setGenelAmaclar(List<GenelAmac> genelAmaclar) {
		this.genelAmaclar = genelAmaclar;
	}

	public String getDersAd() {
		return dersAd;
	}

	public void setDersAd(String dersAd) {
		this.dersAd = dersAd;
	}

	public List<Ogrenci> getOgrenciler() {
		return ogrenciler;
	}

	public void setOgrenciler(List<Ogrenci> ogrenciler) {
		this.ogrenciler = ogrenciler;
	}

	public List<Ogretmen> getOgretmenler() {
		return ogretmenler;
	}

	public void setOgretmenler(List<Ogretmen> ogretmenler) {
		this.ogretmenler = ogretmenler;
	}

	public List<KabaDegerlendirme> getKabadegerlendirmeler() {
		return kabadegerlendirmeler;
	}

	public void setKabadegerlendirmeler(List<KabaDegerlendirme> kabadegerlendirmeler) {
		this.kabadegerlendirmeler = kabadegerlendirmeler;
	}

	public List<Sinif> getSiniflar() {
		return siniflar;
	}

	public void setSiniflar(List<Sinif> siniflar) {
		this.siniflar = siniflar;
	}

	@Override
	public String toString() {
		return dersAd;
	}

}
