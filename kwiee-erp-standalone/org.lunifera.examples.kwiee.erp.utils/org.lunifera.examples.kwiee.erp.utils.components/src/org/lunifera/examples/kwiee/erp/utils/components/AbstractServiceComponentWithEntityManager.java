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
package org.lunifera.examples.kwiee.erp.utils.components;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.log.LogService;

public abstract class AbstractServiceComponentWithEntityManager extends
		AbstractServiceComponent {

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	@Override
	protected void activate(ComponentContext context,
			Map<String, Object> properties) {
		super.activate(context, properties);

		if (entityManagerFactory != null)
			entityManager = entityManagerFactory.createEntityManager();
	}

	@Override
	protected void deactivate(ComponentContext context,
			Map<String, Object> properties) {
		super.deactivate(context, properties);

		getEntityManager().close();
	}

	protected void bindEntityManagerFactory(
			EntityManagerFactory entityManagerFactory,
			Map<String, Object> properties) {
		this.entityManagerFactory = entityManagerFactory;
		getLogService().log(LogService.LOG_DEBUG,
				"Binding EntityManagerFactory");
	}

	protected void unbindEntityManagerFactory(
			EntityManagerFactory entityManagerFactory) {
		if (this.entityManagerFactory == entityManagerFactory) {
			this.entityManagerFactory = null;
		}
	}

	protected EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
