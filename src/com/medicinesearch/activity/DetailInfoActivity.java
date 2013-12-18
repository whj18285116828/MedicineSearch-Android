package com.medicinesearch.activity;

import com.medicinesearch.database.DatabaseOpenHelper;
import com.medicinesearch.util.StateUtil;
import com.medicinesearch_android.R;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailInfoActivity extends Activity implements OnClickListener
{
	private Intent intent;

	private ImageView picture;

	private TextView name;

	private TextView category;

	private TextView taste;

	private TextView shape;

	private TextView function;

	private TextView describe;

	private DatabaseOpenHelper helper;

	private Button goback;

	private Button add;

	private String str;

	private String Mno = null;

	private Time time;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_info);

		picture = (ImageView) findViewById(R.id.detail_picture);
		name = (TextView) findViewById(R.id.detail_name);
		category = (TextView) findViewById(R.id.detail_category);
		taste = (TextView) findViewById(R.id.detail_taste);
		shape = (TextView) findViewById(R.id.detail_shape);
		function = (TextView) findViewById(R.id.detail_function);
		describe = (TextView) findViewById(R.id.detail_describe);
		goback = (Button) findViewById(R.id.detail_goback);
		add = (Button) findViewById(R.id.detail_add);
		helper = new DatabaseOpenHelper(this);
		time = new Time("GMT+8");
		intent = getIntent();
		str = intent.getStringExtra("name");
		System.out.println(str);
		goback.setOnClickListener(this);
		add.setOnClickListener(this);
		bindData();

	}

	private String getCurTime()
	{
		time.setToNow();
		return "" + time.year + "-" + time.month + "-" + time.monthDay + " "
				+ time.hour + ":" + time.minute + ":" + time.second;
	}

	public void bindData()
	{
		Cursor result = helper.query("MedicinalHerbs", null, "Mname=?",
				new String[]
				{ str }, null, null, null);
		result.moveToFirst();
		Mno = result.getString(0);
		name.setText(name.getText().toString() + result.getString(1));
		category.setText(category.getText().toString() + result.getString(2));
		taste.setText(taste.getText().toString() + result.getString(4));
		shape.setText(shape.getText().toString() + result.getString(5));
		function.setText(result.getString(6));
		describe.setText(result.getString(8));

		result.close();
	}

	private ContentValues getData()
	{
		ContentValues data = new ContentValues();
		data.put("Mno", Mno);
		data.put("Uno", StateUtil.curUser);
		data.put("Ctime", getCurTime());
		return data;

	}

	private boolean isExist()
	{
		Cursor result = helper.query("Collection", new String[]
		{ "Mno" }, "Uno=?", new String[]
		{ StateUtil.curUser }, null, null, null);
		if (result.getCount() > 0)
		{
			result.moveToFirst();
			while(!result.isAfterLast()){
				if(Mno.equals(result.getString(0))){
					result.close();
					return true;
				} else
				{
					result.moveToNext();
				}
			}
		}
		result.close();
		return false;
	}

	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		switch (v.getId())
		{
			case R.id.detail_goback:
				helper.close();
				DetailInfoActivity.this.finish();
				break;
			case R.id.detail_add:
				if (StateUtil.isLogin)
				{
					if (!isExist())
					{
						helper.insert("Collection", getData());
						Toast.makeText(this, "收藏成功!", Toast.LENGTH_SHORT)
								.show();
					}
					else
					{
						Toast.makeText(this, "你已经收藏过了哦!", Toast.LENGTH_SHORT)
								.show();
					}
				}
				else
				{
					Toast.makeText(DetailInfoActivity.this, "您还未登录，请先登录后再进行收藏!", Toast.LENGTH_SHORT).show();
					intent.setClass(DetailInfoActivity.this,
							LoginActivity.class);
					DetailInfoActivity.this.startActivity(intent);
				}
				break;

			default:
				break;
		}
	}

}
