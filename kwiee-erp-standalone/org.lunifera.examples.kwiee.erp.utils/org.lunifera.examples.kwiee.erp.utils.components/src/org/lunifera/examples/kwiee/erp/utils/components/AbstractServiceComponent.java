/*******************************************************************************
 *   Copyright (c) 2012, 2013 Committers of lunifera.org - Lunifera.org.
 *   All rights reserved. This program and the accompanying materials
 *   are made available under the terms of the Eclipse Public License v1.0
 *   which accompanies this distribution, and is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *        Cristiano Gavião - initial API and implementation
 *******************************************************************************/
package org.lunifera.examples.kwiee.erp.utils.components;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.log.LogService;

public abstract class AbstractServiceComponent {
	private BundleContext bundleContext;
	private Dictionary<String, Object> cachedConfigurationData;
	private boolean isReady;
	private LogService logService;
	private EventAdmin eventAdmin;

	protected void activate(ComponentContext context,
			Map<String, Object> properties) {

		getLogService().log(LogService.LOG_DEBUG,
				"Activating '" + getComponentName() + "'...");

		Dictionary<String, Object> config = new Hashtable<String, Object>();
		if (properties != null) {
			for (String item : properties.keySet()) {
				config.put(item, properties.get(item));
			}
		}
		cachedConfigurationData = config;

		// save bundleContext reference...
		bundleContext = context.getBundleContext();

		isReady = true;

		// send a post to notify bundles that BPM knowledge is ready to supply
		// sessions...
		postEvent(EventCatalog.TOPIC_SERVICE_READY, getComponentName());
	}

	protected void bindLogService(LogService logService) {
		this.logService = logService;
		getLogService().log(LogService.LOG_DEBUG, "Binded LogService.");
	}

	protected void deactivate(ComponentContext context,
			Map<String, Object> properties) {
		isReady = false;
		cachedConfigurationData = null;
		bundleContext = null;

		getLogService().log(LogService.LOG_INFO,
				"Deactivating '" + getComponentName() + "'...");

		// send a post to notify bundles that this component is finalized...
		postEvent(EventCatalog.TOPIC_SERVICE_FINALIZED, getComponentName());
	}

	protected BundleContext getBundleContext() {
		return bundleContext;
	}

	protected Dictionary<String, Object> getCachedConfigurationData() {
		return cachedConfigurationData;
	}

	protected abstract String getComponentName();

	protected LogService getLogService() {
		return logService;
	}

	public boolean isReady() {
		return isReady;
	}

	protected void modified(ComponentContext context,
			Map<String, Object> properties) {

		getLogService().log(
				LogService.LOG_INFO,
				getComponentName()
						+ " was modified. Service need to be restarted.");
		// send a post to notify bundles that this component is being
		// restarted...
		postEvent(EventCatalog.TOPIC_SERVICE_RESTARTED, getComponentName());
	}

	protected void unbindLogService(LogService logService) {
		if (this.logService == logService) {
			getLogService().log(LogService.LOG_DEBUG, "Unbinded LogService.");
			this.logService = null;
		}
	}

	protected void bindEventAdmin(EventAdmin eventAdmin) {
		this.eventAdmin = eventAdmin;
		getLogService().log(LogService.LOG_DEBUG, "Binded EventAdminService.");
	}

	protected EventAdmin getEventAdmin() {
		return eventAdmin;
	}

	protected void postEvent(String eventTopic) {
		Map<String, ?> properties = new HashMap<String, Object>();
		postEvent(eventTopic, properties);

	}
	protected void postEvent(String eventTopic, String componentName) {
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("component", componentName);
		postEvent(eventTopic, properties);
		
	}

	protected void postEvent(String eventTopic, Map<String, ?> properties) {
		Event event = new Event(eventTopic, properties);
		eventAdmin.postEvent(event);

	}

	protected void sendEvent(String eventTopic) {
		Dictionary<String, ?> properties = new Hashtable<String, Object>();
		sendEvent(eventTopic, properties);

	}

	protected void sendEvent(String eventTopic, Dictionary<String, ?> properties) {
		Event event = new Event(eventTopic, properties);
		eventAdmin.sendEvent(event);

	}

	protected void unbindEventAdmin(EventAdmin eventAdmin) {
		if (this.eventAdmin == eventAdmin) {
			this.eventAdmin = null;
			getLogService().log(LogService.LOG_DEBUG,
					"Unbinded EventAdminService.");
		}
	}
}
