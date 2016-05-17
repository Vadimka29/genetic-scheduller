(function ($) {
    function renderChart(container, chartData) {
        container.highcharts({
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
        $.get("/api/stub/chart", function(data) {
            var chartData = JSON.parse(data);
            renderChart($("#container"), chartData);
        });


    });

})(jQuery);