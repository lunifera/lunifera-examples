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
package org.lunifera.examples.kwiee.erp.module.core.presentation.web.vaadin;

import java.util.List;

import org.lunifera.examples.kwiee.erp.module.core.bk.IBusinessKnowledgeServiceCore;
import org.lunifera.examples.kwiee.erp.module.core.domain.Task;
import org.lunifera.examples.kwiee.erp.module.core.services.AdministrationService;
import org.lunifera.examples.kwiee.erp.utils.components.AbstractServiceComponent;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

public class VaadinComponentCore extends AbstractServiceComponent implements
		EventHandler {

	private IBusinessKnowledgeServiceCore businessKnowledgeServiceCore;
	private AdministrationService administrationService;

	public IBusinessKnowledgeServiceCore getBusinessKnowledgeServiceCore() {
		return businessKnowledgeServiceCore;
	}

	public void bindBusinessKnowledgeServiceCore(
			IBusinessKnowledgeServiceCore businessKnowledgeServiceCore) {
		this.businessKnowledgeServiceCore = businessKnowledgeServiceCore;
	}

	public void unbindBusinessKnowledgeServiceCore(
			IBusinessKnowledgeServiceCore businessKnowledgeServiceCore) {
		if (this.businessKnowledgeServiceCore == businessKnowledgeServiceCore)
			this.businessKnowledgeServiceCore = null;
	}

	@Override
	protected String getComponentName() {
		return getClass().getSimpleName();
	}

	@Override
	public void handleEvent(Event event) {
		// When things are ok 
		
		if (event.containsProperty("component")){
			
			// I get some data from service and print it.
			List<Task> listTask = getAdministrationService().fetchWithFilter(null, null, null, 0, 0);
			for (Task task : listTask) {
				System.out.println(task.getSubject());
			}
			
			// and start some process
			getBusinessKnowledgeServiceCore().startSystemSetupProcess();
		}
		
	}

	public AdministrationService getAdministrationService() {
		return administrationService;
	}

	public void bindAdministrationService(AdministrationService administrationService) {
		this.administrationService = administrationService;
	}
	public void unbindAdministrationService(AdministrationService administrationService) {
		this.administrationService = administrationService;
	}
}
