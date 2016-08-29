package br.com.fiap.portal.mb;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.fiap.portal.dao.UserDAO;
import br.com.fiap.portal.model.Login;
import br.com.fiap.portal.model.User;
import br.com.fiap.portal.utils.PasswordUtils;
import br.com.fiap.portal.utils.SessionUtils;

@ManagedBean
@SessionScoped
public class LoginBean {
	
	private Login login = new Login();
	private UserDAO userDAO = new UserDAO();
	private User user;
		
	public String login() {			
		user = userDAO.findByRm(login.getUser());
		
		if (user != null) {		
			String encrypted = PasswordUtils.encrypt(login.getPassword());
			if (user.getPassword().equals(encrypted)) {
				HttpSession session = SessionUtils.getSession();
				session.setAttribute("user", user);
				return "logged";
			}
						
		}
						
		FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Login inválido",
							"Por favor informe seu usuário e senha"));
		return "login";		 			
	}
	
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login";
	}

	public Login getLogin() {
		return login;
	}


	public void setLogin(Login login) {
		this.login = login;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
