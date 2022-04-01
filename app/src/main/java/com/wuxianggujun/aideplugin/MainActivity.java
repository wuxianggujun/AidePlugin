package com.wuxianggujun.aideplugin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.wuxianggujun.aplugin.api.Constants;
import com.wuxianggujun.aplugin.api.PluginManager;
import com.wuxianggujun.aplugin.api.VLog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    public final static String PLUGIN_NAME = "test-debug.apk";
    public final static String PLUGIN_PACKAGE_NAME = "com.wuxianggujun.test";
    //com.wuxianggujun.test.TestActivity
    public final static String PLUGIN_CLAZZ_NAME = "com.wuxianggujun.test.MainActivity";
    //public final static String PLUGIN_CLAZZ_NAME = "com.wuxianggujun.test.TestActivity";
    PluginManager mPluginManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.main).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(Constants.PACKAGE_NAME, PLUGIN_PACKAGE_NAME);
                intent.putExtra(Constants.PLUGIN_CLASS_NAME, PLUGIN_CLAZZ_NAME);
                mPluginManager.startActivity(intent);
            }
        });
        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(Constants.PACKAGE_NAME, PLUGIN_PACKAGE_NAME);
                intent.putExtra(Constants.PLUGIN_CLASS_NAME, "com.wuxianggujun.test.TestActivity");
                mPluginManager.startActivity(intent);
            }
        });

        PluginManager.init(getApplicationContext());
        mPluginManager = PluginManager.getInstance();

        //String pluginApkPath = getCacheDir().getAbsolutePath()+PLUGIN_NAME;
                //Environment.getExternalStorageDirectory() +
                //File.separator + "plugins" + File.separator + PLUGIN_NAME;

        String pluginApkPath =  copyAssetAndWrite(MainActivity.this,PLUGIN_NAME);

        Log.d("apk路径",pluginApkPath);


        VLog.log("can read: " + Environment.getExternalStorageDirectory().canRead());
        VLog.log(pluginApkPath);
        mPluginManager.loadApk(pluginApkPath);
    }


    public static String copyAssetAndWrite(Context context, String fileName) {
        try {
            File cacheDir = context.getCacheDir();
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            File outFile = new File(cacheDir, fileName);
            if (!outFile.exists()) {
                boolean res = outFile.createNewFile();
                if (res) {
                    InputStream is = context.getAssets().open(fileName);
                    FileOutputStream fos = new FileOutputStream(outFile);
                    byte[] buffer = new byte[is.available()];
                    int byteCount;
                    while ((byteCount = is.read(buffer)) != -1) {
                        fos.write(buffer, 0, byteCount);
                    }
                    fos.flush();
                    is.close();
                    fos.close();
                    Toast.makeText(context, "移动成功", Toast.LENGTH_SHORT).show();
                    return outFile.getAbsolutePath();
                }
            } else {
                Toast.makeText(context, "文件已存在", Toast.LENGTH_SHORT).show();
                return outFile.getAbsolutePath();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}