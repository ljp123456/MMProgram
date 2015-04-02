package com.example.mmprogram;


import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Parcelable;
import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

public class MainActivity extends Activity {

	
	List<View> listViews;
	
	Context context = null;

	LocalActivityManager manager = null;

	TabHost th = null;

	private ViewPager pager = null;

	//定义存放按钮图片的数组
	private int[] imageId = {R.drawable.find,R.drawable.message,
			R.drawable.link,R.drawable.me};
	//存放于图片相对应的标题
	private String[] mTexts = {"发现","消息","联系人","我"};
	//存放activity
	private Class[] activitys = {
			FindActivity.class,MessageActivity.class,
			LinkManActivity.class,MeActivity.class
	};
	
	//定义一个布局
	private LayoutInflater mInflater = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		initView();
		initDate(savedInstanceState);
	}


	private void initDate(Bundle savedInstanceState) {
		context = MainActivity.this;
		// 定放一个放view的list，用于存放viewPager用到的view
		listViews = new ArrayList<View>();
		manager = new LocalActivityManager(this, true);
		manager.dispatchCreate(savedInstanceState);
		
		Intent intent1 = null;
		for(int i=0;i<mTexts.length;i++){
			intent1 = new Intent(context, activitys[i]);
			listViews.add(getView(mTexts[i], intent1));
		}
		th.setup();
		th.setup(manager);
		
		Intent intent = new Intent(context, Empty.class);
		
		TabSpec tabSpec = null;
		for(int i=0;i<imageId.length;i++){
			tabSpec = th.newTabSpec(mTexts[i]);
			tabSpec.setIndicator(getTabItemView(i)).setContent(intent);
			th.addTab(tabSpec);
		}
		th.setCurrentTab(0);
		
		pager.setAdapter(new MyPageAdapter(listViews));
		pager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				// 当viewPager发生改变时，同时改变tabhost上面的currentTab
				th.setCurrentTab(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		
		th.setOnTabChangedListener(new OnTabChangeListener() {

			@Override
			public void onTabChanged(String tabId) {
				
				for(int i=0;i<mTexts.length;i++){
					if(mTexts[i].equals(tabId)){
						pager.setCurrentItem(i);
						TextView id = (TextView) findViewById(R.id.tv);
						id.setText(mTexts[i]);
					}
				}
			}
		});

	}
	
	private View getView(String id, Intent intent) {
		return manager.startActivity(id, intent).getDecorView();
	}


	private void initView() {
		pager =  (ViewPager) findViewById(R.id.vPager);
		th = (TabHost) findViewById(R.id.tabhost);
		//实例化布局对象
    	mInflater = LayoutInflater.from(this);
	}
	
	
	private class MyPageAdapter extends PagerAdapter {

		private List<View> list;

		private MyPageAdapter(List<View> list) {
			this.list = list;
		}

		@Override
		public void destroyItem(View view, int position, Object arg2) {
			ViewPager pViewPager = ((ViewPager) view);
			pViewPager.removeView(list.get(position));
		}

		@Override
		public void finishUpdate(View arg0) {
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object instantiateItem(View view, int position) {
			ViewPager pViewPager = ((ViewPager) view);
			pViewPager.addView(list.get(position));
			return list.get(position);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
		}

	}

	/**
	 * 给Tab按钮设置图标和文字
	 */
    
    private View getTabItemView(int index){
    	View view = mInflater.inflate(R.layout.tab_item_view, null);
    	ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
    	imageView.setImageResource(imageId[index]);
    	
    	TextView textView = (TextView) view.findViewById(R.id.textview);
    	textView.setText(mTexts[index]);
    	
		return view;
    }
	
}
