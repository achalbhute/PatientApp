package com.psl.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.psl.controller.HibernateUtil;
import com.psl.model.Facility;
import com.psl.model.Message;
import com.psl.model.Patient;

public class PatientHelper {
	public static List<Patient> displayPatient(Collection<Facility> facilities){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Patient> patient = new ArrayList<>();
		Iterator<Facility> itr=facilities.iterator();
		StringBuilder sb=new StringBuilder("From Patient where deleted=1 and facility in(");  
		while(itr.hasNext())
		{
			sb.append("'");
			sb.append(itr.next().getName());
			sb.append("'");
			sb.append(",");
		}
		sb.replace(sb.length()-1,sb.length(), ") ");
		@SuppressWarnings("unchecked")
		List<Query> list = session.createQuery(sb.toString()).list();
		Iterator<Query> iterator  =list.iterator();
		while(iterator.hasNext()){
			patient.add((Patient)iterator.next());
		}
		for(Patient p: patient)
			System.out.println(p);
		return patient;
	}
	
	public static Message updatePatient(String[] arr){
		
	Session session = HibernateUtil.getSessionFactory().openSession();
	session.beginTransaction();
		for(String str: arr)
		{
			String a[]=str.split(" ");
			System.out.println(a[0]+ "//"+a[1]);
			Patient patient= (Patient) session.load(Patient.class, Integer.parseInt(a[0]));
			patient.setDeleted("0");
			session.getTransaction().commit();
		}
		session.close();
		return new Message("successfull", true);
	}
	
	public static List<Patient> uploadData(BufferedReader reader,List<String> facilities)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String line;
		List<Patient> patient = new ArrayList<>();
		try {
			while ((line = reader.readLine()) != null) {
				String data[] = line.split(",");
				if(data.length!=5)
				{	
					System.out.println(data[0]+ " is not inserted coz insuff data");
				}
				else if(!(facilities.contains(data[2])))
				{
					System.out.println(data[0]+ " is not inserted coz facility is diff");
				}
				else
				{	
					Patient p=new Patient(data[0], data[1], data[2], Date.valueOf(data[3]),Date.valueOf( data[4]), "1");
					session.saveOrUpdate(p);
					
					patient.add(p);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		session.getTransaction().commit();
		session.close();
		for(Patient p: patient)
			System.out.println(p);
		return patient;
	}
	
	public static List<Patient> SearchPatient(String firstname, String lastname,
			String facility, String dateofadmit, String dateofdischarge, List<String> facilities){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Patient> patient = new ArrayList<>();
		String searchquery = "  SELECT * FROM Patient "+
				 "WHERE firstname = COALESCE(:firstname,firstname) " 
				+" and lastname = COALESCE(:lastname,lastname)"+
				" and facility = COALESCE(:facility,facility)" +
				" and admitdate = COALESCE(:admitdate,admitdate)"+
				" and dischargedate =  COALESCE(:dischargedate,dischargedate)";
		@SuppressWarnings("unchecked")
		Query query =(Query) session.createQuery(searchquery);
		setNullOrString(query, "firstname", firstname);
		setNullOrString(query, "lastname", lastname);
		if( (!facility.isEmpty()) && !facilities.contains(facility))
		{
			setNullOrString(query, "facility", "-1");
		}
		else
		{
		setNullOrString(query,"facility", facility);
		}
		setNullOrDate(query, 4, dateofadmit);
		setNullOrDate(query, 5, dateofdischarge);
		List<Query> list = ((org.hibernate.Query) query).list();
		Iterator<Query> iterator  =list.iterator();
		while(iterator.hasNext()){
			patient.add((Patient)iterator.next());
		}
		for(Patient p: patient)
			System.out.println(p);
		return patient;
	}
	public static void setNullOrString(Query query,String index, String column)
	{
		if(column.equals("")){	
			query.setParameter(index, null);
		}
		else{
			query.setParameter(index, column);
		}
	}

	public static void setNullOrDate(Query query,String index, String column){
		if(column.equals("")){		
			query.setParameter(index,null);
		}
		else{
			Date d = Date.valueOf(column);
			query.setParameter(index, d);
		}
	}

}
