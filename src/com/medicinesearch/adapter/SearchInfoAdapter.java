package com.medicinesearch.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.medicinesearch_android.R;

final class ViewHolder {
	public ImageView picture;
	public TextView name;
	public TextView category;
	public TextView taste;
	
}

public class SearchInfoAdapter extends BaseAdapter{

	private LayoutInflater mInflater;
	
	private List<Map<String, Object>> mData;
	private Context context;
	
	
	public SearchInfoAdapter(Context context){
		this.context = context;
		this.mInflater = LayoutInflater.from(context);
		mData = getData();
	}
	
	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "人参");
		map.put("category", "不知道");
		map.put("taste", "没吃过");
		map.put("picture", R.drawable.ic_launcher);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("name", "人参");
		map.put("category", "不知道");
		map.put("taste", "没吃过");
		map.put("picture", R.drawable.ic_launcher);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("name", "人参");
		map.put("category", "不知道");
		map.put("taste", "没吃过");
		map.put("picture", R.drawable.ic_launcher);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("name", "人参");
		map.put("category", "不知道");
		map.put("taste", "没吃过");
		map.put("picture", R.drawable.ic_launcher);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("name", "人参");
		map.put("category", "不知道");
		map.put("taste", "没吃过");
		map.put("picture", R.drawable.ic_launcher);
		list.add(map);
		
		return list;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mData.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder = null;
		if (convertView == null) {
			
			holder=new ViewHolder();  
			
			convertView = mInflater.inflate(R.layout.listitem_search, null);
			holder.picture = (ImageView)convertView.findViewById(R.id.search_item_picture);
			holder.name = (TextView)convertView.findViewById(R.id.search_item_name);
			holder.category = (TextView)convertView.findViewById(R.id.search_item_category);
			holder.taste = (TextView)convertView.findViewById(R.id.search_item_taste);
			convertView.setTag(holder);
			
		}else {
			
			holder = (ViewHolder)convertView.getTag();
		}
		
		
		holder.picture.setBackgroundResource((Integer)mData.get(position).get("picture"));
		holder.name.setText((String)mData.get(position).get("name"));
		holder.category.setText((String)mData.get(position).get("category"));
		holder.taste.setText((String)mData.get(position).get("taste"));
		
		holder.picture.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(context, "你点击了图片",
					     Toast.LENGTH_SHORT).show();
			}
		});
		
		
		return convertView;
	}
	
}

