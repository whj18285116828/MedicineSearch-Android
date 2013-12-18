package com.medicinesearch.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.medicinesearch.database.DatabaseOpenHelper;
import com.medicinesearch_android.R;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

final class ReplyViewHolder
{
	public TextView title;

	public TextView time;

	public TextView content;

}

public class ReplyAdapter extends BaseAdapter
{

	private LayoutInflater mInflater;

	private DatabaseOpenHelper helper;

	private List<Map<String, Object>> mData;

	private Context context;

	private Intent intent;

	private String preName;

	private String Dno;

	private String[] columns = new String[]
	{ "Uno", "Rtime", "Rcontent" };

	public ReplyAdapter(Context context, String preName, String Dno)
	{
		this.context = context;
		this.mInflater = LayoutInflater.from(context);
		this.Dno = Dno;
		helper = new DatabaseOpenHelper(context);
		intent = new Intent();
		this.preName = preName;
		mData = getData();
	}

	private List<Map<String, Object>> getData()
	{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map;
		Cursor result = helper.query("Reply", columns, "Dno=?", new String[]
		{ Dno }, null, null, null);
		System.out.println(result.getCount());
		if (result.getCount() > 0)
		{
			result.moveToFirst();
			while (!result.isAfterLast())
			{
				map = new HashMap<String, Object>();
				System.out.println(getName(result.getString(0)));
				map.put("title", getName(result.getString(0))+"»Ø¸´"+preName);
				map.put("time", result.getString(1));
				map.put("content", result.getString(2));
				list.add(map);
				// do something useful with these
				result.moveToNext();
			}
			result.close();

			return list;
		}
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
		ReplyViewHolder holder = null;
		if (convertView == null)
		{
			holder = new ReplyViewHolder();
			convertView = mInflater.inflate(R.layout.listitem_reply, null);
			holder.title = (TextView) convertView
					.findViewById(R.id.replyitem_name);
			holder.time = (TextView) convertView
					.findViewById(R.id.replyitem_time);
			holder.content = (TextView) convertView
					.findViewById(R.id.replyitem_content);
			convertView.setTag(holder);
		}
		else
		{
			holder = (ReplyViewHolder) convertView.getTag();
		}

		holder.title.setText((String) mData.get(position).get("title"));
		holder.content.setText((String) mData.get(position).get("content"));
		holder.time.setText((String) mData.get(position).get("time"));

		return convertView;
	}

}
