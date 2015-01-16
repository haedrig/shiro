package com.haedrig.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.haedrig.dao.IResourceDAO;
import com.haedrig.entity.Resource;

@Repository("resourceDAO")
public class ResourcesDAOImpl implements IResourceDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session openSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	public List<Resource> getAll() {
		List<Resource> list = new ArrayList<Resource>();
		Query query = openSession().createQuery("from Resource u where 1 = 1");
		list = query.list();
		return list;
	}

}
