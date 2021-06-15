package com.lookballs.imageloader;

import android.widget.ImageView;

import com.lookballs.imageloader.impl.ImageStrategy;

/**
 * 这里是图片加载配置信息的基类，定义一些所有图片加载框架都可以用的通用参数
 * 每个{@link ImageStrategy} 应该对应一个{@link ImageOptions} 实现类
 */
public class ImageOptions {
    protected Object url;//图片地址
    protected ImageView imageView;//图片ImageView
    protected int placeholder;//加载中的占位图
    protected int errorPlaceholder;//加载失败的占位图

    public Object getUrl() {
        return url;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public int getPlaceholder() {
        return placeholder;
    }

    public int getErrorPlaceholder() {
        return errorPlaceholder;
    }
}
