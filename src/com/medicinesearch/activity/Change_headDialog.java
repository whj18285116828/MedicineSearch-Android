package com.medicinesearch.activity;


import com.medicinesearch_android.R;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;


public class Change_headDialog extends Dialog{
	private LayoutInflater factroy;
	
	public Change_headDialog(Activity act){
		super(act,R.style.madialog);
		factroy = LayoutInflater.from(act);
	}
    protected void OnCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(factroy.inflate(R.layout.changehead_dialog, null));
	}
}
