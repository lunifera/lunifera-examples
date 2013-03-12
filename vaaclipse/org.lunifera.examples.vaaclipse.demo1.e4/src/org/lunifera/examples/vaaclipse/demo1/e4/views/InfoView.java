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
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.model.application.MApplication;
import org.lunifera.examples.vaaclipse.demo1.e4.data.Task;
import org.lunifera.examples.vaaclipse.demo1.e4.editors.TaskEvents;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

public class InfoView {

	private IEventBroker eventBroker;
	private Label label;

	private EventHandler selectionChangedHandler = new EventHandler() {
		public void handleEvent(Event event) {
			Task task = (Task) event.getProperty("org.eclipse.e4.data");
			label.setValue(readHTML(task));
		}
	};

	@PostConstruct
	void registerHandler() {
		eventBroker.subscribe(TaskEvents.TOPIC_SELECTED,
				selectionChangedHandler);
	}

	@PreDestroy
	void unregisterHandlers() {
		eventBroker.unsubscribe(selectionChangedHandler);
	}

	@Inject
	public InfoView(VerticalLayout parent, IEclipseContext context,
			MApplication app) {

		label = new Label("", ContentMode.HTML);
		label.setSizeFull();
		parent.addComponent(label);

		label.setValue("");

		eventBroker = context.get(IEventBroker.class);
	}

	private String readHTML(Task task) {
		if (task == null) {
			return "";
		}
		URL url = getClass().getResource(
				task.getType() == Task.Type.ITEM ? "Info_TaskItem.html"
						: "Info_TaskCRM.html");

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
