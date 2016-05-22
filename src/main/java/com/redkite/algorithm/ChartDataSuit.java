package com.redkite.algorithm;

import com.redkite.entities.chart.ChartData;

/**
 * Created by Vadym on 20.05.2016.
 */
public interface ChartDataSuit {
    ChartData getTemperatureAndIterData();

    ChartData getEnergyFuncAndIterData();

    ChartData getProbabilityAndIterData();

    ChartData getProbabilityAndTemperData();
}