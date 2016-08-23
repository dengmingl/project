package com.deng.listofcontacts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	private ListView lvsection;
	private ListView lvContacts;
	private ContactsAdapter contactAdapter;
	private ArrayAdapter<String> sectionAdapter;
	String[] sections =new String[26];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//定义控件
		lvsection=(ListView)findViewById(R.id.lv_sections);
		lvContacts =(ListView)findViewById(R.id.lv_contacts_list);
		
		//数据
		List<Contact> contacts = new ArrayList<Contact>();
		contacts.add(new Contact("安徒生",R.drawable.a001,"antusheng"));
		contacts.add(new Contact("败家仔", R.drawable.a002,"baijiazi"));
		contacts.add(new Contact("成龙", R.drawable.a003,"chenglong"));
		contacts.add(new Contact("陈虫", R.drawable.a004,"chenchong"));
		contacts.add(new Contact("邓九公", R.drawable.a005,"dengjiugong"));
		contacts.add(new Contact("王海涛",R.drawable.a006,"wanghaitao"));
		contacts.add(new Contact("成恒", R.drawable.a007,"chengheng"));
		contacts.add(new Contact("徐铭", R.drawable.a008,"xuming"));
		contacts.add(new Contact("张久军", R.drawable.a009,"zhangjiujun"));
		contacts.add(new Contact("张广", R.drawable.a010,"zhangguang"));
		contacts.add(new Contact("李莫愁",R.drawable.a011,"limochou"));
		contacts.add(new Contact("雷电", R.drawable.a012,"leidian"));
		contacts.add(new Contact("天龙", R.drawable.a013,"tianlong"));
		contacts.add(new Contact("幽灵", R.drawable.a014,"youling"));
		contacts.add(new Contact("猎狐者", R.drawable.a015,"liehuzhe"));
		contacts.add(new Contact("黄金斧头",R.drawable.a016,"huangjinfutou"));
		contacts.add(new Contact("奥特", R.drawable.a017,"aote"));
		contacts.add(new Contact("地图", R.drawable.a018,"ditu"));
		contacts.add(new Contact("闪光弹", R.drawable.a019,"shangaungdan"));
		contacts.add(new Contact("狙击枪", R.drawable.a020,"jvjiqiang"));
		contacts.add(new Contact("安徒生",R.drawable.a001,"antusheng"));
		contacts.add(new Contact("败家仔", R.drawable.a002,"baijiazi"));
		contacts.add(new Contact("成龙", R.drawable.a003,"chenglong"));
		contacts.add(new Contact("陈虫", R.drawable.a004,"chenchong"));
		contacts.add(new Contact("邓九公", R.drawable.a005,"dengjiugong"));
		contacts.add(new Contact("王海涛",R.drawable.a006,"wanghaitao"));
		contacts.add(new Contact("成恒", R.drawable.a007,"chengheng"));
		contacts.add(new Contact("徐铭", R.drawable.a008,"xuming"));
		contacts.add(new Contact("张久军", R.drawable.a009,"zhangjiujun"));
		contacts.add(new Contact("张广", R.drawable.a010,"zhangguang"));
		contacts.add(new Contact("李莫愁",R.drawable.a011,"limochou"));
		contacts.add(new Contact("雷电", R.drawable.a012,"leidian"));
		contacts.add(new Contact("天龙", R.drawable.a013,"tianlong"));
		contacts.add(new Contact("幽灵", R.drawable.a014,"youling"));
		contacts.add(new Contact("猎狐者", R.drawable.a015,"liehuzhe"));
		contacts.add(new Contact("黄金斧头",R.drawable.a016,"huangjinfutou"));
		contacts.add(new Contact("奥特", R.drawable.a017,"aote"));
		contacts.add(new Contact("地图", R.drawable.a018,"ditu"));
		contacts.add(new Contact("闪光弹", R.drawable.a019,"shangaungdan"));
		contacts.add(new Contact("狙击枪", R.drawable.a020,"jvjiqiang"));
		contacts.add(new Contact("安徒生",R.drawable.a001,"antusheng"));
		contacts.add(new Contact("败家仔", R.drawable.a002,"baijiazi"));
		contacts.add(new Contact("成龙", R.drawable.a003,"chenglong"));
		contacts.add(new Contact("陈虫", R.drawable.a004,"chenchong"));
		contacts.add(new Contact("邓九公", R.drawable.a005,"dengjiugong"));
		contacts.add(new Contact("王海涛",R.drawable.a006,"wanghaitao"));
		contacts.add(new Contact("成恒", R.drawable.a007,"chengheng"));
		contacts.add(new Contact("徐铭", R.drawable.a008,"xuming"));
		contacts.add(new Contact("张久军", R.drawable.a009,"zhangjiujun"));
		contacts.add(new Contact("张广", R.drawable.a010,"zhangguang"));
		contacts.add(new Contact("李莫愁",R.drawable.a011,"limochou"));
		contacts.add(new Contact("雷电", R.drawable.a012,"leidian"));
		contacts.add(new Contact("天龙", R.drawable.a013,"tianlong"));
		contacts.add(new Contact("幽灵", R.drawable.a014,"youling"));
		contacts.add(new Contact("猎狐者", R.drawable.a015,"liehuzhe"));
		contacts.add(new Contact("黄金斧头",R.drawable.a016,"huangjinfutou"));
		contacts.add(new Contact("奥特", R.drawable.a017,"aote"));
		contacts.add(new Contact("地图", R.drawable.a018,"ditu"));
		contacts.add(new Contact("闪光弹", R.drawable.a019,"shangaungdan"));
		contacts.add(new Contact("狙击枪", R.drawable.a020,"jvjiqiang"));
		//排序
		Collections.sort(contacts,new Comparator<Contact>(){

			@Override
			public int compare(Contact contact, Contact contact1) {
				return contact.getPinyin().compareTo(contact1.getPinyin());
			}

		});





		//添加适配器
		contactAdapter =new ContactsAdapter(contacts,this);
		sections=(String[]) contactAdapter.getSections();
		sectionAdapter= new ArrayAdapter<String>(this,R.layout.list_item_section,sections);
		
		
		lvsection.setAdapter(sectionAdapter);
		lvContacts.setAdapter(contactAdapter);

		lvsection.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				String string =sections[position];
				char ch= string.charAt(0);
				int pos=contactAdapter.getPositionForSection(ch);
				if (pos != -1) {
					// 滑动联系人的ListView到指定的位置
					lvContacts.setSelection(pos);
				}

			}
		});


	}























	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
