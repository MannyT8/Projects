package com.revature.project1.model;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RequestTest {

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
	void testReason() {
		Request request = new Request();
request.setReason("food");
assertEquals(request.getReason(), "food");
	}
	
	@Test
	void testAmount() {
		Request request = new Request();
request.setAmount(100);
assertEquals(request.getAmount(), 100);
	}
	
	@Test
	void testDecidingManager() {
		Request request = new Request();
request.setDecidingManager(2);
assertEquals(request.getDecidingManager(), 2);
	}
	
	@Test
	void testDate() {
		Request request = new Request();
		Date date = new Date(0);
request.setDecisionDate(date);
request.setRequestDate(date);
assertEquals(request.getRequestDate(), date);
assertEquals(request.getDecisionDate(), date);
	}
	
	@Test
	void testId() {
		Request request = new Request();
request.setId(1);
assertEquals(request.getId(), 1);
	}

	@Test
	void testStatus() {
		Request request = new Request();
request.setStatus("pending");
assertEquals(request.getStatus(), "pending");
	}
	
	@Test
	void testRequest() {
		Request request = new Request();
request.setRequester(1);
assertEquals(request.getRequester(), 1);
	}
	@Test
	void testToString() {
	Request request = new Request();
assertNotNull(request.toString());



	}
	@Test
	void test() {
		Date date = new Date(0);
		Request request = new Request(1, 1, 1.1f, "i", date, "i", 1, date);
	}
}

