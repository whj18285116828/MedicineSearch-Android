package com.medicinesearch.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.medicinesearch.adapter.ReplyAdapter;
import com.medicinesearch.database.DatabaseOpenHelper;
import com.medicinesearch.util.StateUtil;
import com.medicinesearch_android.R;

public class ReplyActivity extends Activity implements OnClickListener
{

	private TextView title;

	private TextView time;

	private TextView content;

	private ListView list;

	private Button goback;

	private Button add;

	private Button reply;

	private Intent intent;

	private String Dno;

	private String preName;

	private String preTime;

	private String preContent;

	private DatabaseOpenHelper helper;

	private ReplyAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reply);
		intent = getIntent();

		preName = intent.getStringExtra("preName");
		preTime = intent.getStringExtra("preTime");
		preContent = intent.getStringExtra("preContent");

		title = (TextView) findViewById(R.id.reply_to_name);
		time = (TextView) findViewById(R.id.reply_pre_time);
		content = (TextView) findViewById(R.id.reply_pre_content);
		list = (ListView) findViewById(R.id.reply_list);
		goback = (Button) findViewById(R.id.reply_goback);
		add = (Button) findViewById(R.id.reply_add);
		reply = (Button) findViewById(R.id.reply_reply);

		helper = new DatabaseOpenHelper(this);

		Dno = getDno(preTime);
		title.setText(preName);
		time.setText(preTime);
		content.setText(preContent);

		goback.setOnClickListener(this);
		add.setOnClickListener(this);
		reply.setOnClickListener(this);

		adapter = new ReplyAdapter(this, preName, Dno);
		list.setAdapter(adapter);
	}

	@Override
	protected void onResume()
	{
		// TODO Auto-generated method stub
		adapter = new ReplyAdapter(this, preName, Dno);
		list.setAdapter(adapter);
		super.onResume();
	}

	private String getDno(String preTime)
	{
		// TODO Auto-generated method stub
		Cursor result = helper.query("Discuss", new String[]
		{ "Dno" }, "Dtime=?", new String[]
		{ preTime }, null, null, null);
		result.moveToFirst();
		String dno = result.getString(0);
		result.close();
		return dno;
	}

	@Override
	public void onClick(View arg0)
	{
		// TODO Auto-generated method stub
		switch (arg0.getId())
		{
			case R.id.reply_goback:
				helper.close();
				this.finish();
				break;
			case R.id.reply_add:
				if (!StateUtil.isLogin)
				{
					Toast.makeText(this, "Äú»¹Î´µÇÂ¼£¬ÇëÏÈµÇÂ¼!", Toast.LENGTH_SHORT)
							.show();
					intent.setClass(this, LoginActivity.class);
					this.startActivity(intent);
				}
				else
				{
					intent.putExtra("from", "Reply");
					intent.putExtra("Dno", Dno);
					intent.setClass(this, PublishInfoActivity.class);
					startActivity(intent);
				}
				break;
			case R.id.reply_reply:
				if (!StateUtil.isLogin)
				{
					Toast.makeText(this, "Äú»¹Î´µÇÂ¼£¬ÇëÏÈµÇÂ¼!", Toast.LENGTH_SHORT)
							.show();
					intent.setClass(this, LoginActivity.class);
					this.startActivity(intent);
				}
				else
				{
					intent.putExtra("from", "Reply");
					intent.putExtra("Dno", Dno);
					intent.setClass(this, PublishInfoActivity.class);
					startActivity(intent);
				}
				break;

			default:
				break;
		}
	}

}
