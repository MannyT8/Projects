package com.revature.project1.controller;

import java.sql.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.internal.build.AllowSysOut;

import com.google.gson.Gson;
import com.revature.project1.dao.RequestDao;
import com.revature.project1.model.Request;
import com.revature.project1.service.RequestService;
import com.revature.project1.service.RequestServiceImplementation;

import io.javalin.http.Handler;

public class RequestController {
	private static int lever = 0;
	private static String status = null;

	private static RequestDao requestDao = RequestDao.getRequestDao();
	private static RequestService requestService = RequestServiceImplementation.getRequestService();
	private static Gson gson = new Gson();
	
	public static Handler add = (ctx) -> {
		//int userId = Integer.parseInt(ctx.formParam("userId"));
		int userId = UserController.getUserId();
    	double amount = Double.parseDouble(ctx.formParam("amount"));
    	String reason = ctx.formParam("reason");
    	Date requestDate = new Date(System.currentTimeMillis());
    	String status = "pending";
    	Request request = new Request();
    	request.setRequester(userId);
    	request.setAmount((float) amount);
    	request.setReason(reason);
    	request.setRequestDate(requestDate);
    	request.setStatus(status);
    	requestService.add(request);
    	if(request != null) {
    		ctx.redirect("/submitted.html");
			System.out.println("request worked");
    	}
    		else {
    			System.out.println("request failed");
    		}
	};
	
	public static Handler findById = (ctx) -> {
		String requestId = ctx.pathParam("requestId");
		try {
			Request request = requestService.findById(Integer.parseInt(requestId));
			String json = gson.toJson(request);
			ctx.result(json);
			ctx.status(200);
		} catch (Exception ex) {
			ctx.status(404);
			System.out.println(ex.getMessage());
			// error handling
		}
	};
	
	public static Handler findMine = (ctx) -> {
		System.out.println("findmine");

		if(ctx.formParam("status")!=null) {
			status = ctx.formParam("status");
			}
		System.out.println("status = " + status);

		//String sortRequestDate = ctx.queryParam("sort_request_date");
		int user = UserController.getUserId();
		System.out.println("user = " + user);
		
		List<Request> userRequests = requestService.findByRequester(user); //find all requests from user
		List<Request> requests = new ArrayList<Request>();
	    System.out.println("request size = " + userRequests.size());
		if (status != null) {
				for(Request r: userRequests) {
					if (userRequests.contains(r) && (r.getStatus().compareToIgnoreCase(status) == 0 || status.equals("all"))) {
						//if request matches user and the status is pending, add it to list of pending requests
						requests.add(r);
					}
				
//				
//			 else if (userRequests.contains(r) && r.getStatus().compareToIgnoreCase("approved") == 0) {
//				List<Request> approvedRequests = requestService.findApproved();
//				for (Request r: userRequests) {
//					if (approvedRequests.contains(r)) {
//						requests.add(r);
//					}
//				}
//			} else if (status.compareToIgnoreCase("denied") == 0) {
//				List<Request> deniedRequests = requestService.findDenied();
//				for (Request r: userRequests) {
//					if (deniedRequests.contains(r)) {
//						requests.add(r);
//					}
//				}
//			}
			}} else {
			List<Request> sorted = requestService.sortDecisionDateAscending();
			for (Request r: sorted) {
				if (userRequests.contains(r)) {
					requests.add(r);
				}
			}
		}
		if(ctx.formParam("status")!=null) {
			ctx.redirect("ref/requests.html");
			}
		String json = gson.toJson(requests);
		ctx.result(json);	
	};
	
	public static Handler findAll = (ctx) -> {
		if(ctx.formParam("status")!=null) {
		status = ctx.formParam("status");
		}
		System.out.println("status = " + status);

		//String sortRequestDate = ctx.queryParam("sort_request_date");
		//int user = UserController.getUserId();
		//System.out.println("user = " + user);
		
		List<Request> userRequests = requestDao.findAll(); //find all requests from user
		List<Request> requests = new ArrayList<Request>();
	  //  System.out.println("request size = " + userRequests.size());
		if (status != null) {
				for(Request r: userRequests) {
					if (userRequests.contains(r) && (r.getStatus().compareToIgnoreCase(status) == 0)) {
						//if request matches user and the status is pending, add it to list of pending requests
						requests.add(r);
					}
				
			
			 else if (userRequests.contains(r)  && status.equals("resolved") && (r.getStatus().compareToIgnoreCase("pending") != 0)) {
						requests.add(r);
					}
			
			}} else {
			List<Request> sorted = requestService.sortDecisionDateAscending();
			for (Request r: sorted) {
				if (userRequests.contains(r)) {
					requests.add(r);
				}
			}
		}
		if(ctx.formParam("status")!=null) {
		ctx.redirect("ref/allRequests.html");
		}
		String json = gson.toJson(requests);
		ctx.result(json);	
		
	};
	
	public static Handler findPending = (ctx) -> {
		String status = "pending";
		System.out.println("status = " + status);

		//String sortRequestDate = ctx.queryParam("sort_request_date");
		//int user = UserController.getUserId();
		//System.out.println("user = " + user);
		
		List<Request> userRequests = requestDao.findAll(); //find all requests from user
		List<Request> requests = new ArrayList<Request>();
	    System.out.println("request size = " + userRequests.size());
		if (status != null) {
				for(Request r: userRequests) {
					if (userRequests.contains(r) && (r.getStatus().compareToIgnoreCase(status) == 0)) {
						//if request matches user and the status is pending, add it to list of pending requests
						requests.add(r);
					}
				
			
			 else if (userRequests.contains(r)  && status.equals("resolved") && (r.getStatus().compareToIgnoreCase("pending") != 0)) {
						requests.add(r);
					}
			
			}} else {
			List<Request> sorted = requestService.sortDecisionDateAscending();
			for (Request r: sorted) {
				if (userRequests.contains(r)) {
					requests.add(r);
				}
			}
		}
		String json = gson.toJson(requests);
		ctx.result(json);	
		//ctx.redirect("ref/pendingRequests.html");
	};

	public static Handler findResolved = (ctx) -> {
		String status = "resolved";
		System.out.println("status = " + status);

		//String sortRequestDate = ctx.queryParam("sort_request_date");
		//int user = UserController.getUserId();
		//System.out.println("user = " + user);
		
		List<Request> userRequests = requestDao.findAll(); //find all requests from user
		List<Request> requests = new ArrayList<Request>();
	    System.out.println("request size = " + userRequests.size());
		if (status != null) {
				for(Request r: userRequests) {
					if (userRequests.contains(r) && (r.getStatus().compareToIgnoreCase(status) == 0)) {
						//if request matches user and the status is pending, add it to list of pending requests
						requests.add(r);
					}
				
			
			 else if (userRequests.contains(r)  && status.equals("resolved") && (r.getStatus().compareToIgnoreCase("pending") != 0)) {
						requests.add(r);
					}
			
			}} else {
			List<Request> sorted = requestService.sortDecisionDateAscending();
			for (Request r: sorted) {
				if (userRequests.contains(r)) {
					requests.add(r);
				}
			}
		}
		String json = gson.toJson(requests);
		ctx.result(json);	
	};

	public static Handler update = (ctx) -> {
		String status = ctx.formParam("status");
		int id = Integer.parseInt(ctx.formParam("id"));
		
		Request idRequest = requestService.findById(id); //find single request from user
		idRequest.setStatus(status); // set status to approved or denied
    	
    	int decidingManager = UserController.getUserId();
    	idRequest.setDecidingManager(decidingManager);
    	
    	Date decisionDate = new Date(System.currentTimeMillis());

		idRequest.setDecisionDate(decisionDate); // set decisionDate
		requestService.update(idRequest); 
		
		Request returnedRequest = requestService.update(idRequest); // update status for requestid
		ctx.result(gson.toJson(returnedRequest));
		String json = gson.toJson(idRequest);
		ctx.result(json);	
		ctx.redirect("/ref/resolvedRequests.html");
		//ctx.status(202);
	};

	public static Handler viewOneEmployee = (ctx) -> {
		if(ctx.formParam("userId")!=null){
		lever = Integer.parseInt(ctx.formParam("userId"));
		}
		System.out.println("lever = " + lever);
		List<Request> userRequests = requestService.findByRequester(lever); //find all requests from user
	    //System.out.println("request size = " + userRequests.size());
				
					
		String json = gson.toJson(userRequests);
		ctx.result(json);	

		if(ctx.formParam("userId")!=null) {
		ctx.redirect("/ref/MVOE.html");
		}
		else {
		lever = 0;
		}

	};
	

	/*
	 * public static Handler findAll = (ctx) -> { System.out.println("it worked");
	 * 
	 * String requester = ctx.queryParam("requester"); String decidingManager =
	 * ctx.queryParam("deciding_manager");; String status =
	 * ctx.queryParam("status"); String sortAmount = ctx.queryParam("sort_amount");
	 * String sortRequestDate = ctx.queryParam("sort_request_date"); String
	 * sortDecisionDate = ctx.queryParam("sort_decision_date"); List<Request>
	 * requests = new ArrayList<Request>(); if (requester != null) { requests =
	 * requestService.findByRequester(Integer.parseInt(requester)); } else if
	 * (decidingManager != null) { requests =
	 * requestService.findByDecidingManager(Integer.parseInt(decidingManager)); }
	 * else if (status != null) { if (status.compareToIgnoreCase("approved") == 0) {
	 * requests = requestService.findApproved(); } else if
	 * (status.compareToIgnoreCase("denied") == 0) { requests =
	 * requestService.findDenied(); } else { requests =
	 * requestService.findPending(); } } else if (sortAmount != null) { if
	 * (sortAmount.compareToIgnoreCase("asc") == 0) { requests =
	 * requestService.sortAmountAscending(); } else { requests =
	 * requestService.sortAmoundDescending(); } } else if (sortRequestDate != null)
	 * { if (sortRequestDate.compareToIgnoreCase("asc") == 0) { requests =
	 * requestService.sortRequestDateAscending(); } else { requests =
	 * requestService.sortRequestDateDescending(); } } else { if
	 * (sortDecisionDate.compareToIgnoreCase("asc") == 0) { requests =
	 * requestService.sortDecisionDateAscending(); } else { requests =
	 * requestService.sortDecisionDateDescending(); } } String json =
	 * gson.toJson(requests); ctx.result(json); ctx.status(200); };
	 */    
	/*
	 * public static Handler update = (ctx) -> {
	 * System.out.println("findAll Handler!!!");
	 * 
	 * 
	 * String body = ctx.body(); System.out.println("body = " + body);
	 *  Request  request = gson.fromJson(body, Request.class);
	 * 
	 * System.out.println("request = "); 
	 * Request returnedRequest =  requestService.update(request); 
	 * ctx.result(gson.toJson(returnedRequest));
	 * ctx.status(202);
	 * 
	 * };
	 */}