/*
 * Copyright (c) 2013 Development Gateway.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 */

// aplication variable
var app = app || {};

$(document).ready(function () {
    // add more colors for Highcharts
    Highcharts.setOptions({
        colors: ["#4572A7", "#AA4643", "#89A54E", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92", "#DDDF00", "#ED561B", "#6AF9C4"]
    });

    Highcharts.getOptions().colors = $.map(Highcharts.getOptions().colors, function (color) {
        return {
            radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
            stops: [
                [0, color],
                [1, Highcharts.Color(color).brighten(-0.3).get('rgb')] // darken
            ]
        };
    });
});

var pathToCdaFile = '/some/path.cda';
var pieChart,
    columnChart,
    stackedBarChart,
    pieChartDefinition,
    columnsChartDefinition,
    stackedBarChartDefinition,
    pieChartQuery,
    pieQueryResult,
    columnQueryResult,
    columnChartQuery,
    stackedBarChartQuery,
    stackedBarQueryResult,
    sectorList,
    sectorListParameter,
    organizationList,
    organizationListParameter;

sectorList = new app.FilterModel({
    name: "sectorList",
    parameter: "sectorListParameter",
    htmlObject: "sector-list",
    queryDefinition: {
        dataAccessId: "sectorList",
        path: '/some/path.cda'
    },
    postFetch: function (values) {

        return values;
    },

    preExecution: function () {
        return undefined;
    },
    postExecution: function () {
    }
});

organizationList = new app.FilterModel({
    name: "organizationList",
    parameter: "organizationListParameter",
    htmlObject: "organization-list",
    queryDefinition: {
        dataAccessId: "organizationList",
        path: '/some/path.cda'
    },
    preExecution: function () {

        return undefined;
    },
    postExecution: function () {

    }
});

pieChartDefinition = new app.ChartPieDefinitionModel();
pieChartDefinition.get('chart').renderTo = 'pie-chart';
pieChartDefinition.get('plotOptions').series.events.click = function (event) {
    setTimeout(function () {
        console.log('pie chart clicked!');
    }, 500);
};

columnsChartDefinition = new app.ChartColumnDefinitionModel();
columnsChartDefinition.get('chart').renderTo = 'column-chart';
columnsChartDefinition.get('series')[0].pointWidth = 115;
columnsChartDefinition.get('xAxis').labels.rotation = -15;
columnsChartDefinition.get('plotOptions').series.events.click = function (event) {
    setTimeout(function () {
        console.log('column chart clicked!');
    }, 200);
};

stackedBarChartDefinition = new app.StackedBarDefinitionModel();
stackedBarChartDefinition.get('chart').renderTo = 'stackedbar-chart';
// stackedBarChartDefinition.get('plotOptions').series.events.click = function (event) {
//                     setTimeout(function() {
//                         console.log('stacked bar clicked!');
//                     }, 200);
//                 };

pieChartQuery = new app.ChartModel({
    name: "pieChartQuery",
    listeners: ['sectorListParameter'],
    parameters: [],
    resultvar: "pieQueryResult",
    queryDefinition: {
        dataAccessId: "simpleSQLQuery",
        path: '/some/path'
    },
    executeAtStart: true,
    preExecution: function () {
        // do nothing
    },
    postExecution: function () {
        var resultSeries = [];
        for (var i = 0; i < pieQueryResult.length && i < 10; i++) {
            // filter the results
            pieQueryResult[i].splice(0, 1);
            // we need to do this because Highcharts needs 'int' instead of 'string' :(
            // pieQueryResult[i][1] = parseInt(pieQueryResult[i][1], 10);
            pieQueryResult[i][1] = Math.floor(Math.random() * 1000);

            resultSeries.push(pieQueryResult[i]);
        }

        pieChartDefinition.get('series')[0].data = resultSeries;
        pieChart = new Highcharts.Chart(pieChartDefinition.toJSON());
    }
});

columnChartQuery = new app.ChartModel({
    name: "columnChartQuery",
    parameters: [],
    resultvar: "columnQueryResult",
    queryDefinition: {
        dataAccessId: "simpleSQLQuery",
        path: '/some/path'
    },
    executeAtStart: true,
    preExecution: function () {
        // do nothing
    },
    postExecution: function () {
        var resultCategories = [],
            resultSeries = [],
            colors = Highcharts.getOptions().colors,
            len = Highcharts.getOptions().colors.length,
            i;

        for (i = 0; i < columnQueryResult.length && i < 10; i++) {
            if (parseInt(columnQueryResult[i][2], 10) > 0) {
                resultCategories.push(columnQueryResult[i][1]);
                resultSeries.push({
                    name: columnQueryResult[i][1],
                    y: Math.floor(Math.random() * 1000),
                    // y: parseInt(columnQueryResult[i][2], 10), 
                    color: colors[i % len]      // access colors array in a circular manner
                });
            }
        }

        if (resultCategories.length > 0) {
            columnsChartDefinition.get('series')[0].data = resultSeries;
            columnsChartDefinition.get('xAxis').categories = resultCategories;
            columnChart = new Highcharts.Chart(columnsChartDefinition.toJSON());
        }
    }
});

stackedBarChartQuery = new app.ChartModel({
    name: "stackedBarChartQuery",
    parameters: [],
    resultvar: "stackedBarQueryResult",
    queryDefinition: {
        dataAccessId: "simpleSQLQuery",
        path: '/some/path'
    },
    executeAtStart: true,
    preExecution: function () {
        // do nothing
    },
    postExecution: function () {
        var resultSeries = [];

        // stackedBarChartDefinition.get('series')[0].data = [5, 1, 1, 3, 6, 2, 3];
        stackedBarChart = new Highcharts.Chart(stackedBarChartDefinition.toJSON());
    }
});

// get the charts as JSON objects
sectorList = sectorList.toJSON();
organizationList = organizationList.toJSON();
pieChartQuery = pieChartQuery.toJSON();
columnChartQuery = columnChartQuery.toJSON();
stackedBarChartQuery = stackedBarChartQuery.toJSON();

// The components to be loaded into the dashboard within the [] separated by ,
var components = [sectorList, organizationList, pieChartQuery, columnChartQuery, stackedBarChartQuery];

// The initial dashboard load function definition
var load = function () {
    Dashboards.init(components);
};

// The initial dashboard load function execution
load();
