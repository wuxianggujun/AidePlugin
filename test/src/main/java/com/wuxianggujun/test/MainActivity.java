package com.wuxianggujun.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.wuxianggujun.aplugin.api.PluginActivity;

public class MainActivity extends PluginActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}