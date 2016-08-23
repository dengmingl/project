package com.example.suiji;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView tvname;
	private Button  btnStart;
	private Button  btnStop;
	private List<Contact> contacts;
	private InnerThread thread;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		data();
		tvname=(TextView)findViewById(R.id.tv_name);
		btnStart=(Button)findViewById(R.id.bt_start);
		btnStop=(Button)findViewById(R.id.bt_stop);
		InnerOnclickListener listener =new InnerOnclickListener();
		btnStart.setOnClickListener(listener);
		btnStop.setOnClickListener(listener);
		btnStart.setEnabled(true);
		btnStop.setEnabled(false);
		
		
	}
		class InnerOnclickListener implements OnClickListener{

			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.bt_start:
					
					 thread=new InnerThread();
					thread.isrunning=true;
					thread.start();
					btnStart.setEnabled(false);
					btnStop.setEnabled(true);
					break;

				case R.id.bt_stop:
					
				
					thread.isrunning=false;
					
					btnStart.setEnabled(true);
					btnStop.setEnabled(false);
					break;
				}
				
			}
			
		}
	
	
	
	
	
	
	
	
	class InnerThread extends Thread{
	 boolean isrunning;
	 int position;

		@Override
		public void run() {

			while (isrunning) {
				position=new Random().nextInt(contacts.size());
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						tvname.setText(contacts.get(position).getName());

					}
				});
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

	private void data() {
		contacts = new ArrayList<Contact>();
		contacts.add(new Contact(R.drawable.a023, "Æë´ºÃÍ", "", "qichunmeng"));
		contacts.add(new Contact(R.drawable.a002, "ÕÅ»¶", "", "zhanghuan"));
		contacts.add(new Contact(R.drawable.a001, "³ÂÃ÷½ú", "", "chenmingjin"));
		contacts.add(new Contact(R.drawable.a003, "¹ùÁÖÌÎ", "", "guolintao"));
		contacts.add(new Contact(R.drawable.a004, "öÄĞÂ¿ü", "", "quxinkui"));
		contacts.add(new Contact(R.drawable.a005, "Áõ³©", "", "liuchang"));
		contacts.add(new Contact(R.drawable.a006, "Íõ¿µ", "", "wangkang"));
		contacts.add(new Contact(R.drawable.a007, "Àî´æ", "", "licun"));
		contacts.add(new Contact(R.drawable.a008, "¹¨ºê±ò", "", "gonghongbin"));
		contacts.add(new Contact(R.drawable.a009, "Àî¼Î³Ì", "", "lijiacheng"));
		contacts.add(new Contact(R.drawable.a010, "¸ß×Óğ©", "", "gaozihao"));
		contacts.add(new Contact(R.drawable.a005, "Íõè¡", "", "wangchen"));
		contacts.add(new Contact(R.drawable.a012, "°×ÎÄÏ×", "", "baiwenxian"));
		contacts.add(new Contact(R.drawable.a008, "¶­´«Áú", "", "dongchuanlong"));
		contacts.add(new Contact(R.drawable.a007, "»ÆºìÓî", "", "huanghongyu"));
		contacts.add(new Contact(R.drawable.a006, "½ªÎ°", "", "jiangwei"));
		contacts.add(new Contact(R.drawable.a005, "ÍôÎ°", "", "wangwei"));
		contacts.add(new Contact(R.drawable.a004, "Àî·ò¾ü", "", "lifujun"));
		contacts.add(new Contact(R.drawable.a003, "¶¡Àû½£", "", "dinglijian"));
		contacts.add(new Contact(R.drawable.a002, "ÖìÏæçù", "", "zhuxiangqi"));
		contacts.add(new Contact(R.drawable.a001, "Àî»áÔó", "", "lihuize"));
		contacts.add(new Contact(R.drawable.a023, "¶Î¸ê", "", "duange"));
		contacts.add(new Contact(R.drawable.a022, "ÑîÔöÃ÷", "", "yangzengming"));
		contacts.add(new Contact(R.drawable.a021, "¶­Çìºê", "", "dongqinghong"));
		contacts.add(new Contact(R.drawable.a020, "ÀîÊÀìÏ", "", "lishiyu"));
		contacts.add(new Contact(R.drawable.a019, "ÎâµÏ", "", "wudi"));
		contacts.add(new Contact(R.drawable.a018, "ÑîÔÃ", "", "yangyue"));
		contacts.add(new Contact(R.drawable.a017, "Ö£ĞÇÓñ", "", "zhengxingyu"));
		contacts.add(new Contact(R.drawable.a016, "²ÜÉÆÒæ", "", "caoshanyi"));
		contacts.add(new Contact(R.drawable.a015, "Áõæç", "", "liuxiao"));
		contacts.add(new Contact(R.drawable.a014, "ÍõÒøÌÎ", "", "wangyintao"));
		contacts.add(new Contact(R.drawable.a013, "Â¬´«Áú", "", "luchuanlong"));
		contacts.add(new Contact(R.drawable.a012, "Éê×ÓÎÄ", "", "shenziwen"));
		contacts.add(new Contact(R.drawable.a011, "ÎâÊÀ²ı", "", "wushichang"));
		contacts.add(new Contact(R.drawable.a010, "¶­¹ú»ª", "", "dongguohua"));
		contacts.add(new Contact(R.drawable.a009, "½¯ÏşÃ·", "", "jiangxiaomei"));
		contacts.add(new Contact(R.drawable.a008, "Ñ¦¼ÑÔª", "", "xuejiayuan"));
		contacts.add(new Contact(R.drawable.a007, "Ö£ºãĞÇ", "", "zhenghengxing"));
		contacts.add(new Contact(R.drawable.a006, "ÍõÓ¢½¡", "", "wangyingjian"));
		contacts.add(new Contact(R.drawable.a005, "ËÎ·å", "", "songfeng"));
		contacts.add(new Contact(R.drawable.a004, "»Æº£", "", "huanghai"));
		contacts.add(new Contact(R.drawable.a003, "Ô·ÇàÁÖ", "", "yuanqinlin"));
		contacts.add(new Contact(R.drawable.a002, "³ÂºñÈ¨", "", "chenhouquan"));
		contacts.add(new Contact(R.drawable.a001, "ÍòÓê", "", "wanyu"));
	}




}
