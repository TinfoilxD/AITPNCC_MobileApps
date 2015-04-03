package aitpncc.mobile_team17;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.InterruptedIOException;


/**
 * SplashActivity displays a picture of myg5.jpg for 3 seconds before starting MainScreenActivity.
 */

public class SplashActivity extends ActionBarActivity
{
    public static final String SPLASH_TAG = "SplashActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


           //wait(5000);
           new CountDownTimer(3000,1500)
           {

               @Override
               public void onTick(long millisUntilFinished)
               {

               }

               @Override
               public void onFinish()
               {
                   Intent i = new Intent(SplashActivity.this, MainScreenActivity.class);
                   startActivity(i);
               }
           }.start();


    }


}
