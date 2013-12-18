package com.medicinesearch.fragment.view;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.medicinesearch.activity.PPT_list;
import com.medicinesearch.activity.ReviseUersinfo;
import com.medicinesearch.activity.TestActivity;
import com.medicinesearch.activity.collect_list;
import com.medicinesearch.activity.user_set_up;
import com.medicinesearch.database.DatabaseOpenHelper;
import com.medicinesearch.fragments.GeneralFragment;
import com.medicinesearch_android.R;

/**
 * 用户页面
 * 
 */
public class UserView extends GeneralFragment implements OnClickListener {

	Intent intent = new Intent();

	RelativeLayout user_info = null;
	RelativeLayout user_my_collection = null;
	RelativeLayout user_my_course = null;
	RelativeLayout user_setting = null;
	TextView Unicheng = null;
	TextView Uzhanghao = null;
	private DatabaseOpenHelper helper;
	String name = null;
	String zhanghao = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		super.setTitle("我");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragments_user, container, false);

		user_info = (RelativeLayout) view.findViewById(R.id.user_info);
		user_my_collection = (RelativeLayout) view
				.findViewById(R.id.user_my_collection);
		user_my_course = (RelativeLayout) view
				.findViewById(R.id.user_my_course);
		user_setting = (RelativeLayout) view.findViewById(R.id.user_setting);
		Unicheng = (TextView) view.findViewById(R.id.user_nicheng);
		Uzhanghao = (TextView) view.findViewById(R.id.zhanghao);
		user_info.setOnClickListener(this);
		user_my_collection.setOnClickListener(this);
		user_my_course.setOnClickListener(this);
		user_setting.setOnClickListener(this);

		Cursor cursor = helper.query("User", new String[] { "Unickname" },
				"Uno=?", new String[] { "1108060060" }, null, null, null);
		cursor.moveToFirst();
		while (cursor.moveToNext()) {
			name = cursor.getString(cursor.getColumnIndex("Unickname"));
		}
		zhanghao = "1108060060";
		Unicheng.setText(name);
		Uzhanghao.setText(zhanghao);

		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.user_info:
			intent.setClass(this.getActivity(), ReviseUersinfo.class);
			this.startActivity(intent);
			break;

		case R.id.user_my_collection:
			intent.setClass(this.getActivity(), collect_list.class);

			this.startActivity(intent);
			break;

		case R.id.user_my_course:
			intent.setClass(this.getActivity(), PPT_list.class);

			this.startActivity(intent);
			break;

		case R.id.user_setting:
			intent.setClass(this.getActivity(), user_set_up.class);
			this.startActivity(intent);
			break;
		default:
			break;
		}
	}
}
