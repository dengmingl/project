package com.dengmingli.game2048;


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
		label.setBackgroundColor(0xffbbaba0);
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
		label.setText("");
	}
	public boolean equals(Card bCard) {
		return getNum()==bCard.getNum();
	}

	public void changgecolor(int color){
		switch (color) {
		case 0:
			label.setPadding(10, 10, 0, 0);
			label.setBackgroundColor(0x33ffffff);
			break;
		case 2:
			label.setPadding(10, 10, 0, 0);
			label.setBackgroundColor(0xff100fff);
			break;

		case 4:
			label.setPadding(10, 10, 0, 0);
			label.setBackgroundColor(0xffffb6c1);
			break;
		case 8:
			label.setPadding(10, 10, 0, 0);
			label.setBackgroundColor(0xffdc143c);
			break;
		case 16:
			label.setPadding(10, 10, 0, 0);
			label.setBackgroundColor(0xffda70d6);
			break;
		case 32:
			label.setPadding(10, 10, 0, 0);
			label.setBackgroundColor(0xff000080);
			break;
		case 64:
			label.setPadding(10, 10, 0, 0);
			label.setBackgroundColor(0xff6495ed);
			break;
		case 128:
			label.setPadding(10, 10, 0, 0);
			label.setBackgroundColor(0xff87ceeb);
			break;
		case 256:
			label.setPadding(10, 10, 0, 0);
			label.setBackgroundColor(0xffafeeee);
			break;
		case 512:
			label.setPadding(10, 10, 0, 0);
			label.setBackgroundColor(0xff3cb371);
			break;
		case 1024:
			label.setPadding(10, 10, 0, 0);
			label.setBackgroundColor(0xffffd700);
			break;
		case 2048:
			label.setPadding(10, 10, 0, 0);
			label.setBackgroundColor(0xff8b0000);
			break;
		case 4096:
			label.setPadding(10, 10, 0, 0);
			label.setBackgroundColor(0xffa9a9a9);
			break;
		case 8192:
			label.setPadding(10, 10, 0, 0);
			label.setBackgroundColor(0xff000000);
			break;
		}
	}

}
