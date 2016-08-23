package com.dengmingli.game2048;

import org.w3c.dom.Text;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Card extends FrameLayout{

	private int num=0;

	private TextView label;

	public Card(Context context) {
		super(context);

		label=new TextView(getContext());
		label.setTextSize(32);
		label.setGravity(Gravity.CENTER);
		label.setBackgroundColor(0x33ffffff);
		LayoutParams lpLayoutParams= new LayoutParams(-1,-1);
		lpLayoutParams.setMargins(10, 10, 0, 0);
		addView(label, lpLayoutParams);
		setNum(0);
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {

		this.num = num;
		if (num<=0) {
			label.setText("");

		}
		else {
			label.setText(num+"");
		}

	}


	public boolean equals(Card bCard) {
		return getNum()==bCard.getNum();
	}



}
