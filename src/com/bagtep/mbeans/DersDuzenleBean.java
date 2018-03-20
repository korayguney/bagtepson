package com.bagtep.mbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import com.bagtep.business.DersService;
import com.bagtep.domain.Ders;

@ManagedBean
@ViewScoped
public class DersDuzenleBean {

	private List<Ders> dersler;
	private int dersId;

	@EJB
	private DersService dersService;

	@PostConstruct
	public void init() {
		this.dersler = dersService.getAllDers();
	}

	public void onRowEdit(RowEditEvent event) {
		Ders ders = (Ders) event.getObject();
		dersService.updateDers(ders);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sonuç : "));
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ders.getDersAd() + " güncellendi."));
	}

	public void selecttoDeleteDers(int dersId) {
		this.dersId = dersId;
	}

	public void deleteDers() {
		dersService.deleteDers(dersId);
		this.dersler = dersService.getAllDers();
	}

	public List<Ders> getDersler() {
		return dersler;
	}

	public void setDersler(List<Ders> dersler) {
		this.dersler = dersler;
	}

}
