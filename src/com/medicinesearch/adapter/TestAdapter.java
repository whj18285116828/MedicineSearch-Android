package com.medicinesearch.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.medicinesearch.database.DatabaseOpenHelper;
import com.medicinesearch_android.R;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

final class TestViewHolder
{
	public TextView name;

	public TextView right;

	public TextView explain;

	public RadioButton answer_a;

	public RadioButton answer_b;

	public RadioButton answer_c;

	public RadioButton answer_d;
	
	public RadioButton answer_cache;

}

public class TestAdapter extends BaseAdapter
{
	private LayoutInflater mInflater;

	private DatabaseOpenHelper helper;

	private List<Map<String, Object>> mData;

	private Context context;

	private int state; // 0为不显示答案，1为显示答案

	private HashMap<Integer, String> rightAnswer;

	private HashMap<Integer, String> userAnswer;

	String[] columns = new String[]
	{ "TestContent", "TestAnls", "TestBnls", "TestCnls", "TestDnls",
			"RightAnswer", "Explain" };

	public TestAdapter(Context context, int state)
	{
		this.context = context;
		this.state = state;
		this.mInflater = LayoutInflater.from(context);
		helper = new DatabaseOpenHelper(context);
		rightAnswer = new HashMap<Integer, String>();
		userAnswer = new HashMap<Integer, String>();
		mData = getData();
		fillUserAnswer();
	}


	private void fillUserAnswer()
	{
		for (int i = 0; i < mData.size(); i++)
		{
			userAnswer.put(i, "");
			rightAnswer.put(i, (String) mData.get(i).get("right"));
		}
	}

	private List<Map<String, Object>> getData()
	{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map;
		Cursor result = helper.query("OnlineTest", columns, null, null, null,
				null, null);
		System.out.println(result.getCount());
		result.moveToFirst();
		while (!result.isAfterLast())
		{
			map = new HashMap<String, Object>();
			map.put("name", result.getString(0));
			map.put("answer_a", result.getString(1));
			map.put("answer_b", result.getString(2));
			map.put("answer_c", result.getString(3));
			map.put("answer_d", result.getString(4));
			map.put("right", result.getString(5));
			map.put("explain", result.getString(6));
			list.add(map);
			// do something useful with these
			result.moveToNext();
		}
		result.close();

		return list;
	}

	public HashMap<Integer, String> getRightAnswer()
	{
		return rightAnswer;
	}

	public HashMap<Integer, String> getUserAnswer()
	{
		return userAnswer;
	}

	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return mData.size();
	}

	@Override
	public Object getItem(int arg0)
	{
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public long getItemId(int arg0)
	{
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		// TODO Auto-generated method stub
		TestViewHolder holder = null;
		if (convertView == null)
		{

			holder = new TestViewHolder();

			convertView = mInflater.inflate(R.layout.listitem_test, null);

			holder.name = (TextView) convertView.findViewById(R.id.test_name);
			holder.right = (TextView) convertView
					.findViewById(R.id.test_rightanswer);
			holder.explain = (TextView) convertView
					.findViewById(R.id.test_explain);
			holder.answer_a = (RadioButton) convertView
					.findViewById(R.id.test_a);
			holder.answer_b = (RadioButton) convertView
					.findViewById(R.id.test_b);
			holder.answer_c = (RadioButton) convertView
					.findViewById(R.id.test_c);
			holder.answer_d = (RadioButton) convertView
					.findViewById(R.id.test_d);
			holder.answer_cache = (RadioButton) convertView
					.findViewById(R.id.test_cache);
			convertView.setTag(holder);

		}
		else
		{
			holder = (TestViewHolder) convertView.getTag();

		}

		int index = position + 1;

		holder.name.setText("" + index + "."
				+ (String) mData.get(position).get("name"));
		holder.right.setText("正确答案："
				+ (String) mData.get(position).get("right"));
		holder.explain.setText("解释："
				+ (String) mData.get(position).get("explain"));
		holder.answer_a.setText((String) mData.get(position).get("answer_a"));
		holder.answer_b.setText((String) mData.get(position).get("answer_b"));
		holder.answer_c.setText((String) mData.get(position).get("answer_c"));
		holder.answer_d.setText((String) mData.get(position).get("answer_d"));
		if (rightAnswer.get(position) == null)
			rightAnswer
					.put(position, (String) mData.get(position).get("right"));

		holder.answer_a.setOnClickListener(new MyOnClickListener(position));
		holder.answer_b.setOnClickListener(new MyOnClickListener(position));
		holder.answer_c.setOnClickListener(new MyOnClickListener(position));
		holder.answer_d.setOnClickListener(new MyOnClickListener(position));

		if (userAnswer.get(position) == "")
		{
//			holder.answer_a.setChecked(false);
//			holder.answer_b.setChecked(false);
//			holder.answer_c.setChecked(false);
//			holder.answer_d.setChecked(false);
			holder.answer_cache.setChecked(true);
		}
		else
		{
			if (userAnswer.get(position).equals(
					(String) mData.get(position).get("answer_a")))
			{
				System.out.println("a");
				holder.answer_cache.setChecked(true);
				holder.answer_a.setChecked(true);
			}
			if (userAnswer.get(position).equals(
					(String) mData.get(position).get("answer_b")))
			{
				System.out.println("b");
				holder.answer_cache.setChecked(true);
				holder.answer_b.setChecked(true);
			}
			if (userAnswer.get(position).equals(
					(String) mData.get(position).get("answer_c")))
			{
				System.out.println("c");
				holder.answer_cache.setChecked(true);
				holder.answer_c.setChecked(true);
			}
			if (userAnswer.get(position).equals(
					(String) mData.get(position).get("answer_d")))
			{
				System.out.println("d");
				holder.answer_cache.setChecked(true);
				holder.answer_d.setChecked(true);
			}
		}

		if (state == 0)
		{
			holder.right.setVisibility(View.GONE);
			holder.explain.setVisibility(View.GONE);
		}
		if (state == 1)
		{
			holder.right.setVisibility(View.VISIBLE);
			holder.explain.setVisibility(View.VISIBLE);
		}

		return convertView;
	}

	class MyOnClickListener implements OnClickListener
	{

		private int position;

		public MyOnClickListener(int position)
		{
			this.position = position;
			System.out.println("创建" + position);
		}

		@Override
		public void onClick(View arg0)
		{
			// TODO Auto-generated method stub
			switch (arg0.getId())
			{
				case R.id.test_a:
					System.out.println("插入数据R.id.test_a" + "于" + position);
					userAnswer.put(position,
							(String) mData.get(position).get("answer_a"));
					break;
				case R.id.test_b:
					System.out.println("插入数据+R.id.test_b" + "于" + position);
					userAnswer.put(position,
							(String) mData.get(position).get("answer_b"));
					break;
				case R.id.test_c:
					System.out.println("插入数据+R.id.test_c" + "于" + position);
					userAnswer.put(position,
							(String) mData.get(position).get("answer_c"));
					break;
				case R.id.test_d:
					System.out.println("插入数据+R.id.test_d" + "于" + position);
					userAnswer.put(position,
							(String) mData.get(position).get("answer_d"));
					break;

				default:
					break;
			}
		}

	}

}
