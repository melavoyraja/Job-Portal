package com.job.portal.pojo;
// Generated Apr 4, 2016 11:54:04 PM by Hibernate Tools 4.3.1.Final

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
 * Roles generated by hbm2java
 */
@Entity
@Table(name = "roles", catalog = "jobportal")
public class Roles implements java.io.Serializable {

	private Integer roleId;
	private String role;
	private Set<User> users = new HashSet<User>(0);

	public Roles() {
	}

	public Roles(String role) {
		this.role = role;
	}

	public Roles(String role, Set<User> users) {
		this.role = role;
		this.users = users;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "roleID", unique = true, nullable = false)
	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Column(name = "role", nullable = false, length = 50)
	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "roles")
	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
