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
package org.lunifera.examples.kwiee.erp.module.core.presentation.web.vaadin.ui.common;

import com.vaadin.ui.Component;

/**
 * UI module is an abstraction above the UI elements the should be used in the
 * example navigator.
 */
public interface IUIModule {

	/**
	 * Returns the caption of the module.
	 * 
	 * @return
	 */
	String getCaption();

	/**
	 * Returns the main component that should be displayed in the main frame.
	 * <p>
	 * Multiple calls must return the same instance!
	 * 
	 * @return
	 */
	Component getMainComponent();

	/**
	 * Returns the top component that should be displayed in the top frame.
	 * <p>
	 * Multiple calls must return the same instance!
	 * 
	 * @return
	 */
	Component getTopComponent();

	/**
	 * Returns the bottom component that should be displayed in the bottom
	 * frame.
	 * <p>
	 * Multiple calls must return the same instance!
	 * 
	 * @return
	 */
	Component getBottomComponent();
}
