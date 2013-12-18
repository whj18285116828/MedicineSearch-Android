package com.medicinesearch.activity;

import java.util.HashMap;

import com.medicinesearch.adapter.TestAdapter;
import com.medicinesearch_android.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class OnlineTestActivity extends Activity implements OnClickListener
{
	private Button goback;

	private TextView remain_time;

	private Button submit;

	private ListView list;

	private TestAdapter adapter_show;

	private TestAdapter adapter_hide;

	private TimeCount count;

	private static boolean isChecked = false;

	AlertDialog.Builder builder;

	AlertDialog dialog;

	private static int state = -1;// 0��ʾ�˳�

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_list);

		goback = (Button) findViewById(R.id.test_list_goback);
		remain_time = (TextView) findViewById(R.id.test_remain_time);
		submit = (Button) findViewById(R.id.test_list_submit);
		list = (ListView) findViewById(R.id.test_list_list);
		builder = new AlertDialog.Builder(OnlineTestActivity.this);
		state = -1;
		
		showConfirmDialog("����ʱ��Ϊ10���ӣ����ȷ����ʼ���ԣ����ȡ������");
		count = new TimeCount(600000, 1000);

		adapter_show = new TestAdapter(this, 1);
		adapter_hide = new TestAdapter(this, 0);

		goback.setOnClickListener(this);
		submit.setOnClickListener(this);
	}

	private void showConfirmDialog(String title)
	{
		builder.setTitle(title);
		builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener()
		{

			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				// TODO Auto-generated method stub
				if (state == 0)
				{
					dialog.dismiss();
					OnlineTestActivity.this.finish();
				}
				if (state == 1)
				{
					checkAnswer();
					isChecked = true;
				}
				if(state == -1)
				{
					count.start();
					list.setAdapter(adapter_hide);
				}
			}
		});
		builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener()
		{

			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				// TODO Auto-generated method stub
				if(state == -1)
				{
					dialog.dismiss();
					OnlineTestActivity.this.finish();
				}
			}
		});
		dialog = builder.create();
		dialog.show();
	}

	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		switch (v.getId())
		{
			case R.id.test_list_submit:
				state = 1;
				showConfirmDialog("ȷ���ύ��");
				break;
			case R.id.test_list_goback:
				if (!isChecked)
				{
					state = 0;
					showConfirmDialog("��û���ύ�����ѡ�񽫲��ᱣ�棬ȷ�ϣ�");
				}

				break;

			default:
				break;
		}
	}

	private void checkAnswer()
	{
		count.cancel();
		submit.setClickable(false);
		int score = 0;
		int maxScore = 0;
		int i;
		HashMap<Integer, String> rightAnswer = adapter_hide.getRightAnswer();
		HashMap<Integer, String> userAnswer = adapter_hide.getUserAnswer();

		for (i = 0; i < rightAnswer.size(); i++)
		{
			System.out.println("��ȷ�𰸣�" + rightAnswer.get(i));
			System.out.println("��Ĵ𰸣�" + userAnswer.get(i));
			if (rightAnswer.get(i).equals(userAnswer.get(i))) score += 10;
			maxScore += 10;

		}
		state = -1;
		showConfirmDialog("���֣�" + maxScore + "\n��ķ�����" + score);
		list.setAdapter(adapter_show);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			if (!isChecked)
			{
				state = 0;
				showConfirmDialog("��û���ύ�����ѡ�񽫲��ᱣ�棬ȷ�ϣ�");
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	class TimeCount extends CountDownTimer
	{
		public TimeCount(long millisInFuture, long countDownInterval)
		{
			super(millisInFuture, countDownInterval);// ��������Ϊ��ʱ��,�ͼ�ʱ��ʱ����
		}

		@Override
		public void onFinish()
		{// ��ʱ���ʱ����
			checkAnswer();
			isChecked = true;
		}

		@Override
		public void onTick(long millisUntilFinished)
		{// ��ʱ������ʾ
			if (millisUntilFinished <= 60000)
				remain_time.setText(millisUntilFinished / 1000 + "��");
			else remain_time
					.setText(millisUntilFinished
							/ 60000
							+ "��"
							+ (millisUntilFinished - millisUntilFinished / 60000 * 60000)
							/ 1000 + "��");
		}
	}

}
