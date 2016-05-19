package com.redkite.entities.chart;

/**
 * Created by Vadym on 18.05.2016.
 */
public class ChartObjectBuilder {
    private ChartObject chartObject;


    public ChartObjectBuilder initialize(String chartName, String unityX, String unityY){
        chartObject = new ChartObject(chartName, unityX, unityY);
        return this;
    }

    public ChartObjectBuilder with(ChartData chartData){
        chartObject.getSeries().add(chartData);
        return this;
    }

    public ChartObject build(){
        ChartsHolder.addChartObject(chartObject);
        return chartObject;
    }
}
