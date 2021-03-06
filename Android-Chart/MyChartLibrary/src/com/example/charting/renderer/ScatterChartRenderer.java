
package com.example.charting.renderer;

import java.util.List;

import android.graphics.Canvas;
import android.graphics.Paint.Style;
import android.graphics.Path;

import com.example.charting.animation.ChartAnimator;
import com.example.charting.buffer.ScatterBuffer;
import com.example.charting.charts.ScatterChart.ScatterShape;
import com.example.charting.data.Entry;
import com.example.charting.data.ScatterData;
import com.example.charting.data.ScatterDataSet;
import com.example.charting.interfaces.ScatterDataProvider;
import com.example.charting.utils.Highlight;
import com.example.charting.utils.Transformer;
import com.example.charting.utils.Utils;
import com.example.charting.utils.ViewPortHandler;

public class ScatterChartRenderer extends DataRenderer {

    protected ScatterDataProvider mChart;

    protected ScatterBuffer[] mScatterBuffers;

    public ScatterChartRenderer(ScatterDataProvider chart, ChartAnimator animator,
            ViewPortHandler viewPortHandler) {
        super(animator, viewPortHandler);
        mChart = chart;

        mRenderPaint.setStrokeWidth(Utils.convertDpToPixel(1f));
    }

    @Override
    public void initBuffers() {

        ScatterData scatterData = mChart.getScatterData();

        mScatterBuffers = new ScatterBuffer[scatterData.getDataSetCount()];

        for (int i = 0; i < mScatterBuffers.length; i++) {
            ScatterDataSet set = scatterData.getDataSetByIndex(i);
            mScatterBuffers[i] = new ScatterBuffer(set.getEntryCount() * 2);
        }
    }

    @Override
    public void drawData(Canvas c) {

        ScatterData scatterData = mChart.getScatterData();

        for (ScatterDataSet set : scatterData.getDataSets()) {

            if (set.isVisible())
                drawDataSet(c, set);
        }
    }

    protected void drawDataSet(Canvas c, ScatterDataSet dataSet) {

        Transformer trans = mChart.getTransformer(dataSet.getAxisDependency());

        float phaseX = mAnimator.getPhaseX();
        float phaseY = mAnimator.getPhaseY();

        List<Entry> entries = dataSet.getYVals();

        float shapeHalf = dataSet.getScatterShapeSize() / 2f;

        ScatterShape shape = dataSet.getScatterShape();

        ScatterBuffer buffer = mScatterBuffers[mChart.getScatterData().getIndexOfDataSet(
                dataSet)];
        buffer.setPhases(phaseX, phaseY);
        buffer.feed(entries);

        trans.pointValuesToPixel(buffer.buffer);

        switch (shape) {
            case SQUARE:

                mRenderPaint.setStyle(Style.FILL);

                for (int i = 0; i < buffer.size(); i += 2) {

                    if (!mViewPortHandler.isInBoundsRight(buffer.buffer[i]))
                        break;

                    if (!mViewPortHandler.isInBoundsLeft(buffer.buffer[i])
                            || !mViewPortHandler.isInBoundsY(buffer.buffer[i + 1]))
                        continue;

                    mRenderPaint.setColor(dataSet.getColor(i / 2));
                    c.drawRect(buffer.buffer[i] - shapeHalf,
                            buffer.buffer[i + 1] - shapeHalf, buffer.buffer[i]
                                    + shapeHalf, buffer.buffer[i + 1]
                                    + shapeHalf, mRenderPaint);
                }
                break;
            case CIRCLE:

                mRenderPaint.setStyle(Style.FILL);

                for (int i = 0; i < buffer.size(); i += 2) {

                    if (!mViewPortHandler.isInBoundsRight(buffer.buffer[i]))
                        break;

                    if (!mViewPortHandler.isInBoundsLeft(buffer.buffer[i])
                            || !mViewPortHandler.isInBoundsY(buffer.buffer[i + 1]))
                        continue;

                    mRenderPaint.setColor(dataSet.getColor(i / 2));
                    c.drawCircle(buffer.buffer[i], buffer.buffer[i + 1], shapeHalf,
                            mRenderPaint);
                }
                break;
            case TRIANGLE:

                mRenderPaint.setStyle(Style.FILL);

                // create a triangle path
                Path tri = new Path();

                for (int i = 0; i < buffer.size(); i += 2) {

                    if (!mViewPortHandler.isInBoundsRight(buffer.buffer[i]))
                        break;

                    if (!mViewPortHandler.isInBoundsLeft(buffer.buffer[i])
                            || !mViewPortHandler.isInBoundsY(buffer.buffer[i + 1]))
                        continue;

                    mRenderPaint.setColor(dataSet.getColor(i / 2));
                    tri.moveTo(buffer.buffer[i], buffer.buffer[i + 1] - shapeHalf);
                    tri.lineTo(buffer.buffer[i] + shapeHalf, buffer.buffer[i + 1] + shapeHalf);
                    tri.lineTo(buffer.buffer[i] - shapeHalf, buffer.buffer[i + 1] + shapeHalf);
                    tri.close();

                    c.drawPath(tri, mRenderPaint);
                    tri.reset();
                }
                break;
            case CROSS:

                mRenderPaint.setStyle(Style.STROKE);

                for (int i = 0; i < buffer.size(); i += 2) {

                    if (!mViewPortHandler.isInBoundsRight(buffer.buffer[i]))
                        break;

                    if (!mViewPortHandler.isInBoundsLeft(buffer.buffer[i])
                            || !mViewPortHandler.isInBoundsY(buffer.buffer[i + 1]))
                        continue;

                    mRenderPaint.setColor(dataSet.getColor(i / 2));

                    c.drawLine(buffer.buffer[i] - shapeHalf, buffer.buffer[i + 1],
                            buffer.buffer[i] + shapeHalf,
                            buffer.buffer[i + 1], mRenderPaint);
                    c.drawLine(buffer.buffer[i], buffer.buffer[i + 1] - shapeHalf,
                            buffer.buffer[i], buffer.buffer[i + 1]
                                    + shapeHalf, mRenderPaint);
                }
                break;
            default:
                break;
        }

        // else { // draw the custom-shape
        //
        // Path customShape = dataSet.getCustomScatterShape();
        //
        // for (int j = 0; j < entries.size() * mAnimator.getPhaseX(); j += 2) {
        //
        // Entry e = entries.get(j / 2);
        //
        // if (!fitsBounds(e.getXIndex(), mMinX, mMaxX))
        // continue;
        //
        // if (customShape == null)
        // return;
        //
        // mRenderPaint.setColor(dataSet.getColor(j));
        //
        // Path newPath = new Path(customShape);
        // newPath.offset(e.getXIndex(), e.getVal());
        //
        // // transform the provided custom path
        // trans.pathValueToPixel(newPath);
        // c.drawPath(newPath, mRenderPaint);
        // }
        // }
    }

    @Override
    public void drawValues(Canvas c) {

        // if values are drawn
        if (mChart.getScatterData().getYValCount() < mChart.getMaxVisibleCount()
                * mViewPortHandler.getScaleX()) {

            List<ScatterDataSet> dataSets = mChart.getScatterData().getDataSets();

            for (int i = 0; i < mChart.getScatterData().getDataSetCount(); i++) {

                ScatterDataSet dataSet = dataSets.get(i);

                if (!dataSet.isDrawValuesEnabled())
                    continue;

                // apply the text-styling defined by the DataSet
                applyValueTextStyle(dataSet);

                List<Entry> entries = dataSet.getYVals();

                float[] positions = mChart.getTransformer(dataSet.getAxisDependency())
                        .generateTransformedValuesScatter(entries,
                                mAnimator.getPhaseY());

                float shapeSize = dataSet.getScatterShapeSize();

                for (int j = 0; j < positions.length * mAnimator.getPhaseX(); j += 2) {

                    if (!mViewPortHandler.isInBoundsRight(positions[j]))
                        break;

                    // make sure the lines don't do shitty things outside bounds
                    if ((!mViewPortHandler.isInBoundsLeft(positions[j])
                            || !mViewPortHandler.isInBoundsY(positions[j + 1])))
                        continue;

                    float val = entries.get(j / 2).getVal();

                    c.drawText(dataSet.getValueFormatter().getFormattedValue(val), positions[j],
                            positions[j + 1] - shapeSize,
                            mValuePaint);
                }
            }
        }
    }

    @Override
    public void drawExtras(Canvas c) {
    }

    @Override
    public void drawHighlighted(Canvas c, Highlight[] indices) {

        for (int i = 0; i < indices.length; i++) {

            ScatterDataSet set = mChart.getScatterData().getDataSetByIndex(indices[i]
                    .getDataSetIndex());

            if (set == null)
                continue;

            mHighlightPaint.setColor(set.getHighLightColor());

            int xIndex = indices[i].getXIndex(); // get the
                                                 // x-position

            if (xIndex > mChart.getXChartMax() * mAnimator.getPhaseX())
                continue;

            float y = set.getYValForXIndex(xIndex) * mAnimator.getPhaseY(); // get
                                                                            // the
            // y-position

            float[] pts = new float[] {
                    xIndex, mChart.getYChartMax(), xIndex, mChart.getYChartMin(), 0, y,
                    mChart.getXChartMax(), y
            };

            mChart.getTransformer(set.getAxisDependency()).pointValuesToPixel(pts);
            // draw the highlight lines
            c.drawLines(pts, mHighlightPaint);
        }
    }
}
