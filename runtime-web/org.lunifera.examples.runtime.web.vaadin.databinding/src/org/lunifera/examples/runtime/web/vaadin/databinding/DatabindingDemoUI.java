/*******************************************************************************
 * Copyright (c) 2012 by committers of lunifera.org
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Florian Pirchner - initial API and implementation
 *    
 *******************************************************************************/
package org.lunifera.examples.runtime.web.vaadin.databinding;

import org.eclipse.core.databinding.DataBindingContext;
import org.lunifera.runtime.web.vaadin.databinding.IVaadinObservableValue;
import org.lunifera.runtime.web.vaadin.databinding.VaadinObservables;
import org.lunifera.web.vaadin.common.OSGiUI;

import com.vaadin.annotations.Theme;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.Reindeer;

/**
 * Specify the class name after the factory name.
 */
@Theme(Reindeer.THEME_NAME)
public class DatabindingDemoUI extends OSGiUI {

	private static final long serialVersionUID = 1L;
	private ObjectProperty<Object> property1;
	private ObjectProperty<Object> property2;
	private DataBindingContext dbc;

	@Override
	public void init(VaadinRequest request) {
		VaadinObservables.getRealm(getUI());
		dbc = new DataBindingContext();

		GridLayout layout = new GridLayout(4, 3);
		layout.setStyleName(Reindeer.LAYOUT_BLUE);
		layout.setWidth("100%");
		layout.setMargin(true);
		setContent(layout);

		row1(layout);
		row2(layout);
		row3(layout);

	}

	private void row1(GridLayout layout) {
		TextField textField1 = new TextField("field1");
		textField1.setImmediate(true);
		textField1.setBuffered(false);
		property1 = new ObjectProperty<Object>(null, Object.class);
		textField1.setPropertyDataSource(property1);

		TextField textField2 = new TextField("field2");
		textField2.setImmediate(true);
		textField2.setBuffered(false);
		property2 = new ObjectProperty<Object>(null, Object.class);
		textField2.setPropertyDataSource(property2);

		layout.addComponent(textField1, 0, 0);
		layout.addComponent(textField2, 1, 0);

		IVaadinObservableValue observable1 = VaadinObservables
				.observeValue(textField1);
		IVaadinObservableValue observable2 = VaadinObservables
				.observeValue(textField2);

		dbc.bindValue(observable2, observable1);
	}

	private void row2(GridLayout layout) {
		CheckBox readonly = new CheckBox("readonly");
		readonly.setImmediate(true);
		readonly.setBuffered(false);

		TextField textField = new TextField("field2");
		textField.setImmediate(true);
		textField.setBuffered(false);

		layout.addComponent(readonly, 0, 1);
		layout.addComponent(textField, 1, 1);

		IVaadinObservableValue checkboxObservable = VaadinObservables
				.observeValue(readonly);
		IVaadinObservableValue textFieldObservable = VaadinObservables
				.observeReadonly(textField);

		dbc.bindValue(textFieldObservable, checkboxObservable);
	}

	private void row3(GridLayout layout) {
		CheckBox required = new CheckBox("required");
		required.setImmediate(true);
		required.setBuffered(false);

		TextField requiredError = new TextField("requiredError");
		requiredError.setImmediate(true);
		requiredError.setBuffered(false);

		TextField input = new TextField("input");
		input.setImmediate(true);
		input.setBuffered(false);

		layout.addComponent(required, 0, 2);
		layout.addComponent(requiredError, 1, 2);
		layout.addComponent(input, 2, 2);

		IVaadinObservableValue value_requiredObservable = VaadinObservables
				.observeValue(required);
		IVaadinObservableValue value_errorString = VaadinObservables
				.observeValue(requiredError);
		IVaadinObservableValue readonly_errorString = VaadinObservables
				.observeReadonly(requiredError);

		IVaadinObservableValue required_Input = VaadinObservables
				.observeRequired(input);
		IVaadinObservableValue errorMessage_Input = VaadinObservables
				.observeRequiredError(input);

		dbc.bindValue(value_requiredObservable, required_Input);
		dbc.bindValue(value_requiredObservable, readonly_errorString);
		dbc.bindValue(value_errorString, errorMessage_Input);
	}
}
