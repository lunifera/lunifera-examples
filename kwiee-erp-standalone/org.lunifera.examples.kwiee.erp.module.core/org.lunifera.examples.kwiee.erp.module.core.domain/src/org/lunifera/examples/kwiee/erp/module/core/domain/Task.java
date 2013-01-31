/*******************************************************************************
 *   Copyright (c) 2012, 2013 Committers of lunifera.org - Lunifera.org.
 *   All rights reserved. This program and the accompanying materials
 *   are made available under the terms of the Eclipse Public License v1.0
 *   which accompanies this distribution, and is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *        Cristiano Gavião - initial API and implementation
 *******************************************************************************/
package org.lunifera.examples.kwiee.erp.module.core.domain;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Task Entity.
 * 
 * @author cvgaviao
 */
@Entity
public class Task extends ABaseEntity {

	private static final long serialVersionUID = -4189854932256938140L;
	
	/**
	 * Natural property order for Task bean. Used in tables and forms.
	 */
	public static final Object[] NATURAL_COL_ORDER = new Object[] {
			"objectId", "creationDate", "subject", "dueDate", "reporter",
			"assignee", "details", "status", "conclusionDate" };

	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	private String subject;

	@Temporal(TemporalType.DATE)
	private Date dueDate;

	@ManyToOne
	private Employee reporter;

	@ManyToOne
	private Employee assignee;
	
	private String details;

	private TaskStatus status;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date conclusionDate;



	public Task() {
		super();
		this.creationDate = Calendar.getInstance().getTime();
		this.status = TaskStatus.OPEN;
	}


	public TaskStatus getStatus() {
		return status;
	}

	public void setOperation(TaskStatus status) {
		this.status = status;
	}

	public Date getCreationTime() {
		return creationDate;
	}

	public void setCreationTime(Date taskCreationTime) {
		this.creationDate = taskCreationTime;
	}

	public String toString() {
		return "(" + creationDate + " - " + "Acct#: " + assignee.getFirstName()
				+ " " + status.toString() + ")";
	}

	public Employee getAssignee() {
		return assignee;
	}


	public void setAssignee(Employee assignee) {
		this.assignee = assignee;
	}


	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String detail) {
		this.details = detail;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getConclusionDate() {
		return conclusionDate;
	}

	public void setConclusionDate(Date conclusionDate) {
		this.conclusionDate = conclusionDate;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}


	public Employee getReporter() {
		return reporter;
	}


	public void setReporter(Employee reporter) {
		this.reporter = reporter;
	}
}
