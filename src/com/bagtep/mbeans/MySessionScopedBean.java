package com.bagtep.mbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.bagtep.domain.Ogretmen;

@ManagedBean
@SessionScoped
public class MySessionScopedBean {

	// User entity session değerleri...
	private String username = "";
	private String role = "";
	private String firstname = "";
	private String lastname = "";
	private int id;

	// Ogretmen entity session değerleri...
	private Ogretmen ogretmen;

	// Sınıf entity session değerleri...
	private String sinif = "";

	// Öğrenci entity session değerleri...
	private int ogrenciId;
	private String ad = "";
	private String soyad = "";
	private int ogrencino;

	// Ders entity session değerleri...
	private String dersAd;
	private int dersSayisi;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSinif() {
		return sinif;
	}

	public void setSinif(String sinif) {
		this.sinif = sinif;
	}

	public int getOgrenciId() {
		return ogrenciId;
	}

	public void setOgrenciId(int ogrenciId) {
		this.ogrenciId = ogrenciId;
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

	public String getDersAd() {
		return dersAd;
	}

	public void setDersAd(String dersAd) {
		this.dersAd = dersAd;
	}

	public int getDersSayisi() {
		return dersSayisi;
	}

	public void setDersSayisi(int dersSayisi) {
		this.dersSayisi = dersSayisi;
	}

	public Ogretmen getOgretmen() {
		return ogretmen;
	}

	public void setOgretmen(Ogretmen ogretmen) {
		this.ogretmen = ogretmen;
	}

}
