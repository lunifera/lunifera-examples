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

package org.lunifera.examples.vaaclipse.demo1.e4.handlers;

import org.lunifera.examples.vaaclipse.demo1.e4.constants.Demo1Constants;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.events.IEventBroker;

public class CollapseAll
{
	@Execute
	public void execute(IEventBroker broker)
	{
		broker.send(Demo1Constants.COLLAPSE_ALL, null);
	}
}
