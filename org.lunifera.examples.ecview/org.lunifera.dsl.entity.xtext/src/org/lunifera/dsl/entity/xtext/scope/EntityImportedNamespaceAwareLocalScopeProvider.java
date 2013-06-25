/**
 * Copyright (c) 2011 - 2012, Florian Pirchner - lunifera.org
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * 		Hans Georg Glöckler - Initial implementation
 * 		Florian Pirchner - Initial implementation
 */
package org.lunifera.dsl.entity.xtext.scope;

import static java.util.Collections.singletonList;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.ISelectable;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.impl.ImportNormalizer;
import org.eclipse.xtext.scoping.impl.ImportScope;
import org.eclipse.xtext.scoping.impl.ImportedNamespaceAwareLocalScopeProvider;
import org.lunifera.dsl.entity.semantic.model.LCompilerType;
import org.lunifera.dsl.entity.semantic.model.LPackage;
import org.lunifera.dsl.entity.semantic.model.LType;
import org.lunifera.dsl.entity.xtext.util.Util;

import com.google.inject.Inject;

public class EntityImportedNamespaceAwareLocalScopeProvider extends
		ImportedNamespaceAwareLocalScopeProvider {

	@Inject
	IQualifiedNameConverter qualifiedNameConverter;

	@Override
	protected List<ImportNormalizer> getImplicitImports(boolean ignoreCase) {
		List<ImportNormalizer> temp = new ArrayList<ImportNormalizer>();
		temp.add(new ImportNormalizer(QualifiedName.create("builtin", "types",
				"namespace"), true, ignoreCase));
		temp.add(new ImportNormalizer(QualifiedName.create("java.lang"), true,
				ignoreCase));
		temp.add(new ImportNormalizer(QualifiedName.create("java.util"), true,
				ignoreCase));
		temp.add(new ImportNormalizer(QualifiedName
				.create("org.lunifera.platformDataTypes"), true, ignoreCase));

		return temp;
	}
	
	@Override
	protected List<ImportNormalizer> internalGetImportedNamespaceResolvers(
			EObject context, boolean ignoreCase) {
		List<ImportNormalizer> result = new ArrayList<ImportNormalizer>();
		result.addAll(super.internalGetImportedNamespaceResolvers(context,
				ignoreCase));
		if (context instanceof LType) {
			LPackage lPackage = Util.toPackageS(((LType) context));
			if (lPackage != null) {
				QualifiedName qfn = getQualifiedNameProvider()
						.getFullyQualifiedName(lPackage);
				if (qfn != null) {
					result.add(createImportedNamespaceResolver(
							qualifiedNameConverter.toString(qfn) + ".*",
							ignoreCase));
				}
			}
		} else if (context instanceof LCompilerType) {
			LPackage lPackage = (LPackage) ((LCompilerType) context)
					.eContainer();
			if (lPackage != null) {
				QualifiedName fqn = getQualifiedNameProvider()
						.getFullyQualifiedName(lPackage);
				result.add(createImportedNamespaceResolver(
						qualifiedNameConverter.toString(fqn) + ".*", ignoreCase));
			}
		}
		return result;

	}

}
