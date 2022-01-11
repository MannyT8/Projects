package com.revature.project1.service;

import java.util.List;

import com.revature.project1.model.User;

public interface UserService  {

	User findById(int id);

	User findByUserName(String userName);

    User findByFullName(String fullName);

	List<User> findByPosition(String position);
	
	List<User> findAll();
	
	List<User> findAllEmployees();
	
	User update(User user);
//
//	boolean delete(User user);
//	
//	User add(User user);

}
