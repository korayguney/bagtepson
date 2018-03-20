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

@Entity
public class Ogretmen implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String ad;
	private String soyad;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Sinif> siniflar;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Ders> dersler;

	public Ogretmen() {
		// TODO Auto-generated constructor stub
	}

	public Ogretmen(String ad, String soyad) {
		super();
		this.ad = ad;
		this.soyad = soyad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getSoyad() {
		return soyad;
	}

	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}

	public Set<Ders> getDersler() {
		return dersler;
	}

	public void setDersler(Set<Ders> dersler) {
		this.dersler = dersler;
	}

	public Set<Sinif> getSiniflar() {
		return siniflar;
	}

	public void setSiniflar(Set<Sinif> siniflar) {
		this.siniflar = siniflar;
	}

}
