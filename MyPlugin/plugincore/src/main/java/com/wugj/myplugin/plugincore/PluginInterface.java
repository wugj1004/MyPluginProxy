package com.wugj.myplugin.plugincore;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

/**
 * description:
 * </br>
 * author: wugj
 * </br>
 * date: 2019/8/22
 * </br>
 * version:
 */
public interface PluginInterface {
    void attach(Activity activity);
    void onCreate(Bundle saveInstanceState);
    void onStart();
    void onResume();
    void onPause();
    void onDestroy();
    void onSaveInstateState(Bundle outState);
    boolean onTouchEvent(MotionEvent event);
    void onBackPressed();

}
