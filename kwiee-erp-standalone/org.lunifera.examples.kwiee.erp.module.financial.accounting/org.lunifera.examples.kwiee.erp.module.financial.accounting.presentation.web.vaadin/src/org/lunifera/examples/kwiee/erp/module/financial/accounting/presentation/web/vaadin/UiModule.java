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

import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;

public class UiModule implements IUIModule {

	private CssLayout mainLayout;
	private Embedded topComponent;

	@Override
	public String getCaption() {
		return "Accounting";
	}

	@Override
	public Component getMainComponent() {
		if (mainLayout == null) {
			mainLayout = new CssLayout();
			mainLayout.addComponent(new Label("<h2>The accounting module</h2>",
					ContentMode.HTML));
		}

		return null;
	}

	@Override
	public Component getTopComponent() {
		if (topComponent == null) {
			topComponent = new Embedded("Accounting", new ThemeResource(
					"../icons/money_euro.png"));
		}
		return topComponent;
	}

	@Override
	public Component getBottomComponent() {
		return null;
	}

}
