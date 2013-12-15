package com.medicinesearch.fragment.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.medicinesearch.activity.RegistActivity;
import com.medicinesearch.fragments.GeneralFragment;
import com.medicinesearch_android.R;

/**
 * Ö÷Ò³Ãæ
 * 
 */
public class HomeView extends GeneralFragment implements OnClickListener
{

	private Button regist;

	private Button login;
	
	private Intent intent;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		super.setTitle("Ö÷Ò³");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragments_home, container, false);

		regist = (Button) view.findViewById(R.id.main_btn_regist);
		login = (Button) view.findViewById(R.id.main_btn_login);
		intent = new Intent();
		
		regist.setOnClickListener(this);
		login.setOnClickListener(this);

		return view;
	}

	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		switch (v.getId())
		{
			case R.id.main_btn_regist:
				intent.setClass(this.getActivity(), RegistActivity.class);
				HomeView.this.startActivity(intent);
				break;
			case R.id.main_btn_login:

				break;

			default:
				break;
		}
	}
}
