package com.sakebook.android.sample.watch;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        RelativeLayout layout = (RelativeLayout)findViewById(R.id.layout);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        WatchView view = new WatchView(this);
        view.setId(0);
        view.setRadius(200);
        view.setPositionX(400);
        view.setPositionY(400);
        view.setHour(10);
        view.setMinute(8);
        view.setStroke(10);
        params.addRule(RelativeLayout.BELOW, R.id.text);
        layout.addView(view, params);


        WatchView view1 = new WatchView(this);
        view1.setId(1);
        view1.setRadius(200);
        view1.setPositionX(400);
        view1.setPositionY(800);
        view1.setHour(12);
        view1.setMinute(8);
        view1.setStroke(10);
        view1.setColor(Color.TRANSPARENT);
        params.addRule(RelativeLayout.BELOW, 0);
        layout.addView(view1, params);

        WatchView view2 = new WatchView(this);
        view2.setId(2);
        view2.setRadius(100);
        view2.setPositionX(400);
        view2.setPositionY(1200);
        view2.setHour(14);
        view2.setMinute(1);
        view2.setStroke(10);
        params.addRule(RelativeLayout.BELOW, 1);
        layout.addView(view2, params);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
