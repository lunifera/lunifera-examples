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
package org.lunifera.examples.kwiee.erp.module.core.bk.internal;

import java.util.List;
import java.util.Map;

import org.lunifera.examples.kwiee.erp.module.core.bk.IBusinessKnowledgeServiceCore;
import org.lunifera.examples.kwiee.erp.utils.components.AbstractServiceComponentWithEntityManager;
import org.lunifera.examples.kwiee.erp.utils.components.EventCatalog;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.log.LogService;

public class BusinessKnowledgeComponentCore extends
		AbstractServiceComponentWithEntityManager implements
		IBusinessKnowledgeServiceCore {

	@Override
	public void startSystemSanityCheckProcess() {
		if (!isReady()) {
			getLogService().log(LogService.LOG_ERROR,
					"BPM Core Component was not properly started...");
			return;
		}
		startProcess(PROCESS_ID_SYSTEM_SANITY_CHECK, null);
		// send a post to notify bundles that BPM knowledge is ready to supply
		// sessions...
		postEvent(EventCatalog.TOPIC_SERVICE_READY, getComponentName());

	}

	@Override
	protected void modified(ComponentContext context,
			Map<String, Object> properties) {
		// TODO Auto-generated method stub
		super.modified(context, properties);
		
		// send a post to notify bundles that this component is being
		// restarted...
		postEvent(EventCatalog.TOPIC_SERVICE_RESTARTED, getComponentName());

	}
	
	@Override
	public void startSystemSetupProcess() {
		if (!isReady()) {
			getLogService().log(LogService.LOG_ERROR,
					"BPM Core Component was not properly started...");
			return;
		}
		startProcess(PROCESS_ID_SYSTEM_SETUP, null);

	}

	@Override
	public void abortProcessInstance(long processInstanceId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addFacts(List<?> facts) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addProcessesDefinitionResources(BundleContext bundleContext,
			String basePath, String includePattern, String excludePattern) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addRulesDefinitionResources(BundleContext bundleContext,
			String basePath, String includePattern, String excludePattern) {
		// TODO Auto-generated method stub

	}

	@Override
	public int fireAllRules() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void persistKnowledgeBaseData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void purgeKnowledgeBaseData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void signalEvent(String type, Object event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void signalEvent(String type, Object event, long processInstanceId) {
		// TODO Auto-generated method stub

	}

	@Override
	public long startProcess(String processId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long startProcess(String processId, Map<String, Object> properties) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected String getComponentName() {
		return getClass().getSimpleName();
	}

}
