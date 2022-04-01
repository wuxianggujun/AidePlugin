package com.wuxianggujun.aplugin.api;

import android.os.Bundle;

public interface Pluginable {

    void onCreate(Bundle bundle);

    void onStart();

    void onResume();

    void onStop();

    void onPause();

    void onDestroy();
}
