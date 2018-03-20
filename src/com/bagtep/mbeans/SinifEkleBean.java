package com.bagtep.mbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import com.bagtep.business.SinifService;
import com.bagtep.domain.Sinif;

@ManagedBean
public class SinifEkleBean {

	private Sinif newSinif = new Sinif();

	private List<Sinif> siniflar;

	@EJB
	private SinifService sinifService;

	public SinifEkleBean() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {
		this.siniflar = sinifService.getAllSinif();
	}

	public String add() {
		sinifService.saveSinif(this.newSinif);
		this.siniflar = sinifService.getAllSinif();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Yeni sınıf başarıyla eklendi!!"));
		return "foofoo";// forwarding
	}

	public void deleteSinif(int sinifId) {

		sinifService.deleteSinif(sinifId);
		this.siniflar = sinifService.getAllSinif();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Sınıf başarıyla silindi!!"));
	}

	public void onRowEdit(RowEditEvent event) {
		Sinif sinif = (Sinif) event.getObject();
		// DersService.updateUserWithEdit(Ders);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(sinif.getSinifAd() + " düzenlendi"));
	}

	public Sinif getNewSinif() {
		return newSinif;
	}

	public void setNewSinif(Sinif newSinif) {
		this.newSinif = newSinif;
	}

	public List<Sinif> getSiniflar() {
		return siniflar;
	}

	public void setSiniflar(List<Sinif> siniflar) {
		this.siniflar = siniflar;
	}

}
