package com.psl.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Facility {	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	private String address;
	private String city;
	private String state;
	private String zip;
	@ManyToMany(mappedBy="facilities")
	private Collection<UserBean> users = new ArrayList<UserBean>();
	
	
	public Facility() {
		super();
	}

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public Facility(String name, String address, String city, String state, String zip) {
		super();
		this.name = name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	public Collection<UserBean> getUsers() {
		return users;
	}

	public void setUsers(Collection<UserBean> users) {
		this.users = users;
	}
	
}
