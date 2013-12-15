package com.medicinesearch.activity;


import com.medicinesearch.adapter.SearchInfoAdapter;
import com.medicinesearch_android.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class SearchResultActivity extends Activity
{	
	private Button back = null;
	private ListView list = null;
	private SearchInfoAdapter adapter ;
	private Intent intent = null;
	private String []selectionArgs;
	private TextView sorry = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result_list);
		
		back = (Button)findViewById(R.id.result_list_goback);
		list = (ListView)findViewById(R.id.result_search_list);
		sorry = (TextView)findViewById(R.id.search_cannot_find);
		intent = getIntent();
		String str = intent.getStringExtra("selection");
		selectionArgs = new String[]{str};
		adapter = new SearchInfoAdapter(this, selectionArgs);
		if(adapter.isDataExisted()){
			list.setVisibility(View.VISIBLE);
			list.setAdapter(adapter);
		}
		else
			sorry.setVisibility(View.VISIBLE);
		
		list.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3)
			{
				// TODO Auto-generated method stub
				String str = ((TextView)arg1.findViewById(R.id.search_item_name)).getText().toString();
				intent.putExtra("name", str);
				intent.setClass(SearchResultActivity.this, DetailInfoActivity.class);
				SearchResultActivity.this.startActivity(intent);
			}
		});
		
		back.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				SearchResultActivity.this.finish();
			}
		});
	}
	
}
