package com.springsecurity.dao;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.springsecurity.model.MyUser;

public class LoginDAOImpl implements LoginDAO{
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public MyUser login(String username, String password) {
		// TODO Auto-generated method stub
		MyUser user = (MyUser) sessionFactory.openSession().createCriteria(MyUser.class)
				.add(Restrictions.and(Restrictions.eq("emailAddress", username), Restrictions.eq("password", password)))
				.uniqueResult();
		
		return user;
	}

	@Override
	public Serializable register(MyUser user) {
		// TODO Auto-generated method stub
		Serializable serializable = null;
		try {
			serializable = sessionFactory.openSession().save(user);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return serializable;
	}

}
