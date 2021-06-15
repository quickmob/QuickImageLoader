package com.lookballs.imageloader.impl;

import android.content.Context;

import androidx.annotation.NonNull;

import com.lookballs.imageloader.ImageLoader;
import com.lookballs.imageloader.ImageOptions;

/**
 * 图片加载策略,实现 {@link ImageStrategy}
 * 并通过 {@link ImageLoader#setImageStrategy(ImageStrategy)} 配置后,才可进行图片请求
 */
public interface ImageStrategy<T extends ImageOptions> {

    /**
     * 加载图片
     *
     * @param mContext
     * @param config   图片加载配置信息
     */
    void loadImage(@NonNull Object mContext, @NonNull T config);

    /**
     * 停止加载
     *
     * @param mContext
     * @param config   图片加载配置信息
     */
    void clear(@NonNull Context mContext, @NonNull T config);
}
