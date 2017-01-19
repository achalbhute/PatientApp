package com.psl.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;



@Entity
public class UserBean {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	@ManyToMany
	private Collection<Facility> facilities = new ArrayList<Facility>();
	
	private boolean valid;
	private String role;
	private String status;
	private boolean firstLogin;
	
	public UserBean() {
		super();
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public int getId() {
		return id;
	}	
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isFirstLogin() {
		return firstLogin;
	}
	public void setFirstLogin(boolean firstLogin) {
		this.firstLogin = firstLogin;
	}
	public Collection<Facility> getFacilities() {
		return facilities;
	}
	public void setFacilities(Collection<Facility> facilities) {
		this.facilities = facilities;
	}

	public UserBean(String firstName, String lastName, String email, String password, boolean valid, String role,
			String status, boolean firstLogin) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.valid = valid;
		this.role = role;
		this.status = status;
		this.firstLogin = firstLogin;
	}

	
	

}
