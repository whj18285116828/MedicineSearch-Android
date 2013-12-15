package com.medicinesearch.activity;

import com.medicinesearch.database.DatabaseOpenHelper;
import com.medicinesearch_android.R;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class RegistActivity extends Activity implements OnClickListener
{
	private Button btn_goback;

	private Button btn_add;

	private Button btn_commit;

	private EditText edit_name;

	private EditText edit_password;

	private EditText edit_checkpassword;

	private EditText edit_qq;

	private EditText edit_email;

	private EditText edit_nickname;

	private RadioGroup group;

	private RadioButton male;

	private RadioButton female;

	private String str;

	private Time time;

	private DatabaseOpenHelper helper;

	private final String TABLE_NAME = "User";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regist);

		btn_goback = (Button) findViewById(R.id.regist_goback);
		btn_add = (Button) findViewById(R.id.regist_add);
		btn_commit = (Button) findViewById(R.id.regist_btn_submit);
		edit_name = (EditText) findViewById(R.id.regist_edit_name);
		edit_password = (EditText) findViewById(R.id.regist_edit_password);
		edit_checkpassword = (EditText) findViewById(R.id.regist_edit_checkpassword);
		edit_qq = (EditText) findViewById(R.id.regist_edit_qq);
		edit_email = (EditText) findViewById(R.id.regist_edit_email);
		edit_nickname = (EditText) findViewById(R.id.regist_edit_nickname);
		group = (RadioGroup) findViewById(R.id.regist_check_group);
		male = (RadioButton) findViewById(R.id.regist_check_male);
		male.setChecked(true);
		female = (RadioButton) findViewById(R.id.regist_check_female);
		helper = new DatabaseOpenHelper(this);
		time = new Time("GMT+8");

		btn_add.setOnClickListener(this);
		btn_commit.setOnClickListener(this);
		btn_goback.setOnClickListener(this);

		group.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId)
			{
				// TODO Auto-generated method stub
				if (checkedId == male.getId())
				{
					str = male.getText().toString();
				}
				if (checkedId == female.getId())
				{
					str = female.getText().toString();
				}
			}
		});

	}

	private boolean isIDExist()
	{
		Cursor result = helper.query(TABLE_NAME, new String[]
		{ "Uno" }, null, null, null, null, null);
		result.moveToFirst();
		while (!result.isAfterLast())
		{
			if (result.getString(0).equals(edit_name.getText().toString()))
				return true;
			result.moveToNext();
		}
		result.close();
		return false;
	}

	private ContentValues getData()
	{
		ContentValues data = new ContentValues();
		time.setToNow();
		if (!edit_name.getText().toString().equals(""))
		{
			if (isIDExist())
			{
				Toast.makeText(this, "用户名已存在", Toast.LENGTH_SHORT).show();
				return null;
			}
			else
			{
				data.put("Uno", edit_name.getText().toString());
			}

		}

		else
		{
			Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
			return null;
		}
		if (!edit_password.getText().toString().equals(""))
		{
			if (edit_password.getText().toString()
					.equals(edit_checkpassword.getText().toString()))
				data.put("Upassword", edit_name.getText().toString());
			else
			{
				Toast.makeText(this, "两次输入的密码不一样已", Toast.LENGTH_SHORT).show();
				return null;
			}
		}
		else
		{
			Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
			return null;
		}
		if (!edit_qq.getText().toString().equals(""))
			data.put("Uqq", edit_qq.getText().toString());
		else data.put("Uqq", "");
		if (!edit_email.getText().toString().equals(""))
			data.put("Uemail", edit_email.getText().toString());
		else data.put("Uemail", "");
		if (!edit_nickname.getText().toString().equals(""))
			data.put("Unickname", edit_nickname.getText().toString());
		else data.put("Unickname", "");
		data.put("Usex", str);
		data.put("registerTime", "" + time.year + "-" + time.month + "-"
				+ time.monthDay + " " + time.hour + "-" + time.minute + "-"
				+ time.second);
		return data;
	}

	@Override
	public void onClick(View v)
	{
		ContentValues data;
		// TODO Auto-generated method stub
		switch (v.getId())
		{
			case R.id.regist_goback:
				this.finish();
				break;
			case R.id.regist_add:
				data = new ContentValues();
				data = getData();
				if (data != null)
				{
					helper.insert(TABLE_NAME, data);
					Toast.makeText(this, "注册成功！", Toast.LENGTH_SHORT).show();
					helper.close();
					this.finish();
				}
				break;
			case R.id.regist_btn_submit:
				data = new ContentValues();
				data = getData();
				if (data != null)
				{
					helper.insert(TABLE_NAME, data);
					Toast.makeText(this, "注册成功！", Toast.LENGTH_SHORT).show();
					helper.close();
					this.finish();
				}
				break;

			default:
				break;
		}
	}
}
