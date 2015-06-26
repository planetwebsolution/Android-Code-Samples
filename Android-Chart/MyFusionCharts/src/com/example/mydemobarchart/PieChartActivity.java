package com.example.mydemobarchart;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;

import com.example.charting.animation.Easing;
import com.example.charting.charts.PieChart;
import com.example.charting.components.Legend;
import com.example.charting.components.Legend.LegendPosition;
import com.example.charting.data.Entry;
import com.example.charting.data.PieData;
import com.example.charting.data.PieDataSet;
import com.example.charting.utils.ColorTemplate;
import com.example.charting.utils.PercentFormatter;

public class PieChartActivity extends Activity {
	private PieChart mChart;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_piechart);

		mChart = (PieChart) findViewById(R.id.chart1);
		mChart.setUsePercentValues(true);
		mChart.setDescription("");
		mChart.setDragDecelerationFrictionCoef(0.95f);
		mChart.setDrawHoleEnabled(true);
		mChart.setHoleColorTransparent(true);
		mChart.setTransparentCircleColor(Color.WHITE);
		mChart.setHoleRadius(30f);
		mChart.setTransparentCircleRadius(61f);
		mChart.setDrawCenterText(true);
		mChart.setRotationAngle(0); // enable rotation of the chart by touch
		mChart.setRotationEnabled(true);
		mChart.setCenterText("Pie\nChart");
		setData(15, 100);
		mChart.animateY(1500, Easing.EasingOption.EaseInOutQuad); //
		Legend l = mChart.getLegend();
		l.setPosition(LegendPosition.RIGHT_OF_CHART);
		l.setXEntrySpace(7f);
		l.setYEntrySpace(5f);
		mChart.animateXY(1800, 1800);

	}

	private void setData(int count, float range) {

		String[] mYear = new String[] { "2000", "2001", "2002", "2003", "2004",
				"2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012",
				"2013", "2014", "2015" };
		float mult = range;
		
		ArrayList<String> xVals = new ArrayList<String>();
		for (int i = 0; i < count + 1; i++)
			xVals.add(mYear[i % mYear.length]);

		ArrayList<Entry> yVals1 = new ArrayList<Entry>();

		// IMPORTANT: In a PieChart, no values (Entry) should have the same //
		// xIndex (even if from different DataSets), since no values can be //
		// drawn
		// above each other.
	/*	for (int i = 0; i < count + 1; i++) {
			// yVals1.add(new Entry((float) (Math.random() * mult) + mult / 5,
			// i));
			yVals1.add(new Entry(100 * mult, i));
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
		
		PieDataSet dataSet = new PieDataSet(yVals1, " Income ");
		dataSet.setSliceSpace(3f);
		dataSet.setSelectionShift(5f);

		// add a lot of colors

		ArrayList<Integer> colors = new ArrayList<Integer>();

		for (int c : ColorTemplate.VORDIPLOM_COLORS)
			colors.add(c);

		for (int c : ColorTemplate.JOYFUL_COLORS)
			colors.add(c);

		for (int c : ColorTemplate.COLORFUL_COLORS)
			colors.add(c);

		for (int c : ColorTemplate.LIBERTY_COLORS)
			colors.add(c);

		for (int c : ColorTemplate.PASTEL_COLORS)
			colors.add(c);

		colors.add(ColorTemplate.getHoloBlue());

		dataSet.setColors(colors);

		PieData data = new PieData(xVals, dataSet);
		data.setValueFormatter(new PercentFormatter());
		data.setValueTextSize(11f);
		data.setValueTextColor(Color.WHITE);
		
		mChart.setData(data);
		mChart.invalidate();
	}

}
