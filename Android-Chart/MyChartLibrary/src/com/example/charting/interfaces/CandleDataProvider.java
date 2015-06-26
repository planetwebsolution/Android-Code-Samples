package com.example.charting.interfaces;

import com.example.charting.data.CandleData;


public interface CandleDataProvider extends BarLineScatterCandleDataProvider {

    public CandleData getCandleData();
}
