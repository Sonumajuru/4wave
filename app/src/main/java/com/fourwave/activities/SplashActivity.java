package com.fourwave.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.fourwave.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.FirebaseAnalytics;

public class SplashActivity extends AppCompatActivity {

    /** Duration of wait **/
    private static boolean splashLoaded = false;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        if (!splashLoaded)
        {
            setContentView(R.layout.splashscreen_activity);
            int secondsDelayed = 1;
            new Handler().postDelayed(new Runnable() {
                public void run()
                {
                    startActivity(new Intent(SplashActivity.this, MenuActivity.class));
                    finish();
                }
            }, secondsDelayed * 500);

            splashLoaded = true;
        }
        else
        {
            Intent goToMainActivity = new Intent(SplashActivity.this, MenuActivity.class);
            goToMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(goToMainActivity);
            finish();
        }
    }
}