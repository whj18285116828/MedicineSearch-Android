package com.medicinesearch.fragment.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.medicinesearch.fragments.GeneralFragment;
import com.medicinesearch_android.R;

/**
 * ËÑË÷Ò³Ãæ
 *
 */
public class SearchView extends GeneralFragment{

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
		return inflater.inflate(R.layout.fragments_search, container, false);
	}
}
