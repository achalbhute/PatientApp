package com.psl.model;

import java.util.ArrayList;
import java.util.Collection;
/*import java.util.ArrayList;*/
import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Patient {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int AccNo;
	
	private String firstName;	
	private String lastName;
	private String facility;
	private Date admitDate;
	private Date dischargeDate;
	private String deleted;
	
	public int getAccNo() {
		return AccNo;
	}
	
	public Patient(String firstName, String lastName, String facility, Date admitDate, Date dischargeDate,
			String deleted) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.facility = facility;
		this.admitDate = admitDate;
		this.dischargeDate = dischargeDate;
		this.deleted = deleted;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	

	public String getFacility() {
		return facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}

	public Date getAdmitDate() {
		return admitDate;
	}
	public void setAdmitDate(Date admitDate) {
		this.admitDate = admitDate;
	}
	public Date getDischargeDate() {
		return dischargeDate;
	}
	public void setDischargeDate(Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	public void setAccNo(int accNo) {
		AccNo = accNo;
	}


	public Patient(String firstName, String lastName, String facility, Date admitDate, Date dischargeDate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.facility = facility;
		this.admitDate = admitDate;
		this.dischargeDate = dischargeDate;
	}

	public Patient() {
		super();
	}

	@Override
	public String toString() {
		return "Patient [AccNo=" + AccNo + ", firstName=" + firstName + ", lastName=" + lastName + ", facility="
				+ facility + ", admitDate=" + admitDate + ", dischargeDate=" + dischargeDate + ", deleted=" + deleted
				+ "]";
	}
	
	
}