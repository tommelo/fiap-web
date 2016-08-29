package br.com.fiap.portal.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="scores")
public class Score {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private long id;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User student;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="discipline_id")
	private Discipline discipline;
	
	@Column(name="project_one_score")
	private long projectOneScore;
	
	@Column(name="project_two_score")
	private long projectTwoScore;
	
	@Column(name="practical_activity_score")
	private long practicalActivityScore;
	
	@Column(name="status")
	private String status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public Discipline getDiscipline() {
		return discipline;
	}

	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}

	public long getProjectOneScore() {
		return projectOneScore;
	}

	public void setProjectOneScore(long projectOneScore) {
		this.projectOneScore = projectOneScore;
	}

	public long getProjectTwoScore() {
		return projectTwoScore;
	}

	public void setProjectTwoScore(long projectTwoScore) {
		this.projectTwoScore = projectTwoScore;
	}

	public long getPracticalActivityScore() {
		return practicalActivityScore;
	}

	public void setPracticalActivityScore(long practicalActivityScore) {
		this.practicalActivityScore = practicalActivityScore;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}	
	
}
