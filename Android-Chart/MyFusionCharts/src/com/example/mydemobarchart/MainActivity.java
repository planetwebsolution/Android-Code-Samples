package com.example.mydemobarchart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	Button btn_pie, btn_bar, btn_radar, btn_line, btn_signus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		btn_pie = (Button) findViewById(R.id.btn_pie);
		btn_bar = (Button) findViewById(R.id.btn_bar);
		btn_radar = (Button) findViewById(R.id.btn_radar);
		btn_signus = (Button) findViewById(R.id.btn_signus);
		btn_line = (Button) findViewById(R.id.btn_line);

		btn_pie.setOnClickListener(this);
		btn_bar.setOnClickListener(this);
		btn_radar.setOnClickListener(this);
		btn_signus.setOnClickListener(this);
		btn_line.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_pie:

			Intent intent = new Intent(this, PieChartActivity.class);
			startActivity(intent);

			break;

		case R.id.btn_bar:

			Intent intent1 = new Intent(this, BarChartActivity.class);
			startActivity(intent1);

			break;

		case R.id.btn_radar:

			Intent intent2 = new Intent(this, RadarChartActivitry.class);
			startActivity(intent2);

			break;

		case R.id.btn_signus:

			Intent intent3 = new Intent(this, BarChartActivitySinus.class);
			startActivity(intent3);

			break;

		case R.id.btn_line:

			Intent intent4 = new Intent(this, LineChartActivity.class);
			startActivity(intent4);

			break;

		}

	}

}