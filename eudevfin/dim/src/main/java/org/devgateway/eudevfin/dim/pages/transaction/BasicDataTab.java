/*
 * Copyright (c) 2013 Development Gateway.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 */

package org.devgateway.eudevfin.dim.pages.transaction;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.StringResourceModel;
import org.devgateway.eudevfin.dim.core.RWComponentPropertyModel;
import org.devgateway.eudevfin.dim.core.SB;
import org.devgateway.eudevfin.dim.core.components.DropDownField;
import org.devgateway.eudevfin.dim.core.components.TextAreaInputField;
import org.devgateway.eudevfin.dim.core.components.TextInputField;
import org.devgateway.eudevfin.dim.core.components.tabs.AbstractTabWithKey;
import org.devgateway.eudevfin.dim.core.components.tabs.ITabWithKey;
import org.devgateway.eudevfin.dim.core.permissions.PermissionAwareComponent;

/**
 * @author aartimon@developmentgateway.org
 * @since 01 November 2013
 */
public class BasicDataTab extends Panel implements PermissionAwareComponent {

    public static final String KEY = "tabs.basic";

    private BasicDataTab(String id) {
        super(id);
        addComponents();
    }

    private void addComponents() {
        DropDownField<String> recipient = new DropDownField<>("7recipient", new RWComponentPropertyModel<String>("recipient"),
                SB.countryProvider);
        add(recipient);

        DropDownField<String> cpa = new DropDownField<>("7bCPA", new RWComponentPropertyModel<String>("CPA"),
                SB.yesNoProvider);
        add(cpa);

        TextInputField<String> channelDelivery = new TextInputField<>("8channelDelivery", new RWComponentPropertyModel<String>("channelDelivery"));
        add(channelDelivery);

        DropDownField<String> channelCode = new DropDownField<>("9channelCode", new RWComponentPropertyModel<String>("channelCode"),
                SB.countryProvider);
        add(channelCode);

        DropDownField<String> bilateralMultilateral = new DropDownField<>("10bilateralMultilateral", new RWComponentPropertyModel<String>("bilateralMultilateral"),
                SB.countryProvider);
        add(bilateralMultilateral);

        DropDownField<String> typeOfFlow = new DropDownField<>("11typeOfFlow", new RWComponentPropertyModel<String>("typeOfFlow"),
                SB.countryProvider);
        typeOfFlow.required();
        add(typeOfFlow);

        DropDownField<String> typeOfFinance = new DropDownField<>("12typeOfFinance", new RWComponentPropertyModel<String>("typeOfFinance"),
                SB.countryProvider);
        typeOfFinance.required();
        add(typeOfFinance);

        DropDownField<String> typeOfAid = new DropDownField<>("13typeOfAid", new RWComponentPropertyModel<String>("typeOfAid"),
                SB.countryProvider);
        add(typeOfAid);

        TextAreaInputField activityProjectTitle = new TextAreaInputField("14activityProjectTitle", new RWComponentPropertyModel<String>("activityProjectTitle"));
        add(activityProjectTitle);

        DropDownField<String> sectorPurposeCode = new DropDownField<>("15sectorPurposeCode", new RWComponentPropertyModel<String>("sectorPurposeCode"),
                SB.countryProvider);
        add(sectorPurposeCode);
    }

    public static ITabWithKey newTab(Component askingComponent) {
        return new AbstractTabWithKey(new StringResourceModel(KEY, askingComponent, null), KEY) {
            private static final long serialVersionUID = -724508987522388955L;

            @Override
            public WebMarkupContainer getPanel(String panelId) {
                return new BasicDataTab(panelId);
            }
        };
    }

    @Override
    public String getPermissionKey() {
        return KEY;
    }

    @Override
    public void enableRequired() {
    }
}
