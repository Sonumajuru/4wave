package com.fourwave.activities;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.fourwave.FourWaveApp;
import com.fourwave.R;

public class SettingActivity extends AppCompatActivity {

    private static final String TAG = "SettingActivity";
    private FourWaveApp fourWaveApp;
    private Toolbar mToolbar;
    private Toolbar streamToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_layout);

        fourWaveApp = FourWaveApp.getInstance();

        streamToolbar = findViewById(R.id.streamingToolbar);
        mToolbar = findViewById(R.id.menu_toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setBackgroundDrawable(new ColorDrawable(Color.argb(0, 100, 181, 246)));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation_menu_header, menu);
        return(super.onCreateOptionsMenu(menu));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_profile:
                fourWaveApp.displayProfile(this,ProfileActivity.class);
                break;

            case R.id.action_premium:
                fourWaveApp.displayPremium(this,PremiumActivity.class);
                break;

            case R.id.action_setting:
                break;

            case R.id.action_logOut:
                fourWaveApp.logOut(this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
