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
import java.util.Map;
import java.util.Set;

import org.lunifera.examples.kwiee.erp.module.core.presentation.web.vaadin.ui.common.IUIModule;
import org.lunifera.examples.kwiee.erp.module.core.presentation.web.vaadin.ui.common.IUIModuleProvider;
import org.lunifera.web.vaadin.common.OSGiUI;
import org.vaadin.artur.icepush.ICEPush;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TabSheet;
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

		// Create bottom frame
		bottomFrame = new CssLayout();
		bottomFrame.setWidth("100%");
		bottomFrame.setHeight("48px");
		root.addComponent(bottomFrame, 7, 7, 0, 7);

	}

	/**
	 * Calles by OSGi DS
	 * 
	 * @param provider
	 */
	public void addModule(IUIModuleProvider provider) {
		IUIModule module = provider.createModule();
		registerModule(provider, module);

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
