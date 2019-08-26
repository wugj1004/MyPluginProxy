package com.wugj.myplugin.plugincore;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * description: 替身/壳
 */
public class ProxyActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String className = getIntent().getStringExtra("className");

        try {
           Class<?> clazz = PluginManager.getInstance().getDexClassLoader().loadClass(className);
            Object newInstance = clazz.newInstance();
           if (newInstance instanceof PluginInterface){
               PluginInterface pluginInterface = (PluginInterface) newInstance;

               pluginInterface.attach(this);

               Bundle bundle = new Bundle();
               pluginInterface.onCreate(bundle);
           }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Resources getResources() {
        return PluginManager.getInstance().getResources();
    }

    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance().getDexClassLoader();
    }

    @Override
    public void startActivity(Intent intent) {
        String className = intent.getStringExtra("className");
        Intent intent1 = new Intent(this,ProxyActivity.class);
        intent1.putExtra("className",className);
        super.startActivity(intent1);
    }
}
