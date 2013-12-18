package com.medicinesearch.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.medicinesearch.adapter.CourseAdapter;
import com.medicinesearch_android.R;

public class CoursewareActivity extends Activity
{
	private Button goback;

	private ListView list;

	private View line;

	private LinearLayout more;

	private CourseAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_course_list);

		goback = (Button) findViewById(R.id.course_goback);
		list = (ListView) findViewById(R.id.course_list);

		adapter = new CourseAdapter(this);
		list.setAdapter(adapter);

		list.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3)
			{
				// TODO Auto-generated method stub
				line = (View) arg1.findViewById(R.id.course_item_line);
				more = (LinearLayout) arg1.findViewById(R.id.course_item_more);
				if (line.getVisibility() == View.GONE)
				{
					line.setVisibility(View.VISIBLE);
					more.setVisibility(View.VISIBLE);
				}
				else
				{
					line.setVisibility(View.GONE);
					more.setVisibility(View.GONE);
				}

			}
		});

		goback.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				CoursewareActivity.this.finish();
			}
		});
	}

}
