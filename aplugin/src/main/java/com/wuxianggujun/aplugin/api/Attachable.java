package com.wuxianggujun.aplugin.api;

public interface Attachable<T> {
    void attach(T proxy,PluginApk apk);
}
