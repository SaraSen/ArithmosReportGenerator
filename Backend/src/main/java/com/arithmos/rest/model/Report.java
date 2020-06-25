package com.arithmos.rest.model;

public class Report {

	public Report() {

	}

	public Report(String team, String assigneee, String jiraID, String taskDesc, String comment, String onCall,
			String deliveryDate, String status, String blockers) {
		super();
		this.team = team;
		this.assigneee = assigneee;
		this.jiraID = jiraID;
		this.taskDesc = taskDesc;
		this.comment = comment;
		this.onCall = onCall;
		this.deliveryDate = deliveryDate;
		this.status = status;
		this.blockers = blockers;

	}

	private String team;
	private String assigneee;
	private String jiraID;
	private String taskDesc;
	private String comment;
	private String onCall;
	private String deliveryDate;
	private String status;
	private String blockers;
	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getAssigneee() {
		return assigneee;
	}

	public void setAssigneee(String assigneee) {
		this.assigneee = assigneee;
	}

	public String getJiraID() {
		return jiraID;
	}

	public void setJiraID(String jiraID) {
		this.jiraID = jiraID;
	}

	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String isOnCall() {
		return onCall;
	}

	public void setOnCall(String onCall) {
		this.onCall = onCall;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBlockers() {
		return blockers;
	}

	public void setBlockers(String blockers) {
		this.blockers = blockers;
	}
	
	

}
