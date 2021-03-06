/*******************************************************************************
 * Copyright (c) 2014 Development Gateway.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 *******************************************************************************/
/**
 * 
 */
package org.devgateway.eudevfin.sheetexp.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.authroles.authorization.strategies.role.metadata.MetaDataRoleAuthorizationStrategy;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.devgateway.eudevfin.auth.common.domain.AuthConstants;
import org.devgateway.eudevfin.sheetexp.util.MetadataConstants;
import org.devgateway.eudevfin.ui.common.WicketNavbarComponentInitializer;
import org.devgateway.eudevfin.ui.common.components.RepairedNavbarDropDownButton;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.MenuBookmarkablePageLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.MenuDivider;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.MenuHeader;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarDropDownButton;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.button.DropDownAutoOpen;

/**
 * @author Alex
 * 
 */
public final class NavbarInitializer {

	@WicketNavbarComponentInitializer(position = Navbar.ComponentPosition.LEFT,order=4)
	public static Component newExportSpreadSheetButton(final Page page) {

		final NavbarDropDownButton exportMenu = new RepairedNavbarDropDownButton(new StringResourceModel(
				"navbar.spreadsheets.export", page, null, null)) {

			@Override
			public boolean isActive(final Component item) {
				return false;
			}

			@Override
			protected List<AbstractLink> newSubMenuButtons(final String buttonMarkupId) {
				final List<AbstractLink> list = new ArrayList<>();
				
				list.add(new MenuHeader(new StringResourceModel("navbar.export.header", this, null, null)));
				list.add(new MenuDivider());

				final PageParameters paramsCRS = new PageParameters();
				paramsCRS.set(MetadataConstants.REPORT_TYPE_PARAM, MetadataConstants.CRS_REPORT_TYPE);
				list.add((AbstractLink) new MenuBookmarkablePageLink<ExportSpreadsheetsPage>(
						ExportSpreadsheetsPage.class, paramsCRS, new StringResourceModel("navbar.reports.export.crs",
								this, null, null)).setEnabled(true));

				final PageParameters paramsFSS = new PageParameters();
				paramsFSS.set(MetadataConstants.REPORT_TYPE_PARAM, MetadataConstants.FSS_REPORT_TYPE);
				list.add((AbstractLink) new MenuBookmarkablePageLink<ExportSpreadsheetsPage>(
						ExportSpreadsheetsPage.class, paramsFSS, new StringResourceModel("navbar.reports.export.fss",
								this, null, null)).setEnabled(true));

				final PageParameters paramsIATI = new PageParameters();
				paramsIATI.set("reportType", "iati");
				list.add((AbstractLink) new MenuBookmarkablePageLink<ExportSpreadsheetsPage>(ExportSpreadsheetsPage.class, paramsIATI,
						new StringResourceModel("navbar.reports.export.iati", this, null, null)).setEnabled(true));

				return list;
			}

		};
		exportMenu.setIconType(IconType.arrowdown);
		exportMenu.add(new DropDownAutoOpen());
		MetaDataRoleAuthorizationStrategy.authorize(exportMenu, Component.RENDER, AuthConstants.Roles.ROLE_USER);
		return exportMenu;
	}
}
