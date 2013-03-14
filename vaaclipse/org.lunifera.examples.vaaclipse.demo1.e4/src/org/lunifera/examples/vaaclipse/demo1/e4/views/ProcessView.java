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

import com.vaadin.ui.Alignment;

import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.VerticalLayout;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.model.application.MApplication;
import org.lunifera.examples.vaaclipse.demo1.e4.Demo1Activator;
import org.lunifera.examples.vaaclipse.demo1.e4.data.Task;
import org.lunifera.examples.vaaclipse.demo1.e4.editors.TaskEvents;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;
import org.semanticsoft.vaaclipse.publicapi.resources.ResourceHelper;

public class ProcessView {
	@Inject
	private IEventBroker eventBroker;
	private VerticalLayout parent;

	private Embedded embedded;

	@Inject
	public ProcessView(VerticalLayout parent, IEclipseContext context,
			MApplication app) {
		this.parent = parent;
		parent.setSizeFull();
		parent.setMargin(true);
		
		embedded = new Embedded();
		parent.addComponent(embedded);
		parent.setComponentAlignment(embedded, Alignment.MIDDLE_CENTER);
	}

	@PostConstruct
	protected void setup() {
		eventBroker.subscribe(TaskEvents.TOPIC_SELECTED, new EventHandler() {
			@Override
			public void handleEvent(Event event) {
				embedded.setWidth("85%");
				Task task = (Task) event.getProperty("org.eclipse.e4.data");
				switch (task.getType()) {
				case ITEM:
					embedded.setSource(ResourceHelper
							.createResource("platform:/plugin/org.lunifera.examples.vaaclipse.demo1.e4/img/bpm/BPM3.png"));
					break;
				case CRM:
					embedded.setSource(ResourceHelper
							.createResource("platform:/plugin/org.lunifera.examples.vaaclipse.demo1.e4/img/bpm/BPM2.png"));
					break;
				}
			}
		});
	}

	private Resource findImage(String name) {
		return new FileResource(Demo1Activator.findImage(name));
	}
}
