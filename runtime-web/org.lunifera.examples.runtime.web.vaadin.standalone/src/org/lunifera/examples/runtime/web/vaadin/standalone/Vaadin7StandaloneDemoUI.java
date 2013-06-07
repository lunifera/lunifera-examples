/*******************************************************************************
 * Copyright (c) 2012 by committers of lunifera.org
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Florian Pirchner - initial API and implementation
 *    
 *******************************************************************************/
package org.lunifera.examples.runtime.web.vaadin.standalone;

import org.lunifera.runtime.web.vaadin.osgi.common.OSGiUI;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

/**
 * Specify the class name after the factory name.
 */
@Push
@Theme(Reindeer.THEME_NAME)
public class Vaadin7StandaloneDemoUI extends OSGiUI {

	private static final String MSG = "Vaadin is running in an OSGi environment now!";
	private static final long serialVersionUID = 1L;
	private Label label;

	@Override
	public void init(VaadinRequest request) {

		label = new Label(MSG);

		VerticalLayout layout = new VerticalLayout(label);
		layout.setStyleName(Reindeer.LAYOUT_BLUE);
		layout.setSizeFull();
		layout.setMargin(true);
		setContent(layout);
	}
}
