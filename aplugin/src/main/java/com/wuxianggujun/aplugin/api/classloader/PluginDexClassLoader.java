package com.wuxianggujun.aplugin.api.classloader;

import android.util.Log;

import dalvik.system.BaseDexClassLoader;

public class PluginDexClassLoader extends BaseDexClassLoader {
    private static final  String TAG = PluginDexClassLoader.class.getSimpleName();
    /**
     * Creates a {@code DexClassLoader} that finds interpreted and native
     * code.  Interpreted classes are found in a set of DEX files contained
     * in Jar or APK files.
     *
     * <p>The path lists are separated using the character specified by the
     * {@code path.separator} system property, which defaults to {@code :}.
     *
     * @param dexPath the list of jar/apk files containing classes and
     *     resources, delimited by {@code File.pathSeparator}, which
     *     defaults to {@code ":"} on Android
     * @param optimizedDirectory this parameter is deprecated and has no effect since API level 26.
     * @param librarySearchPath the list of directories containing native
     *     libraries, delimited by {@code File.pathSeparator}; may be
     *     {@code null}
     * @param parent the parent class loader
     */


    public PluginDexClassLoader(String dexPath, String optimizedDirectory,
                                String librarySearchPath, ClassLoader parent) {

        super(dexPath, null, librarySearchPath, parent);

    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Log.i(TAG,name);
        return super.findClass(name);
    }
}