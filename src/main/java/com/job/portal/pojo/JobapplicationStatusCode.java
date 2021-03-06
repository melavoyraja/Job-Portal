package com.job.portal.pojo;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Jobapplicationstatuscode generated by hbm2java
 */
@Entity
@Table(name = "jobapplicationstatuscode", catalog = "jobportal")
public class JobapplicationStatusCode implements java.io.Serializable {

	private Integer statusId;
	private String status;
	private Set<Appliedjobs> appliedjobses = new HashSet<Appliedjobs>(0);

	public JobapplicationStatusCode() {
	}

	public JobapplicationStatusCode(String status) {
		this.status = status;
	}

	public JobapplicationStatusCode(String status, Set<Appliedjobs> appliedjobses) {
		this.status = status;
		this.appliedjobses = appliedjobses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "statusID", unique = true, nullable = false)
	public Integer getStatusId() {
		return this.statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	@Column(name = "status", nullable = false, length = 100)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "jobApplicationStatusCode")
	public Set<Appliedjobs> getAppliedjobses() {
		return this.appliedjobses;
	}

	public void setAppliedjobses(Set<Appliedjobs> appliedjobses) {
		this.appliedjobses = appliedjobses;
	}

}

