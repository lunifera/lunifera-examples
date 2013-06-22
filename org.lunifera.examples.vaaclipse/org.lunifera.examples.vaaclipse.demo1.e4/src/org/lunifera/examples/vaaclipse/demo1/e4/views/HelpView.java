/*******************************************************************************
 * Copyright (c) 2012 Rushan R. Gilmullin and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Rushan R. Gilmullin - initial API and implementation
 *******************************************************************************/

package org.lunifera.examples.vaaclipse.demo1.e4.views;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import javax.inject.Inject;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.workbench.modeling.ISelectionListener;

@SuppressWarnings("restriction")
public class HelpView {

	private Label label;
	
	@Inject
	public HelpView(VerticalLayout parent, IEclipseContext context,
			MApplication app) {

		label = new Label("", ContentMode.HTML);
		label.setSizeFull();
		parent.addComponent(label);
		parent.setMargin(true);
		
		label.setValue(readHTML());
	}

	private String readHTML() {
		URL url = getClass().getResource("Help.html");

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					url.openStream()));

			StringBuilder builder = new StringBuilder();
			while (reader.ready()) {
				builder.append(reader.readLine());
				builder.append("\n");
			}

			return builder.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
