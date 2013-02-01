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
package org.lunifera.examples.kwiee.erp.module.core.services;

import java.util.List;
import java.util.Map;

import org.lunifera.examples.kwiee.erp.module.core.domain.Task;

public interface AdministrationService {

	public List<Task> fetchWithFilter(Map<String, Object> queryFilter,
			Object[] sortPropertyIds, boolean[] sortStates, int startIndex,
			int fetchSize);
	
	public int fetchSizeWithFilter(Map<String, Object> queryFilter);
	
	public void save(List<Task> addedObjects, List<Task> modifiedObjects,
			List<Task> removedObjects);
	
}
