package com.lookballs.app.imageloader;

import android.app.Application;

import com.lookballs.imageloader.ImageLoader;
import com.lookballs.imageloader.glide.GlideImageStrategy;
import com.lookballs.imageloader.glide.util.GlideUtil;

public class StartApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader();
    }

    /**
     * 初始化图片加载库
     */
    private void initImageLoader() {
        //图片加载库设置图片加载策略
        ImageLoader.setImageStrategy(new GlideImageStrategy());
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        //内存低的时候清理Glide缓存
        GlideUtil.onLowMemory(this);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        //内存低的时候清理Glide缓存
        if (level == TRIM_MEMORY_UI_HIDDEN) {
            GlideUtil.onLowMemory(this);
        }
        GlideUtil.onTrimMemory(this, level);
    }
}
