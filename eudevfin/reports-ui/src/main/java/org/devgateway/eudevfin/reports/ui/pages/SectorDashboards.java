package org.devgateway.eudevfin.reports.ui.pages;

import org.apache.log4j.Logger;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValue;
import org.devgateway.eudevfin.auth.common.domain.AuthConstants;
import org.devgateway.eudevfin.financial.service.FinancialTransactionService;
import org.devgateway.eudevfin.reports.core.service.QueryService;
import org.devgateway.eudevfin.reports.ui.components.BarChartNVD3;
import org.devgateway.eudevfin.reports.ui.components.PieChartNVD3;
import org.devgateway.eudevfin.reports.ui.components.Table;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * @author idobre
 * @since 6/2/14
 */

@MountPath(value = "/sectordashboards")
@AuthorizeInstantiation(AuthConstants.Roles.ROLE_USER)
public class SectorDashboards extends ReportsDashboards {
    private static final Logger logger = Logger.getLogger(SectorDashboards.class);

    private String countryCurrency = "$";

    private int tableYear;
    // variables that holds the parameters received from filter
    private String currencyParam;
    private String sectorParam;

    @SpringBean
    protected QueryService CdaService;

    @SpringBean
    private FinancialTransactionService financialTransactionService;

    public SectorDashboards(final PageParameters parameters) {
        // get the reporting year
        tableYear = Calendar.getInstance().get(Calendar.YEAR) - 1;

        // process the parameters received from the filters
        if(!parameters.get(ReportsConstants.ISNATIONALCURRENCY_PARAM).equals(StringValue.valueOf((String) null))) {
            currencyParam = parameters.get(ReportsConstants.ISNATIONALCURRENCY_PARAM).toString();
            if (currencyParam.equals("true")) {
                countryCurrency = ReportsDashboardsUtils.getCurrency();
            }
        } else {
            countryCurrency = ReportsDashboardsUtils.getCurrency();
        }
        if(!parameters.get(ReportsConstants.SECTOR_PARAM).equals(StringValue.valueOf((String) null))) {
            sectorParam = parameters.get(ReportsConstants.SECTOR_PARAM).toString();
        }

        String subTitle = new StringResourceModel("page.subtitle", this, null, null).getObject();
        Label country = new Label("sector", (sectorParam != null ? subTitle + " - " + sectorParam : ""));
        add(country);

        addComponents();
    }

    private void addComponents() {
        addTable();
        addPieChart();
        addBarChart();
        addTableList();
    }

    protected void addTable () {
        Label title = new Label("sectorTableTitle", "Net Disbursement by Country - " + tableYear + " - " + countryCurrency + " - full amount");
        add(title);

        Table table = new Table(CdaService, "sectorTable", "sectorTableRows", "sectorDashboardsTable") {
            @Override
            public ListView<String[]> getTableRows () {
                super.getTableRows();

                this.rows = new ArrayList<>();
                this.result = this.runQuery();

                return ReportsDashboardsUtils.processTableRowsWithTotalOneYear(this.rows, this.result, this.rowId, true,
                        currencyParam, ReportsConstants.isCountry, false);
            }
        };

        // add MDX queries parameters
        table.setParam("paramFIRST_YEAR", Integer.toString(tableYear));
        table.setParam("paramMainSector", (sectorParam != null ? sectorParam : ""));
        if (currencyParam != null) {
            if (currencyParam.equals("true")) {
                table.setParam("paramcurrency", ReportsConstants.MDX_NAT_CURRENCY);
            }
        } else {
            table.setParam("paramcurrency", ReportsConstants.MDX_NAT_CURRENCY);
        }

        Label firstYear = new Label("firstYear", tableYear);
        table.getTable().add(firstYear);

        add(table.getTable());
        table.addTableRows();
    }

    private void addPieChart() {
        Label title = new Label("sectorPieChartTitle", "Net Disbursement by subsector - " + tableYear + " - " + countryCurrency + " - full amount");
        add(title);

        PieChartNVD3 pieChartNVD3 = new PieChartNVD3(CdaService, "sectorPieChart", "sectorDashboardsPieChart");

        // add MDX queries parameters
        pieChartNVD3.setParam("paramFIRST_YEAR", Integer.toString(tableYear));
        pieChartNVD3.setParam("paramMainSector", (sectorParam != null ? sectorParam : ""));
        if (currencyParam != null) {
            if (currencyParam.equals("true")) {
                pieChartNVD3.setParam("paramcurrency", ReportsConstants.MDX_NAT_CURRENCY);
            }
        } else {
            pieChartNVD3.setParam("paramcurrency", ReportsConstants.MDX_NAT_CURRENCY);
        }

        add(pieChartNVD3);
    }

    private void addBarChart() {
        Label title = new Label("sectorBarChartTitle", "Net Disbursement by Country - " + tableYear + " - " + countryCurrency + " - full amount");
        add(title);

        BarChartNVD3 barChartNVD3 = new BarChartNVD3(CdaService, "sectorBarChart", "sectorDashboardsBarChart");

        // add MDX queries parameters
        barChartNVD3.setParam("paramFIRST_YEAR", Integer.toString(tableYear));
        barChartNVD3.setParam("paramMainSector", (sectorParam != null ? sectorParam : ""));
        if (currencyParam != null) {
            if (currencyParam.equals("true")) {
                barChartNVD3.setParam("paramcurrency", ReportsConstants.MDX_NAT_CURRENCY);
            }
        } else {
            barChartNVD3.setParam("paramcurrency", ReportsConstants.MDX_NAT_CURRENCY);
        }

        barChartNVD3.setNumberOfSeries(1);
        barChartNVD3.setSeries1("Year " + (tableYear));

        add(barChartNVD3);
    }

    protected void addTableList () {
        Label title = new Label("sectorTableListTitle", "Net Disbursement - " + tableYear + " - " + countryCurrency + " - full amount");
        add(title);

        Table table = new Table(CdaService, "sectorTableList", "sectorTableListRows", "sectorDashboardsTableList") {
            @Override
            public ListView<String[]> getTableRows () {
                super.getTableRows();

                this.rows = new ArrayList<>();
                this.result = this.runQuery();

                return ReportsDashboardsUtils.processTableRowsTransactions(financialTransactionService,
                        this.rows, this.result, this.rowId, currencyParam, ReportsConstants.isSector);
            }
        };

        // add MDX queries parameters
        table.setParam("paramFIRST_YEAR", Integer.toString(tableYear));
        table.setParam("paramMainSector", (sectorParam != null ? sectorParam : ""));
        if (currencyParam != null) {
            if (currencyParam.equals("true")) {
                table.setParam("paramcurrency", ReportsConstants.MDX_NAT_CURRENCY);
                table.setParam("paramcurrencyDisbursement", ReportsConstants.MDX_NAT_EXTENDED_CURRENCY);
            }
        } else {
            table.setParam("paramcurrency", ReportsConstants.MDX_NAT_CURRENCY);
            table.setParam("paramcurrencyDisbursement", ReportsConstants.MDX_NAT_EXTENDED_CURRENCY);
        }

        Label firstYear = new Label("firstYear", tableYear + " Disbursement");
        table.getTable().add(firstYear);

        add(table.getTable());
        table.addTableRows();
    }
}