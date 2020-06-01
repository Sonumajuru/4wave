package com.fourwave;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import com.fourwave.activities.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class FourWaveApp extends AppCompatActivity {

    private static final String TAG = "FourWaveApp";
    private static FourWaveApp fourWaveApp;
    private Context mContext; // Used to access activities from different class

    private FourWaveApp()
    {
        //private constructor to limit direct instantiation
    }

    public static FourWaveApp getInstance() {
        //if null then only create instance
        if (fourWaveApp ==null) {
            fourWaveApp = new FourWaveApp();
        }
        //otherwise return cached instance
        return fourWaveApp;
    }

    public void logOut(Context context) {
        mContext = context;
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(mContext, LoginActivity.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//makesure user cant go back
        mContext.startActivity(intent);
    }

    public void displayProfile(Context context, final Class<?> activityProfile) {
        mContext = context;
        Intent myIntent = new Intent(mContext, activityProfile);
        mContext.startActivity(myIntent);
    }

    public void displayPremium(Context context,final Class<?> activityPremium) {
        mContext = context;
        Intent myIntent = new Intent(mContext, activityPremium);
        mContext.startActivity(myIntent);
    }

    public void displayHome(Context context,final Class<?> activityHome) {
        mContext = context;
        Intent myIntent = new Intent(mContext, activityHome);
        mContext.startActivity(myIntent);
    }

    public void displayLibrary(Context context,final Class<?> activityLibrary) {
        mContext = context;
        Intent myIntent = new Intent(mContext, activityLibrary);
        mContext.startActivity(myIntent);
    }

    public void displaySearch(Context context,final Class<?> activitySearch) {
        mContext = context;
        Intent myIntent = new Intent(mContext, activitySearch);
        mContext.startActivity(myIntent);
    }

    public void displayInspire(Context context,final Class<?> activityInspire) {
        mContext = context;
        Intent myIntent = new Intent(mContext, activityInspire);
        mContext.startActivity(myIntent);
    }

    public void setUpBottomNavigationView(BottomNavigationViewEx bottomNavigationView)
    {
        Log.d(TAG, "setUpBottomNavigationView: Setting Up BottomNavigationView");
        bottomNavigationView.enableAnimation(false);
        bottomNavigationView.enableItemShiftingMode(false);
        bottomNavigationView.enableShiftingMode(false);
        bottomNavigationView.setTextVisibility(false);
    }
}
