(function ($) {
    function renderChart(container, chartData) {
        container.highcharts({
            chart: {
                type: chartData.type || "line"
            },
            title: {
                text: chartData.chartName
            },
            xAxis: {
                title: {
                    text: chartData.unitX
                }
            },
            yAxis: {
                title: {
                    text: chartData.unitY
                }
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'middle',
                borderWidth: 0
            },
            series: chartData.series,
            credits: {enabled: false},
            exporting: {enabled: false}
        });
    }

    $(document).ready(function () {
        $.get("/api/stub/chart", function(charts) {
            var container = $(".chart-container");
            for(var i = 0; i < charts.length; i++) {
                var chartDiv = $("<div></div>").appendTo(container);
                chartDiv.add("chart");
                renderChart(chartDiv, charts[i]);
            }

        });
    });

})(jQuery);