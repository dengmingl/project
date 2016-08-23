package com.example.suiji;

public class Contact {

	private int avatarResId;
	private String name;
	private String email;
	private String pinyin;

	public Contact(int avatarResId, String name, String email, String pinyin) {
		super();
		this.avatarResId = avatarResId;
		this.name = name;
		this.email = email;
		this.pinyin = pinyin;
	}

	public int getAvatarResId() {
		return avatarResId;
	}

	public void setAvatarResId(int avatarResId) {
		this.avatarResId = avatarResId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

}
