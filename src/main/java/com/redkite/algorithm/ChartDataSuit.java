package com.redkite.algorithm;

import com.redkite.entities.chart.ChartData;


public interface ChartDataSuit {

    default ChartData getTemperatureAndIterData() {
        return new ChartData("Empty");
    }

    default ChartData getEnergyFuncAndIterData() {
        return new ChartData("Empty");
    }

    default ChartData getProbabilityAndIterData() {
        return new ChartData("Empty");
    }

    default ChartData getProbabilityAndTemperData() {
        return new ChartData("Empty");
    }

    default ChartData getFitnessAndIterationData() {
        return new ChartData("Empty");
    }
}
