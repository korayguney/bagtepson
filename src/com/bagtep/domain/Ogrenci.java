package com.bagtep.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Ogrenci implements Cloneable, Serializable {

	private static final long serialVersionUID = 2051419634586362750L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String ad;
	private String soyad;
	private int ogrencino;
	private String cinsiyet;
	private String dogumTarih;
	private String veliad;
	private String velisoyad;
	private String telefon;
	private String evadres;
	private boolean kabaDegerlendirmeDurumu;
	private boolean donemDegerlendirmeDurumu;

	@ManyToOne
	private Sinif sinif;

	@ManyToMany
	private List<Ders> dersler;
	
	@OneToMany(mappedBy = "ogrenci")
	private Set<KabaDegerlendirme> kabadegerlendirmeler;
	
	@OneToMany(mappedBy = "ogrenci")
	private Set<BireyselEgitimPlani> bireyselegitimplani;
	
	@OneToMany(mappedBy = "ogrenci")
	private Set<DonemDegerlendirme> donemdegerlendirmeler;
	
	@OneToMany(mappedBy = "ogrenci")
	private Set<YilSonuDegerlendirme> yilsonudegerlendirmeler;

	public Ogrenci() {
		// TODO Auto-generated constructor stub
	}

	public Ogrenci(String ad, String soyad, int ogrencino, String cinsiyet, String veliad, String velisoyad,
			String telefon, String evadres) {
		super();
		this.ad = ad;
		this.soyad = soyad;
		this.ogrencino = ogrencino;
		this.cinsiyet = cinsiyet;
		this.veliad = veliad;
		this.velisoyad = velisoyad;
		this.telefon = telefon;
		this.evadres = evadres;
	}

	public Ogrenci(String ad, String soyad, int ogrencino, String cinsiyet, String veliad, String velisoyad,
			String telefon, String evadres, Sinif sinif) {
		super();
		this.ad = ad;
		this.soyad = soyad;
		this.ogrencino = ogrencino;
		this.cinsiyet = cinsiyet;
		this.veliad = veliad;
		this.velisoyad = velisoyad;
		this.telefon = telefon;
		this.evadres = evadres;
		this.sinif = sinif;
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

	public int getOgrencino() {
		return ogrencino;
	}

	public void setOgrencino(int ogrencino) {
		this.ogrencino = ogrencino;
	}

	public String getCinsiyet() {
		return cinsiyet;
	}

	public void setCinsiyet(String cinsiyet) {
		this.cinsiyet = cinsiyet;
	}

	public String getDogumTarih() {
		return dogumTarih;
	}

	public void setDogumTarih(String dogumTarih) {
		this.dogumTarih = dogumTarih;
	}

	public Sinif getSinif() {
		return sinif;
	}

	public void setSinif(Sinif sinif) {
		this.sinif = sinif;
	}

	public String getVeliad() {
		return veliad;
	}

	public void setVeliad(String veliad) {
		this.veliad = veliad;
	}

	public String getVelisoyad() {
		return velisoyad;
	}

	public void setVelisoyad(String velisoyad) {
		this.velisoyad = velisoyad;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getEvadres() {
		return evadres;
	}

	public void setEvadres(String evadres) {
		this.evadres = evadres;
	}

	public List<Ders> getDersler() {
		return dersler;
	}

	public void setDersler(List<Ders> dersler) {
		this.dersler = dersler;
	}

	public Set<KabaDegerlendirme> getKabadegerlendirmeler() {
		return kabadegerlendirmeler;
	}

	public void setKabadegerlendirmeler(Set<KabaDegerlendirme> kabadegerlendirmeler) {
		this.kabadegerlendirmeler = kabadegerlendirmeler;
	}

	public boolean isKabaDegerlendirmeDurumu() {
		return kabaDegerlendirmeDurumu;
	}

	public void setKabaDegerlendirmeDurumu(boolean kabaDegerlendirmeDurumu) {
		this.kabaDegerlendirmeDurumu = kabaDegerlendirmeDurumu;
	}

	public boolean isDonemDegerlendirmeDurumu() {
		return donemDegerlendirmeDurumu;
	}

	public void setDonemDegerlendirmeDurumu(boolean donemDegerlendirmeDurumu) {
		this.donemDegerlendirmeDurumu = donemDegerlendirmeDurumu;
	}

	public Set<BireyselEgitimPlani> getBireyselegitimplani() {
		return bireyselegitimplani;
	}

	public void setBireyselegitimplani(Set<BireyselEgitimPlani> bireyselegitimplani) {
		this.bireyselegitimplani = bireyselegitimplani;
	}

	public Set<DonemDegerlendirme> getDonemdegerlendirmeler() {
		return donemdegerlendirmeler;
	}

	public void setDonemdegerlendirmeler(Set<DonemDegerlendirme> donemdegerlendirmeler) {
		this.donemdegerlendirmeler = donemdegerlendirmeler;
	}

	public Set<YilSonuDegerlendirme> getYilsonudegerlendirmeler() {
		return yilsonudegerlendirmeler;
	}

	public void setYilsonudegerlendirmeler(Set<YilSonuDegerlendirme> yilsonudegerlendirmeler) {
		this.yilsonudegerlendirmeler = yilsonudegerlendirmeler;
	}

	@Override
	public String toString() {
		return ad + " " + soyad;
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
