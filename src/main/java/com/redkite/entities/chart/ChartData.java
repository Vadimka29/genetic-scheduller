package com.redkite.entities.chart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadym on 18.05.2016.
 */
public class ChartData {
    private String name;
    List<Number[]> data;


    public ChartData(String name) {
        this.name = name;
        data = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Number[]> getData() {
        return data;
    }
}
