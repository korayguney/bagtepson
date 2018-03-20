package com.bagtep.mbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import com.bagtep.business.OgretmenService;
import com.bagtep.business.UserService;
import com.bagtep.domain.User;
import com.bagtep.exceptions.UsernameExistsException;

@ManagedBean
public class UserListBean {

	private User newUser = new User();

	private List<User> users;
	private List<User> usersexceptadmin;

	@EJB
	private UserService userService;
	@EJB
	private OgretmenService ogretmenService;

	public UserListBean() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {
		this.users = userService.getAllUsers();
		this.usersexceptadmin = userService.getUsersExceptAdmin();
	}

	public String add() {
		try {
			userService.saveUser(this.newUser);
			if (newUser.getRole().equals("Öğretmen")) {
				ogretmenService.ogretmenKaydet(newUser.getFirstname(), newUser.getLastname());
			}
			this.users = userService.getAllUsers();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Yeni kullanıcı başarıyla eklendi!!"));
			return "kullaniciekle";// forwarding
		} catch (UsernameExistsException e) {
			FacesContext.getCurrentInstance().addMessage("registerForm:username",
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Dikkat!", "Bu kullanıcı adı kayıtlı!!"));
			return "kullaniciekle";
		}
	}

	public User takeUser(int userId){
		return userService.getUserFromId(userId);
	}
	
	
	public void deleteUser(int userId) {

		userService.deleteUser(userId);
		User user = takeUser(userId);
		if (user.getRole().equals("Öğretmen")){
			ogretmenService.ogretmeniSil(user.getFirstname(), user.getLastname());
		}
		this.users = userService.getAllUsers();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Kullanıcı başarıyla silindi!!"));
	}

	public void onRowEdit(RowEditEvent event) {
		User user = (User) event.getObject();
		// userService.updateUserWithEdit(user);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(user.getFirstname() + " düzenlendi"));
	}

	public User getNewUser() {
		return newUser;
	}

	public void setNewUser(User newUser) {
		this.newUser = newUser;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<User> getUsersexceptadmin() {
		return usersexceptadmin;
	}

	public void setUsersexceptadmin(List<User> usersexceptadmin) {
		this.usersexceptadmin = usersexceptadmin;
	}

}
