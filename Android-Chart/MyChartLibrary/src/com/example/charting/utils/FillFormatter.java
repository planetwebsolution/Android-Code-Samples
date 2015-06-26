
package com.example.charting.utils;

import com.example.charting.data.LineData;
import com.example.charting.data.LineDataSet;


/**
 * Interface for providing a custom logic to where the filling line of a DataSet
 * should end. If setFillEnabled(...) is set to true.

 */
public interface FillFormatter {

    /**
     * Returns the vertical (y-axis) position where the filled-line of the
     * DataSet should end.
     * 
     * @param dataSet
     * @param data
     * @param chartMaxY
     * @param chartMinY
     * @return
     */
    public float getFillLinePosition(LineDataSet dataSet, LineData data, float chartMaxY,
            float chartMinY);
}
