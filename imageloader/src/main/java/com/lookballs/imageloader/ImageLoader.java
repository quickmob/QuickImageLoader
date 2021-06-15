package com.lookballs.imageloader;

import android.content.Context;

import androidx.annotation.NonNull;

import com.lookballs.imageloader.impl.ImageStrategy;

/**
 * {@link ImageLoader} 使用策略模式和建造者模式，可以动态切换图片请求框架(比如说切换成Picasso)
 * 当需要切换图片请求框架或图片请求框架升级后变更了API时
 * 这里可以将影响范围降到最低，所以封装 {@link ImageLoader} 是为了屏蔽这个风险
 */
public class ImageLoader {

    private static ImageStrategy mStrategy;

    /**
     * 加载图片
     *
     * @param mContext
     * @param config
     * @param <T>
     */
    public static <T extends ImageOptions> void loadImage(@NonNull Object mContext, @NonNull T config) {
        if (mStrategy == null) {
            throw new NullPointerException(">>>Please setImageStrategy before");
        }
        mStrategy.loadImage(mContext, config);
    }

    /**
     * 清理缓存
     *
     * @param mContext
     * @param config
     * @param <T>
     */
    public static <T extends ImageOptions> void clear(@NonNull Context mContext, @NonNull T config) {
        if (mStrategy == null) {
            throw new NullPointerException(">>>Please setImageStrategy before");
        }
        mStrategy.clear(mContext, config);
    }

    /**
     * 可在运行时随意切换 {@link ImageStrategy}
     *
     * @param strategy
     */
    public static synchronized void setImageStrategy(@NonNull ImageStrategy strategy) {
        mStrategy = strategy;
    }

    public static ImageStrategy getImageStrategy() {
        return mStrategy;
    }
}
