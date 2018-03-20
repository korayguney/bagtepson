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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class OzelAmacEK implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToMany(mappedBy = "ozelAmac")
	private List<KazanimEK> kazanimlar;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "ozelAmacEK", cascade = CascadeType.ALL)
	private Set<DonemDegerlendirmeKazanimCevap> donemDegerlendirmeKazanimCevap;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "ozelAmacEK", cascade = CascadeType.ALL)
	private Set<YilSonuDegerlendirmeKazanimCevap> yilSonuDegerlendirmeKazanimCevap;

	@ManyToOne(cascade = CascadeType.ALL)
	private GenelAmac genelAmac;
	private String icerik;
	private double degerlendirmePuani;

	public OzelAmacEK(GenelAmac genelAmac, String icerik) {
		super();
		this.genelAmac = genelAmac;
		this.icerik = icerik;
	}

	public OzelAmacEK() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<KazanimEK> getKazanimlar() {
		return kazanimlar;
	}

	public void setKazanimlar(List<KazanimEK> kazanimlar) {
		this.kazanimlar = kazanimlar;
	}

	public GenelAmac getGenelAmac() {
		return genelAmac;
	}

	public void setGenelAmac(GenelAmac genelAmac) {
		this.genelAmac = genelAmac;
	}

	public String getIcerik() {
		return icerik;
	}

	public void setIcerik(String icerik) {
		this.icerik = icerik;
	}

	public double getDegerlendirmePuani() {
		return degerlendirmePuani;
	}

	public void setDegerlendirmePuani(double degerlendirmePuani) {
		this.degerlendirmePuani = degerlendirmePuani;
	}

	public Set<DonemDegerlendirmeKazanimCevap> getDonemDegerlendirmeKazanimCevap() {
		return donemDegerlendirmeKazanimCevap;
	}

	public void setDonemDegerlendirmeKazanimCevap(Set<DonemDegerlendirmeKazanimCevap> donemDegerlendirmeKazanimCevap) {
		this.donemDegerlendirmeKazanimCevap = donemDegerlendirmeKazanimCevap;
	}

	public Set<YilSonuDegerlendirmeKazanimCevap> getYilSonuDegerlendirmeKazanimCevap() {
		return yilSonuDegerlendirmeKazanimCevap;
	}

	public void setYilSonuDegerlendirmeKazanimCevap(
			Set<YilSonuDegerlendirmeKazanimCevap> yilSonuDegerlendirmeKazanimCevap) {
		this.yilSonuDegerlendirmeKazanimCevap = yilSonuDegerlendirmeKazanimCevap;
	}

}
