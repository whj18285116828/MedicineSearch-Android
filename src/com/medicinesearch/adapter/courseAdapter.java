package com.medicinesearch.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import android.R.string;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

final class course_coutViewHolder {
	public TextView name;
	public Button download;
}

public class courseAdapter extends BaseAdapter{
	private LayoutInflater mInflater;
	

	private List<Map<String, Object>> mData;


	private Context context;
	
	public courseAdapter(Context context) {
		this.context = context;
		this.mInflater = LayoutInflater.from(context);
		mData = getData();
	}

	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "人参");
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("name", "人参");
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("name", "人参");
		list.add(map);
		
		
		map = new HashMap<String, Object>();
		map.put("name", "人参");
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
		course_coutViewHolder holder = null;
		if(convertView == null){
			holder = new course_coutViewHolder();
			convertView = mInflater.inflate(null, null);
			holder.name = (TextView)convertView.findViewById(0);
			holder.download = (Button)convertView.findViewById(0);
			convertView.setTag(holder);
		}
		else
		{
			holder = (course_coutViewHolder)convertView.getTag();
		}
		holder.name.setText(mData.get(position).get("name").toString());
		holder.download.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d(null, null);
				
			}
		});
		return convertView;
	}

}
