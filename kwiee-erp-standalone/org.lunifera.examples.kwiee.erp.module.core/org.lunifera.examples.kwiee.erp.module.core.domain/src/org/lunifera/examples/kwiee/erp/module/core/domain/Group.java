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
import javax.persistence.ManyToMany;

@Entity
public class Group extends ABaseEntity {
	
	private static final long serialVersionUID = 6621632593040874116L;

	private String name;
	
	@ManyToMany()
	private Set<Role> roles;
	
	@ManyToMany(mappedBy="groups")
	private Set<SystemUser> systemUsers;

	public String getName() {
		return name;
	}

	public Set<SystemUser> getUsers() {
		return systemUsers;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setUsers(Set<SystemUser> systemUsers) {
		this.systemUsers = systemUsers;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
