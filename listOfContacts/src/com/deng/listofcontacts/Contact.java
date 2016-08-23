package com.deng.listofcontacts;

import android.media.Image;
import android.widget.ImageView;

public class Contact implements Comparable<Contact>{
	private String name;
	private int imageView;
	private String pinyin;
	
	
	
	public Contact() {
		super();
	}



	public Contact(String name, int imageView, String pinyin) {
		super();
		this.name = name;
		this.imageView = imageView;
		this.pinyin = pinyin;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getImageView() {
		return imageView;
	}



	public void setImageView(int imageView) {
		this.imageView = imageView;
	}



	public String getPinyin() {
		return pinyin;
	}



	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}



	@Override
	public int compareTo(Contact contact2) {
		return this.pinyin.compareToIgnoreCase(contact2.pinyin);
	}
	
	

	
}
