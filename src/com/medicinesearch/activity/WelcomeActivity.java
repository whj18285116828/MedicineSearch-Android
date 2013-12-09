package com.medicinesearch.activity;

import com.medicinesearch.fragments.MainFragment;
import com.medicinesearch_android.R;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.widget.TextView;

public class WelcomeActivity extends Activity
{

	private TextView mTvAppName = null;

	/**
	 * 欢迎界面中横着的颜色条ID
	 */
	private int[] mImgIds = new int[]
	{ R.id.welcome_block_1, R.id.welcome_block_2, R.id.welcome_block_3,
			R.id.welcome_block_4, R.id.welcome_block_5, R.id.welcome_block_6, };

	private View[] mIvColorBlocks = new View[mImgIds.length];

	private Intent intent = new Intent();

	// 创建Handler对象
	Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);

		mTvAppName = (TextView) findViewById(R.id.welcome_app_name);

		for (int i = 0; i < mImgIds.length; i++)
		{
			mIvColorBlocks[i] = findViewById(mImgIds[i]);
		}

		AnimationSet animationSet = new AnimationSet(true);
		AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
		alphaAnimation.setDuration(1600);
		animationSet.addAnimation(alphaAnimation);
		mTvAppName.setAnimation(animationSet);

		handler.postDelayed(updateThread, 1500);
	}

	// 新建一个线程对象
	Runnable updateThread = new Runnable()
	{
		// 将要执行的操作写在线程对象的run方法当中
		int count = 0;

		public void run()
		{
			mIvColorBlocks[count].setVisibility(View.VISIBLE);
			count++;
			if (count == 6)
			{
				intent.setClass(WelcomeActivity.this, MainFragment.class);
				handler.removeCallbacks(updateThread);
				finish();
				startActivity(intent);
			}
			else
			{
				handler.postDelayed(updateThread, 300);
			}
		}
	};

}
