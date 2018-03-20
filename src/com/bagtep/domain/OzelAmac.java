package com.bagtep.domain;

import java.io.Serializable;
import java.util.LinkedHashSet;
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
public class OzelAmac implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "ozelAmac", cascade = CascadeType.ALL)
	private Set<Kazanim> kazanimlar = new LinkedHashSet<>();

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "ozelAmac", cascade = CascadeType.ALL)
	private List<KabaDegerlendirmeKazanimCevap> kabaDegerlendirmeKazanimCevap;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "ozelAmac", cascade = CascadeType.ALL)
	private Set<DonemDegerlendirmeKazanimCevap> donemDegerlendirmeKazanimCevap;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "ozelAmac", cascade = CascadeType.ALL)
	private Set<YilSonuDegerlendirmeKazanimCevap> yilSonuDegerlendirmeKazanimCevap;

	@ManyToOne
	private GenelAmac genelAmac;
	private String icerik;
	private double degerlendirmePuani;

	public OzelAmac(GenelAmac genelAmac, String icerik) {
		super();
		this.genelAmac = genelAmac;
		this.icerik = icerik;
		System.out.println("");
	}

	public OzelAmac() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<Kazanim> getKazanimlar() {
		return kazanimlar;
	}

	public void setKazanimlar(Set<Kazanim> kazanimlar) {
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

	public List<KabaDegerlendirmeKazanimCevap> getKabaDegerlendirmeKazanimCevap() {
		return kabaDegerlendirmeKazanimCevap;
	}

	public void setKabaDegerlendirmeKazanimCevap(List<KabaDegerlendirmeKazanimCevap> kabaDegerlendirmeKazanimCevap) {
		this.kabaDegerlendirmeKazanimCevap = kabaDegerlendirmeKazanimCevap;
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

	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((kazanimlar == null) ? 0 : kazanimlar.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OzelAmac other = (OzelAmac) obj;
		if (kazanimlar == null) {
			if (other.kazanimlar != null)
				return false;
		} else if (!kazanimlar.equals(other.kazanimlar))
			return false;
		return true;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return icerik;
	}

}
