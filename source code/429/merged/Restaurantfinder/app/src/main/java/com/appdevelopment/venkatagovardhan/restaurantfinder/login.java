package com.appdevelopment.venkatagovardhan.restaurantfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by Venkata Govardhan on 2/16/2016.
 */
public class login extends AppCompatActivity {
    datatbase helper = new datatbase(this);
    private LoginButton facebookLoginButton;
    private CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics dm= new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout((int) (width * .9), (int) (height * .7));
        initInstances();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    private void initInstances() {
        //Facebook Login
        facebookLoginButton = (LoginButton)findViewById(R.id.flogin);
        facebookLoginButton.setReadPermissions(Arrays.asList("public_profile, email, user_birthday, user_friends"));

        callbackManager = CallbackManager.Factory.create();

        // Callback registration
        facebookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {
                                // Application code
                            }
                        });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender, birthday");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Toast.makeText(login.this, "User cancelled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(login.this, "Error on Login, check your facebook app_id", Toast.LENGTH_LONG).show();
            }
        });

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

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
