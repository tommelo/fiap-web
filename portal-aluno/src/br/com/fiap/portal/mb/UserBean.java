package br.com.fiap.portal.mb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.fiap.portal.dao.CourseDAO;
import br.com.fiap.portal.dao.UserDAO;
import br.com.fiap.portal.model.Course;
import br.com.fiap.portal.model.User;
import br.com.fiap.portal.utils.PasswordUtils;

@ManagedBean
@RequestScoped
public class UserBean {
	
	private UserDAO userDAO = new UserDAO();
	private CourseDAO courseDAO = new CourseDAO();
	
	private List<User> teachers = new ArrayList<User>();
	private List<User> students = new ArrayList<User>();
	private List<User> admins = new ArrayList<User>();
	private List<User> users = new ArrayList<User>();
	private User user = new User();
	
	private final String TEACHER_ROLE = "PROFESSOR";
	private final String STUDENT_ROLE = "ALUNO";
	private final String ADMIN_ROLE = "ADMIN";
	
	private String role;	
	private long courseId;
	
	
	public String register() {
		if (user.getFirstName() == null || "".equals(user.getFirstName().trim())
			|| user.getLastName() == null || "".equals(user.getLastName().trim())
			|| user.getEmail() == null || "".equals(user.getEmail().trim())
			|| user.getPassword() == null || "".equals(user.getPassword().trim())
			|| user.getRm() == null || "".equals(user.getRm().trim()))
			return "register-user";
			
					
		Course course = courseDAO.find(courseId);				
		String encrypted = PasswordUtils.encrypt(user.getPassword());
		
		user.setPassword(encrypted);
		user.setCourse(course);
		user.setRole(role);
		user.setCreatedAt(new Date());
		
		userDAO.insert(user);
		return "users";
	}
	
	public List<User> getTeachers() {
		return userDAO.findByRole(TEACHER_ROLE);
	}
	
	public void setTeachers(List<User> teachers) {
		this.teachers = teachers;
	}
	
	public List<User> getStudents() {
		return userDAO.findByRole(STUDENT_ROLE);
	}
	public void setStudents(List<User> students) {
		this.students = students;
	}
	public List<User> getAdmins() {
		return userDAO.findByRole(ADMIN_ROLE);
	}
	public void setAdmins(List<User> admins) {
		this.admins = admins;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return userDAO.findAll();
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
		
}
