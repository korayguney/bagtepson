package com.bagtep.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class YilSonuDegerlendirmeKazanimCevap implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private double yilSonuDegerlendirmeCevap;
	private String yorum;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private YilSonuDegerlendirme yilSonuDegerlendirme;

	@ManyToOne
	private OzelAmac ozelAmac;

	@ManyToOne
	private OzelAmacEK ozelAmacEK;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getYorum() {
		return yorum;
	}

	public void setYorum(String yorum) {
		this.yorum = yorum;
	}

	public OzelAmac getOzelAmac() {
		return ozelAmac;
	}

	public void setOzelAmac(OzelAmac ozelAmac) {
		this.ozelAmac = ozelAmac;
	}

	public double getYilSonuDegerlendirmeCevap() {
		return yilSonuDegerlendirmeCevap;
	}

	public void setYilSonuDegerlendirmeCevap(double yilSonuDegerlendirmeCevap) {
		this.yilSonuDegerlendirmeCevap = yilSonuDegerlendirmeCevap;
	}

	public YilSonuDegerlendirme getYilSonuDegerlendirme() {
		return yilSonuDegerlendirme;
	}

	public void setYilSonuDegerlendirme(YilSonuDegerlendirme yilSonuDegerlendirme) {
		this.yilSonuDegerlendirme = yilSonuDegerlendirme;
	}

	public OzelAmacEK getOzelAmacEK() {
		return ozelAmacEK;
	}

	public void setOzelAmacEK(OzelAmacEK ozelAmacEK) {
		this.ozelAmacEK = ozelAmacEK;
	}

}
