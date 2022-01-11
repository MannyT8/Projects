package com.revature.project1.dao;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.revature.project1.controller.UserController;
import com.revature.project1.model.Request;
import com.revature.project1.util.ConnectionFactory;

public class RequestDao {
	
	private static RequestDao requestDao = null;
	
	RequestDao() {
		super();
	}

	public static RequestDao getRequestDao() {
		if (requestDao == null) {
			requestDao = new RequestDao();
		}
		return requestDao;
	}

	public Request findById(int id) {
		try (Connection connection = ConnectionFactory.getConnection()){
			String query = "SELECT * FROM revature.request WHERE id = ?";
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			return buildRequest(rs);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// error handling
			return null;
		}
	}
	
	public Request add(Request request) {
		try (Connection connection = ConnectionFactory.getConnection()) {
			String query = "INSERT INTO revature.request VALUES (default,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, request.getRequester());
		    pstmt.setFloat(2, request.getAmount());
		    pstmt.setString(3, request.getReason());
		    pstmt.setDate(4, request.getRequestDate());
		    pstmt.setString(5, request.getStatus());
		    pstmt.setInt(6, request.getDecidingManager());
		    pstmt.setDate(7, request.getDecisionDate());
		    pstmt.execute();
		    ResultSet rs = pstmt.getGeneratedKeys();
		    rs.next();
		    int key = rs.getInt("id");
		    request.setId(key);
		    return request;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// error handling
			return null;
		}
	}

	public List<Request> findAll() {
		try (Connection connection = ConnectionFactory.getConnection()){
			String query = "SELECT * FROM revature.request";
			PreparedStatement pstmt = connection.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			List<Request> requests = new ArrayList<Request>();
			Request request;
			while (rs.next()) {
				request = buildRequest(rs);
				requests.add(request);
			}
			return requests;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// error handling
			return null;
		}
	}

	public Request update(Request request) {
		try (Connection connection = ConnectionFactory.getConnection()) {
			String query = "UPDATE Revature.REQUEST SET status=?,deciding_manager=?,decision_date=? WHERE id = ?";
		    PreparedStatement pstmt = connection.prepareStatement(query);
		    pstmt.setString(1, request.getStatus());
		    pstmt.setInt(2, request.getDecidingManager());
		    pstmt.setDate(3, request.getDecisionDate());
		    pstmt.setInt(4, request.getId());
		    if (pstmt.executeUpdate() > 0) {
		    	return request;
		    }
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// error handling
		}
		return null;
	}

	private Request buildRequest(ResultSet rs) {
		try {
		    Request request = new Request();
		    request.setId(rs.getInt("id"));
		    request.setRequester(rs.getInt("requester"));
		    request.setAmount(rs.getFloat("amount"));
		    request.setReason(rs.getString("reason"));
		    request.setRequestDate(rs.getDate("request_date"));
		    request.setStatus(rs.getString("status"));
		    request.setDecidingManager(rs.getInt("deciding_manager"));
		    request.setDecisionDate(rs.getDate("decision_date"));
		    return request;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// error handling
			return null;
		}
	}
}
