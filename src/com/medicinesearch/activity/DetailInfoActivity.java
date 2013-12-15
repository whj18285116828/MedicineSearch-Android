package com.medicinesearch.activity;

import java.util.HashMap;

import com.medicinesearch.database.DatabaseOpenHelper;
import com.medicinesearch_android.R;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

	private Cursor result;

	private String str;

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

		intent = getIntent();
		str = intent.getStringExtra("name");
		System.out.println(str);
		bindData();

	}

	public void bindData()
	{
		result = helper.query("MedicinalHerbs", null, "Mname=?", new String[]
		{ str }, null, null, null);
		result.moveToFirst();

		name.setText(name.getText().toString() + result.getString(1));
		category.setText(category.getText().toString() + result.getString(2));
		taste.setText(taste.getText().toString() + result.getString(4));
		shape.setText(shape.getText().toString() + result.getString(5));
		function.setText(result.getString(6));
		describe.setText(result.getString(8));

		result.close();
	}

	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		switch (v.getId())
		{
			case R.id.detail_goback:
				DetailInfoActivity.this.finish();
				break;
			case R.id.detail_add:
				
				break;

			default:
				break;
		}
	}

}
