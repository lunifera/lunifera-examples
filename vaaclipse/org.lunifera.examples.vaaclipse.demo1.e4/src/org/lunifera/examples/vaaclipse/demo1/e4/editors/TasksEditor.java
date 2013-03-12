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

package org.lunifera.examples.vaaclipse.demo1.e4.editors;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.RowHeaderMode;
import com.vaadin.ui.VerticalLayout;
import javax.inject.Inject;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.ui.basic.MInputPart;
import org.eclipse.e4.ui.services.internal.events.EventBroker;
import org.lunifera.examples.vaaclipse.demo1.e4.Demo1Activator;
import org.lunifera.examples.vaaclipse.demo1.e4.data.Task;

/**
 * @author rushan
 * 
 */
public class TasksEditor {
	protected Label text;
	protected String content;
	@Inject
	EventBroker eventBroker;
	@Inject
	IEclipseContext context;
	private Table table;

	@SuppressWarnings("serial")
	@Inject
	public TasksEditor(VerticalLayout layout, MInputPart inputPart) {
		layout.setMargin(true);

		table = new Table();
		table.setSelectable(true);
		table.setImmediate(true);
		table.setSizeFull();

		BeanItemContainer<Task> container = new BeanItemContainer<Task>(
				Task.class);
		container.addBean(new Task(Task.Type.ITEM, "Update Item 1234",
				"Item 1234 has to be updated with additional values.",
				findImage("item.png")));
		container.addBean(new Task(Task.Type.ITEM, "Update Item 9999",
				"Item 9999 has to be updated with additional values.",
				findImage("item.png")));
		container.addBean(new Task(Task.Type.CRM, "Update CRM Address",
				"Please verify the address of customer 56478",
				findImage("crm.png")));

		table.setContainerDataSource(container);
		table.setVisibleColumns(Task.visibleColumns);

		table.setRowHeaderMode(RowHeaderMode.ICON_ONLY);
		table.setItemIconPropertyId("icon");

		layout.addComponent(table);

		table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
			@SuppressWarnings("restriction")
			@Override
			public void itemClick(ItemClickEvent event) {
				eventBroker.send(TaskEvents.TOPIC_SELECTED, event.getItemId());
			}
		});
	}

	private Resource findImage(String name) {
		return new FileResource(Demo1Activator.findImage(name));
	}
}
