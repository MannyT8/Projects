package com.revature.project1.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

import com.revature.project1.dao.UserDao;
import com.revature.project1.model.User;

public class UserServiceImplementation implements UserService {
	
	private static UserService userService = null;
    private static UserDao userDao = UserDao.getUserDao();
	
    private UserServiceImplementation() {
    	super();
    }
    
    public static UserService getUserService() {
    	if (userService == null) {
    		userService = new UserServiceImplementation();
    	}
    	return userService;
    }

	@Override
	public User findById(int id) {
		return userDao.findById(id);
	}

	@Override
	public User findByUserName(String userName) {
		List<User> users = findAll();
		for (User u: users) {
			if (u.getUserName().compareToIgnoreCase(userName) == 0) {
				return u;
			}
		}
		return null;
	}

	@Override
	public User findByFullName(String fullName) {
		List<User> users = findAll();
		for (User u: users) {
			if (u.getFullName().compareToIgnoreCase(fullName) == 0) {
				return u;
			}
		}
		return null;
	}

	@Override
	public List<User> findByPosition(String position) {
		List<User> users = findAll();
		List<User> usersInPosition = new ArrayList<User>();
		for (User u: users) {
			if (u.getPosition().compareToIgnoreCase(position) == 0) {
				usersInPosition.add(u);
			}
		}
		return usersInPosition;
	}

	@Override
	public List<User> findAll() {
		List<User> users = userDao.findAll();
		//Collections.sort(users, (u1, u2) -> String.CASE_INSENSITIVE_ORDER.compare(u1.getFullName(), u2.getFullName()));
		return users;
	}
	
	@Override
	public List<User> findAllEmployees() {
		List<User> users = userDao.findAllEmployees();
		//Collections.sort(users, (u1, u2) -> String.CASE_INSENSITIVE_ORDER.compare(u1.getFullName(), u2.getFullName()));
		return users;
	}

	@Override
	public User update(User user) {
		return userDao.update(user);
	}
//
//	@Override
//	public boolean delete(User user) {
//		return userDao.delete(user);
//	}
//	
//	@Override
//	public User add(User user) {
//		return userDao.add(user);
//	}

}
