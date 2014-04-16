package org.devgateway.eudevfin.reports.ui.pages;

import org.apache.log4j.Logger;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.devgateway.eudevfin.auth.common.domain.AuthConstants;
import org.devgateway.eudevfin.ui.common.components.BootstrapSubmitButton;
import org.wicketstuff.annotation.mount.MountPath;

/**
 * @author idobre
 * @since 4/3/14
 */

@AuthorizeInstantiation(AuthConstants.Roles.ROLE_USER)
@MountPath(value = "/reportscountrysectorfilter")
public class ReportsCountrySectorFilter extends CustomReportsPage {
    private static final Logger logger = Logger.getLogger(ReportsCountrySectorFilter.class);

    public ReportsCountrySectorFilter () {
        Label title = new Label("title", new StringResourceModel("reportsCountrySector", this, null, null));
        add(title);

        nationalInstitution.setVisibilityAllowed(Boolean.FALSE);
        multilateralAgency.setVisibilityAllowed(Boolean.FALSE);
        typeOfFlowBiMulti.setVisibilityAllowed(Boolean.FALSE);
        typeOfAid.setVisibilityAllowed(Boolean.FALSE);
        typeOfExpenditure.setVisibilityAllowed(Boolean.FALSE);
        valueOfActivity.setVisibilityAllowed(Boolean.FALSE);
        startingYear.setVisibilityAllowed(Boolean.FALSE);
        completitionYear.setVisibilityAllowed(Boolean.FALSE);
        humanitarianAid.setVisibilityAllowed(Boolean.FALSE);
        showRelatedBudgetCodes.setVisibilityAllowed(Boolean.FALSE);
    }

    @Override
    protected void addSubmitButton() {
        form.add(new BootstrapSubmitButton("submit", new StringResourceModel("button.submit", this, null, null)) {
            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                super.onError(target, form);
                target.add(feedbackPanel);
            }

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                CustomReportsModel customReportsModel = (CustomReportsModel)form.getModelObject();
//                logger.info(customReportsModel.getRecipient().getName());

                PageParameters pageParameters = new PageParameters();
                pageParameters.add("msg", "this is parameter value");
                setResponsePage(ReportsCountrySectorDashboards.class, pageParameters);
            }
        });
    }
}
