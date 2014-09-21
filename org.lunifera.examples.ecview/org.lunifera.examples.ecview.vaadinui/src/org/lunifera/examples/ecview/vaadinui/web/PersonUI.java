/**
 * Copyright 2013 Lunifera GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.lunifera.examples.ecview.vaadinui.web;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.lunifera.ecview.core.common.context.ContextException;
import org.lunifera.ecview.core.common.context.IViewContext;
import org.lunifera.ecview.core.common.model.core.YView;
import org.lunifera.ecview.xtext.builder.participant.IECViewAddonsMetadataService;
import org.lunifera.examples.ecview.Address;
import org.lunifera.examples.ecview.ButtonCallback;
import org.lunifera.examples.ecview.CountryService;
import org.lunifera.examples.ecview.Person;
import org.lunifera.examples.ecview.vaadinui.Activator;
import org.lunifera.runtime.web.ecview.presentation.vaadin.VaadinRenderer;
import org.osgi.util.tracker.ServiceTracker;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.Reindeer;

@SuppressWarnings("serial")
@Theme(Reindeer.THEME_NAME)
@Push
public class PersonUI extends UI implements PropertyChangeListener {

	private IViewContext viewContext;
	private ButtonCallback buttonCallback = new ButtonCallback();
	private CountryService countryService = createCountryService();
	private List<Person> persons = createPersons();
	private Person currentPerson;

	@Override
	protected void init(VaadinRequest request) {
		currentPerson = persons.get(0);

		registerButtonCallbacks();

		HorizontalLayout layout = new HorizontalLayout();
		setContent(layout);
		layout.setStyleName(Reindeer.LAYOUT_BLUE);
		layout.setSizeFull();

		YView yView = findViewModel("org.lunifera.examples.ecview.uimodels.PersonView");
		if (yView == null) {
			Notification.show("PersonView could not be found!",
					Type.ERROR_MESSAGE);
			return;
		}

		// render the Vaadin UI
		VaadinRenderer renderer = new VaadinRenderer();
		try {
			viewContext = renderer.render(layout, yView, null);
			viewContext.setBean("countryService", countryService);
			viewContext.setBean("ds", currentPerson);
			viewContext.setBean("buttonCallback", buttonCallback);

		} catch (ContextException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Register callbacks for buttons.
	 */
	protected void registerButtonCallbacks() {
		buttonCallback.addPropertyChangeListener("previousPerson", this);
		buttonCallback.addPropertyChangeListener("nextPerson", this);
		buttonCallback.setHasNext(true);
		buttonCallback.setHasPrevious(false);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		int index = persons.indexOf(currentPerson);
		if (evt.getPropertyName() == "previousPerson") {
			if (index > 0) {
				currentPerson = persons.get(--index);
				viewContext.setBean("ds", currentPerson);
			}
			if (index == 0) {
				buttonCallback.setHasPrevious(false);
				buttonCallback.setHasNext(true);
			} else {
				buttonCallback.setHasPrevious(true);
			}
			
			if(index < 2){
				buttonCallback.setHasNext(true);
			}else{
				buttonCallback.setHasNext(false);
			}
		} else if (evt.getPropertyName() == "nextPerson") {
			if (index < 2) {
				currentPerson = persons.get(++index);
				viewContext.setBean("ds", currentPerson);
			}
			if (index == 2) {
				buttonCallback.setHasNext(false);
			} else {
				buttonCallback.setHasNext(true);
			}
			
			if(index > 0){
				buttonCallback.setHasPrevious(true);
			}else{
				buttonCallback.setHasPrevious(false);
			}
		}
	}

	/**
	 * Later this will be replaced by a countryService.
	 * 
	 * @return
	 */
	protected CountryService createCountryService() {
		CountryService service = new CountryService();
		service.initService();
		return service;
	}

	/**
	 * Creates a list of persons.
	 * 
	 * @return
	 */
	protected List<Person> createPersons() {
		List<Person> persons = new ArrayList<Person>();
		persons.add(createPerson1());
		persons.add(createPerson2());
		persons.add(createPerson3());
		return persons;
	}

	/**
	 * Create a person record. You may also use a database.
	 * 
	 * @return
	 */
	protected Person createPerson1() {

		Address deliveryAddress = new Address();
		deliveryAddress.setCity("Vienna");
		deliveryAddress.setStreet("Stephansplace 1");
		deliveryAddress.setPostalcode("1010");
		deliveryAddress.setCountry(countryService.getCountries().get(0));

		Address companyAddress = new Address();
		deliveryAddress.setCity("Vienna");
		deliveryAddress.setStreet("Mooslackengasse");
		deliveryAddress.setPostalcode("1220");
		deliveryAddress.setCountry(countryService.getCountries().get(0));

		Person person = new Person();
		person.setFirstname("Florian");
		person.setLastname("Pirchner");
		person.setHomeAddress(deliveryAddress);
		person.setCompanyAddress(companyAddress);

		return person;
	}

	/**
	 * Create a person record. You may also use a database.
	 * 
	 * @return
	 */
	protected Person createPerson2() {

		Address deliveryAddress = new Address();
		deliveryAddress.setCity("Munich");
		deliveryAddress.setStreet("Somewhere there");
		deliveryAddress.setPostalcode("89001");
		deliveryAddress.setCountry(countryService.getCountries().get(1));

		Address companyAddress = new Address();
		deliveryAddress.setCity("Berlin");
		deliveryAddress.setStreet("Mainstreet");
		deliveryAddress.setPostalcode("11520");
		deliveryAddress.setCountry(countryService.getCountries().get(1));

		Person person = new Person();
		person.setFirstname("Klemens");
		person.setLastname("Edler");
		person.setHomeAddress(deliveryAddress);
		person.setCompanyAddress(companyAddress);

		return person;
	}

	/**
	 * Create a person record. You may also use a database.
	 * 
	 * @return
	 */
	protected Person createPerson3() {

		Address deliveryAddress = new Address();
		deliveryAddress.setCity("Fortelezza");
		deliveryAddress.setStreet("Street at the beach");
		deliveryAddress.setPostalcode("999876");
		deliveryAddress.setCountry(countryService.getCountries().get(2));

		Address companyAddress = new Address();
		deliveryAddress.setCity("Fortelezza");
		deliveryAddress.setStreet("Near the woods");
		deliveryAddress.setPostalcode("999874");
		deliveryAddress.setCountry(countryService.getCountries().get(2));

		Person person = new Person();
		person.setFirstname("Cristiano");
		person.setLastname("Gaviáo");
		person.setHomeAddress(deliveryAddress);
		person.setCompanyAddress(companyAddress);

		return person;
	}

	/**
	 * Tries to find the view model using the ecview addons service.
	 * 
	 * @param uiName
	 * @return
	 */
	protected YView findViewModel(String uiName) {
		ServiceTracker<IECViewAddonsMetadataService, IECViewAddonsMetadataService> tracker = new ServiceTracker<IECViewAddonsMetadataService, IECViewAddonsMetadataService>(
				Activator.getContext(), IECViewAddonsMetadataService.class,
				null);
		tracker.open();
		try {
			IECViewAddonsMetadataService uiService = tracker
					.waitForService(5000);
			return uiService.getViewMetadata(uiName);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			tracker.close();
		}
		return null;
	}

}
