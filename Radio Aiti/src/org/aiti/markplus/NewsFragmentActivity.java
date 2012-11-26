package org.aiti.markplus;

import org.aiti.markplus.adapter.NewsFragmentAdapter;
import org.aiti.markplus.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import com.viewpagerindicator.TitlePageIndicator;

public class NewsFragmentActivity extends FragmentActivity {

    private NewsFragmentAdapter mAdapter;
	private ViewPager mPager;
	private TitlePageIndicator mIndicator;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_main_act);
        mAdapter = new NewsFragmentAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        mIndicator = (TitlePageIndicator)findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_fragment, menu);
        return true;
    }
}
