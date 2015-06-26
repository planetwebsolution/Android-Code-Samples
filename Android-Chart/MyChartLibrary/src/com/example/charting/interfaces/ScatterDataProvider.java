package com.example.charting.interfaces;

import com.example.charting.data.ScatterData;


public interface ScatterDataProvider extends BarLineScatterCandleDataProvider {

    public ScatterData getScatterData();
    
}
