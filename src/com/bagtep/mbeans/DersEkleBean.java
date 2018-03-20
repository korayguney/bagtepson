package com.bagtep.mbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import com.bagtep.business.DersService;
import com.bagtep.domain.Ders;

@ManagedBean
public class DersEkleBean {

	private Ders newDers = new Ders();

	private List<Ders> dersler;

	@EJB
	private DersService dersService;

	public DersEkleBean() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {
		this.dersler = dersService.getAllDers();
	}

	public String add() {
		dersService.saveDers(this.newDers);
		this.dersler = dersService.getAllDers();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Yeni ders başarıyla eklendi!!"));
		return "foofoo";// forwarding
	}

	public void deleteDers(int dersId) {

		dersService.deleteDers(dersId);
		this.dersler = dersService.getAllDers();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Ders başarıyla silindi!!"));
	}

	public void onRowEdit(RowEditEvent event) {
		Ders Ders = (Ders) event.getObject();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(Ders.getDersAd() + " düzenlendi"));
	}

	public Ders getNewDers() {
		return newDers;
	}

	public void setNewDers(Ders newDers) {
		this.newDers = newDers;
	}

	public List<Ders> getDersler() {
		return dersler;
	}

	public void setDersler(List<Ders> dersler) {
		this.dersler = dersler;
	}

}
