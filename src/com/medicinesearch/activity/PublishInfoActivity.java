package com.medicinesearch.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.medicinesearch.database.DatabaseOpenHelper;
import com.medicinesearch.util.StateUtil;
import com.medicinesearch_android.R;

public class PublishInfoActivity extends Activity implements OnClickListener
{
	private EditText content;

	private Button goback;

	private Button commit;

	private Button add;

	private DatabaseOpenHelper helper;

	private Time time;

	private String from;
	
	private String Dno;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_publish_info);

		Intent intent = getIntent();
		from = intent.getStringExtra("from");
		Dno = intent.getStringExtra("Dno");

		content = (EditText) findViewById(R.id.publish_info_content);
		goback = (Button) findViewById(R.id.publish_info_goback);
		commit = (Button) findViewById(R.id.publish_info_commit);
		add = (Button) findViewById(R.id.publish_info_add);
		helper = new DatabaseOpenHelper(this);

		time = new Time("GMT+8");

		goback.setOnClickListener(this);
		commit.setOnClickListener(this);
		add.setOnClickListener(this);

	}

	private String getCurTime()
	{
		time.setToNow();
		return "" + time.year + "-" + time.month + "-" + time.monthDay + " "
				+ time.hour + ":" + time.minute + ":" + time.second;
	}

	private ContentValues getData4Discuss()
	{
		ContentValues data = new ContentValues();
		data.put("Uno", StateUtil.curUser);
		data.put("Dcontent", content.getText().toString());
		data.put("Dtime", getCurTime());
		return data;
	}

	private ContentValues getData4Reply()
	{
		ContentValues data = new ContentValues();
		data.put("Uno", StateUtil.curUser);
		data.put("Rcontent", content.getText().toString());
		data.put("Dno", Dno);
		data.put("Rtime", getCurTime());
		return data;
	}

	@Override
	public void onClick(View arg0)
	{
		// TODO Auto-generated method stub
		switch (arg0.getId())
		{
			case R.id.publish_info_commit:
				if (from.equals("Discuss"))
				{
					helper.insert("Discuss", getData4Discuss());
					Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
				}
				if (from.equals("Reply"))
				{
					helper.insert("Reply", getData4Reply());
					Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
				}

				helper.close();
				this.finish();
				break;
			case R.id.publish_info_goback:
				helper.close();
				this.finish();
				break;
			case R.id.publish_info_add:
				if (from.equals("Discuss"))
				{
					helper.insert("Discuss", getData4Discuss());
					Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
				}
				if (from.equals("Reply"))
				{
					helper.insert("Reply", getData4Reply());
					Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
				}
				helper.close();
				this.finish();
				break;

			default:
				break;
		}
	}

}
