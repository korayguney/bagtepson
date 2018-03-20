package com.bagtep.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class KazanimEK implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String icerik;

	@ManyToOne
	private OzelAmacEK ozelAmac;

	public KazanimEK(String icerik, OzelAmacEK ozelAmac) {
		super();
		this.icerik = icerik;
		this.ozelAmac = ozelAmac;
	}

	public KazanimEK() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIcerik() {
		return icerik;
	}

	public void setIcerik(String icerik) {
		this.icerik = icerik;
	}

	public OzelAmacEK getOzelAmac() {
		return ozelAmac;
	}

	public void setOzelAmac(OzelAmacEK ozelAmac) {
		this.ozelAmac = ozelAmac;
	}

}
