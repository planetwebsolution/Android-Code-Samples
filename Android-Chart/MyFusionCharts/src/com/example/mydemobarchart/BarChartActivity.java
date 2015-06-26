package com.example.mydemobarchart;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.example.charting.charts.BarChart;
import com.example.charting.components.Legend;
import com.example.charting.components.Legend.LegendForm;
import com.example.charting.components.Legend.LegendPosition;
import com.example.charting.components.XAxis;
import com.example.charting.components.XAxis.XAxisPosition;
import com.example.charting.components.YAxis;
import com.example.charting.components.YAxis.YAxisLabelPosition;
import com.example.charting.data.BarData;
import com.example.charting.data.BarDataSet;
import com.example.charting.data.BarEntry;
import com.example.charting.utils.ColorTemplate;
import com.example.charting.utils.ValueFormatter;

public class BarChartActivity extends Activity {

	public BarChart mychart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_barchart);

		mychart = (BarChart) findViewById(R.id.chart1);

		mychart.setDrawBarShadow(false);
		mychart.setDrawValueAboveBar(true);
	
		mychart.setMaxVisibleValueCount(100);

		mychart.setPinchZoom(false);
		mychart.setDescription("");
		
		XAxis xAxis = mychart.getXAxis();
		xAxis.setPosition(XAxisPosition.BOTTOM);
		xAxis.setDrawGridLines(false);
		xAxis.setSpaceBetweenLabels(2);

		ValueFormatter custom = new MyValueFormatter();

		YAxis leftAxis = mychart.getAxisLeft();
		leftAxis.setLabelCount(8);
		leftAxis.setValueFormatter(custom);
		leftAxis.setPosition(YAxisLabelPosition.OUTSIDE_CHART);
		leftAxis.setSpaceTop(15f);

		/*
		 * YAxis rightAxis = mChart.getAxisRight();
		 * rightAxis.setDrawGridLines(false); rightAxis.setLabelCount(8);
		 * rightAxis.setValueFormatter(custom); rightAxis.setSpaceTop(15f);
		 */

		Legend l = mychart.getLegend();
		l.setPosition(LegendPosition.BELOW_CHART_LEFT);
		l.setForm(LegendForm.CIRCLE);
		l.setFormSize(10f);
		l.setTextSize(11f);
		l.setXEntrySpace(4f);

		setData(15, 20);

		mychart.animateXY(3000, 3000);
	}

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
		mychart.setData(data);
	}

}
