package com.redkite.entities.chart;

import org.springframework.context.Lifecycle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadym on 17.05.2016.
 */
@Service
public class ChartsHolder {
    private static List<ChartObject> chartObjectList = new ArrayList<>();

    public static void addChartObject(ChartObject chartObject){
        if(chartObject == null){
            throw new IllegalArgumentException("chartObject can't be null!");
        }
        chartObjectList.add(chartObject);
    }
    public static List<ChartObject> getChartObjectList(){
        return chartObjectList;
    }
}
