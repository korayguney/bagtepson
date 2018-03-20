package com.bagtep.mbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import com.bagtep.business.SinifService;
import com.bagtep.domain.Sinif;

@ManagedBean
@ViewScoped
public class SinifDuzenleBean {

	private List<Sinif> siniflar;
	private int sinifId;

	@EJB
	private SinifService sinifService;
	private Sinif newSinif = new Sinif();

	@PostConstruct
	public void init() {
		this.siniflar = sinifService.getAllSinif();
	}

	public void onRowEdit(RowEditEvent event) {
		Sinif sinif = (Sinif) event.getObject();
		sinifService.updateSinif(sinif);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sonuç : "));
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(sinif.getSinifAd() + " güncellendi."));
	}

	public void selecttoDeleteSinif(int sinifId) {
		this.sinifId = sinifId;
	}

	public void deleteSinif() {

		try {
			sinifService.deleteSinif(sinifId);
		} catch (Exception e) {
			System.out.println("Sınıfa atanmış öğrenciler mevcut, Sınıfı silmek için öncelikle bu öğrencilerin sınıfını değiştiniz veya öğrencileri siliniz !!!");
		}
		this.siniflar = sinifService.getAllSinif();
	}

	public String deleteSinif(int sinifId) {
		boolean result = false;
		try {
			result = sinifService.deleteSinif(sinifId);
		} catch (Exception e) {
			System.out.println("Sınıfa atanmış öğrenciler mevcut, Sınıfı silmek için öncelikle bu öğrencilerin sınıfını değiştiniz veya öğrencileri siliniz !!!");
		}
		this.siniflar = sinifService.getAllSinif();
		
		if(result){
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "", "SONUÇ :"));
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Sınıf başarıyla silindi!!"));
			return "foofoo";
		}else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "", "DİKKAT !!!"));
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Sınıfa atanmış öğrenciler mevcut !!!"));
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Sınıfı silmek için öncelikle bu öğrencilerin sınıfını değiştiniz veya öğrencileri siliniz !!!"));
			return "foofoo";
		}
	}

	public List<Sinif> getSiniflar() {
		return siniflar;
	}

	public void setSiniflar(List<Sinif> siniflar) {
		this.siniflar = siniflar;
	}

	public Sinif getNewSinif() {
		return newSinif;
	}

	public void setNewSinif(Sinif newSinif) {
		this.newSinif = newSinif;
	}

}
