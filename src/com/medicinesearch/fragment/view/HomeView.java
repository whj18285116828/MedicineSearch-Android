package com.medicinesearch.fragment.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.medicinesearch.fragments.GeneralFragment;
import com.medicinesearch_android.R;

/**
 * Ö÷Ò³Ãæ
 *
 */
public class HomeView extends GeneralFragment{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		super.setTitle("Ö÷Ò³");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragments_home, container, false);
	}
}
