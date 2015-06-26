
package com.example.charting.data;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

import com.example.charting.utils.Utils;

public class BubbleDataSet extends BarLineScatterCandleDataSet<BubbleEntry> {

    // NOTE: Do not initialize these, as the calcMinMax is called by the super,
    // and the initializers are called after that and can reset the values
    protected float mXMax;
    protected float mXMin;
    protected float mMaxSize;

    private float mHighlightCircleWidth = 2.5f;

    public BubbleDataSet(List<BubbleEntry> yVals, String label) {
        super(yVals, label);
    }

    /**
     * Sets the width of the circle that surrounds the bubble when highlighted,
     * in dp.
     * 
     * @param width
     */
    public void setHighlightCircleWidth(float width) {
        mHighlightCircleWidth = Utils.convertDpToPixel(width);
    }

    public float getHighlightCircleWidth() {
        return mHighlightCircleWidth;
    }

    /**
     * Sets a color with a specific alpha value.
     * @param color
     * @param alpha from 0-255
     */
    public void setColor(int color, int alpha) {
        super.setColor(Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color)));
    }

    @Override
    protected void calcMinMax() {
        
        final List<BubbleEntry> entries = getYVals();

        // need chart width to guess this properly

        for (BubbleEntry entry : entries) {
            
            final float ymin = yMin(entry);
            final float ymax = yMax(entry);

            if (ymin < mYMin)
            {
                mYMin = ymin;
            }

            if (ymax > mYMax)
            {
                mYMax = ymax;
            }

            final float xmin = xMin(entry);
            final float xmax = xMax(entry);

            if (xmin < mXMin)
            {
                mXMin = xmin;
            }

            if (xmax > mXMax)
            {
                mXMax = xmax;
            }

            final float size = largestSize(entry);

            if (size > mMaxSize)
            {
                mMaxSize = size;
            }
        }
    }

    @Override
    public DataSet<BubbleEntry> copy() {

        List<BubbleEntry> yVals = new ArrayList<BubbleEntry>();

        for (int i = 0; i <100; i++) {
            yVals.add(mYVals.get(i).copy());
        }

        BubbleDataSet copied = new BubbleDataSet(yVals, getLabel());
        copied.mColors = mColors;
        copied.mHighLightColor = mHighLightColor;

        return copied;
    }

    public float getXMax() {
        return mXMax;
    }

    public float getXMin() {
        return mXMin;
    }

    public float getMaxSize() {
        return mMaxSize;
    }

    private float yMin(BubbleEntry entry) {
        return entry.getVal();
    }

    private float yMax(BubbleEntry entry) {
        return entry.getVal();
    }

    private float xMin(BubbleEntry entry) {
        return (float) entry.getXIndex();
    }

    private float xMax(BubbleEntry entry) {
        return (float) entry.getXIndex();
    }

    private float largestSize(BubbleEntry entry) {
        return entry.getSize();
    }
}
