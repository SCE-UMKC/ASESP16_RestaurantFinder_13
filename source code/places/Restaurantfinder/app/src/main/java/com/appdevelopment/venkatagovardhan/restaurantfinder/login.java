package com.appdevelopment.venkatagovardhan.restaurantfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Venkata Govardhan on 2/16/2016.
 */
public class login extends AppCompatActivity {
    datatbase helper = new datatbase(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics dm= new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout((int) (width * .9), (int) (height * .7));

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    public void checkCredentials(View v)
    {
        EditText usernameCtrl = (EditText)findViewById(R.id.Text_Username);
        EditText pwordCtrl = (EditText) findViewById(R.id.Text_password);

        String userName = usernameCtrl.getText().toString();
        String password = pwordCtrl.getText().toString();




        String pword = helper.searchPass(userName);
        if(password.equals(pword))
        {
            Intent i = new Intent(login.this,home.class);
            i.putExtra("Username",userName);
            startActivity(i);
        }
        else
        {
            Toast temp = Toast.makeText(login.this , "Username and password don't match!" , Toast.LENGTH_SHORT);
            temp.show();
        }



    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id. action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
