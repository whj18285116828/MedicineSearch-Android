package com.medicinesearch.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.medicinesearch_android.R;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

final class sellect_course_coutViewHolder {
	public TextView name;
	
}



public class sellect_courseAdapter extends BaseAdapter{
	private LayoutInflater mInflater;
	

	private List<Map<String, Object>> mData;


	private Context context;
	
	public sellect_courseAdapter(Context context) {
		this.context = context;
		this.mInflater = LayoutInflater.from(context);
		mData = getData();
	}

	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "软件工程");
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("name", "算法设计");
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("name", "数据库");
		list.add(map);
		
		
		map = new HashMap<String, Object>();
		map.put("name", "计算机网络");
		list.add(map);
		
		
		return list;
	}

	@Override
	public int getCount() {
		
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		sellect_course_coutViewHolder holder = null;
		if(convertView == null){
			holder = new sellect_course_coutViewHolder();
			convertView = mInflater.inflate(R.layout.listitem_collect_course, null);
			holder.name = (TextView)convertView.findViewById(R.id.collect_course_name);
			
			convertView.setTag(holder);
		}
		else
		{
			holder = (sellect_course_coutViewHolder)convertView.getTag();
		}
		holder.name.setText(mData.get(position).get("name").toString());
		
		return convertView;
	}

}

