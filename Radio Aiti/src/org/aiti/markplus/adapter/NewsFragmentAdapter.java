package org.aiti.markplus.adapter;

import org.aiti.markplus.util.TestFragment;
import org.aiti.markplus.NewsFragment;
import org.aiti.markplus.NewsMainFragment;
import org.aiti.markplus.R;

import com.viewpagerindicator.IconPagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class NewsFragmentAdapter extends FragmentPagerAdapter implements IconPagerAdapter{

	public NewsFragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	protected static final String[] CONTENT = new String[]
			{ "Home", "Main Story", "Movement", "Maneuver", "Manifesto"
			, "Maestro", "Mark Your Style"};
	
	@Override
	public int getIconResId(int index) {
		return R.drawable.ic_action_search;
	}

	@Override
	public Fragment getItem(int arg0) {
		switch (arg0) {
		case 0:
			return new NewsMainFragment();
		default:
			return new NewsFragment(CONTENT[arg0 % CONTENT.length]);
		}
		
//		return TestFragment.newInstance(CONTENT[arg0 % CONTENT.length]);
	}

	@Override
	public int getCount() {
		return CONTENT.length;
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		return CONTENT[position % CONTENT.length].toUpperCase();
	}
	
	

}
