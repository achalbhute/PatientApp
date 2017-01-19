package com.psl.model;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

	public static void main(String[] args) {
		UserBean userBean = new UserBean("Achal", "Bhute", "achal_bhute@persistent.co.in",
				"achal@3", true, "Clerk", "Active", false);
		Facility facility1 = new Facility("Apollo", "Carol bagh", "Delhi", "U.P", "110022");
		Facility facility2 = new Facility("JK Hospital", "Carol bagh", "Delhi", "U.P", "110024");
		userBean.getFacilities().add(facility1);
		userBean.getFacilities().add(facility2);
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(facility1);
		session.save(facility2);
		session.save(userBean);
		session.getTransaction().commit();
		session.close();
		
		session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<Query> list = session.createQuery("From UserBean").list();
		Iterator<Query> iterator  =list.iterator();
		while(iterator.hasNext()){
			UserBean bean = (UserBean)iterator.next();
			System.out.println(bean.getFirstName()+" "+bean.getPassword());
		}
	}

}
