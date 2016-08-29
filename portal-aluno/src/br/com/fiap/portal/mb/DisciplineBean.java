package br.com.fiap.portal.mb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.fiap.portal.dao.CourseDAO;
import br.com.fiap.portal.dao.DisciplineDAO;
import br.com.fiap.portal.dao.UserDAO;
import br.com.fiap.portal.model.Course;
import br.com.fiap.portal.model.Discipline;
import br.com.fiap.portal.model.User;

@ManagedBean
@RequestScoped
public class DisciplineBean {

	private DisciplineDAO disciplineDAO = new DisciplineDAO();
	private CourseDAO courseDAO = new CourseDAO();
	private UserDAO userDAO = new UserDAO();
	private List<Discipline> disciplines = new ArrayList<Discipline>();	
	private Discipline discipline = new Discipline();
	private long courseId;
	private long teacherId;
	
	public String register() {
		if (discipline.getName() == null || "".equals(discipline.getName().trim()))
			return "register-discipline";
		
		User teacher = userDAO.find(teacherId); 
		Course course = courseDAO.find(courseId);
		
		discipline.setTeacher(teacher);
		discipline.setCourse(course);
		discipline.setCreatedAt(new Date());
		disciplineDAO.insert(discipline);
		return "disciplines";
	}
		
	public List<Discipline> getDisciplines() {
		return disciplineDAO.findAll();
	}
	
	public void setDisciplines(List<Discipline> disciplines) {
		this.disciplines = disciplines;
	}
	
	public Discipline getDiscipline() {
		return discipline;
	}
	
	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(long teacherId) {
		this.teacherId = teacherId;
	}
	
}
