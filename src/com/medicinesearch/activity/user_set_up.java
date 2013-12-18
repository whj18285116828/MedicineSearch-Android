package com.medicinesearch.activity;

import com.medicinesearch.database.DatabaseOpenHelper;
import com.medicinesearch.fragment.view.UserView;
import com.medicinesearch_android.R;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class user_set_up extends Activity implements OnClickListener {
	private Intent intent = new Intent();
	private Button changemima = null;
	private Button affirm = null;
	private EditText name = null;
	private EditText qq = null;
	private EditText e_mail = null;
	private RadioGroup mRadiogroup = null;
	private String Name = null;
	private String Qq = null;
	private String E_mail = null;
	private String sex = null;
	private DatabaseOpenHelper helper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_set);
		changemima = (Button) findViewById(R.id.gerenxinxi_mim);
		affirm = (Button) findViewById(R.id.set_info);
		name = (EditText) findViewById(R.id.set_info);
		qq = (EditText) findViewById(R.id.set_info);
		e_mail = (EditText) findViewById(R.id.set_info);
		mRadiogroup = (RadioGroup) findViewById(R.id.set_info);
		changemima.setOnClickListener(this);
		affirm.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.gerenxinxi_mim:
			intent.setClass(user_set_up.this, change_mima.class);
			this.startActivity(intent);
			break;
		case R.id.set_info:
			Name = name.getText().toString();
			Qq = qq.getText().toString();
			E_mail = e_mail.getText().toString();
			mRadiogroup
					.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

						@Override
						public void onCheckedChanged(RadioGroup group,
								int checkedId) {
							switch (checkedId) {
							case R.id.radio_man:
								sex = "男";
								break;
							case R.id.radio_woman:
								sex = "女";
								break;
							}

						}
					});
			ContentValues values = new ContentValues();
			values.put("Unickname", Name);
			values.put("Usex", sex);
			values.put("Uemail", E_mail);
			values.put("Uqq", Qq);
			String uno[] = { "1108060060" };
			helper = new DatabaseOpenHelper(this);
			helper.updata(null, values, "Uno=?", uno);
			Toast.makeText(user_set_up.this, "修改成功", Toast.LENGTH_LONG).show();
			intent.setClass(user_set_up.this, UserView.class);
			Bundle bundle = new Bundle();
			bundle.putString("na", Name);
			intent.putExtras(bundle);
			this.startActivity(intent);
			break;
		}

	}

}
