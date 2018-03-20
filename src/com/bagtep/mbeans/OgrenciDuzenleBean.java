package com.bagtep.mbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import com.bagtep.business.OgrenciService;
import com.bagtep.domain.Ogrenci;

@ManagedBean
@ViewScoped
public class OgrenciDuzenleBean {

	private List<Ogrenci> ogrenciler;
	private List<Ogrenci> ogrencilerFilter;

	@EJB
	private OgrenciService ogrenciService;
	private Ogrenci newOgrenci = new Ogrenci();
	private int ogrId;

	@PostConstruct
	public void init() {
		this.ogrenciler = ogrenciService.getAllOgrenci();
	}

	public void onRowEdit(RowEditEvent event) {
		Ogrenci ogrenci = (Ogrenci) event.getObject();
		ogrenciService.updateOgrenci(ogrenci);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ogrenci.getAd() + " güncellendi."));
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(ogrenci.getAd() + " " + ogrenci.getSoyad() + " güncellendi."));
	}

	public String silmedenDon() {
		return "users/admin/adminogrenciduzenle";
	}

	public void selecttoDeleteOgrenci(int ogrenciId) {
		this.ogrId = ogrenciId;
	}

	public void deleteOgrenci() {

		ogrenciService.deleteOgrenci(ogrId);
		this.ogrenciler = ogrenciService.getAllOgrenci();
	}

	public void addOgrenci() {
		ogrenciService.addNewOgrenci(this.newOgrenci);
		this.ogrenciler = ogrenciService.getAllOgrenci();
	}

	public List<Ogrenci> getOgrenciler() {
		return ogrenciler;
	}

	public void setOgrenciler(List<Ogrenci> ogrenciler) {
		this.ogrenciler = ogrenciler;
	}

	public Ogrenci getNewOgrenci() {
		return newOgrenci;
	}

	public void setNewOgrenci(Ogrenci newOgrenci) {
		this.newOgrenci = newOgrenci;
	}

	public List<Ogrenci> getOgrencilerFilter() {
		return ogrencilerFilter;
	}

	public void setOgrencilerFilter(List<Ogrenci> ogrencilerFilter) {
		this.ogrencilerFilter = ogrencilerFilter;
	}

	public int getOgrId() {
		return ogrId;
	}

	public void setOgrId(int ogrId) {
		this.ogrId = ogrId;
	}

}
