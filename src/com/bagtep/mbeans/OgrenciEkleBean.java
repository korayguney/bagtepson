package com.bagtep.mbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import com.bagtep.business.OgrenciService;
import com.bagtep.business.SinifService;
import com.bagtep.domain.Ogrenci;
import com.bagtep.domain.Sinif;

@ManagedBean
public class OgrenciEkleBean {

	private Ogrenci newOgrenci = new Ogrenci();

	private List<Ogrenci> ogrenciler;
	private List<Sinif> siniflar;
	private int sinifId;

	@EJB
	private OgrenciService ogrenciService;
	@EJB
	private SinifService sinifService;

	public OgrenciEkleBean() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {
		this.ogrenciler = ogrenciService.getAllOgrenci();
		this.siniflar = sinifService.getAllSinif();

	}

	public String add() {
		ogrenciService.saveOgrenci(this.newOgrenci, this.sinifId);
		this.ogrenciler = ogrenciService.getAllOgrenci();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Yeni öğrenci başarıyla eklendi!!"));
		return "foofoo";// forwarding
	}

	public void deleteOgrenci(int ogrenciId) {

		ogrenciService.deleteOgrenci(ogrenciId);
		this.ogrenciler = ogrenciService.getAllOgrenci();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Öğrenci başarıyla silindi!!"));
	}

	public void onRowEdit(RowEditEvent event) {
		Ogrenci ogrenci = (Ogrenci) event.getObject();
		// ogrenciService.updateUserWithEdit(ogrenci);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ogrenci.getAd() + " düzenlendi"));
	}

	public Ogrenci getNewOgrenci() {
		return newOgrenci;
	}

	public void setNewOgrenci(Ogrenci newOgrenci) {
		this.newOgrenci = newOgrenci;
	}

	public int getSinifId() {
		return sinifId;
	}

	public void setSinifId(int sinifId) {
		this.sinifId = sinifId;
	}

	public List<Ogrenci> getOgrenciler() {
		return ogrenciler;
	}

	public void setOgrenciler(List<Ogrenci> ogrenciler) {
		this.ogrenciler = ogrenciler;
	}

	public List<Sinif> getSiniflar() {
		return siniflar;
	}

	public void setSiniflar(List<Sinif> siniflar) {
		this.siniflar = siniflar;
	}

}
