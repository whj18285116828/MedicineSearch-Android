package com.medicinesearch.activity;

import java.util.ArrayList;
import java.util.List;

import com.medicinesearch.adapter.CollectionAdapater;
import com.medicinesearch.adapter.SearchInfoAdapter;
import com.medicinesearch.database.DatabaseOpenHelper;
import com.medicinesearch.util.StateUtil;
import com.medicinesearch_android.R;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MyCollectionActivity extends Activity
{
	private Button back = null;

	private ListView list = null;

	private CollectionAdapater adapter;

	private Intent intent = null;

	private TextView sorry = null;

	private DatabaseOpenHelper helper;

	private List<String> args;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_collection_list);

		back = (Button) findViewById(R.id.collection_list_goback);
		list = (ListView) findViewById(R.id.collection_search_list);
		sorry = (TextView) findViewById(R.id.collection_cannot_find);
		intent = new Intent();
		helper = new DatabaseOpenHelper(this);
		args = new ArrayList<String>();
		args = getList();
		if (args.size() > 1)
		{
			adapter = new CollectionAdapater(this, args);
			list.setVisibility(View.VISIBLE);
			list.setAdapter(adapter);
		}
		else
		{
			sorry.setVisibility(View.VISIBLE);
		}

		list.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3)
			{
				// TODO Auto-generated method stub
				String str = ((TextView) arg1
						.findViewById(R.id.search_item_name)).getText()
						.toString();
				intent.putExtra("name", str);
				intent.setClass(MyCollectionActivity.this,
						DetailInfoActivity.class);
				MyCollectionActivity.this.startActivity(intent);
			}
		});

		back.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				helper.close();
				MyCollectionActivity.this.finish();
			}
		});
	}

	private List<String> getList()
	{
		Cursor result = helper.query("Collection", new String[]
		{ "Mno" }, "Uno=?", new String[]
		{ StateUtil.curUser }, null, null, null);
		result.moveToFirst();
		if (result.getCount() > 0)
		{
			while (!result.isAfterLast())
			{
				args.add(result.getString(0));
				result.moveToNext();
			}
			result.close();
		}

		else
		{
			result.close();
		}
		return args;

	}
}
