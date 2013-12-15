package com.medicinesearch.activity;

import com.medicinesearch.adapter.SearchInfoAdapter;
import com.medicinesearch_android.R;

import android.app.ListActivity;

import android.os.Bundle;
import android.widget.ListView;

public class collect_list extends ListActivity {
	private ListView listView;
	private SearchInfoAdapter adapter;

	public void onCreate(Bundle saveInstanceState) {
		super.onCreate(saveInstanceState);
		setContentView(R.layout.user_collcet);
		listView=getListView();
		adapter = new SearchInfoAdapter(this, null);
		listView.setAdapter(adapter);
	}

}
