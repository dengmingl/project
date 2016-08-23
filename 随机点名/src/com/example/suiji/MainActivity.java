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
		contacts.add(new Contact(R.drawable.a023, "�봺��", "", "qichunmeng"));
		contacts.add(new Contact(R.drawable.a002, "�Ż�", "", "zhanghuan"));
		contacts.add(new Contact(R.drawable.a001, "������", "", "chenmingjin"));
		contacts.add(new Contact(R.drawable.a003, "������", "", "guolintao"));
		contacts.add(new Contact(R.drawable.a004, "���¿�", "", "quxinkui"));
		contacts.add(new Contact(R.drawable.a005, "����", "", "liuchang"));
		contacts.add(new Contact(R.drawable.a006, "����", "", "wangkang"));
		contacts.add(new Contact(R.drawable.a007, "���", "", "licun"));
		contacts.add(new Contact(R.drawable.a008, "�����", "", "gonghongbin"));
		contacts.add(new Contact(R.drawable.a009, "��γ�", "", "lijiacheng"));
		contacts.add(new Contact(R.drawable.a010, "�����", "", "gaozihao"));
		contacts.add(new Contact(R.drawable.a005, "���", "", "wangchen"));
		contacts.add(new Contact(R.drawable.a012, "������", "", "baiwenxian"));
		contacts.add(new Contact(R.drawable.a008, "������", "", "dongchuanlong"));
		contacts.add(new Contact(R.drawable.a007, "�ƺ���", "", "huanghongyu"));
		contacts.add(new Contact(R.drawable.a006, "��ΰ", "", "jiangwei"));
		contacts.add(new Contact(R.drawable.a005, "��ΰ", "", "wangwei"));
		contacts.add(new Contact(R.drawable.a004, "����", "", "lifujun"));
		contacts.add(new Contact(R.drawable.a003, "������", "", "dinglijian"));
		contacts.add(new Contact(R.drawable.a002, "������", "", "zhuxiangqi"));
		contacts.add(new Contact(R.drawable.a001, "�����", "", "lihuize"));
		contacts.add(new Contact(R.drawable.a023, "�θ�", "", "duange"));
		contacts.add(new Contact(R.drawable.a022, "������", "", "yangzengming"));
		contacts.add(new Contact(R.drawable.a021, "�����", "", "dongqinghong"));
		contacts.add(new Contact(R.drawable.a020, "������", "", "lishiyu"));
		contacts.add(new Contact(R.drawable.a019, "���", "", "wudi"));
		contacts.add(new Contact(R.drawable.a018, "����", "", "yangyue"));
		contacts.add(new Contact(R.drawable.a017, "֣����", "", "zhengxingyu"));
		contacts.add(new Contact(R.drawable.a016, "������", "", "caoshanyi"));
		contacts.add(new Contact(R.drawable.a015, "����", "", "liuxiao"));
		contacts.add(new Contact(R.drawable.a014, "������", "", "wangyintao"));
		contacts.add(new Contact(R.drawable.a013, "¬����", "", "luchuanlong"));
		contacts.add(new Contact(R.drawable.a012, "������", "", "shenziwen"));
		contacts.add(new Contact(R.drawable.a011, "������", "", "wushichang"));
		contacts.add(new Contact(R.drawable.a010, "������", "", "dongguohua"));
		contacts.add(new Contact(R.drawable.a009, "����÷", "", "jiangxiaomei"));
		contacts.add(new Contact(R.drawable.a008, "Ѧ��Ԫ", "", "xuejiayuan"));
		contacts.add(new Contact(R.drawable.a007, "֣����", "", "zhenghengxing"));
		contacts.add(new Contact(R.drawable.a006, "��Ӣ��", "", "wangyingjian"));
		contacts.add(new Contact(R.drawable.a005, "�η�", "", "songfeng"));
		contacts.add(new Contact(R.drawable.a004, "�ƺ�", "", "huanghai"));
		contacts.add(new Contact(R.drawable.a003, "Է����", "", "yuanqinlin"));
		contacts.add(new Contact(R.drawable.a002, "�º�Ȩ", "", "chenhouquan"));
		contacts.add(new Contact(R.drawable.a001, "����", "", "wanyu"));
	}




}
