package com.springsecurity.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springsecurity.model.User;
@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public void addUser(User user) {
		sessionFactory.getCurrentSession().save(user);

	}

	@Override
	public void editUser(User user) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(user);
	}

	@Override
	public void deleteUser(int userid) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(findUser(userid));
	}

	@Override
	public User findUser(int userid) {
		return (User) sessionFactory.getCurrentSession().get(User.class, userid);

	}

	@Override
	public User findUserByName(String username) {
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(User.class)
					.add(Restrictions.eq("username", username));
					
		return (User)criteria.uniqueResult();
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createCriteria(User.class).list();
	}

}
