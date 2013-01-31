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
package org.lunifera.examples.kwiee.erp.module.financial.accounting.bk.api;


public interface IBusinessKnowledgeServiceAccounting  {

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

}
