package com.revature.project1.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.revature.project1.main.App;
import com.revature.project1.model.Request;
import com.revature.project1.model.User;
import com.revature.project1.service.UserService;
import com.revature.project1.service.UserServiceImplementation;

import io.javalin.http.Handler;
import org.apache.log4j.Logger;

public class UserController {

	final static Logger logger = Logger.getLogger(UserController.class.getName());

	private static UserService userService = UserServiceImplementation.getUserService();
	private static Gson gson = new Gson();
	private static User user = null;

	public static int getUserId() {
		return user.getId();
	}

	public static Handler signIn = (ctx) -> {

		String userName = ctx.formParam("userName");
		String password = ctx.formParam("password");
		user = userService.findByUserName(userName);
if (user.getPassword().equals(password)) {
			if (user.getPosition().compareToIgnoreCase("employee") == 0) {
				ctx.redirect("employee.html");
				logger.info("Employee Login: " + userName);
			} else {
				ctx.redirect("/manager.html");
				logger.info("Manager Login: " + userName);

			}
		}
		;

	};

	public static Handler findById = (ctx) -> {
		String userId = ctx.pathParam("userId");
		try {
			User user = userService.findById(Integer.parseInt(userId));
			String json = gson.toJson(user);
			ctx.result(json);
			ctx.status(200);
		} catch (Exception e) {
			ctx.status(404);
			System.out.println(e.getMessage());
			// do error handling
		}
	};

	public static Handler getAll = (ctx) -> {
		String userName = ctx.queryParam("userName");
		String fullName = ctx.queryParam("fullName");
		String position = ctx.queryParam("position");

		List<User> users = new ArrayList<User>();

		if (userName != null) {
			users.add(userService.findByUserName(userName));
		} else if (fullName != null) {
			users.add(userService.findByFullName(fullName));
		} else if (position == "employee") {
			users = userService.findByPosition(position);
		} else {
			users = userService.findAll();
		}
		String json = gson.toJson(users);
		ctx.result(json);
		ctx.status(200);
	};

	public static Handler getAllEmployees = (ctx) -> {
		System.out.println("hello");
		String userName = ctx.queryParam("userName");
		String fullName = ctx.queryParam("fullName");
		String position = ctx.queryParam("position");

		List<User> users = new ArrayList<User>();

		if (userName != null) {
			users.add(userService.findByUserName(userName));
		} else if (fullName != null) {
			users.add(userService.findByFullName(fullName));
		} else if (position != null) {
			users = userService.findByPosition(position);
		} else {
			users = userService.findAllEmployees();
		}
		String json = gson.toJson(users);
		ctx.result(json);
		ctx.status(200);
		// System.out.println("users = " + users);

	};

	public static Handler viewInfo = (ctx) -> {
		int userId = UserController.getUserId();

		// System.out.println("userId =" + userId);
		user = userService.findById(userId);
		String json = gson.toJson(user);
		ctx.result(json);
		ctx.status(200);

		System.out.println("user = " + user);
	};

	public static Handler updateUserName = (ctx) -> {
		String userName = ctx.formParam("userName");
		int userId = UserController.getUserId();
		user = userService.findById(userId);
		String oldUserName = user.getUserName();
		user.setUserName(userName);
		logger.info("Username: " + oldUserName + " Updated to: " + user.getUserName());
		userService.update(user);

		String json = gson.toJson(user);

		ctx.result(json);

		ctx.redirect("../ref/viewInfo.html");

	};

	public static Handler updatePassword = (ctx) -> {
		String password = ctx.formParam("password");
		int userId = UserController.getUserId();
		user = userService.findById(userId);
		user.setPassword(password);
		userService.update(user);

		String json = gson.toJson(user);
		ctx.result(json);
		ctx.redirect("../ref/viewInfo.html");
	};

//    public static Handler update = (ctx) -> {
//    	String body = ctx.body();
//    	User user = gson.fromJson(body, User.class);
//    	User returnedUser = userService.update(user);
//    	String json = gson.toJson(returnedUser);
//    	ctx.result(json);
//    	ctx.status(202);
//    };
//    
//    public static Handler delete = (ctx) -> {
//    	String body = ctx.body();
//    	User user = gson.fromJson(body, User.class);
//    	try {
//    		boolean wasDeleted = userService.delete(user);
//    		if (wasDeleted) {
//    			ctx.status(200);
//    		} else {
//    			ctx.status(404);
//    		}
//    	} catch (Exception e) {
//    		ctx.status(404);
//    		System.out.println(e.getMessage());
//    		// error handling
//    	}
//    };
//    
//    public static Handler add = (ctx) -> {
//    	String body = ctx.body();
//    	try {
//    		User user = gson.fromJson(body, User.class);
//    		if (user != null) {
//    			User returnedUser = userService.add(user);
//    			ctx.result(gson.toJson(returnedUser));
//    			ctx.status(200);
//    		} else {
//    			ctx.status(404);
//    			// do error handling?
//    		}
//    	} catch (Exception e) {
//    		ctx.status(404);
//    		System.out.println(e.getMessage());
//    		// do error handling
//    	}
//    };

}
