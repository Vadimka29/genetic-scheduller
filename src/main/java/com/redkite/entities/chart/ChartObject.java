package com.redkite.entities.chart;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChartObject {
    private final String chartName;
    private final String unitY;
    private final String unitX;
    private final String type;
    private final List<ChartData> series;

    public ChartObject(String chartName, String unitX, String unitY, String type) {
        this.chartName = chartName;
        this.unitY = unitY;
        this.unitX = unitX;
        this.type = type;
        series = new ArrayList<>();
    }


    public ChartObject(Builder builder) {
        this.chartName = builder.chartName;
        this.unitX = builder.unitX;
        this.unitY = builder.unitY;
        this.series = builder.series;
        this.type = builder.type;
    }


    public static class Builder {
        private String chartName;
        private String unitY;
        private String unitX;
        private String type;
        private List<ChartData> series = new ArrayList<>();

        public Builder(String chartName, String unitX, String unitY) {
            this.chartName = chartName;
            this.unitX = unitX;
            this.unitY = unitY;
        }

        public Builder with(ChartData chartData) {
            series.add(chartData);
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public ChartObject build() {
            return new ChartObject(this);
        }
    }
}
