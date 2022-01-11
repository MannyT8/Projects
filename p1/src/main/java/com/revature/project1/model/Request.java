package com.revature.project1.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "request")
public class Request {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@Column
	private int requester; 
	@Column
	private float amount;
	@Column
	private String reason;
	@Column(name = "request_date")
	private Date requestDate;
	@Column
	private String status; // pending, approved or denied
	@Column(name = "deciding_manager")
	private int decidingManager; // null when status is pending
	@Column(name = "decision_date")
	private Date decisionDate; // null when status is pending
	
	public Request(int id, int requester, float amount, String reason, Date requestDate, String status, int decidingManager,
			Date decisionDate) {
		super();
		this.id = id;
		this.requester = requester;
		this.amount = amount;
		this.reason = reason;
		this.requestDate = requestDate;
		this.status = status;
		this.decidingManager = decidingManager;
		this.decisionDate = decisionDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Request() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRequester() {
		return requester;
	}

	public void setRequester(int requester) {
		this.requester = requester;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getDecidingManager() {
		return decidingManager;
	}

	public void setDecidingManager(int decidingManager) {
		this.decidingManager = decidingManager;
	}

	public Date getDecisionDate() {
		return decisionDate;
	}

	public void setDecisionDate(Date decisionDate) {
		this.decisionDate = decisionDate;
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", requester=" + requester + ", amount=" + amount + ", reason=" + reason + ", requestDate=" + requestDate
				+ ", status=" + status + ", decidingManager=" + decidingManager + ", decisionDate=" + decisionDate
				+ "]";
	}	
}
