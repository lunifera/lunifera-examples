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
package org.lunifera.examples.kwiee.erp.module.financial.accounting.presentation.web.vaadin;

import org.lunifera.examples.kwiee.erp.module.financial.accounting.bk.api.IBusinessKnowledgeServiceAccounting;
import org.lunifera.examples.kwiee.erp.utils.components.AbstractServiceComponent;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

public class VaadinComponentAcc extends AbstractServiceComponent implements
		EventHandler {

	private IBusinessKnowledgeServiceAccounting businessKnowledgeServiceAccounting;

	@Override
	protected String getComponentName() {
		return getClass().getSimpleName();
	}

	public IBusinessKnowledgeServiceAccounting getBusinessKnowledgeServiceAccounting() {
		return businessKnowledgeServiceAccounting;
	}

	public void bindBusinessKnowledgeServiceAccounting(
			IBusinessKnowledgeServiceAccounting businessKnowledgeServiceAccounting) {
		this.businessKnowledgeServiceAccounting = businessKnowledgeServiceAccounting;
	}

	public void unbindBusinessKnowledgeServiceAccounting(
			IBusinessKnowledgeServiceAccounting businessKnowledgeServiceAccounting) {
		if (this.businessKnowledgeServiceAccounting == businessKnowledgeServiceAccounting)
			this.businessKnowledgeServiceAccounting = null;
	}

	@Override
	public void handleEvent(Event event) {
		if (event.containsProperty("component")) {
			System.out.println("Event Captured:"+ event.getTopic());
		}
	}

}
