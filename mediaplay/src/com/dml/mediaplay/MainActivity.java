package com.dml.mediaplay;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import android.R.integer;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity {
	private ImageButton ib_play_pause;   //播放或暂停
	private ImageButton ibPreviou;			//上一首
	private ImageButton ibNext;		//下一首
	private ImageButton ib_mode;
	private ListView lv_music;              //列表控件
	private List<Music> musics;			// 加载的数据源 abcdefghijklnb
	private MusicAdapter adapter;       //音乐适配器
	private MediaPlayer player;         //播放器
	private int pausePosition;           //暂停节点
	private int currentMusicIndex;        //当前音乐索引
	private static int mode=0;
	private TextView tv_music_currentposition;
	private TextView tv_music_duration;
	private SeekBar sb_music_length;
	private static final int REPEATED=0;
	private static final int RANDOM=1;
	private static final int SINGLE=2;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		player=new MediaPlayer();
		loadData();
		setViews();
		setListeners();
		setAadpter();


	}

	/*
	 * 创建承载数据源的ArraysList<T>对象object    T为录入的数据源格式，此处为Music类，object为musics
	 * 		if   sdcard可用
	 * 			  确定需要在设备上适用的文件夹
	 * 			 if   目录存在
	 * 				 获取该目录下的所有文件，以数组形式保存
	 *              if  如果数组内存有可用数据（不为null）
	 *              	for 遍历所有的数据
	 *              		if 是文件（数据可能为目录，如果是目录，则不对其进行操作）
	 *              			if 是不是所需要的格式（文件类型可分其他格式，本例为MP3格式）
	 *              				实例化相应的对象并传递数据封装
	 *              					此处为实例化Music类对象music。对其title和path属性进行封装
	 *              					将对象添加至集合中作为数据存在
	 *    
	 * 				
	 * 			
	 */
	private void loadData() {
		musics=new ArrayList<Music>();
		//（Media_MOUTED为常量，mounted）
		//	getExternalStorageState()，获取设备当前状态
		//	|可用，返回mouted  
		//    |不可用，输出异常信息 "Failed to read external storage state; 返回MEDIA_REMOVED
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
			//getExternalStoragePublicDirectory(String type) 获取当前指定类型下的文件目录，并返回相应的
			//目录路径，【注意：】这个目录可能未在设备上创建，所以在使用这个目录前需要确保该目录已被创建
			//【复习】：File 可以封装文件路径、目录路径、不存在的路径
			File musicDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);		
			//所以在使用这个目录前需要确保该目录已被创建
			if (musicDir.exists()) {
				//listFiles()  以数组的形式返回该目录下的所有文件，如果该目录下没有文件，则返回null
				File[] files =musicDir.listFiles();
				//【提问】：如果files数组不为null，则其长度不应该是>0了吗？
				//如果files数组为null，则其长度也应该大于0了。那么，为何要判断长度大于0呢？
				if (files!=null&&files.length>0) {

					for (int i = 0; i < files.length; i++) {

						if (files[i].isFile()) {

							String fileName = files[i].getName();
							if(fileName.toUpperCase().endsWith(".MP3")){
								Music music =new Music();
								music.setTitle(fileName.substring(0, fileName.length() - 4));
								music.setPath(files[i].getAbsolutePath());
								musics.add(music);
							}
						}
					}
				}
			}
			
		}

	}
	private void setViews() {
		ibNext=(ImageButton)findViewById(R.id.ib_next);
		ibPreviou=(ImageButton)findViewById(R.id.ib_previous);
		ib_play_pause=(ImageButton)findViewById(R.id.ib_play_or_pause);
		lv_music=(ListView)findViewById(R.id.lv_music_list);
		tv_music_currentposition=(TextView)findViewById(R.id.tv_now_play_time);
		tv_music_duration=(TextView)findViewById(R.id.tv_song_title_time);
		sb_music_length=(SeekBar)findViewById(R.id.sb_song_playing);
		ib_mode=(ImageButton)findViewById(R.id.ib_mode);


	}
	private void setListeners() {
		InnerOnClickListener listener =new InnerOnClickListener();
		ib_play_pause.setOnClickListener(listener);
		ibNext.setOnClickListener(listener);
		ibPreviou.setOnClickListener(listener);
		ib_mode.setOnClickListener(listener);
		lv_music.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				play(position);
				boolean isRunningApp=false;

			}
		});

		player.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer arg0) {
				playNext();
			}
		});

		sb_music_length.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

				seekplay(sb_music_length.getProgress());
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {


			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {



			}
		});


	}


	private void setAadpter() {
		adapter=new MusicAdapter(musics,this);
		lv_music.setAdapter(adapter);
	}

	private class InnerOnClickListener implements OnClickListener{
		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.ib_play_or_pause:
				if (player.isPlaying()) {
					pause();
				}else {
					play();
				}
				break;

			case R.id.ib_previous:
				playPrevious();
				break;
			case R.id.ib_next:
				playNext();
				break;
			case R.id.ib_mode:

				int[] modes={
						R.drawable.selector_mode_repeat,
						R.drawable.selector_mode_random,
						R.drawable.selector_mode_single,
				};
				ib_mode.setImageResource(modes[mode]);
				mode++;
				mode%=modes.length;
				break;



			}
		}
	}


	private void  seekplay(int progress) {
		pausePosition=player.getDuration()*progress/100;
		play();

	}

	public void play(){

		try {
			player.reset();
			//musics集合中的currentMudicIndex位置文件的盘符
			player.setDataSource(musics.get(currentMusicIndex).getPath());
			player.prepare();
			player.seekTo(pausePosition);
			player.start();
			ib_play_pause.setImageResource(R.drawable.selector_pause_music);
			/*	完成【当前播放】标识步骤 
			 * 【1】遍历music对象数组,将其isplay状态设为false，即不在list_item_music列表中标识
			 * 【2】将当前索引项指定播放的音乐的isplay状态设为ture，在list_item_music列表中标识出来
			 */
			for (int i = 0; i < musics.size(); i++) {
				musics.get(i).setPlaying(false);
			}
			musics.get(currentMusicIndex).setPlaying(true);


			//更改设置（向observices说明有数据进行了变动，让其刷新数据，保存新的数据）
			//【推测】如果更改需求，需要添加music的播放时长和总时长，需要在此之前对数据进行处理，否则数据不会更新？？
			adapter.notifyDataSetChanged();
			//将列表指向平缓地滑至指定的位置
			lv_music.smoothScrollToPosition(currentMusicIndex);

			int duration=player.getDuration();
			Date date =new Date();
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("mm:ss");
			date.setTime(duration);
			tv_music_duration.setText(simpleDateFormat.format(date));
			UpdateProcessThread updateProcessThread=new UpdateProcessThread();
			updateProcessThread.start();


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void changs(int mode) {


	}



	public void pause(){
		player.pause();
		pausePosition=player.getCurrentPosition();
		ib_play_pause.setImageResource(R.drawable.selector_play_music);
	}
	private void playPrevious() {
		currentMusicIndex--;
		if (currentMusicIndex < 0) {
			currentMusicIndex =musics.size()-1;
		}
		pausePosition=0;
		play();
	}

	private void playNext() {
		switch(mode) {
		case 0:
			currentMusicIndex++;
			if (currentMusicIndex >= musics.size()) {
				currentMusicIndex = 0;
			}
			break;

		case 2:
			break;

		case 1:
			// 生成随机索引，即需要播放的歌曲的索引
			int i = new Random().nextInt(musics.size());
			// 判断歌曲数量是否大于1，否则就没有必要反复生成
			if (musics.size() > 1) {
				// 如果生成的随机数就是当前歌曲索引，则反复生成，直至生成不相同的索引
				while (i == currentMusicIndex) {
					i = new Random().nextInt(musics.size());
				}
			}
			// 将随机数字设置为即将播放的歌曲的索引
			currentMusicIndex = i;
			break;
		}
		currentMusicIndex++;
		if (currentMusicIndex >= musics.size()) {
			currentMusicIndex = 0 ;
		}
		pausePosition=0;
		play();
	}



	boolean isRunningApp=true;

	protected void play(int position) {
		if (isRunningApp||currentMusicIndex!=position) {
			currentMusicIndex=position;
			pausePosition=0;
			play();
		}
		play();

	}

	class UpdateProcessThread extends Thread{

		@Override
		public void run() {

			while(true){
				runOnUiThread( new Runnable() {
					public void run() {
						SimpleDateFormat simpleDateFormat=new SimpleDateFormat("mm:ss");
						int currentposition =player.getCurrentPosition();
						int duration =player.getDuration();
						Date date =new Date();
						int progress =currentposition*100 / duration;
						date.setTime(currentposition);
						sb_music_length.setProgress(progress);
						tv_music_currentposition.setText(simpleDateFormat.format(date));
					}
				});

				try {
					Thread.sleep(997);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}










	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
