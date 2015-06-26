package com.example.mydemobarchart;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.example.charting.charts.RadarChart;
import com.example.charting.components.Legend;
import com.example.charting.components.Legend.LegendPosition;
import com.example.charting.components.XAxis;
import com.example.charting.components.YAxis;
import com.example.charting.data.DataSet;
import com.example.charting.data.Entry;
import com.example.charting.data.RadarData;
import com.example.charting.data.RadarDataSet;
import com.example.charting.utils.ColorTemplate;

public class RadarChartActivitry extends Activity {

	private RadarChart mChart;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_radarchart);
		
		mChart = (RadarChart) findViewById(R.id.chart1);
		mChart.setWebLineWidth(1.5f);
		mChart.setWebLineWidthInner(0.75f);
		mChart.setWebAlpha(100);
		mChart.setDescription("");

		MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);
		mChart.setMarkerView(mv);

		setData();

		XAxis xAxis = mChart.getXAxis();
		xAxis.setTextSize(9f);

		YAxis yAxis = mChart.getYAxis();
		yAxis.setLabelCount(5);
		yAxis.setTextSize(9f);
		yAxis.setStartAtZero(true);

		Legend l = mChart.getLegend();
		l.setPosition(LegendPosition.RIGHT_OF_CHART);
		l.setXEntrySpace(7f);
		l.setYEntrySpace(5f);

		// //////////////////////////
		
		if (mChart.isRotationEnabled())
			mChart.setRotationEnabled(true);
		else
			mChart.setRotationEnabled(false);
		   mChart.invalidate();
		
		////////////

		for (DataSet<?> set : mChart.getData().getDataSets())
			set.setDrawValues(!set.isDrawValuesEnabled());

		// //////////////

		ArrayList<RadarDataSet> sets = (ArrayList<RadarDataSet>) mChart
				.getData().getDataSets();

		for (RadarDataSet set : sets) {
			if (set.isDrawFilledEnabled())
				set.setDrawFilled(false);
			else
				set.setDrawFilled(true);
		}
		mChart.invalidate();
	}
	
	public void setData() {

		String[] mYear = new String[] { "2000", "2001", "2002", "2003", "2004",
				"2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012",
				"2013", "2014", "2015" };

		//float mult = 150;
		int cnt = 15;

		ArrayList<String> xVals = new ArrayList<String>();

		for (int i = 0; i < cnt; i++)
			xVals.add(mYear[i % mYear.length]);
		
		ArrayList<Entry> yVals1 = new ArrayList<Entry>();
		ArrayList<Entry> yVals2 = new ArrayList<Entry>();

	/*	for (int i = 0; i < cnt; i++) {
			yVals1.add(new Entry((float) (Math.random() * mult) + mult / 2, i));
		}

		for (int i = 0; i < cnt; i++) {
			yVals2.add(new Entry((float) (Math.random() * mult) + mult / 2, i));
		}*/
		
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
		
		yVals2.add(new Entry(10000, 0));
		yVals2.add(new Entry(20000, 1));
		yVals2.add(new Entry(40000, 2));
		yVals2.add(new Entry(20000, 3));
		yVals2.add(new Entry(20000, 4));
		yVals2.add(new Entry(40000, 5));
		yVals2.add(new Entry(33000, 6));
		yVals2.add(new Entry(11000, 7));
		yVals2.add(new Entry(23000, 8));
		yVals2.add(new Entry(30000, 9));
		yVals2.add(new Entry(45000, 10));
		yVals2.add(new Entry(37000, 11));
		yVals2.add(new Entry(12000, 12));
		yVals2.add(new Entry(23000, 13));
		yVals2.add(new Entry(31000, 14));


		RadarDataSet set1 = new RadarDataSet(yVals1, "Income");
		set1.setColor(ColorTemplate.COLORFUL_COLORS[0]);
		//set1.setDrawFilled(true);
		set1.setLineWidth(2f);

		RadarDataSet set2 = new RadarDataSet(yVals2, "Expenses");
		set2.setColor(ColorTemplate.COLORFUL_COLORS[4]);
		//set2.setDrawFilled(true);
		set2.setLineWidth(2f);

		ArrayList<RadarDataSet> sets = new ArrayList<RadarDataSet>();
		sets.add(set1);
		sets.add(set2);     

		RadarData data = new RadarData(xVals, sets);
		data.setValueTextSize(8f);
		data.setDrawValues(false);
		
		mChart.setData(data);
		mChart.invalidate();

}
}
