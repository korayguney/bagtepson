package com.bagtep.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BireyselEgitimPlaniRaporSecim implements Serializable {

	private static final long serialVersionUID = -7748655281126697255L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private boolean bireyselEgitimPlaniCevap;

	@ManyToOne
	private BireyselEgitimPlani bireyselEgitimPlani;

	@ManyToOne
	private OzelAmac ozelAmac;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public OzelAmac getOzelAmac() {
		return ozelAmac;
	}

	public void setOzelAmac(OzelAmac ozelAmac) {
		this.ozelAmac = ozelAmac;
	}

	public boolean isBireyselEgitimPlaniCevap() {
		return bireyselEgitimPlaniCevap;
	}

	public void setBireyselEgitimPlaniCevap(boolean bireyselEgitimPlaniCevap) {
		this.bireyselEgitimPlaniCevap = bireyselEgitimPlaniCevap;
	}

	public BireyselEgitimPlani getBireyselEgitimPlani() {
		return bireyselEgitimPlani;
	}

	public void setBireyselEgitimPlani(BireyselEgitimPlani bireyselEgitimPlani) {
		this.bireyselEgitimPlani = bireyselEgitimPlani;
	}

}
