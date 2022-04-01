package com.wuxianggujun.aplugin.api;

import android.content.pm.PackageInfo;
import android.content.res.Resources;

import dalvik.system.DexClassLoader;

public class PluginApk {
    public PackageInfo packageInfo;
    public DexClassLoader classLoader;
    public Resources pluginResources;


    public PluginApk(Resources pluginResources) {
        this.pluginResources = pluginResources;
    }
}
