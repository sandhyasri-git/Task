package com.niit.task_backend.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.task_backend.model.UserDetails;
@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void addUser(UserDetails user) {
		Session session=sessionFactory.getCurrentSession();
		session.save(user);
		
	}

	public UserDetails login(UserDetails user) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("From UserDetails where username=? and pass=?");
		query.setParameter(0, user.getUsername());
		query.setParameter(1, user.getPass());
		return (UserDetails)query.uniqueResult();
		
	}

	public void update(UserDetails user) {
		Session session=sessionFactory.getCurrentSession();
		session.update(user);
		
	}

	public UserDetails getUserById(long id) {
	Session session=sessionFactory.getCurrentSession();
	UserDetails user=(UserDetails)session.get(UserDetails.class, id);
	return user;
	}

}
