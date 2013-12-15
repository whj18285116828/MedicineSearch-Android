package com.medicinesearch.activity;



import com.medicinesearch.adapter.courseAdapter;
import com.medicinesearch_android.R;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;

public class PPT_list extends ListActivity{
	private ListView listView;
	private courseAdapter adapter;

	public void onCreate(Bundle saveInstanceState) {
		super.onCreate(saveInstanceState);
		setContentView(R.layout.user_collcet);
		listView=getListView();
		adapter = new courseAdapter(this);
		listView.setAdapter(adapter);
	}

}
