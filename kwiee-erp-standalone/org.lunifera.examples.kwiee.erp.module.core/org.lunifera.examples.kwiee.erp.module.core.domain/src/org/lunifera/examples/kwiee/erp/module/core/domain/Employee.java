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

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Employee entity.
 * 
 * @author cvgaviao
 */
@Entity
public class Employee extends ABaseEntity implements Comparable<Employee> {

	private static final long serialVersionUID = -6236073454961349534L;

	private String lastName;

	private String firstName;

	@OneToOne
	private SystemUser systemUser;

	private int age;

	private Set<Permission> userPermissions;

	@OneToMany(mappedBy = "assignee")
	private Set<Task> assignedTasks;

	@OneToMany(mappedBy = "reporter")
	private Set<Task> reportedTasks;

	public Employee() {
		super();
	}

	public Employee(String lastName, String firstName, int age) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.age = age;
	}

	public int compareTo(Employee o) {

		return this.getFirstName().compareToIgnoreCase(o.getFirstName());
	}

	public int getAddress() {
		return age;
	}

	public Set<Task> getAssignedTasks() {
		return assignedTasks;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Set<Task> getReportedTasks() {
		return reportedTasks;
	}

	public SystemUser getUser() {
		return systemUser;
	}

	public Set<Permission> getUserPermissions() {
		return userPermissions;
	}

	public void setAddress(int age) {
		this.age = age;
	}

	public void setAssignedTasks(Set<Task> assignedTasks) {
		this.assignedTasks = assignedTasks;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setReportedTasks(Set<Task> reportedTasks) {
		this.reportedTasks = reportedTasks;
	}

	public void setUser(SystemUser systemUser) {
		this.systemUser = systemUser;
	}

	public void setUserPermissions(Set<Permission> userPermissions) {
		this.userPermissions = userPermissions;
	}

	public String toString() {
		return firstName + " " + lastName;
	}
}
