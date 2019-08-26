package com.wugj.myplugin.plugincore;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * description:
 * </br>
 * author: wugj
 * </br>
 * date: 2019/8/22
 * </br>
 * version:
 */
public class BaseActivity extends Activity implements PluginInterface{


    protected Activity that;
    @Override
    public void attach(Activity activity) {
        this.that = activity;
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onSaveInstateState(Bundle outState) {

    }

    @Override
    public void setContentView(View view) {
        if (that == null){
            super.setContentView(view);
        }else{
            this.setContentView(view);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        that.setContentView(layoutResID);

    }


    @Override
    public <T extends View> T findViewById(int id) {
        return that.findViewById(id);
    }

    @Override
    public Intent getIntent() {
        return that.getIntent();
    }

    @Override
    public ClassLoader getClassLoader() {
        return that.getClassLoader();
    }


    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        return that.getLayoutInflater();
    }


    @Override
    public void startActivity(Intent intent) {
        Intent intent1 = new Intent();
        //intent.getComponent().getClassName()获取带报名的activity
        intent1.putExtra("className",intent.getComponent().getClassName());
        that.startActivity(intent1);
    }

    @Override
    public ApplicationInfo getApplicationInfo() {
        return that.getApplicationInfo();
    }

    @Override
    public Window getWindow() {
        return that.getWindow();
    }

    @Override
    public WindowManager getWindowManager() {
        return that.getWindowManager();
    }


}
