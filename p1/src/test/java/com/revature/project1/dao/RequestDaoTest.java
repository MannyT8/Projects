package com.revature.project1.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.project1.model.Request;
import com.revature.project1.util.ConnectionFactory;

class RequestDaoTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
RequestDao requestdao = new RequestDao();
//requestdao.add(Request);
//{	try (Connection connection = ConnectionFactory.getConnection()) {
//		String query = "INSERT INTO revature.request VALUES (default,?,?,?,?,?,?,?)";
//		PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//
//		pstmt.setInt(1, request.getRequester());
//	    pstmt.setFloat(2, request.getAmount());
//	    pstmt.setString(3, request.getReason());
//	    pstmt.setDate(4, request.getRequestDate());
//	    pstmt.setString(5, request.getStatus());
//	    pstmt.setInt(6, request.getDecidingManager());
//	    pstmt.setDate(7, request.getDecisionDate());
//	    pstmt.execute();
//	    ResultSet rs = pstmt.getGeneratedKeys();
//	    rs.next();
//	    int key = rs.getInt("id");
//	    request.setId(key);
//	    return request;
//	} catch (Exception e) {
//		System.out.println(e.getMessage());
//		// error handling
//		return null;
//	}
//}

}

}
