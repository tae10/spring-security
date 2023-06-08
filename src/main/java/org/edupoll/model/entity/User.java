package org.edupoll.model.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	String id;
	String password;
	String enabled;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	List<UserRole> roles;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	// default constructor
	public User() {
		super();
	}

	public User(String id, String password) {
		super();
		this.id = id;
		this.password = password;
		this.enabled = "Y";
	}

}
