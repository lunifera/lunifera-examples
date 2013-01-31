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
package org.lunifera.examples.kwiee.erp.server.application.equinox;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

public class ServerApplication implements IApplication {

	@Override
	public Object start(final IApplicationContext context) throws Exception {

		System.out.println("Starting Application...");

		return IApplication.EXIT_OK;
	}

	@Override
	public void stop() {
		System.out.println("Stopping Application...");
	}

}
