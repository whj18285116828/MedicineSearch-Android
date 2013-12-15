package com.medicinesearch.fragment.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.medicinesearch.activity.DetailInfoActivity;
import com.medicinesearch.activity.SearchResultActivity;
import com.medicinesearch.adapter.SearchInfoAdapter;
import com.medicinesearch.fragments.GeneralFragment;
import com.medicinesearch_android.R;

/**
 * ËÑË÷Ò³Ãæ
 * 
 */
public class SearchView extends GeneralFragment
{

	private ListView search_list = null;

	private EditText search_info = null;

	private Button search = null;

	private SearchInfoAdapter adapter;

	private Intent intent = new Intent();

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		super.setTitle("ËÑË÷");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragments_search, container,
				false);

		search_list = (ListView) view.findViewById(R.id.seek_search_list);
		search_info = (EditText) view.findViewById(R.id.search_input_edit);
		search = (Button) view.findViewById(R.id.search_btn_search);
		adapter = new SearchInfoAdapter(this.getActivity(), null);

		search_list.setAdapter(adapter);
		search_list.setOnItemClickListener(new OnItemClickListener()
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
				intent.setClass(SearchView.this.getActivity(),
						DetailInfoActivity.class);
				SearchView.this.startActivity(intent);
			}
		});

		search.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View arg0)
			{
				// TODO Auto-generated method stub
				intent.setClass(SearchView.this.getActivity(),
						SearchResultActivity.class);
				intent.putExtra("selection", search_info.getText().toString());
				SearchView.this.startActivity(intent);

			}
		});
		return view;

	}

}
