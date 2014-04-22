package org.devgateway.eudevfin.reports.ui.pages;

import org.apache.log4j.Logger;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.devgateway.eudevfin.auth.common.domain.AuthConstants;
import org.devgateway.eudevfin.reports.core.service.QueryService;
import org.devgateway.eudevfin.ui.common.pages.HeaderFooter;
import org.wicketstuff.annotation.mount.MountPath;

/**
 * @author idobre
 * @since 4/16/14
 */
@MountPath(value = "/reportsimplementationstatusdashboards")
@AuthorizeInstantiation(AuthConstants.Roles.ROLE_USER)
public class ReportsImplementationStatusDashboards extends HeaderFooter {
    private static final Logger logger = Logger.getLogger(ReportsImplementationStatusDashboards.class);

    @SpringBean
    QueryService CdaService;

    public ReportsImplementationStatusDashboards(final PageParameters parameters) {
        addComponents();
    }

    private void addComponents() {
        Label title = new Label("title", new StringResourceModel("reportsimplementationstatusdashboards.title", this, null, null));
        add(title);
    }
}