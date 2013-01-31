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
package org.lunifera.examples.kwiee.erp.module.core.bk;

import java.util.List;
import java.util.Map;

import org.osgi.framework.BundleContext;


public interface IBusinessKnowledgeServiceCore {

	/*
	 * Processes
	 */
	static final String PROCESS_ID_SYSTEM_SANITY_CHECK = "process_system_sanity_check";
	static final String PROCESS_ID_SYSTEM_SETUP = "process_system_setup";

	/**
	 * This process will certify the conditions of the environment needed to run
	 * Feast.
	 */
	public void startSystemSanityCheckProcess();

	public void startSystemSetupProcess();

	void abortProcessInstance(long processInstanceId);

	void addProcessesDefinitionResources(BundleContext bundleContext,
			String basePath, String includePattern, String excludePattern);

	void addFacts(List<?> facts);

	void addRulesDefinitionResources(BundleContext bundleContext,
			String basePath, String includePattern, String excludePattern);

	int fireAllRules();

	void persistKnowledgeBaseData();

	void purgeKnowledgeBaseData();

	void signalEvent(String type, Object event);

	void signalEvent(String type, Object event, long processInstanceId);

	long startProcess(String processId);

	long startProcess(String processId, Map<String, Object> properties);

}
