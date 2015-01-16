package com.haedrig.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haedrig.dao.IUserDAO;
import com.haedrig.entity.User;
import com.haedrig.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserDAO userDAO;

	public User getUser(String login) {
		return userDAO.getUser(login);
	}

}
