package com.medicinesearch.fragment.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.medicinesearch.activity.TestActivity;
import com.medicinesearch.fragments.GeneralFragment;
import com.medicinesearch_android.R;

/**
 * ѧϰҳ��
 * 
 */
public class LearnView extends GeneralFragment implements OnClickListener
{

	private LinearLayout course = null;

	private LinearLayout discuss = null;

	private LinearLayout test = null;

	Intent intent = new Intent();

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		super.setTitle("ѧϰ");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		View view = inflater
				.inflate(R.layout.fragments_learn, container, false);

		course = (LinearLayout) view.findViewById(R.id.learn_course);
		discuss = (LinearLayout) view.findViewById(R.id.learn_discuss);
		test = (LinearLayout) view.findViewById(R.id.learn_test);

		course.setOnClickListener(this);
		discuss.setOnClickListener(this);
		test.setOnClickListener(this);

		return view;
	}

	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		switch (v.getId())
		{
			case R.id.learn_course:
				intent.setClass(this.getActivity(), TestActivity.class);
				this.startActivity(intent);
				break;

			case R.id.learn_discuss:
				intent.setClass(this.getActivity(), TestActivity.class);
				this.startActivity(intent);
				break;
			case R.id.learn_test:
				intent.setClass(this.getActivity(), TestActivity.class);
				this.startActivity(intent);
				break;
			default:
				break;
		}

	}
}
