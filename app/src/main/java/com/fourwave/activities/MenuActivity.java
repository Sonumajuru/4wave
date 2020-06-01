package com.fourwave.activities;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.fourwave.FourWaveApp;
import com.fourwave.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class MenuActivity extends AppCompatActivity {

    private static final String TAG = "MenuActivity";
    private FirebaseAuth mAuth;
    private Toolbar mToolbar;
    private BottomNavigationViewEx fToolbar;
    private Toolbar streamToolbar;
    private FourWaveApp fourWaveApp;
    private Context mContext;
    private Boolean isStreamed = false;
    private int mMenuId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        supportRequestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(R.layout.menu_activity);

        mAuth = FirebaseAuth.getInstance();

        fourWaveApp = FourWaveApp.getInstance();
        mContext = this;

        fToolbar = findViewById(R.id.bottom_nav_toolbar);
        setUpBottomNavigationView();
        mToolbar = findViewById(R.id.menu_toolbar);
        streamToolbar = findViewById(R.id.streamingToolbar);

        setSupportActionBar(mToolbar);
        mToolbar.setBackgroundDrawable(new ColorDrawable(Color.argb(0, 100, 181, 246)));
        onNavigationItemSelected();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation_menu_header, menu);
        return(super.onCreateOptionsMenu(menu));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        switch (id){
            case R.id.action_profile:
               fourWaveApp.displayProfile(this, ProfileActivity.class);
                break;

            case R.id.action_premium:
                fourWaveApp.displayPremium(this, PremiumActivity.class);
                break;

            case R.id.action_setting:
                break;

            case R.id.action_logOut:
                fourWaveApp.logOut(this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpBottomNavigationView()
    {
        Log.d(TAG, "setUpBottomNavigationView: Setting Up BottomNavigationView");
        fourWaveApp.setUpBottomNavigationView(fToolbar);

    }

    private void onNavigationItemSelected()
    {

        fToolbar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mMenuId = item.getItemId();
                for (int i = 0; i < fToolbar.getMenu().size(); i++) {
                    MenuItem menuItem = fToolbar.getMenu().getItem(i);
                    boolean isChecked = menuItem.getItemId() == item.getItemId();
                    menuItem.setChecked(isChecked);
                }

                switch (item.getItemId()) {
                    case R.id.nav_home: {
//                        fourWaveApp.displayHome(this,MenuActivity.class);
                    }
                    break;
                    case R.id.nav_library: {
                        fourWaveApp.displayLibrary(mContext,LibraryActivity.class);
                    }
                    break;
                    case R.id.nav_streaming: {
                        streamPlayer();
                    }
                    break;
                    case R.id.nav_search: {
                        fourWaveApp.displaySearch(mContext,SearchActivity.class);
                    }
                    break;
                    case R.id.nav_inspire: {
                        fourWaveApp.displayInspire(mContext,InspireActivity.class);
                    }
                    break;
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        finish();
    }

    private void streamPlayer()
    {
        if (!isStreamed)
        {
            isStreamed = true;
            streamToolbar.setVisibility(View.VISIBLE);
        }
        else
        {
            isStreamed = false;
            streamToolbar.setVisibility(View.GONE);
        }
    }
}
