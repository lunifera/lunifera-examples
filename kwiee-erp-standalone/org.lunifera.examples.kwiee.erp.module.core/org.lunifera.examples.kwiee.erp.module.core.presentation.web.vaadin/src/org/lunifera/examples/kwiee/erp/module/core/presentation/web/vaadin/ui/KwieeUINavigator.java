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
package org.lunifera.examples.kwiee.erp.module.core.presentation.web.vaadin.ui;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.lunifera.examples.kwiee.erp.module.core.domain.Task;
import org.lunifera.examples.kwiee.erp.module.core.presentation.web.vaadin.ui.common.IUIModule;
import org.lunifera.examples.kwiee.erp.module.core.presentation.web.vaadin.ui.common.IUIModuleProvider;
import org.lunifera.web.vaadin.common.OSGiUI;
import org.vaadin.artur.icepush.ICEPush;

import com.vaadin.annotations.Theme;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

/**
 * Thats the main UI element of kwiee example.
 */
@Theme(Reindeer.THEME_NAME)
public class KwieeUINavigator extends OSGiUI {

	private static final long serialVersionUID = 1L;

	private Map<IUIModuleProvider, Set<IUIModule>> mappings = new HashMap<IUIModuleProvider, Set<IUIModule>>();

	private ICEPush pusher;

	private CssLayout topFrame;
	private CssLayout bottomFrame;
	private CssLayout mainFrame;
	private TabSheet tabSheet;

	private Table taskTable;

	private Button refreshTasksButton;

	@Override
	public void init(VaadinRequest request) {

		// initialize push
		//
		pusher = new ICEPush();
		pusher.extend(this);

		// prepare UI
		//
		GridLayout root = new GridLayout(8, 8);
		root.setSizeFull();
		root.setMargin(true);
		root.setSpacing(true);
		setContent(root);

		// Create top frame
		topFrame = new CssLayout();
		topFrame.setWidth("100%");
		topFrame.setHeight("48px");
		root.addComponent(topFrame, 0, 0, 0, 7);

		// Create main frame
		mainFrame = new CssLayout();
		mainFrame.setSizeFull();
		root.addComponent(topFrame, 1, 1, 6, 6);
		tabSheet = new TabSheet();
		tabSheet.setSizeFull();
		mainFrame.addComponent(tabSheet);

		// task table
		createTasksTable();

		// Create bottom frame
		bottomFrame = new CssLayout();
		bottomFrame.setWidth("100%");
		bottomFrame.setHeight("48px");
		root.addComponent(bottomFrame, 7, 7, 0, 7);

	}

	@SuppressWarnings("serial")
	protected void createTasksTable() {

		// create layout
		VerticalLayout tableArea = new VerticalLayout();
		tableArea.setSizeFull();
		tabSheet.addTab(tableArea, "Tasks");

		// create table -> expand 1.0
		taskTable = new Table();
		tableArea.addComponent(taskTable);
		tableArea.setExpandRatio(taskTable, 1.0f);
		taskTable.setSizeFull();
		taskTable.setImmediate(true);
		taskTable.setBuffered(false);
		taskTable.setSelectable(true);
		taskTable.setVisibleColumns(Task.NATURAL_COL_ORDER);
		taskTable.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				showTask((Task) event.getProperty().getValue());
			}
		});
		
		refreshTasks();

		// create button -> 28px
		refreshTasksButton = new Button("Refresh");
		refreshTasksButton.setHeight("28px");
		tableArea.addComponent(refreshTasksButton);
		tableArea.setComponentAlignment(refreshTasksButton,
				Alignment.BOTTOM_LEFT);
		refreshTasksButton.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				refreshTasks();
			}
		});
	}

	/**
	 * Refreshes all tasks in the task table.
	 */
	protected void refreshTasks() {
		List<Task> tasks = null; // TODO
		BeanItemContainer<Task> container = new BeanItemContainer<Task>(
				Task.class);
		for (Task task : tasks) {
			container.addBean(task);
		}
		taskTable.setContainerDataSource(container);
	}

	/**
	 * Show a dialog for the task.
	 * 
	 * @param value
	 */
	protected void showTask(Task value) {

	}

	/**
	 * Calles by OSGi DS
	 * 
	 * @param provider
	 */
	public void addModule(IUIModuleProvider provider) {
		IUIModule module = provider.createModule();
		registerModule(provider, module);

		synchronized (this) {
			Component topComponent = module.getTopComponent();
			if (topComponent != null) {
				topFrame.addComponent(topComponent);
			}

			Component mainComponent = module.getMainComponent();
			if (mainComponent != null) {
				tabSheet.addTab(module.getMainComponent(), module.getCaption());
			}

			Component bottomComponent = module.getBottomComponent();
			if (bottomComponent != null) {
				bottomFrame.addComponent(bottomComponent);
			}

			pusher.push();
		}
	}

	/**
	 * Registers the module at the provider.
	 * 
	 * @param provider
	 * @param module
	 */
	private void registerModule(IUIModuleProvider provider, IUIModule module) {
		if (!mappings.containsKey(provider)) {
			mappings.put(provider, new HashSet<IUIModule>());
		}

		Set<IUIModule> modules = mappings.get(provider);
		modules.add(module);
	}

	/**
	 * Calles by OSGi DS
	 * 
	 * @param provider
	 */
	public void removeModule(IUIModuleProvider provider) {

		if (mappings.containsKey(provider)) {
			// lock the UI instance
			synchronized (this) {
				Set<IUIModule> modules = mappings.remove(provider);
				for (IUIModule module : modules) {
					Component topComponent = module.getTopComponent();
					if (topComponent != null) {
						topFrame.removeComponent(topComponent);
					}

					Component mainComponent = module.getMainComponent();
					if (mainComponent != null) {
						tabSheet.removeComponent(module.getMainComponent());
					}

					Component bottomComponent = module.getBottomComponent();
					if (bottomComponent != null) {
						bottomFrame.removeComponent(bottomComponent);
					}
				}

				pusher.push();
			}
		}
	}
}
