package com.medicinesearch.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.medicinesearch.fragments.GeneralFragment;
import com.medicinesearch_android.R;

public class ReviseUersinfo extends Activity implements OnClickListener {
	Bundle savedInstanceState = null;
	Intent intent = new Intent();
	RelativeLayout toxiang = null;
	RelativeLayout nicheng = null;
	RelativeLayout zhanghao = null;
	RelativeLayout xingbei = null;
	RelativeLayout mima = null;

	public void onCreate(Bundle saveInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reviseuersinfo);
		toxiang = (RelativeLayout) findViewById(R.id.gerenxixin_toux);
		nicheng = (RelativeLayout) findViewById(R.id.gerenxinxi_nic);
		xingbei = (RelativeLayout) findViewById(R.id.gerenxinxi_xingb);
		mima = (RelativeLayout) findViewById(R.id.gerenxinxi_mim);
		zhanghao = (RelativeLayout) findViewById(R.id.gerenxinxi_zhangh);
		toxiang.setOnClickListener(this);
		nicheng.setOnClickListener(this);
		xingbei.setOnClickListener(this);
		mima.setOnClickListener(this);
		zhanghao.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.gerenxixin_toux:
			new Change_headDialog(this).show();
			break;
		case R.id.gerenxinxi_nic:
			intent.setClass(ReviseUersinfo.this, TestActivity.class);
			this.startActivity(intent);
			break;

		case R.id.gerenxinxi_xingb:
			intent.setClass(ReviseUersinfo.this, TestActivity.class);
			this.startActivity(intent);
			break;
		case R.id.gerenxinxi_mim:
			intent.setClass(ReviseUersinfo.this, TestActivity.class);
			this.startActivity(intent);
			break;
		case R.id.gerenxinxi_zhangh:
			intent.setClass(ReviseUersinfo.this, TestActivity.class);
			this.startActivity(intent);
			break;
		}

	}
}