package com.revature.project1.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.revature.project1.dao.RequestDao;
import com.revature.project1.model.Request;

public class RequestServiceImplementation implements RequestService {

	private static RequestDao requestDao = RequestDao.getRequestDao();
	private static RequestService requestService;
	
	RequestServiceImplementation() {}
	
	public static RequestService getRequestService() {
        if (requestService == null) {
        	requestService = new RequestServiceImplementation();
        }
        return requestService;
	}

	@Override
	public Request findById(int id) {
		return requestDao.findById(id);
	}

	@Override
	public Request add(Request request) {
		return requestDao.add(request);
	}

	@Override
	public List<Request> findByRequester(int requester) {
		List<Request> allRequests = requestDao.findAll();
		List<Request> requestedBy = new ArrayList<Request>();
		for (Request r: allRequests) {
			//System.out.println("r = " + r.getStatus());
			if (r.getRequester() == requester) {
				requestedBy.add(r);
				//System.out.println("request received!");
			}
		}
		return requestedBy;
	}

	@Override
	public List<Request> findByDecidingManager(int decidingManager) {
	    List<Request> allRequests = requestDao.findAll();
	    List<Request> decidedBy = new ArrayList<Request>();
	    for (Request r: allRequests) {
	    	if (r.getRequester() == decidingManager) {
	    		decidedBy.add(r);
	    	}
	    }
	    return decidedBy;
	}

	@Override
	public List<Request> findApproved() {
	    List<Request> allRequests = requestDao.findAll();
	    List<Request> approved = new ArrayList<Request>();
	    for (Request r: allRequests) {
	    	if (r.getStatus().compareToIgnoreCase("approved") == 0) {
	    		approved.add(r);
	    	}
	    }
	    return approved;
	}

	@Override
	public List<Request> findDenied() {
        List<Request> allRequests = requestDao.findAll();
        List<Request> denied = new ArrayList<Request>();
        for (Request r: allRequests) {
        	if (r.getStatus().compareToIgnoreCase("denied") == 0) {
        		denied.add(r);
        	}
        }
        return denied;
	}

	@Override
	public List<Request> findPending() {
		List<Request> allRequests = requestDao.findAll();
		List<Request> pending = new ArrayList<Request>();
		for (Request r: allRequests) {
			if (r.getStatus().compareToIgnoreCase("pending") == 0) {
				pending.add(r);
			}
		}
		return pending;
	}

	@Override
	public List<Request> sortAmountAscending() {
		List<Request> allRequests = requestDao.findAll();
		Collections.sort(allRequests, Comparator.comparingDouble(Request::getAmount));
		return allRequests;
	}

	@Override
	public List<Request> sortAmoundDescending() {
	    List<Request> allRequests = requestDao.findAll();
	    Collections.sort(allRequests, Comparator.comparingDouble(Request::getAmount).reversed());
	    return allRequests;
	}

	@Override
	public List<Request> sortRequestDateAscending() {
		List<Request> allRequests = requestDao.findAll();
		Collections.sort(allRequests, (r1, r2) -> String.CASE_INSENSITIVE_ORDER.compare(r1.getRequestDate().toString(), r2.getRequestDate().toString()));
        Collections.reverse(allRequests);
        return allRequests;
	}

	@Override
	public List<Request> sortRequestDateDescending() {
		List<Request> allRequests = requestDao.findAll();
		Collections.sort(allRequests, (r1, r2) -> String.CASE_INSENSITIVE_ORDER.compare(r1.getRequestDate().toString(), r2.getRequestDate().toString()));
		return allRequests;
	}

	@Override
	public List<Request> sortDecisionDateAscending() {
		List<Request> allRequests = requestDao.findAll();
		Collections.sort(allRequests, (r1, r2) -> String.CASE_INSENSITIVE_ORDER.compare(r1.getDecisionDate().toString(), r2.getDecisionDate().toString()));
		Collections.reverse(allRequests);
		return allRequests;
	}

	@Override
	public List<Request> sortDecisionDateDescending() {
		List<Request> allRequests = requestDao.findAll();
		Collections.sort(allRequests, (r1, r2) -> String.CASE_INSENSITIVE_ORDER.compare(r1.getDecisionDate().toString(), r2.getDecisionDate().toString()));
		return allRequests;
	}

	@Override
	public Request update(Request request) {
		return requestDao.update(request);
	}

}
