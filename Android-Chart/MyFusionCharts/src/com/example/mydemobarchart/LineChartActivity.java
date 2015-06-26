package com.example.mydemobarchart;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;

import com.example.charting.charts.LineChart;
import com.example.charting.components.Legend;
import com.example.charting.components.Legend.LegendForm;
import com.example.charting.components.XAxis;
import com.example.charting.components.YAxis;
import com.example.charting.data.DataSet;
import com.example.charting.data.Entry;
import com.example.charting.data.LineData;
import com.example.charting.data.LineDataSet;

public class LineChartActivity extends Activity {

	private LineChart mChart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_linechart);

		mChart = (LineChart) findViewById(R.id.chart1);
		mChart.setDrawGridBackground(true);
		// enable value highlighting
		mChart.setHighlightEnabled(true);
		mChart.setDescription("");
		// enable touch gestures
		mChart.setTouchEnabled(true);
		mChart.setPinchZoom(true);
		mChart.setBackgroundColor(Color.LTGRAY);
		
		MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);
		mChart.setMarkerView(mv);
		
		XAxis xl = mChart.getXAxis();
		xl.setAvoidFirstLastClipping(true);

		YAxis leftAxis = mChart.getAxisLeft();
		leftAxis.setInverted(true);

		YAxis rightAxis = mChart.getAxisRight();
		rightAxis.setEnabled(false);
		// add data
		setData(15, 50);

		Legend l = mChart.getLegend();
		l.setForm(LegendForm.SQUARE);
		// //////////////////

		ArrayList<LineDataSet> sets = (ArrayList<LineDataSet>) mChart.getData()
				.getDataSets();

		for (LineDataSet set : sets) {
			if (set.isDrawCirclesEnabled())
				set.setDrawCircles(true);
			else
				set.setDrawCircles(false);
		}

		for (DataSet<?> set : mChart.getData().getDataSets())
			set.setDrawValues(set.isDrawValuesEnabled());

		// ///////////////////////

		mChart.animateX(3000);
		// /////////////

		if (mChart.isPinchZoomEnabled())
			mChart.setPinchZoom(true);
		else
			mChart.setPinchZoom(false);

		// //////////

		mChart.getAxisLeft().setStartAtZero(
				!mChart.getAxisLeft().isStartAtZeroEnabled());
		mChart.getAxisRight().setStartAtZero(
				!mChart.getAxisRight().isStartAtZeroEnabled());

		// ////////////

		mChart.invalidate();
	}

	private void setData(int count, float range) {

		ArrayList<String> xVals = new ArrayList<String>();
		/*
		 * for (int i = 0; i < count; i++) { xVals.add((i % 30) + "/" + (i % 12)
		 * + "/14"); }
		 */

		String[] mYear = new String[] { "2000", "2001", "2002", "2003", "2004",
				"2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012",
				"2013", "2014", "2015" };

		for (int i = 0; i < count; i++) {
			xVals.add(mYear[i % 15]);
		}

		ArrayList<Entry> yVals1 = new ArrayList<Entry>();

		/*
		 * for (int i = 0; i < count; i++) { float mult = (range + 1); float val
		 * = (float) (Math.random() * mult) + 3;
		 * 
		 * 
		 * yVals1.add(new Entry(val, i)); }
		 */

		yVals1.add(new Entry(30000, 0));
		yVals1.add(new Entry(40000, 1));
		yVals1.add(new Entry(50000, 2));
		yVals1.add(new Entry(30000, 3));
		yVals1.add(new Entry(20000, 4));
		yVals1.add(new Entry(30000, 5));
		yVals1.add(new Entry(40000, 6));
		yVals1.add(new Entry(50000, 7));
		yVals1.add(new Entry(30000, 8));
		yVals1.add(new Entry(30000, 9));
		yVals1.add(new Entry(40000, 10));
		yVals1.add(new Entry(50000, 11));
		yVals1.add(new Entry(30000, 12));
		yVals1.add(new Entry(30000, 13));
		yVals1.add(new Entry(40000, 14));

		// create a dataset and give it a type
		LineDataSet set1 = new LineDataSet(yVals1, "Income");

		set1.setLineWidth(1.5f);
		set1.setCircleSize(4f);

		// create a data object with the datasets
		LineData data = new LineData(xVals, set1);

		// set data
		mChart.setData(data);
	}
}
