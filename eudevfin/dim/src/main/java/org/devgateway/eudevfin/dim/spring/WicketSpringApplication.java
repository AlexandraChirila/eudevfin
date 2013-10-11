/*******************************************************************************
 * Copyright (c) 2013 Development Gateway.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *    mpostelnicu
 ******************************************************************************/
package org.devgateway.eudevfin.dim.spring;

 import java.util.Calendar;

import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.devgateway.eudevfin.dim.ItemsPage;
import org.devgateway.eudevfin.dim.LoginPage;
import org.devgateway.eudevfin.domain.Person;
import org.devgateway.eudevfin.persistence.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;

public class WicketSpringApplication extends AuthenticatedWebApplication {

	@Autowired
	private PersonService personService;
	
	@Override
	protected void init() {
		super.init();

		getComponentInstantiationListeners().add(
				new SpringComponentInjector(this));
		
		//test populating one person bean upon startup
		final Person person = new Person();
		Calendar createdDateTime = Calendar.getInstance();
		createdDateTime.set(1980, 0, 1);
		person.setCreatedDateTime(createdDateTime.getTime());
		person.setName("bubu");
		
		personService.updatePerson(person);
	}

	@Override
	public Class getHomePage() {
		return ItemsPage.class;
	}

	@Override
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
		return SpringWicketWebSession.class;
	}

	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return LoginPage.class;
	}
}
