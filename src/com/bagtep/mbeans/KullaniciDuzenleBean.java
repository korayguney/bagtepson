package com.bagtep.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import com.bagtep.business.OgretmenService;
import com.bagtep.business.UserService;
import com.bagtep.domain.Ogretmen;
import com.bagtep.domain.User;

@ManagedBean
@ViewScoped
public class KullaniciDuzenleBean {

	private List<User> users;
	private int userId;

	@EJB
	private UserService userService;
	@EJB
	private OgretmenService ogretmenService;

	@PostConstruct
	public void init() {
		this.users = userService.getAllUsers();
	}

	public void onRowEdit(RowEditEvent event) {
		User user = (User) event.getObject();
		userService.updateUser(user);
		ogretmenService.ogretmenGuncelle(user);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sonuç : "));
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(user.getUsername() + " güncellendi."));
	}

	public void selecttoDeleteKullanici(int kullaniciId) {
		this.userId = kullaniciId;
	}


	public User takeUser(int userId){
		return userService.getUserFromId(userId);
	}
	
	public void deleteUser() {

		User user = takeUser(userId);
		if(user.getRole().equals("Öğretmen")){
			ogretmenService.ogretmeniSil(user.getFirstname(), user.getLastname());
		}
		userService.deleteUser(userId);
		this.users = userService.getAllUsers();

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sonuç : "));
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Kullanıcı başarıyla silindi."));

	}
	
	public List<User> sessiondakiUserGetir(String username, String lastname){
//		System.out.println("Sessiondaki user : " + username);
		List<User> users = userService.getUser(username, lastname);
		return users;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
