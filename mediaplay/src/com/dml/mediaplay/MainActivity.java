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
	private ImageButton ib_play_pause;   //���Ż���ͣ
	private ImageButton ibPreviou;			//��һ��
	private ImageButton ibNext;		//��һ��
	private ImageButton ib_mode;
	private ListView lv_music;              //�б�ؼ�
	private List<Music> musics;			// ���ص�����Դ abcdefghijklnb
	private MusicAdapter adapter;       //����������
	private MediaPlayer player;         //������
	private int pausePosition;           //��ͣ�ڵ�
	private int currentMusicIndex;        //��ǰ��������
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
	 * ������������Դ��ArraysList<T>����object    TΪ¼�������Դ��ʽ���˴�ΪMusic�࣬objectΪmusics
	 * 		if   sdcard����
	 * 			  ȷ����Ҫ���豸�����õ��ļ���
	 * 			 if   Ŀ¼����
	 * 				 ��ȡ��Ŀ¼�µ������ļ�����������ʽ����
	 *              if  ��������ڴ��п������ݣ���Ϊnull��
	 *              	for �������е�����
	 *              		if ���ļ������ݿ���ΪĿ¼�������Ŀ¼���򲻶�����в�����
	 *              			if �ǲ�������Ҫ�ĸ�ʽ���ļ����Ϳɷ�������ʽ������ΪMP3��ʽ��
	 *              				ʵ������Ӧ�Ķ��󲢴������ݷ�װ
	 *              					�˴�Ϊʵ����Music�����music������title��path���Խ��з�װ
	 *              					�������������������Ϊ���ݴ���
	 *    
	 * 				
	 * 			
	 */
	private void loadData() {
		musics=new ArrayList<Music>();
		//��Media_MOUTEDΪ������mounted��
		//	getExternalStorageState()����ȡ�豸��ǰ״̬
		//	|���ã�����mouted  
		//    |�����ã�����쳣��Ϣ "Failed to read external storage state; ����MEDIA_REMOVED
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
			//getExternalStoragePublicDirectory(String type) ��ȡ��ǰָ�������µ��ļ�Ŀ¼����������Ӧ��
			//Ŀ¼·������ע�⣺�����Ŀ¼����δ���豸�ϴ�����������ʹ�����Ŀ¼ǰ��Ҫȷ����Ŀ¼�ѱ�����
			//����ϰ����File ���Է�װ�ļ�·����Ŀ¼·���������ڵ�·��
			File musicDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);		
			//������ʹ�����Ŀ¼ǰ��Ҫȷ����Ŀ¼�ѱ�����
			if (musicDir.exists()) {
				//listFiles()  ���������ʽ���ظ�Ŀ¼�µ������ļ��������Ŀ¼��û���ļ����򷵻�null
				File[] files =musicDir.listFiles();
				//�����ʡ������files���鲻Ϊnull�����䳤�Ȳ�Ӧ����>0����
				//���files����Ϊnull�����䳤��ҲӦ�ô���0�ˡ���ô��Ϊ��Ҫ�жϳ��ȴ���0�أ�
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
			//musics�����е�currentMudicIndexλ���ļ����̷�
			player.setDataSource(musics.get(currentMusicIndex).getPath());
			player.prepare();
			player.seekTo(pausePosition);
			player.start();
			ib_play_pause.setImageResource(R.drawable.selector_pause_music);
			/*	��ɡ���ǰ���š���ʶ���� 
			 * ��1������music��������,����isplay״̬��Ϊfalse��������list_item_music�б��б�ʶ
			 * ��2������ǰ������ָ�����ŵ����ֵ�isplay״̬��Ϊture����list_item_music�б��б�ʶ����
			 */
			for (int i = 0; i < musics.size(); i++) {
				musics.get(i).setPlaying(false);
			}
			musics.get(currentMusicIndex).setPlaying(true);


			//�������ã���observices˵�������ݽ����˱䶯������ˢ�����ݣ������µ����ݣ�
			//���Ʋ⡿�������������Ҫ���music�Ĳ���ʱ������ʱ������Ҫ�ڴ�֮ǰ�����ݽ��д����������ݲ�����£���
			adapter.notifyDataSetChanged();
			//���б�ָ��ƽ���ػ���ָ����λ��
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
			// �����������������Ҫ���ŵĸ���������
			int i = new Random().nextInt(musics.size());
			// �жϸ��������Ƿ����1�������û�б�Ҫ��������
			if (musics.size() > 1) {
				// ������ɵ���������ǵ�ǰ�����������򷴸����ɣ�ֱ�����ɲ���ͬ������
				while (i == currentMusicIndex) {
					i = new Random().nextInt(musics.size());
				}
			}
			// �������������Ϊ�������ŵĸ���������
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
