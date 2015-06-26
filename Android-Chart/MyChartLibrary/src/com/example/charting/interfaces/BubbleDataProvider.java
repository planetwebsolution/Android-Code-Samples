package com.example.charting.interfaces;

import com.example.charting.data.BubbleData;


public interface BubbleDataProvider extends BarLineScatterCandleDataProvider {

    public BubbleData getBubbleData();
    
}
