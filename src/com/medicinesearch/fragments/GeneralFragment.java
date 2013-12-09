package com.medicinesearch.fragments;

import java.io.Serializable;
import com.medicinesearch.fragment.view.HomeView;
import com.medicinesearch.fragment.view.LearnView;
import com.medicinesearch.fragment.view.SearchView;
import com.medicinesearch.fragment.view.UserView;
import com.medicinesearch_android.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
/**
 * ����࣬���󹫹�����
 *
 */
public class GeneralFragment extends Fragment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int item; //�������ֵײ��˵���
	protected static View main_title_RelativeLayout; //������
	protected final static String key = "Bundle";   //��תֵ����key������
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		if(getArguments() != null) {  //���ݽ������ഫ����arguments�ж�item����һ��
			if(getArguments().containsKey(MainFragment.Item)) {
				item = getArguments().getInt(MainFragment.Item);
			}
		}
	}
	
	/**ΪFragment���ز���ʱ���� **/
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_general, container, false);
		GeneralFragment fragment = null;
		switch(item) {
		case R.id.fragment_bottom_home:  //��ʼ����ҳ
			fragment = new HomeView();
			break;
		case R.id.fragment_bottom_order:
			fragment = new SearchView();  //��ʼ������
			 break;
		case R.id.fragment_bottom_notice:
			fragment = new LearnView();   //��ʼ��ѧϰ
			break;
		case R.id.fragment_bottom_more:
			fragment = new UserView();  //��ʼ���û�
			break;
		default:
			break;
		}
		if(fragment != null) {
			//����mainView�м�����ݣ�home,msg,at,more��
			getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.general_fragment, fragment).commit();
		}
		main_title_RelativeLayout =  ((View) container.getParent()).findViewById(R.id.main_title_RelativeLayout);
		//�����ɵ�view����
		return view;
	}
	
	/**���ñ���**/
	protected void setTitle(Object title) {
		if(main_title_RelativeLayout != null) {
			//�������е�����
			TextView mTvTitle = (TextView) main_title_RelativeLayout.findViewById(R.id.main_title_TextView);
			if(mTvTitle != null) {
				if(title instanceof Integer) {  //����
					mTvTitle.setText((Integer)title);
				} else { //�ַ�����
					mTvTitle.setText((CharSequence)title);
				}
			}
		}
	}
	
	/**ҳ����תֵ����**/
	protected void setBundle(Object... objects) {
		Bundle arguments = new Bundle();
		arguments.putSerializable(key, objects);
		GeneralFragment generalFragment = new GeneralFragment();
		generalFragment.setArguments(arguments);
	}
	
	/**��ȡ�����ݵ�ֵ**/
	protected Object[] getBundle() {
		if(getArguments() != null) {
			System.out.println("getBundle");
			if(getArguments().containsKey(key)) {
				Object[] object = (Object[]) getArguments().getSerializable(key);
				return object;
			}
		}
		return null;
	}
	
	/**�޲�ҳ����ת**/
	protected void toIntent(GeneralFragment generalFragment) {
		if(generalFragment != null) {
			getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.general_fragment, generalFragment).commit();
		}
	}

}
