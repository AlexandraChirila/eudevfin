/*******************************************************************************
 * Copyright (c) 2013 Development Gateway.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 *
 * Contributors:
 *    aartimon
 ******************************************************************************/

package org.devgateway.eudevfin.dim.pages.transaction;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.StringResourceModel;

/**
 * @author aartimon@developmentgateway.org
 * @since 01 November 2013
 */
public class VolumeDataTab extends Panel{

    public VolumeDataTab(String id) {
        super(id);
        addComponents();
    }

    private void addComponents() {


    }

    public static ITab newTab(Component askingComponent){
        return new AbstractTab(new StringResourceModel("tabs.volume", askingComponent, null)){
            private static final long serialVersionUID = -724508987522388955L;

            @Override
            public WebMarkupContainer getPanel(String panelId) {
                return new VolumeDataTab(panelId);
            }
        };
    }
}
