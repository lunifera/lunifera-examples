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

public final class EventCatalog {

	public static final String TOPIC_BASE = "org/lunifera/examples/kwiee/erp/events/";
	public static final String TOPIC_SERVICE_BASE = TOPIC_BASE + "service/";
	public static final String TOPIC_SERVICE_ALL = TOPIC_SERVICE_BASE + "*";
	public static final String TOPIC_SERVICE_READY = TOPIC_SERVICE_BASE + "READY";
	public static final String TOPIC_SERVICE_FINALIZED = TOPIC_SERVICE_BASE + "FINALIZED";	
	public static final String TOPIC_SERVICE_RESTARTED = TOPIC_SERVICE_BASE + "RESTARTED";	

}
