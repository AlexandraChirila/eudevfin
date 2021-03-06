/*******************************************************************************
 * Copyright (c) 2014 Development Gateway.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 *******************************************************************************/

package org.devgateway.eudevfin.mcm.pages;

import org.apache.log4j.Logger;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.devgateway.eudevfin.auth.common.domain.AuthConstants;
import org.devgateway.eudevfin.auth.common.service.PersistedUserService;
import org.devgateway.eudevfin.mcm.components.PersistedUserTableListPanel;
import org.devgateway.eudevfin.mcm.util.PersistedUserListGenerator;
import org.devgateway.eudevfin.ui.common.components.BootstrapSubmitButton;
import org.devgateway.eudevfin.ui.common.pages.ListPage;
import org.wicketstuff.annotation.mount.MountPath;

/**
 * @author mihai
 * @since 19.12.2013
 */
@AuthorizeInstantiation(AuthConstants.Roles.ROLE_SUPERVISOR)
@MountPath(value = "/users")
public class ListPersistedUsersPage extends ListPage {

	private static final long serialVersionUID = -8637428983397263676L;

	@SpringBean
	private PersistedUserService userService;

	private static final Logger logger = Logger.getLogger(ListPersistedUsersPage.class);

	@SuppressWarnings("unchecked")
	public ListPersistedUsersPage(final PageParameters parameters) {

		PersistedUserTableListPanel persistedUserTableListPanel = new PersistedUserTableListPanel(
				WICKETID_LIST_PANEL, new PersistedUserListGenerator(userService, ""));
		add(persistedUserTableListPanel);
		
		Form form = new Form("form");
		BootstrapSubmitButton submitButton = new BootstrapSubmitButton("addNewUser", new StringResourceModel("button.addNewUser", this, null, null)) {			
			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
		
			}

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				setResponsePage(EditPersistedUserPageElevated.class, new PageParameters().add(
						EditPersistedUserPageElevated.PARAM_USER_ID,
						EditPersistedUserPageElevated.PARAM_USER_ID_VALUE_NEW));
			}
			
		};
		submitButton.setDefaultFormProcessing(false);
		form.add(submitButton);
		add(form);
	}

}
