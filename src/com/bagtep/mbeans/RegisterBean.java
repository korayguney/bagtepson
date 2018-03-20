package com.bagtep.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.bagtep.business.UserService;
import com.bagtep.domain.User;
import com.bagtep.exceptions.UsernameExistsException;

@ManagedBean
public class RegisterBean {

	private User newUser = new User();
	private List<SelectItem> roles;

	@PostConstruct
	public void init() {
		roles = new ArrayList<SelectItem>();
		roles.add(new SelectItem("Admin"));
		roles.add(new SelectItem("Öğretmen"));
	}

	@EJB
	private UserService userService;

	public String register() {

		try {
			userService.saveUser(this.newUser);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Kayıt başarılı !"));
			return "kullaniciekle";
		} catch (UsernameExistsException e) {
			FacesContext.getCurrentInstance().addMessage("registerForm:username",
					new FacesMessage("Bu kullanıcı adı kayıtlıdır!!"));
			return "kullaniciekle";
		}
	}

	public User getNewUser() {
		return newUser;
	}

	public void setNewUser(User newUser) {
		this.newUser = newUser;
	}

	public List<SelectItem> getRoles() {
		return roles;
	}

	public void setRoles(List<SelectItem> roles) {
		this.roles = roles;
	}

}
