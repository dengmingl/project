package com.deng.listofcontacts;

import java.util.List;
import java.util.Locale;
import java.util.TreeSet;

import javax.xml.namespace.QName;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;

public class ContactsAdapter extends BaseAdapter implements SectionIndexer{
	private Context context;
	private List<Contact>contacts;



	public ContactsAdapter(List<Contact> contacts,Context context) {
		this.contacts=contacts;
		this.context=context;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return contacts.size();
	}


	@Override
	public View getView(int position, View concerView, ViewGroup parent) {

		Contact contact=contacts.get(position);

		if (concerView ==null) {


			LayoutInflater inflater =(LayoutInflater)context.getSystemService
					(context.LAYOUT_INFLATER_SERVICE);

			concerView =inflater.inflate(R.layout.list_item_contacts, null);
		}

		TextView tvName =(TextView)concerView.findViewById(R.id.tv_contast_name);
		ImageView imTitle =(ImageView)concerView.findViewById(R.id.iv_title_image);
		TextView tv_sort = (TextView)concerView.findViewById(R.id.tv_contact_sort);


		//		if (position==0) {
		//			tv_sort.setVisibility(View.VISIBLE);
		//	}else {
		//			char lastSection = contacts.get(position-1).getPinyin().charAt(0);
		//			char thisSelection=contacts.get(position).getPinyin().charAt(0);
		//			if (lastSection==thisSelection) {
		//				tv_sort.setVisibility(View.GONE);
		//			}else {
		//				tv_sort.setVisibility(View.VISIBLE);
		//			}
		//		} 
		if (position==getPositionForSection(getSectionForPosition(position))) {
			tv_sort.setVisibility(View.VISIBLE);
		}else {
			tv_sort.setVisibility(View.GONE);

		}


		tv_sort.setText(""+contact.getPinyin().toUpperCase(Locale.CHINA).charAt(0));
		tvName.setText(contact.getName());
		imTitle.setImageResource(contact.getImageView());

		return concerView;
	}



	@Override
	public int getSectionForPosition(int position) {
		return contacts.get(position).getPinyin().toUpperCase().charAt(0);	
	}

	@Override
	public int getPositionForSection(int section) {
		for (int i = 0; i < contacts.size(); i++) {
			if (getSectionForPosition(i)==section) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Object[] getSections() {
		
		TreeSet<String> strings =new TreeSet<String>();
		for (int i = 0; i < contacts.size(); i++) {
			strings.add((char)getSectionForPosition(i)+"");
		}
		String[] sections =new String[strings.size()];
		int i=0;   
		for (String str : strings) {
			sections[i]=str;
			i++;
		}
		return sections;
	}
}
