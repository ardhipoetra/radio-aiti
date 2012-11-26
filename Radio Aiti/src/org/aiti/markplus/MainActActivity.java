package org.aiti.markplus;

import org.aiti.markplus.R;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class MainActActivity extends TabActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act);
        
        TabHost host = (TabHost) findViewById(android.R.id.tabhost);
		host.setup();
		
		
		addtab(host, NewsFragmentActivity.class, "News");
		addtab(host, RadioActivity.class, "Radio");
		addtab(host, TwitterActivity.class, "Twitter");
		
		host.setCurrentTab(0);
    }
    
    private void addtab(TabHost host, Class<?> classNext, String label) {
    	
    	//untk kustomisasi
//		View tabIndicator = LayoutInflater.from(host.getContext()).inflate(R.layout.tab, null);
//		TextView title = (TextView) tabIndicator.findViewById(R.id.tabsText);
//		title.setText(label);
		
		TabSpec spec = host.newTabSpec(label);
		spec.setIndicator(label);
		spec.setContent(new Intent(this, classNext));
		host.addTab(spec);
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_act, menu);
        return true;
    }
}
