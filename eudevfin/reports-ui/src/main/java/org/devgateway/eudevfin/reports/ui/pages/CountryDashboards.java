package org.devgateway.eudevfin.reports.ui.pages;

import com.googlecode.wickedcharts.highcharts.options.Options;
import com.googlecode.wickedcharts.highcharts.options.SeriesType;
import com.googlecode.wickedcharts.highcharts.options.series.Point;
import com.googlecode.wickedcharts.highcharts.options.series.PointSeries;
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
import org.devgateway.eudevfin.reports.ui.components.PieChart;
import org.devgateway.eudevfin.reports.ui.components.PieChartNVD3;
import org.devgateway.eudevfin.reports.ui.components.Table;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author idobre
 * @since 6/2/14
 */
@MountPath(value = "/countrydashboards")
@AuthorizeInstantiation(AuthConstants.Roles.ROLE_USER)
public class CountryDashboards extends ReportsDashboards {
    private static final Logger logger = Logger.getLogger(CountryDashboards.class);

    private String countryCurrency = "$";

    private int tableYear;
    // variables that holds the parameters received from filter
    private String currencyParam;
    private String recipientParam;

    @SpringBean
    protected QueryService CdaService;

    @SpringBean
    private FinancialTransactionService financialTransactionService;

    public CountryDashboards(final PageParameters parameters) {
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
        if(!parameters.get(ReportsConstants.RECIPIENT_PARAM).equals(StringValue.valueOf((String) null))) {
            recipientParam = parameters.get(ReportsConstants.RECIPIENT_PARAM).toString();
        }

        String subTitle = new StringResourceModel("page.subtitle", this, null, null).getObject();
        Label country = new Label("country", (recipientParam != null ? subTitle + " - " + recipientParam : ""));
        add(country);

        addComponents();
    }

    private void addComponents() {
        addTable();
        addChart();
        addTableList();
    }

    protected void addTable () {
        Label title = new Label("countryTableTitle", "Net Disbursement by Sector - " + tableYear + " - " + countryCurrency + " - full amount");
        add(title);

        Table table = new Table(CdaService, "countryTable", "countryTableRows", "countryDashboardsTable") {
            @Override
            public ListView<String[]> getTableRows () {
                super.getTableRows();

                this.rows = new ArrayList<>();
                this.result = this.runQuery();

                return ReportsDashboardsUtils.processTableRowsOneYear(this.rows, this.result, this.rowId,
                        currencyParam, ReportsConstants.isSector);
            }
        };

        // add MDX queries parameters
        table.setParam("paramFIRST_YEAR", Integer.toString(tableYear));
        table.setParam("paramCountry", (recipientParam != null ? recipientParam : ""));
        if (currencyParam != null) {
            if (currencyParam.equals("true")) {
                table.setParam("paramcurrency", ReportsConstants.MDX_NAT_CURRENCY);
            }
        } else {
            table.setParam("paramcurrency", ReportsConstants.MDX_NAT_CURRENCY);
        }

        table.setdisableStripeClasses(Boolean.TRUE);

        Label firstYear = new Label("firstYear", tableYear);
        table.getTable().add(firstYear);

        add(table.getTable());
        table.addTableRows();
    }

    protected void addChart () {
        Label title = new Label("countryChartTitle", "Net Disbursement by Sector - " + tableYear + " - " + countryCurrency + " - full amount");
        add(title);

        if (USE_NVD3) {
            PieChartNVD3 pieChartNVD3 = new PieChartNVD3(CdaService, "countryChart", "countryDashboardsChart");

            // add MDX queries parameters
            pieChartNVD3.setParam("paramFIRST_YEAR", Integer.toString(tableYear));
            pieChartNVD3.setParam("paramCountry", (recipientParam != null ? recipientParam : ""));
            if (currencyParam != null) {
                if (currencyParam.equals("true")) {
                    pieChartNVD3.setParam("paramcurrency", ReportsConstants.MDX_NAT_CURRENCY);
                }
            } else {
                pieChartNVD3.setParam("paramcurrency", ReportsConstants.MDX_NAT_CURRENCY);
            }

            add(pieChartNVD3);
        } else {
            PieChart pieChart = new PieChart(CdaService, "countryChart", "countryDashboardsChart") {
                @Override
                public List<Point> getResultSeries() {
                    this.result = this.runQuery();
                    List<Point> resultSeries = new ArrayList<>();

                    for (List<String> item : result.getResultset()) {
                        resultSeries.add(new Point(item.get(0), Float.parseFloat(item.get(1))));
                    }

                    return resultSeries;
                }
            };

            // add MDX queries parameters
            pieChart.setParam("paramFIRST_YEAR", Integer.toString(tableYear));
            pieChart.setParam("paramCountry", (recipientParam != null ? recipientParam : ""));
            if (currencyParam != null) {
                if (currencyParam.equals("true")) {
                    pieChart.setParam("paramcurrency", ReportsConstants.MDX_NAT_CURRENCY);
                }
            } else {
                pieChart.setParam("paramcurrency", ReportsConstants.MDX_NAT_CURRENCY);
            }

            Options options = pieChart.getOptions();
            // check if we have a result and make the chart slightly higher
            if (pieChart.getResultSeries().size() != 0) {
                options.getChartOptions().setHeight(350);
            }

            options.addSeries(new PointSeries()
                    .setType(SeriesType.PIE)
                    .setData(pieChart.getResultSeries()));
            add(pieChart.getChart());
        }
    }

    protected void addTableList () {
        Label title = new Label("countryTableListTitle", "Net Disbursement - " + tableYear + " - " + countryCurrency + " - full amount");
        add(title);

        Table table = new Table(CdaService, "countryTableList", "countryTableListRows", "countryDashboardsTableList") {
            @Override
            public ListView<String[]> getTableRows () {
                super.getTableRows();

                this.rows = new ArrayList<>();
                this.result = this.runQuery();

                return ReportsDashboardsUtils.processTableRowsTransactions(financialTransactionService,
                        this.rows, this.result, this.rowId, currencyParam, ReportsConstants.isCountry);
            }
        };

        // add MDX queries parameters
        table.setParam("paramFIRST_YEAR", Integer.toString(tableYear));
        table.setParam("paramCountry", (recipientParam != null ? recipientParam : ""));
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
