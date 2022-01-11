package com.revature.project1.service;

import java.util.List;

import com.revature.project1.model.Request;

public interface RequestService {

	Request findById(int parseInt);

	Request add(Request request);

	List<Request> findByRequester(int parseInt);

	List<Request> findByDecidingManager(int parseInt);

	List<Request> findApproved();

	List<Request> findDenied();

	List<Request> findPending();

	List<Request> sortAmountAscending();

	List<Request> sortAmoundDescending();

	List<Request> sortRequestDateAscending();

	List<Request> sortRequestDateDescending();

	List<Request> sortDecisionDateAscending();

	List<Request> sortDecisionDateDescending();

	Request update(Request request);

}
