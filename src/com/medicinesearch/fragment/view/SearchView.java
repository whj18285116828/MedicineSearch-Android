package com.medicinesearch.fragment.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.medicinesearch.adapter.SearchInfoAdapter;
import com.medicinesearch.fragments.GeneralFragment;
import com.medicinesearch_android.R;

/**
 * ËÑË÷Ò³Ãæ
 *
 */
public class SearchView extends GeneralFragment{
	
	private ListView search_list = null;
	private EditText search_info = null;
	private Button search = null;
	private SearchInfoAdapter adapter ;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		super.setTitle("ËÑË÷");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragments_search, container, false);
		
		search_list = (ListView)view.findViewById(R.id.search_list);
		search_info = (EditText)view.findViewById(R.id.search_input_edit);
		search = (Button)view.findViewById(R.id.search_btn_search);
		adapter = new SearchInfoAdapter(this.getActivity());
		
		search_list.setAdapter(adapter);
		search.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View arg0)
			{
				// TODO Auto-generated method stub
				
			}
		});
		
		return view;
	}
	
}
