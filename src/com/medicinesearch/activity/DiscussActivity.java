package com.medicinesearch.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.medicinesearch.adapter.DiscussAdapter;
import com.medicinesearch.util.StateUtil;
import com.medicinesearch_android.R;

public class DiscussActivity extends Activity implements OnClickListener
{

	private Button goback;

	private Button publish;

	private ListView list;

	private DiscussAdapter adapter;

	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_discuss_list);

		goback = (Button) findViewById(R.id.discuss_list_goback);
		publish = (Button) findViewById(R.id.discuss_list_publish);
		list = (ListView) findViewById(R.id.discuss_list_list);
		intent = new Intent();

		adapter = new DiscussAdapter(this);

		goback.setOnClickListener(this);
		publish.setOnClickListener(this);
		list.setDividerHeight(0);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3)
			{
				// TODO Auto-generated method stub
				intent.setClass(DiscussActivity.this, ReplyActivity.class);
				String preName = ((TextView) arg1
						.findViewById(R.id.discussitem_name)).getText()
						.toString();
				String preTime = ((TextView) arg1
						.findViewById(R.id.discussitem_time)).getText()
						.toString();
				String preContent = ((TextView) arg1
						.findViewById(R.id.discussitem_content)).getText()
						.toString();
				intent.putExtra("preName", preName);
				intent.putExtra("preTime", preTime);
				intent.putExtra("preContent",preContent);
				DiscussActivity.this.startActivity(intent);
			}
		});
	}

	@Override
	protected void onResume()
	{
		// TODO Auto-generated method stub
		adapter = new DiscussAdapter(this);
		list.setAdapter(adapter);
		super.onResume();

	}

	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		switch (v.getId())
		{
			case R.id.discuss_list_publish:
				if (!StateUtil.isLogin)
				{
					Toast.makeText(this, "Äú»¹Î´µÇÂ¼£¬ÇëÏÈµÇÂ¼!", Toast.LENGTH_SHORT)
							.show();
					intent.setClass(this, LoginActivity.class);
					this.startActivity(intent);
				}
				else
				{
					intent.putExtra("from", "Discuss");
					intent.setClass(this, PublishInfoActivity.class);
					this.startActivity(intent);
				}

				break;
			case R.id.discuss_list_goback:
				this.finish();
				break;

			default:
				break;
		}
	}

}
