package org.aiti.markplus;

import org.aiti.markplus.adapter.NewsFragmentAdapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;

import com.androidquery.AQuery;
import com.viewpagerindicator.TitlePageIndicator;
import com.viewpagerindicator.TitlePageIndicator.IndicatorStyle;

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
        final float density = getResources().getDisplayMetrics().density;
        
        mIndicator.setBackgroundColor(0x18FF0000);
        mIndicator.setFooterColor(0xFFAA2222);
        mIndicator.setFooterLineHeight(1 * density); //1dp
        mIndicator.setFooterIndicatorHeight(3 * density); //3dp
        mIndicator.setFooterIndicatorStyle(IndicatorStyle.Underline);
        mIndicator.setTextSize(20);
        mIndicator.setTextColor(0xAA000000);
        mIndicator.setSelectedColor(0xFF000000);
        mIndicator.setSelectedBold(true);

        LayoutInflater l = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		AQuery aq = new AQuery(l.inflate(R.layout.news_main, null));
		aq.id(R.id.button1).clicked(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			}
		});
		
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_fragment, menu);
        return true;
    }
}
