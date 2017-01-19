package com.psl.dao;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.psl.model.UserBean;

public class Login {
	public static UserBean login(UserBean userBean){
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<Query> list = session.createQuery("From UserBean").list();
		UserBean bean = null;
		Iterator<Query> iterator  =list.iterator();
		while(iterator.hasNext()){
			bean = (UserBean)iterator.next();
			System.out.println(bean.getFirstName());
			if(userBean.getEmail().equals(bean.getEmail())&& userBean.getPassword().equals(bean.getPassword())){
				break;
			}
			else{
				bean.setValid(false);
			}
		}
		return bean;
	}
}
