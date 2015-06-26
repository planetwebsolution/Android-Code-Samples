package com.example.charting.interfaces;

import com.example.charting.data.BarData;


public interface BarDataProvider extends BarLineScatterCandleDataProvider {

    public BarData getBarData();
    public boolean isDrawBarShadowEnabled();
    public boolean isDrawValueAboveBarEnabled();
    public boolean isDrawHighlightArrowEnabled();
    public boolean isDrawValuesForWholeStackEnabled();
}
