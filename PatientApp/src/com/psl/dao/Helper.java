package com.psl.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;


import com.psl.controller.HibernateUtil;
import com.psl.model.UserBean;

public class Helper {
	private static UserBean bean = null;
	public static UserBean login(UserBean userBean){
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<Query> list = session.createQuery("From UserBean").list();
		
		Iterator<Query> iterator  =list.iterator();
		while(iterator.hasNext()){
			bean = (UserBean)iterator.next();
			System.out.println(bean.getFirstName());
			if(userBean.getEmail().equals(bean.getEmail()) && userBean.getPassword().equals(bean.getPassword())){
				break;
			}
			else{
				bean.setValid(false);
			}
		}
		return bean;
	}
	
	public static List<UserBean> displayUser(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<UserBean> users = new ArrayList<>();
		@SuppressWarnings("unchecked")
		List<Query> list = session.createQuery("From UserBean").list();
		Iterator<Query> iterator  =list.iterator();
		while(iterator.hasNext()){
			users.add((UserBean)iterator.next());
		}
		return users;
	}
}
