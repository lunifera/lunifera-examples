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
package org.lunifera.dsl.entity.semantic.model.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.lunifera.dsl.entity.semantic.model.*;
import org.lunifera.dsl.entity.semantic.model.EntityPackage;
import org.lunifera.dsl.entity.semantic.model.LAnnotationDef;
import org.lunifera.dsl.entity.semantic.model.LAnnotationTarget;
import org.lunifera.dsl.entity.semantic.model.LBean;
import org.lunifera.dsl.entity.semantic.model.LBeanProp;
import org.lunifera.dsl.entity.semantic.model.LClass;
import org.lunifera.dsl.entity.semantic.model.LCompilerType;
import org.lunifera.dsl.entity.semantic.model.LDataType;
import org.lunifera.dsl.entity.semantic.model.LDerivedBeanProp;
import org.lunifera.dsl.entity.semantic.model.LDerivedEntityProp;
import org.lunifera.dsl.entity.semantic.model.LDerivedProperty;
import org.lunifera.dsl.entity.semantic.model.LEntity;
import org.lunifera.dsl.entity.semantic.model.LEntityCollectionProp;
import org.lunifera.dsl.entity.semantic.model.LEntityModel;
import org.lunifera.dsl.entity.semantic.model.LEntityProp;
import org.lunifera.dsl.entity.semantic.model.LEnum;
import org.lunifera.dsl.entity.semantic.model.LEnumLiteral;
import org.lunifera.dsl.entity.semantic.model.LGenSettings;
import org.lunifera.dsl.entity.semantic.model.LImport;
import org.lunifera.dsl.entity.semantic.model.LModifier;
import org.lunifera.dsl.entity.semantic.model.LMultiplicity;
import org.lunifera.dsl.entity.semantic.model.LOperation;
import org.lunifera.dsl.entity.semantic.model.LPackage;
import org.lunifera.dsl.entity.semantic.model.LPersistentProperty;
import org.lunifera.dsl.entity.semantic.model.LProperty;
import org.lunifera.dsl.entity.semantic.model.LScalarType;
import org.lunifera.dsl.entity.semantic.model.LType;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.lunifera.dsl.entity.semantic.model.EntityPackage
 * @generated
 */
public class EntityAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static EntityPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EntityAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = EntityPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EntitySwitch<Adapter> modelSwitch =
		new EntitySwitch<Adapter>() {
			@Override
			public Adapter caseLEntityModel(LEntityModel object) {
				return createLEntityModelAdapter();
			}
			@Override
			public Adapter caseLPackage(LPackage object) {
				return createLPackageAdapter();
			}
			@Override
			public Adapter caseLImport(LImport object) {
				return createLImportAdapter();
			}
			@Override
			public Adapter caseLGenSettings(LGenSettings object) {
				return createLGenSettingsAdapter();
			}
			@Override
			public Adapter caseLCompilerType(LCompilerType object) {
				return createLCompilerTypeAdapter();
			}
			@Override
			public Adapter caseLAnnotationDef(LAnnotationDef object) {
				return createLAnnotationDefAdapter();
			}
			@Override
			public Adapter caseLAnnotationTarget(LAnnotationTarget object) {
				return createLAnnotationTargetAdapter();
			}
			@Override
			public Adapter caseLType(LType object) {
				return createLTypeAdapter();
			}
			@Override
			public Adapter caseLScalarType(LScalarType object) {
				return createLScalarTypeAdapter();
			}
			@Override
			public Adapter caseLDataType(LDataType object) {
				return createLDataTypeAdapter();
			}
			@Override
			public Adapter caseLEnum(LEnum object) {
				return createLEnumAdapter();
			}
			@Override
			public Adapter caseLEnumLiteral(LEnumLiteral object) {
				return createLEnumLiteralAdapter();
			}
			@Override
			public Adapter caseLClass(LClass object) {
				return createLClassAdapter();
			}
			@Override
			public Adapter caseLBean(LBean object) {
				return createLBeanAdapter();
			}
			@Override
			public Adapter caseLEntity(LEntity object) {
				return createLEntityAdapter();
			}
			@Override
			public Adapter caseLProperty(LProperty object) {
				return createLPropertyAdapter();
			}
			@Override
			public Adapter caseLDerivedProperty(LDerivedProperty object) {
				return createLDerivedPropertyAdapter();
			}
			@Override
			public Adapter caseLPersistentProperty(LPersistentProperty object) {
				return createLPersistentPropertyAdapter();
			}
			@Override
			public Adapter caseLBeanProp(LBeanProp object) {
				return createLBeanPropAdapter();
			}
			@Override
			public Adapter caseLDerivedBeanProp(LDerivedBeanProp object) {
				return createLDerivedBeanPropAdapter();
			}
			@Override
			public Adapter caseLEntityProp(LEntityProp object) {
				return createLEntityPropAdapter();
			}
			@Override
			public Adapter caseLEntityCollectionProp(LEntityCollectionProp object) {
				return createLEntityCollectionPropAdapter();
			}
			@Override
			public Adapter caseLDerivedEntityProp(LDerivedEntityProp object) {
				return createLDerivedEntityPropAdapter();
			}
			@Override
			public Adapter caseLOperation(LOperation object) {
				return createLOperationAdapter();
			}
			@Override
			public Adapter caseLModifier(LModifier object) {
				return createLModifierAdapter();
			}
			@Override
			public Adapter caseLMultiplicity(LMultiplicity object) {
				return createLMultiplicityAdapter();
			}
			@Override
			public Adapter caseLIndex(LIndex object) {
				return createLIndexAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.lunifera.dsl.entity.semantic.model.LEntityModel <em>LEntity Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.lunifera.dsl.entity.semantic.model.LEntityModel
	 * @generated
	 */
	public Adapter createLEntityModelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.lunifera.dsl.entity.semantic.model.LPackage <em>LPackage</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.lunifera.dsl.entity.semantic.model.LPackage
	 * @generated
	 */
	public Adapter createLPackageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.lunifera.dsl.entity.semantic.model.LImport <em>LImport</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.lunifera.dsl.entity.semantic.model.LImport
	 * @generated
	 */
	public Adapter createLImportAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.lunifera.dsl.entity.semantic.model.LGenSettings <em>LGen Settings</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.lunifera.dsl.entity.semantic.model.LGenSettings
	 * @generated
	 */
	public Adapter createLGenSettingsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.lunifera.dsl.entity.semantic.model.LCompilerType <em>LCompiler Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.lunifera.dsl.entity.semantic.model.LCompilerType
	 * @generated
	 */
	public Adapter createLCompilerTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.lunifera.dsl.entity.semantic.model.LType <em>LType</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.lunifera.dsl.entity.semantic.model.LType
	 * @generated
	 */
	public Adapter createLTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.lunifera.dsl.entity.semantic.model.LScalarType <em>LScalar Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.lunifera.dsl.entity.semantic.model.LScalarType
	 * @generated
	 */
	public Adapter createLScalarTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.lunifera.dsl.entity.semantic.model.LEntity <em>LEntity</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.lunifera.dsl.entity.semantic.model.LEntity
	 * @generated
	 */
	public Adapter createLEntityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.lunifera.dsl.entity.semantic.model.LAnnotationTarget <em>LAnnotation Target</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.lunifera.dsl.entity.semantic.model.LAnnotationTarget
	 * @generated
	 */
	public Adapter createLAnnotationTargetAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.lunifera.dsl.entity.semantic.model.LProperty <em>LProperty</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.lunifera.dsl.entity.semantic.model.LProperty
	 * @generated
	 */
	public Adapter createLPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.lunifera.dsl.entity.semantic.model.LDerivedProperty <em>LDerived Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.lunifera.dsl.entity.semantic.model.LDerivedProperty
	 * @generated
	 */
	public Adapter createLDerivedPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.lunifera.dsl.entity.semantic.model.LPersistentProperty <em>LPersistent Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.lunifera.dsl.entity.semantic.model.LPersistentProperty
	 * @generated
	 */
	public Adapter createLPersistentPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.lunifera.dsl.entity.semantic.model.LBeanProp <em>LBean Prop</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.lunifera.dsl.entity.semantic.model.LBeanProp
	 * @generated
	 */
	public Adapter createLBeanPropAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.lunifera.dsl.entity.semantic.model.LDerivedBeanProp <em>LDerived Bean Prop</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.lunifera.dsl.entity.semantic.model.LDerivedBeanProp
	 * @generated
	 */
	public Adapter createLDerivedBeanPropAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.lunifera.dsl.entity.semantic.model.LEntityProp <em>LEntity Prop</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.lunifera.dsl.entity.semantic.model.LEntityProp
	 * @generated
	 */
	public Adapter createLEntityPropAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.lunifera.dsl.entity.semantic.model.LEntityCollectionProp <em>LEntity Collection Prop</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.lunifera.dsl.entity.semantic.model.LEntityCollectionProp
	 * @generated
	 */
	public Adapter createLEntityCollectionPropAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.lunifera.dsl.entity.semantic.model.LDerivedEntityProp <em>LDerived Entity Prop</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.lunifera.dsl.entity.semantic.model.LDerivedEntityProp
	 * @generated
	 */
	public Adapter createLDerivedEntityPropAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.lunifera.dsl.entity.semantic.model.LOperation <em>LOperation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.lunifera.dsl.entity.semantic.model.LOperation
	 * @generated
	 */
	public Adapter createLOperationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.lunifera.dsl.entity.semantic.model.LModifier <em>LModifier</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.lunifera.dsl.entity.semantic.model.LModifier
	 * @generated
	 */
	public Adapter createLModifierAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.lunifera.dsl.entity.semantic.model.LEnum <em>LEnum</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.lunifera.dsl.entity.semantic.model.LEnum
	 * @generated
	 */
	public Adapter createLEnumAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.lunifera.dsl.entity.semantic.model.LEnumLiteral <em>LEnum Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.lunifera.dsl.entity.semantic.model.LEnumLiteral
	 * @generated
	 */
	public Adapter createLEnumLiteralAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.lunifera.dsl.entity.semantic.model.LClass <em>LClass</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.lunifera.dsl.entity.semantic.model.LClass
	 * @generated
	 */
	public Adapter createLClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.lunifera.dsl.entity.semantic.model.LBean <em>LBean</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.lunifera.dsl.entity.semantic.model.LBean
	 * @generated
	 */
	public Adapter createLBeanAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.lunifera.dsl.entity.semantic.model.LAnnotationDef <em>LAnnotation Def</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.lunifera.dsl.entity.semantic.model.LAnnotationDef
	 * @generated
	 */
	public Adapter createLAnnotationDefAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.lunifera.dsl.entity.semantic.model.LMultiplicity <em>LMultiplicity</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.lunifera.dsl.entity.semantic.model.LMultiplicity
	 * @generated
	 */
	public Adapter createLMultiplicityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.lunifera.dsl.entity.semantic.model.LIndex <em>LIndex</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.lunifera.dsl.entity.semantic.model.LIndex
	 * @generated
	 */
	public Adapter createLIndexAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.lunifera.dsl.entity.semantic.model.LDataType <em>LData Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.lunifera.dsl.entity.semantic.model.LDataType
	 * @generated
	 */
	public Adapter createLDataTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //EntityAdapterFactory
