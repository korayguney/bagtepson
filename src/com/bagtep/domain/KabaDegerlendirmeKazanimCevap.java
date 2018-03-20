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
public class KabaDegerlendirmeKazanimCevap implements Serializable {

	private static final long serialVersionUID = -7748655281126697255L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private boolean kabaDegerlendirmeCevap;
	private String yorum;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private KabaDegerlendirme kabaDegerlendirme;

	@ManyToOne
	private OzelAmac ozelAmac;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isKabaDegerlendirmeCevap() {
		return kabaDegerlendirmeCevap;
	}

	public void setKabaDegerlendirmeCevap(boolean kabaDegerlendirmeCevap) {
		this.kabaDegerlendirmeCevap = kabaDegerlendirmeCevap;
	}

	public String getYorum() {
		return yorum;
	}

	public void setYorum(String yorum) {
		this.yorum = yorum;
	}

	public KabaDegerlendirme getKabaDegerlendirme() {
		return kabaDegerlendirme;
	}

	public void setKabaDegerlendirme(KabaDegerlendirme kabaDegerlendirme) {
		this.kabaDegerlendirme = kabaDegerlendirme;
	}

	public OzelAmac getOzelAmac() {
		return ozelAmac;
	}

	public void setOzelAmac(OzelAmac ozelAmac) {
		this.ozelAmac = ozelAmac;
	}
}
