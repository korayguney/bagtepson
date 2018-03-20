package com.bagtep.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class BireyselEgitimPlani implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Date degerlendirmeTarihi;
	private String degerlendirici;
	private String sinifAd;
	private Date kabaDegerlendirmeTarihi;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "bireyselEgitimPlani", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BireyselEgitimPlaniRaporSecim> bireyselEgitimPlaniRaporSecim;

	@ManyToOne
	private Ogrenci ogrenci;

	@ManyToOne
	private Ders ders;

	public BireyselEgitimPlani() {
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

	public List<BireyselEgitimPlaniRaporSecim> getBireyselEgitimPlaniRaporSecim() {
		return bireyselEgitimPlaniRaporSecim;
	}

	public void setBireyselEgitimPlaniRaporSecim(List<BireyselEgitimPlaniRaporSecim> bireyselEgitimPlaniRaporSecim) {
		this.bireyselEgitimPlaniRaporSecim = bireyselEgitimPlaniRaporSecim;
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

	public Date getKabaDegerlendirmeTarihi() {
		return kabaDegerlendirmeTarihi;
	}

	public void setKabaDegerlendirmeTarihi(Date kabaDegerlendirmeTarihi) {
		this.kabaDegerlendirmeTarihi = kabaDegerlendirmeTarihi;
	}

	@Override
	public String toString() {
		return "KabaDegerlendirme [id=" + id + "]";
	}

}
