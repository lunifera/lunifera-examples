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
package org.lunifera.examples.kwiee.erp.module.financial.accounting.bk.internal;

import org.lunifera.examples.kwiee.erp.module.financial.accounting.bk.api.IBusinessKnowledgeServiceAccounting;
import org.lunifera.examples.kwiee.erp.utils.components.AbstractServiceComponentWithEntityManager;

public class BpmsCoreComponent extends AbstractServiceComponentWithEntityManager implements
		IBusinessKnowledgeServiceAccounting {

	@Override
	public void startSystemSanityCheckProcess() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startSystemSetupProcess() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getComponentName() {
		return getClass().getSimpleName();
	}




}
