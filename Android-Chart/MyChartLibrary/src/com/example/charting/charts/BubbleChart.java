package com.example.charting.charts;

import android.content.Context;
import android.util.AttributeSet;

import com.example.charting.data.BubbleData;
import com.example.charting.data.BubbleDataSet;
import com.example.charting.interfaces.BubbleDataProvider;
import com.example.charting.renderer.BubbleChartRenderer;



public class BubbleChart extends BarLineChartBase<BubbleData> implements
		BubbleDataProvider {

	public BubbleChart(Context context) {
		super(context);
	}

	public BubbleChart(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public BubbleChart(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void init() {
		super.init();

		mRenderer = new BubbleChartRenderer(this, mAnimator, mViewPortHandler);
	}

	@Override
	protected void calcMinMax() {
		super.calcMinMax();

		if (mDeltaX == 0 && mData.getYValCount() > 0)
			mDeltaX = 1;

		mXChartMin = -0.5f;
		mXChartMax = (float) mData.getXValCount() - 0.5f;

		if (mRenderer != null) {
			for (BubbleDataSet set : mData.getDataSets()) {

				final float xmin = set.getXMin();
				final float xmax = set.getXMax();

				if (xmin < mXChartMin)
					mXChartMin = xmin;

				if (xmax > mXChartMax)
					mXChartMax = xmax;
			}
		}

		mDeltaX = Math.abs(mXChartMax - mXChartMin);
	}

	public BubbleData getBubbleData() {
		return mData;
	}
}
