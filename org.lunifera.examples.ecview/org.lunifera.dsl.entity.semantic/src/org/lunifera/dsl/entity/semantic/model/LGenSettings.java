/**
 * Copyright (c) 2011 - 2012, Florian Pirchner - lunifera.org
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Sources generated by Xtext  
 * 
 * Contributors:
 *  		Hans Georg Glöckler - Initial implementation
 *  		Florian Pirchner - Initial implementation
 */
package org.lunifera.dsl.entity.semantic.model;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>LGen Settings</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.lunifera.dsl.entity.semantic.model.LGenSettings#isNoSource <em>No Source</em>}</li>
 *   <li>{@link org.lunifera.dsl.entity.semantic.model.LGenSettings#getCompilerType <em>Compiler Type</em>}</li>
 *   <li>{@link org.lunifera.dsl.entity.semantic.model.LGenSettings#isLifecycle <em>Lifecycle</em>}</li>
 *   <li>{@link org.lunifera.dsl.entity.semantic.model.LGenSettings#isPropertyChangeSupport <em>Property Change Support</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.lunifera.dsl.entity.semantic.model.EntityPackage#getLGenSettings()
 * @model
 * @generated
 */
public interface LGenSettings extends EObject {
	/**
	 * Returns the value of the '<em><b>No Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>No Source</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>No Source</em>' attribute.
	 * @see #setNoSource(boolean)
	 * @see org.lunifera.dsl.entity.semantic.model.EntityPackage#getLGenSettings_NoSource()
	 * @model
	 * @generated
	 */
	boolean isNoSource();

	/**
	 * Sets the value of the '{@link org.lunifera.dsl.entity.semantic.model.LGenSettings#isNoSource <em>No Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>No Source</em>' attribute.
	 * @see #isNoSource()
	 * @generated
	 */
	void setNoSource(boolean value);

	/**
	 * Returns the value of the '<em><b>Compiler Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Compiler Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Compiler Type</em>' reference.
	 * @see #setCompilerType(LCompilerType)
	 * @see org.lunifera.dsl.entity.semantic.model.EntityPackage#getLGenSettings_CompilerType()
	 * @model
	 * @generated
	 */
	LCompilerType getCompilerType();

	/**
	 * Sets the value of the '{@link org.lunifera.dsl.entity.semantic.model.LGenSettings#getCompilerType <em>Compiler Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Compiler Type</em>' reference.
	 * @see #getCompilerType()
	 * @generated
	 */
	void setCompilerType(LCompilerType value);

	/**
	 * Returns the value of the '<em><b>Lifecycle</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lifecycle</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lifecycle</em>' attribute.
	 * @see #setLifecycle(boolean)
	 * @see org.lunifera.dsl.entity.semantic.model.EntityPackage#getLGenSettings_Lifecycle()
	 * @model
	 * @generated
	 */
	boolean isLifecycle();

	/**
	 * Sets the value of the '{@link org.lunifera.dsl.entity.semantic.model.LGenSettings#isLifecycle <em>Lifecycle</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lifecycle</em>' attribute.
	 * @see #isLifecycle()
	 * @generated
	 */
	void setLifecycle(boolean value);

	/**
	 * Returns the value of the '<em><b>Property Change Support</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property Change Support</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Property Change Support</em>' attribute.
	 * @see #setPropertyChangeSupport(boolean)
	 * @see org.lunifera.dsl.entity.semantic.model.EntityPackage#getLGenSettings_PropertyChangeSupport()
	 * @model
	 * @generated
	 */
	boolean isPropertyChangeSupport();

	/**
	 * Sets the value of the '{@link org.lunifera.dsl.entity.semantic.model.LGenSettings#isPropertyChangeSupport <em>Property Change Support</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Property Change Support</em>' attribute.
	 * @see #isPropertyChangeSupport()
	 * @generated
	 */
	void setPropertyChangeSupport(boolean value);

} // LGenSettings
