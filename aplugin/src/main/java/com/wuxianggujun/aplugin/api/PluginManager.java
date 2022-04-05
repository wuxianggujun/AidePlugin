package com.wuxianggujun.aplugin.api;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import dalvik.system.DexClassLoader;
/*双亲委派模型
        双亲委派模型是一种组织类加载器之间关系的一种规范，
        他的工作原理是：如果一个类加载器收到了类加载的请求，
        它不会自己去尝试加载这个类，而是把这个请求委派给父类加载器去完成，
        这样层层递进，最终所有的加载请求都被传到最顶层的启动类加载器中，
        只有当父类加载器无法完成这个加载请求(它的搜索范围内没有找到所需的类)时，
        才会交给子类加载器去尝试加载。

        优点：java类随着它的类加载器一起具备了带有优先级的层次关系，
        这是十分必要的。比如java.langObject，它存放在\jre\lib\rt.jar中，
        它是所有java类的父类，因此无论哪个类加载都要加载这个类，
        最终所有的加载请求都汇总到顶层的启动类加载器中，因此Object类会由启动类加载器来加载，
        所以加载的都是同一个类，如果不使用双亲委派模型，由各个类加载器自行去加载的话，
        系统中就会出现不止一个Object类，
        应用程序就会全乱了。*/

public class PluginManager {
    static class PluginManagerHolder {
        static PluginManager pluginManager = new PluginManager();

    }

    private static Context mContext;

    Map<String, PluginApk> sMap = new HashMap<String, PluginApk>();

    public static PluginManager getInstance() {
        return PluginManagerHolder.pluginManager;
    }

    public PluginApk getPluginApk(String packageName) {
        return sMap.get(packageName);
    }

    public static void init(Context context) {
        mContext = context.getApplicationContext();
    }

    public final void loadApk(String apkPath) {
        PackageInfo packageInfo = queryPackageInfo(apkPath);
        if (packageInfo == null || TextUtils.isEmpty(packageInfo.packageName)) {
            return;
        }
        PluginApk pluginApk = sMap.get(packageInfo.packageName);
        if (pluginApk == null) {
            pluginApk = createApk(apkPath);
            if (pluginApk != null) {
                pluginApk.packageInfo = packageInfo;
                sMap.put(packageInfo.packageName, pluginApk);
            } else {
                throw new NullPointerException("PluginApk is null");
            }
        }
    }


    private PluginApk createApk(String apkPath) {
        String addAssetPathMethod = "addAssetPath";
        PluginApk pluginApk = null;

        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getMethod(addAssetPathMethod, String.class);
            addAssetPath.invoke(assetManager, apkPath);

            Resources pluginResources = new Resources(assetManager,
                    mContext.getResources().getDisplayMetrics(),
                    mContext.getResources().getConfiguration());
            pluginApk = new PluginApk(pluginResources);
            pluginApk.classLoader = createDexClassLoader(apkPath);

        } catch (IllegalAccessException
                | InstantiationException
                | NoSuchMethodException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
        return pluginApk;
    }

    private DexClassLoader createDexClassLoader(String apkPath) {
        File dexOutputDir = mContext.getDir("dex", Context.MODE_PRIVATE);
       /* dexPath	包含dex文件的jar包或apk文件路径
        optimizedDirectory	释放目录，可以理解为缓存目录，必须为应用私有目录，不能为空
        librarySearchPath	native库的路径（so文件），可为空
        parent	父类加载器*/
        DexClassLoader loader = new DexClassLoader(apkPath, dexOutputDir.getAbsolutePath(),
                null, mContext.getClassLoader());
        return loader;
    }

    private PackageInfo queryPackageInfo(String apkPath) {
        PackageInfo packageInfo = mContext.getPackageManager().getPackageArchiveInfo(apkPath,
                PackageManager.GET_ACTIVITIES | PackageManager.GET_SERVICES);
        if (packageInfo == null) {
            return null;
        }
        return packageInfo;
    }

    public void startActivity(Intent intent) {
        Intent pluginIntent = new Intent(mContext, ProxyActivity.class);
        Bundle extra = intent.getExtras();

        if (extra == null || !extra.containsKey(Constants.PLUGIN_CLASS_NAME) && !extra.containsKey(Constants.PACKAGE_NAME)) {
            try {
                throw new IllegalAccessException("lack class of plugin and package name");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        pluginIntent.putExtras(intent);
        pluginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(pluginIntent);

    }
}
