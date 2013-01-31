/*******************************************************************************
 *   Copyright (c) 2012, 2013 Committers of lunifera.org - Lunifera.org.
 *   All rights reserved. This program and the accompanying materials
 *   are made available under the terms of the Eclipse Public License v1.0
 *   which accompanies this distribution, and is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *        Florian Pirchner - initial API and implementation
 *******************************************************************************/
package org.lunifera.examples.kwiee.erp.module.financial.accounting.presentation.web.vaadin;

import org.lunifera.examples.kwiee.erp.module.core.presentation.web.vaadin.ui.common.IUIModule;
import org.lunifera.examples.kwiee.erp.module.core.presentation.web.vaadin.ui.common.IUIModuleProvider;

public class UIModuleProvider implements IUIModuleProvider {

	@Override
	public IUIModule createModule() {
		return new UiModule();
	}

}
