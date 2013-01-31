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
public class SystemUser extends ABaseEntity {

	private static final long serialVersionUID = 6773037774529900385L;

	private String name;

	private String password;

	@ManyToMany()
	private Set<Role> roles;

	@ManyToMany()
	private Set<Group> groups;

	public SystemUser() {

	}

	public Set<Group> getGroups() {
		return groups;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		if (password == null || password.isEmpty())
			return;
		this.password = password;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
