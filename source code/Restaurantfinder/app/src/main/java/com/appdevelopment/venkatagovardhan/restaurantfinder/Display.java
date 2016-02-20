package com.appdevelopment.venkatagovardhan.restaurantfinder;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Venkata Govardhan on 2/18/2016.
 */
public class Display extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);
        String username = getIntent().getStringExtra("Username");

        TextView tv = (TextView)findViewById(R.id.txt_fname);
        tv.setText(username);

    }
}
