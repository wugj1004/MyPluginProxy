package com.wugj.myplugin.plugincore;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * description:
 * </br>
 * author: wugj
 * </br>
 * date: 2019/8/22
 * </br>
 * version:
 */
public class PluginManager {

    private Resources resources;
    private PackageInfo packageInfo;
    private DexClassLoader dexClassLoader;
    private Context ctx;

    private static PluginManager instance;
    public static PluginManager getInstance(){
        if (instance == null){
            synchronized (PluginManager.class){
                if (instance ==null){
                    instance = new PluginManager();

                }
            }
        }
        return instance;
    }

    /**
     * 插件中上下文全部用的占坑activity的上下文
     * @param ctx
     */
    public void setCtx(Context ctx) {
        this.ctx = ctx;
    }

    public void loadPath(String path){
        //获取当前应用内部似有目录
        File dexOutFile = ctx.getDir("dex",Context.MODE_PRIVATE);
        //初始化类加载器
        dexClassLoader = new DexClassLoader(path,dexOutFile.getAbsolutePath(),null,ctx.getClassLoader());

        //包管理器
        PackageManager packageManager = ctx.getPackageManager();
        //通过PackageInfo可以获取到插件包中的类名"com.wugj.myplugin.pluginapp.MainActivity"
        packageInfo = packageManager.getPackageArchiveInfo(path,PackageManager.GET_ACTIVITIES);

        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = AssetManager.class.getMethod("addAssetPath",String.class);
            addAssetPath.invoke(assetManager,path);
            resources = new Resources(assetManager,ctx.getResources().getDisplayMetrics(),ctx.getResources().getConfiguration());



        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }

    public Resources getResources() {
        return resources;
    }

    public PackageInfo getPackageInfo() {
        return packageInfo;
    }

    public DexClassLoader getDexClassLoader() {
        return dexClassLoader;
    }


}
