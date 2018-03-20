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
public class Kazanim implements Serializable,Comparable<Kazanim> {

	private static final long serialVersionUID = -5515962866275174728L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String icerik;

	@ManyToOne
	private OzelAmac ozelAmac;

	public Kazanim(String icerik, OzelAmac ozelAmac) {
		super();
		this.icerik = icerik;
		this.ozelAmac = ozelAmac;
	}

	public Kazanim() {
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

	public OzelAmac getOzelAmac() {
		return ozelAmac;
	}

	public void setOzelAmac(OzelAmac ozelAmac) {
		this.ozelAmac = ozelAmac;
	}

	@Override
	public int compareTo(Kazanim o) {
		return this.getIcerik().compareTo(o.getIcerik());
	}

}
