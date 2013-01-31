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
public class Role extends ABaseEntity {

	private static final long serialVersionUID = 6404010014184492690L;

	private String name;

	@ManyToMany(mappedBy="roles")
	private Set<Permission> permissions;
	
	@ManyToMany(mappedBy="roles")
	private Set<Group> groups;
	
	@ManyToMany(mappedBy="roles")
	private Set<SystemUser> systemUsers;

	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	public Set<SystemUser> getUsers() {
		return systemUsers;
	}

	public void setUsers(Set<SystemUser> systemUsers) {
		this.systemUsers = systemUsers;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}
}
