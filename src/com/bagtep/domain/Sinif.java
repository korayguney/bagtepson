package com.bagtep.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Sinif implements Serializable {

	private static final long serialVersionUID = -6873061229863242749L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "sinif")
	private List<Ogrenci> ogrenciler;

	@ManyToMany(mappedBy = "siniflar")
	private Set<Ogretmen> ogretmen;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Ders> dersler;

	private String sinifAd;

	public Sinif() {
		super();
	}

	public Sinif(List<Ogrenci> ogrenciler, String sinifAd) {
		super();
		this.ogrenciler = ogrenciler;
		this.sinifAd = sinifAd;
	}

	public Sinif(String sinifAd) {
		super();
		this.sinifAd = sinifAd;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Ogrenci> getOgrenciler() {
		return ogrenciler;
	}

	public void setOgrenciler(List<Ogrenci> ogrenciler) {
		this.ogrenciler = ogrenciler;
	}

	public String getSinifAd() {
		return sinifAd;
	}

	public void setSinifAd(String sinifAd) {
		this.sinifAd = sinifAd;
	}

	public Set<Ogretmen> getOgretmen() {
		return ogretmen;
	}

	public void setOgretmen(Set<Ogretmen> ogretmen) {
		this.ogretmen = ogretmen;
	}

	public Set<Ders> getDersler() {
		return dersler;
	}

	public void setDersler(Set<Ders> dersler) {
		this.dersler = dersler;
	}

	@Override
	public String toString() {
		return sinifAd;
	}

}
