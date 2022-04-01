package com.wuxianggujun.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.wuxianggujun.aplugin.api.PluginActivity;
import com.wuxianggujun.aplugin.api.PluginApk;

import java.lang.reflect.Field;

public class TestActivity extends PluginActivity {
    // protected Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Resources resources = getResources();
//        mContext = new ContextThemeWrapper(getBaseContext(), 0);
//        Class<? extends Context> clazz = mContext.getClass();
//
//        try {
//            Field mResourceField = clazz.getDeclaredField("mResources");
//            mResourceField.setAccessible(true);
//            mResourceField.set(mContext, resources);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        // View view = LayoutInflater.from(mContext).inflate(R.layout.activity_test, null);

        // setContentView(view);
        setContentView(R.layout.activity_test);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//       getMenuInflater().inflate(R.menu.toolbar, menu);
//       return true;
//    }


//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.backup:
//                Toast.makeText(this,"点击了第一个按钮", Toast.LENGTH_LONG).show();
//                break;
//            case  R.id.backup1:
//                Toast.makeText(this,"点击了第二个按钮",Toast.LENGTH_LONG).show();
//                break;
//            case  R.id.backup2:
//                Toast.makeText(this,"点击了第三个按钮",Toast.LENGTH_LONG).show();
//                break;
//        }
//        return true;
//    }
}
