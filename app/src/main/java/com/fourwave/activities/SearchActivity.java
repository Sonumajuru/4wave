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
import android.widget.RelativeLayout;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.fourwave.FourWaveApp;
import com.fourwave.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class SearchActivity extends AppCompatActivity {

    private static final String TAG = "SearchActivity";
    private SearchView searchView;
    private RelativeLayout footerLayout;
    private FourWaveApp fourWaveApp;
    private Toolbar mToolbar;
    private BottomNavigationViewEx fToolbar;
    private Context mContext;
    private Toolbar streamToolbar;
    private Boolean isStreamed = false;
    private int mMenuId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);

        searchView = findViewById(R.id.searchView);
        footerLayout = findViewById(R.id.footer_layout);

        fourWaveApp = FourWaveApp.getInstance();
        mContext = this;

        fToolbar = findViewById(R.id.bottom_nav_toolbar);
        setUpBottomNavigationView();
        mToolbar = findViewById(R.id.menu_toolbar);
        streamToolbar = findViewById(R.id.streamingToolbar);

        setSupportActionBar(mToolbar);
        mToolbar.setBackgroundDrawable(new ColorDrawable(Color.argb(0, 100, 181, 246)));
        onNavigationItemSelected();

        searchView.setQueryHint("Artist, songs, or stations");
        if(!searchView.isFocused()) {
            searchView.clearFocus();
        }

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                footerLayout.setVisibility(View.GONE);
            }
        });

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View view, boolean b)
            {
                if(!b)
                {
                    if(searchView.getQuery().toString().length() < 1)
                    {
                        searchView.setIconified(true); //close the search editor and make search icon again
                    }
                    searchView.clearFocus();
                }
            }
        });

        // Inform any listener of focus changes
//        searchView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (mOnQueryTextFocusChangeListener != null) {
//                    mOnQueryTextFocusChangeListener.onFocusChange(SearchActivity.this, hasFocus);
//                }
//            }
//        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
//                footerLayout.setVisibility(View.VISIBLE);
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
                        fourWaveApp.displayHome(mContext,MenuActivity.class);
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
