package br.com.fiap.portal.mb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.fiap.portal.dao.CourseDAO;
import br.com.fiap.portal.dao.SchoolDAO;
import br.com.fiap.portal.model.Course;
import br.com.fiap.portal.model.School;

@ManagedBean
@RequestScoped
public class CourseBean {

	private CourseDAO courseDAO = new CourseDAO();
	private SchoolDAO schoolDAO = new SchoolDAO();
	private List<Course> courses = new ArrayList<Course>();
	private Course course = new Course();
	private long schoolId;
	
	public String register() {
		if (course.getName() == null || "".equals(course.getName().trim()))
			return "register-course";
		
		School school = schoolDAO.find(schoolId);
		
		course.setSchool(school);
		course.setCreatedAt(new Date());
		courseDAO.insert(course);
		
		return "courses";
	}
	
	public List<Course> getCourses() {
		return courseDAO.findAll();
	}
	
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(long schoolId) {
		this.schoolId = schoolId;
	}
		
}
