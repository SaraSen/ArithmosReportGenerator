package com.arithmos.rest.model;

public class Report {

	public Report() {

	}

<<<<<<< Updated upstream
	public Report(String date, String team, String assignee, String jiraID, String taskDesc, String comment, String onCall,
			String deliveryDate, String status, String blockers) {
=======
	public Report(String date, String team, String assignee, String jiraID, String taskDesc, String comment,
			String onCall, String deliveryDate, String status, String blockers) {
>>>>>>> Stashed changes
		super();

		this.date = date;
		this.team = team;
		this.assignee = assignee;
		this.jiraID = jiraID;
		this.taskDesc = taskDesc;
		this.comment = comment;
		this.onCall = onCall;
		this.deliveryDate = deliveryDate;
		this.status = status;
		this.blockers = blockers;

	}

	private String date;
	private String team;
	private String assignee;
	private String jiraID;
	private String taskDesc;
	private String comment;
	private String onCall;
	private String deliveryDate;
	private String status;
	private String blockers;
	
<<<<<<< Updated upstream
		
=======
	

>>>>>>> Stashed changes
	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

<<<<<<< Updated upstream
	
	public String getJiraID() {
=======
		public String getJiraID() {
>>>>>>> Stashed changes
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

	public String getOnCall() {
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
