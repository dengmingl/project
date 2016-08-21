package com.dml.mediaplay;

import java.util.List;

import android.R.integer;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MusicAdapter extends BaseAdapter {

	private List<Music> musics;
	private Context context;
	private final static int SELECTED =1;
	private final static int UNSELECTED =0;
	//定义构造方法
	public MusicAdapter(List<Music> musics,Context context) {
		this.musics=musics;
		this.context=context;
	}

	/**
	 * 获取显示列表对象的个数
	 */
	@Override
	public int getCount() {
		return musics.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//[1]
		Music music=musics.get(position);
		//[2]
		if (convertView==null) {
			LayoutInflater inflater =LayoutInflater.from(context);
			if (getItemViewType(position)==1) {
				convertView=inflater.inflate(R.layout.list_item_music_selected, null);
			}else {
				convertView = inflater.inflate(R.layout.list_item_music, null);
			}
		}
		//[3]
		TextView tv_title =(TextView)convertView.findViewById(R.id.tv_music_item_title);
		TextView tv_music =(TextView)convertView.findViewById(R.id.tv_music_item_list);
		//[4]
		tv_title.setText(music.getTitle());
		tv_music.setText(music.getPath());
		//[5]
		return convertView;
	}

	@Override
	public int getItemViewType(int position) {
		return musics.get(position).isPlaying()?SELECTED:UNSELECTED;
	}
	/*
	 * 返回列表的个数【当前布局中存有两个列表，所以返回2进行标识，
	 * 否则会默认只有一个列表，则对另一个列表不做处理】
	 * (non-Javadoc)
	 * @see android.widget.BaseAdapter#getViewTypeCount()
	 */
	@Override
	public int getViewTypeCount() {
		return 2;
	}
	@Override
	public Object getItem(int arg0) {
		return null;
	}
	@Override
	public long getItemId(int arg0) {
		return 0;
	}
}
