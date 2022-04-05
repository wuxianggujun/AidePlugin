package com.wuxianggujun.aplugin.api;

import android.content.pm.PackageInfo;
import android.content.res.Resources;

import com.wuxianggujun.aplugin.api.classloader.PluginDexClassLoader;

public class PluginApk {
    public PackageInfo packageInfo;
    public PluginDexClassLoader classLoader;
    public Resources pluginResources;


    public PluginApk(Resources pluginResources) {
        this.pluginResources = pluginResources;
    }
}
