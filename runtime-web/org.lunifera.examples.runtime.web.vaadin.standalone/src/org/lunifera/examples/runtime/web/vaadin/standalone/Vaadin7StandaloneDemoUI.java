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

import org.json.JSONArray;
import org.json.JSONException;
import org.lunifera.runtime.web.vaadin.osgi.common.OSGiUI;

import com.vaadin.annotations.Theme;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.JavaScriptFunction;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

/**
 * Specify the class name after the factory name.
 */
@Theme(Reindeer.THEME_NAME)
public class Vaadin7StandaloneDemoUI extends OSGiUI {

	private static final String MSG = "Vaadin is running in an OSGi environment now!";
	private static final long serialVersionUID = 1L;
	private Label label;

	@Override
	public void init(VaadinRequest request) {

		JavaScript.getCurrent().addFunction("com.example.view.open",
				new JavaScriptFunction() {
					@Override
					public void call(JSONArray arguments) throws JSONException {
						String application = arguments.getString(0);
					}
				});

		JavaScript.getCurrent().addFunction("com.example.signoff",
				new JavaScriptFunction() {
					@Override
					public void call(JSONArray arguments) throws JSONException {
						// do signoff
					}
				});

		label = new Label(MSG);
		label.setValue("Click this <a href='javascript:com.example.view.open(\"org.example.view.help\");'>Link</a> to open view. <br>"
				+ "Or that <a href='javascript:com.example.signoff();'>Link</a> to signoff.");
		label.setContentMode(ContentMode.HTML);

		TextField text = new TextField();
		VerticalLayout layout = new VerticalLayout(label);
		layout.addComponent(text);
		layout.setStyleName(Reindeer.LAYOUT_BLUE);
		layout.setSizeFull();
		layout.setMargin(true);
		setContent(layout);

		layout.addShortcutListener(new ShortcutListener("E^xit") {
			@Override
			public void handleAction(Object sender, Object target) {
				System.out.println(target);
			}
		});

		text.addShortcutListener(new ShortcutListener("E^xit") {
			@Override
			public void handleAction(Object sender, Object target) {
				System.out.println(target);
			}
		});
	}
}
