package org.aiti.markplus;

import org.aiti.markplus.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class TwitterActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.twitter_act);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.twitter_act, menu);
        return true;
    }
}
