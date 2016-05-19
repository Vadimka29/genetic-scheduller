package com.redkite.entities.chart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadym on 17.05.2016.
 */
public class ChartObject {
    private String chartName;
    private String unitY;
    private String unitX;
    private List<ChartData> series;

    public ChartObject(String chartName, String unitX, String unitY) {
        this.chartName = chartName;
        this.unitY = unitY;
        this.unitX = unitX;
        series = new ArrayList<>();
    }

    public String getChartName() {
        return chartName;
    }

    public void setChartName(String chartName) {
        this.chartName = chartName;
    }

    public String getUnitY() {
        return unitY;
    }

    public void setUnitY(String unitY) {
        this.unitY = unitY;
    }

    public String getUnitX() {
        return unitX;
    }

    public void setUnitX(String unitX) {
        this.unitX = unitX;
    }

    public List<ChartData> getSeries() {
        return series;
    }

    public void setSeries(List<ChartData> series) {
        this.series = series;
    }
}
