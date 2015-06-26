package com.example.charting.interfaces;

import com.example.charting.components.YAxis.AxisDependency;
import com.example.charting.utils.Transformer;


public interface BarLineScatterCandleDataProvider extends ChartInterface {

    public Transformer getTransformer(AxisDependency axis);
    public int getMaxVisibleCount();
    public boolean isInverted(AxisDependency axis);
    
    public int getLowestVisibleXIndex();
    public int getHighestVisibleXIndex();
}
