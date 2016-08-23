package com.dengmingli.game2048;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.text.StaticLayout;
import android.view.Menu;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView tv_score;
	private TextView tvcolor;

	
	private int color=0;
	public void setColor(int color) {
		this.color = color;
	}
	
	public void showcolor() {
			switch (color) {
			case 2:
				tvcolor.setBackgroundColor(0xff100fff);
				break;
			case 4:
				tvcolor.setBackgroundColor(0xffffb6c1);
				break;
			case 8:
				tvcolor.setBackgroundColor(0xffdc143c);
				break;
			case 16:
				tvcolor.setBackgroundColor(0xffda70d6);
				break;
			case 32:
				tvcolor.setBackgroundColor(0xff000080);
				break;
			case 64:
				tvcolor.setBackgroundColor(0xff6495ed);
				break;
			case 128:
				tvcolor.setBackgroundColor(0xff87ceeb);
				break;
			case 256:
				tvcolor.setBackgroundColor(0xffafeeee);
				break;
			case 512:
				tvcolor.setBackgroundColor(0xff3cb371);
				break;
			case 1024:
				tvcolor.setBackgroundColor(0xffffd700);
				break;
			case 2048:
				tvcolor.setBackgroundColor(0xff8b0000);
				break;
			case 4096:
				tvcolor.setBackgroundColor(0xffa9a9a9);
				break;
			case 8192:
				tvcolor.setBackgroundColor(0xff000000);
				break;
			}
		}
		
	private int score=0;

	public MainActivity() {
		MainActivity=this;
	}
	private static  MainActivity MainActivity=null;

	public static MainActivity getMainActivity() {
		return MainActivity;
	}
	
	public void clearScore() {
		score=0;
		showScore();
	}
	public void showScore() {
		tv_score.setText(score+"");
	}
	public void addScore(int s) {
		score+=s;
		showScore();
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		tv_score=(TextView)findViewById(R.id.tv_score);
		tvcolor=(TextView)findViewById(R.id.tv_color);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
