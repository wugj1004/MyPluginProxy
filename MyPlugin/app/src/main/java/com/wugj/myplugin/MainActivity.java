package com.wugj.myplugin;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.wugj.myplugin.plugincore.PluginManager;
import com.wugj.myplugin.plugincore.ProxyActivity;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE_STORAGE = 20171222;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (hasPermission()) {
            Log.d(TAG,"loadPlugin");

            this.loadPlugin();
        } else {
            requestPermission();
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (PERMISSION_REQUEST_CODE_STORAGE == requestCode) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                requestPermission();
            } else {
                this.loadPlugin();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    private boolean hasPermission() {

        Log.d(TAG,"hasPermission");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    private void requestPermission() {

        Log.d(TAG,"requestPermission");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE_STORAGE);
        }
    }


    private void loadPlugin(){
        PluginManager pluginManager = PluginManager.getInstance();
        pluginManager.setCtx(this);

        String path = new StringBuffer(Environment.getExternalStorageDirectory().getAbsolutePath())
                .append("/other_debug.apk").toString();
        pluginManager.loadPath(path);
    }

    public void jumpActivity(View view) {
        Intent intent = new Intent().setClass(this, ProxyActivity.class);
        //拿到第三方插件的跟activity名字（包含报名）
        String otherApkMainActivity = PluginManager.getInstance().getPackageInfo().activities[0].name;
        intent.putExtra("className",otherApkMainActivity);

        startActivity(intent);
    }
}
