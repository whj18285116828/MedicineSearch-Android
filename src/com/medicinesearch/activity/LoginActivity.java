package com.medicinesearch.activity;

import com.medicinesearch.database.DatabaseOpenHelper;
import com.medicinesearch.util.StateUtil;
import com.medicinesearch_android.R;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener
{

	private Button btn_login;

	private Button btn_regist;

	private Button btn_goback;

	private EditText edit_name;

	private EditText edit_password;

	private DatabaseOpenHelper helper;

	private Intent intent;

	private String name;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		btn_login = (Button) findViewById(R.id.login_btn_login);
		btn_regist = (Button) findViewById(R.id.login_btn_regist);
		btn_goback = (Button) findViewById(R.id.login_goback);
		edit_name = (EditText) findViewById(R.id.login_edit_name);
		edit_password = (EditText) findViewById(R.id.login_edit_password);
		intent = new Intent();
		helper = new DatabaseOpenHelper(this);

		btn_login.setOnClickListener(this);
		btn_regist.setOnClickListener(this);
		btn_goback.setOnClickListener(this);

	}

	/**
	 * 判断密码是否正确
	 * 
	 */
	private boolean checkPassword()
	{
		name = edit_name.getText().toString().trim();

		Cursor result = helper.query("User", new String[]
		{ "Upassword" }, "Uno=?", new String[]
		{ name }, null, null, null);
		if (result.getCount() == 0)
		{
			Toast.makeText(this, "用户名不存在", Toast.LENGTH_SHORT).show();
			result.close();
			return false;
		}
		else
		{
			result.moveToFirst();
			System.out.println(result.getString(0));
			if (result.getString(0).equals(
					edit_password.getText().toString().trim()))
			{
				result.close();
				return true;
			}
			else
			{
				result.close();
				return false;
			}
		}
	}

	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		switch (v.getId())
		{
			case R.id.login_btn_login:
				if (checkPassword())
				{
					System.out.println(StateUtil.isLogin);
					StateUtil.isLogin = true;
					StateUtil.curUser = edit_name.getText().toString().trim();
					Toast.makeText(this, "登录成功！", Toast.LENGTH_SHORT).show();
					this.finish();
				}
				else
				{
					Toast.makeText(this, "密码不正确！", Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.login_btn_regist:
				intent.setClass(LoginActivity.this, RegistActivity.class);
				startActivity(intent);
				break;
			case R.id.login_goback:
				helper.close();
				this.finish();
				break;

			default:
				break;
		}
	}

}
