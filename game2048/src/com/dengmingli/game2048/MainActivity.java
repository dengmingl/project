package com.dengmingli.game2048;

import android.os.Bundle;
import android.app.Activity;
import android.text.StaticLayout;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView tv_score;
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
		setContentView(R.layout.activity_main);

		tv_score=(TextView)findViewById(R.id.tv_score);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
