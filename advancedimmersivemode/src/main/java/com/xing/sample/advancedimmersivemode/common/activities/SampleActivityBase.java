package com.xing.sample.advancedimmersivemode.common.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xing.sample.advancedimmersivemode.R;

public class SampleActivityBase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_activity_base);
    }
}
