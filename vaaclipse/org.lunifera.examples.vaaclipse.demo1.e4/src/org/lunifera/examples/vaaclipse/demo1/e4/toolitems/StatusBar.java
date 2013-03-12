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

package org.lunifera.examples.vaaclipse.demo1.e4.toolitems;

import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Label;
import javax.inject.Inject;
import org.eclipse.e4.core.contexts.IEclipseContext;

public class StatusBar
{
	@Inject
	public void EditModeToolItem(ComponentContainer parent, IEclipseContext context)
	{
		//the parent - is toolcontrol widget, we must add style for layouting in trimbar to parent widget, not to user control
		//parent.addStyleName("editmode");
		
		final Label label = new Label("Ready");
		parent.addComponent(label);
	}
}
