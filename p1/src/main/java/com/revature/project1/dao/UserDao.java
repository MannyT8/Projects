package com.revature.project1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;

//import org.hibernate.Transaction;

//import com.revature.project1.model.Request;
import com.revature.project1.model.User;
import com.revature.project1.util.ConnectionFactory;

public class UserDao {

	private static UserDao userDao;
	
	private UserDao () {
		super();
	}
	
	public static UserDao getUserDao() {
	    if (userDao == null) {
	    	userDao = new UserDao();
	    }
	    return userDao;
	}
	
	public List<User> findAll() {
        try (Connection connection = ConnectionFactory.getConnection()) {
        	String query = "SELECT * FROM users";
        	PreparedStatement pstmt = connection.prepareStatement(query);
        	ResultSet rs = pstmt.executeQuery();
        	List<User> users = new ArrayList<User>();
        	User user;
        	while (rs.next()) {
        		user = new User();
        		user.setId(rs.getInt(1));
        		user.setUserName(rs.getString(2));
        		user.setPassword(rs.getString(3));
        		user.setFullName(rs.getString(4));
        		user.setPosition(rs.getString(5));
        		users.add(user);
        	}
        	return users;
        } catch (Exception e) {
        	System.out.println(e.getLocalizedMessage());
        	//error handling
        	return null;
        }
 	}
	
	public List<User> findAllEmployees() {
        try (Connection connection = ConnectionFactory.getConnection()) {
        	String query = "SELECT * FROM users where position = 'employee'";
        	PreparedStatement pstmt = connection.prepareStatement(query);
        	ResultSet rs = pstmt.executeQuery();
        	List<User> users = new ArrayList<User>();
        	User user;
        	while (rs.next()) {
        		user = new User();
        		user.setId(rs.getInt(1));
        		user.setUserName(rs.getString(2));
        		user.setPassword(rs.getString(3));
        		user.setFullName(rs.getString(4));
        		user.setPosition(rs.getString(5));
        		users.add(user);
        	}
        	return users;
        } catch (Exception e) {
        	System.out.println(e.getLocalizedMessage());
        	//error handling
        	return null;
        }
 	}

	public User findById(int id) {
		try (Connection connection = ConnectionFactory.getConnection()) {
			String query = "SELECT * FROM users WHERE id = ?";
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setUserName(rs.getString("userName"));
			user.setPassword(rs.getString("password"));
			user.setFullName(rs.getString("fullName"));
			user.setPosition(rs.getString("position"));
			return user;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// error handling
	    	return null;
		}
	}
	
	public User update(User user) {
		try (Connection connection = ConnectionFactory.getConnection()) {
			String query = "UPDATE USERS SET userName=?, password=? WHERE id = ?";
		    PreparedStatement pstmt = connection.prepareStatement(query);
		    pstmt.setString(1, user.getUserName());
		    pstmt.setString(2, user.getPassword());
		    pstmt.setInt(3, user.getId());

		    if (pstmt.executeUpdate() > 0) {
		    	return user;
		    }
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// error handling
		}
		return null;
	}
	
	
//	
//	public boolean delete(User user) {
//		// implement
//		return true;
//	}
//
//	public User add(User user) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
