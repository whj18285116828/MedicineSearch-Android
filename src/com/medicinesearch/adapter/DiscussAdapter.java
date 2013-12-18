package com.medicinesearch.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.medicinesearch.activity.DiscussActivity;
import com.medicinesearch.activity.ReplyActivity;
import com.medicinesearch.database.DatabaseOpenHelper;
import com.medicinesearch_android.R;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

final class DiscussViewHolder
{
	public TextView name;

	public TextView time;

	public TextView content;

	public Button reply;
}

public class DiscussAdapter extends BaseAdapter
{

	private LayoutInflater mInflater;

	private DatabaseOpenHelper helper;

	private List<Map<String, Object>> mData;

	private Context context;

	private Intent intent;

	private String[] columns = new String[]
	{ "Uno", "Dtime", "Dcontent" };

	public DiscussAdapter(Context context)
	{
		this.context = context;
		this.mInflater = LayoutInflater.from(context);
		helper = new DatabaseOpenHelper(context);
		intent = new Intent();
		mData = getData();
	}

	private List<Map<String, Object>> getData()
	{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map;
		Cursor result = helper.query("Discuss", columns, null, null, null,
				null, null);
		System.out.println(result.getCount());
		result.moveToFirst();
		while (!result.isAfterLast())
		{
			map = new HashMap<String, Object>();
			System.out.println(getName(result.getString(0)));
			map.put("name", getName(result.getString(0)));
			map.put("time", result.getString(1));
			map.put("content", result.getString(2));
			list.add(map);
			// do something useful with these
			result.moveToNext();
		}
		result.close();

		return list;
	}

	private String getName(String uno)
	{
		Cursor result = helper.query("User", new String[]
		{ "Unickname" }, "Uno=?", new String[]
		{ uno }, null, null, null);
		result.moveToFirst();
		String name = result.getString(0);
		result.close();
		return name;
	}

	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return mData.size();
	}

	@Override
	public Object getItem(int arg0)
	{
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public long getItemId(int arg0)
	{
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		// TODO Auto-generated method stub
		DiscussViewHolder holder = null;
		if (convertView == null)
		{
			holder = new DiscussViewHolder();
			convertView = mInflater.inflate(R.layout.listitem_discuss, null);
			holder.name = (TextView) convertView
					.findViewById(R.id.discussitem_name);
			holder.reply = (Button) convertView
					.findViewById(R.id.discussitem_reply);
			holder.time = (TextView) convertView
					.findViewById(R.id.discussitem_time);
			holder.content = (TextView) convertView
					.findViewById(R.id.discussitem_content);
			convertView.setTag(holder);
		}
		else
		{
			holder = (DiscussViewHolder) convertView.getTag();
		}

		holder.name.setText((String) mData.get(position).get("name"));
		holder.content.setText((String) mData.get(position).get("content"));
		holder.time.setText((String)mData.get(position).get("time"));
		
		holder.reply.setOnClickListener(new MyOnclickListener(position));

		return convertView;
	}
	
	class MyOnclickListener implements OnClickListener{
		private int position;
		
		public MyOnclickListener(int position){
			this.position = position;
		}
		@Override
		public void onClick(View arg0)
		{
			// TODO Auto-generated method stub
			intent.setClass(context, ReplyActivity.class);
			String preName = ((String) mData.get(position).get("name"));
			String preTime = ((String) mData.get(position).get("time"));
			String preContent = ((String) mData.get(position).get("content"));
			intent.putExtra("preName", preName);
			intent.putExtra("preTime", preTime);
			intent.putExtra("preContent",preContent);
			context.startActivity(intent);
		}
		
	}

}
