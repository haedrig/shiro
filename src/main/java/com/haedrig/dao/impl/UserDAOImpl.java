package com.haedrig.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.haedrig.dao.IUserDAO;
import com.haedrig.entity.User;

@Repository("userDAO")
public class UserDAOImpl implements IUserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session openSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public User getUser(String account) {
		List<User> userList = new ArrayList<User>();
		Query query = openSession().createQuery("from User u where u.account = :login");
		query.setParameter("login", account);
		userList = query.list();
		if (userList.size() > 0)
			return userList.get(0);
		else
			return null;	
	}

}
