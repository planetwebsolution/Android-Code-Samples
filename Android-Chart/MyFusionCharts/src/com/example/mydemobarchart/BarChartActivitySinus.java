package com.example.mydemobarchart;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Window;
import com.example.charting.charts.BarChart;
import com.example.charting.components.Legend;
import com.example.charting.components.Legend.LegendForm;
import com.example.charting.components.Legend.LegendPosition;
import com.example.charting.components.XAxis;
import com.example.charting.components.XAxis.XAxisPosition;
import com.example.charting.components.YAxis;
import com.example.charting.data.BarData;
import com.example.charting.data.BarDataSet;
import com.example.charting.data.BarEntry;
import com.example.charting.data.DataSet;
import com.example.charting.utils.ColorTemplate;
import com.example.charting.utils.FileUtils;

public class BarChartActivitySinus extends Activity {
	
	protected BarChart mChart;
	private Typeface mTf;
	private List<BarEntry> mSinusData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_barchart_sinus);
		mSinusData = FileUtils.loadBarEntriesFromAssets(getAssets(),
				"othersine.txt");

		mChart = (BarChart) findViewById(R.id.chart1);
		mChart.setDrawBarShadow(false);
		mChart.setDrawValueAboveBar(true);
		mChart.setDescription("");

		mChart.setMaxVisibleValueCount(60);
		// scaling can now only be done on x- and y-axis separately
		mChart.setPinchZoom(false);
		mChart.setDrawGridBackground(false);
		// mChart.setDrawYLabels(false);
		mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");

		XAxis xAxis = mChart.getXAxis();
		xAxis.setPosition(XAxisPosition.BOTTOM);
		xAxis.setTypeface(mTf);
		xAxis.setDrawGridLines(false);
		xAxis.setEnabled(false);

		YAxis leftAxis = mChart.getAxisLeft();
		leftAxis.setTypeface(mTf);
		leftAxis.setLabelCount(6);
		leftAxis.setStartAtZero(false);
		leftAxis.setAxisMinValue(-1.0f);
		leftAxis.setAxisMaxValue(1.0f);

		/*YAxis rightAxis = mChart.getAxisRight();
		rightAxis.setDrawGridLines(false);
		rightAxis.setTypeface(mTf);
		rightAxis.setLabelCount(6);
		rightAxis.setStartAtZero(false);          
		rightAxis.setAxisMinValue(-2.5f);
		rightAxis.setAxisMaxValue(2.5f);*/

		Legend l = mChart.getLegend();
		l.setPosition(LegendPosition.BELOW_CHART_LEFT);
		l.setForm(LegendForm.SQUARE);
		l.setFormSize(9f);
		l.setTextSize(11f);
		l.setXEntrySpace(4f);

		mChart.animateXY(2000, 2000);
		
		setData(100);
		
		for (DataSet<?> set : mChart.getData().getDataSets())
			set.setDrawValues(!set.isDrawValuesEnabled());
////
		
		if (mChart.isPinchZoomEnabled())
			mChart.setPinchZoom(false);
		else
			mChart.setPinchZoom(true);
		mChart.invalidate();
	}


	private void setData(int count) {

		ArrayList<String> xVals = new ArrayList<String>();

		ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

		for (int i = 0; i < count; i++) {
			xVals.add(i + "");
			entries.add(mSinusData.get(i));
		}

		BarDataSet set = new BarDataSet(entries, "Sine data");
		set.setBarSpacePercent(40f);
		set.setColor(Color.rgb(240, 120, 124));
		BarData data = new BarData(xVals, set);
		data.setValueTextSize(10f);
		data.setValueTypeface(mTf);
		data.setDrawValues(false);
		
		set.setColors(ColorTemplate.COLORFUL_COLORS);
		mChart.setData(data);
	}
	
	/*
	public void setData(int count, float range) {

		String[] mYear = new String[] { "2000", "2001", "2002", "2003", "2004",
				"2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012",
				"2013", "2014", "2015" };

		ArrayList<String> xVals = new ArrayList<String>();
		for (int i = 0; i < count; i++) {
			xVals.add(mYear[i % 15]);
		}

		ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
		yVals1.add(new BarEntry(30000, 0));
		yVals1.add(new BarEntry(40000, 1));
		yVals1.add(new BarEntry(50000, 2));
		yVals1.add(new BarEntry(30000, 3));
		yVals1.add(new BarEntry(20000, 4));
		yVals1.add(new BarEntry(30000, 5));
		yVals1.add(new BarEntry(40000, 6));
		yVals1.add(new BarEntry(50000, 7));
		yVals1.add(new BarEntry(30000, 8));
		yVals1.add(new BarEntry(30000, 9));
		yVals1.add(new BarEntry(40000, 10));
		yVals1.add(new BarEntry(50000, 11));
		yVals1.add(new BarEntry(30000, 12));
		yVals1.add(new BarEntry(30000, 13));
		yVals1.add(new BarEntry(40000, 14));

		BarDataSet set1 = new BarDataSet(yVals1, "Expenses of Year");
		set1.setColors(ColorTemplate.COLORFUL_COLORS);
		set1.setBarSpacePercent(35f);

		ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
		dataSets.add(set1);

		BarData data = new BarData(xVals, dataSets);

		data.setValueTextSize(10f);
		mChart.setData(data);
	}
}*/
}