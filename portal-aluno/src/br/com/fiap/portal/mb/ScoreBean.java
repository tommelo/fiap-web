package br.com.fiap.portal.mb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.fiap.portal.dao.DisciplineDAO;
import br.com.fiap.portal.dao.ScoreDAO;
import br.com.fiap.portal.dao.UserDAO;
import br.com.fiap.portal.model.Discipline;
import br.com.fiap.portal.model.Score;
import br.com.fiap.portal.model.User;

@ManagedBean
@RequestScoped
public class ScoreBean {

	private ScoreDAO scoreDAO = new ScoreDAO();
	private UserDAO userDAO = new UserDAO();
	private DisciplineDAO disciplineDAO = new DisciplineDAO();
	
	private List<Score> scores = new ArrayList<Score>();
	private Score score = new Score();
	
	private long studentId;
	private long disciplineId;
	private int projectOneScore;
	private int projectTwoScore;
	private int practicalActivityScore;
	
	
	public String register() {
		User student = userDAO.find(studentId);
		Discipline discipline = disciplineDAO.find(disciplineId);
		
		score.setStudent(student);
		score.setDiscipline(discipline);
		score.setProjectOneScore(projectOneScore);
		score.setProjectTwoScore(projectTwoScore);
		score.setPracticalActivityScore(practicalActivityScore);
		score.setCreatedAt(new Date());
		
		int nota = (projectOneScore + projectTwoScore + practicalActivityScore) / 3;
		if (nota >= 7)
			score.setStatus("APROVADO");
		else
			score.setStatus("REPROVADO");
		
		scoreDAO.insert(score);
		return "scores";		
	}
	
	public List<Score> getScores() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		User loggedUser = (User) session.getAttribute("user");
		if (loggedUser.getRole().equals("ALUNO"))
			return scoreDAO.findByUserId(loggedUser.getId());
		
		return scoreDAO.findAll();
	}
	
	public void setScores(List<Score> scores) {
		this.scores = scores;
	}
	
	public Score getScore() {
		return score;
	}
	
	public void setScore(Score score) {
		this.score = score;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public long getDisciplineId() {
		return disciplineId;
	}

	public void setDisciplineId(long disciplineId) {
		this.disciplineId = disciplineId;
	}

	public int getProjectOneScore() {
		return projectOneScore;
	}

	public void setProjectOneScore(int projectOneScore) {
		this.projectOneScore = projectOneScore;
	}

	public int getProjectTwoScore() {
		return projectTwoScore;
	}

	public void setProjectTwoScore(int projectTwoScore) {
		this.projectTwoScore = projectTwoScore;
	}

	public int getPracticalActivityScore() {
		return practicalActivityScore;
	}

	public void setPracticalActivityScore(int practicalActivityScore) {
		this.practicalActivityScore = practicalActivityScore;
	}	
		
}
