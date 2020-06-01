package com.fourwave.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.fourwave.R;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "ProfileActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);

    }
}
