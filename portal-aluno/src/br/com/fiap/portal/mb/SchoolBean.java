package br.com.fiap.portal.mb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import br.com.fiap.portal.dao.SchoolDAO;
import br.com.fiap.portal.model.School;

@ManagedBean
@RequestScoped
public class SchoolBean {

	private SchoolDAO schoolDAO = new SchoolDAO();
	private List<School> schools = new ArrayList<School>();
	private School school = new School();
		
	public String register() {
		if (school.getName() == null || "".equals(school.getName().trim()))
			return "register-school";
		
		school.setCreatedAt(new Date());
		schoolDAO.insert(school);
		return "index";
	}
	
	
	public List<School> getSchools() {
		return schoolDAO.findAll();
	}

	public void setSchools(List<School> schools) {
		this.schools = schools;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}
		
}
