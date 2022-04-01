package com.wuxianggujun.test;

import android.os.Bundle;

import com.wuxianggujun.aplugin.api.PluginActivity;

public class TestActivity extends PluginActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

}
