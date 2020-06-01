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
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.fourwave.FourWaveApp;
import com.fourwave.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class PremiumActivity extends AppCompatActivity {

    private static final String TAG = "PremiumActivity";
    private WebView webView;
    private Toolbar mToolbar;
    private BottomNavigationViewEx fToolbar;
    private FourWaveApp fourWaveApp;
    private Toolbar streamToolbar;
    private Boolean isStreamed = false;
    private Context mContext;
    private int mMenuId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.premium_activity);

        fourWaveApp = FourWaveApp.getInstance();

        webView = findViewById(R.id.web_view);
        fourWaveApp = FourWaveApp.getInstance();
        mContext = this;

        fToolbar = findViewById(R.id.bottom_nav_toolbar);
        setUpBottomNavigationView();
        mToolbar = findViewById(R.id.menu_toolbar);
        streamToolbar = findViewById(R.id.streamingToolbar);

        setSupportActionBar(mToolbar);
        mToolbar.setBackgroundDrawable(new ColorDrawable(Color.argb(0, 100, 181, 246)));
        onNavigationItemSelected();

        startWebView("https://www.youtube.com");

    }

    private void startWebView(String url) {
        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl(url);

        // you need to setWebViewClient for forcefully open in your webview
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
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
                fourWaveApp.displayProfile(this, ProfileActivity.class);
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

    private void setUpBottomNavigationView() {
        Log.d(TAG, "setUpBottomNavigationView: Setting Up BottomNavigationView");
        fourWaveApp.setUpBottomNavigationView(fToolbar);

    }

    private void onNavigationItemSelected() {
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
                        fourWaveApp.displayHome(mContext, MenuActivity.class);
                    }
                    break;
                    case R.id.nav_library: {
                        fourWaveApp.displayLibrary(mContext, LibraryActivity.class);
                    }
                    break;
                    case R.id.nav_streaming: {
                        streamPlayer();
                    }
                    break;
                    case R.id.nav_search: {
                        fourWaveApp.displaySearch(mContext, SearchActivity.class);
                    }
                    break;
                    case R.id.nav_inspire: {
                        fourWaveApp.displayInspire(mContext, InspireActivity.class);
                    }
                    break;
                }
                return true;
            }
        });
    }

    private void streamPlayer() {
        if (!isStreamed) {
            isStreamed = true;
            streamToolbar.setVisibility(View.VISIBLE);
        } else {
            isStreamed = false;
            streamToolbar.setVisibility(View.GONE);
        }
    }
}
