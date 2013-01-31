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
package org.lunifera.examples.kwiee.erp.module.core.persistence;

import org.lunifera.examples.kwiee.erp.utils.components.AbstractServiceComponent;

public class PersistenceComponentCore extends AbstractServiceComponent {

	@Override
	protected String getComponentName() {
		return getClass().getSimpleName();
	}

}
