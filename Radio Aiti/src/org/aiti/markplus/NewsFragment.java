package org.aiti.markplus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NewsFragment extends Fragment {
	
	private String category;

	public NewsFragment(String cat) {
		this.category = cat;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.news_temp_fragment, container,false);
		((TextView) v.findViewById(R.id.teksCat)).setText(category);
		return v;
	}
}
