package com.medicinesearch.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.medicinesearch.database.DatabaseOpenHelper;
import com.medicinesearch_android.R;

final class View_Holder {
	public ImageView picture;

	public TextView name;

	public TextView category;

	public TextView taste;

}

public class CollectionAdapater extends BaseAdapter {

	private LayoutInflater mInflater;

	private DatabaseOpenHelper helper;

	private List<Map<String, Object>> mData;

	private Context context;

	private List<String> selectionArgs;
	private String selection = "Mno=?";

	String[] columns = new String[] { "Mname", "Mcategory", "Mtaste" };

	public CollectionAdapater(Context context, List<String> selectionArgs) {

		this.context = context;
		this.selectionArgs = new ArrayList<String>();
		this.mInflater = LayoutInflater.from(context);
		this.selectionArgs = selectionArgs;
		System.out.println(this.selectionArgs.size());
		helper = new DatabaseOpenHelper(context);
		mData = getData();
	}

	public boolean isDataExisted() {
		if (mData.size() == 0)
			return false;
		else
			return true;
	}

	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map;
		int leg = selectionArgs.size();
		int c = 0;
		while (c < leg) {
			String s = selectionArgs.get(c);
			Cursor result = helper.query("MedicinalHerbs", columns, selection,
					new String[] { s }, null, null, null);
			System.out.println(result.getCount());
			result.moveToFirst();
			while (!result.isAfterLast()) {
				map = new HashMap<String, Object>();
				map.put("name", result.getString(0));
				map.put("category", result.getString(1));
				map.put("taste", result.getString(2));
				map.put("picture", R.drawable.ic_launcher);
				list.add(map);
				// do something useful with these
				result.moveToNext();
			}
			result.close();
			c++;
		}

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

		View_Holder holder = null;
		if (convertView == null) {

			holder = new View_Holder();
			convertView = mInflater.inflate(R.layout.listitem_search, null);
			holder.picture = (ImageView) convertView
					.findViewById(R.id.search_item_picture);
			holder.name = (TextView) convertView
					.findViewById(R.id.search_item_name);
			holder.category = (TextView) convertView
					.findViewById(R.id.search_item_category);
			holder.taste = (TextView) convertView
					.findViewById(R.id.search_item_taste);
			convertView.setTag(holder);

		} else {

			holder = (View_Holder) convertView.getTag();
		}

		holder.picture.setBackgroundResource((Integer) mData.get(position).get(
				"picture"));
		holder.name.setText((String) mData.get(position).get("name"));
		holder.category.setText((String) mData.get(position).get("category"));
		holder.taste.setText((String) mData.get(position).get("taste"));

		holder.picture.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(context, "Äãµã»÷ÁËÍ¼Æ¬", Toast.LENGTH_SHORT).show();
			}
		});

		return convertView;
	}

}
