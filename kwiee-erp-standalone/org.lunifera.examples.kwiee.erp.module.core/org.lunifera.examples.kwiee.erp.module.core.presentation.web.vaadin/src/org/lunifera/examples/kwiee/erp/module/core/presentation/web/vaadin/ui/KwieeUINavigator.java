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
import org.lunifera.runtime.web.vaadin.osgi.common.OSGiUI;
import org.vaadin.artur.icepush.ICEPush;

import com.vaadin.annotations.Theme;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
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
	private Set<IUIModule> pendingModules = new HashSet<IUIModule>();

	private ICEPush pusher;

	private HorizontalLayout topFrame;
	private CssLayout bottomFrame;
	private CssLayout mainFrame;
	private TabSheet tabSheet;

	private Table taskTable;

	private Button refreshTasksButton;

	private CssLayout navFrame;

	private boolean initialized;

	@Override
	public void init(VaadinRequest request) {

		// initialize push
		//
		pusher = new ICEPush();
		pusher.extend(this);

		// prepare UI
		//
		GridLayout root = new GridLayout(8, 8);
		root.setStyleName(Reindeer.LAYOUT_BLUE);
		root.addStyleName("kwiee");
		root.setSizeFull();
		root.setMargin(new MarginInfo(false, true, true, true));
		root.setSpacing(false);
		setContent(root);

		root.setRowExpandRatio(0, 0.7f);
		root.setRowExpandRatio(1, 1.0f);
		root.setRowExpandRatio(2, 1.0f);
		root.setRowExpandRatio(3, 1.0f);
		root.setRowExpandRatio(4, 1.0f);
		root.setRowExpandRatio(5, 1.0f);
		root.setRowExpandRatio(6, 1.0f);
		root.setRowExpandRatio(7, 0.4f);

		// Create top frame
		topFrame = new HorizontalLayout();
		topFrame.addStyleName("k-topframe");
		topFrame.setMargin(true);
		topFrame.setSpacing(true);
		topFrame.setSizeFull();
		root.addComponent(topFrame, 1, 0, 7, 0);

		// Create top frame
		navFrame = new CssLayout();
		navFrame.addStyleName("k-navframe");
		navFrame.setSizeFull();
		root.addComponent(navFrame, 0, 1, 0, 6);

		// Create main frame
		mainFrame = new CssLayout();
		mainFrame.setSizeFull();
		root.addComponent(mainFrame, 1, 1, 7, 6);
		tabSheet = new TabSheet();
		tabSheet.setSizeFull();
		mainFrame.addComponent(tabSheet);

		// task table
		createTasksTable();

		// Create bottom frame
		bottomFrame = new CssLayout();
		bottomFrame.addStyleName("k-bottomframe");
		bottomFrame.setSizeFull();
		root.addComponent(bottomFrame, 0, 7, 7, 7);

		initialized = true;

		// attach the pending modules
		//
		synchronized (this) {
			for (IUIModule module : pendingModules) {
				attachModule(module);
			}
			pendingModules.clear();
		}

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
		taskTable.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				showTask((Task) event.getProperty().getValue());
			}
		});

		refreshTasks();

		// create button -> 28px
		HorizontalLayout buttonBar = new HorizontalLayout();
		buttonBar.setSizeFull();
		buttonBar.setMargin(true);
		buttonBar.setSpacing(true);
		buttonBar.setHeight("48px");
		tableArea.addComponent(buttonBar);

		refreshTasksButton = new Button("Refresh");
		buttonBar.addComponent(refreshTasksButton);
		buttonBar.setComponentAlignment(refreshTasksButton,
				Alignment.MIDDLE_LEFT);
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
		if (tasks != null) {
			for (Task task : tasks) {
				container.addBean(task);
			}
		}
		taskTable.setContainerDataSource(container);
		taskTable.setVisibleColumns(Task.NATURAL_COL_ORDER);
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

		if (!initialized) {
			pendingModules.add(module);
			return;
		}

		synchronized (this) {
			attachModule(module);
			pusher.push();
		}
	}

	/**
	 * Attaches the given module to the UI.
	 * 
	 * @param module
	 */
	protected void attachModule(IUIModule module) {
		Component topComponent = module.getTopComponent();
		if (topComponent != null) {
			topFrame.addComponent(topComponent);
			topComponent.addStyleName("k-navcomponent");
			topFrame.setComponentAlignment(topComponent, Alignment.MIDDLE_LEFT);
		}

		Component mainComponent = module.getMainComponent();
		if (mainComponent != null) {
			tabSheet.addTab(module.getMainComponent(), module.getCaption());
		}

		Component bottomComponent = module.getBottomComponent();
		if (bottomComponent != null) {
			bottomFrame.addComponent(bottomComponent);
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
