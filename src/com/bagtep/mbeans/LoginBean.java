package com.bagtep.mbeans;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.bagtep.business.DersService;
import com.bagtep.business.UserService;

@ManagedBean
@SessionScoped
public class LoginBean {

	private String username;
	private String password;
	private int dersSayisi;

	private boolean adminLoggedIn;
	private boolean ogretmenLoggedIn;

	private String text;

	@EJB
	private UserService userService;
	@EJB
	private DersService dersService;

	@ManagedProperty("#{mySessionScopedBean}")
	private MySessionScopedBean mySessionScopedBean;

	public String login() {
		if (userService.checkUser(username, password)) {
			mySessionScopedBean.setUsername(username);

			String role = userService.getRole(username);
			mySessionScopedBean.setRole(role);

			String firstname = userService.getFirstname(username);
			mySessionScopedBean.setFirstname(firstname);

			String lastname = userService.getLastname(username);
			mySessionScopedBean.setLastname(lastname);

			int id = userService.getId(username, lastname);
			mySessionScopedBean.setId(id);

			dersSayisi = dersService.getAllDers().size();
			mySessionScopedBean.setDersSayisi(dersSayisi);
			if (role.equals("Admin")) {
				adminLoggedIn = true;
				return "users/admin/adminanasayfa";
			}

			ogretmenLoggedIn = true;
			return "users/ogretmen/ogretmenanasayfa";

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Yanlış/Eksik Parola veya Kullanıcı Adı!"));
			return "index";
		}
	}

	public String logout() {
		adminLoggedIn = false;
		ogretmenLoggedIn = false;
		mySessionScopedBean.setUsername("");
		return "index";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public MySessionScopedBean getMySessionScopedBean() {
		return mySessionScopedBean;
	}

	public void setMySessionScopedBean(MySessionScopedBean mySessionScopedBean) {
		this.mySessionScopedBean = mySessionScopedBean;
	}

	public int getDersSayisi() {
		return dersSayisi;
	}

	public void setDersSayisi(int dersSayisi) {
		this.dersSayisi = dersSayisi;
	}

	public boolean isAdminLoggedIn() {
		return adminLoggedIn;
	}

	public void setAdminLoggedIn(boolean adminLoggedIn) {
		this.adminLoggedIn = adminLoggedIn;
	}

	public boolean isOgretmenLoggedIn() {
		return ogretmenLoggedIn;
	}

	public void setOgretmenLoggedIn(boolean ogretmenLoggedIn) {
		this.ogretmenLoggedIn = ogretmenLoggedIn;
	}

}