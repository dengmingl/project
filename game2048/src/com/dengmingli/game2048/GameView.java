package com.dengmingli.game2048;


import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

public class GameView extends GridLayout{

	private Card[][] cardsmap =new Card[4][4];
	private List<Point> enpoints =new ArrayList<Point>();


	public GameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initGameView();
	}
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initGameView();
	}
	public GameView(Context context) {
		super(context);
		initGameView();
	}

	private void initGameView() {
		setColumnCount(4);
		setBackgroundColor(0xffbbaba0);
		//手势操作实现  setonTouchListener
		/*
		 * 通过设置监听器的方式，对触屏移动方向进行判断
		 */
		setOnTouchListener(new OnTouchListener() {

			private float startX,startY,offsetX,offsetY;

			@Override
			public boolean onTouch(View view, MotionEvent event) {
				/*
				 * 具体的实现方式：判断用户手指按下的位置和用户手指离开的位置
				 */
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					startX = event.getX();
					startY = event.getY();
					break;
				case MotionEvent.ACTION_UP:
					offsetX = event.getX()-startX;
					offsetY= event.getY()-startY;

					if (Math.abs(offsetX)>Math.abs(offsetY)) {

						if (offsetX<-5) {

							swipeLeft();
							System.out.println("left");
						}else if (offsetX > 5) {

							swipeRight();
							System.out.println("right");
						}}
					else {
						if (offsetY<-5) {
							swipeUp();
							System.out.println("up");
						}else if(offsetY>5){
							swipeDown();
							System.out.println("down");
						}
					}

					break;

				}

				return true;
			}
		});
	}


	//调整宽高
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		//卡片宽高
		int cardWidth= (Math.min(w, h)-10)/4;
		addCard(cardWidth,cardWidth);
		startGame();
	}
	private void addCard(int CardWidth,int CardHeight) {
		Card card;
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				card=new Card(getContext());
				card.setNum(0);
				addView(card,CardWidth,CardHeight);
				cardsmap[x][y] =card;
			}



		}
	}

	private void startGame() {
		MainActivity.getMainActivity().clearScore();
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				cardsmap[x][y].setNum(0);
			}
		}


		addRundomNum();
		addRundomNum();



	}





	private void addRundomNum(){
		enpoints.clear();

		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				if (cardsmap[x][y].getNum()<=0) {
					enpoints.add(new Point(x, y));
				}

			}

		}
		Point p=enpoints.remove((int)(Math.random()*enpoints.size()));
		cardsmap[p.x][p.y].setNum(Math.random()>0.1?2:4);

	}



	private void swipeRight(){

		boolean merge=false;
		for (int y = 0; y <4; y++) {
			for (int x = 3; x >=0; x--) {
				for (int x1 = x-1; x1 >=0; x1--) {
					if (cardsmap[x1][y].getNum()>0) {
						if (cardsmap[x][y].getNum()<=0) {
							cardsmap[x][y].setNum(cardsmap[x1][y].getNum());
							cardsmap[x1][y].setNum(0);
							x++;
							merge=true;
						}else if (cardsmap[x][y].equals(cardsmap[x1][y].getNum())) {
							cardsmap[x][y].setNum(cardsmap[x][y].getNum()*2);
							cardsmap[x1][y].setNum(0);
							MainActivity.getMainActivity().addScore(cardsmap[x][y].getNum());
							merge=true;
						}

						break;
					}
				}
			}
		}
		if (merge) {
			addRundomNum();
			isfinish();

		}

	}
	private void swipeLeft(){
		boolean merge=false;
		for (int y = 0; y <4; y++) {
			for (int x = 0; x < 4; x++) {

				for (int x1 = x+1; x1 < 4; x1++) {
					if (cardsmap[x1][y].getNum()>0) {
						if (cardsmap[x][y].getNum()<=0) {
							cardsmap[x][y].setNum(cardsmap[x1][y].getNum());
							cardsmap[x1][y].setNum(0);
							x--;

							merge=true;
						}else if (cardsmap[x][y].equals(cardsmap[x1][y])) {
							cardsmap[x][y].setNum(cardsmap[x][y].getNum()*2);
							cardsmap[x1][y].setNum(0);
							MainActivity.getMainActivity().addScore(cardsmap[x][y].getNum());
							merge=true;
						}

						break;
					}
				}
			}
		}
		if (merge) {
			addRundomNum();
			isfinish();

		}
	}
	private void swipeDown(){
		boolean merge=false;
		for (int x = 0; x <4; x++) {
			for (int y = 3; y >=0; y--) {

				for (int y1 = y-1; y1 >=0; y1--) {
					if (cardsmap[x][y1].getNum()>0) {
						if (cardsmap[x][y].getNum()<=0) {
							cardsmap[x][y].setNum(cardsmap[x][y1].getNum());
							cardsmap[x][y1].setNum(0);
							y++;
							merge=true;
						}else if (cardsmap[x][y].equals(cardsmap[x][y1])) {
							cardsmap[x][y].setNum(cardsmap[x][y].getNum()*2);
							cardsmap[x][y1].setNum(0);
							MainActivity.getMainActivity().addScore(cardsmap[x][y].getNum());
							merge=true;
						}

						break;
					}
				}
			}
		}if (merge) {
			addRundomNum();
			isfinish();

		}

	}

	private void swipeUp(){
		boolean merge=false;
		for (int x = 0; x <4; x++) {
			for (int y = 0; y < 4; y++) {

				for (int y1 = y+1; y1 < 4; y1++) {
					if (cardsmap[x][y1].getNum()>0) {
						if (cardsmap[x][y].getNum()<=0) {
							cardsmap[x][y].setNum(cardsmap[x][y1].getNum());
							cardsmap[x][y1].setNum(0);
							y--;
							merge=true;
						}else if (cardsmap[x][y].equals(cardsmap[x][y1])) {
							cardsmap[x][y].setNum(cardsmap[x][y].getNum()*2);
							cardsmap[x][y1].setNum(0);
							MainActivity.getMainActivity().addScore(cardsmap[x][y].getNum());
							merge=true;
						}

						break;
					}
				}
			}
		}if (merge) {
			addRundomNum();
			isfinish();

		}
	}


	private void isfinish() {
		boolean gameOver =true;
		ALL:
			for (int y = 0; y <4; y++) {
				for (int x = 0; x <4; x++) {
					if (cardsmap[x][y].getNum()==0||
							(x>0&&cardsmap[x][y].equals(cardsmap[x-1][y]))||
							(x<3&&cardsmap[x][y].equals(cardsmap[x+1][y]))||
							(y>0&&cardsmap[x][y].equals(cardsmap[x][y-1]))||
							(y<3&&cardsmap[x][y].equals(cardsmap[x][y+1]))) {
						gameOver=false;
						break ALL;
					}
				}
			}
		if (gameOver) {

			new AlertDialog.Builder(getContext()).setTitle("你好").setMessage("游戏结束").setPositiveButton("重来",new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					startGame();
					
				}
			}).show(); 
	}








	}

}









