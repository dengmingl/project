package com.dml.mediaplay;


public class Music {
	private String title;
	private String path;
	private boolean isPlaying;
	

	
	public boolean isPlaying() {
		return isPlaying;
	}

	public void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}

	public String getTitle() {
		return title;
	}

	public void setPath(String path) {
		this.path =path;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPath() {
		return path;
	}
	@Override
	public String toString() {
		return "Music [title=" + title + ", path=" + path + "]";
	}
}
